package org.wolf_kingdom.gs.plugins.listeners.action;

import org.wolf_kingdom.gs.model.Player;

public interface PlayerDeathListener {
	/**
	 * Called on a players death
	 * @param p Player that has died
	 */
	public void onPlayerDeath(Player p);

}
