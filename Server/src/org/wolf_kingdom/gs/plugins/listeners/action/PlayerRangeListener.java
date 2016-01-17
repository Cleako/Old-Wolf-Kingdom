package org.wolf_kingdom.gs.plugins.listeners.action;

import org.wolf_kingdom.gs.model.Player;

public interface PlayerRangeListener {
	/**
	 * Called when a player ranges a mob
	 * @param p Player that ranged a mob
	 * @param affectedMob Mob that the player ranged
	*/
	public void onPlayerRange(Player p, Player affectedMob);
	
}
