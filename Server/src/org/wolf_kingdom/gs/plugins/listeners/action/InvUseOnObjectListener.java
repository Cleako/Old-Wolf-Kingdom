package org.wolf_kingdom.gs.plugins.listeners.action;

import org.wolf_kingdom.gs.model.GameObject;
import org.wolf_kingdom.gs.model.InvItem;
import org.wolf_kingdom.gs.model.Player;

public interface InvUseOnObjectListener {
    /**
     * Called when a user uses an inventory item on a game object
	 * @param obj Object that an inventor item is used on
	 * @param item Inventory item that is used on an object
	 * @param player Player that uses an inventory item on an object
     */
    public void onInvUseOnObject(GameObject obj, InvItem item, Player player);
}
