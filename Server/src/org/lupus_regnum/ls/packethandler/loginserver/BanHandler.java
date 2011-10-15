package org.lupus_regnum.ls.packethandler.loginserver;

import java.sql.ResultSet;

import org.apache.mina.common.IoSession;
import org.lupus_regnum.ls.Server;
import org.lupus_regnum.ls.model.World;
import org.lupus_regnum.ls.net.LSPacket;
import org.lupus_regnum.ls.net.Packet;
import org.lupus_regnum.ls.packetbuilder.loginserver.ReplyPacketBuilder;
import org.lupus_regnum.ls.packethandler.PacketHandler;
import org.lupus_regnum.ls.util.DataConversions;


public class BanHandler implements PacketHandler {
    private ReplyPacketBuilder builder = new ReplyPacketBuilder();

    public void handlePacket(Packet p, IoSession session) throws Exception {
	final long uID = ((LSPacket) p).getUID();
	boolean banned = ((LSPacket) p).getID() == 4;
	long user = p.readLong();
	long modhash = p.readLong();

	ResultSet result = Server.db.getQuery("SELECT u.group_id, p.playermod, p.owner FROM `users` AS u INNER JOIN `rsca2_players` AS p ON p.owner=u.id WHERE p.user='" + user + "'");
	if (!result.next()) {
	    builder.setSuccess(false);
	    builder.setReply("There is not an account by that username");
	} else if (banned && (result.getInt("group_id") < 3 || result.getInt("playermod") == 1)) {
	    builder.setSuccess(false);
	    builder.setReply("You cannot ban a (p)mod or admin!");
	} else if (Server.db.updateQuery("UPDATE `rsca2_players` SET `banned`='" + (banned ? "1" : "0") + "' WHERE `user` LIKE '" + user + "'") == 0) {
	    builder.setSuccess(false);
	    builder.setReply("There is not an account by that username");
	} else {
	    World w = Server.getServer().findWorld(user);
	    if (w != null) {
		w.getActionSender().logoutUser(user);
	    }
	    if (banned)
		Server.db.updateQuery("INSERT `rsca2_banlog` VALUES('" + user + "','" + modhash + "','" + (System.currentTimeMillis() / 1000) + "')");
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
