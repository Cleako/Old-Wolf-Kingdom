package org.lupus_regnum.gs.plugins.listeners.executive;

import org.lupus_regnum.gs.model.InvItem;
import org.lupus_regnum.gs.model.Player;

public interface DropExecutiveListener {
	/**
	 * Return true if you wish to prevent a user from dropping an item
	 * @param p Player we want to prevent from dropping an item
	 * @param i Item in inventory we wish to prevent from being dropped
	 */
	public boolean blockDrop(Player p, InvItem i);
}
