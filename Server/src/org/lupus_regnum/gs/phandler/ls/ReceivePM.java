package org.lupus_regnum.gs.phandler.ls;

import org.apache.mina.common.IoSession;
import org.lupus_regnum.gs.connection.LSPacket;
import org.lupus_regnum.gs.connection.Packet;
import org.lupus_regnum.gs.model.Player;
import org.lupus_regnum.gs.model.World;
import org.lupus_regnum.gs.phandler.PacketHandler;


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