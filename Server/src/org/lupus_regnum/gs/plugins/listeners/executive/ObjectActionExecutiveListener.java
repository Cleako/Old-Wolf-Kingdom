package org.lupus_regnum.gs.plugins.listeners.executive;

import org.lupus_regnum.gs.model.GameObject;
import org.lupus_regnum.gs.model.Player;

public interface ObjectActionExecutiveListener {
    /**
     * Prevent a user from activating an in-game object.
	 * @param obj Object we want to prevent player from activating
	 * @param command Command we want player not to activate through object
	 * @param player Player we do not want activating an object
     */
    public boolean blockObjectAction(GameObject obj, String command, Player player);
}
