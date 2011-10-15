package org.lupus_regnum.ls.packethandler.loginserver;

import java.sql.SQLException;

import org.apache.mina.common.IoSession;
import org.lupus_regnum.ls.Server;
import org.lupus_regnum.ls.net.Packet;
import org.lupus_regnum.ls.packethandler.PacketHandler;


public class KillHandler implements PacketHandler {

    public void handlePacket(Packet p, IoSession session) throws Exception {
	try {
	    Server.db.updateQuery("INSERT INTO `rsca2_kills`(`user`, `killed`, `time`, `type`) VALUES('" + p.readLong() + "', '" + p.readLong() + "', " + (int) (System.currentTimeMillis() / 1000) + ", " + p.readByte() + ")");
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

}
