package org.wolf_kingdom.gs.plugins.listeners.action;

import org.wolf_kingdom.gs.model.InvItem;
import org.wolf_kingdom.gs.model.Player;

public interface WieldListener {
	/**
	 * Called when a user wields an item
	 * @param player Player that has wielded the item
	 * @param item Item that the player has wielded
	 */
	public void onWield(Player player, InvItem item);
}
