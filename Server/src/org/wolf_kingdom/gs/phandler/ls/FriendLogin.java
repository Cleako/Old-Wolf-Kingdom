package org.wolf_kingdom.gs.phandler.ls;

import org.apache.mina.common.IoSession;
import org.wolf_kingdom.gs.connection.LSPacket;
import org.wolf_kingdom.gs.connection.Packet;
import org.wolf_kingdom.gs.model.Player;
import org.wolf_kingdom.gs.model.World;
import org.wolf_kingdom.gs.phandler.PacketHandler;


public class FriendLogin implements PacketHandler {
    /**
     * World instance
     */
    public static final World world = World.getWorld();

    public void handlePacket(Packet p, IoSession session) throws Exception {
		long uID = ((LSPacket) p).getUID();
		Player player = world.getPlayer(p.readLong());
		if (player == null) {
		    return;
		}
		long friend = p.readLong();
		if (player.isFriendsWith(friend)) {
		    player.getActionSender().sendFriendUpdate(friend, p.readShort());
		}
    }

}