package org.lupus_regnum.gs.plugins.quests;

import org.lupus_regnum.gs.external.EntityHandler;
import org.lupus_regnum.gs.model.ChatMessage;
import org.lupus_regnum.gs.model.InvItem;
import org.lupus_regnum.gs.model.MenuHandler;
import org.lupus_regnum.gs.model.Npc;
import org.lupus_regnum.gs.model.Player;
import org.lupus_regnum.gs.plugins.Quest;
import org.lupus_regnum.gs.plugins.listeners.action.TalkToNpcListener;
import org.lupus_regnum.gs.plugins.listeners.executive.TalkToNpcExecutiveListener;

public class ImpCatcher extends Quest implements TalkToNpcListener, TalkToNpcExecutiveListener {

	@Override
	public int getQuestId() {
		return 4;
	}

	@Override
	public String getQuestName() {
		return "Imp Catcher";
	}

	@Override
	public void onTalkToNpc(Player p, final Npc n) {
		if(n.getID() == 17) {
				if(p.getQuestStage(this) == 0) {
					n.setBusy(true);
					p.setBusy(true);
					System.out.println("Quest stage: " + 0);
					p.informOfNpcMessage(new ChatMessage(n, "...Avada kedavra!", p));
					p.setLastDamage(0);
					p.informOfModifiedHits(p);
					p.getActionSender().sendTeleBubble(n.getLocation().getX(), n.getLocation().getY(), false);
					p.getActionSender().sendSound("combat1a");
					sleep(1000);
					p.informOfChatMessage(new ChatMessage(p, "wow!!", n));
					p.setBusy(true);
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "What? Huh? Get out of the way you fool!", p));
					p.setBusy(true);
					sleep(2000);
					p.informOfChatMessage(new ChatMessage(p, "You shot me you stupid sod", n));
					p.setBusy(true);
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "Hmm, indeed, But it worked!", p));
					p.setBusy(true);
					sleep(2000);
					p.informOfChatMessage(new ChatMessage(p, "Yeah it did. You burnt my sleeve", n));
					p.setBusy(true);
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "Collateral damage. A necessary casualty", p));
					p.setBusy(true);
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "Your sleeve is expendable in this war", p));
					p.setBusy(true);
					sleep(2000);
					p.informOfChatMessage(new ChatMessage(p, "What war?", n));
					p.setBusy(true);
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "Well the war against those nasty little imps of course", p));
					p.setBusy(true);
					sleep(2000);
					p.informOfChatMessage(new ChatMessage(p, "Imps? What threat could they possibly pose?", n));
					p.setBusy(true);
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "Those little red demons stole my beads", p));
					p.setBusy(true);
					sleep(2000);
					p.informOfChatMessage(new ChatMessage(p, "Riight.. your beads. And what's so special about these beads?", n));
					p.setBusy(true);
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "Why, absolutely nothing. It's the principle of the matter.", p));
					p.setBusy(true);
					sleep(2000);
					p.informOfChatMessage(new ChatMessage(p, "Surely it can't be that hard to get them to give them back", n));
					p.setBusy(true);
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "Oh really? Why don't you try and get them back then", p));
					p.setBusy(true);
					sleep(2000);
					p.informOfChatMessage(new ChatMessage(p, "Yeah right, chase imps around for free. You're crazier than I thought", n));
					p.setBusy(true);
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "Free? Nonsense. I'd be happy to reward you.", p));
					p.setBusy(true);
					sleep(2000);
					p.informOfChatMessage(new ChatMessage(p, "Hmm. What kind of reward?", n));
					p.setBusy(true);
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "I've got a few valuable things lying around, I'll find something.", p));
					p.setBusy(true);
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "How about it? Would you help an old wizard out?", p));
					n.setBusy(false);
					p.setBusy(false);
					sleep(2000);
					String[] options = new String[] { "No way you crazy man", "Sure, why not" };
			    	p.setMenuHandler(new MenuHandler(options) {
				    	public void handleReply(final int option, final String reply) {
							switch (option) {
							case 1: // sure why not
								owner.setBusy(true);
								n.setBusy(true);
								sleep(1500);
								owner.informOfNpcMessage(new ChatMessage(n, "Excellent! Begin at once if you wish to collect them within a decade", owner));
								sleep(2000);
								owner.informOfNpcMessage(new ChatMessage(n, "There are four beads! Red, white, black, and yellow", owner));
								sleep(2000);
								owner.informOfChatMessage(new ChatMessage(owner, "Ahh you said... nevermind", n));
								sleep(2000);
								owner.informOfChatMessage(new ChatMessage(owner, "I'll come back when i get the beads", n));
								sleep(2000);
								owner.informOfNpcMessage(new ChatMessage(n, "If! If you get the beads. Don't underestimate the little beasts!", owner));
								sleep(2000);
								owner.informOfChatMessage(new ChatMessage(owner, "Sure, whatever old man.", n));
								sleep(2000);
								owner.setQuestStage(getQuestId(), 1);
								owner.informOfNpcMessage(new ChatMessage(n, "Tarantallegra!", owner));
								owner.setLastDamage(0);
								owner.informOfModifiedHits(owner);
								owner.getActionSender().sendTeleBubble(n.getLocation().getX(), n.getLocation().getY(), false);
								owner.getActionSender().sendSound("combat1a");
								sleep(2000);
								owner.informOfChatMessage(new ChatMessage(owner, "Ouch! Would you bloody wait til I'm gone!", n));
								owner.setBusy(false);
								n.setBusy(false);
								sleep(2000);
								owner.informOfNpcMessage(new ChatMessage(n, "Move it!", owner));
								break;
							case 0: //no
								owner.setBusy(true);
								n.setBusy(true);
								sleep(1500);
								owner.informOfNpcMessage(new ChatMessage(n, "Fine. But don't come to me when you need some pomegranate!", owner));
								sleep(2000);
								owner.setBusy(true);
								owner.informOfChatMessage(new ChatMessage(owner, "Okaaay...", n));
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
					p.informOfNpcMessage(new ChatMessage(n, "I know you. You're that fellow that stole my beads!", p));
					sleep(2000);
					p.informOfChatMessage(new ChatMessage(p, "What? No, I'm the one that offered to find them for you.", n));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "It makes no difference, I still don't like you.", p));
					sleep(2000);
					p.informOfNpcMessage(new ChatMessage(n, "But my beads, did you find them ?", p));
					sleep(1000);
					n.setBusy(false);
					p.setBusy(false);
					String[] options1 = new String[] { "Yeah I did", "Not yet" };
			    	p.setMenuHandler(new MenuHandler(options1) {
				    	public void handleReply(final int option, final String reply) {
							switch (option) {
							case 0: //yeah
								owner.setBusy(true);
								n.setBusy(true);
								sleep(1500);
								if (owner.getInventory().hasItemId(231) && owner.getInventory().hasItemId(232) && owner.getInventory().hasItemId(233) && owner.getInventory().hasItemId(234)) {
									owner.informOfNpcMessage(new ChatMessage(n, "Excellent! Hand them over immediately", owner));
									sleep(2000);
									owner.informOfChatMessage(new ChatMessage(owner, "Alright, calm down. I thought they were useless anyway", n));
									sleep(2000);
									owner.informOfNpcMessage(new ChatMessage(n, "Useless! Oh my dear " + (owner.isMale() ? "boy" : "girl") + ", they are quite important", owner));
									sleep(2000);
									owner.getActionSender().sendMessage("You hand over the beads");
									owner.getInventory().remove(231, 1);
									owner.getInventory().remove(232, 1);
									owner.getInventory().remove(233, 1);
									owner.getInventory().remove(234, 1);
									owner.getActionSender().sendInventory();
									owner.getActionSender().sendSound("click");
									sleep(1500);
									owner.informOfChatMessage(new ChatMessage(owner, "Alright, that's my half of the bargain.", n));
									sleep(2000);
									owner.informOfChatMessage(new ChatMessage(owner, "Now what of my reward?", n));
									sleep(2000);
									owner.informOfNpcMessage(new ChatMessage(n, "Ah, your reward. I found this! You can take it.", owner));
									sleep(2000);
									owner.getActionSender().sendMessage(EntityHandler.getNpcDef(17).getName() + " gives you one " + EntityHandler.getItemDef(235).getName());
									owner.getInventory().add(new InvItem(235, 1));
									owner.getActionSender().sendInventory();
									sleep(1500);
									owner.informOfChatMessage(new ChatMessage(owner, "What am I supposed to do with this?", n));
									sleep(2000);
									owner.informOfNpcMessage(new ChatMessage(n, "I don't know, whatever you like. I certainly don't want it", owner));
									sleep(2000);
									owner.informOfNpcMessage(new ChatMessage(n, "And have a read of this book", owner));
									sleep(2000);
									owner.informOfNpcMessage(new ChatMessage(n, "It'll teach you a thing or two about magic!", owner));
									sleep(2000);
									owner.getInventory().remove(owner.getInventory().getLastIndexById(235));
									owner.getActionSender().sendInventory();
									owner.incExp(6, 350, false, false, false);
									owner.getActionSender().sendStat(6);
									owner.informOfNpcMessage(new ChatMessage(n, "Now I have work to do! Leave my tower!", owner));
									owner.setLastDamage(0);
									owner.informOfModifiedHits(owner);
									owner.getActionSender().sendTeleBubble(n.getLocation().getX(), n.getLocation().getY(), false);
									owner.getActionSender().sendSound("combat1a");
									sleep(1500);
									owner.informOfChatMessage(new ChatMessage(owner, "Damnit! Gladly...", n));
									owner.setQuestStage(getQuestId(), -1);
									n.setBusy(false);
									owner.setBusy(false);
								} else {
									n.setBusy(true);
									owner.setBusy(true);
									owner.informOfNpcMessage(new ChatMessage(n, "Well what are you doing here then? Move it!", owner));
									owner.setLastDamage(0);
									owner.informOfModifiedHits(owner);
									owner.getActionSender().sendTeleBubble(n.getLocation().getX(), n.getLocation().getY(), false);
									owner.getActionSender().sendSound("combat1a");
									sleep(2000);
									owner.informOfChatMessage(new ChatMessage(owner, "Would you bugger off with the spells you mad fool!", n));
									n.setBusy(false);
									owner.setBusy(false);
								}
								break;
							case 1: // not yet
								n.setBusy(true);
								owner.setBusy(true);
								sleep(1500);
								owner.informOfNpcMessage(new ChatMessage(n, "Well what are you doing here then? Move it!", owner));
								owner.setLastDamage(0);
								owner.informOfModifiedHits(owner);
								owner.getActionSender().sendTeleBubble(n.getLocation().getX(), n.getLocation().getY(), false);
								owner.getActionSender().sendSound("combat1a");
								sleep(2000);
								owner.informOfChatMessage(new ChatMessage(owner, "Would you bugger off with the spells you mad fool!", n));
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
					p.getActionSender().sendMenu(options1);
				}
		}
	}

	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		if(n.getID() == 17) {
			return true;
		}
		return false;
	}

}
