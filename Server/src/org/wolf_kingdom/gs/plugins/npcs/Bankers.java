package org.wolf_kingdom.gs.plugins.npcs;

import org.wolf_kingdom.gs.event.ShortEvent;
import org.wolf_kingdom.gs.model.ChatMessage;
import org.wolf_kingdom.gs.model.Npc;
import org.wolf_kingdom.gs.model.Player;
import org.wolf_kingdom.gs.model.World;
import org.wolf_kingdom.gs.plugins.listeners.action.TalkToNpcListener;
import org.wolf_kingdom.gs.tools.DataConversions;

public class Bankers implements TalkToNpcListener {

	int[] bankers = new int[]{95, 224, 268, 485, 540, 617, 792};
	
	@Override
	public void onTalkToNpc(Player player, final Npc npc) {
		if(!DataConversions.inArray(bankers, npc.getID())) {
			return;
		}
		player.setBusy(true);
		player.informOfChatMessage(new ChatMessage(player, "I'd like to access my bank account please", npc));
		World.getWorld().getDelayedEventHandler().add(new ShortEvent(player) {
		    public void action() {
				owner.informOfNpcMessage(new ChatMessage(npc, "Certainly " + (owner.isMale() ? "sir" : "miss"), owner));
				World.getWorld().getDelayedEventHandler().add(new ShortEvent(owner) {
				    public void action() {
						owner.setBusy(false);
						owner.setAccessingBank(true);
						owner.getActionSender().showBank();
				    }
				});
				npc.unblock();
		    }
		});
		npc.blockedBy(player);
	}
	
}
