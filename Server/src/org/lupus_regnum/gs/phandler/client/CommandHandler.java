package org.lupus_regnum.gs.phandler.client;

import org.apache.mina.common.IoSession;
import org.lupus_regnum.gs.connection.Packet;
import org.lupus_regnum.gs.model.Player;
import org.lupus_regnum.gs.model.World;
import org.lupus_regnum.gs.phandler.PacketHandler;
import org.lupus_regnum.gs.plugins.PluginHandler;



public class CommandHandler implements PacketHandler {
    /**
     * World instance
     */
    public static final World world = World.getWorld();

    public void handlePacket(Packet p, IoSession session) throws Exception {
		Player player = (Player) session.getAttachment();
		player.resetAll();
		String s = new String(p.getData()).trim();
		int firstSpace = s.indexOf(" ");
		String cmd = s;
		String[] args = new String[0];
		if (firstSpace != -1) {
		    cmd = s.substring(0, firstSpace).trim();
		    args = s.substring(firstSpace + 1).trim().split(" ");
		}
		if (player.isBusy() && !cmd.equalsIgnoreCase("say")) {
		    player.resetPath();
		    return;
		}
		PluginHandler.getPluginHandler().handleAction("Command", new Object[] { cmd.toLowerCase(), args, player }); 
    }
}
