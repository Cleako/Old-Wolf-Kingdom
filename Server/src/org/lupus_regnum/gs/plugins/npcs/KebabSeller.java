package org.lupus_regnum.gs.plugins.npcs;

import org.lupus_regnum.gs.event.ShortEvent;
import org.lupus_regnum.gs.model.ChatMessage;
import org.lupus_regnum.gs.model.InvItem;
import org.lupus_regnum.gs.model.MenuHandler;
import org.lupus_regnum.gs.model.Npc;
import org.lupus_regnum.gs.model.Player;
import org.lupus_regnum.gs.model.World;
import org.lupus_regnum.gs.plugins.listeners.action.TalkToNpcListener;

public class KebabSeller implements TalkToNpcListener {
    /**
     * World instance
     */
    public World world = World.getWorld();

	@Override
	public void onTalkToNpc(Player player, final Npc npc) {
		if(npc.getID() != 90) {
			return;
		}
		player.informOfNpcMessage(new ChatMessage(npc, "Would you like to buy a nice kebab? Only 1 gold", player));
		player.setBusy(true);
		world.getDelayedEventHandler().add(new ShortEvent(player) {
		    public void action() {
			owner.setBusy(false);
			String[] options = new String[] { "I think I'll give it a miss", "Yes please" };
			owner.setMenuHandler(new MenuHandler(options) {
			    public void handleReply(final int option, final String reply) {
				if (owner.isBusy()) {
				    return;
				}
				owner.informOfChatMessage(new ChatMessage(owner, reply, npc));
				owner.setBusy(true);
				world.getDelayedEventHandler().add(new ShortEvent(owner) {
				    public void action() {
					owner.setBusy(false);
					if (option == 1) {
					    if (owner.getInventory().remove(10, 1) > -1) {
						owner.getActionSender().sendMessage("You buy a kebab");
						owner.getInventory().add(new InvItem(210, 1));
						owner.getActionSender().sendInventory();
						npc.unblock();
					    } 
					    else {
						owner.informOfChatMessage(new ChatMessage(owner, "Oops I forgot to bring any money with me", npc));
						owner.setBusy(true);
						world.getDelayedEventHandler().add(new ShortEvent(owner) {
						    public void action() {
							owner.setBusy(false);
							owner.informOfNpcMessage(new ChatMessage(npc, "Come back when you have some", owner));
							npc.unblock();
						    }
						});
					    }
					} else {
					    npc.unblock();
					}
				    }
				});
			    }
			});
			owner.getActionSender().sendMenu(options);
		    }
		});
		npc.blockedBy(player);
	}

}
