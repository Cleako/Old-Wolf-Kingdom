package org.wolf_kingdom.gs.phandler.ls;

import org.apache.mina.common.IoSession;
import org.wolf_kingdom.gs.connection.LSPacket;
import org.wolf_kingdom.gs.connection.Packet;
import org.wolf_kingdom.gs.model.Player;
import org.wolf_kingdom.gs.model.World;
import org.wolf_kingdom.gs.phandler.PacketHandler;


public class FriendLogout implements PacketHandler {
    /**
     * World instance
     */
    public static final World world = World.getWorld();

    public void handlePacket(Packet p, IoSession session) throws Exception {
		long uID = ((LSPacket) p).getUID();
		long friend = p.readLong();
	
		switch (((LSPacket) p).getID()) {
			case 12:
			    for (Player player : world.getPlayers()) {
					if (player.isFriendsWith(friend)) {
					    player.getActionSender().sendFriendUpdate(friend, 0);
					}
			    }
			    break;
			case 13:
			    Player player = world.getPlayer(p.readLong());
			    if (player != null) {
			    	player.getActionSender().sendFriendUpdate(friend, 0);
			    }
			    break;
		}
    }
}