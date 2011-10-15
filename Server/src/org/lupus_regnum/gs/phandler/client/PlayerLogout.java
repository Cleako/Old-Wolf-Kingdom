package org.lupus_regnum.gs.phandler.client;

import org.apache.mina.common.IoSession;
import org.lupus_regnum.gs.connection.Packet;
import org.lupus_regnum.gs.model.World;
import org.lupus_regnum.gs.phandler.PacketHandler;


public class PlayerLogout implements PacketHandler {
    /**
     * World instance
     */
    public static final World world = World.getWorld();

    public void handlePacket(Packet p, IoSession session) throws Exception {
    }
}
