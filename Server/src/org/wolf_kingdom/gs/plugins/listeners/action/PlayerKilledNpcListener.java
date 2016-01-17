package org.wolf_kingdom.gs.plugins.listeners.action;

import org.wolf_kingdom.gs.model.Npc;
import org.wolf_kingdom.gs.model.Player;

public interface PlayerKilledNpcListener {
	/**
	 * Called when a player kills a npc
	 * @param p Player that killed the NPC
	 * @param n NPC that the player killed
	 */	
	public void onPlayerKilledNpc(Player p, Npc n);
}
