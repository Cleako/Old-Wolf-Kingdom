package org.lupus_regnum.gs.plugins.listeners.executive;

import org.lupus_regnum.gs.model.Player;

public interface TeleportExecutiveListener {
	/**
	 * Return true to prevent teleportation (this includes ::stuck)
	 * This does not include teleportations without bubbles (stairs, death, ladders etc)
	 * @param p Player we do not want able to teleport
	 */
	public boolean blockTeleport(Player p);
}
