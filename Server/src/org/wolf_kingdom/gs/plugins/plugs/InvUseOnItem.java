package org.wolf_kingdom.gs.plugins.plugs;

import org.wolf_kingdom.gs.model.InvItem;
import org.wolf_kingdom.gs.model.Player;
import org.wolf_kingdom.gs.plugins.listeners.action.InvUseOnItemListener;

public class InvUseOnItem implements InvUseOnItemListener {
    int[] capes = { 183, 209, 229, 511, 512, 513, 514 };
    int[] dye = { 238, 239, 272, 282, 515, 516 };
    int[] newCapes = { 183, 512, 229, 513, 511, 514 };

	@Override
	public void onInvUseOnItem(Player player, InvItem item1, InvItem item2) {

		
		if (item1.getID() == 1276 && item2.getID() == 1277) {
		    if (player.getInventory().remove(new InvItem(1276)) > -1 && player.getInventory().remove(new InvItem(1277)) > -1) {
				player.getActionSender().sendMessage("You combine the two parts.");
				player.getInventory().add(new InvItem(1278));
				player.getActionSender().sendInventory();
				return;
		    }
		}
		if (item1.getID() == 238 && item2.getID() == 239 || item1.getID() == 239 && item2.getID() == 238) {
		    if (player.getInventory().remove(new InvItem(239)) > -1 && player.getInventory().remove(new InvItem(238)) > -1) {
			player.getInventory().add(new InvItem(282));
			player.getActionSender().sendMessage("You mix the Dyes");
			player.getActionSender().sendInventory();
			return;
		    }
		}
		if (item1.getID() == 238 && item2.getID() == 272 || item1.getID() == 272 && item2.getID() == 238) {
		    if (player.getInventory().remove(new InvItem(272)) > -1 && player.getInventory().remove(new InvItem(238)) > -1) {
			player.getInventory().add(new InvItem(516));
			player.getActionSender().sendMessage("You mix the Dyes");
			player.getActionSender().sendInventory();
			return;
		    }
		}
		if (item1.getID() == 239 && item2.getID() == 272 || item1.getID() == 272 && item2.getID() == 239) {
		    if (player.getInventory().remove(new InvItem(272)) > -1 && player.getInventory().remove(new InvItem(239)) > -1) {
			player.getInventory().add(new InvItem(515));
			player.getActionSender().sendMessage("You mix the Dyes");
			player.getActionSender().sendInventory();
			return;
		    }
		}


		for (Integer il : capes) {
		    if (il == item1.getID()) {
				for (int i = 0; i < dye.length; i++) {
				    if (dye[i] == item2.getID()) {
						if (player.getInventory().remove(new InvItem(item1.getID())) > -1 && player.getInventory().remove(new InvItem(item2.getID())) > -1) {
						    player.getActionSender().sendMessage("You dye the Cape");
						    player.getInventory().add(new InvItem(newCapes[i]));
						    player.getActionSender().sendInventory();
						    return;
							}
				    }
				}
		    } 
		    else if (il == item2.getID()) {
				for (int i = 0; i < dye.length; i++) {
				    if (dye[i] == item1.getID()) {
						if (player.getInventory().remove(new InvItem(item1.getID())) > -1 && player.getInventory().remove(new InvItem(item2.getID())) > -1) {
						    player.getActionSender().sendMessage("You dye the Cape");
						    player.getInventory().add(new InvItem(newCapes[i]));
						    player.getActionSender().sendInventory();
						    return;
						}
				    }
				}
		    }
		}
		if (item1.getID() == 273 && item2.getID() == 282 || item1.getID() == 282 && item2.getID() == 273) {
		    if (player.getInventory().remove(new InvItem(282)) > -1 && player.getInventory().remove(new InvItem(273)) > -1) {
			player.getInventory().add(new InvItem(274));
			player.getActionSender().sendMessage("You dye the goblin armor");
			player.getActionSender().sendInventory();
			return;
		    }
		}
		if (item1.getID() == 273 && item2.getID() == 272 || item1.getID() == 272 && item2.getID() == 273) {
		    if (player.getInventory().remove(new InvItem(272)) > -1 && player.getInventory().remove(new InvItem(273)) > -1) {
			player.getInventory().add(new InvItem(275));
			player.getActionSender().sendMessage("You dye the goblin armor");
			player.getActionSender().sendInventory();
			return;
		    }
		}
		else if (item1.getID() == 526 && combineKeys(player, item1, item2)) {
			return;
		} 
		else if (item2.getID() == 526 && combineKeys(player, item2, item1)) {
		    return;
		}
	}
	
    private boolean combineKeys(Player player, final InvItem firstHalf, final InvItem secondHalf) {
		if (secondHalf.getID() != 527) {
		    return false;
		}
		if (player.getInventory().remove(firstHalf) > -1 && player.getInventory().remove(secondHalf) > -1) {
		    player.getActionSender().sendMessage("You combine the key halves to make a crystal key.");
		    player.getInventory().add(new InvItem(525, 1));
		    player.getActionSender().sendInventory();
		}
		return true;
    }

}
