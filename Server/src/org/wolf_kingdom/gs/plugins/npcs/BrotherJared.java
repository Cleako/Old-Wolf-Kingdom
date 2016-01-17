package org.wolf_kingdom.gs.plugins.npcs;

import org.wolf_kingdom.gs.event.ShortEvent;
import org.wolf_kingdom.gs.model.ChatMessage;
import org.wolf_kingdom.gs.model.InvItem;
import org.wolf_kingdom.gs.model.MenuHandler;
import org.wolf_kingdom.gs.model.Npc;
import org.wolf_kingdom.gs.model.Player;
import org.wolf_kingdom.gs.model.World;
import org.wolf_kingdom.gs.plugins.listeners.action.TalkToNpcListener;

public class BrotherJared implements TalkToNpcListener {
	/**
	 * World instance
	 */
	public final World world = World.getWorld();

	static final int[] unchargedAmmys = {
		45
	};
	static final int[] chargedAmmys = {
		385
	};
	
	@Override
	public void onTalkToNpc(final Player player, final Npc npc) {
		if(npc.getID() != 176) {
			return;
		}
		player.informOfNpcMessage(new ChatMessage(npc, "Greetings traveller", player));
  		player.setBusy(true);
  		world.getDelayedEventHandler().add(new ShortEvent(player)
  			{
  			public void action() 
  				{
  				owner.setBusy(false);
  				String[] options = new String[]{"Can you bless my ammulets?"};
  				owner.setMenuHandler(new MenuHandler(options) {
				public void handleReply(final int option, final String reply) 
					{
					if(owner.isBusy()) 
						{
						return;
						}
					owner.informOfChatMessage(new ChatMessage(owner, reply, npc));
					owner.setBusy(true);
					npc.setBusy(true);
					world.getDelayedEventHandler().add(new ShortEvent(owner)
						{
						public void action() 
							{
							if(option == 0) 
								{
  								owner.informOfNpcMessage(new ChatMessage(npc, "Sure", owner));
  								world.getDelayedEventHandler().add(new ShortEvent(owner) 
  									{
  									public void action() 
  										{
  										
  										boolean bool = true;
  										for(int i=0; i < unchargedAmmys.length; i++)
											{
											int itemCount = owner.getInventory().countId(unchargedAmmys[i]);
											for(int r=0; r < itemCount; r++) 
												{
												if(owner.getInventory().remove(unchargedAmmys[i], 1) > -1) 
													{
													bool = false;
													owner.getInventory().add(new InvItem(chargedAmmys[i], 1));
													}
												}
											}
  										if(bool) {
  											owner.informOfNpcMessage(new ChatMessage(npc, "It appears you have left your ammulets at home, come back with them!", owner));
  											}
  										else 
  											{
  											owner.getActionSender().sendMessage("The priest does his hokuspokus!");
  											world.getDelayedEventHandler().add(new ShortEvent(owner)
      											{
      											public void action() 
      												{
      												owner.informOfNpcMessage(new ChatMessage(npc, "Here you go sir!", owner)); 
      												owner.getActionSender().sendInventory();
      												}
      											});
  											}
  										owner.setBusy(false);
  										npc.unblock();
  										}
  									});
								}
							else 
								{
								owner.setBusy(false);
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
