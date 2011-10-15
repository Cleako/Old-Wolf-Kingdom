
package org.lupus_regnum.gs.plugins.quests;

import org.lupus_regnum.gs.model.ChatMessage;
import org.lupus_regnum.gs.model.InvItem;
import org.lupus_regnum.gs.model.MenuHandler;
import org.lupus_regnum.gs.model.Npc;
import org.lupus_regnum.gs.model.Player;
import org.lupus_regnum.gs.plugins.Quest;
import org.lupus_regnum.gs.plugins.listeners.action.TalkToNpcListener;
import org.lupus_regnum.gs.plugins.listeners.executive.TalkToNpcExecutiveListener;

public class SheepShearer extends Quest implements TalkToNpcListener, TalkToNpcExecutiveListener {

	@Override
	public int getQuestId() {
		return 8;
	}

	@Override
	public String getQuestName() {
		return "Sheep Shearer";
	}
	
	@Override
	public void onTalkToNpc(Player p, final Npc n) {
		if(n.getID() == 77) {			
				if(p.getQuestStage(this) == 0) {
					n.setBusy(true);
					p.setBusy(true);
					System.out.println("Quest stage: " + 0);
					p.informOfNpcMessage(new ChatMessage(n, "Hi there, traveller. Care to make some money?", p));
					sleep(2000);
					p.setBusy(false);
					n.setBusy(false);
					String[] options = new String[] { "Sure, what do I need to do?", "No thanks, I'm good." };
			    	p.setMenuHandler(new MenuHandler(options) {
				    	public void handleReply(final int option, final String reply) {
				    		switch (option) {
					        	case 1: // No
									n.setBusy(false);
									owner.setBusy(false);
					        		break;
					        	case 0: // Yes
									n.setBusy(true);
									owner.setBusy(true);
									sleep(1500);
					        		owner.informOfNpcMessage(new ChatMessage(n, "If you collect 20 balls of wool for me, I'll pay you 500 coins.", owner));
									sleep(2000);
									owner.informOfNpcMessage(new ChatMessage(n, "Maybe I'll teach you a thing or two about crafting, too.", owner));
									sleep(2000);
									owner.informOfNpcMessage(new ChatMessage(n, "I'm afraid you'll have to find your own shears, but the sheep are outside.", owner));
									sleep(2000);
									owner.setBusy(false);
									n.setBusy(true);
									String[] options2 = new String[] { "Sorry, I don't like the sound of that.", "I'd be happy to help." };
											owner.setMenuHandler(new MenuHandler(options2) {
												public void handleReply(final int option, final String reply) {
													switch (option) {
														case 1: //yes
															sleep(1500);
															owner.informOfNpcMessage(new ChatMessage(n, "Great! Come back and see me when you're done.", owner));
															owner.setQuestStage(getQuestId(), 1);
															owner.setBusy(false);
															n.setBusy(false);
															break;						
														case 0: // no
															sleep(1500);
															owner.informOfNpcMessage(new ChatMessage(n, "Suit yourself. Come and see me if you change your mind.", owner));
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
											owner.getActionSender().sendMenu(options2);
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
					p.informOfNpcMessage(new ChatMessage(n, "Ahh, you've returned! Do you have my wool?", p));
					sleep(2000);
					p.setBusy(false);
					n.setBusy(false);
					String[] options3 = new String[] { "I'm afraid not.", "Yes, I do." };
						p.setMenuHandler(new MenuHandler(options3) {
							public void handleReply(final int option, final String reply) {
								switch (option) {
									case 0: //no
										sleep(1500);
										owner.informOfNpcMessage(new ChatMessage(n, "Well, come and see me when you do. The offer still stands", owner));
										owner.setBusy(false);
										n.setBusy(false);
										break;
									case 1: // yes
										owner.setBusy(true);
										n.setBusy(true);
										sleep(1500);
										if(owner.getInventory().hasItemId(207) && owner.getInventory().countId(207) >= 20) {
											owner.setBusy(false);
											n.setBusy(false);
											owner.informOfNpcMessage(new ChatMessage(n, "Thank you very much! As promised, here's your reward.", owner));
											owner.incExp(12, 350, false, false, false);
											owner.getActionSender().sendStat(12);
											for (int i = 0; i < 20; i++)
												owner.getInventory().remove(207, 1);
											owner.getInventory().add(new InvItem(10, 330));
											owner.getActionSender().sendInventory();
											sleep(2000);
											owner.getActionSender().sendMessage("You have completed the quest of @gre@Sheep Shearer");
											owner.setQuestStage(getQuestId(), -1);
										} else {
											owner.informOfNpcMessage(new ChatMessage(n, "Um, no you don't. Get back to me when you do. The reward still stands!", owner));
											owner.setBusy(false);
											n.setBusy(false);
										}
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
					n.setBusy(true);
					p.informOfNpcMessage(new ChatMessage(n, "Hello " + p.getUsername() + "!", p));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "Thank you very much for your help!", p));
					sleep(2000);
					p.setBusy(false);
					n.setBusy(false);								
				}
		}
	}

	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		if(n.getID() == 77) {
			return true;
		}
		return false;
	}
	
}
