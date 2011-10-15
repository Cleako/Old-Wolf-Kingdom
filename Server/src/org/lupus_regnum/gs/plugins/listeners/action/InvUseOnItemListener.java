package org.lupus_regnum.gs.plugins.listeners.action;

import org.lupus_regnum.gs.model.InvItem;
import org.lupus_regnum.gs.model.Player;

public interface InvUseOnItemListener {
	/**
     * Called when a user uses an inventory item on an item
	 * @param player Player that uses an inventory item on an item
	 * @param item1 Item that resides in the inventory that is used first
	 * @param item2 Other item that resides in the inventory that is used second
     */
	public void onInvUseOnItem(Player player, InvItem item1, InvItem item2);
}
