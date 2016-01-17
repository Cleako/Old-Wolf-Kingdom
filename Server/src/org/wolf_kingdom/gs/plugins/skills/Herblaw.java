package org.wolf_kingdom.gs.plugins.skills;

import org.wolf_kingdom.gs.event.MiniEvent;
import org.wolf_kingdom.gs.external.EntityHandler;
import org.wolf_kingdom.gs.external.ItemHerbDef;
import org.wolf_kingdom.gs.external.ItemHerbSecond;
import org.wolf_kingdom.gs.external.ItemUnIdentHerbDef;
import org.wolf_kingdom.gs.model.InvItem;
import org.wolf_kingdom.gs.model.Player;
import org.wolf_kingdom.gs.model.World;
import org.wolf_kingdom.gs.plugins.listeners.action.InvActionListener;
import org.wolf_kingdom.gs.plugins.listeners.action.InvUseOnItemListener;

public class Herblaw implements InvActionListener, InvUseOnItemListener {

	@Override
	public void onInvAction(final InvItem item, Player player) {
		if (item.getDef().getCommand().equalsIgnoreCase("identify")) {
			handleHerbCleanup(item, player);
		}
	}

	private boolean handleHerbCleanup(final InvItem item, Player player) {

	    ItemUnIdentHerbDef herb = item.getUnIdentHerbDef();
	    if (herb == null) {
	    	return false;
	    }
	    if (player.getMaxStat(15) < herb.getLevelRequired()) {
			player.getActionSender().sendMessage("Your herblaw ability is not high enough to clean this herb.");
			return true;
	    }
	    player.setBusy(true);
	    World.getWorld().getDelayedEventHandler().add(new MiniEvent(player) {
			public void action() {
			    ItemUnIdentHerbDef herb = item.getUnIdentHerbDef();
			    InvItem newItem = new InvItem(herb.getNewId());
			    owner.getInventory().remove(item);
			    owner.getInventory().add(newItem);
			    owner.getActionSender().sendMessage("You clean the mud off the " + newItem.getDef().getName() + ".");
			    owner.incExp(15, herb.getExp(), true);
			    owner.getActionSender().sendStat(15);
			    owner.getActionSender().sendInventory();
			    owner.setBusy(false);
			    if(owner.getInventory().hasItemId(item.getID())) {
			    	handleHerbCleanup(item, owner);
			    }
			    return;
			}
	    });
	    return true;
	}

