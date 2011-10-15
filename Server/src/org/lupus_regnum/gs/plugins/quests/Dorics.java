package org.lupus_regnum.gs.plugins.quests;

import org.lupus_regnum.config.Formulae;
import org.lupus_regnum.gs.model.ChatMessage;
import org.lupus_regnum.gs.model.GameObject;
import org.lupus_regnum.gs.model.InvItem;
import org.lupus_regnum.gs.model.MenuHandler;
import org.lupus_regnum.gs.model.Npc;
import org.lupus_regnum.gs.model.Player;
import org.lupus_regnum.gs.model.World;
import org.lupus_regnum.gs.plugins.Quest;
import org.lupus_regnum.gs.plugins.listeners.action.InvUseOnObjectListener;
import org.lupus_regnum.gs.plugins.listeners.action.TalkToNpcListener;
import org.lupus_regnum.gs.plugins.listeners.executive.InvUseOnObjectExecutiveListener;
import org.lupus_regnum.gs.plugins.listeners.executive.TalkToNpcExecutiveListener;

public class Dorics extends Quest implements 
        TalkToNpcListener, 
        TalkToNpcExecutiveListener, 
        InvUseOnObjectListener, 
        InvUseOnObjectExecutiveListener {

	@Override
	public int getQuestId() {
		return 5;
	}

	@Override
	public String getQuestName() {
		return "Doric's Quest";
	}
	
	@Override
	public void onTalkToNpc(Player p, final Npc n) {
		if(n.getID() == 144) {			
				if(p.getQuestStage(this) == 0) {
					n.setBusy(true);
					p.setBusy(true);
					System.out.println("Quest stage: " + 0);
					p.informOfNpcMessage(new ChatMessage(n, "Hello traveller, what brings you to my humble smithy", p));
					sleep(2000);
					n.setBusy(false);
					p.setBusy(false);
					String[] options = new String[] { "I wanted to use your anvils", "Mind your own business, shortstuff", "I was just checking out the landscape", "What do you make here?" };
			    	p.setMenuHandler(new MenuHandler(options) {
				    	public void handleReply(final int option, final String reply) {
				    		switch (option) {
					        	case 0: // Yes
									n.blockedBy(owner);
									owner.setBusy(true);
									sleep(1500);
									owner.informOfNpcMessage(new ChatMessage(n, "My anvils get enough work with my own use", owner));
									sleep(2000);
									owner.informOfNpcMessage(new ChatMessage(n, "I make amulets, it takes a lot of work", owner));
									sleep(2000);
									owner.informOfNpcMessage(new ChatMessage(n, "If you could get me some more materials i could let you use them", owner));
									sleep(2000);
										n.setBusy(false);
										owner.setBusy(false);
										String[] options2 = new String[] { "Yes I will get you materials", "No, hitting rocks is for the boring people, sorry." };
										owner.setMenuHandler(new MenuHandler(options2) {
											public void handleReply(final int option, final String reply) {
												switch (option) {
													case 0: //yes
														owner.setBusy(true);
														n.setBusy(false);
														sleep(1500);
														owner.informOfNpcMessage(new ChatMessage(n, "Well, clay is what i use more than anything, i make casts", owner));
														sleep(2000);
														owner.informOfNpcMessage(new ChatMessage(n, "Could you get me 6 clay, and 4 copper ore, and 2 iron ore please?", owner));
														sleep(2000);
														owner.informOfNpcMessage(new ChatMessage(n, "I could pay a little, and let you use my anvils", owner));
														sleep(2000);
														owner.informOfChatMessage(new ChatMessage(owner, "Certainly, i will get them for you. Goodbye", n));
														sleep(2000);
														owner.setQuestStage(getQuestId(), 1);
														n.setBusy(false);
														owner.setBusy(false);
														break;
													case 1: // No
														owner.setBusy(true);
														n.setBusy(true);
														sleep(1500);
														owner.informOfNpcMessage(new ChatMessage(n, "That is your choice, nice to meet you anyway", owner));
														n.setBusy(false);
														owner.setBusy(false);
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
					        	case 1: // No
					        		n.setBusy(true);
									owner.setBusy(true);
									sleep(1500);
									owner.informOfNpcMessage(new ChatMessage(n, "How nice to meet someone with such pleasant manners", owner));
									sleep(2000);
									owner.informOfNpcMessage(new ChatMessage(n, "Do come again when you need to shout at someone smaller than you", owner));
									sleep(2000);
									n.setBusy(false);
									owner.setBusy(false);
									break;
								case 2:
									n.setBusy(true);
									owner.setBusy(true);
									sleep(1500);
									owner.informOfNpcMessage(new ChatMessage(n, "We have a fine town here, its suits us very well", owner));
									sleep(2000);
									owner.informOfChatMessage(new ChatMessage(owner, "Do you have any to sell?", n));
									sleep(2000);
									owner.informOfNpcMessage(new ChatMessage(n, "Not at the moment, sorry. Try again later", owner));
									sleep(2000);
									owner.setBusy(false);
									n.setBusy(false);
									break;
								case 3:
									n.setBusy(true);
									owner.setBusy(true);
									sleep(1500);
									owner.informOfNpcMessage(new ChatMessage(n, "I make amulets. I am the best maker of them in runescape", owner));
									sleep(2000);
									owner.informOfNpcMessage(new ChatMessage(n, "Do come again when you need to shout at someone smaller than you", owner));
									sleep(2000);
									n.setBusy(false);
									owner.setBusy(false);
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
						p.setBusy(true);
						n.setBusy(true);
						p.informOfNpcMessage(new ChatMessage(n, "Have you got my materials yet traveller?", p));
						sleep(2000);
					if (p.getInventory().countId(149) >= 6 && p.getInventory().countId(150) >= 4 && p.getInventory().countId(151) >= 2) {
						p.informOfChatMessage(new ChatMessage(p, "Yes, i do have them all here", n));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "Excellent! Hand 'em over, then", p));
						sleep(2000);
						p.getActionSender().sendMessage("You hand Doric the materials");
						for (int i = 0; i < 6; i++)
							p.getInventory().remove(149, 1);
						for (int i = 0; i < 4; i++)
							p.getInventory().remove(150, 1);
						for (int i = 0; i < 2; i++)
							p.getInventory().remove(151, 1);
						
						p.getActionSender().sendInventory();
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "Thanks for your help " + p.getUsername(), p));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "Anyone who lends a hand is welcome in my workshop", p));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "Come back and use my anvils whenever you like!", p));
						sleep(2000);
						p.getInventory().add(new InvItem(10, 1500));
						p.incExp(13, 1000, false, false, false);
						p.setQuestStage(getQuestId(), -1);
						n.setBusy(false);
						p.setBusy(false);
					} else {
						p.informOfChatMessage(new ChatMessage(p, "Sorry, i don't have them all yet", n));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "Not to worry, stick at it", p));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "Remember i need 6 clay, 4 copper ore and 2 iron ore", p));
						sleep(2000);
						n.setBusy(false);
						p.setBusy(false);	
					}
				} else if(p.getQuestStage(this) == -1) {
					p.setBusy(true);
					n.blockedBy(p);
					p.informOfNpcMessage(new ChatMessage(n, "Be sure to use my anvils at anytime friend!", p));
					sleep(2000);
					p.informOfChatMessage(new ChatMessage(p, "I will do that, thanks.", n));
					p.setBusy(false);
					n.unblock();		
				}
		}
	}
	
	@Override
	public void onInvUseOnObject(GameObject obj, InvItem item, Player player) {
		if(obj.getID() == 177 && player.getQuestStage(this) == -1) {
			int minSmithingLevel = Formulae.minSmithingLevel(item.getID());
			if (minSmithingLevel < 0) {
				player.getActionSender().sendMessage("Nothing interesting happens.");
				return;
			}
			if (player.getInventory().countId(168) < 1) {
				player.getActionSender().sendMessage("You need a hammer to work the metal with.");
				return;
			}
			if (player.getCurStat(13) < minSmithingLevel) {
				player.getActionSender().sendMessage("You need a smithing level of " + minSmithingLevel + " to use this type of bar");
				return;
			}
			player.setSmithing(true);
			player.setSmithingBar(item.getID());
			player.getActionSender().showSmithing(Formulae.getBarType(item.getID()));	
		} else if(obj.getID() == 177 && player.getQuestStage(this) > -1) {
			Npc doric = World.getWorld().getNpc(144, 323, 327, 487, 492, true);
			player.informOfNpcMessage(new ChatMessage(doric, "Heh who said you could use that?", player));
			player.getActionSender().sendMessage("You need to finish " + getQuestName() + " to use this anvil");
			player.setBusy(false);
		}
	}
	
	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		if(n.getID() == 144) {
			return true;
		}
		return false;
	}
	@Override
	public boolean blockInvUseOnObject(GameObject obj, InvItem item, Player player) {
		if(obj.getID() == 177) {
			return true;
		}
		return false;
	}
}
