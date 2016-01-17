package org.wolf_kingdom.ls.packethandler;

import org.apache.mina.common.IoSession;
import org.wolf_kingdom.ls.net.Packet;


public interface PacketHandler {
    public void handlePacket(Packet p, IoSession session) throws Exception;
}
