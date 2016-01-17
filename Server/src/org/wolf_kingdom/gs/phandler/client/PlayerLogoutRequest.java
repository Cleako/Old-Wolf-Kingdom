package org.wolf_kingdom.gs.phandler.client;

import org.apache.mina.common.IoSession;
import org.wolf_kingdom.gs.connection.Packet;
import org.wolf_kingdom.gs.model.Player;
import org.wolf_kingdom.gs.model.World;
import org.wolf_kingdom.gs.phandler.PacketHandler;
import org.wolf_kingdom.gs.plugins.PluginHandler;
import org.wolf_kingdom.gs.event.Party;


public class PlayerLogoutRequest implements PacketHandler {
    /**
     * World instance
     */
    public static final World world = World.getWorld();

    public void handlePacket(Packet p, IoSession session) throws Exception {
		Player player = (Player) session.getAttachment();
		
		if(PluginHandler.getPluginHandler().blockDefaultAction("PlayerLogout", new Object[] { player }, false)) {
			player.getActionSender().sendCantLogout();
			return;
		}
		
		if (player.canLogout()) {
			/**
		     * Destroy player
		     */
			Party party = new Party(player);
			party.removePlayer();
			player.destroy(true);
		} 
		else {
		    player.getActionSender().sendCantLogout();
		}
    }
}
