package org.wolf_kingdom.gs.plugins.listeners.executive;

import org.wolf_kingdom.gs.model.Player;

public interface PlayerRangeExecutiveListener {
	/**
	 * Return true if you wish to prevent a user from ranging a mob
	 * @param p Player we do not want being able to range a mob
	 * @param affectedMob Mob we do not want our player able to range
	 */
	public boolean blockPlayerRange(Player p, Player affectedMob);
}
