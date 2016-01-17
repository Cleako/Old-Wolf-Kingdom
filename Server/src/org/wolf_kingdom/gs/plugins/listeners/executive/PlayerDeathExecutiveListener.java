package org.wolf_kingdom.gs.plugins.listeners.executive;

import org.wolf_kingdom.gs.model.Player;

public interface PlayerDeathExecutiveListener {
	/**
	 * Return true to prevent the default action on death (stake item drop, wild item drop etc)
	 * @param p Player we do not want having the default death action occuring to
	 * @return
	 */
	public boolean blockPlayerDeath(Player p);
}
