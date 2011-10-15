package org.lupus_regnum.gs.plugins.listeners.action;

import org.lupus_regnum.gs.model.Player;

/**
 * Interface for handling player log outs
 */
public interface PlayerLogoutListener {
	/**
	 * Called when player logs out (by himself, or when he's logged out by a timeout, mod etc)
	 * @param player Player that has logged out
	 */
	public void onPlayerLogout(Player player);
}
