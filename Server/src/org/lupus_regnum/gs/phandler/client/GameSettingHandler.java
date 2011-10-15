package org.lupus_regnum.gs.phandler.client;

import org.apache.mina.common.IoSession;
import org.lupus_regnum.gs.builders.ls.GameSettingUpdatePacketBuilder;
import org.lupus_regnum.gs.connection.LSPacket;
import org.lupus_regnum.gs.connection.Packet;
import org.lupus_regnum.gs.model.Player;
import org.lupus_regnum.gs.model.World;
import org.lupus_regnum.gs.phandler.PacketHandler;


public class GameSettingHandler implements PacketHandler {
    /**
     * World instance
     */
    public static final World world = World.getWorld();
   
    private GameSettingUpdatePacketBuilder builder = new GameSettingUpdatePacketBuilder();

    public void handlePacket(Packet p, IoSession session) throws Exception {
		Player player = (Player) session.getAttachment();
		int idx = (int) p.readByte();
		if (idx < 0 || idx > 6) {
		    player.setSuspiciousPlayer(true);
		    return;
		}
		boolean on = p.readByte() == 1;
		player.setGameSetting(idx, on);

		builder.setPlayer(player);
		builder.setIndex(idx);
		builder.setOn(on);

		LSPacket packet = builder.getPacket();
		if (packet != null) {
		    World.getWorld().getServer().getLoginConnector().getSession().write(packet);
		}
    }

}
