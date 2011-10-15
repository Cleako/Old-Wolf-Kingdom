package org.lupus_regnum.gs.phandler.client;

import org.apache.mina.common.IoSession;
import org.lupus_regnum.gs.connection.Packet;
import org.lupus_regnum.gs.model.Mob;
import org.lupus_regnum.gs.model.Npc;
import org.lupus_regnum.gs.model.Player;
import org.lupus_regnum.gs.model.World;
import org.lupus_regnum.gs.phandler.PacketHandler;
import org.lupus_regnum.gs.plugins.skills.Thieving;


public class NpcCommand implements PacketHandler {
    /**
     * World instance
     */
    public static final World world = World.getWorld();

    public void handlePacket(Packet p, IoSession session) throws Exception {
		int serverIndex = p.readShort();
		final Player player = (Player) session.getAttachment();
		if (player.isBusy()) {
		    return;
		}
	
		final Mob affectedMob = world.getNpc(serverIndex);
		final Npc affectedNpc = (Npc) affectedMob;
		if (affectedNpc == null || affectedMob == null || player == null)
		    return;
	
	    final int npcID = affectedNpc.getID();
			Thieving thiev = new Thieving(player, affectedNpc, affectedMob);
			thiev.beginPickpocket();
			return;
    }

}
