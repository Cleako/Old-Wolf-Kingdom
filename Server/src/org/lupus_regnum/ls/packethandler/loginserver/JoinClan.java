package org.lupus_regnum.ls.packethandler.loginserver;

import org.lupus_regnum.ls.packethandler.PacketHandler;
import org.lupus_regnum.ls.model.World;
import org.lupus_regnum.ls.net.LSPacket;
import org.lupus_regnum.ls.net.Packet;
import org.lupus_regnum.ls.Server;

import org.apache.mina.common.IoSession;

import java.sql.SQLException;

public class JoinClan implements PacketHandler {
        
		public void handlePacket(Packet p, IoSession session) throws Exception {
			long user = p.readLong();
			long clanName = p.readLong();
				try { Server.db.updateQuery("INSERT INTO `rsca2_clans`(`member`, `clan_name`) VALUES('" + user + "', '" + clanName + "')"); } catch(SQLException e) { }
        }
}