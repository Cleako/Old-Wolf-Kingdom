package org.wolf_kingdom.gs.phandler.client;

import org.apache.mina.common.IoSession;
import org.wolf_kingdom.gs.connection.Packet;
import org.wolf_kingdom.gs.event.DelayedEvent;
import org.wolf_kingdom.gs.model.InvItem;
import org.wolf_kingdom.gs.model.Item;
import org.wolf_kingdom.gs.model.Player;
import org.wolf_kingdom.gs.model.World;
import org.wolf_kingdom.gs.phandler.PacketHandler;
import org.wolf_kingdom.gs.plugins.PluginHandler;
import org.wolf_kingdom.gs.states.Action;


public class DropHandler implements PacketHandler {
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
		final int idx = (int) p.readShort();
		if (idx < 0 || idx >= player.getInventory().size()) {
		    player.setSuspiciousPlayer(true);
		    return;
		}
		final InvItem item = player.getInventory().get(idx);
		if (item == null) {
		    player.setSuspiciousPlayer(true);
		    return;
		}
		if(player.isPMod() && !player.isMod())
		    return;
		player.setStatus(Action.DROPPING_GITEM);
		World.getWorld().getDelayedEventHandler().add(new DelayedEvent(player, 500) {
		    public void run() {
			if (owner.isBusy() || !owner.getInventory().contains(item) || owner.getStatus() != Action.DROPPING_GITEM) {
			    matchRunning = false;
			    return;
			}
			if (owner.hasMoved()) {
			    return;
			}
			if(PluginHandler.getPluginHandler().blockDefaultAction("Drop", new Object[] { owner, item })) {
				return;
			}
			owner.getActionSender().sendSound("dropobject");
			owner.getInventory().remove(item);
			owner.getActionSender().sendInventory();
			world.registerItem(new Item(item.getID(), owner.getX(), owner.getY(), item.getAmount(), owner));
			matchRunning = false;
		    }
		});
    }
}
