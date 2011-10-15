package org.lupus_regnum.gs.plugins.listeners.executive;

import org.lupus_regnum.gs.model.Npc;
import org.lupus_regnum.gs.model.Player;

public interface PlayerMageNpcExecutiveListener {
	/**
	 * Return true if you wish to prevent a user from maging a NPC
	 * @param p Player we do not want being able to cast a spell on a NPC
	 * @param n NPC we do not want our player to be able to cast a spell on
	 */
	public boolean blockPlayerMageNpc(Player p, Npc n);
}
