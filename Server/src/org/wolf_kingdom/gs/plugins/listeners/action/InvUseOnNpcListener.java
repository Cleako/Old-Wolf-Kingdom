package org.wolf_kingdom.gs.plugins.listeners.action;

import org.wolf_kingdom.gs.model.InvItem;
import org.wolf_kingdom.gs.model.Npc;
import org.wolf_kingdom.gs.model.Player;

public interface InvUseOnNpcListener {
	 /**
     * Called when a user uses an inventory item on a npc
     */
    public void onInvUseOnNPC(Npc npc, InvItem item, Player player);
}
