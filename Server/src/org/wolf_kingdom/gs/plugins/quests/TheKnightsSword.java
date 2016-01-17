package org.wolf_kingdom.gs.plugins.quests;

import org.wolf_kingdom.gs.model.ChatMessage;
import org.wolf_kingdom.gs.model.GameObject;
import org.wolf_kingdom.gs.model.InvItem;
import org.wolf_kingdom.gs.model.MenuHandler;
import org.wolf_kingdom.gs.model.Npc;
import org.wolf_kingdom.gs.model.Player;
import org.wolf_kingdom.gs.model.World;
import org.wolf_kingdom.gs.plugins.Quest;
import org.wolf_kingdom.gs.plugins.listeners.action.ObjectActionListener;
import org.wolf_kingdom.gs.plugins.listeners.action.TalkToNpcListener;
import org.wolf_kingdom.gs.plugins.listeners.executive.ObjectActionExecutiveListener;
import org.wolf_kingdom.gs.plugins.listeners.executive.TalkToNpcExecutiveListener;

public class TheKnightsSword extends Quest implements TalkToNpcListener, 
        TalkToNpcExecutiveListener, 
        ObjectActionListener, 
        ObjectActionExecutiveListener {

	@Override
	public int getQuestId() {
		return 10;
	}

	@Override
	public String getQuestName() {
		return "The Knight's Sword";
	}
	
	@Override
	public void onTalkToNpc(Player p, final Npc n) {
		if(n.getID() == 132) {			
				if(p.getQuestStage(this) == 0) {
					n.setBusy(true);
					p.setBusy(true);
					System.out.println("Quest stage: " + 0);
					p.informOfNpcMessage(new ChatMessage(n, "Hello, i am the squire to sir vyvin", p));
					sleep(2000);
					p.setBusy(false);
					n.setBusy(false);
					String[] options = new String[] { "And how is life as squire?", "Wouldn't you prefer to be a squire for me?" };
			    	p.setMenuHandler(new MenuHandler(options) {
				    	public void handleReply(final int option, final String reply) {
				    		switch (option) {
					        	case 1: // No
									sleep(1500);
									owner.informOfNpcMessage(new ChatMessage(n, "No, sorry i'm loyal to vyvin", owner));
									n.setBusy(false);
									owner.setBusy(false);
					        		break;
					        	case 0: // Yes
									n.setBusy(true);
									owner.setBusy(true);
									sleep(1500);
					        		owner.informOfNpcMessage(new ChatMessage(n, "Well sir vyvin is a good guy to work for", owner));
									sleep(2000);
									owner.informOfNpcMessage(new ChatMessage(n, "However i'm in a spot of trouble today", owner));
									sleep(2000);
									owner.informOfNpcMessage(new ChatMessage(n, "I've gone and lost sir vyvin's sword", owner));
									sleep(2000);
									owner.setBusy(false);
									n.setBusy(true);
									String[] options2 = new String[] { "Do you know where you lost it?", "I can make a new sword if you like", "Is he angry?" };
											owner.setMenuHandler(new MenuHandler(options2) {
												public void handleReply(final int option, final String reply) {
													switch (option) {
														case 0: //yes
															sleep(1500);
															owner.informOfNpcMessage(new ChatMessage(n, "Well now if i knew that", owner));
															sleep(2000);
															owner.informOfNpcMessage(new ChatMessage(n, "It wouldn't be lost, now would it?", owner));
															sleep(2000);
															owner.setBusy(false);
															n.setBusy(false);
															String[] options3 = new String[] { "Well do you know the vague area you lost it?", "I can make a new sword if you like", "Well the kingdom is fairly abundant with swords", "Well I hope you find it soon" };
																owner.setMenuHandler(new MenuHandler(options3) {
																	public void handleReply(final int option, final String reply) {
																		switch (option) {
																			case 0:
																				owner.setBusy(true);
																				n.setBusy(true);
																				sleep(1500);
																				owner.informOfNpcMessage(new ChatMessage(n, "No i was carrying it for him all the way from where he had it stored in Varrock", owner));
																				sleep(2000);
																				owner.informOfNpcMessage(new ChatMessage(n, "It must have slipped from my pack during the trip", owner));
																				sleep(2000);
																				owner.informOfNpcMessage(new ChatMessage(n, "And you know what people are like these days", owner));
																				sleep(2000);
																				owner.informOfNpcMessage(new ChatMessage(n, "Someone will have just picked it up and kept it for themselves", owner));
																				sleep(2000);
																				owner.setBusy(false);
																				n.setBusy(false);
																				String[] options4 = new String[]{ "I can make a new sword if you like", "Well the kingdom is fairly abundant with swords", "Well I hope you find it soon" };
																					owner.setMenuHandler(new MenuHandler(options4) {
																						public void handleReply(final int option, final String reply) {
																							switch(option) {
																								case 0:
																									owner.setBusy(true);
																									n.setBusy(true);
																									sleep(1500);
																									owner.informOfNpcMessage(new ChatMessage(n, "Thanks for the offer", owner));
																									sleep(2000);
																									owner.informOfNpcMessage(new ChatMessage(n, "I'd be surprised if you could though", owner));
																									sleep(2000);
																									owner.informOfNpcMessage(new ChatMessage(n, "The thing is, this sword is a family heirloom", owner));
																									sleep(2000);
																									owner.informOfNpcMessage(new ChatMessage(n, "It has been passed down through vyvin's family for five generations", owner));
																									sleep(2000);
																									owner.informOfNpcMessage(new ChatMessage(n, "It was originally made by the incando dwarves", owner));
																									sleep(2000);
																									owner.informOfNpcMessage(new ChatMessage(n, "Who were a particularly skilled tribe of dwarven smiths", owner));
																									sleep(2000);
																									owner.informOfNpcMessage(new ChatMessage(n, "I doubt anyone could make it in the style they do", owner));
																									sleep(2000);
																									owner.setBusy(false);
																									n.setBusy(false);
																									String[] options5 = new String[]{ "So would these dwarves make another one?", "Well I hope you find it soon" };
																									owner.setMenuHandler(new MenuHandler(options5) {
																										public void handleReply(final int option, final String reply) {
																											switch(option) {
																												case 0:
																													owner.setBusy(true);
																													n.setBusy(true);
																													sleep(1500);
																													owner.informOfNpcMessage(new ChatMessage(n, "I'm not a hundred percent sure the incando tribe exists anymore", owner));
																													sleep(2000);
																													owner.informOfNpcMessage(new ChatMessage(n, "I should think reldo the palace librarian in varrock will know", owner));
																													sleep(2000);
																													owner.informOfNpcMessage(new ChatMessage(n, "He has done a lot of research on the races of these lands", owner));
																													sleep(2000);
																													owner.informOfNpcMessage(new ChatMessage(n, "I don't suppose you could try and track down the incando dwarves for me?", owner));
																													sleep(2000);
																													owner.informOfNpcMessage(new ChatMessage(n, "I've got so much work to do", owner));
																													sleep(2000);
																													owner.setBusy(false);
																													n.setBusy(false);
																													String[] options6 = new String[]{ "Ok I'll give it a go", "No I've got lots of mining work to do" };
																													owner.setMenuHandler(new MenuHandler(options6) {
																														public void handleReply(final int option, final String reply) {
																															switch(option) {
																																case 0:
																																	owner.setBusy(true);
																																	n.setBusy(true);
																																	sleep(1500);
																																	owner.informOfNpcMessage(new ChatMessage(n, "Thanyou very much", owner));
																																	sleep(2000);
																																	owner.informOfNpcMessage(new ChatMessage(n, "As i say the best place to start should be with reldo", owner));
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
																													owner.getActionSender().sendMenu(options6);
																													break;
																												case 1:
																													owner.setBusy(true);
																													n.setBusy(true);
																													sleep(1500);
																													owner.informOfNpcMessage(new ChatMessage(n, "Yes me too", owner));
																													sleep(2000);
																													owner.informOfNpcMessage(new ChatMessage(n, "I'm not looking forward to telling vyvin i've lost it", owner));
																													sleep(2000);
																													owner.informOfNpcMessage(new ChatMessage(n, "He's going to want it for the parade next week as well", owner));
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
																									n.setBusy(true);
																									sleep(1500);
																									owner.informOfNpcMessage(new ChatMessage(n, "Yes you can get bronze swords anywhere", owner));
																									sleep(2000);
																									owner.informOfNpcMessage(new ChatMessage(n, "But this isn't any old sword", owner));
																									sleep(2000);
																									owner.informOfNpcMessage(new ChatMessage(n, "The thing is, this sword is a family heirloom", owner));
																									sleep(2000);
																									owner.informOfNpcMessage(new ChatMessage(n, "It has been passed down through vyvin's family for five generations", owner));
																									sleep(2000);
																									owner.informOfNpcMessage(new ChatMessage(n, "It was originally made by the incando dwarves", owner));
																									sleep(2000);
																									owner.informOfNpcMessage(new ChatMessage(n, "Who were a particularly skilled tribe of dwarven smiths", owner));
																									sleep(2000);
																									owner.informOfNpcMessage(new ChatMessage(n, "I doubt anyone could make it in the style they do", owner));
																									sleep(2000);
																									owner.setBusy(false);
																									n.setBusy(false);
																									String[] options7 = new String[]{ "So would these dwarves make another one?", "Well I hope you find it soon" };
																									owner.setMenuHandler(new MenuHandler(options7) {
																										public void handleReply(final int option, final String reply) {
																											switch(option) {
																												case 0:
																													owner.setBusy(true);
																													n.setBusy(true);
																													sleep(1500);
																													owner.informOfNpcMessage(new ChatMessage(n, "I'm not a hundred percent sure the incando tribe exists anymore", owner));
																													sleep(2000);
																													owner.informOfNpcMessage(new ChatMessage(n, "I should think reldo the palace librarian in varrock will know", owner));
																													sleep(2000);
																													owner.informOfNpcMessage(new ChatMessage(n, "He has done a lot of research on the races of these lands", owner));
																													sleep(2000);
																													owner.informOfNpcMessage(new ChatMessage(n, "I don't suppose you could try and track down the incando dwarves for me?", owner));
																													sleep(2000);
																													owner.informOfNpcMessage(new ChatMessage(n, "I've got so much work to do", owner));
																													sleep(2000);
																													owner.setBusy(false);
																													n.setBusy(false);
																													String[] options8 = new String[]{ "Ok I'll give it a go", "No I've got lots of mining work to do" };
																													owner.setMenuHandler(new MenuHandler(options8) {
																														public void handleReply(final int option, final String reply) {
																															switch(option) {
																																case 0:
																																	owner.setBusy(true);
																																	n.setBusy(true);
																																	sleep(1500);
																																	owner.informOfNpcMessage(new ChatMessage(n, "Thanyou very much", owner));
																																	sleep(2000);
																																	owner.informOfNpcMessage(new ChatMessage(n, "As i say the best place to start should be with reldo", owner));
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
																													owner.getActionSender().sendMenu(options8);
																													break;
																												case 1:
																													owner.setBusy(true);
																													n.setBusy(true);
																													sleep(1500);
																													owner.informOfNpcMessage(new ChatMessage(n, "Yes me too", owner));
																													sleep(2000);
																													owner.informOfNpcMessage(new ChatMessage(n, "I'm not looking forward to telling vyvin i've lost it", owner));
																													sleep(2000);
																													owner.informOfNpcMessage(new ChatMessage(n, "He's going to want it for the parade next week as well", owner));
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
																									owner.getActionSender().sendMenu(options7);
																									break;
																								case 2:
																									owner.setBusy(true);
																									n.setBusy(true);
																									sleep(1500);
																									owner.informOfNpcMessage(new ChatMessage(n, "Yes me too", owner));
																									sleep(2000);
																									owner.informOfNpcMessage(new ChatMessage(n, "I'm not looking forward to telling vyvin i've lost it", owner));
																									sleep(2000);
																									owner.informOfNpcMessage(new ChatMessage(n, "He's going to want it for the parade next week as well", owner));
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
																					owner.getActionSender().sendMenu(options4);
																					break;
																				case 1:
																					owner.setBusy(true);
																					n.setBusy(true);
																					sleep(1500);
																					owner.informOfNpcMessage(new ChatMessage(n, "Thanks for the offer", owner));
																					sleep(2000);
																					owner.informOfNpcMessage(new ChatMessage(n, "I'd be surprised if you could though", owner));
																					sleep(2000);
																					owner.informOfNpcMessage(new ChatMessage(n, "The thing is, this sword is a family heirloom", owner));
																					sleep(2000);
																					owner.informOfNpcMessage(new ChatMessage(n, "It has been passed down through vyvin's family for five generations", owner));
																					sleep(2000);
																					owner.informOfNpcMessage(new ChatMessage(n, "It was originally made by the incando dwarves", owner));
																					sleep(2000);
																					owner.informOfNpcMessage(new ChatMessage(n, "Who were a particularly skilled tribe of dwarven smiths", owner));
																					sleep(2000);
																					owner.informOfNpcMessage(new ChatMessage(n, "I doubt anyone could make it in the style they do", owner));
																					sleep(2000);
																					owner.setBusy(false);
																					n.setBusy(false);
																					String[] options9 = new String[]{ "So would these dwarves make another one?", "Well I hope you find it soon" };
																						owner.setMenuHandler(new MenuHandler(options9) {
																							public void handleReply(final int option, final String reply) {
																								switch(option) {
																									case 0:
																										owner.setBusy(true);
																										n.setBusy(true);
																										sleep(1500);
																										owner.informOfNpcMessage(new ChatMessage(n, "I'm not a hundred percent sure the incando tribe exists anymore", owner));
																										sleep(2000);
																										owner.informOfNpcMessage(new ChatMessage(n, "I should think reldo the palace librarian in varrock will know", owner));
																										sleep(2000);
																										owner.informOfNpcMessage(new ChatMessage(n, "He has done a lot of research on the races of these lands", owner));
																										sleep(2000);
																										owner.informOfNpcMessage(new ChatMessage(n, "I don't suppose you could try and track down the incando dwarves for me?", owner));
																										sleep(2000);
																										owner.informOfNpcMessage(new ChatMessage(n, "I've got so much work to do", owner));
																										sleep(2000);
																										owner.setBusy(false);
																										n.setBusy(false);
																										String[] options10 = new String[]{ "Ok I'll give it a go", "No I've got lots of mining work to do" };
																											owner.setMenuHandler(new MenuHandler(options10) {
																												public void handleReply(final int option, final String reply) {
																													switch(option) {
																														case 0:
																															owner.setBusy(true);
																															n.setBusy(true);
																															sleep(1500);
																															owner.informOfNpcMessage(new ChatMessage(n, "Thanyou very much", owner));
																															sleep(2000);
																															owner.informOfNpcMessage(new ChatMessage(n, "As i say the best place to start should be with reldo", owner));
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
																											owner.getActionSender().sendMenu(options10);
																											break;
																									case 1:
																										owner.setBusy(true);
																										n.setBusy(true);
																										sleep(1500);
																										owner.informOfNpcMessage(new ChatMessage(n, "Yes me too", owner));
																										sleep(2000);
																										owner.informOfNpcMessage(new ChatMessage(n, "I'm not looking forward to telling vyvin i've lost it", owner));
																										sleep(2000);
																										owner.informOfNpcMessage(new ChatMessage(n, "He's going to want it for the parade next week as well", owner));
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
																						owner.getActionSender().sendMenu(options9);
																						break;
																					case 2:
																						owner.setBusy(true);
																						n.setBusy(true);
																						sleep(1500);
																						owner.informOfNpcMessage(new ChatMessage(n, "Yes you can get bronze swords anywhere", owner));
																						sleep(2000);
																						owner.informOfNpcMessage(new ChatMessage(n, "But this isn't any old sword", owner));
																						sleep(2000);
																						owner.informOfNpcMessage(new ChatMessage(n, "The thing is, this sword is a family heirloom", owner));
																						sleep(2000);
																						owner.informOfNpcMessage(new ChatMessage(n, "It has been passed down through vyvin's family for five generations", owner));
																						sleep(2000);
																						owner.informOfNpcMessage(new ChatMessage(n, "It was originally made by the incando dwarves", owner));
																						sleep(2000);
																						owner.informOfNpcMessage(new ChatMessage(n, "Who were a particularly skilled tribe of dwarven smiths", owner));
																						sleep(2000);
																						owner.informOfNpcMessage(new ChatMessage(n, "I doubt anyone could make it in the style they do", owner));
																						sleep(2000);
																						owner.setBusy(false);
																						n.setBusy(false);
																						String[] options11 = new String[]{ "So would these dwarves make another one?", "Well I hope you find it soon" };
																							owner.setMenuHandler(new MenuHandler(options11) {
																								public void handleReply(final int option, final String reply) {
																									switch(option) {
																										case 0:
																											owner.setBusy(true);
																											n.setBusy(true);
																											sleep(1500);
																											owner.informOfNpcMessage(new ChatMessage(n, "I'm not a hundred percent sure the incando tribe exists anymore", owner));
																											sleep(2000);
																											owner.informOfNpcMessage(new ChatMessage(n, "I should think reldo the palace librarian in varrock will know", owner));
																											sleep(2000);
																											owner.informOfNpcMessage(new ChatMessage(n, "He has done a lot of research on the races of these lands", owner));
																											sleep(2000);
																											owner.informOfNpcMessage(new ChatMessage(n, "I don't suppose you could try and track down the incando dwarves for me?", owner));
																											sleep(2000);
																											owner.informOfNpcMessage(new ChatMessage(n, "I've got so much work to do", owner));
																											sleep(2000);
																											owner.setBusy(false);
																											n.setBusy(false);
																											String[] options12 = new String[]{ "Ok I'll give it a go", "No I've got lots of mining work to do" };
																												owner.setMenuHandler(new MenuHandler(options12) {
																													public void handleReply(final int option, final String reply) {
																														switch(option) {
																															case 0:
																																owner.setBusy(true);
																																n.setBusy(true);
																																sleep(1500);
																																owner.informOfNpcMessage(new ChatMessage(n, "Thanyou very much", owner));
																																sleep(2000);
																																owner.informOfNpcMessage(new ChatMessage(n, "As i say the best place to start should be with reldo", owner));
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
																												owner.getActionSender().sendMenu(options12);
																												break;
																											case 1:
																												owner.setBusy(true);
																												n.setBusy(true);
																												sleep(1500);
																												owner.informOfNpcMessage(new ChatMessage(n, "Yes me too", owner));
																												sleep(2000);
																												owner.informOfNpcMessage(new ChatMessage(n, "I'm not looking forward to telling vyvin i've lost it", owner));
																												sleep(2000);
																												owner.informOfNpcMessage(new ChatMessage(n, "He's going to want it for the parade next week as well", owner));
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
																								owner.getActionSender().sendMenu(options11);
																								break;
																							case 3:
																								owner.setBusy(true);
																								n.setBusy(true);
																								sleep(1500);
																								owner.informOfNpcMessage(new ChatMessage(n, "Yes me too", owner));
																								sleep(2000);
																								owner.informOfNpcMessage(new ChatMessage(n, "I'm not looking forward to telling vyvin i've lost it", owner));
																								sleep(2000);
																								owner.informOfNpcMessage(new ChatMessage(n, "He's going to want it for the parade next week as well", owner));
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
														case 1: // no
															owner.setBusy(true);
															n.setBusy(true);
															sleep(1500);
															owner.informOfNpcMessage(new ChatMessage(n, "Thanks for the offer", owner));
															sleep(2000);
															owner.informOfNpcMessage(new ChatMessage(n, "I'd be surprised if you could though", owner));
															sleep(2000);
															owner.informOfNpcMessage(new ChatMessage(n, "The thing is, this sword is a family heirloom", owner));
															sleep(2000);
															owner.informOfNpcMessage(new ChatMessage(n, "It has been passed down through vyvin's family for five generations", owner));
															sleep(2000);
															owner.informOfNpcMessage(new ChatMessage(n, "It was originally made by the incando dwarves", owner));
															sleep(2000);
															owner.informOfNpcMessage(new ChatMessage(n, "Who were a particularly skilled tribe of dwarven smiths", owner));
															sleep(2000);
															owner.informOfNpcMessage(new ChatMessage(n, "I doubt anyone could make it in the style they do", owner));
															sleep(2000);
															owner.setBusy(false);
															n.setBusy(false);
															String[] options13 = new String[]{ "So would these dwarves make another one?", "Well I hope you find it soon" };
																owner.setMenuHandler(new MenuHandler(options13) {
																	public void handleReply(final int option, final String reply) {
																			switch(option) {
																				case 0:
																					owner.setBusy(true);
																					n.setBusy(true);
																					sleep(1500);
																					owner.informOfNpcMessage(new ChatMessage(n, "I'm not a hundred percent sure the incando tribe exists anymore", owner));
																					sleep(2000);
																					owner.informOfNpcMessage(new ChatMessage(n, "I should think reldo the palace librarian in varrock will know", owner));
																					sleep(2000);
																					owner.informOfNpcMessage(new ChatMessage(n, "He has done a lot of research on the races of these lands", owner));
																					sleep(2000);
																					owner.informOfNpcMessage(new ChatMessage(n, "I don't suppose you could try and track down the incando dwarves for me?", owner));
																					sleep(2000);
																					owner.informOfNpcMessage(new ChatMessage(n, "I've got so much work to do", owner));
																					sleep(2000);
																					owner.setBusy(false);
																					n.setBusy(false);
																					String[] options14 = new String[]{ "Ok I'll give it a go", "No I've got lots of mining work to do" };
																						owner.setMenuHandler(new MenuHandler(options14) {
																							public void handleReply(final int option, final String reply) {
																								switch(option) {
																									case 0:
																										owner.setBusy(true);
																										n.setBusy(true);
																										sleep(1500);
																										owner.informOfNpcMessage(new ChatMessage(n, "Thanyou very much", owner));
																										sleep(2000);
																										owner.informOfNpcMessage(new ChatMessage(n, "As i say the best place to start should be with reldo", owner));
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
																						owner.getActionSender().sendMenu(options14);
																						break;
																					case 1:
																						owner.setBusy(true);
																						n.setBusy(true);
																						sleep(1500);
																						owner.informOfNpcMessage(new ChatMessage(n, "Yes me too", owner));
																						sleep(2000);
																						owner.informOfNpcMessage(new ChatMessage(n, "I'm not looking forward to telling vyvin i've lost it", owner));
																						sleep(2000);
																						owner.informOfNpcMessage(new ChatMessage(n, "He's going to want it for the parade next week as well", owner));
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
																		owner.getActionSender().sendMenu(options13);
																		break;
														case 2:
															owner.setBusy(true);
															n.setBusy(true);
															sleep(1500);
															owner.informOfNpcMessage(new ChatMessage(n, "He dosen't know yet", owner));
															sleep(2000);
															owner.informOfNpcMessage(new ChatMessage(n, "I was hoping i could think of something to do", owner));
															sleep(2000);
															owner.setBusy(false);
															n.setBusy(false);
															String[] options15 = new String[] { "Well do you know the vague area you lost it?", "I can make a new sword if you like", "Well the kingdom is fairly abundant with swords", "Well I hope you find it soon" };
																owner.setMenuHandler(new MenuHandler(options15) {
																	public void handleReply(final int option, final String reply) {
																		switch (option) {
																			case 0:
																				owner.setBusy(true);
																				n.setBusy(true);
																				sleep(1500);
																				owner.informOfNpcMessage(new ChatMessage(n, "No i was carrying it for him all the way from where he had it stored in Varrock", owner));
																				sleep(2000);
																				owner.informOfNpcMessage(new ChatMessage(n, "It must have slipped from my pack during the trip", owner));
																				sleep(2000);
																				owner.informOfNpcMessage(new ChatMessage(n, "And you know what people are like these days", owner));
																				sleep(2000);
																				owner.informOfNpcMessage(new ChatMessage(n, "Someone will have just picked it up and kept it for themselves", owner));
																				sleep(2000);
																				n.setBusy(false);
																				owner.setBusy(false);
																				String[] options16 = new String[]{ "I can make a new sword if you like", "Well the kingdom is fairly abundant with swords", "Well I hope you find it soon" };
																					owner.setMenuHandler(new MenuHandler(options16) {
																						public void handleReply(final int option, final String reply) {
																							switch(option) {
																								case 0:
																									owner.setBusy(true);
																									n.setBusy(true);
																									sleep(1500);
																									owner.informOfNpcMessage(new ChatMessage(n, "Thanks for the offer", owner));
																									sleep(2000);
																									owner.informOfNpcMessage(new ChatMessage(n, "I'd be surprised if you could though", owner));
																									sleep(2000);
																									owner.informOfNpcMessage(new ChatMessage(n, "The thing is, this sword is a family heirloom", owner));
																									sleep(2000);
																									owner.informOfNpcMessage(new ChatMessage(n, "It has been passed down through vyvin's family for five generations", owner));
																									sleep(2000);
																									owner.informOfNpcMessage(new ChatMessage(n, "It was originally made by the incando dwarves", owner));
																									sleep(2000);
																									owner.informOfNpcMessage(new ChatMessage(n, "Who were a particularly skilled tribe of dwarven smiths", owner));
																									sleep(2000);
																									owner.informOfNpcMessage(new ChatMessage(n, "I doubt anyone could make it in the style they do", owner));
																									sleep(2000);
																									owner.setBusy(false);
																									n.setBusy(false);
																									String[] options17 = new String[]{ "So would these dwarves make another one?", "Well I hope you find it soon" };
																									owner.setMenuHandler(new MenuHandler(options17) {
																										public void handleReply(final int option, final String reply) {
																											switch(option) {
																												case 0:
																													owner.setBusy(true);
																													n.setBusy(true);
																													sleep(1500);
																													owner.informOfNpcMessage(new ChatMessage(n, "I'm not a hundred percent sure the incando tribe exists anymore", owner));
																													sleep(2000);
																													owner.informOfNpcMessage(new ChatMessage(n, "I should think reldo the palace librarian in varrock will know", owner));
																													sleep(2000);
																													owner.informOfNpcMessage(new ChatMessage(n, "He has done a lot of research on the races of these lands", owner));
																													sleep(2000);
																													owner.informOfNpcMessage(new ChatMessage(n, "I don't suppose you could try and track down the incando dwarves for me?", owner));
																													sleep(2000);
																													owner.informOfNpcMessage(new ChatMessage(n, "I've got so much work to do", owner));
																													sleep(2000);
																													owner.setBusy(false);
																													n.setBusy(false);
																													String[] options18 = new String[]{ "Ok I'll give it a go", "No I've got lots of mining work to do" };
																													owner.setMenuHandler(new MenuHandler(options18) {
																														public void handleReply(final int option, final String reply) {
																															switch(option) {
																																case 0:
																																	owner.setBusy(true);
																																	n.setBusy(true);
																																	sleep(1500);
																																	owner.informOfNpcMessage(new ChatMessage(n, "Thanyou very much", owner));
																																	sleep(2000);
																																	owner.informOfNpcMessage(new ChatMessage(n, "As i say the best place to start should be with reldo", owner));
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
																													owner.getActionSender().sendMenu(options18);
																													break;
																												case 1:
																													owner.setBusy(true);
																													n.setBusy(true);
																													sleep(1500);
																													owner.informOfNpcMessage(new ChatMessage(n, "Yes me too", owner));
																													sleep(2000);
																													owner.informOfNpcMessage(new ChatMessage(n, "I'm not looking forward to telling vyvin i've lost it", owner));
																													sleep(2000);
																													owner.informOfNpcMessage(new ChatMessage(n, "He's going to want it for the parade next week as well", owner));
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
																									owner.getActionSender().sendMenu(options17);
																									break;
																								case 1:
																									owner.setBusy(true);
																									n.setBusy(true);
																									sleep(1500);
																									owner.informOfNpcMessage(new ChatMessage(n, "Yes you can get bronze swords anywhere", owner));
																									sleep(2000);
																									owner.informOfNpcMessage(new ChatMessage(n, "But this isn't any old sword", owner));
																									sleep(2000);
																									owner.informOfNpcMessage(new ChatMessage(n, "The thing is, this sword is a family heirloom", owner));
																									sleep(2000);
																									owner.informOfNpcMessage(new ChatMessage(n, "It has been passed down through vyvin's family for five generations", owner));
																									sleep(2000);
																									owner.informOfNpcMessage(new ChatMessage(n, "It was originally made by the incando dwarves", owner));
																									sleep(2000);
																									owner.informOfNpcMessage(new ChatMessage(n, "Who were a particularly skilled tribe of dwarven smiths", owner));
																									sleep(2000);
																									owner.informOfNpcMessage(new ChatMessage(n, "I doubt anyone could make it in the style they do", owner));
																									sleep(2000);
																									owner.setBusy(false);
																									n.setBusy(false);
																									String[] options19 = new String[]{ "So would these dwarves make another one?", "Well I hope you find it soon" };
																									owner.setMenuHandler(new MenuHandler(options19) {
																										public void handleReply(final int option, final String reply) {
																											switch(option) {
																												case 0:
																													owner.setBusy(true);
																													n.setBusy(true);
																													sleep(1500);
																													owner.informOfNpcMessage(new ChatMessage(n, "I'm not a hundred percent sure the incando tribe exists anymore", owner));
																													sleep(2000);
																													owner.informOfNpcMessage(new ChatMessage(n, "I should think reldo the palace librarian in varrock will know", owner));
																													sleep(2000);
																													owner.informOfNpcMessage(new ChatMessage(n, "He has done a lot of research on the races of these lands", owner));
																													sleep(2000);
																													owner.informOfNpcMessage(new ChatMessage(n, "I don't suppose you could try and track down the incando dwarves for me?", owner));
																													sleep(2000);
																													owner.informOfNpcMessage(new ChatMessage(n, "I've got so much work to do", owner));
																													sleep(2000);
																													owner.setBusy(false);
																													n.setBusy(false);
																													String[] options20 = new String[]{ "Ok I'll give it a go", "No I've got lots of mining work to do" };
																													owner.setMenuHandler(new MenuHandler(options20) {
																														public void handleReply(final int option, final String reply) {
																															switch(option) {
																																case 0:
																																	owner.setBusy(true);
																																	n.setBusy(true);
																																	sleep(1500);
																																	owner.informOfNpcMessage(new ChatMessage(n, "Thanyou very much", owner));
																																	sleep(2000);
																																	owner.informOfNpcMessage(new ChatMessage(n, "As i say the best place to start should be with reldo", owner));
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
																													owner.getActionSender().sendMenu(options20);
																													break;
																												case 1:
																													owner.setBusy(true);
																													n.setBusy(true);
																													sleep(1500);
																													owner.informOfNpcMessage(new ChatMessage(n, "Yes me too", owner));
																													sleep(2000);
																													owner.informOfNpcMessage(new ChatMessage(n, "I'm not looking forward to telling vyvin i've lost it", owner));
																													sleep(2000);
																													owner.informOfNpcMessage(new ChatMessage(n, "He's going to want it for the parade next week as well", owner));
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
																									owner.getActionSender().sendMenu(options19);
																									break;
																								case 2:
																									owner.setBusy(true);
																									n.setBusy(true);
																									sleep(1500);
																									owner.informOfNpcMessage(new ChatMessage(n, "Yes me too", owner));
																									sleep(2000);
																									owner.informOfNpcMessage(new ChatMessage(n, "I'm not looking forward to telling vyvin i've lost it", owner));
																									sleep(2000);
																									owner.informOfNpcMessage(new ChatMessage(n, "He's going to want it for the parade next week as well", owner));
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
																					owner.getActionSender().sendMenu(options16);
																					break;
																				case 1:
																					owner.setBusy(true);
																					n.setBusy(true);
																					sleep(1500);
																					owner.informOfNpcMessage(new ChatMessage(n, "Thanks for the offer", owner));
																					sleep(2000);
																					owner.informOfNpcMessage(new ChatMessage(n, "I'd be surprised if you could though", owner));
																					sleep(2000);
																					owner.informOfNpcMessage(new ChatMessage(n, "The thing is, this sword is a family heirloom", owner));
																					sleep(2000);
																					owner.informOfNpcMessage(new ChatMessage(n, "It has been passed down through vyvin's family for five generations", owner));
																					sleep(2000);
																					owner.informOfNpcMessage(new ChatMessage(n, "It was originally made by the incando dwarves", owner));
																					sleep(2000);
																					owner.informOfNpcMessage(new ChatMessage(n, "Who were a particularly skilled tribe of dwarven smiths", owner));
																					sleep(2000);
																					owner.informOfNpcMessage(new ChatMessage(n, "I doubt anyone could make it in the style they do", owner));
																					sleep(2000);
																					owner.setBusy(false);
																					n.setBusy(false);
																					String[] options21 = new String[]{ "So would these dwarves make another one?", "Well I hope you find it soon" };
																						owner.setMenuHandler(new MenuHandler(options21) {
																							public void handleReply(final int option, final String reply) {
																								switch(option) {
																									case 0:
																										owner.setBusy(true);
																										n.setBusy(true);
																										sleep(1500);
																										owner.informOfNpcMessage(new ChatMessage(n, "I'm not a hundred percent sure the incando tribe exists anymore", owner));
																										sleep(2000);
																										owner.informOfNpcMessage(new ChatMessage(n, "I should think reldo the palace librarian in varrock will know", owner));
																										sleep(2000);
																										owner.informOfNpcMessage(new ChatMessage(n, "He has done a lot of research on the races of these lands", owner));
																										sleep(2000);
																										owner.informOfNpcMessage(new ChatMessage(n, "I don't suppose you could try and track down the incando dwarves for me?", owner));
																										sleep(2000);
																										owner.informOfNpcMessage(new ChatMessage(n, "I've got so much work to do", owner));
																										sleep(2000);
																										owner.setBusy(false);
																										n.setBusy(false);
																										String[] options22 = new String[]{ "Ok I'll give it a go", "No I've got lots of mining work to do" };
																											owner.setMenuHandler(new MenuHandler(options22) {
																												public void handleReply(final int option, final String reply) {
																													switch(option) {
																														case 0:
																															owner.setBusy(true);
																															n.setBusy(true);
																															sleep(1500);
																															owner.informOfNpcMessage(new ChatMessage(n, "Thanyou very much", owner));
																															sleep(2000);
																															owner.informOfNpcMessage(new ChatMessage(n, "As i say the best place to start should be with reldo", owner));
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
																											owner.getActionSender().sendMenu(options22);
																											break;
																									case 1:
																										owner.setBusy(true);
																										n.setBusy(true);
																										sleep(1500);
																										owner.informOfNpcMessage(new ChatMessage(n, "Yes me too", owner));
																										sleep(2000);
																										owner.informOfNpcMessage(new ChatMessage(n, "I'm not looking forward to telling vyvin i've lost it", owner));
																										sleep(2000);
																										owner.informOfNpcMessage(new ChatMessage(n, "He's going to want it for the parade next week as well", owner));
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
																						owner.getActionSender().sendMenu(options21);
																						break;
																					case 2:
																						owner.setBusy(true);
																						n.setBusy(true);
																						sleep(1500);
																						owner.informOfNpcMessage(new ChatMessage(n, "Yes you can get bronze swords anywhere", owner));
																						sleep(2000);
																						owner.informOfNpcMessage(new ChatMessage(n, "But this isn't any old sword", owner));
																						sleep(2000);
																						owner.informOfNpcMessage(new ChatMessage(n, "The thing is, this sword is a family heirloom", owner));
																						sleep(2000);
																						owner.informOfNpcMessage(new ChatMessage(n, "It has been passed down through vyvin's family for five generations", owner));
																						sleep(2000);
																						owner.informOfNpcMessage(new ChatMessage(n, "It was originally made by the incando dwarves", owner));
																						sleep(2000);
																						owner.informOfNpcMessage(new ChatMessage(n, "Who were a particularly skilled tribe of dwarven smiths", owner));
																						sleep(2000);
																						owner.informOfNpcMessage(new ChatMessage(n, "I doubt anyone could make it in the style they do", owner));
																						sleep(2000);
																						owner.setBusy(false);
																						n.setBusy(false);
																						String[] options23 = new String[]{ "So would these dwarves make another one?", "Well I hope you find it soon" };
																							owner.setMenuHandler(new MenuHandler(options23) {
																								public void handleReply(final int option, final String reply) {
																									switch(option) {
																										case 0:
																											owner.setBusy(true);
																											n.setBusy(true);
																											sleep(1500);
																											owner.informOfNpcMessage(new ChatMessage(n, "I'm not a hundred percent sure the incando tribe exists anymore", owner));
																											sleep(2000);
																											owner.informOfNpcMessage(new ChatMessage(n, "I should think reldo the palace librarian in varrock will know", owner));
																											sleep(2000);
																											owner.informOfNpcMessage(new ChatMessage(n, "He has done a lot of research on the races of these lands", owner));
																											sleep(2000);
																											owner.informOfNpcMessage(new ChatMessage(n, "I don't suppose you could try and track down the incando dwarves for me?", owner));
																											sleep(2000);
																											owner.informOfNpcMessage(new ChatMessage(n, "I've got so much work to do", owner));
																											sleep(2000);
																											owner.setBusy(false);
																											n.setBusy(false);
																											String[] options24 = new String[]{ "Ok I'll give it a go", "No I've got lots of mining work to do" };
																												owner.setMenuHandler(new MenuHandler(options24) {
																													public void handleReply(final int option, final String reply) {
																														switch(option) {
																															case 0:
																																owner.setBusy(true);
																																n.setBusy(true);
																																sleep(1500);
																																owner.informOfNpcMessage(new ChatMessage(n, "Thanyou very much", owner));
																																sleep(2000);
																																owner.informOfNpcMessage(new ChatMessage(n, "As i say the best place to start should be with reldo", owner));
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
																												owner.getActionSender().sendMenu(options24);
																												break;
																											case 1:
																												owner.setBusy(true);
																												n.setBusy(true);
																												sleep(1500);
																												owner.informOfNpcMessage(new ChatMessage(n, "Yes me too", owner));
																												sleep(2000);
																												owner.informOfNpcMessage(new ChatMessage(n, "I'm not looking forward to telling vyvin i've lost it", owner));
																												sleep(2000);
																												owner.informOfNpcMessage(new ChatMessage(n, "He's going to want it for the parade next week as well", owner));
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
																								owner.getActionSender().sendMenu(options23);
																								break;
																							case 3:
																								owner.setBusy(true);
																								n.setBusy(true);
																								sleep(1500);
																								owner.informOfNpcMessage(new ChatMessage(n, "Yes me too", owner));
																								sleep(2000);
																								owner.informOfNpcMessage(new ChatMessage(n, "I'm not looking forward to telling vyvin i've lost it", owner));
																								sleep(2000);
																								owner.informOfNpcMessage(new ChatMessage(n, "He's going to want it for the parade next week as well", owner));
																								sleep(2000);
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
																owner.getActionSender().sendMenu(options15);
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
				else if(p.getQuestStage(this) > 1 && p.getQuestStage(this) < 4) {
					n.setBusy(true);
					p.setBusy(true);
					p.informOfNpcMessage(new ChatMessage(n, "So how are you doing getting a sword", p));
					sleep(2000);
					p.informOfChatMessage(new ChatMessage(p, "I'm still looking for incando dwarves", n));
					p.setBusy(false);
					n.setBusy(false);
				} 
				else if(p.getQuestStage(this) == 4) {
					p.setBusy(true);
					n.setBusy(true);
					p.informOfNpcMessage(new ChatMessage(n, "So how are you doing getting a sword", p));
					sleep(2000);
					p.informOfChatMessage(new ChatMessage(p, "I've found an incando dwarf", n));
					sleep(2000);
					p.informOfChatMessage(new ChatMessage(p, "But he needs a picture of the sword before he can make it", n));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "A picture eh?", p));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "The only one i can think of is in a small portrait of sir vyvin's father", p));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "He's holding the sword in it", p));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "Sir vyvin keeps it in a cupboard in his room i think", p));
					p.setQuestStage(getQuestId(), 5);
					sleep(2000);		
					p.setBusy(false);
					n.setBusy(false);								
				}
				else if(p.getQuestStage(this) == 5 || p.getQuestStage(this) == 6) {
					if(p.getInventory().hasItemId(265)) {
						p.setBusy(true);
						n.setBusy(true);
						p.informOfChatMessage(new ChatMessage(p, "I have retrieved your sword for you", n));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "Thankyou, thankyou", p));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "I was seriously worried i'd have to own up to sir vyvin", p));
						sleep(2000);
						p.getActionSender().sendMessage("You give the sword to the squire");
						p.getInventory().remove(265, 1);
						sleep(2000);
						p.setQuestStage(getQuestId(), -1);
						p.incExp(13, 4000, false, false, false);
						p.setBusy(false);
						n.setBusy(false);
					} 
					else if(p.getInventory().hasItemId(264)) {
						p.setBusy(true);
						n.setBusy(true);
						p.informOfNpcMessage(new ChatMessage(n, "So how are you doing getting a sword?", p));
						sleep(2000);
						p.informOfChatMessage(new ChatMessage(p, "I haven't got it from Thurgo yet", n));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "Please let me know when you do", p));
						sleep(2000);
						p.setBusy(false);
						n.setBusy(false);
					}
					else {
						p.setBusy(true);
						n.setBusy(true);
						p.informOfNpcMessage(new ChatMessage(n, "So how are you doing getting a sword", p));
						sleep(2000);
						p.informOfChatMessage(new ChatMessage(p, "I've found an incando dwarf", n));
						sleep(2000);
						p.informOfChatMessage(new ChatMessage(p, "But he needs a picture of the sword before he can make it", n));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "A picture eh?", p));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "The only one i can think of is a small portrait of sir vyvin's father", p));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "Sir vyvin keeps it in a cupboard in his room i think", p));
						p.setBusy(false);
						n.setBusy(false);
					}				
				} 
				else if(p.getQuestStage(this) == -1) {
					p.setBusy(true);
					n.setBusy(true);
					p.informOfNpcMessage(new ChatMessage(n, "Hello friend", p));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "Thanks for your help before", p));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "Vyvin never even realised it was a different", p));
					sleep(2000);
					p.setBusy(false);
					n.setBusy(false);
				}
		} 
		else if(n.getID() == 20) {
				if(p.getQuestStage(this) == 1) {
					p.setBusy(true);
					n.setBusy(true);
					p.informOfChatMessage(new ChatMessage(p, "Hello", n));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "Hello stranger", p));
					sleep(2000);
					p.setBusy(false);
					n.setBusy(false);
					String[] options25 = new String[]{ "I'm in search of a quest", "Do you have anything to trade?", "What do you do?", "What do you know about the incando dwarves" };
						p.setMenuHandler(new MenuHandler(options25) {
							public void handleReply(final int option, final String reply) {
								switch(option) {
									case 0:
										owner.setBusy(true);
										n.setBusy(true);
										sleep(1500);
										owner.informOfNpcMessage(new ChatMessage(n, "I don't think theres any I could give you at the moment", owner));
										owner.setBusy(false);
										n.setBusy(false);
										break;
									case 1:
										owner.setBusy(true);
										n.setBusy(true);
										sleep(1500);
										owner.informOfNpcMessage(new ChatMessage(n, "No, sorry. I'm not the trading type", owner));
										sleep(2000);
										owner.informOfChatMessage(new ChatMessage(owner, "Ah well", n));
										owner.setBusy(false);
										n.setBusy(false);
										break;
									case 2:
										owner.setBusy(true);
										n.setBusy(true);
										sleep(1500);
										owner.informOfNpcMessage(new ChatMessage(n, "I'm the palace librarian", owner));
										sleep(2000);
										owner.informOfChatMessage(new ChatMessage(owner, "Ah that's why you're in the library then", n));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "Yes", owner));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "Though i might be in here even if i didn't work here", owner));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "I like reading", owner));
										owner.setBusy(false);
										n.setBusy(false);
										break;
									case 3:
										owner.setBusy(true);
										n.setBusy(true);
										sleep(1500);
										owner.informOfNpcMessage(new ChatMessage(n, "The incando dwarves, you say?", owner));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "They were the world's most skilled smiths about a hundred years ago", owner));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "They used secret knowledge", owner));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "Which they passed down from generation to generation", owner));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "Unfortunatly about a century ago the once thriving race", owner));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "Was wiped our during the barbarian invasions of that time", owner));
										sleep(2000);
										owner.informOfChatMessage(new ChatMessage(owner, "So are there any incando left at all?", n));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "A few of them survived", owner));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "But with the bulk of their population destroyed", owner));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "Their numbers have dwindled even further", owner));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "Last i knew there were a couple living in asgarnia", owner));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "Near the cliffs on the asgarnian southern peninsula", owner));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "They tend to keep to themselves", owner));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "They don't tend to tell people that they're the descendants of the incando", owner));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "Which is why people think that the tribe has died out totally", owner));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "You may have more luck talking to them if you bring them some red berry pie", owner));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "They really like red berry pie", owner));
										sleep(2000);
										owner.informOfChatMessage(new ChatMessage(owner, "Thank you", n));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "You're welcome", owner));
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
						p.getActionSender().sendMenu(options25);			
				} 
				else {
					p.setBusy(true);
					n.setBusy(true);
					p.informOfNpcMessage(new ChatMessage(n, "I'm busy, leave me alone", p));
					p.setBusy(false);
					n.setBusy(false);
				}				
		} 
		else if(n.getID() == 138) {
				if(p.getQuestStage(this) >= -1) {
					p.setBusy(true);
					n.setBusy(true);
					p.informOfChatMessage(new ChatMessage(p, "Hello", n));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "Greetings traveller", p));
					sleep(2000);
					p.setBusy(false);
					n.setBusy(false);
					String[] options26 = new String[]{ "Do you have anything to trade", "Why are there so many knights in this city" };
						p.setMenuHandler(new MenuHandler(options26) {
							public void handleReply(final int option, final String reply) {
								switch(option) {
									case 0:
										owner.setBusy(true);
										n.setBusy(true);
										sleep(1500);
										owner.informOfNpcMessage(new ChatMessage(n, "No I'm sorry", owner));
										owner.setBusy(false);
										n.setBusy(false);
										break;
									case 1:
										owner.setBusy(true);
										n.setBusy(true);
										sleep(1500);
										owner.informOfNpcMessage(new ChatMessage(n, "We are the white knights of falador", owner));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "We are the most powerfull orfer of knights in the land", owner));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "We are helping the king vallance rule the kingdom", owner));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "AS he is getting old and tired", owner));
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
						p.getActionSender().sendMenu(options26);			
				}
		}
		else if(n.getID() == 134) {
				if(p.getQuestStage(this) == 2) {
					p.setBusy(false);
					n.setBusy(false);
					sleep(2000);
					String[] options27 = new String[]{ "Hello are you an incando Dwarf", "Would you like some redberry pie?" };
						p.setMenuHandler(new MenuHandler(options27) {
							public void handleReply(final int option, final String reply) {
								switch(option) {
									case 0:
										owner.setBusy(true);
										n.setBusy(true);
										sleep(1500);
										owner.informOfNpcMessage(new ChatMessage(n, "Yeah what about it?", owner));
										sleep(2000);
										owner.setBusy(false);
										n.setBusy(false);
										String[] options28 = new String[]{ "Would you like some reberry pie?", "Can you make me a special sword" };
										owner.setMenuHandler(new MenuHandler(options28) {
											public void handleReply(final int option, final String reply) {
												switch(option) {
													case 0:
														owner.setBusy(true);
														n.setBusy(true);
														sleep(1500);
														owner.getActionSender().sendMessage("Thurgo's eyes light up");
														sleep(2000);
														owner.informOfNpcMessage(new ChatMessage(n, "I'd never say no to a redberry pie", owner));
														if(owner.getInventory().hasItemId(258)) {
															owner.getInventory().remove(258, 1);
															owner.getActionSender().sendInventory();
															owner.getActionSender().sendMessage("You hand over the pie");
															owner.setQuestStage(getQuestId(), 3);
															sleep(2000);
															owner.informOfNpcMessage(new ChatMessage(n, "It's great stuff", owner));
															sleep(2000);
															owner.getActionSender().sendMessage("Thurgo eats the pie");
															sleep(2000);
															owner.getActionSender().sendMessage("He pats his stomach");
															sleep(2000);
															owner.informOfNpcMessage(new ChatMessage(n, "By guthix that was good pie", owner));
															sleep(2000);
															owner.informOfNpcMessage(new ChatMessage(n, "Anyone who makes pie liek that has gotta be alright", owner));
															owner.setBusy(false);
															n.setBusy(false);
														} else {
															owner.informOfChatMessage(new ChatMessage(owner, "Well that's too bad, because I don't have any", n));
															sleep(2000);
															owner.getActionSender().sendMessage("Thurgo does not look impressed");
															owner.setBusy(false);
															n.setBusy(false);
														}
														break;
													case 1:
														owner.setBusy(true);
														n.setBusy(true);
														sleep(1500);
														owner.informOfNpcMessage(new ChatMessage(n, "No i don't do that anymore", owner));
														sleep(2000);
														owner.informOfNpcMessage(new ChatMessage(n, "I'm getting old", owner));
														owner.setBusy(false);
														n.setBusy(false);
													default:
														owner.setBusy(false);
														n.setBusy(false);
														return;											
												}
											}
										});
										owner.getActionSender().sendMenu(options28);			
										break;
									case 1:
										owner.setBusy(true);
										n.setBusy(true);
										sleep(1500);
										owner.getActionSender().sendMessage("Thurgo's eyes light up");
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "I'd never say no to a redberry pie", owner));
										if(owner.getInventory().hasItemId(258)) {
											owner.getInventory().remove(258, 1);
											owner.getActionSender().sendInventory();
											owner.getActionSender().sendMessage("You hand over the pie");
											owner.setQuestStage(getQuestId(), 3);
											sleep(2000);
											owner.informOfNpcMessage(new ChatMessage(n, "It's great stuff", owner));
											sleep(2000);
											owner.getActionSender().sendMessage("Thurgo eats the pie");
											sleep(2000);
											owner.getActionSender().sendMessage("He pats his stomach");
											sleep(2000);
											owner.informOfNpcMessage(new ChatMessage(n, "By guthic that was good pie", owner));
											sleep(2000);
											owner.informOfNpcMessage(new ChatMessage(n, "Anyone who makes pie liek that has gotta be alright", owner));
											owner.setBusy(false);
											n.setBusy(false);
										} else {
											owner.informOfChatMessage(new ChatMessage(owner, "Well that's too bad, because I don't have any", n));
											sleep(2000);
											owner.getActionSender().sendMessage("Thurgo does not look impressed");
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
						p.getActionSender().sendMenu(options27);				
				}
				else if(p.getQuestStage(this) == 3) {
					p.setBusy(true);
					n.setBusy(true);
					p.informOfChatMessage(new ChatMessage(p, "Can you make me a special sword", n));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "Well after you've brought me such a great pie", p));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "I guess i should give it a go", p));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "What sort of sword is it?", p));
					sleep(2000);
					p.informOfChatMessage(new ChatMessage(p, "I need you to make a sword for one of falador's knights", n));
					sleep(2000);
					p.informOfChatMessage(new ChatMessage(p, "He had one which was passed down through five generations", n));
					sleep(2000);
					p.informOfChatMessage(new ChatMessage(p, "But his squire has lost it", n));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "A knight's sword eh?", p));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "Well i'd need to know exactly how it looked", p));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "Before i could make a new one", p));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "All the faladian knights used to have swords with different designs", p));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "Could you bring me a picture or something?", p));
					sleep(2000);
					p.informOfChatMessage(new ChatMessage(p, "I'll see if i can find one", n));
					sleep(2000);
					p.informOfChatMessage(new ChatMessage(p, "I'll go and ask his squire", n));
					sleep(2000);
					p.setQuestStage(getQuestId(), 4);
					p.setBusy(false);
					n.setBusy(false);
				}
				else if(p.getQuestStage(this) == 4 || p.getQuestStage(this) == 5) {
					if(p.getInventory().hasItemId(264)) {
						p.setBusy(true);
						n.setBusy(true);
						sleep(2000);
						p.informOfChatMessage(new ChatMessage(p, "I have found a picture of the sword i would like you to make", n));
						sleep(2000);
						p.getActionSender().sendMessage("You have Thurgo the portrait");
						sleep(2000);
						p.getInventory().remove(264, 1);
						p.getActionSender().sendInventory();
						p.getActionSender().sendMessage("Thurgo examines the picture for a moment");
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "Ok you'll need to get me some stuff for me to make this", p));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "I'll need two iron bars to make the sword to start with", p));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "I'll also need an ore called blurite", p));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "It's useless for making actual weapons for fighting with", p));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "But i'll need some as decoration for the hilt", p));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "It is a fairly rare sort of ore", p));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "The only place i know where to get it", p));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "Is under this cliff here", p));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "But it is guarded by a very powerful ice giant", p));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "Most the rocks in that cliff are pretty useless", p));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "Don't contain much of anything", p));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "But there's definitly some blurite in there", p));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "You'll need a little bit of mining experience", p));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "To be able to find it", p));
						sleep(2000);
						p.informOfChatMessage(new ChatMessage(p, "Ok i'll go and find them", n));
						p.setQuestStage(getQuestId(), 6);
						p.setBusy(false);
						n.setBusy(false);					
					} else {
						p.setBusy(true);
						n.setBusy(true);
						p.informOfNpcMessage(new ChatMessage(n, "Have you got a picture of the sword for me yet?", p));
						sleep(2000);
						p.informOfChatMessage(new ChatMessage(p, "No sorry, not yet", n));
						sleep(2000);
						p.setBusy(false);
						n.setBusy(false);
					}
				} else if(p.getQuestStage(this) == 6) {
					p.setBusy(true);
					n.setBusy(true);
					p.informOfNpcMessage(new ChatMessage(n, "How are you doing finding sword materials?", p));
					sleep(2000);
					if(p.getInventory().countId(170) >= 2 && p.getInventory().countId(266) >= 1) {
						p.informOfNpcMessage(new ChatMessage(n, "I have them all", p));
						sleep(2000);
						p.getActionSender().sendMessage("You hand Thurgo the items");
						p.getInventory().remove(170, 2);
						p.getInventory().remove(266, 1);
						p.getActionSender().sendInventory();
						sleep(2000);
						p.getActionSender().sendMessage("Thurgo hammers the materials into a metal sword");
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "Here you go", p));
						p.getInventory().add(new InvItem(265, 1));
						p.getActionSender().sendInventory();
						sleep(2000);
						p.informOfChatMessage(new ChatMessage(p, "Thank you very much", n));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "Just remember to call in with more pie some time", p));
						p.setBusy(false);
						n.setBusy(false);					
					} else {
						p.informOfChatMessage(new ChatMessage(n, "How are you doing finding sword materials?", p));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(p, "I haven't found everything yet", n));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "Well come back when you do", p));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "Remember i need blurite ore and two iron bars", p));
						p.setBusy(false);
						n.setBusy(false);
					}
				} else if(p.getQuestStage(this) == -1) {
					p.setBusy(true);
					n.setBusy(true);
					p.informOfChatMessage(new ChatMessage(p, "Thanks for your help getting the sword for me", n));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "No worries mate", p));
					sleep(2000);
					p.setBusy(false);
					n.setBusy(false);
				} else {
					p.setBusy(true);
					n.setBusy(true);
					p.informOfNpcMessage(new ChatMessage(n, "Go away", p));
					sleep(2000);
					p.setBusy(false);
					n.setBusy(false);
				}
		}
	}
	
	@Override
	public void onObjectAction(GameObject obj, String command, Player player) {
		final Npc vyvin = World.getWorld().getNpc(138, 316, 320, 2454, 2459);
			if (command.equals("search") && obj.getID() == 175 && obj.getX() == 318 && obj.getY() == 2454 && player.getQuestStage(this) == 5 && vyvin.isBusy()) {
				player.setBusy(true);
				player.getActionSender().sendMessage("You search through the cupboard");
				sleep(2000);
				player.getActionSender().sendMessage("You find a small portrait in there which you take");
				sleep(2000);
				player.getInventory().add(new InvItem(264, 1));
				player.getActionSender().sendInventory();
				player.setBusy(false);
				return;
			} 
			else if (command.equals("search") && obj.getID() == 175 && obj.getX() == 318 && obj.getY() == 2454 && player.getQuestStage(this) == 5 && !vyvin.isBusy()) {
				player.setBusy(true);
				player.setSprite(4);
                vyvin.setSprite(0);
				player.informOfNpcMessage(new ChatMessage(vyvin, "Hey what are you doing?", player));
				sleep(2000);
				player.informOfNpcMessage(new ChatMessage(vyvin, "That's my cupboard", player));
				sleep(2000);
				player.getActionSender().sendMessage("Maybe you need someone to distract Sir Vivyn for you");
				player.setBusy(false);
				return;
			} 
			else if (command.equals("search") && obj.getID() == 175 && obj.getX() == 318 && obj.getY() == 2454 && player.getQuestStage(this) != 5 && vyvin.isBusy()){
				player.getActionSender().sendMessage("You search through the cupboard");
				sleep(2000);
				player.getActionSender().sendMessage("The cupboard is full of junk");
				sleep(2000);
				return;
			}
			else if (command.equals("search") && obj.getID() == 175 && obj.getX() == 318 && obj.getY() == 2454 && player.getQuestStage(this) != 5 && !vyvin.isBusy()) {
				player.setBusy(true);
				player.setSprite(4);
                vyvin.setSprite(0);
				player.informOfNpcMessage(new ChatMessage(vyvin, "Hey what are you doing?", player));
				sleep(2000);
				player.informOfNpcMessage(new ChatMessage(vyvin, "That's my cupboard", player));
				sleep(2000);
				player.getActionSender().sendMessage("Maybe you need someone to distract Sir Vivyn for you");
				player.setBusy(false);
				return;
			} 
	}

	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		if(n.getID() == 132 || n.getID() == 20 || n.getID() == 138 || n.getID() == 134) {
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
