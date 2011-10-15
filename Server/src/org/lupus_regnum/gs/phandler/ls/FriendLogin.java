package org.lupus_regnum.gs.phandler.ls;

import org.apache.mina.common.IoSession;
import org.lupus_regnum.gs.connection.LSPacket;
import org.lupus_regnum.gs.connection.Packet;
import org.lupus_regnum.gs.model.Player;
import org.lupus_regnum.gs.model.World;
import org.lupus_regnum.gs.phandler.PacketHandler;


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