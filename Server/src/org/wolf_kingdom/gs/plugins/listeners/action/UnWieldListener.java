package org.wolf_kingdom.gs.plugins.listeners.action;

import org.wolf_kingdom.gs.model.InvItem;
import org.wolf_kingdom.gs.model.Player;

public interface UnWieldListener {
	/**
	 * Called when a user unwields an item
	 * @param player Player that has unwielded the item
	 * @param item Item that the player has unwielded
	 */
	public void onUnWield(Player player, InvItem item);

}
