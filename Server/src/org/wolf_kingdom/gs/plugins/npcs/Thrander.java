package org.wolf_kingdom.gs.plugins.npcs;

import org.wolf_kingdom.gs.event.ShortEvent;
import org.wolf_kingdom.gs.model.ChatMessage;
import org.wolf_kingdom.gs.model.Npc;
import org.wolf_kingdom.gs.model.Player;
import org.wolf_kingdom.gs.model.World;
import org.wolf_kingdom.gs.plugins.listeners.action.TalkToNpcListener;

public class Thrander implements TalkToNpcListener {

	private World world = World.getWorld();
	
	@Override
	public void onTalkToNpc(Player player, final Npc npc) {
		if(npc.getID() != 160) {
			return;
		}
		player.informOfNpcMessage(new ChatMessage(npc, "Hello i'm thrander the smith, I'm an expert in armour modification", player));
		player.setBusy(true);
		world.getDelayedEventHandler().add(new ShortEvent(player) {
		    public void action() {
				owner.informOfNpcMessage(new ChatMessage(npc, "Give me your armour designed for men and I can convert it", owner));
				world.getDelayedEventHandler().add(new ShortEvent(owner) {
				    public void action() {
						owner.setBusy(false);
						owner.informOfNpcMessage(new ChatMessage(npc, "Into something more comfortable for a woman, and vice versa", owner));
						npc.unblock();
				    }
				});
		    }
		});
		npc.blockedBy(player);
	}

}
