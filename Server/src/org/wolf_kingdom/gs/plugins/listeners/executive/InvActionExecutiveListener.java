package org.wolf_kingdom.gs.plugins.listeners.executive;

import org.wolf_kingdom.gs.model.InvItem;
import org.wolf_kingdom.gs.model.Player;

public interface InvActionExecutiveListener {
	/**
	 * Return true to prevent inventory action
	 * @param item Item we want the player not to be able to use
	 * @param player Player we want not to be able to use item
	 */
	public boolean blockInvAction(InvItem item, Player player);
}
