package org.lupus_regnum.gs.plugins.listeners.action;

import org.lupus_regnum.gs.model.InvItem;
import org.lupus_regnum.gs.model.Player;

public interface UnWieldListener {
	/**
	 * Called when a user unwields an item
	 * @param player Player that has unwielded the item
	 * @param item Item that the player has unwielded
	 */
	public void onUnWield(Player player, InvItem item);

}
