package org.lupus_regnum.gs.plugins.listeners.executive;

import org.lupus_regnum.gs.model.GameObject;
import org.lupus_regnum.gs.model.InvItem;
import org.lupus_regnum.gs.model.Player;

public interface InvUseOnObjectExecutiveListener {
    /**
     * Return true to prevent a user when he uses an inventory item on an game object
	 * @param obj Object we want to prevent the player using an item on
	 * @param item Item we want the player to be prevented from using on an object
	 * @param player Player we want to prevent using an item on an object
     */
    public boolean blockInvUseOnObject(GameObject obj, InvItem item, Player player);
}
