package org.wolf_kingdom.gs.plugins.listeners.action;

import org.wolf_kingdom.gs.model.InvItem;
import org.wolf_kingdom.gs.model.Player;

/**
 * Interface for handling Inv Actions
 */
public interface InvActionListener {
	/**
	 * Called when a user performs an inventory action
	 * @param item Item in the inventory that a player uses as an action
	 * @param player Player that uses the inventory item for an action
	 */
	public void onInvAction(InvItem item, Player player);
}
