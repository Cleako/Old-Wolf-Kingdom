package org.lupus_regnum.gs.plugins.npcs;

import org.lupus_regnum.gs.event.ShortEvent;
import org.lupus_regnum.gs.model.ChatMessage;
import org.lupus_regnum.gs.model.Npc;
import org.lupus_regnum.gs.model.Player;
import org.lupus_regnum.gs.model.World;
import org.lupus_regnum.gs.plugins.listeners.action.TalkToNpcListener;
import org.lupus_regnum.gs.tools.DataConversions;

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
