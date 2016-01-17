package org.wolf_kingdom.gs.plugins.listeners.executive;

import org.wolf_kingdom.gs.model.InvItem;
import org.wolf_kingdom.gs.model.Player;

public interface DropExecutiveListener {
	/**
	 * Return true if you wish to prevent a user from dropping an item
	 * @param p Player we want to prevent from dropping an item
	 * @param i Item in inventory we wish to prevent from being dropped
	 */
	public boolean blockDrop(Player p, InvItem i);
}
