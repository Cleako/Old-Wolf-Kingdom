package org.wolf_kingdom.gs.core;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.apache.mina.common.IoSession;
import org.wolf_kingdom.config.Config;
import org.wolf_kingdom.gs.connection.PacketQueue;
import org.wolf_kingdom.gs.connection.RSCPacket;
import org.wolf_kingdom.gs.event.DelayedEvent;
import org.wolf_kingdom.gs.model.ActiveTile;
import org.wolf_kingdom.gs.model.Player;
import org.wolf_kingdom.gs.model.Shop;
import org.wolf_kingdom.gs.model.World;
import org.wolf_kingdom.gs.model.snapshot.Snapshot;
import org.wolf_kingdom.gs.phandler.PacketHandler;
import org.wolf_kingdom.gs.phandler.PacketHandlerDef;
import org.wolf_kingdom.gs.plugins.PluginHandler;
import org.wolf_kingdom.gs.util.Logger;
import org.wolf_kingdom.gs.util.PersistenceManager;


/**
 * The central motor of the game. This class is responsible for the primary
 * operation of the entire game.
 */
public final class GameEngine extends Thread {

        /**
         * World instance
         */
        private static final World world = World.getWorld();

        /**
         * Responsible for updating all connected clients
         */
        private ClientUpdater clientUpdater = new ClientUpdater();
        /**
         * Handles delayed events rather than events to be ran every iteration
         */
        private DelayedEventHandler eventHandler = new DelayedEventHandler();
        /**
         * When the update loop was last ran, required for throttle
         */
        private long lastSentClientUpdate = System.currentTimeMillis();
        private long lastSentClientUpdateFast = System.currentTimeMillis();
        private long lastCleanedChatlogs = 0;
        /**
         * The mapping of packet IDs to their handler
         */
        private TreeMap<Integer, PacketHandler> packetHandlers = new TreeMap<Integer, PacketHandler>();
        /**
         * The packet queue to be processed
         */
        private PacketQueue<RSCPacket> packetQueue;
        /**
         * Whether the engine's thread is running
         */
        private boolean running = true;
        long time = 0;
        /**
         * Processes incoming packets.
         */
        private Map<String, Integer> written = Collections.synchronizedMap(new HashMap<String, Integer>());

        /**
         * Constructs a new game engine with an empty packet queue.
         */
        public GameEngine() {
                packetQueue = new PacketQueue<RSCPacket>();
                loadPacketHandlers();
                for (Shop shop : world.getShops()) {
                        shop.initRestock();
                }
                //redirectSystemStreams();
        }

        public void emptyWorld() {
                for (Player p : world.getPlayers()) {
                        p.save();
                        p.getActionSender().sendLogout();
                }
                World.getWorld().getServer().getLoginConnector().getActionSender().saveProfiles();
        }

        /**
         * Returns the current packet queue.
         *
         * @return A <code>PacketQueue</code>
         */
        public PacketQueue<RSCPacket> getPacketQueue() {
                return packetQueue;
        }

        public void kill() {
                Logger.println("Terminating GameEngine");
                running = false;
        }

        /**
         * Loads the packet handling classes from the persistence manager.
         */
        protected void loadPacketHandlers() {
                PacketHandlerDef[] handlerDefs = (PacketHandlerDef[]) PersistenceManager.load("PacketHandlers.xml");
                for (PacketHandlerDef handlerDef : handlerDefs) {
                        try {
                                String className = handlerDef.getClassName();
                                Class<?> c = Class.forName(className);
                                if (c != null) {
                                        PacketHandler handler = (PacketHandler) c.newInstance();
                                        for (int packetID : handlerDef.getAssociatedPackets()) {
                                                packetHandlers.put(packetID, handler);
                                        }
                                }
                        } catch (Exception e) {
                                Logger.error(e);
                        }
                }
        }
        private void processClients() {
                clientUpdater.sendQueuedPackets();
                long now = System.currentTimeMillis();
                if (now - lastSentClientUpdate >= 600) {
                        if (now - lastSentClientUpdate >= 1000) {
                                //Logger.println("MAJOR UPDATE DELAYED: " + (now - lastSentClientUpdate));
                        }
                        lastSentClientUpdate = now;
                        clientUpdater.doMajor();
                }
                if (now - lastSentClientUpdateFast >= 104) {
                        if (now - lastSentClientUpdateFast >= 6000) {
                                //Logger.println("MINOR UPDATE DELAYED: " + (now - lastSentClientUpdateFast));
                        }
                        lastSentClientUpdateFast = now;
                        clientUpdater.doMinor();
                }
        }

        private void processEvents() {
                eventHandler.doEvents();
        }

