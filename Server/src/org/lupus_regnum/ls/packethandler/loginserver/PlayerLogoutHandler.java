package org.lupus_regnum.ls.packethandler.loginserver;

import org.apache.mina.common.IoSession;
import org.lupus_regnum.ls.model.World;
import org.lupus_regnum.ls.net.Packet;
import org.lupus_regnum.ls.packethandler.PacketHandler;


public class PlayerLogoutHandler implements PacketHandler {

    public void handlePacket(Packet p, IoSession session) throws Exception {
	long user = p.readLong();
	World world = (World) session.getAttachment();
	world.unregisterPlayer(user);
    }
}
