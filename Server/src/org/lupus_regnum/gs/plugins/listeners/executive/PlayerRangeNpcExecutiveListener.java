package org.lupus_regnum.gs.plugins.listeners.executive;

import org.lupus_regnum.gs.model.Npc;
import org.lupus_regnum.gs.model.Player;

public interface PlayerRangeNpcExecutiveListener {
	/**
	 * Return true if you wish to prevent a user from ranging a NPC
	 * @param p Player we do not want able to range a NPC
	 * @param n NPC we do not want our player able to range
	 */
	public boolean blockPlayerRangeNpc(Player p, Npc n);
}
