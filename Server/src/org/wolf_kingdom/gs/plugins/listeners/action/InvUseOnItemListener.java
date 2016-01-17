package org.wolf_kingdom.gs.plugins.listeners.action;

import org.wolf_kingdom.gs.model.InvItem;
import org.wolf_kingdom.gs.model.Player;

public interface InvUseOnItemListener {
	/**
     * Called when a user uses an inventory item on an item
	 * @param player Player that uses an inventory item on an item
	 * @param item1 Item that resides in the inventory that is used first
	 * @param item2 Other item that resides in the inventory that is used second
     */
	public void onInvUseOnItem(Player player, InvItem item1, InvItem item2);
}
