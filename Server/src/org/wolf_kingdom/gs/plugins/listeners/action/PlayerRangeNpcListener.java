package org.wolf_kingdom.gs.plugins.listeners.action;

import org.wolf_kingdom.gs.model.Npc;
import org.wolf_kingdom.gs.model.Player;

public interface PlayerRangeNpcListener {
	/**
	 * Called when a player ranges a npc
	 * @param p Player that has ranged a NPC
	 * @param n NPC that the player ranged
	 */	
	public void onPlayerRangeNpc(Player p, Npc n);
}
