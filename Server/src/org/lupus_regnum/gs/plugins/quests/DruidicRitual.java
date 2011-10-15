package org.lupus_regnum.gs.plugins.quests;

import org.lupus_regnum.gs.model.ChatMessage;
import org.lupus_regnum.gs.model.GameObject;
import org.lupus_regnum.gs.model.InvItem;
import org.lupus_regnum.gs.model.MenuHandler;
import org.lupus_regnum.gs.model.Npc;
import org.lupus_regnum.gs.model.Player;
import org.lupus_regnum.gs.plugins.Quest;
import org.lupus_regnum.gs.plugins.listeners.action.InvUseOnObjectListener;
import org.lupus_regnum.gs.plugins.listeners.action.TalkToNpcListener;
import org.lupus_regnum.gs.plugins.listeners.executive.InvUseOnObjectExecutiveListener;
import org.lupus_regnum.gs.plugins.listeners.executive.TalkToNpcExecutiveListener;

public class DruidicRitual extends Quest implements 
        TalkToNpcListener, 
        TalkToNpcExecutiveListener, 
        InvUseOnObjectListener, 
        InvUseOnObjectExecutiveListener {

	@Override
	public int getQuestId() {
		return 2;
	}

	@Override
	public String getQuestName() {
		return "Druidic Ritual";
	}
	
	@Override
	public void onTalkToNpc(Player p, final Npc n) {
		if(n.getID() == 204) {			
				if(p.getQuestStage(this) == 0) {
					n.setBusy(true);
					p.setBusy(true);
					System.out.println("Quest stage: " + 0);
					p.informOfNpcMessage(new ChatMessage(n, "Hello adventurer. How can I help you?", p));
					sleep(2000);
					p.setBusy(false);
					n.setBusy(false);
					String[] options = new String[] { "I am in search of a quest", "Nothing, sorry" };
			    	p.setMenuHandler(new MenuHandler(options) {
				    	public void handleReply(final int option, final String reply) {
				    		switch (option) {
					        	case 0: // Yes
									owner.setBusy(true);
									n.setBusy(true);
									sleep(1500);
									owner.informOfNpcMessage(new ChatMessage(n, "As it happens, Sanfew is in need of a little help!", owner));
									sleep(2000);
									owner.informOfNpcMessage(new ChatMessage(n, "Head south into the town of Taverly.", owner));
									sleep(2000);
									owner.informOfNpcMessage(new ChatMessage(n, "Sanfew will be wandering around there.", owner));
									sleep(2000);
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
					p.informOfNpcMessage(new ChatMessage(n, "Sanfew is located south of here in the town of Taverly", p));
					sleep(2000);
					n.setBusy(false);
					p.setBusy(false);
					
				} 
				else if(p.getQuestStage(this) == 3) {
					n.setBusy(true);
					p.setBusy(true);
					p.informOfNpcMessage(new ChatMessage(n, "Sanfew informed me of your help. May you be blessed with the art of Herblaw!", p));
					sleep(2000);
					p.incExp(15, 250, false, false, false);
					p.getActionSender().sendStat(15);
					p.setQuestStage(getQuestId(), -1);
					n.setBusy(false);
					p.setBusy(false);
				}	
				else if(p.getQuestStage(this) == 2) {
					n.setBusy(true);
					p.setBusy(true);
					p.informOfNpcMessage(new ChatMessage(n, "Sorry I can't help you at the moment, go talk with Sanfew", p));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "Head south into the town of Taverly.", p));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "Sanfew will be wandering around there.", p));
					sleep(2000);
					n.setBusy(false);
					p.setBusy(false);
				}					
				else if(p.getQuestStage(this) == -1) {
					p.setBusy(true);
					n.blockedBy(p);
					p.informOfNpcMessage(new ChatMessage(n, "How's the herblaw coming along?", p));
					sleep(2000);
					p.setBusy(false);
					n.setBusy(false);
					String[] options2 = new String[] { "Fine thank you", "Not too good" };
			    	p.setMenuHandler(new MenuHandler(options2) {
				    	public void handleReply(final int option, final String reply) {
				    		switch (option) {
					        	case 0: // Yes
									owner.setBusy(true);
									n.setBusy(true);
									sleep(1500);
									owner.informOfNpcMessage(new ChatMessage(n, "That's good to hear! My teachings paid off on you!", owner));
									owner.setBusy(false);
									n.setBusy(false);
									break;
								case 1:
									owner.setBusy(true);
									n.setBusy(true);
									sleep(1500);
									owner.informOfNpcMessage(new ChatMessage(n, "Practice makes perfect! Don't give it up!", owner));
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
			    	p.getActionSender().sendMenu(options2);	
				}
		} 
		else if(n.getID() == 205) {
				if(p.getQuestStage(this) == 1) {
					n.setBusy(true);
					p.setBusy(true);
					p.informOfNpcMessage(new ChatMessage(n, "Hello adventurer. How can I help you?", p));
					sleep(2000);
					p.setBusy(false);
					n.setBusy(false);
					String[] options3 = new String[] { "I am in search of a quest", "Nothing, sorry" };
			    	p.setMenuHandler(new MenuHandler(options3) {
				    	public void handleReply(final int option, final String reply) {
				    		switch (option) {
					        	case 0: // Yes
									owner.setBusy(true);
									n.setBusy(true);
									sleep(1500);
									owner.informOfNpcMessage(new ChatMessage(n, "Ah! Then I am the person to speak to! I need 4 types of raw meat", owner));
									sleep(2000);
									owner.informOfNpcMessage(new ChatMessage(n, "I need the meat of a Rat, Chicken, Bear and a Cow. It also must,", owner));
									sleep(2000);
									owner.informOfNpcMessage(new ChatMessage(n, "be dipped into the Cauldron of Thunder to enchant it! Good luck on your quest!", owner));
									sleep(2000);
									owner.setQuestStage(getQuestId(), 2);
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
				else if(p.getQuestStage(this) == 2) {
					n.setBusy(true);
					p.setBusy(true);
					p.informOfNpcMessage(new ChatMessage(n, "Have you got the 4 pieces of enchanted meat yet?", p));
					sleep(2000);
					p.setBusy(false);
					n.setBusy(false);
					String[] options4 = new String[] { "Yes, I have them right here", "No sorry. I am still searching for them" };
			    	p.setMenuHandler(new MenuHandler(options4) {
				    	public void handleReply(final int option, final String reply) {
				    		switch (option) {
					        	case 0: // Yes
									owner.setBusy(true);
									n.setBusy(true);
									sleep(1500);
									if(owner.getInventory().countId(505) > 0 && owner.getInventory().countId(506) > 0 && owner.getInventory().countId(507) > 0 && owner.getInventory().countId(508) > 0) {
										owner.informOfNpcMessage(new ChatMessage(n, "Ah, thank you! Kaqemeex will now teach you the art of Herblaw", owner));
										owner.getInventory().remove(505, 1);
										owner.getInventory().remove(506, 1);
										owner.getInventory().remove(507, 1);
										owner.getInventory().remove(508, 1);
										owner.getActionSender().sendInventory();
										owner.setQuestStage(getQuestId(), 3);
										owner.setBusy(false);
										n.setBusy(false);
									} else {
										owner.informOfNpcMessage(new ChatMessage(n, "You don't have the enchanted meats!", owner));
										owner.setBusy(false);
										n.setBusy(false);
									}
									break;		
								case 1: 
									sleep(1500);
									owner.informOfNpcMessage(new ChatMessage(n, "Ok, come back when you have them!", owner));
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
			    	p.getActionSender().sendMenu(options4);
				} 
				else if(p.getQuestStage(this) == 3) {
					p.setBusy(true);
					n.setBusy(true);
					p.informOfNpcMessage(new ChatMessage(n, "Go speak to Kaqemeex to gain the ability of Herblaw", p));
					sleep(2000);
					p.setBusy(false);
					n.setBusy(false);
				}
				else if(p.getQuestStage(this) == -1) {
					n.setBusy(true);
					p.setBusy(true);
					p.informOfNpcMessage(new ChatMessage(n, "Hello adventurer. How can I help you?", p));
					sleep(2000);
					p.setBusy(false);
					n.setBusy(false);
					String[] options5 = new String[] { "I am in search of a quest", "Nothing, sorry" };
			    	p.setMenuHandler(new MenuHandler(options5) {
				    	public void handleReply(final int option, final String reply) {
				    		switch (option) {
					        	case 0: // Yes
									owner.setBusy(true);
									n.setBusy(true);
									sleep(1500);
									owner.informOfNpcMessage(new ChatMessage(n, "Sorry I don't got any quests to give you", owner));
									sleep(2000);
									owner.informOfNpcMessage(new ChatMessage(n, "come back some other time maybe I got something for you then", owner));
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
			    	p.getActionSender().sendMenu(options5);
				} 
		}
	}
	
	@Override
	public void onInvUseOnObject(GameObject obj, InvItem item, Player player) {
			if(obj.getID() == 236 && player.getQuestStage(this) == 2 && item.getID() == 133) {
				player.getActionSender().sendMessage("You dip the meat into the Cauldron of Thunder.");
				sleep(2000);
				player.setBusy(true);
				player.getActionSender().sendMessage("The meat has now been enchanted.");
				player.getInventory().remove(133, 1);
				sleep(2000);
				player.getInventory().add(new InvItem(508));
				player.getActionSender().sendInventory();
				player.setBusy(false);				
			}	
			else if(obj.getID() == 236 && player.getQuestStage(this) == 2 && item.getID() == 502) {
				player.getActionSender().sendMessage("You dip the meat into the Cauldron of Thunder.");
				sleep(2000);
				player.setBusy(true);
				player.getActionSender().sendMessage("The meat has now been enchanted.");
				player.getInventory().remove(502, 1);
				sleep(2000);
				player.getInventory().add(new InvItem(505));
				player.getActionSender().sendInventory();
				player.setBusy(false);					
			}
			else if(obj.getID() == 236 && player.getQuestStage(this) == 2 && item.getID() == 503) {
				player.getActionSender().sendMessage("You dip the meat into the Cauldron of Thunder.");
				sleep(2000);
				player.setBusy(true);
				player.getActionSender().sendMessage("The meat has now been enchanted.");
				player.getInventory().remove(503, 1);
				sleep(2000);
				player.getInventory().add(new InvItem(506));
				player.getActionSender().sendInventory();
				player.setBusy(false);			
			}
			else if(obj.getID() == 236 && player.getQuestStage(this) == 2 && item.getID() == 504) {
				player.getActionSender().sendMessage("You dip the meat into the Cauldron of Thunder.");
				sleep(2000);
				player.setBusy(true);
				player.getActionSender().sendMessage("The meat has now been enchanted.");
				player.getInventory().remove(504, 1);
				sleep(2000);
				player.getInventory().add(new InvItem(507));
				player.getActionSender().sendInventory();
				player.setBusy(false);					
			}
	}

	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		if(n.getID() == 204 || n.getID() == 205) {
			return true;
		}
		return false;
	}
	@Override
	public boolean blockInvUseOnObject(GameObject obj, InvItem item, Player player) {
		if(item.getID() == 133 || item.getID() == 502 || item.getID() == 503 || item.getID() == 504 && obj.getID() == 236) {
			return true;
		}
		return false;
	}
	
}