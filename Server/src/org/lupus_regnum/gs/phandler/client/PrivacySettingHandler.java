package org.lupus_regnum.gs.phandler.client;

import org.apache.mina.common.IoSession;
import org.lupus_regnum.gs.builders.ls.PrivacySettingUpdatePacketBuilder;
import org.lupus_regnum.gs.connection.LSPacket;
import org.lupus_regnum.gs.connection.Packet;
import org.lupus_regnum.gs.model.Player;
import org.lupus_regnum.gs.model.World;
import org.lupus_regnum.gs.phandler.PacketHandler;


public class PrivacySettingHandler implements PacketHandler {
    /**
     * World instance
     */
    public static final World world = World.getWorld();
    
    private PrivacySettingUpdatePacketBuilder builder = new PrivacySettingUpdatePacketBuilder();

    public void handlePacket(Packet p, IoSession session) throws Exception {
		Player player = (Player) session.getAttachment();
	
		boolean[] newSettings = new boolean[4];
		for (int i = 0; i < 4; i++) {
		    newSettings[i] = p.readByte() == 1;
		}
	
		builder.setPlayer(player);
		for (int i = 0; i < 4; i++) {
		    builder.setIndex(i);
		    if (newSettings[i] && !player.getPrivacySetting(i)) {
		    	builder.setOn(true);
		    } 
		    else if (!newSettings[i] && player.getPrivacySetting(i)) {
		    	builder.setOn(false);
		    } 
		    else {
		    	continue;
		    }
		    LSPacket packet = builder.getPacket();
		    if (packet != null) {
		    	World.getWorld().getServer().getLoginConnector().getSession().write(packet);
		    }
		}
		for (int i = 0; i < 4; i++) {
		    player.setPrivacySetting(i, newSettings[i]);
		}
    }

}
