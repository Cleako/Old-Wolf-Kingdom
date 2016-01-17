package org.wolf_kingdom.gs.plugins.listeners.executive;

import org.wolf_kingdom.gs.model.Player;

public interface PlayerAttackExecutiveListener {
	/**
	 * Return true if you wish to prevent a user from attacking a mob
	 * @param p Player we do not want attacking a mob
	 * @param affectedmob Mob we do not want the player attacking
	 */
	public boolean blockPlayerAttack(Player p, Player affectedmob);
}
