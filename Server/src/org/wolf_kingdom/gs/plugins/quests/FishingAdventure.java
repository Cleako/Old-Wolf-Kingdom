package org.wolf_kingdom.gs.plugins.quests;

import org.wolf_kingdom.gs.model.ChatMessage;
import org.wolf_kingdom.gs.model.GameObject;
import org.wolf_kingdom.gs.model.InvItem;
import org.wolf_kingdom.gs.model.MenuHandler;
import org.wolf_kingdom.gs.model.Npc;
import org.wolf_kingdom.gs.model.Player;
import org.wolf_kingdom.gs.plugins.Quest;
import org.wolf_kingdom.gs.plugins.listeners.action.ObjectActionListener;
import org.wolf_kingdom.gs.plugins.listeners.action.TalkToNpcListener;
import org.wolf_kingdom.gs.plugins.listeners.executive.TalkToNpcExecutiveListener;
import org.wolf_kingdom.gs.tools.DataConversions;

public class FishingAdventure extends Quest implements 
        TalkToNpcListener, 
        TalkToNpcExecutiveListener, 
        ObjectActionListener {
	
	@Override
	public int getQuestId() {
		return 0;
	}

	@Override
	public String getQuestName() {
		return "Fishing adventure";
	}
	
	@Override
	public void onTalkToNpc(Player p, final Npc n) {
		if(n.getID() == 124) {
			try {
				if(p.getQuestStage(this) == 0) {
					n.setBusy(true);
					p.setBusy(true);
					System.out.println("Quest stage: " + 0);
					p.informOfNpcMessage(new ChatMessage(n, "Oh dear oh dear oh dear", p));
					Thread.sleep(2000);
					p.informOfChatMessage(new ChatMessage(p, "What's the matter old man?", n));
					Thread.sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "Old man? Boy, you're talking to a master fisher!", p));
					Thread.sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "I'd show you my trophy but I lost it yesterday", p));
					Thread.sleep(2000);
					p.informOfChatMessage(new ChatMessage(p, "This sounds like a quest...", n));
					Thread.sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "A quest you say? Well, that's not a bad idea. Find my trohpy and I'll award you!", p));
					Thread.sleep(2000);
					p.getActionSender().sendMessage("Would you like to start the " + this.getQuestName() + " quest?");
					String[] options = new String[] { "Yes", "No" };
			    	p.setMenuHandler(new MenuHandler(options) {
				    	public void handleReply(final int option, final String reply) {
				    		switch (option) {
					        	case 0: // Yes
									owner.informOfNpcMessage(new ChatMessage(n, "I lost it while fishing right here...", owner));
									owner.getActionSender().sendMessage("Maybe I should go fish for a trophy.");
									owner.setQuestStage(getQuestId(), 1);
									n.setBusy(false);
									owner.setBusy(false);
					        		break;
					        	case 1: // No
					        		owner.informOfChatMessage(new ChatMessage(owner, "I'll better be on my way...", n));
									owner.setBusy(false);
									n.setBusy(false);
					        		break;
					        	default:
					        		owner.setBusy(false);
									n.setBusy(false);
					        		return;
				    		}
				    	}
			    	});
			    	p.getActionSender().sendMenu(options);
				}
				else if(p.getQuestStage(this) == 1) {
					n.setBusy(true);
					p.setBusy(true);
					if(p.getInventory().hasItemId(720)) {
						p.informOfNpcMessage(new ChatMessage(n, "My trophy! Thank you so much!", p));
						Thread.sleep(2000);
						p.getActionSender().sendMessage("Ned takes the trophy from you");
						p.getInventory().remove(p.getInventory().getLastIndexById(720));
						p.getActionSender().sendInventory();
						Thread.sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "Take these coins as an award!", p));
						p.getInventory().add(new InvItem(10,5000));
						p.getActionSender().sendInventory();
						Thread.sleep(1000);
						p.setQuestStage(this, -1);
					}
					else {
						p.informOfNpcMessage(new ChatMessage(n, "I remember where I lost it!", p));
						Thread.sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "The trophy fell into the water when I was fishing!", p));
						Thread.sleep(2000);
					}
					n.setBusy(false);
					p.setBusy(false);
				}
				else {
					p.informOfNpcMessage(new ChatMessage(n, "Thank you so much kind sir!", p));
				}
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onObjectAction(GameObject obj, String command, Player player) {
		if (command.equals("net") && player.getQuestStage(this) == 1 && player.getInventory().hasItemId(376)) {
			if(DataConversions.random(0, 100) <= 90) {
				player.getInventory().add(new InvItem(720));
				player.getActionSender().sendInventory();
				player.getActionSender().sendMessage("You found a trophy");
			}
		}
		
	}

	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		if(n.getID() == 124) {
			return true;
		}
		return false;
	}
	
}
