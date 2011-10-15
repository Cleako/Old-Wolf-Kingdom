package org.lupus_regnum.ls.packethandler;

import org.apache.mina.common.IoSession;
import org.lupus_regnum.ls.net.Packet;


public interface PacketHandler {
    public void handlePacket(Packet p, IoSession session) throws Exception;
}
