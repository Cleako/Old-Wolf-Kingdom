package org.wolf_kingdom.gs.plugins.listeners.executive;

import org.wolf_kingdom.gs.model.Player;

public interface PlayerLogoutExecutiveListener {
	/**
	 * Return true to prevent a player from logging out. This only disables a players ability to logout by clicking the logout button. You CANNOT force a player to stay logged in if he lags out or closes the client.
	 * @param player Player we do not want to be able to normally log out.
	 */
	public boolean blockPlayerLogout(Player player);
}
