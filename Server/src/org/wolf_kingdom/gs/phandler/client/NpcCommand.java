package org.wolf_kingdom.gs.phandler.client;

import org.apache.mina.common.IoSession;
import org.wolf_kingdom.gs.connection.Packet;
import org.wolf_kingdom.gs.model.Mob;
import org.wolf_kingdom.gs.model.Npc;
import org.wolf_kingdom.gs.model.Player;
import org.wolf_kingdom.gs.model.World;
import org.wolf_kingdom.gs.phandler.PacketHandler;
import org.wolf_kingdom.gs.plugins.skills.Thieving;


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
