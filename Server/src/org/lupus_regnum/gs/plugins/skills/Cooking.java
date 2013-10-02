package org.lupus_regnum.gs.plugins.skills;

import java.util.Arrays;

import org.lupus_regnum.config.Formulae;
import org.lupus_regnum.gs.event.ShortEvent;
import org.lupus_regnum.gs.external.ItemCookingDef;
import org.lupus_regnum.gs.model.Bubble;
import org.lupus_regnum.gs.model.GameObject;
import org.lupus_regnum.gs.model.InvItem;
import org.lupus_regnum.gs.model.MenuHandler;
import org.lupus_regnum.gs.model.Player;
import org.lupus_regnum.gs.model.World;
import org.lupus_regnum.gs.plugins.listeners.action.InvUseOnItemListener;
import org.lupus_regnum.gs.plugins.listeners.action.InvUseOnObjectListener;

public class Cooking implements InvUseOnObjectListener, InvUseOnItemListener {

	static int[] ids;
	static {
		ids = new int[] { 97, 11, 119, 274, 435, 491  };
		Arrays.sort(ids);
	}
	@Override
	public void onInvUseOnObject(GameObject object, InvItem item, Player owner) {
		if(Arrays.binarySearch(ids, object.getID()) >= 0) {
			handleCooking(item, owner, object);
			return;
		}
		return;
	}
	private void handleCooking(final InvItem item, Player owner, final GameObject object) {
    	 if (item.getID() == 622) { // Seaweed (Glass)
 			owner.setBusy(true);
 			showBubble(owner, item);
 			owner.getActionSender().sendSound("cooking");
 			owner.getActionSender().sendMessage("You put the seaweed on the  " + object.getGameObjectDef().getName() + ".");
 			World.getWorld().getDelayedEventHandler().add(new ShortEvent(owner) {
 			    public void action() {
	 				if (owner.getInventory().remove(item) > -1) {
	 				    owner.getActionSender().sendMessage("The seaweed burns to ashes");
	 				    owner.getInventory().add(new InvItem(624, 1));
	 				    owner.getActionSender().sendInventory();
	 				}
	 				if(owner.getInventory().hasItemId(item.getID())) {
	 					handleCooking(item, owner, object);
	 				}
	 				owner.setBusy(false);
 			    }
 			});
    	 } 
    	 else {
    		 final ItemCookingDef cookingDef = item.getCookingDef();
    		 if (cookingDef == null) {
 			 	owner.getActionSender().sendMessage("Nothing interesting happens.");
 			 	return;
    		 }
    		 if (owner.getCurStat(7) < cookingDef.getReqLevel()) {
    			 owner.getActionSender().sendMessage("You need a cooking level of " + cookingDef.getReqLevel() + " to cook this.");
    			 return;
    		 }
    		 if (!owner.withinRange(object, 1))
    		    return;
    		 owner.setBusy(true);
    		 showBubble(owner, item);
    		 owner.getActionSender().sendSound("cooking");
    		 owner.getActionSender().sendMessage("You cook the " + item.getDef().getName() + " on the " + object.getGameObjectDef().getName() + ".");
    		 World.getWorld().getDelayedEventHandler().add(new ShortEvent(owner) {
    			 public void action() {
    				 InvItem cookedFood = new InvItem(cookingDef.getCookedId());
    				 if (owner.getInventory().remove(item) > -1) {
    					 if (!Formulae.burnFood(item.getID(), owner.getCurStat(7))) {
    						 owner.getInventory().add(cookedFood);
    						 owner.getActionSender().sendMessage("The " + item.getDef().getName() + " is now nicely cooked.");
    						 owner.incExp(7, cookingDef.getExp(), true);
    						 owner.getActionSender().sendStat(7);
    					 } 
    					 else {
    						 owner.getInventory().add(new InvItem(cookingDef.getBurnedId()));
    						 owner.getActionSender().sendMessage("You accidently burn the " + item.getDef().getName() + ".");
    					 }
    					 owner.getActionSender().sendInventory();
    				 }
    				 owner.setBusy(false);
    				 if(owner.getInventory().hasItemId(item.getID())) {
	 					handleCooking(item, owner, object);
	 				}
	 				
	    		}
	 		});
	    }
	}
	
