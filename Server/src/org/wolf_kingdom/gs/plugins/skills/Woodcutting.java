package org.wolf_kingdom.gs.plugins.skills;

import org.wolf_kingdom.config.Formulae;
import org.wolf_kingdom.gs.event.ShortEvent;
import org.wolf_kingdom.gs.external.EntityHandler;
import org.wolf_kingdom.gs.external.ObjectWoodcuttingDef;
import org.wolf_kingdom.gs.model.Bubble;
import org.wolf_kingdom.gs.model.GameObject;
import org.wolf_kingdom.gs.model.InvItem;
import org.wolf_kingdom.gs.model.Player;
import org.wolf_kingdom.gs.model.World;
import org.wolf_kingdom.gs.plugins.listeners.action.ObjectActionListener;
import org.wolf_kingdom.gs.tools.DataConversions;


public class Woodcutting implements ObjectActionListener {
	
	@Override
    public void onObjectAction(final GameObject object, String command, Player owner) {
		if (command.equals("chop")) {
			handleWoodcutting(object, owner, owner.click);
			return;
		}
    	return;
    }

    private void handleWoodcutting(final GameObject object, Player owner, final int click) {
		final ObjectWoodcuttingDef def = EntityHandler.getObjectWoodcuttingDef(object.getID());
		if (owner.isBusy()) {
		    return;
		}
		if (!owner.withinRange(object, 2))
		    return;
		if (def == null) { // This shoudln't happen
		    return;
		}
		if (owner.getCurStat(8) < def.getReqLevel()) {
		    owner.getActionSender().sendMessage("You need a woodcutting level of " + def.getReqLevel() + " to axe this tree.");
		    return;
		}
		int axeId = -1;
		for (int a : Formulae.woodcuttingAxeIDs) {
		    if (owner.getInventory().countId(a) > 0) {
			axeId = a;
			break;
		    }
		}
		if (axeId < 0) {
		    owner.getActionSender().sendMessage("You need an axe to chop this tree down.");
		    return;
		}
		owner.setBusy(true);
		Bubble bubble = new Bubble(owner, axeId);
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
		owner.getActionSender().sendMessage("You swing your " + EntityHandler.getItemDef(axeId).getName() + " at the tree...");
		final int axeID = axeId;
		World.getWorld().getDelayedEventHandler().add(new ShortEvent(owner) {
		    public void action() {
				if (Formulae.getLog(def, owner.getCurStat(8), axeID)) {
				    InvItem log = new InvItem(def.getLogId());
				    owner.getInventory().add(log);
				    owner.getActionSender().sendMessage("You get some wood.");
				    owner.getActionSender().sendInventory();
				    owner.incExp(8, def.getExp(), true);
				    owner.getActionSender().sendStat(8);
				    if (DataConversions.random(1, 100) <= def.getFell()) {
						world.registerGameObject(new GameObject(object.getLocation(), 4, object.getDirection(), object.getType()));
						world.delayedSpawnObject(object.getLoc(), def.getRespawnTime() * 1000);
						owner.setBusy(false);
				    }
				    else {
				    	owner.setBusy(false);
				    	if(!owner.getInventory().full()) {
				    		handleWoodcutting(object, owner, owner.click);
						}
				    }
				    
				} 
				else {
				    owner.getActionSender().sendMessage("You slip and fail to hit the tree.");
				    owner.setBusy(false);
				    if(!owner.getInventory().full()) {
				    	handleWoodcutting(object, owner, owner.click);
					}
				}
		    }
		});
    }
}
