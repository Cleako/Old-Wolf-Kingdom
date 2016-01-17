package org.wolf_kingdom.gs.plugins.quests;

import org.wolf_kingdom.gs.model.ChatMessage;
import org.wolf_kingdom.gs.model.MenuHandler;
import org.wolf_kingdom.gs.model.Npc;
import org.wolf_kingdom.gs.model.Player;
import org.wolf_kingdom.gs.plugins.Quest;
import org.wolf_kingdom.gs.plugins.listeners.action.TalkToNpcListener;
import org.wolf_kingdom.gs.plugins.listeners.executive.TalkToNpcExecutiveListener;

public class CooksAssistant extends Quest implements TalkToNpcListener, TalkToNpcExecutiveListener {

	@Override
	public int getQuestId() {
		return 13;
	}

	@Override
	public String getQuestName() {
		return "Cook's Assistant";
	}
	
	@Override
	public void onTalkToNpc(Player p, final Npc n) {
		if(n.getID() == 7) {			
				if(p.getQuestStage(this) == 0) {
					n.setBusy(true);
					p.setBusy(true);
					System.out.println("Quest stage: " + 0);
					p.informOfNpcMessage(new ChatMessage(n, "Hello friend, how is the adventuring going?", p));
					sleep(2000);
					p.setBusy(false);
					n.setBusy(false);
					String[] options = new String[] { "I am getting strong and mighty", "I keep on dying", "Nice hat", "Can I use your range?" };
			    	p.setMenuHandler(new MenuHandler(options) {
				    	public void handleReply(final int option, final String reply) {
				    		switch (option) {
					        	case 0: // Yes
									sleep(1500);
									owner.informOfNpcMessage(new ChatMessage(n, "Glad to hear it", owner));
									owner.setBusy(false);
									n.setBusy(false);
									break;
					        	case 1: // No
									sleep(1500);
					        		owner.informOfNpcMessage(new ChatMessage(n, "Ah well at least you keep coming back to life!", owner));
									owner.setBusy(false);
									n.setBusy(false);
									break;
								case 2: 
									sleep(1500);
					        		owner.informOfNpcMessage(new ChatMessage(n, "Err thank you - it's a pretty ordinary cooks hat really", owner));
									owner.setBusy(false);
									n.setBusy(false);
									break;
								case 3:
									owner.setBusy(true);
									n.setBusy(true);
									sleep(1500);
					        		owner.informOfNpcMessage(new ChatMessage(n, "Sure, but first I need you to do a favour for me. It's the Duke's birthday", owner));
									sleep(2000);
									owner.informOfNpcMessage(new ChatMessage(n, "but I seem to have lost some ingredients to make him a cake!", owner));
									sleep(2000);
									owner.informOfNpcMessage(new ChatMessage(n, "Bring me an egg, some milk and a pot of flour so I can finish the cake!", owner));
									sleep(1500);
									owner.setQuestStage(getQuestId(), 1);
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
					p.informOfNpcMessage(new ChatMessage(n, "Have you got my ingredients?", p));
					sleep(2000);
					n.setBusy(false);
					p.setBusy(false);
					String[] options3 = new String[] { "Yes, I have them here", "No, sorry. I am still looking for them" };
			    	p.setMenuHandler(new MenuHandler(options3) {
				    	public void handleReply(final int option, final String reply) {
				    		switch (option) {
								case 1:
									owner.setBusy(true);
									n.setBusy(true);
									sleep(1500);
									owner.informOfNpcMessage(new ChatMessage(n, "Ok. Come back when you have found the cake ingredients!", owner));
									sleep(2000);
									owner.informOfNpcMessage(new ChatMessage(n, "Remember I need egg, some milk and a pot of flour", owner));
									sleep(1500);
									n.setBusy(false);
									owner.setBusy(false);
									break;
								case 0:
									owner.setBusy(true);
									n.setBusy(true);
									sleep(1500);
									if(owner.getInventory().hasItemId(19) && owner.getInventory().hasItemId(136) && owner.getInventory().hasItemId(22)) {
										owner.informOfNpcMessage(new ChatMessage(n, "Thank you! You saved the Duke's birthday!", owner));
										sleep(1500);
										owner.incExp(7, 250, false, false, false);
										owner.getActionSender().sendStat(7);
										owner.getInventory().remove(19, 1);
										owner.getInventory().remove(136, 1);
										owner.getInventory().remove(22, 1);
										owner.getActionSender().sendInventory();
										owner.setQuestStage(getQuestId(), -1);
										owner.setBusy(false);
										n.setBusy(false);
									} else {
										sleep(1500);
										owner.informOfNpcMessage(new ChatMessage(n, "It appears you don't have my ingredients. This is important!", owner));
										owner.setBusy(false);
										n.setBusy(false);
									}
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
					p.getActionSender().sendMenu(options3);
				} 
				else if(p.getQuestStage(this) == -1) {
					p.setBusy(true);
					n.blockedBy(p);
					p.informOfNpcMessage(new ChatMessage(n, "Thank you. The Duke really enjoyed his birthday cake!", p));
					p.setBusy(false);
					n.unblock();
			
				}
		}
	}

	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		if(n.getID() == 7) {
			return true;
		}
		return false;
	}
	
}
