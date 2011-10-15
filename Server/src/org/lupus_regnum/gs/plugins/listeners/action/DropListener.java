package org.lupus_regnum.gs.plugins.listeners.action;

import org.lupus_regnum.gs.model.InvItem;
import org.lupus_regnum.gs.model.Player;

public interface DropListener {
	/**
	 * Called when a user drops an item
	 * @param p Player that drops an item
	 * @param i Item that a player drops
	 */
	public void onDrop(Player p, InvItem i);
}
