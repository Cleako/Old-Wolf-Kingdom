package org.lupus_regnum.gs.plugins.listeners.action;

import org.lupus_regnum.gs.model.Player;

public interface PlayerAttackListener {
	/**
	 * Called when a player attacks
	 * @param p Player that is doing the attacking
	 * @param affectedmob Mob that the player is attacking
	 */	
	public void onPlayerAttack(Player p, Player affectedmob);
}
