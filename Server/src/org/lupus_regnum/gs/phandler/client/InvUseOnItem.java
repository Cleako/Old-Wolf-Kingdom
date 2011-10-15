package org.lupus_regnum.gs.phandler.client;

import org.apache.mina.common.IoSession;
import org.lupus_regnum.gs.connection.Packet;
import org.lupus_regnum.gs.model.InvItem;
import org.lupus_regnum.gs.model.Player;
import org.lupus_regnum.gs.model.World;
import org.lupus_regnum.gs.model.snapshot.Activity;
import org.lupus_regnum.gs.phandler.PacketHandler;
import org.lupus_regnum.gs.plugins.PluginHandler;



public class InvUseOnItem implements PacketHandler {
    /**
     * World instance
     */
    public static final World world = World.getWorld();

    public void handlePacket(Packet p, IoSession session) throws Exception {
		Player player = (Player) session.getAttachment();
		if (player.isBusy()) {
		    player.resetPath();
		    return;
		}
		player.resetAll();
		InvItem item1 = player.getInventory().get(p.readShort());
		InvItem item2 = player.getInventory().get(p.readShort());
		if (item1 == null || item2 == null) {
		    player.setSuspiciousPlayer(true);
		    return;
		}
		world.addEntryToSnapshots(new Activity(player.getUsername(), player.getUsername() + " used item " + item1.getDef().getName() + "(" + item1.getID() + ")" + " [CMD: "+item1.getDef().getCommand()+"] ON A ANOTHER INV ITEM "+ item2.getDef().getName() + "(" + item2.getID() + ")" + " [CMD: "+item2.getDef().getCommand()+"] at: " + player.getX() + "/" + player.getY()));
		if(PluginHandler.getPluginHandler().blockDefaultAction("InvUseOnItem", new Object[] { player, item1, item2 }))
			return;
		player.getActionSender().sendMessage("Nothing interesting happens");
    }
}
