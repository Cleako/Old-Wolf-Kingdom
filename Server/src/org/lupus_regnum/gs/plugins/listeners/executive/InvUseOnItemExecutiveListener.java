package org.lupus_regnum.gs.plugins.listeners.executive;

import org.lupus_regnum.gs.model.InvItem;
import org.lupus_regnum.gs.model.Player;

public interface InvUseOnItemExecutiveListener {
	/**
     * Return true to prevent a user when he uses an inventory item on an item
	 * @param player
	 * @param item1
	 * @param item2
     */
	public boolean onInvUseOnItem(Player player, InvItem item1, InvItem item2);
	
}
