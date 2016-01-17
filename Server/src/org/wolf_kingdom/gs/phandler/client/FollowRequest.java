package org.wolf_kingdom.gs.phandler.client;

import org.apache.mina.common.IoSession;
import org.wolf_kingdom.gs.connection.Packet;
import org.wolf_kingdom.gs.model.Player;
import org.wolf_kingdom.gs.model.World;
import org.wolf_kingdom.gs.model.snapshot.Activity;
import org.wolf_kingdom.gs.phandler.PacketHandler;


public class FollowRequest implements PacketHandler {
    /**
     * World instance
     */
    public static final World world = World.getWorld();

    public void handlePacket(Packet p, IoSession session) throws Exception {
	Player player = (Player) session.getAttachment();
	Player affectedPlayer = world.getPlayer(p.readShort());
	if (affectedPlayer == null) {
	    player.setSuspiciousPlayer(true);
	    return;
	}
	if (player.isBusy()) {
	    player.resetPath();
	    return;
	}
	if (System.currentTimeMillis() - player.getLastRun() < 3000)
	    return;
	player.resetAll();
	player.setFollowing(affectedPlayer, 1);
	player.getActionSender().sendMessage("Now following " + affectedPlayer.getUsername());
	world.addEntryToSnapshots(new Activity(player.getUsername(), player.getUsername() + " started to follow " + affectedPlayer.getUsername() + " at: " + player.getX() + "/" + player.getY() + " | " + affectedPlayer.getX() + "/" + affectedPlayer.getY()));

    }
}
