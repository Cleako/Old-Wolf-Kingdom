package org.lupus_regnum.gs.plugins.listeners.action;

import org.lupus_regnum.gs.model.InvItem;
import org.lupus_regnum.gs.model.Player;

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
