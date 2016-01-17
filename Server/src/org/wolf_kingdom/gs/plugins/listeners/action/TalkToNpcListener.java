package org.wolf_kingdom.gs.plugins.listeners.action;

import org.wolf_kingdom.gs.model.Npc;
import org.wolf_kingdom.gs.model.Player;

public interface TalkToNpcListener {
	/**
	 * Called when a player talks to a npc
	 * @param p Player that talked to the NPC
	 * @param n NPC that the player talked to
	 */
	public void onTalkToNpc(Player p, Npc n);

}
