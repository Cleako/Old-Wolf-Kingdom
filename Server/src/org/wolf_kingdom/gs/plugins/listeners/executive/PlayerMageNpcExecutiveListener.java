package org.wolf_kingdom.gs.plugins.listeners.executive;

import org.wolf_kingdom.gs.model.Npc;
import org.wolf_kingdom.gs.model.Player;

public interface PlayerMageNpcExecutiveListener {
	/**
	 * Return true if you wish to prevent a user from maging a NPC
	 * @param p Player we do not want being able to cast a spell on a NPC
	 * @param n NPC we do not want our player to be able to cast a spell on
	 */
	public boolean blockPlayerMageNpc(Player p, Npc n);
}
