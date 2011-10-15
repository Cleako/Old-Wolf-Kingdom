package org.lupus_regnum.gs.phandler.ls;

import org.apache.mina.common.IoSession;
import org.lupus_regnum.gs.connection.LSPacket;
import org.lupus_regnum.gs.connection.Packet;
import org.lupus_regnum.gs.model.World;
import org.lupus_regnum.gs.phandler.PacketHandler;
import org.lupus_regnum.gs.util.Logger;


public class ShutdownHandler implements PacketHandler {
    /**
     * World instance
     */
    public static final World world = World.getWorld();

    public void handlePacket(Packet p, IoSession session) throws Exception {
		long uID = ((LSPacket) p).getUID();
		Logger.event("LOGIN_SERVER requested shutdown");
		World.getWorld().getServer().kill();
    }

}