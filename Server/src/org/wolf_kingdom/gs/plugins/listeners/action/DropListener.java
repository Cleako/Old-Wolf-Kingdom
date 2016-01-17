package org.wolf_kingdom.gs.plugins.listeners.action;

import org.wolf_kingdom.gs.model.InvItem;
import org.wolf_kingdom.gs.model.Player;

public interface DropListener {
	/**
	 * Called when a user drops an item
	 * @param p Player that drops an item
	 * @param i Item that a player drops
	 */
	public void onDrop(Player p, InvItem i);
}
