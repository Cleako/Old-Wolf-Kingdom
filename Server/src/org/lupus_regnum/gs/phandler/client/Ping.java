package org.lupus_regnum.gs.phandler.client;

import org.apache.mina.common.IoSession;
import org.lupus_regnum.gs.connection.Packet;
import org.lupus_regnum.gs.model.Player;
import org.lupus_regnum.gs.phandler.PacketHandler;
import org.lupus_regnum.gs.util.Logger;


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
