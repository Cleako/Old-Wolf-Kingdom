package org.lupus_regnum.gs.plugins.plugs;

import org.lupus_regnum.gs.model.InvItem;
import org.lupus_regnum.gs.model.MenuHandler;
import org.lupus_regnum.gs.model.Player;
import org.lupus_regnum.gs.plugins.listeners.action.InvActionListener;

public class XPBoxes implements InvActionListener {
	String[] combatOptions = new String[] { 
			"Attack (10% of required xp to level up)", 
			"Defense (10% of required xp to level up)", 
			"Strength (10% of required xp to level up)",
			"",
			"Ranged (10% of required xp to level up)", 
			"Prayer (10% of required xp to level up)",
			"Magic (10% of required xp to level up)",
	};
	String[] skillOptions = new String[] { 
		"Cooking (5% of required xp to level up)",
		"Woodcutting (5% of required xp to level up)",
		"Fletching (5% of required xp to level up)",
		"Fishing (5% of required xp to level up)",
		"Firemaking (5% of required xp to level up)",
		"Crafting (5% of required xp to level up)",
		"Smithing (5% of required xp to level up)",
		"Mining (5% of required xp to level up)",
		"Herblaw (5% of required xp to level up)",
	};
	@Override
	public void onInvAction(final InvItem item, final Player player) {
		if(item.getID() == 1321) {
			player.getActionSender().sendMessage("Pick a skill to receieve a XP boost");
			String[] options = new String[] { 
					"Recieve a boost in combat skills (10% of required xp)",
					"Recieve a boost in regular skills (5% of required xp)",
				};
	    	player.setMenuHandler(new MenuHandler(options) {
		    	public void handleReply(final int option, final String reply) {
		    		switch (option) {
			        	case 0: //Combat
			        		player.setMenuHandler(new MenuHandler(combatOptions) {
			    		    	public void handleReply(final int option, final String reply) {
			    		    		switch (option) {
			    		    			case 0:
			    		    			case 1:
			    		    			case 2:
			    		    				player.incExp(3, (int)(player.getXpTillNextLevel(option) * 0.10)/3, false, false, false);
			    		    				player.incExp(option, (int)(player.getXpTillNextLevel(option) * 0.10), false, false, false);
			    		    				player.getActionSender().sendStat(3);
			    		    				player.getActionSender().sendStat(option);
			    		    				player.getActionSender().sendMessage("The box dissapears!");
			    		    				player.getInventory().remove(item);
			    		    				player.getActionSender().sendInventory();
			    		    				return;
			    		    			case 3:
			    		    				return;
			    		    			case 4:
			    		    			case 5:
			    		    			case 6:
			    		    				player.incExp(option, (int)(player.getXpTillNextLevel(option) * 0.10), false, false, false);
			    		    				player.getActionSender().sendStat(option);
			    		    				player.getActionSender().sendMessage("The box dissapears!");
			    		    				player.getInventory().remove(item);
			    		    				player.getActionSender().sendInventory();
			    		    				return;
			    		    			default:
			    			        		return;
			    		    		}
			    		    	}
			    	    	});
			    	    	player.getActionSender().sendMenu(combatOptions);
			        		return;
			        	case 1: //Non combat
			        		player.setMenuHandler(new MenuHandler(skillOptions) {
			    		    	public void handleReply(final int option, final String reply) {
			    		    		switch (option) {
			    		    			case 0:
			    		    			case 1:
			    		    			case 2:
			    		    			case 3:
			    		    			case 4:
			    		    			case 5:
			    		    			case 6:
			    		    			case 7:
			    		    			case 8:
			    		    				player.incExp(option+7, (int)(player.getXpTillNextLevel(option+7) * 0.05), false, false, false);
			    		    				player.getActionSender().sendStat(option+7);
			    		    				player.getActionSender().sendMessage("The box dissapears!");
			    		    				player.getInventory().remove(item);
			    		    				player.getActionSender().sendInventory();
			    		    				return;
			    		    			default:
			    			        		return;
			    		    		}
			    		    	}
			    	    	});
			    	    	player.getActionSender().sendMenu(skillOptions);
			        		return;

			        	default:
			        		return;
		    		}
		    	}
	    	});
	    	player.getActionSender().sendMenu(options);
		}
		return;
	}

}
