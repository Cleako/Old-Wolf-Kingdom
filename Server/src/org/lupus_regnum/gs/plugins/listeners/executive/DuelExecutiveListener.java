package org.lupus_regnum.gs.plugins.listeners.executive;

import org.lupus_regnum.gs.model.Player;

public interface DuelExecutiveListener {
	/**
	 * Return true if you wish to prevent a user from duelling
	 * @param p Player we want to prevent from dueling
	 * @param p2 Other player we want to prevent from dueling
	 */
	public boolean blockDuel(Player p, Player p2);
}
