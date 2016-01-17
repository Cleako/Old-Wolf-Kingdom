package org.wolf_kingdom.gs.plugins.listeners.action;

import org.wolf_kingdom.gs.model.GameObject;
import org.wolf_kingdom.gs.model.Player;

public interface ObjectActionListener {
    /**
     * When a user activates an in-game Object.
	 * @param obj Object that is activated
	 * @param command Command that the activated object sends
	 * @param player Player that activates an object
     */
    public void onObjectAction(GameObject obj, String command, Player player);

}