        private void processIncomingPackets() {
                for (RSCPacket p : packetQueue.getPackets()) {
                        IoSession session = p.getSession();
                        Player player = (Player) session.getAttachment();
                        if (player.getUsername() == null && p.getID() != 32 && p.getID() != 77 && p.getID() != 0) {
                                final String ip = player.getCurrentIP();
                                if (!written.containsKey(ip)) {
                                        eventHandler.add(new DelayedEvent(null, 1800000) {

                                                public void run() {
                                                        written.remove(ip);
                                                }
                                        });
                                        try {
                                                // Runtime.getRuntime().exec(
                                                // "sudo /sbin/route add " + ip + " gw 127.0.0.1");
                                        } catch (Exception err) {
                                                System.out.println(err);
                                        }
                                        Logger.println("Dummy packet from " + player.getCurrentIP() + ": " + p.getID());
                                        written.put(ip, 1);
                                }
                                continue;
                        }
                        PacketHandler handler = packetHandlers.get(p.getID());
                        player.ping();
                        if (handler != null) {
                                try {
                                        handler.handlePacket(p, session);
                                } catch (Exception e) {
                                        e.printStackTrace();
                                        player.getActionSender().sendLogout();
                                        player.destroy(false);
                                }
                        } else {
                                Logger.error("Unhandled packet from " + player.getCurrentIP() + ": " + p.getID() + "len: " + p.getLength());
                        }
                }
        }

        public void processLoginServer() {
                LoginConnector connector = World.getWorld().getServer().getLoginConnector();
                if (connector != null) {
                        connector.processIncomingPackets();
                        connector.sendQueuedPackets();
                }
        }

        /**
         * The thread execution process.
         */
        public void run() {
                Logger.println("GameEngine now running");
                time = System.currentTimeMillis();

                PluginHandler.getPluginHandler().handleAction("Startup", new Object[]{});
                

                eventHandler.add(new DelayedEvent(null, Config.GARBAGE_COLLECT_INTERVAL) {
                        @Override
                        public void run() {
                                new Thread(new Runnable() {
                                        public void run() {
                                                        garbageCollect();
                                                }
                                        }).start();
                        }}
                );
                
                eventHandler.add(new DelayedEvent(null, 1800000) {
                        @Override
                        public void run() {
                                new Thread(new Runnable() {
                                        public void run() {
                                                        World.getWorld().getDB().createConnection();
                                                }
                                        }).start();
                        }}
                );
                
                
                eventHandler.add(new DelayedEvent(null, Config.SAVE_INTERVAL) { // 5 min
                        public void run() {
                                Long now = System.currentTimeMillis();
                                for (Player p : world.getPlayers()) {
                                        //if (now - p.getLastSaveTime() >= Config.SAVE_INTERVAL) {
                                        if (world.countPlayers() > 0) {
                                                p.save();
                                                p.setLastSaveTime(now);
                                                //Logger.println("Automatic server save complete.");
                                        }
                                }
                                World.getWorld().getServer().getLoginConnector().getActionSender().saveProfiles();
                        }
                });
                while (running) {
                        try {
                                Thread.sleep(50);
                        } 
                        catch (InterruptedException ie) {}

                        processLoginServer();
                        processIncomingPackets();
                        processEvents();
                        processClients();
                        cleanSnapshotDeque();
                
                }
        }
        /**
         * Cleans snapshots of entries over 60 seconds old (executed every second)
         */
        public void cleanSnapshotDeque() {
                long curTime = System.currentTimeMillis();
                if (curTime - lastCleanedChatlogs > 1000) {
                        lastCleanedChatlogs = curTime;
                        Iterator<Snapshot> i = world.getSnapshots().descendingIterator();
                        Snapshot s = null;
                        synchronized (i) {
                                while (i.hasNext()) {
                                        s = i.next();
                                        if (curTime - s.getTimestamp() > 60000) {
                                                i.remove();
                                                s = null;
                                                }
                                        else {
                                                s = null;
                                        }
                                }
                                i = null;
                        }
                        
                }
        }
        /**
         * Cleans garbage (Tilecleanup)
         */
        public synchronized void garbageCollect() {
                long startTime = System.currentTimeMillis();
                int curMemory = (int) (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1000;
                int tileObjs = 0;
                int cleaned = 0;
                for (int i = 0; i < World.getWorld().tiles.length; i++) {
                        for (int in = 0; in < World.getWorld().tiles[i].length; in++) {
                                ActiveTile tile = World.getWorld().tiles[i][in];
                                if (tile != null) {
                                        tileObjs++;
                                        if (!tile.hasGameObject() && !tile.hasItems() && !tile.hasNpcs() && !tile.hasPlayers()) {
                                                World.getWorld().tiles[i][in] = null;
                                                cleaned++;
                                        }
                                }
                        }
                }
                Runtime.getRuntime().gc();
                int newMemory = (int) (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1000;
                //Logger.println("GARBAGE COLLECT | Executing Memory Cleanup");
                //Logger.println("GARBAGE COLLECT | Memory before: " + curMemory + "kb" + " Memory after: " + newMemory + " (Freed: " + (curMemory - newMemory) + "kb)");
                //Logger.println("GARBAGE COLLECT | Cleanup took " + (System.currentTimeMillis() - startTime) + "ms");
        }
        
}