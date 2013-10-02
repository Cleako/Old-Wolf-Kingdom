package org.lupus_regnum.gs.plugins.skills;

import org.lupus_regnum.config.Formulae;
import org.lupus_regnum.gs.event.MiniEvent;
import org.lupus_regnum.gs.event.ShortEvent;
import org.lupus_regnum.gs.external.EntityHandler;
import org.lupus_regnum.gs.external.ItemCraftingDef;
import org.lupus_regnum.gs.external.ItemGemDef;
import org.lupus_regnum.gs.model.Bubble;
import org.lupus_regnum.gs.model.GameObject;
import org.lupus_regnum.gs.model.InvItem;
import org.lupus_regnum.gs.model.MenuHandler;
import org.lupus_regnum.gs.model.Player;
import org.lupus_regnum.gs.model.World;
import org.lupus_regnum.gs.plugins.listeners.action.InvUseOnItemListener;
import org.lupus_regnum.gs.plugins.listeners.action.InvUseOnObjectListener;
import org.lupus_regnum.gs.tools.DataConversions;

public class Crafting implements InvUseOnItemListener, InvUseOnObjectListener {
	
    /**
     * World instance
     */
    public static final World world = World.getWorld();

	@Override
	public void onInvUseOnObject(GameObject obj, final InvItem item, Player owner) {
		switch(obj.getID()) {
			case 118:
			case 813: // Furnace
				if(item.getID() == 172) { // Gold Bar (Crafting)
					world.getDelayedEventHandler().add(new MiniEvent(owner) {
						public void action() {
							owner.getActionSender().sendMessage("What would you like to make?");
							String[] options = new String[]{"Ring", "Necklace", "Amulet"};
							owner.setMenuHandler(new MenuHandler(options) {
							    public void handleReply(int option, String reply) {
									if(owner.isBusy() || option < 0 || option > 2) {
									    return;
									}
									final int[] moulds = {293, 295, 294};
									final int[] gems = {-1, 164, 163, 162, 161, 523};
									String[] options = {"Gold", "Sapphire", "Emerald", "Ruby", "Diamond", "Dragonstone"};
									final int craftType = option;
									if(owner.getInventory().countId(moulds[craftType]) < 1) {
									    owner.getActionSender().sendMessage("You need a " + EntityHandler.getItemDef(moulds[craftType]).getName() + " to make a " + reply);
									    return;
									}
									owner.getActionSender().sendMessage("What type of " + reply + " would you like to make?");
									owner.setMenuHandler(new MenuHandler(options) {
									    public void handleReply(int option, String reply) {
											if(owner.isBusy() || option < 0 || option > 5) {
											    return;
											}
											if(option != 0 && owner.getInventory().countId(gems[option]) < 1) {
											    owner.getActionSender().sendMessage("You don't have a " + reply + ".");
											    return;
											}
											ItemCraftingDef def = EntityHandler.getCraftingDef((option * 3) + craftType);
											if(def == null) {
											    owner.getActionSender().sendMessage("Nothing interesting happens.");
											    return;
											}
											if(owner.getCurStat(12) < def.getReqLevel()) {
											    owner.getActionSender().sendMessage("You need at crafting level of " + def.getReqLevel() + " to make this");
											    return;
											}
											if(owner.getInventory().remove(item) > -1 && (option == 0 || owner.getInventory().remove(gems[option], 1) > -1)) {
											    showBubble(owner, item);
											    InvItem result = new InvItem(def.getItemID(), 1);
											    owner.getActionSender().sendMessage("You make a " + result.getDef().getName());
											    owner.getInventory().add(result);
											    owner.incExp(12, def.getExp(), true);
											    owner.getActionSender().sendStat(12);
											    owner.getActionSender().sendInventory();
											}
									    }
									});
								owner.getActionSender().sendMenu(options);
							    }
							});
						owner.getActionSender().sendMenu(options);
					    }
					});
					return;
				}
				if(item.getID() == 384) { // Silver Bar (Crafting)
					world.getDelayedEventHandler().add(new MiniEvent(owner){
					    public void action() {
							owner.getActionSender().sendMessage("What would you like to make?");
							String[] options = new String[]{"Holy Symbol of Saradomin", "UnHoly Symbol of Zamorak"};
							owner.setMenuHandler(new MenuHandler(options) {
							    public void handleReply(int option, String reply) {
									if(owner.isBusy() || option < 0 || option > 1) {
									    return;
									}
									int[] moulds = {386, 1026};
									int[] results = {44, 1027};
									if(owner.getInventory().countId(moulds[option]) < 1) {
									    owner.getActionSender().sendMessage("You need a " + EntityHandler.getItemDef(moulds[option]).getName() + " to make a " + reply);
									    return;
									}
									if(owner.getCurStat(12) < 16) {
									    owner.getActionSender().sendMessage("You need a crafting level of 16 to make this");
									    return;
									}
									if(owner.getInventory().remove(item) > -1) {
									    showBubble(owner, item);
									    InvItem result = new InvItem(results[option]);
									    owner.getActionSender().sendMessage("You make a " + result.getDef().getName());
									    owner.getInventory().add(result);
									    owner.incExp(12, 50, true);
									    owner.getActionSender().sendStat(12);
									    owner.getActionSender().sendInventory();
									}
							    }
							});
						owner.getActionSender().sendMenu(options);
					    }
					});
					return;
				} 
				else if (item.getID() == 625) { // Sand (Glass)
					if (owner.getInventory().countId(624) < 1) {
					    owner.getActionSender().sendMessage("You need some soda ash to mix the sand with.");
					    return;
					}
					owner.setBusy(true);
					showBubble(owner, item);
					owner.getActionSender().sendMessage("You put the seaweed and the soda ash in the furnace.");
					World.getWorld().getDelayedEventHandler().add(new ShortEvent(owner) {
					    public void action() {
							if (owner.getInventory().remove(624, 1) > -1 && owner.getInventory().remove(item) > -1) {
							    owner.getActionSender().sendMessage("It mixes to make some molten glass");
							    owner.getInventory().add(new InvItem(623, 1));
							    owner.incExp(12, 20, true);
							    owner.getActionSender().sendStat(12);
							    owner.getActionSender().sendInventory();
							}
							owner.setBusy(false);
					    }
					});
					return;
				}
			    break;
		}
		return;
	}
	@Override
	public void onInvUseOnItem(Player player, InvItem item1, InvItem item2) {
		if (item1.getID() == 167 && doCutGem(player, item1, item2)) {
			return;
		}
		else if (item2.getID() == 167 && doCutGem(player, item2, item1)) {
			return;
		}
		else if(item1.getID() == 621 && doGlassBlowing(player, item1, item2)) {
		    return;
		}
		else if(item2.getID() == 621 && doGlassBlowing(player, item2, item1)) {
		    return;
		}
		if (item1.getID() == 39 && makeLeather(player, item1, item2)) {
		    return;
		} 
		else if (item2.getID() == 39 && makeLeather(player, item2, item1)) {
		    return;
		}
		else if (item1.getID() == 207 && useWool(player, item1, item2)) {
			return;
		} 
		else if (item2.getID() == 207 && useWool(player, item2, item1)) {
			return;
		} 
		else if ((item1.getID() == 50 || item1.getID() == 141 || item1.getID() == 342) && useWater(player, item1, item2)) {
			return;
		} 
		else if ((item2.getID() == 50 || item2.getID() == 141 || item2.getID() == 342) && useWater(player, item2, item1)) {
		    return;
		}
		return;
	}
    private boolean doCutGem(Player player, final InvItem chisel, final InvItem gem) {
    	final ItemGemDef gemDef = EntityHandler.getItemGemDef(gem.getID());
    	if (gemDef == null) {
    	    return false;
    	}
    	if (player.getCurStat(12) < gemDef.getReqLevel()) {
    	    player.getActionSender().sendMessage("You need a crafting level of " + gemDef.getReqLevel() + " to cut this gem");
    	    return true;
    	}
    	World.getWorld().getDelayedEventHandler().add(new MiniEvent(player) {
    	    public void action() {
	    		if (owner.getInventory().remove(gem) > -1) {
	    		    InvItem cutGem = new InvItem(gemDef.getGemID(), 1);
	    		    owner.getActionSender().sendMessage("You cut the " + cutGem.getDef().getName());
	    		    owner.getActionSender().sendSound("chisel");
	    		    owner.getInventory().add(cutGem);
	    		    owner.incExp(12, gemDef.getExp(), true);
	    		    owner.getActionSender().sendStat(12);
	    		    owner.getActionSender().sendInventory();
	    		}
    	    }
    	});
    	return true;
    }
    private boolean doGlassBlowing(Player player, final InvItem pipe, final InvItem glass) {
    	if (glass.getID() != 623) {
    	    return false;
    	}
    	World.getWorld().getDelayedEventHandler().add(new MiniEvent(player) {
	    	    public void action() {
	    		String[] options = new String[] { "Beer Glass", "Vial", "Orb", "Cancel" };
	    		owner.setMenuHandler(new MenuHandler(options) {
	    		    public void handleReply(final int option, final String reply) {
		    			InvItem result;
		    			int reqLvl, exp;
		    			switch (option) {
			    			case 0:
			    			    result = new InvItem(620, 1);
			    			    reqLvl = 1;
			    			    exp = 18;
			    			    break;
			    			case 1:
			    			    result = new InvItem(465, 1);
			    			    reqLvl = 33;
			    			    exp = 35;
			    			    break;
			    			case 2:
			    			    result = new InvItem(611, 1);
			    			    reqLvl = 46;
			    			    exp = 53;
			    			    break;
			    			default:
			    			    return;
		    				}
			    		if (owner.getCurStat(12) < reqLvl) {
			    		    owner.getActionSender().sendMessage("You need a crafting level of " + reqLvl + " to make a " + result.getDef().getName() + ".");
			    		    return;
			   			}
			   			if (owner.getInventory().remove(glass) > -1) {
			   			    owner.getActionSender().sendMessage("You make a " + result.getDef().getName());
			   			    owner.getInventory().add(result);
			   			    owner.incExp(12, exp, true);
			   			    owner.getActionSender().sendStat(12);
			   			    owner.getActionSender().sendInventory();
			   			}
			  	    }
	    		});
	    	owner.getActionSender().sendMenu(options);
		    }
	    });
    return true;
    }
    private boolean makeLeather(Player player, final InvItem needle, final InvItem leather) {
		if (leather.getID() != 148) {
		    return false;
		}
		if (player.getInventory().countId(43) < 1) {
		    player.getActionSender().sendMessage("You need some thread to make anything out of leather");
		    return true;
		}
		if (DataConversions.random(0, 5) == 0) {
		    player.getInventory().remove(43, 1);
		}
		World.getWorld().getDelayedEventHandler().add(new MiniEvent(player) {
		    public void action() {
				String[] options = new String[] { "Armour", "Gloves", "Boots", "Cancel" };
				owner.setMenuHandler(new MenuHandler(options) {
				    public void handleReply(final int option, final String reply) {
						InvItem result;
						int reqLvl, exp;
						switch (option) {
						case 0:
						    result = new InvItem(15, 1);
						    reqLvl = 14;
						    exp = 25;
						    break;
						case 1:
						    result = new InvItem(16, 1);
						    reqLvl = 1;
						    exp = 14;
						    break;
						case 2:
						    result = new InvItem(17, 1);
						    reqLvl = 7;
						    exp = 17;
						    break;
						default:
						    return;
						}
						if (owner.getCurStat(12) < reqLvl) {
						    owner.getActionSender().sendMessage("You need a crafting level of " + reqLvl + " to make " + result.getDef().getName() + ".");
						    return;
						}
						if (owner.getInventory().remove(leather) > -1) {
						    owner.getActionSender().sendMessage("You make some " + result.getDef().getName());
						    owner.getInventory().add(result);
						    owner.incExp(12, exp, true);
						    owner.getActionSender().sendStat(12);
						    owner.getActionSender().sendInventory();
						}
				    }
				});
			owner.getActionSender().sendMenu(options);
		    }
		});
		return true;
    }
    private boolean useWool(Player player, final InvItem woolBall, final InvItem item) {
		int newID;
		switch (item.getID()) {
		case 44: // Holy Symbol of saradomin
		    newID = 45;
		    break;
		case 1027: // Unholy Symbol of Zamorak
		    newID = 1028;
		    break;
		case 296: // Gold Amulet
		    newID = 301;
		    break;
		case 297: // Sapphire Amulet
		    newID = 302;
		    break;
		case 298: // Emerald Amulet
		    newID = 303;
		    break;
		case 299: // Ruby Amulet
		    newID = 304;
		    break;
		case 300: // Diamond Amulet
		    newID = 305;
		    break;
		case 524: // Dragonstone Amulet
		    newID = 610;
		    break;
		default:
		    return false;
		}
		final int newId = newID;
		World.getWorld().getDelayedEventHandler().add(new MiniEvent(player) {
		    public void action() {
				if (owner.getInventory().remove(woolBall) > -1 && owner.getInventory().remove(item) > -1) {
				    owner.getActionSender().sendMessage("You string the amulet");
				    owner.getInventory().add(new InvItem(newId, 1));
				    owner.getActionSender().sendInventory();
				}
		    }
		});
		return true;
    }
    private boolean useWater(Player player, final InvItem water, final InvItem item) {
		int jugID = Formulae.getEmptyJug(water.getID());
		if (jugID == -1) { // This shouldn't happen
		    return false;
		}
		switch (item.getID()) {
		case 149: // Clay
		    if (player.getInventory().remove(water) > -1 && player.getInventory().remove(item) > -1) {
			player.getActionSender().sendMessage("You soften the clay.");
			player.getInventory().add(new InvItem(jugID, 1));
			player.getInventory().add(new InvItem(243, 1));
			player.getActionSender().sendInventory();
		    }
		    break;
		default:
		    return false;
		}
		return true;
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
}
