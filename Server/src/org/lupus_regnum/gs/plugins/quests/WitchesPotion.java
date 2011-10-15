package org.lupus_regnum.gs.plugins.quests;

import org.lupus_regnum.gs.model.ChatMessage;
import org.lupus_regnum.gs.model.GameObject;
import org.lupus_regnum.gs.model.MenuHandler;
import org.lupus_regnum.gs.model.Npc;
import org.lupus_regnum.gs.model.Player;
import org.lupus_regnum.gs.plugins.Quest;
import org.lupus_regnum.gs.plugins.listeners.action.ObjectActionListener;
import org.lupus_regnum.gs.plugins.listeners.action.TalkToNpcListener;
import org.lupus_regnum.gs.plugins.listeners.executive.ObjectActionExecutiveListener;
import org.lupus_regnum.gs.plugins.listeners.executive.TalkToNpcExecutiveListener;

/*
 * TODO: restrict rats tails from being seen by players not on the quest fix
 *
 * @author Kakur007
*/
public class WitchesPotion extends Quest implements 
        TalkToNpcListener, 
        TalkToNpcExecutiveListener, 
        ObjectActionListener, 
        ObjectActionExecutiveListener {

	@Override
	public int getQuestId() {
		return 11;
	}

	@Override
	public String getQuestName() {
		return "Witch's Potion";
	}
	
	@Override
	public void onTalkToNpc(Player p, final Npc n) {
		if(n.getID() == 148) {			
				if(p.getQuestStage(this) == 0) {
					n.setBusy(true);
					p.setBusy(true);
					System.out.println("Quest stage: " + 0);
					p.informOfNpcMessage(new ChatMessage(n, "Greetings traveller", p));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "What could you want with an old woman like me", p));
					sleep(2000);
					n.setBusy(false);
					p.setBusy(false);
					String[] options = new String[] { "I am in search of a quest", "I've heard that you are a witch" };
			    	p.setMenuHandler(new MenuHandler(options) {
				    	public void handleReply(final int option, final String reply) {
				    		switch (option) {
					        	case 0: // Yes
									n.setBusy(true);
									owner.setBusy(true);
									sleep(1500);
									owner.informOfNpcMessage(new ChatMessage(n, "Hmm maybe i can think of something for you", owner));
									sleep(2000);
									owner.informOfNpcMessage(new ChatMessage(n, "Would you like to become more proficient in the dark arts?", owner));
									sleep(2000);
										n.setBusy(false);
										owner.setBusy(false);
										String[] options2 = new String[] { "Yes help me become one with my darker side", "No I have my principles and honour", "What, you mean improve my magic?" };
										owner.setMenuHandler(new MenuHandler(options2) {
											public void handleReply(final int option, final String reply) {
												switch (option) {
													case 0: //yes
														owner.setBusy(true);
														n.setBusy(false);
														sleep(1500);
														owner.informOfNpcMessage(new ChatMessage(n, "Ok, I'm going to make a potion to help bring out your darker self", owner));
														sleep(2000);
														owner.informOfNpcMessage(new ChatMessage(n, "So that you can perform acts of dark magic with greater ease", owner));
														sleep(2000);
														owner.informOfChatMessage(new ChatMessage(owner, "Dark magic?", n));
														sleep(2000);
														owner.informOfNpcMessage(new ChatMessage(n, "It's not as ominous as it sounds, trust me", owner));
														sleep(2000);
														owner.setBusy(false);
														n.setBusy(false);
														String[] options3 = new String[] { "No, I don't like the sound of it", "Well, alright..." };
														owner.setMenuHandler(new MenuHandler(options3) {
															public void handleReply(final int option, final String reply) {
																switch (option) {
																	case 0: //yes
																		owner.setBusy(true);
																		n.setBusy(false);
																		sleep(1500);
																		owner.informOfNpcMessage(new ChatMessage(n, "Fine, suit yourself", owner));
																		sleep(2000);
																		owner.informOfNpcMessage(new ChatMessage(n, "But I sense a great deal of dark power within you", owner));
																		sleep(2000);
																		owner.informOfNpcMessage(new ChatMessage(n, "You'll change your mind one day", owner));
																		owner.setBusy(false);
																		n.setBusy(false);
																		break;
																	case 1:
																		owner.setBusy(true);
																		n.setBusy(false);
																		sleep(1500);
																		owner.informOfNpcMessage(new ChatMessage(n, "You will need certain ingredients", owner));
																		sleep(2000);
																		owner.informOfChatMessage(new ChatMessage(owner, "What do i need?", n));
																		sleep(2000);
																		owner.informOfNpcMessage(new ChatMessage(n, "You need an eye of newt, a rat's tail, an onion and a pieace of burnt meat", owner));
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
														owner.getActionSender().sendMenu(options3);
														break;
													case 1: // No
														owner.setBusy(true);
														n.setBusy(true);
														sleep(1500);
														owner.informOfNpcMessage(new ChatMessage(n, "Suit yourself, but you're missing out", owner));
														n.setBusy(false);
														owner.setBusy(false);
														break;
													case 2:	
														owner.setBusy(true);
														n.setBusy(true);
														sleep(1500);
														owner.informOfNpcMessage(new ChatMessage(n, "Yes improve your magic", owner));
														sleep(2000);
														owner.informOfNpcMessage(new ChatMessage(n, "Do you have no sense of drama", owner));
														sleep(2000);
														n.setBusy(false);
														owner.setBusy(false);
														String[] options4 = new String[] { "Yes I'd like to improve my magic", "No I'm not interested", "Show me the mysteries of the dark arts" };
														owner.setMenuHandler(new MenuHandler(options4) {
															public void handleReply(final int option, final String reply) {
																switch (option) {
																	case 0: //yes
																		owner.setBusy(true);
																		n.setBusy(false);
																		sleep(1500);
																		owner.informOfNpcMessage(new ChatMessage(n, "Ok, I'm going to make a potion to help bring out your darker self", owner));
																		sleep(2000);
																		owner.informOfNpcMessage(new ChatMessage(n, "So that you can perform acts of dark magic with greater ease", owner));
																		sleep(2000);
																		owner.informOfChatMessage(new ChatMessage(owner, "Dark magic?", n));
																		sleep(2000);
																		owner.informOfNpcMessage(new ChatMessage(n, "It's not as ominous as it sounds, trust me", owner));
																		sleep(2000);
																		owner.setBusy(false);
																		n.setBusy(false);
																		String[] options5 = new String[] { "No, I don't like the sound of it", "Well, alright..." };
																		owner.setMenuHandler(new MenuHandler(options5) {
																			public void handleReply(final int option, final String reply) {
																				switch (option) {
																					case 0: //yes
																						owner.setBusy(true);
																						n.setBusy(false);
																						sleep(1500);
																						owner.informOfNpcMessage(new ChatMessage(n, "Fine, suit yourself", owner));
																						sleep(2000);
																						owner.informOfNpcMessage(new ChatMessage(n, "But I sense a great deal of dark power within you", owner));
																						sleep(2000);
																						owner.informOfNpcMessage(new ChatMessage(n, "You'll change your mind one day", owner));
																						owner.setBusy(false);
																						n.setBusy(false);
																						break;
																					case 1:
																						owner.setBusy(true);
																						n.setBusy(false);
																						sleep(1500);
																						owner.informOfNpcMessage(new ChatMessage(n, "You will need certain ingredients", owner));
																						sleep(2000);
																						owner.informOfChatMessage(new ChatMessage(owner, "What do i need?", n));
																						sleep(2000);
																						owner.informOfNpcMessage(new ChatMessage(n, "You need an eye of newt, a rat's tail, an onion and a pieace of burnt meat", owner));
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
																		owner.getActionSender().sendMenu(options5);
																		break;
																	case 1:
																		owner.setBusy(true);
																		n.setBusy(false);
																		sleep(1500);
																		owner.informOfNpcMessage(new ChatMessage(n, "Many aren't to start off with", owner));
																		sleep(2000);
																		owner.informOfNpcMessage(new ChatMessage(n, "But i think you'll be drawn back to this place", owner));
																		owner.setBusy(false);
																		n.setBusy(false);
																		break;
																	case 2:
																		owner.setBusy(true);
																		n.setBusy(false);
																		sleep(1500);
																		owner.informOfNpcMessage(new ChatMessage(n, "Ok, I'm going to make a potion to help bring out your darker self", owner));
																		sleep(2000);
																		owner.informOfNpcMessage(new ChatMessage(n, "So that you can perform acts of dark magic with greater ease", owner));
																		sleep(2000);
																		owner.informOfChatMessage(new ChatMessage(owner, "Dark magic?", n));
																		sleep(2000);
																		owner.informOfNpcMessage(new ChatMessage(n, "It's not as ominous as it sounds, trust me", owner));
																		sleep(2000);
																		owner.setBusy(false);
																		n.setBusy(false);
																		String[] options6 = new String[] { "No, I don't like the sound of it", "Well, alright..." };
																		owner.setMenuHandler(new MenuHandler(options6) {
																			public void handleReply(final int option, final String reply) {
																				switch (option) {
																					case 0: //yes
																						owner.setBusy(true);
																						n.setBusy(false);
																						sleep(1500);
																						owner.informOfNpcMessage(new ChatMessage(n, "Fine, suit yourself", owner));
																						sleep(2000);
																						owner.informOfNpcMessage(new ChatMessage(n, "But I sense a great deal of dark power within you", owner));
																						sleep(2000);
																						owner.informOfNpcMessage(new ChatMessage(n, "You'll change your mind one day", owner));
																						owner.setBusy(false);
																						n.setBusy(false);
																						break;
																					case 1:
																						owner.setBusy(true);
																						n.setBusy(false);
																						sleep(1500);
																						owner.informOfNpcMessage(new ChatMessage(n, "You will need certain ingredients", owner));
																						sleep(2000);
																						owner.informOfChatMessage(new ChatMessage(owner, "What do i need?", n));
																						sleep(2000);
																						owner.informOfNpcMessage(new ChatMessage(n, "You need an eye of newt, a rat's tail, an onion and a pieace of burnt meat", owner));
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
																		owner.getActionSender().sendMenu(options6);
																		break;
																	default:
																		owner.setBusy(false);
																		n.setBusy(false);
																		return;
																}
															}
														});
														owner.getActionSender().sendMenu(options4);
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
									owner.informOfNpcMessage(new ChatMessage(n, "Yes it does seem to be getting fairly common knowledge", owner));
									sleep(2000);
									owner.informOfNpcMessage(new ChatMessage(n, "I fear i may get a visit from the witch hunters of falador before long", owner));
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
						p.informOfNpcMessage(new ChatMessage(n, "So have you found the things for the potion", p));
						sleep(2000);
					if (p.getInventory().hasItemId(271) && p.getInventory().hasItemId(270) && p.getInventory().hasItemId(134) && p.getInventory().hasItemId(241)) {
						p.informOfChatMessage(new ChatMessage(p, "Yes i have everything", n));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "Excellent, can i have them then?", p));
						sleep(2000);
						p.getActionSender().sendMessage("You pass the ingredients to Hetty");
						p.getInventory().remove(271, 1);
						p.getInventory().remove(270, 1);
						p.getInventory().remove(134, 1);
						p.getInventory().remove(241, 1);
						p.getActionSender().sendInventory();
						sleep(2000);
						p.getActionSender().sendMessage("Hetty puts all the ingredients in her cauldron");
						sleep(2000);
						p.getActionSender().sendMessage("She closes her eyes and begins to chant");
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "Ok drink from the cauldron", p));
						sleep(2000);
						p.setQuestStage(getQuestId(), 2);
						n.setBusy(false);
						p.setBusy(false);
					} else {
						p.informOfChatMessage(new ChatMessage(p, "No not yet", n));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "Well remember what you need to get", p));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "An eye of newt, a rat's tail, some burnt meat and an onion", p));
						sleep(2000);
						n.setBusy(false);
						p.setBusy(false);	
					}
				} 
				else if(p.getQuestStage(this) == 2) {
					p.setBusy(true);
					n.blockedBy(p);
					p.informOfNpcMessage(new ChatMessage(n, "Well are you going to drink the potion or not?", p));
					sleep(2000);
					p.setBusy(false);
					n.setBusy(false);		
				} 
				else if(p.getQuestStage(this) == -1) {
					p.setBusy(true);
					n.blockedBy(p);
					p.informOfNpcMessage(new ChatMessage(n, "Greetings traveller", p));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "How's your magic coming along", p));
					sleep(2000);
					p.informOfChatMessage(new ChatMessage(p, "I'm practicing and slowly getting better", n));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "Good, good", p));
					p.setBusy(false);
					n.setBusy(false);
				}
		} 
		else if(n.getID() == 29) {
			if(p.getQuestStage(this) >= -1) {
				p.setBusy(true);
				p.getActionSender().sendMessage("Rats can't talk!");
				p.setBusy(false);
				n.setBusy(false);
			}
		}
	}
	
	@Override
	public void onObjectAction(GameObject obj, String command, Player player) {
			if(command.equals("drink from") && obj.getID() == 147 && obj.getX() == 316 && obj.getY() == 666) {
				if(player.getQuestStage(this) != 2) {
					player.setBusy(true);
					player.informOfChatMessage(new ChatMessage(player, "I'd rather not", null));
					sleep(2000);
					player.informOfChatMessage(new ChatMessage(player, "It doesn't look very tasty", null));
					sleep(2000);
					player.setBusy(false);
				} else {
					player.setBusy(true);
					player.getActionSender().sendMessage("You drink from the cauldron");
					sleep(2000);
					player.getActionSender().sendMessage("You feel yourself imbued with power");
					sleep(2000);
					player.setQuestStage(getQuestId(), -1);
					player.incExp(6, 1000, false, false, false);
					player.setBusy(false);
					return;
				}				
			}
	}
	
	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		if(n.getID() == 148 || n.getID() == 29) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean blockObjectAction(GameObject obj, String command, Player player) {
		if(obj.getID() == 175 && command.equals("search")) {
			return true;
		}
		return false;
	}	
}
