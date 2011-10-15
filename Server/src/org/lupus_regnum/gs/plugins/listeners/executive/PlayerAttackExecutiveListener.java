package org.lupus_regnum.gs.plugins.listeners.executive;

import org.lupus_regnum.gs.model.Player;

public interface PlayerAttackExecutiveListener {
	/**
	 * Return true if you wish to prevent a user from attacking a mob
	 * @param p Player we do not want attacking a mob
	 * @param affectedmob Mob we do not want the player attacking
	 */
	public boolean blockPlayerAttack(Player p, Player affectedmob);
}
