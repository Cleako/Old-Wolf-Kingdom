package org.lupus_regnum.gs.plugins.quests;

import org.lupus_regnum.gs.model.ChatMessage;
import org.lupus_regnum.gs.model.GameObject;
import org.lupus_regnum.gs.model.InvItem;
import org.lupus_regnum.gs.model.MenuHandler;
import org.lupus_regnum.gs.model.Npc;
import org.lupus_regnum.gs.model.Player;
import org.lupus_regnum.gs.plugins.Quest;
import org.lupus_regnum.gs.plugins.listeners.action.InvUseOnObjectListener;
import org.lupus_regnum.gs.plugins.listeners.action.ObjectActionListener;
import org.lupus_regnum.gs.plugins.listeners.action.TalkToNpcListener;
import org.lupus_regnum.gs.plugins.listeners.executive.InvUseOnObjectExecutiveListener;
import org.lupus_regnum.gs.plugins.listeners.executive.ObjectActionExecutiveListener;
import org.lupus_regnum.gs.plugins.listeners.executive.TalkToNpcExecutiveListener;

public class TheRestlessGhost extends Quest implements 
    TalkToNpcListener, 
    TalkToNpcExecutiveListener, 
    ObjectActionListener, 
    InvUseOnObjectListener, 
    InvUseOnObjectExecutiveListener, 
    ObjectActionExecutiveListener {

    @Override
    public int getQuestId() {
        return 7;
    }

    @Override
    public String getQuestName() {
	return "The Restless Ghost";
    }
	
    @Override
    public void onTalkToNpc(Player p, final Npc n) {
        if(n.getID() == 9) {			
            if(p.getQuestStage(this) == 0) {
                n.setBusy(true);
                p.setBusy(true);
                System.out.println("Quest stage: " + 0);
                p.informOfNpcMessage(new ChatMessage(n, "Welcome to the church of holy Saradomin", p));
                sleep(2000);
                n.setBusy(false);
                p.setBusy(false);
		String[] options = new String[] { "Who's Saradomin?", "Nice place you've got here", "I'm looking for a quest" };
		p.setMenuHandler(new MenuHandler(options) {
		public void handleReply(final int option, final String reply) {
                    switch (option) {
		       	case 0: // Yes
                            n.setBusy(true);
                            owner.setBusy(true);
                            sleep(1500);
                            owner.informOfNpcMessage(new ChatMessage(n, "Surely you have heard of the God, Saradomin", owner));
                            sleep(2000);
                            owner.informOfNpcMessage(new ChatMessage(n, "He who creates the forces of goodness and purity in this world?", owner));
                            sleep(2000);
                            owner.informOfNpcMessage(new ChatMessage(n, "I cannot belive your ignorance!", owner));
                            sleep(2000);
                            owner.informOfNpcMessage(new ChatMessage(n, "This is the God with more followers than any other!", owner));
                            sleep(2000);
                            owner.informOfNpcMessage(new ChatMessage(n, "At least in theses parts", owner));
                            sleep(2000);
                            owner.informOfNpcMessage(new ChatMessage(n, "He who along with his brothers Guthix and Zamorak created this world", owner));
                            sleep(2000);
                            owner.setBusy(false);
                            n.setBusy(false);
                            String[] options2 = new String[] { "Oh that Saradomin", "Oh sorry I'm not from this world" };
                            owner.setMenuHandler(new MenuHandler(options2) {
				public void handleReply(final int option, final String reply) {
                                    switch (option) {
                                    case 0: //yes
                                    	sleep(1500);
					owner.informOfNpcMessage(new ChatMessage(n, "There is only one Saradomin", owner));
                                        n.setBusy(false);
					owner.setBusy(false);
					break;
                                    case 1: // No
					owner.setBusy(true);
					n.setBusy(true);
					sleep(1500);
                                        owner.informOfNpcMessage(new ChatMessage(n, "That's strange", owner));
					sleep(2000);
					owner.informOfNpcMessage(new ChatMessage(n, "I tought things not from this world were all slime and tenticles", owner));
                                        sleep(2000);
					owner.setBusy(false);
					n.setBusy(false);
					String[] options3 = new String[] { "You don't understand. This is Sparta!!", "I am - do you like my disguise?" };
					owner.setMenuHandler(new MenuHandler(options3) {
                                            public void handleReply(final int option, final String reply) {
						switch (option) {
                                                    case 0: //yes
							owner.setBusy(true);
							n.setBusy(true);
							sleep(1500);
							owner.informOfNpcMessage(new ChatMessage(n, "I beg your pardon?", owner));
                                                        sleep(2000);
							owner.informOfChatMessage(new ChatMessage(owner, "Never Mind", n));
							n.setBusy(false);
							owner.setBusy(false);
							break;
                                                    case 1: // No
                                                        owner.setBusy(true);
							n.setBusy(true);
							sleep(1500);
							owner.informOfNpcMessage(new ChatMessage(n, "I am not sure what you are playing at", owner));
							sleep(2000);
							owner.informOfNpcMessage(new ChatMessage(n, "But I advise you to stop it", owner));
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
					owner.getActionSender().sendMenu(options3);
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
                            owner.setBusy(true);
                            n.setBusy(true);
                            sleep(1500);
                            owner.informOfNpcMessage(new ChatMessage(n, "It is, isn't it?", owner));
                            sleep(2000);
                            owner.informOfNpcMessage(new ChatMessage(n, "It was built 230 years ago", owner));
                            owner.setBusy(false);
                            n.setBusy(false);
                            break;
			case 2:
                            owner.setBusy(true);
                            n.setBusy(true);
                            sleep(1500);
                            owner.informOfNpcMessage(new ChatMessage(n, "That's lucky, I need someone to do a quest for me", owner));
                            sleep(2000);
                            owner.informOfChatMessage(new ChatMessage(owner, "Ok I'll help", n));
                            sleep(2000);
                            owner.informOfNpcMessage(new ChatMessage(n, "Ok the problem is, there is a ghost in the church graveyard", owner));
                            sleep(2000);
                            owner.informOfNpcMessage(new ChatMessage(n, "I would like you to get rid of it", owner));
                            sleep(2000);
                            owner.informOfNpcMessage(new ChatMessage(n, "If you need any help", owner));
                            sleep(2000);
                            owner.informOfNpcMessage(new ChatMessage(n, "My friend father URhney is an expert of ghosts", owner));
                            sleep(2000);
                            owner.informOfNpcMessage(new ChatMessage(n, "I belive he is currently living as a hermit", owner));
                            sleep(2000);
                            owner.informOfNpcMessage(new ChatMessage(n, "He has a little shack somewhere in the swamps south of here", owner));
                            sleep(2000);
                            owner.informOfNpcMessage(new ChatMessage(n, "I'm sure if you told him that I sent you he'd be willing to help", owner));
                            sleep(2000);
                            owner.informOfNpcMessage(new ChatMessage(n, "My name is father Aereck by the way", owner));
                            sleep(2000);
                            owner.informOfNpcMessage(new ChatMessage(n, "Be careful going through the swamps", owner));
                            sleep(2000);
                            owner.informOfNpcMessage(new ChatMessage(n, "I have heard they can be quite dangerous", owner));
                            owner.setQuestStage(getQuestId(), 1);
                            owner.setBusy(false);
                            n.setBusy(false);																	
                            default:
                            owner.setBusy(false);
                            n.setBusy(false);
                            return;
                    }
		}
    	});
	p.getActionSender().sendMenu(options);
        }
	else if(p.getQuestStage(this) == 2 && p.getInventory().hasItemId(24)) {
            n.setBusy(true);
            p.setBusy(true);
            p.informOfNpcMessage(new ChatMessage(n, "Have you got rid of the ghost yet", p));
            sleep(2000);
            p.informOfChatMessage(new ChatMessage(p, "I had talk with father Urhney", n));
            sleep(2000);
            p.informOfChatMessage(new ChatMessage(p, "He has given me this funny amulet to talk to the ghost with", n));
            sleep(2000);
            p.informOfNpcMessage(new ChatMessage(n, "I always wondered what that amulet was", p));
            sleep(2000);
            p.informOfNpcMessage(new ChatMessage(n, "Well i hope it's useful. Tell me if you get rid of the ghost", p));
            sleep(2000);
            p.informOfNpcMessage(new ChatMessage(n, "You'll have to go through the wood to the west to get round the fence", p));
            sleep(2000);
            p.informOfNpcMessage(new ChatMessage(n, "Then you'll have to go right into the eastern depths of the swamp", p));
            sleep(2000);
            p.setBusy(false);
            n.setBusy(false);
	}
	else if(p.getQuestStage(this) == 1) {
            n.setBusy(true);
            p.setBusy(true);
            p.informOfNpcMessage(new ChatMessage(n, "Have you got rid of the ghost yet", p));
            sleep(2000);
            p.informOfChatMessage(new ChatMessage(p, "I can't find father Urhney at the moment", n));
            sleep(2000);
            p.informOfNpcMessage(new ChatMessage(n, "Well to get to the swamp he is in", p));
            sleep(2000);
            p.informOfNpcMessage(new ChatMessage(n, "you need to go round the back of the castle", p));
            sleep(2000);
            p.informOfNpcMessage(new ChatMessage(n, "The swamp is on the otherside of the fence to the south", p));
            sleep(2000);
            p.informOfNpcMessage(new ChatMessage(n, "You'll have to go through the wood to the west to get round the fence", p));
            sleep(2000);
            p.informOfNpcMessage(new ChatMessage(n, "Then you'll have to go right into the eastern depths of the swamp", p));
            sleep(2000);
            p.setBusy(false);
            n.setBusy(false);			
        } 
	else if(p.getQuestStage(this) == 3) {
            n.setBusy(true);
            p.setBusy(true);
            p.informOfNpcMessage(new ChatMessage(n, "Have you got rid of the ghost yet", p));
            sleep(2000);
            p.informOfChatMessage(new ChatMessage(p, "I found out that skull is missing from his coffin", n));
            sleep(2000);
            p.informOfChatMessage(new ChatMessage(p, "thats why he haven't left yet", n));
            sleep(2000);
            p.informOfNpcMessage(new ChatMessage(n, "Well go and find his skull then", p));
            sleep(2000);
            p.setBusy(false);
            n.setBusy(false);
	}
        else if(p.getQuestStage(this) == 3 && p.getInventory().hasItemId(27)) {
            n.setBusy(true);
            p.setBusy(true);
            p.informOfNpcMessage(new ChatMessage(n, "Have you got rid of the ghost yet", p));
            sleep(2000);
            p.informOfChatMessage(new ChatMessage(p, "I've finally found the ghost's skull", n));
            sleep(2000);
            p.informOfNpcMessage(new ChatMessage(n, "Put it in the ghost's coffin and see what happens", p));
            sleep(2000);
            p.setBusy(false);
            n.setBusy(false);
        }
	else if(p.getQuestStage(this) == -1) {
            p.setBusy(true);
            n.blockedBy(p);
            p.informOfNpcMessage(new ChatMessage(n, "I am sorry, I dont need any more help now", p));
            sleep(2000);
            p.informOfNpcMessage(new ChatMessage(n, "May Saradomin smile upn your journeys", p));
            p.setBusy(false);
            n.unblock();
	}
        } 
	else if(n.getID() == 10) {
            if(p.getQuestStage(this) == 1) {
		p.setBusy(true);
		n.setBusy(true);
		p.informOfNpcMessage(new ChatMessage(n, "Go away, I'm meditating", p));
		p.setBusy(false);
		n.setBusy(false);
		sleep(1500);
		String[] options4 = new String[] { "Father Aereck sent me to talk to you", "Well that's friendly", "I've come to repossess your house" };
		p.setMenuHandler(new MenuHandler(options4) {
		public void handleReply(final int option, final String reply) {
                    switch (option) {
                        case 0: //yes
                            owner.setBusy(true);
                            n.setBusy(true);
                            sleep(1500);
                            owner.informOfNpcMessage(new ChatMessage(n, "I suppose I'd better talk to you then", owner));
                            sleep(2000);
                            owner.informOfNpcMessage(new ChatMessage(n, "What problem has he got himself into this time?", owner));
                            sleep(2000);
                            n.setBusy(false);
                            owner.setBusy(false);
                            String[] options5 = new String[] { "He's got a ghost haunting his graveyard", "You mean he gets himself into lots of problems?" };
                            owner.setMenuHandler(new MenuHandler(options5) {
                            public void handleReply(final int option, final String reply) {
				switch (option) {
                                    case 0: //yes
                                        owner.setBusy(true);
                                        n.setBusy(true);
					sleep(1500);
					owner.informOfNpcMessage(new ChatMessage(n, "Oh the silly fool", owner));
					sleep(2000);
					owner.informOfNpcMessage(new ChatMessage(n, "I leave town for just five months", owner));
					sleep(2000);
					owner.informOfNpcMessage(new ChatMessage(n, "and already he can't manage", owner));
					sleep(2000);
					owner.informOfNpcMessage(new ChatMessage(n, "Sigh", owner));
					sleep(2000);
					owner.informOfNpcMessage(new ChatMessage(n, "Well I can't go back and exorcise it", owner));
					sleep(2000);
					owner.informOfNpcMessage(new ChatMessage(n, "I vowed not to leave this place", owner));
					sleep(2000);
					owner.informOfNpcMessage(new ChatMessage(n, "Until i had done a full two years of prayer and meditation", owner));
					sleep(2000);
					owner.informOfNpcMessage(new ChatMessage(n, "Tell you what i can do though", owner));
					sleep(2000);
					owner.informOfNpcMessage(new ChatMessage(n, "Take this amulet", owner));
					owner.getInventory().add(new InvItem(24));
					owner.getActionSender().sendInventory();
					sleep(1500);
					owner.getActionSender().sendMessage("Father Urhney hands you an amulet");
					sleep(2000);
					owner.informOfNpcMessage(new ChatMessage(n, "It is an amulet of Ghostspeak", owner));
					sleep(2000);
					owner.informOfNpcMessage(new ChatMessage(n, "So called because when you wear it you can speak to ghosts", owner));
					sleep(2000);
					owner.informOfNpcMessage(new ChatMessage(n, "A lot of ghosts are doomed to be ghosts", owner));
					sleep(2000);
					owner.informOfNpcMessage(new ChatMessage(n, "Because they have left some task uncompleted", owner));
					sleep(2000);
					owner.informOfNpcMessage(new ChatMessage(n, "Maybe if you know what this task is", owner));
					sleep(2000);
					owner.informOfNpcMessage(new ChatMessage(n, "You can get rid of the ghost", owner));
					sleep(2000);
					owner.informOfNpcMessage(new ChatMessage(n, "I'm not making any guarantees mind you", owner));
					sleep(2000);
					owner.informOfNpcMessage(new ChatMessage(n, "But it is the best I can do right now", owner));
					sleep(2000);
					owner.informOfChatMessage(new ChatMessage(owner, "Thank you, I'll give it a try", n));
					owner.setQuestStage(getQuestId(), 2);
					n.setBusy(false);
					owner.setBusy(false);
					break;
                                    case 1: // No
					owner.setBusy(true);
					n.setBusy(true);
					sleep(1500);
					owner.informOfNpcMessage(new ChatMessage(n, "Yeah.For example when we were trainee prests", owner));
                                        sleep(2000);
					owner.informOfNpcMessage(new ChatMessage(n, "He kept on getting stuck up bell ropes", owner));
                                        sleep(2000);
                                        owner.informOfNpcMessage(new ChatMessage(n, "Anyway I don't have time for chitchat", owner));
					sleep(2000);
					owner.informOfNpcMessage(new ChatMessage(n, "What's his problem this time?", owner));
					sleep(2000);
					owner.informOfChatMessage(new ChatMessage(owner, "He's got a ghost haunting his graveyard", n));
					sleep(2000);
					owner.informOfNpcMessage(new ChatMessage(n, "Oh the silly fool", owner));
					sleep(2000);
					owner.informOfNpcMessage(new ChatMessage(n, "I leave town for just five months", owner));
					sleep(2000);
					owner.informOfNpcMessage(new ChatMessage(n, "and already he can't manage", owner));
					sleep(2000);
					owner.informOfNpcMessage(new ChatMessage(n, "Sigh", owner));
					sleep(2000);
					owner.informOfNpcMessage(new ChatMessage(n, "Well I can't go back and exorcise it", owner));
					sleep(2000);
					owner.informOfNpcMessage(new ChatMessage(n, "I vowed not to leave this place", owner));
					sleep(2000);
					owner.informOfNpcMessage(new ChatMessage(n, "Until i had done a full two years of prayer and meditation", owner));
					sleep(2000);
					owner.informOfNpcMessage(new ChatMessage(n, "Tell you what i can do though", owner));
					sleep(2000);
					owner.informOfNpcMessage(new ChatMessage(n, "Take this amulet", owner));
					owner.getInventory().add(new InvItem(24));
					owner.getActionSender().sendInventory();
					sleep(1500);
					owner.getActionSender().sendMessage("Father Urhney hands you an amulet");
					sleep(2000);
					owner.informOfNpcMessage(new ChatMessage(n, "It is an amulet of Ghostspeak", owner));
					sleep(2000);
					owner.informOfNpcMessage(new ChatMessage(n, "So called because when you wear it you can speak to ghosts", owner));
					sleep(2000);
					owner.informOfNpcMessage(new ChatMessage(n, "A lot of ghosts are doomed to be ghosts", owner));
					sleep(2000);
					owner.informOfNpcMessage(new ChatMessage(n, "Because they have left some task uncompleted", owner));
					sleep(2000);
					owner.informOfNpcMessage(new ChatMessage(n, "Maybe if you know what this task is", owner));
					sleep(2000);
					owner.informOfNpcMessage(new ChatMessage(n, "You can get rid of the ghost", owner));
					sleep(2000);
					owner.informOfNpcMessage(new ChatMessage(n, "I'm not making any guarantees mind you", owner));
					sleep(2000);
					owner.informOfNpcMessage(new ChatMessage(n, "But it is the best I can do right now", owner));
					sleep(2000);
					owner.informOfChatMessage(new ChatMessage(owner, "Thank you, I'll give it a try", n));
					owner.setQuestStage(getQuestId(), 2);
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
                            owner.getActionSender().sendMenu(options5);
                            break;
			case 1: // No
                            n.setBusy(true);
                            owner.setBusy(true);
                            sleep(1500);
                            owner.informOfNpcMessage(new ChatMessage(n, "I said go away!", owner));
                            sleep(2000);
                            owner.informOfChatMessage(new ChatMessage(owner, "Ok, ok", n));
                            n.setBusy(false);
                            owner.setBusy(false);
                            break;
			case 2:
										n.setBusy(true);
										owner.setBusy(true);
										sleep(1500);
										owner.informOfNpcMessage(new ChatMessage(n, "Under what grounds?", owner));
										sleep(2000);
										n.setBusy(false);
										owner.setBusy(false);
										String[] options6 = new String[] { "Repeated failure on mortgage payments", "I don't know, I just wanted this house" };
											owner.setMenuHandler(new MenuHandler(options6) {
												public void handleReply(final int option, final String reply) {
													switch (option) {
														case 0: //yes
															owner.setBusy(true);
															n.setBusy(true);
															sleep(1500);
															owner.informOfNpcMessage(new ChatMessage(n, "I don't have a mortgage", owner));
															sleep(2000);
															owner.informOfNpcMessage(new ChatMessage(n, "I built this house myself", owner));
															sleep(2000);
															owner.informOfChatMessage(new ChatMessage(owner, "Sorry I must have got the wrong address", n));
															sleep(2000);
															owner.informOfChatMessage(new ChatMessage(owner, "All the houses look the same around here", n));
															n.setBusy(false);
															owner.setBusy(false);
															break;
														case 1: // No
															n.setBusy(true);
															owner.setBusy(true);
															sleep(1500);
															owner.informOfNpcMessage(new ChatMessage(n, "Oh go away and stop wasting my time", owner));
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
											owner.getActionSender().sendMenu(options6);
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
				else if(p.getQuestStage(this) == 0 || p.getQuestStage(this) == 2) {
					p.setBusy(true);
					n.setBusy(true);
					p.informOfNpcMessage(new ChatMessage(n, "Go away, I'm meditating", p));
					sleep(2000);
					p.setBusy(false);
					n.setBusy(false);
						String[] options14 = new String[] { "Well that's friendly", "I've come to repossess your house" };
						p.setMenuHandler(new MenuHandler(options14) {
							public void handleReply(final int option, final String reply) {
								switch (option) {
									case 0: 
										n.setBusy(true);
										owner.setBusy(true);
										sleep(1500);
										owner.informOfNpcMessage(new ChatMessage(n, "I said go away!", owner));
										sleep(2000);
										owner.informOfChatMessage(new ChatMessage(owner, "Ok, ok", n));
										n.setBusy(false);
										owner.setBusy(false);
										break;
									case 1:
										owner.setBusy(true);
										n.setBusy(true);
										sleep(1500);
										owner.informOfNpcMessage(new ChatMessage(n, "Under what grounds?", owner));
										sleep(2000);
										n.setBusy(false);
										owner.setBusy(false);
										String[] options15 = new String[] { "Repeated failure on mortgage payments", "I don't know, I just wanted this house" };
											owner.setMenuHandler(new MenuHandler(options15) {
												public void handleReply(final int option, final String reply) {
													switch (option) {
														case 0: //yes
															owner.setBusy(true);
															n.setBusy(true);
															sleep(1500);
															owner.informOfNpcMessage(new ChatMessage(n, "I don't have a mortgage", owner));
															sleep(2000);
															owner.informOfNpcMessage(new ChatMessage(n, "I built this house myself", owner));
															sleep(2000);
															owner.informOfChatMessage(new ChatMessage(owner, "Sorry I must have got the wrong address", n));
															sleep(2000);
															owner.informOfChatMessage(new ChatMessage(owner, "All the houses look the same around here", n));
															n.setBusy(false);
															owner.setBusy(false);
															break;
														case 1: // No
															n.setBusy(true);
															owner.setBusy(true);
															sleep(1500);
															owner.informOfNpcMessage(new ChatMessage(n, "Oh go away and stop wasting my time", owner));
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
											owner.getActionSender().sendMenu(options15);
											break;
									default:
										owner.setBusy(false);
										n.setBusy(false);
										return;
								}
							}
						});
						p.getActionSender().sendMenu(options14);
				} 
				else if(p.getQuestStage(this) == 3) {
					p.setBusy(true);
					n.setBusy(true);
					p.informOfChatMessage(new ChatMessage(p, "This amulet works perfectly", n));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "Good good!", p));
					sleep(2000);
					p.informOfChatMessage(new ChatMessage(p, "I'm going to run now to resolve ghost problem", n));
					p.setBusy(false);
					n.setBusy(false);
				}				
		} 
		else if(n.getID() == 15) {
				if(p.getQuestStage(this) == 2 && p.getInventory().wielding(24)) {
					p.setBusy(true);
					n.setBusy(true);
					p.informOfChatMessage(new ChatMessage(p, "Hello ghost, how are you", n));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "Not very good actually", p));
					sleep(2000);
					p.informOfChatMessage(new ChatMessage(p, "What's the problem then?", n));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "Did you just understand what I said?", p));
					sleep(2000);
					p.setBusy(false);
					n.setBusy(false);
					String[] options7 = new String[] { "Yep, now tell me what the problem is", "No, you just sound like you're speaking nonsense to me", "Wow, this amulet works" };
						p.setMenuHandler(new MenuHandler(options7) {
							public void handleReply(final int option, final String reply) {
								switch (option) {
									case 0: //yes
										owner.setBusy(true);
										n.setBusy(true);
										sleep(1500);
										owner.informOfNpcMessage(new ChatMessage(n, "Wow this is incredible, I didn't expect any one to understand me again", owner));
										sleep(2000);
										owner.informOfChatMessage(new ChatMessage(owner, "Yes, yes I can understand you", n));
										sleep(2000);
										owner.informOfChatMessage(new ChatMessage(owner, "But have you any idea why're doomed to be a ghost", n));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "I'm not sure", owner));
										sleep(2000);
										owner.informOfChatMessage(new ChatMessage(owner, "I've been told a certain task may need to be completed", n));
										sleep(2000);
										owner.informOfChatMessage(new ChatMessage(owner, "So you can rest in peace", n));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "I should think it is probably because", owner));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "A warlock has come along and stolen my skull", owner));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "If you look inside my coffin there", owner));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "you'll find a corpse without a head on it", owner));
										sleep(2000);
										owner.informOfChatMessage(new ChatMessage(owner, "Do you know where this warlock might be now?", n));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "I think it was one of the warlocks who lives in the big tower", owner));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "In the sea southwest from here", owner));
										sleep(2000);
										owner.informOfChatMessage(new ChatMessage(owner, "Ok I will try and get the skull back for you, so you can rest in peace", n));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "Ooh thank you. That would be such a great relief", owner));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "It is so dull being a ghost", owner));
										owner.setQuestStage(getQuestId(), 3);
										n.setBusy(false);
										owner.setBusy(false);
										break;
									case 1: // No
										n.setBusy(true);
										owner.setBusy(true);
										sleep(1500);
										owner.informOfChatMessage(new ChatMessage(owner, "No", n));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "Oh that's a pity. You got my hopes up there", owner));
										sleep(2000);
										owner.informOfChatMessage(new ChatMessage(owner, "Yeah, it is pity. Sorry", n));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "Hang on a second. You can understand me", owner));
										sleep(2000);
										n.setBusy(false);
										owner.setBusy(false);
										String[] options8 = new String[] { "No I can't", "Yep clever aren't I" };
											owner.setMenuHandler(new MenuHandler(options8) {
												public void handleReply(final int option, final String reply) {
													switch (option) {
														case 0: //yes
															owner.setBusy(true);
															n.setBusy(true);
															sleep(1500);
															owner.informOfNpcMessage(new ChatMessage(n, "I don't know, the first person i can speak to in ages is a moron", owner));
															n.setBusy(false);
															owner.setBusy(false);
															break;
														case 1: // No
															n.setBusy(true);
															owner.setBusy(true);
															sleep(1500);
															owner.informOfNpcMessage(new ChatMessage(n, "I'm Impressed", owner));
															sleep(2000);
															owner.informOfNpcMessage(new ChatMessage(n, "You must be very powerful", owner));
															sleep(2000);
															owner.informOfNpcMessage(new ChatMessage(n, "I don't suppose you can stop me being a ghost", owner));
															sleep(2000);
															n.setBusy(false);
															owner.setBusy(false);
															String[] options9 = new String[] { "Yes, Ok. Do you know why you're a ghost?", "No, you're scary" };
																owner.setMenuHandler(new MenuHandler(options9) {
																	public void handleReply(final int option, final String reply) {
																		switch (option) {
																			case 0: //yes
																				n.setBusy(true);
																				owner.setBusy(true);
																				sleep(1500);
																				owner.informOfNpcMessage(new ChatMessage(n, "I should think it is probably because", owner));
																				sleep(2000);
																				owner.informOfNpcMessage(new ChatMessage(n, "A warlock has come along and stolen my skull", owner));
																				sleep(2000);
																				owner.informOfNpcMessage(new ChatMessage(n, "If you look inside my coffin there", owner));
																				sleep(2000);
																				owner.informOfNpcMessage(new ChatMessage(n, "you'll find a corpse without a head on it", owner));
																				sleep(2000);
																				owner.informOfChatMessage(new ChatMessage(owner, "Do you know where this warlock might be now?", n));
																				sleep(2000);
																				owner.informOfNpcMessage(new ChatMessage(n, "I think it was one of the warlocks who lives in the big tower", owner));
																				sleep(2000);
																				owner.informOfNpcMessage(new ChatMessage(n, "In the sea southwest from here", owner));
																				sleep(2000);
																				owner.informOfChatMessage(new ChatMessage(owner, "Ok I will try and get the skull back for you, so you can rest in peace", n));
																				sleep(2000);
																				owner.informOfNpcMessage(new ChatMessage(n, "Ooh thank you. That would be such a great relief", owner));
																				sleep(2000);
																				owner.informOfNpcMessage(new ChatMessage(n, "It is so dull being a ghost", owner));
																				owner.setQuestStage(getQuestId(), 3);
																				n.setBusy(false);
																				owner.setBusy(false);
																				break;
																			case 1: // No
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
																owner.getActionSender().sendMenu(options9);
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
									case 2:
										owner.setBusy(true);
										n.setBusy(true);
										sleep(1500);
										owner.informOfNpcMessage(new ChatMessage(n, "Oh its your amulet that's doing it. I did wonder", owner));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "I don't suppose you can help me? I dont like being a ghost", owner));
										sleep(2000);
										owner.setBusy(false);
										n.setBusy(false);
										String[] options10 = new String[] { "Yes, Ok. Do you know why you're a ghost?", "No, you're scary" };
											owner.setMenuHandler(new MenuHandler(options10) {
												public void handleReply(final int option, final String reply) {
													switch (option) {
														case 0: //yes
															n.setBusy(true);
															owner.setBusy(true);
															sleep(1500);
															owner.informOfNpcMessage(new ChatMessage(n, "I should think it is probably because", owner));
															sleep(2000);
															owner.informOfNpcMessage(new ChatMessage(n, "A warlock has come along and stolen my skull", owner));
															sleep(2000);
															owner.informOfNpcMessage(new ChatMessage(n, "If you look inside my coffin there", owner));
															sleep(2000);
															owner.informOfNpcMessage(new ChatMessage(n, "you'll find a corpse without a head on it", owner));
															sleep(2000);
															owner.informOfChatMessage(new ChatMessage(owner, "Do you know where this warlock might be now?", n));
															sleep(2000);
															owner.informOfNpcMessage(new ChatMessage(n, "I think it was one of the warlocks who lives in the big tower", owner));
															sleep(2000);
															owner.informOfNpcMessage(new ChatMessage(n, "In the sea southwest from here", owner));
															sleep(2000);
															owner.informOfChatMessage(new ChatMessage(owner, "Ok I will try and get the skull back for you, so you can rest in peace", n));
															sleep(2000);
															owner.informOfNpcMessage(new ChatMessage(n, "Ooh thank you. That would be such a great relief", owner));
															sleep(2000);
															owner.informOfNpcMessage(new ChatMessage(n, "It is so dull being a ghost", owner));
															owner.setQuestStage(getQuestId(), 3);
															n.setBusy(false);
															owner.setBusy(false);
															break;
														case 1: // No
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
											owner.getActionSender().sendMenu(options10);
											break;
									default:
										owner.setBusy(false);
										n.setBusy(false);
										return;
								}
							}
						});
						p.getActionSender().sendMenu(options7);
				} 
				else if(p.getQuestStage(this) == 3 && p.getInventory().wielding(24) && p.getInventory().hasItemId(27)) {
					p.setBusy(true);
					n.setBusy(true);
					p.informOfChatMessage(new ChatMessage(p, "Hello ghost, how are you", n));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "How are you doing finding my skull ?", p));
					sleep(2000);
					p.informOfChatMessage(new ChatMessage(p, "I have found it", n));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "Hurrah now i can stop being a ghost", p));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "You just need to put it in my coffin over there", p));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "And i will be free", p));
					sleep(2000);
					p.setBusy(false);
					n.setBusy(false);
				}
				else if(p.getQuestStage(this) == 3 && !p.getInventory().hasItemId(27) && p.getInventory().wielding(24)) {
					p.setBusy(true);
					n.setBusy(true);
					p.informOfChatMessage(new ChatMessage(p, "Hello ghost, how are you", n));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "How are you doing finding my skull ?", p));
					sleep(2000);
					p.informOfChatMessage(new ChatMessage(p, "Sorry, i can't find it at the moment", n));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "Ah well keep on looking", p));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "I'm pretty sure it's somewhere in the tower southwest from here", p));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "There's a lot of levels to the tower though", p));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "I suppose it might take a little while to find", p));
					sleep(2000);
					p.setBusy(false);
					n.setBusy(false);
				}
				else {
					p.setBusy(true);
					n.setBusy(true);
					p.informOfChatMessage(new ChatMessage(p, "Hello ghost, how are you", n));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "Wooo wooo wooooo", p));
					sleep(2000);
					p.setBusy(false);
					n.setBusy(false);
					String[] options11 = new String[] { "Sorry I don't speak ghost", "Ooh that's intresting", "Any hints where I can find some treasure?" };
						p.setMenuHandler(new MenuHandler(options11) {
							public void handleReply(final int option, final String reply) {
								switch (option) {
									case 0: //yes
										n.setBusy(true);
										owner.setBusy(true);
										sleep(1500);
										owner.informOfNpcMessage(new ChatMessage(n, "Woo woo?", owner));
										sleep(2000);
										owner.informOfChatMessage(new ChatMessage(owner, "Nope still don't understand you", n));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "Woooooooooo", owner));
										sleep(2000);
										owner.informOfChatMessage(new ChatMessage(owner, "Never mind", n));
										n.setBusy(false);
										owner.setBusy(false);
										break;
									case 1: // No
										owner.setBusy(true);
										n.setBusy(true);
										sleep(1500);
										owner.informOfNpcMessage(new ChatMessage(n, "Woo woooo", owner));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "Woooooooooooooooooo", owner));
										sleep(2000);
										n.setBusy(false);
										owner.setBusy(false);
										String[] options12 = new String[] { "Did he really?", "Yeah that's what i thought" };
											owner.setMenuHandler(new MenuHandler(options12) {
												public void handleReply(final int option, final String reply) {
													switch (option) {
														case 0: //yes
															n.setBusy(true);
															owner.setBusy(true);
															sleep(1500);
															owner.informOfNpcMessage(new ChatMessage(n, "Woo", owner));
															sleep(2000);
															owner.informOfChatMessage(new ChatMessage(owner, "My brother had exactly the same problem", n));
															sleep(2000);
															owner.informOfNpcMessage(new ChatMessage(n, "Woo woooooo", owner));
															sleep(2000);
															owner.informOfNpcMessage(new ChatMessage(n, "Woooooo woo woo woo", owner));
															sleep(2000);
															owner.informOfChatMessage(new ChatMessage(owner, "You'll have to give me the recipe some time", n));
															sleep(2000);
															owner.informOfNpcMessage(new ChatMessage(n, "Wooooooooooo wo woooooooooo", owner));
															sleep(2000);
															owner.informOfChatMessage(new ChatMessage(owner, "Hmm I'm not sure about that", n));
															sleep(2000);
															owner.informOfNpcMessage(new ChatMessage(n, "Wooo woo", owner));
															sleep(2000);
															owner.informOfChatMessage(new ChatMessage(owner, "Well if you insist", n));
															sleep(2000);
															owner.informOfNpcMessage(new ChatMessage(n, "Woooooo", owner));
															sleep(2000);
															owner.informOfChatMessage(new ChatMessage(owner, "Ah well, better be off now", n));
															sleep(2000);
															owner.informOfNpcMessage(new ChatMessage(n, "Woo", owner));
															sleep(2000);
															owner.informOfChatMessage(new ChatMessage(owner, "Bye", n));
															n.setBusy(false);
															owner.setBusy(false);
															break;
														case 1: // No
															owner.setBusy(true);
															n.setBusy(true);
															sleep(1500);
															owner.informOfNpcMessage(new ChatMessage(n, "Wooooo wooooooooooooooo", owner));
															sleep(2000);
															owner.informOfChatMessage(new ChatMessage(owner, "Goodbye. Thanks for the chat", n));
															sleep(2000);
															owner.informOfNpcMessage(new ChatMessage(n, "Woooo wooooo", owner));
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
										owner.getActionSender().sendMenu(options12);
										break;
									case 2:
										owner.setBusy(true);
										n.setBusy(true);
										sleep(1500);
										owner.informOfNpcMessage(new ChatMessage(n, "Woooooooooo woo!", owner));
										sleep(2000);
										owner.setBusy(false);
										n.setBusy(false);
										String[] options13 = new String[] { "Sorry I don't speak ghost", "Thank you, You've been very helpful" };
											owner.setMenuHandler(new MenuHandler(options13) {
												public void handleReply(final int option, final String reply) {
													switch (option) {
														case 0: //yes
															n.setBusy(true);
															owner.setBusy(true);
															sleep(1500);
															owner.informOfNpcMessage(new ChatMessage(n, "Woo woo?", owner));
															sleep(2000);
															owner.informOfChatMessage(new ChatMessage(owner, "Nope still don't understand you", n));
															sleep(2000);
															owner.informOfNpcMessage(new ChatMessage(n, "Woooooooooo", owner));
															sleep(2000);
															owner.informOfChatMessage(new ChatMessage(owner, "Never mind", n));
															n.setBusy(false);
															owner.setBusy(false);
															break;
														case 1: // No
															owner.setBusy(true);
															n.setBusy(true);
															sleep(1500);
															owner.informOfNpcMessage(new ChatMessage(n, "Woooooooo", owner));							
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
										owner.getActionSender().sendMenu(options13);
										break;
									default:
										owner.setBusy(false);
										n.setBusy(false);
										return;
								}
							}
						});
						p.getActionSender().sendMenu(options11);
				}
		}
	}
	
	@Override
	public void onObjectAction(GameObject obj, String command, Player player) {
		if (command.equals("search") && obj.getID() == 40 && player.getQuestStage(this) >= 0) {
			player.getActionSender().sendMessage("There's a skeleton without a skull in here");
			return;
		} 
		else if(command.equals("search") && obj.getID() == 40 && player.getQuestStage(this) == -1) {
			player.getActionSender().sendMessage("There is a nice and complete skeleton in here");
			return;
		}
		
	}
	
	@Override
	public void onInvUseOnObject(GameObject obj, InvItem item, Player player) {
			if(obj.getID() == 40 && player.getQuestStage(this) == 3 && item.getID() == 27) {
				player.getActionSender().sendMessage("You put the skull in the coffin");
				player.setQuestStage(getQuestId(), -1);
				player.getInventory().remove(27, 1);
				player.getActionSender().sendInventory();
				sleep(2000);
				player.getActionSender().sendMessage("You think you hear a faint voice in the air");
				sleep(2000);
				player.getActionSender().sendMessage("Thank you");
				player.incExp(5, 1125, false, false, false);
				return;
			}
	}
	
	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		if(n.getID() == 9 || n.getID() == 15 || n.getID() == 10) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean blockInvUseOnObject(GameObject obj, InvItem item, Player player) {
		if(item.getID() == 27 && obj.getID() == 40) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean blockObjectAction(GameObject obj, String command, Player player) {
		if(obj.getID() == 40 && command.equals("search")) {
			return true;
		}
		return false;
	}
}
