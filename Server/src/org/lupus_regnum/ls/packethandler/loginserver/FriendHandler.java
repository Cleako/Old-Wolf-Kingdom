package org.lupus_regnum.ls.packethandler.loginserver;

import java.sql.SQLException;

import org.apache.mina.common.IoSession;
import org.lupus_regnum.ls.Server;
import org.lupus_regnum.ls.model.PlayerSave;
import org.lupus_regnum.ls.model.World;
import org.lupus_regnum.ls.net.LSPacket;
import org.lupus_regnum.ls.net.Packet;
import org.lupus_regnum.ls.packethandler.PacketHandler;


public class FriendHandler implements PacketHandler {

    public void handlePacket(Packet p, IoSession session) throws Exception {
	World world = (World) session.getAttachment();
	Server server = Server.getServer();

	long user = p.readLong();
	long friend = p.readLong();

	World w;
	PlayerSave save = server.findSave(user, world);
	switch (((LSPacket) p).getID()) {
	case 10: // Send PM
	    boolean avoidBlock = p.readByte() == 1;
	    byte[] message = p.getRemainingData();
	    w = server.findWorld(friend);
	    if (w != null) {
		w.getActionSender().sendPM(user, friend, avoidBlock, message);
	    }
	    break;
	case 11: // Add friend
	    try {
		save.addFriend(friend);
		Server.db.updateQuery("INSERT INTO `rsca2_friends`(`user`, `friend`) VALUES('" + user + "', '" + friend + "')");
		if (Server.db.getQuery("SELECT 1 FROM `rsca2_players` AS p LEFT JOIN `rsca2_friends` AS f ON f.user=p.user WHERE (p.block_private=0 OR f.friend='" + user + "') AND p.user='" + friend + "'").next()) {
		    w = server.findWorld(friend);
		    if (w != null) {
			world.getActionSender().friendLogin(user, friend, w.getID());
		    }
		}
		if (Server.db.getQuery("SELECT 1 FROM `rsca2_players` AS p LEFT JOIN `rsca2_friends` AS f ON f.friend=p.user WHERE p.block_private=1 AND f.user='" + friend + "' AND p.user='" + user + "'").next()) {
		    w = server.findWorld(friend);
		    if (w != null) {
			w.getActionSender().friendLogin(friend, user, world.getID());
		    }
		}
	    } catch (SQLException e) {
		Server.error(e.getMessage());
	    }
	    break;
	case 12: // Remove friend
	    try {
		save.removeFriend(friend);
		Server.db.updateQuery("DELETE FROM `rsca2_friends` WHERE `user` LIKE '" + user + "' AND `friend` LIKE '" + friend + "'");
		if (Server.db.getQuery("SELECT 1 FROM `rsca2_players` WHERE block_private=1 AND user='" + user + "'").next()) {
		    w = server.findWorld(friend);
		    if (w != null) {
			w.getActionSender().friendLogout(friend, user);
		    }
		}
	    } catch (SQLException e) {
		Server.error(e.getMessage());
	    }
	    break;
	case 13: // Add ignore
	    try {
		save.addIgnore(friend);
		Server.db.updateQuery("INSERT INTO `rsca2_ignores`(`user`, `ignore`) VALUES('" + user + "', '" + friend + "')");
	    } catch (SQLException e) {
		Server.error(e.getMessage());
	    }
	    break;
	case 14: // Remove ignore
	    try {
		save.removeIgnore(friend);
		Server.db.updateQuery("DELETE FROM `rsca2_ignores` WHERE `user` LIKE '" + user + "' AND `ignore` LIKE '" + friend + "'");
	    } catch (SQLException e) {
		Server.error(e.getMessage());
	    }
	    break;
	}
    }

}
