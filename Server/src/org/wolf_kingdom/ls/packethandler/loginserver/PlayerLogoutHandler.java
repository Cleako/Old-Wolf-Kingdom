package org.wolf_kingdom.ls.packethandler.loginserver;

import org.apache.mina.common.IoSession;
import org.wolf_kingdom.ls.model.World;
import org.wolf_kingdom.ls.net.Packet;
import org.wolf_kingdom.ls.packethandler.PacketHandler;


public class PlayerLogoutHandler implements PacketHandler {

    public void handlePacket(Packet p, IoSession session) throws Exception {
	long user = p.readLong();
	World world = (World) session.getAttachment();
	world.unregisterPlayer(user);
    }
}
