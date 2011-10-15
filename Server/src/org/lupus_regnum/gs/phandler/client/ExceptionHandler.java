package org.lupus_regnum.gs.phandler.client;

import org.apache.mina.common.IoSession;
import org.lupus_regnum.gs.connection.Packet;
import org.lupus_regnum.gs.model.Player;
import org.lupus_regnum.gs.model.World;
import org.lupus_regnum.gs.phandler.PacketHandler;
import org.lupus_regnum.gs.util.Logger;


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