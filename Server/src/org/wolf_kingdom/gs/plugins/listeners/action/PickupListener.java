package org.wolf_kingdom.gs.plugins.listeners.action;

import org.wolf_kingdom.gs.model.InvItem;
import org.wolf_kingdom.gs.model.Player;

public interface PickupListener {
	/**
	 * Called when a user picks up an item
	 * @param p Player that picks up the item
	 * @param i Item that the player picks up
	 */
	public void onPickup(Player p, InvItem i);
}
