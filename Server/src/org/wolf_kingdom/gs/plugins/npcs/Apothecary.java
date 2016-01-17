package org.wolf_kingdom.gs.plugins.npcs;

import org.wolf_kingdom.gs.event.ShortEvent;
import org.wolf_kingdom.gs.model.ChatMessage;
import org.wolf_kingdom.gs.model.InvItem;
import org.wolf_kingdom.gs.model.MenuHandler;
import org.wolf_kingdom.gs.model.Npc;
import org.wolf_kingdom.gs.model.Player;
import org.wolf_kingdom.gs.model.World;
import org.wolf_kingdom.gs.plugins.listeners.action.TalkToNpcListener;

public class Apothecary implements TalkToNpcListener {
	
	public World world = World.getWorld();

    String[] names = { "Do you have a potion to make my hair fall out?", "I am in need of a strength potion", "No thanks" };

    @Override
	public void onTalkToNpc(Player player, final Npc npc) {
    	if(npc.getID() != 33) {
    		return;
    	}
		if(player.getQuestStage(World.getWorld().getQuest(6)) == 5 || player.getQuestStage(World.getWorld().getQuest(6)) == 4) {
			return;
		}
		player.setBusy(false);
    	player.informOfNpcMessage(new ChatMessage(npc, "Hello, can i help you?", player));
    	world.getDelayedEventHandler().add(new ShortEvent(player) {
    	    public void action() {

    		owner.setMenuHandler(new MenuHandler(names) {
    		    public void handleReply(final int option, final String reply) {
    			if (option < 0 && option > names.length)
    			    return;

    			if (option == 0) {
    			    owner.informOfChatMessage(new ChatMessage(owner, reply, npc));
    			    world.getDelayedEventHandler().add(new ShortEvent(owner) {
    				public void action() {
    				    owner.informOfNpcMessage(new ChatMessage(npc, "Here you are, enjoy this", owner));
    				    world.getDelayedEventHandler().add(new ShortEvent(owner) {
    					public void action() {
    					    owner.getActionSender().sendMessage("Apothecary hands you a mysterious potion");
    					    owner.getInventory().add(new InvItem(58));
    					    owner.getActionSender().sendInventory();
    					    owner.setBusy(false);
    					    npc.setBusy(false);
    					    npc.unblock();
    					    return;
    					}
    				    });
    				}
    			    });

    			} else if (option == 1) {

    			    owner.informOfChatMessage(new ChatMessage(owner, reply, npc));
    			    world.getDelayedEventHandler().add(new ShortEvent(owner) {
    				public void action() {
    				    owner.informOfNpcMessage(new ChatMessage(npc, "Bring me a limpwurt root and a spider egg and i will make you one", owner));
    				    world.getDelayedEventHandler().add(new ShortEvent(owner) {
    					public void action() {
    					    String[] s = { "I have the ingredients", "Ok ill find them." };
    					    owner.setMenuHandler(new MenuHandler(s) {
    						public void handleReply(final int option, final String reply) {
    						    if (option == 0) {
    							owner.informOfChatMessage(new ChatMessage(owner, reply, npc));
    							world.getDelayedEventHandler().add(new ShortEvent(owner) {
    							    public void action() {
    								if (owner.getInventory().countId(220) < 1 || owner.getInventory().countId(219) < 1) {
    								    owner.informOfNpcMessage(new ChatMessage(npc, "It seems you don't have everything i asked for, come back later.", owner));
    								    owner.setBusy(false);
    								    npc.setBusy(false);
    								    npc.unblock();
    								    return;
    								} else {
    								    if (owner.getInventory().remove(220, 1) > -1 && owner.getInventory().remove(219, 1) > -1) {
    									owner.getInventory().add(new InvItem(221));
    									owner.getActionSender().sendMessage("Apothecary hands you a Strength Potion (4 dose)");
    									owner.getActionSender().sendInventory();
    									owner.setBusy(false);
    									npc.setBusy(false);
    									npc.unblock();
    									return;
    								    }
    								}
    							    }
    							});
    						    } else {
    							owner.setBusy(false);
    							npc.setBusy(false);
    							npc.unblock();
    						    }
    						}
    					    });
    					    owner.getActionSender().sendMenu(s);
    					}
    				    });
    				}
    			    });

    			} else {
    			    owner.setBusy(false);
    			    npc.setBusy(false);
    			    npc.unblock();
    			}

    		    }
    		});
    		owner.getActionSender().sendMenu(names);
    	    }
    	});	
	}
}
