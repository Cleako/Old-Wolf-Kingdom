package org.lupus_regnum.gs.plugins.listeners.action;

import org.lupus_regnum.gs.model.Player;

/**
 * Interface for handling player logins
 *
 */
public interface PlayerLoginListener {
	/**
	 * Called when player logins
	 * @param player Player that has logged in
	 */
	public void onPlayerLogin(Player player);
}
