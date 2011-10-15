package org.lupus_regnum.gs.plugins.listeners.action;

import org.lupus_regnum.gs.model.Player;

public interface PlayerDeathListener {
	/**
	 * Called on a players death
	 * @param p Player that has died
	 */
	public void onPlayerDeath(Player p);

}
