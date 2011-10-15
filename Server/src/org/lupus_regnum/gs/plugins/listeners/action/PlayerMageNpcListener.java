package org.lupus_regnum.gs.plugins.listeners.action;

import org.lupus_regnum.gs.model.Npc;
import org.lupus_regnum.gs.model.Player;

public interface PlayerMageNpcListener {
	/**
	 * Called when a player casts a spell on a npc
	 * @param p Player that casts the spell on a NPC
	 * @param n NPC that the player has cast a spell on
	 */	
	public void onPlayerMageNpc(Player p, Npc n);
}
