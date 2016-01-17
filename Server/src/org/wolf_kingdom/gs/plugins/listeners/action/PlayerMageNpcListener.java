package org.wolf_kingdom.gs.plugins.listeners.action;

import org.wolf_kingdom.gs.model.Npc;
import org.wolf_kingdom.gs.model.Player;

public interface PlayerMageNpcListener {
	/**
	 * Called when a player casts a spell on a npc
	 * @param p Player that casts the spell on a NPC
	 * @param n NPC that the player has cast a spell on
	 */	
	public void onPlayerMageNpc(Player p, Npc n);
}
