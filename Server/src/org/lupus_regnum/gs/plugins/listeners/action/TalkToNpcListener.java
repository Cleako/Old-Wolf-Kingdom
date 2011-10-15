package org.lupus_regnum.gs.plugins.listeners.action;

import org.lupus_regnum.gs.model.Npc;
import org.lupus_regnum.gs.model.Player;

public interface TalkToNpcListener {
	/**
	 * Called when a player talks to a npc
	 * @param p Player that talked to the NPC
	 * @param n NPC that the player talked to
	 */
	public void onTalkToNpc(Player p, Npc n);

}
