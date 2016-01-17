package org.wolf_kingdom.gs.phandler.ls;

import org.apache.mina.common.IoSession;
import org.wolf_kingdom.gs.connection.LSPacket;
import org.wolf_kingdom.gs.connection.Packet;
import org.wolf_kingdom.gs.model.Player;
import org.wolf_kingdom.gs.model.World;
import org.wolf_kingdom.gs.phandler.PacketHandler;


public class ReceivePM implements PacketHandler {
    /**
     * World instance
     */
    public static final World world = World.getWorld();

    public void handlePacket(Packet p, IoSession session) throws Exception {
		long uID = ((LSPacket) p).getUID();
		long sender = p.readLong();
		Player recipient = world.getPlayer(p.readLong());
		boolean avoidBlock = p.readByte() == 1;
		if (recipient == null || !recipient.loggedIn()) {
		    return;
		}
		if (recipient.getPrivacySetting(1) && !recipient.isFriendsWith(sender) && !avoidBlock) {
		    return;
		}
		if (recipient.isIgnoring(sender) && !avoidBlock) {
		    return;
		}
		recipient.getActionSender().sendPrivateMessage(sender, p.getRemainingData());
    }

}