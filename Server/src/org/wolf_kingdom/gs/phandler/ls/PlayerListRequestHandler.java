package org.wolf_kingdom.gs.phandler.ls;

import org.apache.mina.common.IoSession;
import org.wolf_kingdom.gs.builders.ls.PlayerListRequestPacketBuilder;
import org.wolf_kingdom.gs.connection.LSPacket;
import org.wolf_kingdom.gs.connection.Packet;
import org.wolf_kingdom.gs.model.World;
import org.wolf_kingdom.gs.phandler.PacketHandler;
import org.wolf_kingdom.gs.util.Logger;


public class PlayerListRequestHandler implements PacketHandler {
    /**
     * World instance
     */
    public static final World world = World.getWorld();
   
    private PlayerListRequestPacketBuilder builder = new PlayerListRequestPacketBuilder();

    public void handlePacket(Packet p, IoSession session) throws Exception {
		long uID = ((LSPacket) p).getUID();
		Logger.event("LOGIN_SERVER requested player list (uID: " + uID + ")");
		builder.setUID(uID);
		LSPacket temp = builder.getPacket();
		if (temp != null) {
		    session.write(temp);
		}
    }

}