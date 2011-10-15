package org.lupus_regnum.gs.plugins.listeners.action;

import org.lupus_regnum.gs.model.InvItem;
import org.lupus_regnum.gs.model.Player;

public interface WieldListener {
	/**
	 * Called when a user wields an item
	 * @param player Player that has wielded the item
	 * @param item Item that the player has wielded
	 */
	public void onWield(Player player, InvItem item);
}
