package org.wolf_kingdom.gs.plugins.plugs;

import org.wolf_kingdom.gs.event.ShortEvent;
import org.wolf_kingdom.gs.external.GameObjectDef;
import org.wolf_kingdom.gs.model.GameObject;
import org.wolf_kingdom.gs.model.InvItem;
import org.wolf_kingdom.gs.model.Player;
import org.wolf_kingdom.gs.model.World;
import org.wolf_kingdom.gs.plugins.listeners.action.ObjectActionListener;

public class RandomObjects implements ObjectActionListener {

	@Override
	public void onObjectAction(final GameObject object, String command, Player owner) {	
		
		GameObjectDef def = object.getGameObjectDef();
		   
	    if (command.equals("search") && def.name.equals("cupboard")) {
			owner.getActionSender().sendMessage("You search the " + def.name + "...");
			World.getWorld().getDelayedEventHandler().add(new ShortEvent(owner) {
			    public void action() {
					if (object.getX() == 216 && object.getY() == 1562) {
					    owner.getActionSender().sendMessage("You find Garlic!");
					    owner.getInventory().add(new InvItem(218));
					    owner.getActionSender().sendInventory();
					} else {
					    owner.getActionSender().sendMessage("You find nothing");
					}
			    }
			});
			return;
	    }
	    else if (command.equals("board")) {
			owner.getActionSender().sendMessage("You must talk to the owner about this.");
			return;
		    }
		else {
			switch (object.getID()) {
				case 613: // Shilo cart
					if (object.getX() != 384 || object.getY() != 851) {
						return;
					}
				    owner.setBusy(true);
				    owner.getActionSender().sendMessage("You search for a way over the cart");
				    World.getWorld().getDelayedEventHandler().add(new ShortEvent(owner) {
						public void action() {
						    owner.getActionSender().sendMessage("You climb across");
						    if (owner.getX() <= 383) {
						    	owner.teleport(386, 851, false);
						    } 
						    else {
						    	owner.teleport(383, 851, false);
						    }
						    owner.setBusy(false);
						}
				    });
				    break;
				case 643: // Gnome tree stone
				    if (object.getX() != 416 || object.getY() != 161) {
				    	return;
				    }
				    owner.setBusy(true);
				    owner.getActionSender().sendMessage("You twist the stone tile to one side");
				    World.getWorld().getDelayedEventHandler().add(new ShortEvent(owner) {
						public void action() {
						    owner.getActionSender().sendMessage("It reveals a ladder, you climb down");
						    owner.teleport(703, 3284, false);
						    owner.setBusy(false);
						}
				    });
				    break;
				case 638: // First roots in gnome cave
				    if (object.getX() != 701 || object.getY() != 3280) {
				    	return;
				    }
				    owner.setBusy(true);
				    owner.getActionSender().sendMessage("You push the roots");
				    World.getWorld().getDelayedEventHandler().add(new ShortEvent(owner) {
						public void action() {
						    owner.getActionSender().sendMessage("They wrap around you and drag you forwards");
						    owner.teleport(701, 3278, false);
						    owner.setBusy(false);
						}
				    });
				case 639: // Second roots in gnome cave
				    if (object.getX() != 701 || object.getY() != 3279) {
				    	return;
				    }
				    owner.setBusy(true);
				    owner.getActionSender().sendMessage("You push the roots");
				    World.getWorld().getDelayedEventHandler().add(new ShortEvent(owner) {
						public void action() {
						    owner.getActionSender().sendMessage("They wrap around you and drag you forwards");
						    owner.teleport(701, 3281, false);
						    owner.setBusy(false);
						}
				    });
				    break;
				default:
				    owner.getActionSender().sendMessage("Nothing interesting happens.");
				    return;
				}
			  return;
			}
	}
}
