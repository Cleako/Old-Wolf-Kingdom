package org.lupus_regnum.gs.plugins.npcs;

import org.lupus_regnum.gs.event.ShortEvent;
import org.lupus_regnum.gs.model.ChatMessage;
import org.lupus_regnum.gs.model.Npc;
import org.lupus_regnum.gs.model.Player;
import org.lupus_regnum.gs.model.World;
import org.lupus_regnum.gs.plugins.listeners.action.TalkToNpcListener;

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
