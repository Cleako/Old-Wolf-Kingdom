package org.lupus_regnum.gs.plugins.listeners.action;

import org.lupus_regnum.gs.model.Player;

public interface PlayerRangeListener {
	/**
	 * Called when a player ranges a mob
	 * @param p Player that ranged a mob
	 * @param affectedMob Mob that the player ranged
	*/
	public void onPlayerRange(Player p, Player affectedMob);
	
}
