package org.lupus_regnum.gs.plugins.npcs;

import org.lupus_regnum.gs.event.ShortEvent;
import org.lupus_regnum.gs.model.ChatMessage;
import org.lupus_regnum.gs.model.MenuHandler;
import org.lupus_regnum.gs.model.Npc;
import org.lupus_regnum.gs.model.Player;
import org.lupus_regnum.gs.model.World;
import org.lupus_regnum.gs.plugins.listeners.action.TalkToNpcListener;
import org.lupus_regnum.gs.tools.DataConversions;

public class EntranaMonks implements TalkToNpcListener {

	public World world = World.getWorld();
	
	int[] monks = new int[]{ 212 };
	
	@Override
	public void onTalkToNpc(Player player, final Npc npc) {
		if(!DataConversions.inArray(monks, npc.getID())) {
			return;
		}
		final boolean toEntrana = !player.getLocation().inBounds(390,530, 440, 580); 
		player.informOfNpcMessage(new ChatMessage(npc, toEntrana ? "Are you looking to take passage to our holy island?" : "Are you ready to go back to the mainland?", player));
		player.setBusy(true);
		world.getDelayedEventHandler().add(new ShortEvent(player) {
			public void action() { 
				owner.setBusy(false); 
				String[] options = {"Yes okay I'm ready to go", "No thanks"};
				owner.setMenuHandler(new MenuHandler(options) { 
					public void handleReply(final int option, final String reply) {
						if(owner.isBusy()) { 
							npc.unblock(); 
							return; 
						}
						owner.informOfChatMessage(new ChatMessage(owner, reply, npc));
						owner.setBusy(true); 
						world.getDelayedEventHandler().add(new ShortEvent(owner) { 
							public void action() { 
								if(option == 0) {
									owner.getActionSender().sendMessage("You board the ship");
									world.getDelayedEventHandler().add(new ShortEvent(owner) {
										public void action() { 
											if(toEntrana) { 
												owner.teleport(418,570, false); 
											} 
											else { 
												owner.teleport(263, 659, false); 
											}
											owner.getActionSender().sendMessage("The ship arrives at " + (toEntrana ? "Entrana" : "Port Sarim")); 
											owner.setBusy(false);
											npc.unblock(); 
										}
									}); 
								}
								else { 
									owner.setBusy(false);
									npc.unblock(); 
									} 
								}
							}); 
						}
					});
			    owner.getActionSender().sendMenu(options); } 
			});
		npc.blockedBy(player);
		return;
	}
}
