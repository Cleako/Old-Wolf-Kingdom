package org.wolf_kingdom.gs.phandler.ls;

import org.apache.mina.common.IoSession;
import org.wolf_kingdom.gs.connection.LSPacket;
import org.wolf_kingdom.gs.connection.Packet;
import org.wolf_kingdom.gs.model.Player;
import org.wolf_kingdom.gs.model.World;
import org.wolf_kingdom.gs.phandler.PacketHandler;
import org.wolf_kingdom.gs.util.Logger;


public class UpdateHandler implements PacketHandler {
    /**
     * World instance
     */
    public static final World world = World.getWorld();

    public void handlePacket(Packet p, IoSession session) throws Exception {
		long uID = ((LSPacket) p).getUID();
		Logger.event("LOGIN_SERVER sent update (uID: " + uID + ")");
		String reason = p.readString();
		if (World.getWorld().getServer().shutdownForUpdate()) {
		    for (Player player : world.getPlayers()) {
				player.getActionSender().sendAlert("The server will be shutting down in 60 seconds: " + reason, false);
				player.getActionSender().startShutdown(60);
		    }
		}
    }

}