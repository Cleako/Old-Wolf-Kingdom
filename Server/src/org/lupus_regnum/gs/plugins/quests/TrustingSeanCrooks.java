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

public class TrustingSeanCrooks extends Quest implements 
    TalkToNpcListener, 
    TalkToNpcExecutiveListener, 
    ObjectActionListener, 
    InvUseOnObjectListener, 
    InvUseOnObjectExecutiveListener, 
    ObjectActionExecutiveListener {

    @Override
    public int getQuestId() {
        return 12;
    }

    @Override
    public String getQuestName() {
	return "Trusting Sean Crooks";
    }
	
    @Override
    public void onTalkToNpc(Player p, final Npc n) {
        if(n.getID() == 782) { //Sean Crooks			
            if(p.getQuestStage(this) == 0) {
                n.setBusy(true);
                p.setBusy(true);
                System.out.println("Quest stage: " + 0);

                /*
                 * Our beloved heroes begin their venture arriving in Mystenhaven, 
                 * the last great outpost between the badlands and the ocean beyond.
                 * 
                 * Standing beside a pile of sacks beside the general store in the 
                 * wilderness fort, Mystenhaven, is a child named Sean Crooks. 
                 */
                
                p.informOfChatMessage(new ChatMessage(p, "Hello, are you okay?", n));
		sleep(2000);
                p.informOfNpcMessage(new ChatMessage(n, "War is comin! Join da ninja gang!", p));
                sleep(2000);
                p.informOfNpcMessage(new ChatMessage(n, "Hey, i hav a problem. ", p));
                sleep(2000);
                p.informOfNpcMessage(new ChatMessage(n, "I hav friends and they are starting to form a gang.", p));
		sleep(3000);
                p.informOfNpcMessage(new ChatMessage(n, "I never asked 2 join it but they signed me up anywai", p));
                sleep(3000);
                p.informOfNpcMessage(new ChatMessage(n, "and we sadly r scheduled too fight in 1 week.", p));
		sleep(3000);
                p.informOfNpcMessage(new ChatMessage(n, "Man, u see how weak I iz, Im ded! ", p));
                sleep(2000);
                p.informOfNpcMessage(new ChatMessage(n, "Buhsides, I dedant ask tuh fight, I wuz pulled in!", p));
                sleep(4000);		
                p.informOfNpcMessage(new ChatMessage(n, "O i forgot, dey made me sign a controct, ", p));
                sleep(2000);
                p.informOfNpcMessage(new ChatMessage(n, "wat do i do?", p));
		sleep(2000);
                p.informOfNpcMessage(new ChatMessage(n, "We do haff mor members den dey do, so", p));
                sleep(2000);
                p.informOfNpcMessage(new ChatMessage(n, "ders a better pussibility off winning some kills.", p));
                sleep(3000);
                p.informOfNpcMessage(new ChatMessage(n, "I signed a contract and ef i brake it, iz it wrong?", p));
		sleep(2000);
                n.setBusy(false);
                p.setBusy(false);
		String[] options = new String[] { "I do not associate with "
                        + "children. Go away!", "This child gang stuff is "
                        + "not really my scene..." };
		p.setMenuHandler(new MenuHandler(options) {
                    @Override
		public void handleReply(final int option, final String reply) {
                    switch (option) {
		       	case 0: // Quit quest
                            n.setBusy(true);
                            owner.setBusy(true);
                            sleep(1500);
                            owner.informOfNpcMessage(new ChatMessage(n, "Nooo!! Plz halp!", owner));                            
                            sleep(2000);
                            owner.setBusy(false);
                            n.setBusy(false);
                            break;
                        case 1: //Agree to help, continue quest
                            owner.setBusy(true);
                            n.setBusy(true);
                            sleep(1500);
                            owner.informOfNpcMessage(new ChatMessage(n, "Pmfg! We need u 2 fite n da gang war!", owner));
                            sleep(2000);
                            owner.informOfNpcMessage(new ChatMessage(n, "We gotta beet up .D.A.R n cleako! He sux!", owner));
                            sleep(4000);
                            owner.informOfChatMessage(new ChatMessage(owner, "Cleako? The ruthless leader of DAR CORP,", n));
                            sleep(3000);
                            owner.informOfChatMessage(new ChatMessage(owner, "the most prominent merchant association in the five kingdoms?", n));
                            sleep(3000);
                            owner.informOfNpcMessage(new ChatMessage(n, "YEA!!1 I h9 dat n00b.", owner));
                            sleep(2000);
                            owner.informOfNpcMessage(new ChatMessage(n, "He had .D.A.R destruhy mah clan n smash our castle.", owner));
                            sleep(3000);
                            owner.informOfNpcMessage(new ChatMessage(n, "Mayb my gang can get big enuf 2 take revange!", owner));
                            sleep(3000);
                            owner.informOfChatMessage(new ChatMessage(owner, "You probably should not say that as loud as you are.", n));
                            sleep(3000);
                            owner.informOfChatMessage(new ChatMessage(owner, "I would be cautious of being overheard by one of his Dragon Highlords.", n));
                            sleep(4000);
                            owner.informOfChatMessage(new ChatMessage(owner, "I hear they spare no mercy if you even look at them wrong.", n));
                            sleep(3000);
                            owner.informOfNpcMessage(new ChatMessage(n, "Ya I threw gravel at 1 of dem n he took out hiz sword.", owner));
                            sleep(3000);
                            owner.informOfNpcMessage(new ChatMessage(n, "I got scard n ran away...", owner));
                            sleep(2000);
                            owner.informOfChatMessage(new ChatMessage(owner, "Well look, I don't like seeing the little guys suffer", n));
                            sleep(3000);
                            owner.informOfChatMessage(new ChatMessage(owner, "and you look pretty scrawny.", n));
                            sleep(2000);
                            owner.informOfNpcMessage(new ChatMessage(n, "Yup, dats me. So, will u join da ninja gang?", owner));
                            sleep(2000);
                            String[] options2 = new String[] { "I think I left the kettle on the stove. "
                                    + "Good luck with your gang!", "Sure, tell me what I need to do." };
                            owner.setMenuHandler(new MenuHandler(options2) {
                            @Override
				public void handleReply(final int option, final String reply) {
                                    switch (option) {
                                    case 0: //Run away, quit quest
                                    	sleep(1500);
					owner.informOfNpcMessage(new ChatMessage(n, "Huh? w8 plz dun go..", owner));
                                        n.setBusy(false);
					owner.setBusy(false);
					break;
                                    case 1: // Yes, continue quest
					owner.setBusy(true);
					n.setBusy(true);
					sleep(1500);
                                        owner.informOfNpcMessage(new ChatMessage(n, "Gimme 3k gold first plz! I neeeeeed it!", owner));
					sleep(2000);
					owner.setBusy(false);
					n.setBusy(false);
					String[] options3 = new String[] { "Sorry, I don't have that much.", "Here you go." };
					owner.setMenuHandler(new MenuHandler(options3) {
                                            public void handleReply(final int option, final String reply) {
						switch (option) {
                                                    case 0: // Don't give money to the noob
							owner.setBusy(true);
							n.setBusy(true);
							sleep(1500);
							owner.informOfNpcMessage(new ChatMessage(n, "I need 3k gp or u cant b in da gang!", owner));
							n.setBusy(false);
							owner.setBusy(false);
							break;
                                                    case 1: // Give money to the noob
                                                        
							if (owner.getInventory().hasItemId(10) && owner.getInventory().countId(10) >= 3000) {
								owner.informOfNpcMessage(new ChatMessage(n, "Gimmie!!", owner));
								sleep(2000);
								owner.getActionSender().sendMessage("You hand over the gold");
								owner.getInventory().remove(10, 3000);
								owner.getActionSender().sendInventory();
                                                                owner.getActionSender().sendSound("click");
                                                                sleep(1500);
                                                                owner.setBusy(true);
                                                                n.setBusy(true);
                                                                sleep(1500);
                                                                owner.informOfNpcMessage(new ChatMessage(n, "Nao we haz enuf monies to fund our war!", owner));
                                                                sleep(2000);
                                                                owner.informOfNpcMessage(new ChatMessage(n, "But we need stuffz to fight wit!", owner));
                                                                sleep(2000);
                                                                owner.informOfNpcMessage(new ChatMessage(n, "Welz, we ar gunna ned wood fer catapults", owner));
                                                                sleep(2000);
                                                                owner.informOfNpcMessage(new ChatMessage(n, "and stonz fer armore.", owner));
                                                                sleep(2000);
                                                                owner.informOfNpcMessage(new ChatMessage(n, "Gimme 10 logz, 5 cooper ores and 5 tin ores.", owner));
                                                                sleep(2000);
                                                                owner.informOfChatMessage(new ChatMessage(owner, "You got it boss", n));
                                                                owner.setQuestStage(getQuestId(), 1);
                                                                n.setBusy(false);
                                                                owner.setBusy(false);
                                                        } 
                                                        else {
								n.setBusy(true);
								owner.setBusy(true);
								owner.informOfNpcMessage(new ChatMessage(n, "Nuh dats not enuf. I need 3k gp!", owner));
								n.setBusy(false);
								owner.setBusy(false);
							}
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
                    }
		}
    	});
	p.getActionSender().sendMenu(options);
        }
        else if(p.getQuestStage(this) == 1 
                && p.getInventory().hasItemId(14)
                && p.getInventory().countId(14) >= 10 
                && p.getInventory().hasItemId(202)
                && p.getInventory().countId(202) >= 5
                && p.getInventory().hasItemId(150)
                && p.getInventory().countId(150) >= 5
                ) {
            
            n.setBusy(true);
            p.setBusy(true);
            p.informOfNpcMessage(new ChatMessage(n, "Giv it alz to mah naow!", p));
            sleep(2000);
            p.getActionSender().sendMessage("You hand over the items");
            for (int i = 0; i < 10; i++)
                    p.getInventory().remove(14, 1);
            for (int i = 0; i < 5; i++)
                    p.getInventory().remove(202, 1);
            for (int i = 0; i < 5; i++)
                    p.getInventory().remove(150, 1);
            p.getActionSender().sendInventory();
            p.getActionSender().sendSound("click");
            sleep(1500);
            p.informOfNpcMessage(new ChatMessage(n, "W00t nao we r rich!", p));
            sleep(2000);
            p.informOfNpcMessage(new ChatMessage(n, "Otay so dis kid named TinTin iz gonna b our fighter.", p));
            sleep(3000);
            p.informOfNpcMessage(new ChatMessage(n, "He lives in da city 2 da south", p));
            sleep(2000);
            p.informOfNpcMessage(new ChatMessage(n, "I need u 2 tall hem we iz redy 2 attak da DAR castle!", p));
            sleep(3000);
            p.informOfChatMessage(new ChatMessage(p, "Do you have anyone else that can fight?", n));
            sleep(3000);
            p.informOfNpcMessage(new ChatMessage(n, "wot? No we r all n00bs n r wai 2 week 2 fite dem.", p));
            sleep(3000);
            p.informOfNpcMessage(new ChatMessage(n, "Mayb u could halp! plz plz plz!", p));
            sleep(3000);
            p.informOfChatMessage(new ChatMessage(p, "I'll get back to you on that", n));
            sleep(2000);
            p.setQuestStage(getQuestId(), 2);
            p.setBusy(false);
            n.setBusy(false);
            return;
        }
        else if(p.getQuestStage(this) == 1) {
            n.setBusy(true);
            p.setBusy(true);
            p.informOfNpcMessage(new ChatMessage(n, "Datz tutolly not enuf, u nedz to get moa r!", p));
            sleep(3000);
            p.informOfNpcMessage(new ChatMessage(n, "Wamember i seadz 10 logz, 5 cooper orez, and 5 tin orez!!!", p));
            sleep(2000);
            p.setBusy(false);
            n.setBusy(false);			
        }
        else if(p.getQuestStage(this) == 2) {
            n.setBusy(true);
            p.setBusy(true);
            p.informOfNpcMessage(new ChatMessage(n, "Halp us fite da gang war! We got ur bak!", p));
            sleep(3000);
            p.informOfChatMessage(new ChatMessage(p, "So you need me to tell tintin that the two ", n));
            sleep(3000);
            p.informOfChatMessage(new ChatMessage(p, "of us shall make the attack on the .D.A.R castle, right? ", n));
            sleep(3000);
            p.informOfChatMessage(new ChatMessage(p, "When should we start the gang war?", n));
            sleep(3000);
            p.informOfNpcMessage(new ChatMessage(n, "Ya. Umm idk.. ", p));
            sleep(1000);
            p.informOfNpcMessage(new ChatMessage(n, "U go attak n when we hear dem yellin we will all", p));
            sleep(3000);
            p.informOfNpcMessage(new ChatMessage(n, "com 2 da castle n fite too, cuz we got ur bak!", p));
            sleep(3000);
            p.informOfChatMessage(new ChatMessage(p, "Yeah.. whatever you say, boss.", n));
            sleep(2000);
            p.informOfNpcMessage(new ChatMessage(n, "O n can i hav 300gp?", p));
            sleep(2000);
            p.informOfChatMessage(new ChatMessage(p, "Sorry, I'm broke", n));
            sleep(2000);
            p.informOfNpcMessage(new ChatMessage(n, "Dats fine, i wuz just seein if u wud giv it 2 me.", p));
            sleep(3000);
            p.informOfChatMessage(new ChatMessage(p, "Okay, I think I might have to punch something. Gotta go!", n));
            sleep(3000);
            p.informOfNpcMessage(new ChatMessage(n, "O ya, herz da key 2 tintin's house!", p));
            sleep(3000);
            p.getInventory().add(new InvItem(392, 1));
            p.getActionSender().sendInventory();
            p.setQuestStage(getQuestId(), 3);
            p.setBusy(false);
            n.setBusy(false);
	}
	else if(p.getQuestStage(this) == -1) {
            p.setBusy(true);
            n.blockedBy(p);
            p.informOfNpcMessage(new ChatMessage(n, "Go awai n00b!", p));
            sleep(2000);
            n.unblock();
	}
    } 
        /*
         * Our hero, having grown sick and tired of the idiot noob, is having doubts about Sean 
         * Crooks' plan. Our hero must now walk to <CITYNAME> and find TinTin. He is 
         * located in a house with several kids running around. The door is locked and 
         * only the yellow key allows entry.
         * 
         * Due to the need to instance this part of the quest, opening the door will 
         * teleport the player to an identical house with blackness surrounding it with 
         * TinTin standing inside. The key vanishes when used on the door for exiting 
         * after talking to TinTin.
         */
                
        else if(n.getID() == 783) { // Here we introduce TinTin!
            if(p.getQuestStage(this) == 3) {
		p.setBusy(true);
		n.setBusy(true);
		p.informOfChatMessage(new ChatMessage(p, "Hello, are you TinTin?", n));
                sleep(3000);
                p.informOfNpcMessage(new ChatMessage(n, "Chea, wuts it 2 u?", p));
		sleep(3000);
                p.informOfChatMessage(new ChatMessage(p, "Your pal, Sean Crooks, asked me to tell you he was ready", n));
                sleep(3000);
                p.informOfChatMessage(new ChatMessage(p, "to have the gang war begin and stated that you and I", n));
                sleep(3000);
                p.informOfChatMessage(new ChatMessage(p, "would start the fight and then he and the others", n));
                sleep(3000);
                p.informOfChatMessage(new ChatMessage(p, "would join once they heard the commotion.", n));
                sleep(3000);
                p.informOfNpcMessage(new ChatMessage(n, "That n00b sux at directions.", p));
		sleep(2000);
                p.informOfNpcMessage(new ChatMessage(n, "Idk if he will be the gang leader soon. Nobody listens to him.", p));
		sleep(3000);
                p.informOfChatMessage(new ChatMessage(p, "I agree. I am not sure about this whole ordeal myself.", n));
                sleep(3000);
                p.informOfChatMessage(new ChatMessage(p, "It seems like we are about to poke a tiger.", n));
                sleep(3000);
                p.informOfNpcMessage(new ChatMessage(n, "Yeah but hes my friend and I trust him with my life.", p));
		sleep(3000);
                p.informOfChatMessage(new ChatMessage(p, "Okay, so are you ready to go?", n));
                sleep(3000);
                p.informOfNpcMessage(new ChatMessage(n, "I will meet you there. I have some unfinished business here first.", p));
		sleep(3000);
                p.informOfChatMessage(new ChatMessage(p, "Goodbye", n));
                p.setBusy(false);
		n.setBusy(false);
		sleep(1500);
		/*String[] options4 = new String[] { "Father Aereck sent me to talk to you", "Well that's friendly", "I've come to repossess your house" };
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
		p.getActionSender().sendMenu(options4);*/
                } 
		else if(p.getQuestStage(this) == 0 || p.getQuestStage(this) == 2) {
			p.setBusy(true);
			n.setBusy(true);
			p.informOfNpcMessage(new ChatMessage(n, "Go away, I'm meditating", p));
			sleep(2000);
			p.setBusy(false);
			n.setBusy(false);
						/*String[] options14 = new String[] { "Well that's friendly", "I've come to repossess your house" };
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
						p.getActionSender().sendMenu(options14);*/
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
