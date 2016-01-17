package org.wolf_kingdom.ls.packethandler.loginserver;

import java.sql.ResultSet;

import org.apache.mina.common.IoSession;
import org.wolf_kingdom.ls.Server;
import org.wolf_kingdom.ls.model.World;
import org.wolf_kingdom.ls.net.LSPacket;
import org.wolf_kingdom.ls.net.Packet;
import org.wolf_kingdom.ls.packetbuilder.loginserver.ReplyPacketBuilder;
import org.wolf_kingdom.ls.packethandler.PacketHandler;
import org.wolf_kingdom.ls.util.DataConversions;


public class BanHandler implements PacketHandler {
    private ReplyPacketBuilder builder = new ReplyPacketBuilder();

    public void handlePacket(Packet p, IoSession session) throws Exception {
	final long uID = ((LSPacket) p).getUID();
	boolean banned = ((LSPacket) p).getID() == 4;
	long user = p.readLong();
	long modhash = p.readLong();

	ResultSet result = Server.db.getQuery("SELECT u.group_id, p.playermod, p.owner FROM `users` AS u INNER JOIN `wk_players` AS p ON p.owner=u.id WHERE p.user='" + user + "'");
	if (!result.next()) {
	    builder.setSuccess(false);
	    builder.setReply("There is not an account by that username");
	} else if (banned && (result.getInt("group_id") < 3 || result.getInt("playermod") == 1)) {
	    builder.setSuccess(false);
	    builder.setReply("You cannot ban a (p)mod or admin!");
	} else if (Server.db.updateQuery("UPDATE `wk_players` SET `banned`='" + (banned ? "1" : "0") + "' WHERE `user` LIKE '" + user + "'") == 0) {
	    builder.setSuccess(false);
	    builder.setReply("There is not an account by that username");
	} else {
	    World w = Server.getServer().findWorld(user);
	    if (w != null) {
		w.getActionSender().logoutUser(user);
	    }
	    if (banned)
		Server.db.updateQuery("INSERT `wk_banlog` VALUES('" + user + "','" + modhash + "','" + (System.currentTimeMillis() / 1000) + "')");
	    builder.setSuccess(true);
	    builder.setReply(DataConversions.hashToUsername(user) + " has been " + (banned ? "banned" : "unbanned"));
	}
	builder.setUID(uID);

	LSPacket temp = builder.getPacket();
	if (temp != null) {
	    session.write(temp);
	}

    }

}
