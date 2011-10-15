package org.lupus_regnum.gs.plugins.listeners.action;

import org.lupus_regnum.gs.model.Npc;
import org.lupus_regnum.gs.model.Player;

public interface PlayerRangeNpcListener {
	/**
	 * Called when a player ranges a npc
	 * @param p Player that has ranged a NPC
	 * @param n NPC that the player ranged
	 */	
	public void onPlayerRangeNpc(Player p, Npc n);
}
