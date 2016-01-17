package org.wolf_kingdom.gs.plugins.listeners.executive;

import org.wolf_kingdom.gs.model.GameObject;
import org.wolf_kingdom.gs.model.Player;

public interface ObjectActionExecutiveListener {
    /**
     * Prevent a user from activating an in-game object.
	 * @param obj Object we want to prevent player from activating
	 * @param command Command we want player not to activate through object
	 * @param player Player we do not want activating an object
     */
    public boolean blockObjectAction(GameObject obj, String command, Player player);
}
