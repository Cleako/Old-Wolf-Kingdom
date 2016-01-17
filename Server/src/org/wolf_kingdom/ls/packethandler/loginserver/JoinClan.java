package org.wolf_kingdom.ls.packethandler.loginserver;

import org.wolf_kingdom.ls.packethandler.PacketHandler;
import org.wolf_kingdom.ls.model.World;
import org.wolf_kingdom.ls.net.LSPacket;
import org.wolf_kingdom.ls.net.Packet;
import org.wolf_kingdom.ls.Server;

import org.apache.mina.common.IoSession;

import java.sql.SQLException;

public class JoinClan implements PacketHandler {
        
		public void handlePacket(Packet p, IoSession session) throws Exception {
			long user = p.readLong();
			long clanName = p.readLong();
				try { Server.db.updateQuery("INSERT INTO `wk_clans`(`member`, `clan_name`) VALUES('" + user + "', '" + clanName + "')"); } catch(SQLException e) { }
        }
}