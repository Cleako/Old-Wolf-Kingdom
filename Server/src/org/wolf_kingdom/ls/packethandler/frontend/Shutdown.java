package org.wolf_kingdom.ls.packethandler.frontend;

import org.apache.mina.common.IoSession;
import org.wolf_kingdom.ls.Server;
import org.wolf_kingdom.ls.model.World;
import org.wolf_kingdom.ls.net.FPacket;
import org.wolf_kingdom.ls.net.Packet;
import org.wolf_kingdom.ls.packetbuilder.FPacketBuilder;
import org.wolf_kingdom.ls.packethandler.PacketHandler;


public class Shutdown implements PacketHandler {
    private static final FPacketBuilder builder = new FPacketBuilder();

    public void handlePacket(Packet p, IoSession session) throws Exception {
	String[] params = ((FPacket) p).getParameters();
	try {
	    int worldID = Integer.parseInt(params[0]);
	    if (worldID == 0) {
		for (World w : Server.getServer().getWorlds()) {
		    w.getActionSender().shutdown();
		}
	    } else {
		World w = Server.getServer().getWorld(worldID);
		if (w == null) {
		    throw new Exception("Unknown world");
		}
		w.getActionSender().shutdown();
	    }
	    builder.setID(1);
	} catch (Exception e) {
	    builder.setID(0);
	}
	FPacket packet = builder.toPacket();
	if (packet != null) {
	    session.write(packet);
	}
    }

}