	@Override
	public void onInvUseOnItem(Player player, InvItem item1, InvItem item2) {
		ItemHerbSecond secondDef = null;
		if ((secondDef = EntityHandler.getItemHerbSecond(item1.getID(), item2.getID())) != null && doHerbSecond(player, item1, item2, secondDef)) {
		    return;
		} 
		else if ((secondDef = EntityHandler.getItemHerbSecond(item2.getID(), item1.getID())) != null && doHerbSecond(player, item2, item1, secondDef)) {
		    return;
		}
		else if(item1.getID() == 468 && doGrind(player, item1, item2)) {
		    return;
		}
		else if(item2.getID() == 468 && doGrind(player, item2, item1)) {
		    return;
		} 
		else if (item1.getID() == 464 && doHerblaw(player, item1, item2)) {
		    return;
		} 
		else if (item2.getID() == 464 && doHerblaw(player, item2, item1)) {
		    return;
		}
		// 1 dose on 2 dose str = 3 dose
		else if (item1.getID() == 224 && item2.getID() == 223 || item1.getID() == 223 && item2.getID() == 224) {
		    if (player.getInventory().remove(new InvItem(224)) > -1 && player.getInventory().remove(new InvItem(223)) > -1) {
			player.getInventory().add(new InvItem(222));
			player.getActionSender().sendMessage("You mix the strength potions");
			player.getActionSender().sendInventory();
			return;
		    }
		}
		// 1 dose on 3 dose = 4 dose
		else if (item1.getID() == 224 && item2.getID() == 222 || item1.getID() == 222 && item2.getID() == 224) {
		    if (player.getInventory().remove(new InvItem(224)) > -1 && player.getInventory().remove(new InvItem(222)) > -1) {
			player.getInventory().add(new InvItem(221));
			player.getActionSender().sendMessage("You mix the strength potions");
			player.getActionSender().sendInventory();
			return;
		    }
		}
		// 2 dose on 2 dose = 4 dose
		else if (item1.getID() == 223 && item2.getID() == 223) {
		    if (player.getInventory().remove(new InvItem(223)) > -1 && player.getInventory().remove(new InvItem(223)) > -1) {
			player.getInventory().add(new InvItem(221));
			player.getActionSender().sendMessage("You mix the strength potions");
			player.getActionSender().sendInventory();
			return;
		    }
		}
		// 2 dose on 2 dose = 4 dose
		else if (item1.getID() == 224 && item2.getID() == 224) {
		    if (player.getInventory().remove(new InvItem(224)) > -1 && player.getInventory().remove(new InvItem(224)) > -1) {
			player.getInventory().add(new InvItem(223));
			player.getActionSender().sendMessage("You mix the strength potions");
			player.getActionSender().sendInventory();
			return;
		    }
		}
		else {
			int[][] combinePotions = { { 475, 476, 474 }, // Attack potions.
				    { 478, 479, 477 }, // Stat restore potions
				    { 481, 482, 480 }, // Defense potions
				    { 484, 485, 483 }, // Prayer potion
				    { 487, 488, 486 }, // SAP
				    { 490, 491, 489 }, // Fishing potion
				    { 493, 494, 492 }, // SSP
				    { 496, 497, 495 }, // SDP
				    { 499, 500, 498 } // Range pot
			    };

		    for (int i = 0; i < combinePotions.length; i++) {
				if ((item1.getID() == combinePotions[i][0] && item2.getID() == combinePotions[i][1]) || (item2.getID() == combinePotions[i][0] && item1.getID() == combinePotions[i][1])) {
				    if (player.getInventory().remove(new InvItem(combinePotions[i][0])) > -1 && player.getInventory().remove(new InvItem(combinePotions[i][1])) > -1) {
						player.getInventory().add(new InvItem(combinePotions[i][2]));
						player.getActionSender().sendInventory();
						player.getActionSender().sendMessage("You combine the Potions");
						return;
					}
				}
				else if (item1.getID() == combinePotions[i][1] && item2.getID() == combinePotions[i][1]) {
				    if (player.getInventory().remove(new InvItem(combinePotions[i][1])) > -1 && player.getInventory().remove(new InvItem(combinePotions[i][1])) > -1) {
						player.getInventory().add(new InvItem(combinePotions[i][0]));
						player.getActionSender().sendInventory();
						player.getActionSender().sendMessage("You combine the Potions");
						return;
					    } 
				    else if (item1.getID() == combinePotions[i][0] && item2.getID() == combinePotions[i][0]) {
						if (player.getInventory().remove(new InvItem(combinePotions[i][0])) > -1 && player.getInventory().remove(new InvItem(combinePotions[i][0])) > -1) {
						    player.getInventory().add(new InvItem(combinePotions[i][2]));
						    player.getInventory().add(new InvItem(combinePotions[i][1]));
						    player.getActionSender().sendInventory();
						    player.getActionSender().sendMessage("You combine the Potions");
						    return;
						}
				    }
				}
		    }
		}
	}
	private boolean doHerblaw(Player player, final InvItem vial, final InvItem herb) {
		final ItemHerbDef herbDef = EntityHandler.getItemHerbDef(herb.getID());
		if (herbDef == null) {
		    return false;
		}
		if (player.getCurStat(15) < herbDef.getReqLevel()) {
		    player.getActionSender().sendMessage("You need a herblaw level of " + herbDef.getReqLevel() + " to mix those.");
		    return true;
		}
		World.getWorld().getDelayedEventHandler().add(new MiniEvent(player) {
		    public void action() {
		    	if (owner.getInventory().remove(vial) > -1 && owner.getInventory().remove(herb) > -1) {
				    owner.getActionSender().sendMessage("You add the " + herb.getDef().getName() + " to the water");
				    owner.getInventory().add(new InvItem(herbDef.getPotionId(), 1));
				    //owner.incExp(15, herbDef.getExp(), true);
				    //owner.getActionSender().sendStat(15);
				    owner.getActionSender().sendInventory();
				}
		    	if(owner.getInventory().contains(herb) && owner.getInventory().contains(vial)) {
		    		doHerblaw(owner, vial, herb);
		    	}
		    }
		});
		return true;
	}
	private boolean doHerbSecond(Player player, final InvItem second, final InvItem unfinished, final ItemHerbSecond def) {
		if (unfinished.getID() != def.getUnfinishedID()) {
			return false;
		}
		if (player.getCurStat(15) < def.getReqLevel()) {
			player.getActionSender().sendMessage("You need a herblaw level of " + def.getReqLevel() + " to mix those");
			return true;
		}
		if (player.getInventory().remove(second) > -1 && player.getInventory().remove(unfinished) > -1) {
			player.getActionSender().sendMessage("You mix the " + second.getDef().getName() + " with the " + unfinished.getDef().getName());
			player.getInventory().add(new InvItem(def.getPotionID(), 1));
			player.incExp(15, def.getExp() * 2, true);
			player.getActionSender().sendStat(15);
			player.getActionSender().sendInventory();
	    	if(player.getInventory().contains(second) && player.getInventory().contains(unfinished)) {
	    		doHerbSecond(player, second, unfinished, def);
	    	}
			return true;
		}
		return false;
		
	}
	
	private boolean doGrind(Player player, final InvItem mortar, final InvItem item) {
		int newID;
		switch (item.getID()) {
			case 466: // Unicorn Horn
			    newID = 473;
			    break;
			case 467: // Blue dragon scale
			    newID = 472;
			    break;
			default:
			    return false;
		}
		if (player.getInventory().remove(item) > -1) {
			player.getActionSender().sendMessage("You grind up the " + item.getDef().getName());
			player.getInventory().add(new InvItem(newID, 1));
			player.getActionSender().sendInventory();
		}
		return true;
	}
}
