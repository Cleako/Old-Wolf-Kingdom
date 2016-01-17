package org.wolf_kingdom.gs.phandler;

import org.apache.mina.common.IoSession;
import org.wolf_kingdom.gs.connection.Packet;


public interface PacketHandler {
    public void handlePacket(Packet p, IoSession session) throws Exception;
}
