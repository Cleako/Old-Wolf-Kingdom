package org.lupus_regnum.gs.plugins.listeners.action;

import org.lupus_regnum.gs.model.Player;

public interface TeleportListener {
	/**
	 * Called when a user teleports (includes ::stuck)
	 * This does not include teleportations without bubbles (stairs, death, ladders etc)
	 * @param p Player that is doing the teleporting
	 */
	public void onTeleport(Player p);
}
