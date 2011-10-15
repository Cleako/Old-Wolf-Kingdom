package org.lupus_regnum.gs.plugins.quests;

import org.lupus_regnum.gs.model.ChatMessage;
import org.lupus_regnum.gs.model.InvItem;
import org.lupus_regnum.gs.model.MenuHandler;
import org.lupus_regnum.gs.model.Npc;
import org.lupus_regnum.gs.model.Player;
import org.lupus_regnum.gs.plugins.Quest;
import org.lupus_regnum.gs.plugins.listeners.action.TalkToNpcListener;
import org.lupus_regnum.gs.plugins.listeners.executive.TalkToNpcExecutiveListener;

public class RomeoAndJuliet extends Quest implements TalkToNpcListener, TalkToNpcExecutiveListener {

	@Override
	public int getQuestId() {
		return 6;
	}

	@Override
	public String getQuestName() {
		return "Romeo and Juliet";
	}
	@Override
	public void onTalkToNpc(Player p, final Npc n) {
		if(n.getID() == 30) {
				if(p.getQuestStage(this) == 0) {
					n.setBusy(true);
					p.setBusy(true);
					System.out.println("Quest stage: " + 0);
					p.informOfNpcMessage(new ChatMessage(n, "Juliet, Juliet, Juliet! Wherefore Art thou?", p));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "Kind friend, Have you seen Juliet?", p));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "Her and her Father seem to have disappeared", p));
					sleep(2000);
					n.setBusy(false);
					p.setBusy(false);
					String[] options = new String[] { "Yes, I have seen her", "No, but that's girls for you ", "Can I help find her for you?" };
			    	p.setMenuHandler(new MenuHandler(options) {
				    	public void handleReply(final int option, final String reply) {
				    		switch (option) {
					        	case 0: // Yes
									n.setBusy(true);
									owner.setBusy(true);
									sleep(1500);
									owner.informOfChatMessage(new ChatMessage(owner, "I think it was her. Blond, stressed", n));
									sleep(2000);
									owner.informOfNpcMessage(new ChatMessage(n, "Yes, that sounds like her", owner));
									sleep(2000);
									owner.informOfNpcMessage(new ChatMessage(n, "Please tell her I long to be with her", owner));
									sleep(2000);
									n.setBusy(false);
									owner.setBusy(false);
										String[] options2 = new String[] { "Yes, I will tell her", "Sorry I am too busy. Maybe later?" };
											owner.setMenuHandler(new MenuHandler(options2) {
												public void handleReply(final int option, final String reply) {
													switch (option) {
														case 0: //yes
															n.setBusy(true);
															owner.setBusy(true);
															sleep(1500);
															owner.setBusy(true);
															owner.informOfNpcMessage(new ChatMessage(n, "You are the saviour of my heard, thank you", owner));
															sleep(2000);
															owner.informOfNpcMessage(new ChatMessage(n, "Err, yes. Ok. Thats... nice.", owner));
															owner.setQuestStage(getQuestId(), 1);
															n.setBusy(false);
															owner.setBusy(false);
															break;
														case 1: // No
															n.setBusy(true);
															owner.setBusy(true);
															sleep(1500);
															owner.informOfNpcMessage(new ChatMessage(n, "Well if you do find her, I would be most grateful", owner));
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
							case 1: //
								owner.setBusy(true);
								n.setBusy(true);
								sleep(1500);
								owner.informOfNpcMessage(new ChatMessage(n, "Not my dear Juliet.", owner));
								sleep(2000);
								owner.informOfNpcMessage(new ChatMessage(n, "Could you find her for me?", owner));
								sleep(2000);
								owner.informOfNpcMessage(new ChatMessage(n, "Please tell her I long to be with her", owner));
								sleep(2000);
								n.setBusy(false);
								owner.setBusy(false);
								String[] options5 = new String[] { "Yes, I will tell her how you feel", "I can't, it sounds like work for me" };
									owner.setMenuHandler(new MenuHandler(options5) {
										public void handleReply(final int option, final String reply) {
											switch (option) {
												case 0: // yes
													n.setBusy(true);
													owner.setBusy(true);
													sleep(1500);
													owner.informOfNpcMessage(new ChatMessage(n, "You are the saviour of my heard, thank you", owner));
													sleep(2000);
													owner.informOfNpcMessage(new ChatMessage(n, "Err, yes. Ok. Thats... nice.", owner));
													owner.setQuestStage(getQuestId(), 1);
													owner.setBusy(false);
													n.setBusy(false);
													break;
												case 1: //no
													owner.setBusy(true);
													n.setBusy(true);
													sleep(1500);
													owner.informOfNpcMessage(new ChatMessage(n, "Well, I guess you are not the romantic type", owner));
													sleep(2000);
													owner.informOfNpcMessage(new ChatMessage(n, "Goodbye", owner));
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
							case 2: //start quest
								n.setBusy(true);
								owner.setBusy(true);
								sleep(1500);
								owner.informOfNpcMessage(new ChatMessage(n, "Oh would you? That would be wonderful!", owner));
								sleep(2000);
								owner.informOfNpcMessage(new ChatMessage(n, "Please tell her I long to be with her", owner));
								sleep(2000);
								owner.informOfChatMessage(new ChatMessage(owner, "Yes, I will tell her how you feel", n));
								sleep(2000);
								owner.informOfNpcMessage(new ChatMessage(n, "You are the saviour of my heard, thank you", owner));
								sleep(2000);
								owner.informOfNpcMessage(new ChatMessage(n, "Err, yes. Ok. Thats... nice.", owner));
								owner.setQuestStage(getQuestId(), 1);
								owner.setBusy(false);
								n.setBusy(false);
								break;
						}
					}
				});
				p.getActionSender().sendMenu(options);
			} 
			else if(p.getQuestStage(this) == 1) {
				p.informOfNpcMessage(new ChatMessage(n, "Please find my Juliet. I am so, so sad", p));
				p.setBusy(false);
				n.setBusy(false);			
			} 
			else if(p.getQuestStage(this) == 2) {
				p.setBusy(true);
				p.informOfChatMessage(new ChatMessage(p, "Romeo, I have a message from Juliet", n));
				sleep(1000);
				p.getActionSender().sendMessage("You pass Juliet's message to Romeo");
				p.getInventory().remove(p.getInventory().getLastIndexById(56));
				p.getActionSender().sendInventory();
				sleep(2500);
				p.informOfNpcMessage(new ChatMessage(n, "Tragic news. Her father is opposing our marriage", p));
				sleep(2000);
				p.informOfNpcMessage(new ChatMessage(n, "If her father sees me, he will kill me", p));
				sleep(2000);
				p.informOfNpcMessage(new ChatMessage(n, "I dare not got near his lands", p));
				sleep(2000);
				p.informOfNpcMessage(new ChatMessage(n, "She says Father Lawrence can help us", p));
				sleep(2000);
				p.informOfNpcMessage(new ChatMessage(n, "Please find him for me. Tell him of our plight", p));
				sleep(1000);
				p.setQuestStage(getQuestId(), 3);
				p.setBusy(false);
				n.setBusy(false);						
			} 
			else if(p.getQuestStage(this) >= 3 && p.getQuestStage(this) < 7) {
				p.setBusy(true);
				p.informOfNpcMessage(new ChatMessage(n, "Please find Father Lawrence, he can help us", p));
				p.setBusy(false);
			} 
			else if(p.getQuestStage(this) == 7) {
				p.setBusy(true);
				n.setBusy(true);
				p.informOfChatMessage(new ChatMessage(p, "Romeo, its all set. Juliet has the potion", n));
				sleep(2000);
				p.informOfNpcMessage(new ChatMessage(n, "Ah right", p));
				sleep(2000);
				p.informOfNpcMessage(new ChatMessage(n, "What potion would that be then", p));
				sleep(2000);
				p.informOfChatMessage(new ChatMessage(p, "The one to get her to the crypt", n));
				sleep(2000);
				p.informOfNpcMessage(new ChatMessage(n, "Ah right", p));
				sleep(2000);
				p.informOfNpcMessage(new ChatMessage(n, "So she is dead then. Ah thats a shame", p));
				sleep(2000);
				p.informOfNpcMessage(new ChatMessage(n, "Thanks for your help anyway", p));
				sleep(1000);
				p.setQuestStage(this, -1);
				p.setBusy(false);
				n.setBusy(false);
			} 
			else if(p.getQuestStage(this) == -1) {
				p.setBusy(true);
				n.setBusy(true);
				p.informOfNpcMessage(new ChatMessage(n, "I heard Juliet had died. Terrible business", p));
				sleep(2000);
				p.informOfNpcMessage(new ChatMessage(n, "Her cousin and i are getting on well though", p));
				sleep(2000);
				p.informOfNpcMessage(new ChatMessage(n, "Thanks for your help", p));
				p.setBusy(false);
				n.setBusy(false);
			}
	} 
	else if(n.getID() == 31) {
			if(p.getQuestStage(this) == 0) {
				p.setBusy(true);
				n.setBusy(true);
				p.informOfNpcMessage(new ChatMessage(n, "Romeo, Romeo, wherefore art thou Romeo", p));
				sleep(2000);
				p.informOfNpcMessage(new ChatMessage(n, "Bold adventurer, have you seen Romeo on your travels?", p));
				sleep(2000);
				p.informOfNpcMessage(new ChatMessage(n, "Skinyy guy, a bit wishy washy, head full of poetry", p));
				sleep(500);
				n.setBusy(false);
				p.setBusy(false);
				String[] options4 = new String[] { "yes, I have met him", "No, I think I would have remembered if i had", "I guess i could find him", "I think you could do better" };
					p.setMenuHandler(new MenuHandler(options4) {
						public void handleReply(final int option, final String reply) {
							switch (option) {
								case 0:
									owner.setBusy(true);
									n.setBusy(true);
									sleep(1500);
									owner.informOfChatMessage(new ChatMessage(owner, "He has his good points", n));
									sleep(2000);
									owner.informOfNpcMessage(new ChatMessage(n, "He dosen't spend all day on the internet, at least", owner));
									owner.setBusy(false);
									n.setBusy(false);
									break;
								case 2:
									owner.setBusy(true);
									n.setBusy(true);
									sleep(1500);
									owner.informOfNpcMessage(new ChatMessage(n, "That is most kind of you", owner));
									sleep(2000);
									owner.informOfNpcMessage(new ChatMessage(n, "Could you please deliver a messsage to him", owner));
									sleep(2000);
									owner.informOfChatMessage(new ChatMessage(owner, "Certinly, I will deliver your message straight away", n));
									sleep(2000);
									owner.informOfNpcMessage(new ChatMessage(n, "It may be our only hope", owner));
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
					p.getActionSender().sendMenu(options4);
			} 
			else if(p.getQuestStage(this) == 1) {
				p.setBusy(true);
				n.setBusy(true);
				p.informOfChatMessage(new ChatMessage(p, "Juliet, I come from Romeo", n));
				sleep(2000);
				p.informOfChatMessage(new ChatMessage(p, "He begs me tell you he cares still", n));
				sleep(2000);
				p.informOfNpcMessage(new ChatMessage(n, "Please, Take this message to him", p));
				sleep(2000);
				p.informOfChatMessage(new ChatMessage(p, "Certinly, I will deliver your message straight away", n));
				sleep(2000);
				p.informOfNpcMessage(new ChatMessage(n, "It may be our only hope", p));
				p.getActionSender().sendMessage("Juliet gives you a message");
				p.getInventory().add(new InvItem(56));
				p.getActionSender().sendInventory();
				sleep(1000);
				p.setQuestStage(getQuestId(), 2);
				p.setBusy(false);
				n.setBusy(false);
			}
			else if(p.getQuestStage(this) == 2) {
				p.setBusy(true);
				n.setBusy(true);
				p.informOfNpcMessage(new ChatMessage(n, "Please, deliver the message to Romeo with all speed", p));
				p.setBusy(false);
				n.setBusy(false);
			} 
			else if(p.getQuestStage(this) == 3) {
				p.setBusy(true);
				n.setBusy(true);
				p.informOfNpcMessage(new ChatMessage(n, "I have heard you have to find Father Lawrence", p));
				sleep(2000);
				p.informOfNpcMessage(new ChatMessage(n, "Please help us brave adventurer", p));
				p.setBusy(false);
				n.setBusy(false);
			} 
			else if(p.getQuestStage(this) == 4) {
				p.setBusy(true);
				n.setBusy(true);
				p.informOfNpcMessage(new ChatMessage(n, "I think you have some things to talk with The Apothecary", p));
				sleep(2000);
				p.informOfNpcMessage(new ChatMessage(n, "We really need your help", p));
				p.setBusy(false);
				n.setBusy(false);
			} 
			else if(p.getQuestStage(this) == 6) {
				p.setBusy(true);
				n.setBusy(true);
				p.informOfChatMessage(new ChatMessage(p, "I have a potion from Father Lawrence", n));
				sleep(2000);
				p.informOfChatMessage(new ChatMessage(p, "It should make you seem dead, and get you away from this place", n));
				sleep(500);
				p.getActionSender().sendMessage("You pass potion to Juliet");
				p.getInventory().remove(p.getInventory().getLastIndexById(57));
				p.getActionSender().sendInventory();
				sleep(2500);
				p.informOfNpcMessage(new ChatMessage(n, "I just hope Romeo can remember to get me from the Crypt", p));
				sleep(2000);
				p.informOfNpcMessage(new ChatMessage(n, "Many thanks kind friend", p));
				sleep(2000);
				p.informOfNpcMessage(new ChatMessage(n, "Please go to Romeo, make sure he understands", p));
				sleep(2000);
				p.informOfNpcMessage(new ChatMessage(n, "He can be a bit dense sometimes", p));
				sleep(1000);
				p.setQuestStage(getQuestId(), 7);
				p.setBusy(false);
				n.setBusy(false);		
			} 
			else if(p.getQuestStage(this) == 7) {
				p.setBusy(true);
				n.setBusy(true);
				p.informOfNpcMessage(new ChatMessage(n, "Have you seen Romeo? He will reward you for your help", p));
				sleep(2000);
				p.informOfNpcMessage(new ChatMessage(n, "He is the wealth in this story", p));
				sleep(2000);
				p.informOfNpcMessage(new ChatMessage(n, "I am just the glamour", p));
				p.setBusy(false);
				n.setBusy(false);			
			} 
			else if(p.getQuestStage(this) == -1) {
				p.setBusy(true);
				n.setBusy(true);
				p.informOfNpcMessage(new ChatMessage(n, "I sat in that cold crypt for ages waiting for Romeo", p));
				sleep(2000);
				p.informOfNpcMessage(new ChatMessage(n, "That useless fool never showed up", p));
				sleep(2000);
				p.informOfNpcMessage(new ChatMessage(n, "And all i got was indigestion. I am done with men like him", p));
				sleep(2000);
				p.informOfNpcMessage(new ChatMessage(n, "Now go away before i call my father", p));
				p.setBusy(false);
				n.setBusy(false);	
			}
	} 
	else if(n.getID() == 32) {
			if(p.getQuestStage(this) == 0) {
				p.setBusy(true);
				n.setBusy(true);
				p.informOfNpcMessage(new ChatMessage(n, "Hello adventurer, do you seek a quest", p));
				sleep(2000);
				n.setBusy(false);
				p.setBusy(false);
				String[] options3 = new String[] { "I'm always looking for a quest", "No, I prefer just to kill things", "Can you recommend a good bar" };
					p.setMenuHandler(new MenuHandler(options3) {
						public void handleReply(final int option, final String reply) {
							switch (option) {
								case 0:
									owner.setBusy(true);
									n.setBusy(true);
									sleep(1500);
									owner.informOfNpcMessage(new ChatMessage(n, "Well, I see poor Romeo wandering around the square. I think he may need help", owner));
									sleep(1500);
									owner.informOfNpcMessage(new ChatMessage(n, "I was helping him and Juliet to meet, but it became impossible", owner));
									sleep(1500);
									owner.informOfNpcMessage(new ChatMessage(n, "I am sure he can use some help", owner));
									owner.setBusy(false);
									n.setBusy(false);
									break;
								case 1:
									sleep(1500);
									owner.setBusy(true);
									n.setBusy(true);
									owner.informOfNpcMessage(new ChatMessage(n, "That's fine career in these lands", owner));
									sleep(2000);
									owner.informOfNpcMessage(new ChatMessage(n, "There is more that needs killing everyday", owner));
									owner.setBusy(false);
									n.setBusy(false);
									break;
								case 2:
									owner.setBusy(true);
									n.setBusy(true);
									sleep(1500);
									owner.informOfNpcMessage(new ChatMessage(n, "Drinking will be the death of you", owner));
									sleep(2000);
									owner.informOfNpcMessage(new ChatMessage(n, "But the Blue Moon in the city is cheap enough", owner));
									sleep(2000);
									owner.informOfNpcMessage(new ChatMessage(n, "And providing you but one drink and hour they let you stay all night", owner));
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
			else if(p.getQuestStage(this) == 3) {
				p.setBusy(true);
				n.setBusy(true);
				p.informOfChatMessage(new ChatMessage(p, "Romeo sent me. He says you can help", n));
				sleep(2000);
				p.informOfNpcMessage(new ChatMessage(n, "Ah Romeo, yes. A fine lad, but a little bit confused", p));
				sleep(2000);
				p.informOfChatMessage(new ChatMessage(p, "Juliet must be rescued from her fathers control", n));
				sleep(2000);
				p.informOfNpcMessage(new ChatMessage(n, "I know just the things. A potion to make her appear dead", p));
				sleep(2000);
				p.informOfNpcMessage(new ChatMessage(n, "Then Romeo can collect her from the crypt", p));
				sleep(2000);
				p.informOfNpcMessage(new ChatMessage(n, "Go to the Apothecary, tell him I sent you", p));
				sleep(2000);
				p.informOfNpcMessage(new ChatMessage(n, "You need some Cadava Potion", p));
				sleep(1000);
				p.setQuestStage(getQuestId(), 4);
				p.setBusy(false);
				n.setBusy(false);		
			} 
			else if(p.getQuestStage(this) == 4) {
				p.setBusy(true);
				p.informOfNpcMessage(new ChatMessage(n, "Ah, have you found the Apothecary yet?", p));
				sleep(2000);
				p.informOfNpcMessage(new ChatMessage(n, "Remember, Cadava potion, for Father Lawrence", p));
				p.setBusy(false);
			} 
			else if(p.getQuestStage(this) == 5) {
				p.setBusy(true);
				n.setBusy(true);
				p.informOfNpcMessage(new ChatMessage(n, "Did you find the Apothecary", p));
				sleep(2000);
				p.informOfChatMessage(new ChatMessage(p, "Yes, I must find some berries", n));
				sleep(2000);
				p.informOfNpcMessage(new ChatMessage(n, "Well, take care. They are poisonous so the touch", p));
				sleep(2000);
				p.informOfNpcMessage(new ChatMessage(n, "You will need gloves", p));
				sleep(2000);
				p.informOfChatMessage(new ChatMessage(p, "I am on my way back to him with the ingredients", n));
				sleep(2000);
				p.informOfNpcMessage(new ChatMessage(n, "Good work. Get the potion to Juliey whn you have it", p));
				sleep(2000);
				p.informOfNpcMessage(new ChatMessage(n, "I will tell Romeo to be ready", p));
				p.setBusy(false);
				n.setBusy(false);		
			} 
			else if(p.getQuestStage(this) == 6) {
				p.setBusy(true);
				n.setBusy(true);
				p.informOfChatMessage(new ChatMessage(p, "Everything I could do is done now,", n));
				sleep(2000);
				p.informOfChatMessage(new ChatMessage(p, "everything else depends on Romeo and Juliet", n));
				sleep(2000);
				p.informOfNpcMessage(new ChatMessage(n, "I hope they will find their luck!", p));
				p.setBusy(false);
				n.setBusy(false);	
			} 
			else if(p.getQuestStage(this) == -1) {
				p.setBusy(true);
				n.setBusy(true);
				p.informOfNpcMessage(new ChatMessage(n, "Oh to be a father in the times of whiskey", p));
				sleep(2000);
				p.informOfNpcMessage(new ChatMessage(n, "I sing and I drink and i wake up in gutters", p));
				sleep(2000);
				p.informOfNpcMessage(new ChatMessage(n, "Top of the morning to you", p));
				sleep(2000);
				p.informOfNpcMessage(new ChatMessage(n, "To err is human, to forgive, quite difficult", p));
				p.setBusy(false);
				n.setBusy(false);
			}
	} 
	else if(n.getID() == 33) {
			if(p.getQuestStage(this) == 4) {
				p.setBusy(true);
				n.setBusy(true);
				p.informOfChatMessage(new ChatMessage(p, "Apothecary. Father Lawrence sent me", n));
				sleep(2000);
				p.informOfChatMessage(new ChatMessage(p, "I need some Cadava potion to help Romeo and Juliet", n));
				sleep(2000);
				p.informOfNpcMessage(new ChatMessage(n, "Cadava potion. Its pretty nasty. And hard to make", p));
				sleep(2000);
				p.informOfNpcMessage(new ChatMessage(n, "Wing of Rat, Tail of frog. Ear of snake and horn of dog", p));
				sleep(2000);
				p.informOfNpcMessage(new ChatMessage(n, "I have all that, but i need some cadavaberries", p));
				sleep(2000);
				p.informOfNpcMessage(new ChatMessage(n, "You will have to find them while i get the rest ready", p));
				sleep(2000);
				p.informOfNpcMessage(new ChatMessage(n, "Bring them here when you have them. But be careful. They are nasty", p));
				sleep(1000);
				p.setQuestStage(getQuestId(), 5);
				p.setBusy(false);
				n.setBusy(false);
			} 
			else if(p.getQuestStage(this) == 5) {
				if(!p.getInventory().hasItemId(55)) {
					p.informOfNpcMessage(new ChatMessage(n, "Keep searching for the berries", p));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "they are needed for the potion", p));
					p.setBusy(false);
				} 
				else {
					p.setBusy(true);
					n.setBusy(true);
					p.informOfNpcMessage(new ChatMessage(n, "Well done. You have the berries", p));
					sleep(500);
					p.getActionSender().sendMessage("You hand over the berries");
					p.getInventory().remove(p.getInventory().getLastIndexById(55));
					p.getActionSender().sendInventory();
					sleep(1000);
					p.getActionSender().sendMessage("Which the apothecary shakes up in vial of strange liquid");
					sleep(1000);
					p.informOfNpcMessage(new ChatMessage(n, "Here is what you need", p));
					p.getActionSender().sendMessage("The apothecary gives you a Cadava potion");
					p.getInventory().add(new InvItem(57));
					p.getActionSender().sendInventory();
					p.getActionSender().sendMessage("I'm meant to give this to Juliet");
					p.setQuestStage(this, 6);
					p.setBusy(false);
					n.setBusy(false);
				}	
			}
	}
}
			
	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		if(n.getID() == 30 || n.getID() == 31 || n.getID() == 32 || n.getID() == 33) {
			return true;
		}
		return false;
	}
	
}
