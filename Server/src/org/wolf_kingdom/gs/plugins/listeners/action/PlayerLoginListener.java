package org.wolf_kingdom.gs.plugins.listeners.action;

import org.wolf_kingdom.gs.model.Player;

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
