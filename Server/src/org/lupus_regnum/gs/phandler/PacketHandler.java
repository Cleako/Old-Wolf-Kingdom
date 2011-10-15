package org.lupus_regnum.gs.phandler;

import org.apache.mina.common.IoSession;
import org.lupus_regnum.gs.connection.Packet;


public interface PacketHandler {
    public void handlePacket(Packet p, IoSession session) throws Exception;
}