    private void showBubble(Player owner, InvItem item) {
		Bubble bubble = new Bubble(owner, item.getID());
		for (Player p : owner.getViewArea().getPlayersSectorA()) {
		    p.informOfBubble(bubble);
		}
                for (Player p : owner.getViewArea().getPlayersSectorB()) {
		    p.informOfBubble(bubble);
		}
                for (Player p : owner.getViewArea().getPlayersSectorC()) {
		    p.informOfBubble(bubble);
		}
                for (Player p : owner.getViewArea().getPlayersSectorD()) {
		    p.informOfBubble(bubble);
		}
    }
	@Override
	public void onInvUseOnItem(Player player, InvItem item1, InvItem item2) {
		// dish ingred id - uncooked dish id
		int[][] stuff = { { 252, 254 }, { 132, 255 }, { 236, 256 } };
		if (item1.getID() == 253 || item2.getID() == 253) {
		    for (int i = 0; i < stuff.length; i++) {
				if (stuff[i][0] == item1.getID() || item2.getID() == stuff[i][0]) {
				    if (player.getInventory().remove(new InvItem(253)) > -1 && player.getInventory().remove(new InvItem(stuff[i][0])) > -1) {
						player.getInventory().add(new InvItem(stuff[i][1]));
						player.getActionSender().sendMessage("You create an uncooked pie!");
						player.getActionSender().sendInventory();
						return;
				    }
				}
		    }
		}
		if (item1.getID() == 132 && item2.getID() == 342 || item1.getID() == 342 && item2.getID() == 132) {
		    if (player.getInventory().remove(new InvItem(342)) > -1 && player.getInventory().remove(new InvItem(132)) > -1) {
				player.getInventory().add(new InvItem(344));
				player.getActionSender().sendMessage("You start to create a stew");
				player.getActionSender().sendInventory();
				return;
		    }
		}

		if (item1.getID() == 348 && item2.getID() == 342 || item1.getID() == 342 && item2.getID() == 348) {
		    if (player.getInventory().remove(new InvItem(342)) > -1 && player.getInventory().remove(new InvItem(348)) > -1) {
				player.getInventory().add(new InvItem(343));
				player.getActionSender().sendMessage("You start to create a stew");
				player.getActionSender().sendInventory();
				return;
		    }
		}

		if (item1.getID() == 132 && item2.getID() == 343 || item1.getID() == 343 && item2.getID() == 132) {
		    if (player.getInventory().remove(new InvItem(343)) > -1 && player.getInventory().remove(new InvItem(132)) > -1) {
				player.getInventory().add(new InvItem(345));
				player.getActionSender().sendMessage("Your stew is now ready, but uncooked");
				player.getActionSender().sendInventory();
				return;
		    }
		}

		if (item1.getID() == 348 && item2.getID() == 344 || item1.getID() == 344 && item2.getID() == 348) {
		    if (player.getInventory().remove(new InvItem(344)) > -1 && player.getInventory().remove(new InvItem(348)) > -1) {
				player.getInventory().add(new InvItem(345));
				player.getActionSender().sendMessage("our stew is now ready, but uncooked");
				player.getActionSender().sendInventory();
				return;
		    }
		}

		if (item1.getID() == 337 && item2.getID() == 330 || item1.getID() == 330 && item2.getID() == 337) {
		    if (player.getInventory().remove(new InvItem(337)) > -1 && player.getInventory().remove(new InvItem(330)) > -1) {
				player.getInventory().add(new InvItem(332));
				player.getActionSender().sendMessage("You add chocolate to the cake");
				player.getActionSender().sendInventory();
				return;
		    }
		}

		int egg = 19;
		int milk = 22;
		int flour = 136;

		if (item1.getID() == 338 || item2.getID() == 338) {
		    if (player.getInventory().countId(egg) > -1 && player.getInventory().countId(milk) > -1 && player.getInventory().countId(flour) > -1) {
				if (player.getInventory().remove(new InvItem(egg)) > -1 && player.getInventory().remove(new InvItem(milk)) > -1 && player.getInventory().remove(new InvItem(flour)) > -1 && player.getInventory().remove(new InvItem(338)) > -1) {
				    player.getInventory().add(new InvItem(135));
				    player.getInventory().add(new InvItem(339));
				    player.getActionSender().sendInventory();
				    player.getActionSender().sendMessage("You create an uncooked cake");
				    return;
				}
		    }
		}
		if (item1.getID() == 250 && item2.getID() == 251 || item1.getID() == 251 && item2.getID() == 250) {
		    if (player.getInventory().remove(new InvItem(251)) > -1 && player.getInventory().remove(new InvItem(250)) > -1) {
			player.getInventory().add(new InvItem(253));
			player.getActionSender().sendMessage("You add the pastry dough in the dish");
			player.getActionSender().sendInventory();
			return;
		    }
		}
		if (item1.getID() == 143 && item2.getID() == 141 || item1.getID() == 141 && item2.getID() == 143) {
			if (player.getCurStat(7) < 35) {
				player.getActionSender().sendMessage("You need level 35 cooking to do this");
				return;
			}
			if (player.getInventory().remove(new InvItem(141)) > -1 && player.getInventory().remove(new InvItem(143)) > -1) {
				int rand = Formulae.Rand(0, 4);
				if (rand == 2) {
				    player.incExp(7, 55, true);
				    player.getInventory().add(new InvItem(180));
				    player.getActionSender().sendMessage("You mix the grapes, and accidentally create Bad wine!");
				}
				else {
				    player.incExp(7, 110, true);
				    player.getInventory().add(new InvItem(142));
				    player.getActionSender().sendMessage("You mix the grapes with the water and create wine!");
				}
				player.getActionSender().sendStat(7);
				player.getActionSender().sendInventory();
				return;
			}
		}
		if (item1.getID() == 50 && item2.getID() == 136 || item1.getID() == 136 && item2.getID() == 50) {
		    player.getActionSender().sendMessage("What would you like to make?");
		    String[] optionsz = new String[] { "Bread Dough", "Pizza Base", "Pastry Dough" };
		    player.setMenuHandler(new MenuHandler(optionsz) {
				public void handleReply(final int option, final String reply) {
				    int newid = 0;
				    if (option == 0) {
				    	newid = 137;
				    } 
				    else if (option == 1) {
				    	newid = 321;
				    }
				    else if (option == 2) {
				    	newid = 250;
				    } 
				    else {
				    	return;
				    }
				    if (owner.getInventory().remove(new InvItem(50)) > -1 && owner.getInventory().remove(new InvItem(136)) > -1) {
						owner.getActionSender().sendMessage("You create a " + reply);
						owner.getInventory().add(new InvItem(21));
						owner.getInventory().add(new InvItem(135));
						owner.getInventory().add(new InvItem(newid));
						owner.getActionSender().sendInventory();
				    }
				}
		    });
		    player.getActionSender().sendMenu(optionsz);
		    return;
		}
		if (item1.getID() == 320 && item2.getID() == 321 || item1.getID() == 321 && item2.getID() == 320) {
		    if (player.getInventory().remove(new InvItem(321)) > -1 && player.getInventory().remove(new InvItem(320)) > -1) {
			player.getInventory().add(new InvItem(323));
			player.getActionSender().sendMessage("You add the Tomato to the Pizza base");
			player.getActionSender().sendInventory();
			return;
		    }
		}
		if (item1.getID() == 319 && item2.getID() == 323 || item1.getID() == 323 && item2.getID() == 319) {
		    if (player.getInventory().remove(new InvItem(323)) > -1 && player.getInventory().remove(new InvItem(319)) > -1) {
			player.getInventory().add(new InvItem(324));
			player.getActionSender().sendMessage("You add Cheese on the Unfinished Pizza");
			player.getActionSender().sendInventory();
			return;
		    }
		}
		if (item1.getID() == 325 && item2.getID() == 352 || item1.getID() == 352 && item2.getID() == 325) {
		    if (player.getCurStat(7) > 54) {
				if (player.getInventory().remove(new InvItem(352)) > -1 && player.getInventory().remove(new InvItem(325)) > -1) {
				    player.getInventory().add(new InvItem(327));
				    player.incExp(7, 110, true);
				    player.getActionSender().sendStat(7);
				    player.getActionSender().sendMessage("You create an Anchovie Pizza.");
				    player.getActionSender().sendInventory();
				    return;
				}
		    } 
		    else {
				player.getActionSender().sendMessage("You need a cooking level of 55 to do this");
				return;
		    }
		}
		if (item1.getID() == 325 && item2.getID() == 132 || item1.getID() == 132 && item2.getID() == 325) {
		    if (player.getCurStat(7) > 44) {
				if (player.getInventory().remove(new InvItem(132)) > -1 && player.getInventory().remove(new InvItem(325)) > -1) {
				    player.getInventory().add(new InvItem(326));
				    player.incExp(7, 110, true);
				    player.getActionSender().sendStat(7);
				    player.getActionSender().sendMessage("You create a Meat Pizza.");
				    player.getActionSender().sendInventory();
				    return;
				}
		    } 
		    else {
				player.getActionSender().sendMessage("You need a cooking level of 44 to do this");
				return;
		    }
		}
		if ((item1.getID() == 23 && item2.getID() == 135) || (item2.getID() == 23 && item1.getID() == 135)) {
			if (player.getInventory().remove(new InvItem(23)) > -1 && player.getInventory().remove(new InvItem(135)) > -1) {
				player.getInventory().add(new InvItem(136));
				player.getActionSender().sendInventory();
				player.getActionSender().sendMessage("You pour the flour into the pot.");
				return;
		    }
		} 
		return;
	}
}
