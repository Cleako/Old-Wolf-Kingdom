package org.wolf_kingdom.gs.phandler.client;

import org.apache.mina.common.IoSession;
import org.wolf_kingdom.gs.connection.Packet;
import org.wolf_kingdom.gs.model.Player;
import org.wolf_kingdom.gs.model.World;
import org.wolf_kingdom.gs.phandler.PacketHandler;
import org.wolf_kingdom.gs.util.Logger;


public class ExceptionHandler implements PacketHandler {
    /**
     * World instance
     */
    public static final World world = World.getWorld();

    public void handlePacket(Packet p, IoSession session) throws Exception {
    	try {
	    	Player player = (Player) session.getAttachment();
			Logger.error("[CLIENT] Exception from " + player.getUsername() + ": " + p.readString());
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    }
}