package org.wolf_kingdom.ls.packethandler.loginserver;

import java.sql.SQLException;

import org.apache.mina.common.IoSession;
import org.wolf_kingdom.ls.Server;
import org.wolf_kingdom.ls.model.World;
import org.wolf_kingdom.ls.net.Packet;
import org.wolf_kingdom.ls.packethandler.PacketHandler;


public class GameSettingHandler implements PacketHandler {

    public void handlePacket(Packet p, IoSession session) throws Exception {
	World world = (World) session.getAttachment();
	long user = p.readLong();
	boolean on = p.readByte() == 1;
	int idx = (int) p.readByte();
	switch (idx) {
	case 0: // Camera Auto
	    try {
		Server.db.updateQuery("UPDATE `wk_players` SET cameraauto=" + (on ? 1 : 0) + " WHERE user='" + user + "'");
	    } catch (SQLException e) {
		Server.error(e.getMessage());
	    }
	    break;
	case 2: // One Mouse
	    try {
		Server.db.updateQuery("UPDATE `wk_players` SET onemouse=" + (on ? 1 : 0) + " WHERE user='" + user + "'");
	    } catch (SQLException e) {
		Server.error(e.getMessage());
	    }
	    break;
	case 3: // Sound Off
	    try {
		Server.db.updateQuery("UPDATE `wk_players` SET soundoff=" + (on ? 1 : 0) + " WHERE user='" + user + "'");
	    } catch (SQLException e) {
		Server.error(e.getMessage());
	    }
	    break;
	case 4: // Show Roof
	    try {
		Server.db.updateQuery("UPDATE `wk_players` SET showroof=" + (on ? 1 : 0) + " WHERE user='" + user + "'");
	    } catch (SQLException e) {
		Server.error(e.getMessage());
	    }
	    break;
	case 5: // Auto Screenshot
	    try {
		Server.db.updateQuery("UPDATE `wk_players` SET autoscreenshot=" + (on ? 1 : 0) + " WHERE user='" + user + "'");
	    } catch (SQLException e) {
		Server.error(e.getMessage());
	    }
	    break;
	case 6: // Combat Window
	    try {
		Server.db.updateQuery("UPDATE `wk_players` SET combatwindow=" + (on ? 1 : 0) + " WHERE user='" + user + "'");
	    } catch (SQLException e) {
		Server.error(e.getMessage());
	    }
	    break;
	}
	Server.getServer().findSave(user, world).setGameSetting(idx, on);
    }

}
