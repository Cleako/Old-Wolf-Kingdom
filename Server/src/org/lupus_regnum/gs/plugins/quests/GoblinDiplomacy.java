package org.lupus_regnum.gs.plugins.quests;

import org.lupus_regnum.gs.model.ChatMessage;
import org.lupus_regnum.gs.model.InvItem;
import org.lupus_regnum.gs.model.MenuHandler;
import org.lupus_regnum.gs.model.Npc;
import org.lupus_regnum.gs.model.Player;
import org.lupus_regnum.gs.model.World;
import org.lupus_regnum.gs.plugins.Quest;
import org.lupus_regnum.gs.plugins.listeners.action.TalkToNpcListener;
import org.lupus_regnum.gs.plugins.listeners.executive.TalkToNpcExecutiveListener;

public class GoblinDiplomacy extends Quest implements TalkToNpcListener, TalkToNpcExecutiveListener {

	@Override
	public int getQuestId() {
		return 9;
	}

	@Override
	public String getQuestName() {
		return "Goblin Diplomacy";
	}
	
	@Override
	public void onTalkToNpc(Player p, final Npc n) {
		final Npc wartface = World.getWorld().getNpc(151, 321, 445, 326, 449);
		final Npc bentnoze = World.getWorld().getNpc(152, 314, 330, 441, 457);
		if(n.getID() == 151) {		
				if(p.getQuestStage(this) == 0) {
					n.setBusy(true);
					p.setBusy(true);
					System.out.println("Quest stage: " + 0);
					p.informOfNpcMessage(new ChatMessage(n, "Green armour best", p));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(bentnoze, "No, no red every time", p));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "Go away human, we busy", p));
					sleep(2000);
					n.setBusy(false);
					bentnoze.setBusy(false);
					p.setBusy(false);				
				}
				else if(p.getQuestStage(this) == 1) {
					n.setBusy(true);
					p.setBusy(true);
					p.informOfNpcMessage(new ChatMessage(n, "Green armour best", p));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(bentnoze, "No, no red every time", p));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "Go away human, we busy", p));
					sleep(2000);
					n.setBusy(false);
					bentnoze.setBusy(false);
					p.setBusy(false);
					n.setBusy(false);
					String[] options = new String[] { "Why are you arguing about the colour of your armour?", "Wouldn't you prefer peace?", "Do you want me to pick an armour colour for you" };
						p.setMenuHandler(new MenuHandler(options) {
							public void handleReply(final int option, final String reply) {
								switch (option) {
									case 0: //yes
										owner.setBusy(true);
										n.setBusy(true);
										sleep(1500);
										owner.informOfNpcMessage(new ChatMessage(n, "We decide to celevrate goblin new century", owner));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "By changing the colour of our armour", owner));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "Light blue get boring after a bit", owner));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "And we cant change", owner));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "Problem is we want different change to us", owner));
										n.setBusy(false);
										owner.setBusy(false);
										break;
									case 1: // No
										owner.setBusy(true);
										wartface.setBusy(true);
										sleep(1500);
										owner.informOfNpcMessage(new ChatMessage(n, "Yeah peace is good as long as it is peace wearing green armour", owner));
										sleep(2000);
										bentnoze.setBusy(true);
										owner.informOfNpcMessage(new ChatMessage(bentnoze, "But green to much like skin!", owner));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(bentnoze, "Nearly make you look naked!", owner));
										bentnoze.setBusy(false);
										wartface.setBusy(false);
										owner.setBusy(false);
										break;
									case 2:
										owner.setBusy(true);
										n.setBusy(true);
										sleep(1500);
										owner.informOfChatMessage(new ChatMessage(owner, "Different to either green or red", n));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "Hmm me dunno what that'd look like", owner));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "You'd have to bring me some, so us could decide", owner));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(bentnoze, "Yep bring us orange armour", owner));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "Yep orange might be good", owner));
										owner.setQuestStage(getQuestId(), 2);
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
				else if(p.getQuestStage(this) == 2) {
					n.setBusy(true);
					p.setBusy(true);
					p.informOfNpcMessage(new ChatMessage(n, "Oh it you", p));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "Have you got some orange goblin armour yet?", p));
					sleep(2000);
					if(p.getInventory().hasItemId(274)) {
						n.setBusy(true);
						p.setBusy(true);
						p.informOfChatMessage(new ChatMessage(p, "Yeah I have it right here", n));
						sleep(2000);
						p.getActionSender().sendMessage("You hand Wartface the armour");
						p.getInventory().remove(274, 1);
						p.getActionSender().sendInventory();
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "No I don't like that much", p));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(bentnoze, "It clashes with my skin colour", p));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "Try bringing us dark blue armour", p));
						sleep(2000);
						p.setQuestStage(getQuestId(), 3);
						n.setBusy(false);
						p.setBusy(false);
					} 
					else {
						p.informOfChatMessage(new ChatMessage(p, "Err no", n));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "Come back when you have some", p));
						sleep(2000);
						n.setBusy(false);
						p.setBusy(false);	
					}
				}
				else if(p.getQuestStage(this) == 3) {
					n.setBusy(true);
					p.setBusy(true);
					p.informOfNpcMessage(new ChatMessage(n, "Oh it you", p));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "Have you got us some dark blue goblin armour?", p));
					sleep(2000);
					if(p.getInventory().hasItemId(275)) {
						n.setBusy(true);
						p.setBusy(true);
						p.informOfChatMessage(new ChatMessage(p, "Yes, here you go", n));
						sleep(2000);
						p.getActionSender().sendMessage("You hand Wartface the armour");
						p.getInventory().remove(275, 1);
						p.getActionSender().sendInventory();
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "Doesn't seem quite right", p));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(bentnoze, "Maybe if it was a bit lighter", p));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "Yeah try light blue", p));
						sleep(2000);
						p.informOfChatMessage(new ChatMessage(p, "I thought that was the armour you were changing from", n));
						sleep(2000);
						p.informOfChatMessage(new ChatMessage(p, "But never mind, anything is worth a try to avoid a war", n));
						sleep(2000);
						p.setQuestStage(getQuestId(), 4);
						n.setBusy(false);
						p.setBusy(false);
					} 
					else {
						p.informOfChatMessage(new ChatMessage(p, "Not yet", n));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "Come back when you have some", p));
						sleep(2000);
						n.setBusy(false);
						p.setBusy(false);	
					}
				}
				else if(p.getQuestStage(this) == 4) {
					n.setBusy(true);
					p.setBusy(true);
					p.informOfNpcMessage(new ChatMessage(n, "Oh it you", p));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "Have you got some light blue goblin armour yet?", p));
					sleep(2000);
					if(p.getInventory().hasItemId(273)) {
						n.setBusy(true);
						p.setBusy(true);
						p.informOfChatMessage(new ChatMessage(p, "Sigh...", n));
						sleep(2000);
						p.informOfChatMessage(new ChatMessage(p, "Yes, here it is", n));
						sleep(2000);
						p.getActionSender().sendMessage("You hand Wartface the armour");
						p.getInventory().remove(273, 1);
						p.getActionSender().sendInventory();
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "That is rather nice", p));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(bentnoze, "Yes i could see myself wearing somethin' like that", p));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "It a deal then", p));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "Light blue it is", p));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "Thank you for sorting our argument", p));
						sleep(2000);
						p.setQuestStage(getQuestId(), -1);
						p.incExp(12, 1000, false, false, false);
						p.getActionSender().sendMessage("General Wartface gives you a gold bar as thanks");
						p.getInventory().add(new InvItem(172, 1));
						p.getActionSender().sendInventory();
						n.setBusy(false);
						p.setBusy(false);
					} 
					else {
						p.informOfChatMessage(new ChatMessage(p, "Not yet", n));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "Come back when you have some", p));
						sleep(2000);
						n.setBusy(false);
						p.setBusy(false);	
					}
				}
				else if(p.getQuestStage(this) == -1) { // COMPLETED
					p.setBusy(true);
					n.setBusy(true);
					p.informOfNpcMessage(new ChatMessage(n, "Now you've solved our argument we gotta think of something else to do", p));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(bentnoze, "Yep, we bored now", p));
					p.setBusy(false);
					n.setBusy(false);
				}
		} 
		else if(n.getID() == 152) {
				if(p.getQuestStage(this) == 0) {
					n.setBusy(true);
					p.setBusy(true);
					System.out.println("Quest stage: " + 0);
					p.informOfNpcMessage(new ChatMessage(n, "Red armour best", p));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(wartface, "No, no green every time", p));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "Go away human, we busy", p));
					sleep(2000);
					n.setBusy(false);
					wartface.setBusy(false);
					p.setBusy(false);				
				}
				else if(p.getQuestStage(this) == 1) {
					n.setBusy(true);
					p.setBusy(true);
					p.informOfNpcMessage(new ChatMessage(n, "Green armour best", p));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(wartface, "No, no red every time", p));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "Go away human, we busy", p));
					sleep(2000);
					n.setBusy(false);
					wartface.setBusy(false);
					p.setBusy(false);
					String[] options = new String[] { "Why are you arguing about the colour of your armour?", "Wouldn't you prefer peace?", "Do you want me to pick an armour colour for you" };
						p.setMenuHandler(new MenuHandler(options) {
							public void handleReply(final int option, final String reply) {
								switch (option) {
									case 0: //yes
										owner.setBusy(true);
										n.setBusy(true);
										sleep(1500);
										owner.informOfNpcMessage(new ChatMessage(n, "We decide to celevrate goblin new century", owner));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "By changing the colour of our armour", owner));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "Light blue get boring after a bit", owner));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "And we cant change", owner));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "Problem is we want different change to us", owner));
										n.setBusy(false);
										owner.setBusy(false);
										break;
									case 1: // No
										owner.setBusy(true);
										wartface.setBusy(true);
										sleep(1500);
										owner.informOfNpcMessage(new ChatMessage(n, "Yeah peace is good as long as it is peace wearing green armour", owner));
										wartface.setBusy(true);
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(wartface, "But green to much like skin!", owner));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(wartface, "Nearly make you look naked!", owner));
										wartface.setBusy(false);
										wartface.setBusy(false);
										owner.setBusy(false);
										break;
									case 2:
										owner.setBusy(true);
										n.setBusy(true);
										sleep(1500);
										owner.informOfChatMessage(new ChatMessage(owner, "Different to either green or red", n));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "Hmm me dunno what that'd look like", owner));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "You'd have to bring me some, so us could decide", owner));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(wartface, "Yep bring us orange armour", owner));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "Yep orange might be good", owner));
										owner.setQuestStage(getQuestId(), 2);
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
				else if(p.getQuestStage(this) == 2) {
					n.setBusy(true);
					p.setBusy(true);
					p.informOfNpcMessage(new ChatMessage(n, "Oh it you", p));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "Have you got some orange goblin armour yet?", p));
					sleep(2000);
					if(p.getInventory().hasItemId(274)) {
						n.setBusy(true);
						p.setBusy(true);
						p.informOfChatMessage(new ChatMessage(p, "Yeah I have it right here", n));
						sleep(2000);
						p.getActionSender().sendMessage("You hand Bentnoze the armour");
						p.getInventory().remove(274, 1);
						p.getActionSender().sendInventory();
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "No I don't like that much", p));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(wartface, "It clashes with my skin colour", p));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "Try bringing us dark blue armour", p));
						sleep(2000);
						p.setQuestStage(getQuestId(), 3);
						n.setBusy(false);
						p.setBusy(false);
					} 
					else {
						p.informOfChatMessage(new ChatMessage(p, "Err no", n));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "Come back when you have some", p));
						sleep(2000);
						n.setBusy(false);
						p.setBusy(false);	
					}
				}
				else if(p.getQuestStage(this) == 3) {
					n.setBusy(true);
					p.setBusy(true);
					p.informOfNpcMessage(new ChatMessage(n, "Oh it you", p));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "Have you got us some dark blue goblin armour?", p));
					sleep(2000);
					if(p.getInventory().hasItemId(275)) {
						n.setBusy(true);
						p.setBusy(true);
						p.informOfChatMessage(new ChatMessage(p, "Yes, here you go", n));
						sleep(2000);
						p.getActionSender().sendMessage("You hand Bentnoze the armour");
						p.getInventory().remove(275, 1);
						p.getActionSender().sendInventory();
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "Doesn't seem quite right", p));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(wartface, "Maybe if it was a bit lighter", p));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "Yeah try light blue", p));
						sleep(2000);
						p.informOfChatMessage(new ChatMessage(p, "I thought that was the armour you were changing from", n));
						sleep(2000);
						p.informOfChatMessage(new ChatMessage(p, "But never mind, anything is worth a try to avoid a war", n));
						sleep(2000);
						p.setQuestStage(getQuestId(), 4);
						n.setBusy(false);
						p.setBusy(false);
					} 
					else {
						p.informOfChatMessage(new ChatMessage(p, "Not yet", n));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "Come back when you have some", p));
						sleep(2000);
						n.setBusy(false);
						p.setBusy(false);	
					}
				}
				else if(p.getQuestStage(this) == 4) {
					n.setBusy(true);
					p.setBusy(true);
					p.informOfNpcMessage(new ChatMessage(n, "Oh it you", p));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "Have you got some light blue goblin armour yet?", p));
					sleep(2000);
					if(p.getInventory().hasItemId(273)) {
						n.setBusy(true);
						p.setBusy(true);
						p.informOfChatMessage(new ChatMessage(p, "Sigh...", n));
						sleep(2000);
						p.informOfChatMessage(new ChatMessage(p, "Yes, here it is", n));
						sleep(2000);
						p.getActionSender().sendMessage("You hand Bentnoze the armour");
						p.getInventory().remove(273, 1);
						p.getActionSender().sendInventory();
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "That is rather nice", p));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(wartface, "Yes i could see myself wearing somethin' like that", p));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "It a deal then", p));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "Light blue it is", p));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "Thank you for sorting our argument", p));
						sleep(2000);
						p.setQuestStage(getQuestId(), -1);
						p.incExp(12, 1000, false, false, false);
						p.getActionSender().sendMessage("General Bentnoze gives you a gold bar as thanks");
						p.getInventory().add(new InvItem(172, 1));
						p.getActionSender().sendInventory();
						n.setBusy(false);
						p.setBusy(false);
					} 
					else {
						p.informOfChatMessage(new ChatMessage(p, "Not yet", n));
						sleep(2000);
						p.informOfNpcMessage(new ChatMessage(n, "Come back when you have some", p));
						sleep(2000);
						n.setBusy(false);
						p.setBusy(false);	
					}
				}
				else if(p.getQuestStage(this) == -1) { // COMPLETED
					p.setBusy(true);
					n.setBusy(true);
					p.informOfNpcMessage(new ChatMessage(n, "Now you've solved our argument we gotta think of something else to do", p));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(bentnoze, "Yep, we bored now", p));
					p.setBusy(false);
					n.setBusy(false);
				}
		} 
		else if(n.getID() == 150) {
				if(p.getQuestStage(this) == 0) {
					n.setBusy(true);
					p.setBusy(true);
					p.informOfNpcMessage(new ChatMessage(n, "Hi there how may i help you", p));
					sleep(2000);
					p.setBusy(false);
					n.setBusy(false);
					String[] options2 = new String[] { "Could I buy a beer please?", "Not very busy in here today is it?" };
						p.setMenuHandler(new MenuHandler(options2) {
							public void handleReply(final int option, final String reply) {
								switch (option) {
									case 0: //yes
										owner.setBusy(true);
										n.setBusy(true);
										sleep(1500);
										owner.informOfNpcMessage(new ChatMessage(n, "Sure that will be 2 gold coins please", owner));
										sleep(2000);
										if(owner.getInventory().countId(10) >= 2) {
											owner.informOfChatMessage(new ChatMessage(owner, "Ok here you go thanks", n));
											sleep(2000);
											owner.getActionSender().sendMessage("You buy a beer");
											owner.getInventory().add(new InvItem(193, 1));
											owner.getInventory().remove(10, 2);
											owner.getActionSender().sendInventory();
											n.setBusy(false);
											owner.setBusy(false);
										} else {
											owner.getActionSender().sendMessage("You don't have enough coins to buy the beer");
											n.setBusy(false);
											owner.setBusy(false);
										}						
										break;
									case 1: // No
										owner.setBusy(true);
										n.setBusy(true);
										sleep(1500);
										owner.informOfNpcMessage(new ChatMessage(n, "No it was earlier", owner));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "There was a guy in here saying the goblins up by the mountain", owner));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "Are arguing again", owner));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "Of all things about the colour of their armour", owner));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "Knowing the goblins,it could easily turn into a full blown war", owner));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "Which wouldn't be good", owner));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "Goblin wars make such a mess of the countryside", owner));
										sleep(2000);
										owner.informOfChatMessage(new ChatMessage(owner, "Well if I have time I'll see if i can go and knock some sense into them", n));
										sleep(2000);
										owner.setQuestStage(getQuestId(), 1);
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
						p.getActionSender().sendMenu(options2);
				} 
				else if(p.getQuestStage(this) == -1) {
					sleep(2000);
					p.setBusy(false);
					n.setBusy(false);
					String[] options3 = new String[] { "Could I buy a beer please?", "I resolved the goblin problem" };
						p.setMenuHandler(new MenuHandler(options3) {
							public void handleReply(final int option, final String reply) {
								switch (option) {
									case 0: //yes
										owner.setBusy(true);
										n.setBusy(true);
										sleep(1500);
										owner.informOfNpcMessage(new ChatMessage(n, "Sure that will be 2 gold coins please", owner));
										sleep(2000);
										if(owner.getInventory().countId(10) >= 2) {
											owner.informOfChatMessage(new ChatMessage(owner, "Ok here you go thanks", n));
											sleep(2000);
											owner.getActionSender().sendMessage("You buy a beer");
											owner.getInventory().add(new InvItem(193, 1));
											owner.getInventory().remove(10, 2);
											owner.getActionSender().sendInventory();
											n.setBusy(false);
											owner.setBusy(false);
										} else {
											owner.getActionSender().sendMessage("You don't have enough coins to buy the beer");
											n.setBusy(false);
											owner.setBusy(false);
										}						
										break;
									case 1: // No
										owner.setBusy(true);
										n.setBusy(true);
										sleep(1500);
										owner.informOfNpcMessage(new ChatMessage(n, "Thanks lad", owner));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "Hard work like that deserver a beer", owner));
										sleep(2000);
										owner.getActionSender().sendMessage("The bartender hands you a free beer");
										sleep(2000);
										owner.getInventory().add(new InvItem(193, 1));
										owner.getActionSender().sendInventory();
										owner.informOfChatMessage(new ChatMessage(owner, "Cheers mate", n));
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
				else {
					sleep(2000);
					p.setBusy(false);
					n.setBusy(false);
					String[] options4 = new String[] { "Could I buy a beer please?", "I'm negotiating with the goblins at the momenty" };
						p.setMenuHandler(new MenuHandler(options4) {
							public void handleReply(final int option, final String reply) {
								switch (option) {
									case 0: //yes
										owner.setBusy(true);
										n.setBusy(true);
										sleep(1500);
										owner.informOfNpcMessage(new ChatMessage(n, "Sure that will be 2 gold coins please", owner));
										sleep(2000);
										if(owner.getInventory().countId(10) >= 2) {
											owner.informOfChatMessage(new ChatMessage(owner, "Ok here you go thanks", n));
											sleep(2000);
											owner.getActionSender().sendMessage("You buy a beer");
											owner.getInventory().add(new InvItem(193, 1));
											owner.getInventory().remove(10, 2);
											owner.getActionSender().sendInventory();
											n.setBusy(false);
											owner.setBusy(false);
										} else {
											owner.getActionSender().sendMessage("You don't have enough coins to buy the beer");
											n.setBusy(false);
											owner.setBusy(false);
										}						
										break;
									case 1: // No
										owner.setBusy(true);
										n.setBusy(true);
										sleep(1500);
										owner.informOfNpcMessage(new ChatMessage(n, "Well goodluck with that", owner));
										sleep(2000);
										owner.informOfNpcMessage(new ChatMessage(n, "If you sort it out, come back and I'll shout you a beer on the house", owner));
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
						p.getActionSender().sendMenu(options4);
				}
			}
	}

	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		if(n.getID() == 150 || n.getID() == 151 || n.getID() == 152) {
			return true;
		}
		return false;
	}
	
}
