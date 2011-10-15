package org.lupus_regnum.gs.plugins.listeners.executive;

import org.lupus_regnum.gs.model.Npc;
import org.lupus_regnum.gs.model.Player;

public interface TalkToNpcExecutiveListener {
	/**
	 * Return true to block a player from talking to a npc
	 * @param p Player we do not want able to talk to a NPC
	 * @param n NPC we do not want our player talking to
	 */
	public boolean blockTalkToNpc(Player p, Npc n);
}
