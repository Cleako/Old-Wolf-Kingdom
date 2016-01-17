package org.wolf_kingdom.gs.phandler.client;

import org.apache.mina.common.IoSession;
import org.wolf_kingdom.gs.connection.Packet;
import org.wolf_kingdom.gs.model.Player;
import org.wolf_kingdom.gs.phandler.PacketHandler;
import org.wolf_kingdom.gs.util.Logger;


public class Ping implements PacketHandler {
    public void handlePacket(Packet p, IoSession session) throws Exception {
	Player player = (Player) session.getAttachment();
	if (p.getLength() > 0) {
	    byte b = p.readByte();
	    if (b == 1) { // 1 is for SCAR.
		if (player.sessionFlags < 1) {
			Logger.println(player.getUsername() + " is using SCAR!");
		    player.sessionFlags++;
		}
	    }
	}
    }
}
