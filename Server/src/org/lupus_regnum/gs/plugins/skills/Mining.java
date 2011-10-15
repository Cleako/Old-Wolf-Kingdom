package org.lupus_regnum.gs.plugins.skills;

import java.util.Arrays;

import org.lupus_regnum.config.Formulae;
import org.lupus_regnum.gs.event.ShortEvent;
import org.lupus_regnum.gs.event.SingleEvent;
import org.lupus_regnum.gs.external.EntityHandler;
import org.lupus_regnum.gs.external.ObjectMiningDef;
import org.lupus_regnum.gs.model.Bubble;
import org.lupus_regnum.gs.model.GameObject;
import org.lupus_regnum.gs.model.InvItem;
import org.lupus_regnum.gs.model.Player;
import org.lupus_regnum.gs.model.World;
import org.lupus_regnum.gs.plugins.listeners.action.ObjectActionListener;
import org.lupus_regnum.gs.tools.DataConversions;


public class Mining implements ObjectActionListener {
	
	static int[] ids;
	static {
		ids = new int[]{ 176, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 195, 196, 210, 211 };
		Arrays.sort(ids);
	}
    
    @Override
    public void onObjectAction(final GameObject object, String command, Player owner) {
		
		if(Arrays.binarySearch(ids, object.getID()) >= 0) {
			handleMining(object, owner, owner.click);
			return;
		}
    }

	/**
	 * Picks the best axe the player can use
	 * @param p
	 * @return
	 */
    public int getAxe(Player p) {
		int lv = p.getCurStat(14);
		for (int i = 0; i < Formulae.miningAxeIDs.length; i++) {
		    if (p.getInventory().countId(Formulae.miningAxeIDs[i]) > 0) {
				if (lv >= Formulae.miningAxeLvls[i]) {
				    return Formulae.miningAxeIDs[i];
				}
		    }
		}
	return -1;
    }

    public void handleMining(final GameObject object, Player owner, int click) {
		if (owner.isBusy() || owner.inCombat()) {
		    return;
		}
		if (!owner.withinRange(object, 1))
		    return;
		final GameObject newobject = World.getWorld().getTile(object.getX(), object.getY()).getGameObject();
		final ObjectMiningDef def = EntityHandler.getObjectMiningDef(newobject.getID());
		if (def == null || def.getRespawnTime() < 1) {
		    owner.getActionSender().sendMessage("There is currently no ore available in this rock.");
		    return;
		}
		final InvItem ore = new InvItem(def.getOreId());
		if (owner.click == 1) {
		    owner.getActionSender().sendMessage("This rock contains " + ore.getDef().getName() + ".");
		    return;
		}
		if (owner.getCurStat(14) < def.getReqLevel()) {
		    owner.getActionSender().sendMessage("You need a mining level of " + def.getReqLevel() + " to mine this rock.");
		    return;
		}
		int axeId = getAxe(owner);
	
		if (axeId < 0) {
		    owner.getActionSender().sendMessage("You need a pickaxe to mine this rock.");
		    return;
		}
		final int axeID = axeId;
		final int mineLvl = owner.getCurStat(14);
		int reqlvl = 1;
		switch (axeID) {
			case 1258:
			    break;
			case 1259:
			    reqlvl = 6;
			    break;
			case 1260:
			    reqlvl = 21;
			    break;
			case 1261:
			    reqlvl = 31;
			    break;
			case 1262:
			    reqlvl = 41;
			    break;
			}
	
		if (reqlvl > mineLvl) {
		    owner.getActionSender().sendMessage("You need to be level " + reqlvl + " to use this pick.");
		    return;
		}
		owner.setBusy(true);
	
		owner.getActionSender().sendSound("mine");
		Bubble bubble = new Bubble(owner, axeId);
		for (Player p : owner.getViewArea().getPlayersInView()) {
		    p.informOfBubble(bubble);
		}
	
		owner.getActionSender().sendMessage("You swing your pick at the rock...");
		World.getWorld().getDelayedEventHandler().add(new ShortEvent(owner) {
		    public void action() {
			if (Formulae.getOre(def, owner.getCurStat(14), axeID)) {
			    if (DataConversions.random(0, 200) == 0) {
					InvItem gem = new InvItem(Formulae.getGem(), 1);
					owner.incExp(14, 100, true);
					owner.getInventory().add(gem);
					owner.getActionSender().sendMessage("You found a gem!");
			    }
			    else {
					owner.getInventory().add(ore);
					owner.getActionSender().sendMessage("You manage to obtain some " + ore.getDef().getName() + ".");
					owner.incExp(14, def.getExp(), true);
					owner.getActionSender().sendStat(14);
					world.registerGameObject(new GameObject(object.getLocation(), 98, object.getDirection(), object.getType()));
					world.delayedSpawnObject(newobject.getLoc(), def.getRespawnTime() * 1000);
			    }
			    owner.getActionSender().sendInventory();
			    if (!owner.getInventory().full()) {
					world.getDelayedEventHandler().add(new SingleEvent(owner, 500) {
					    public void action() {
						handleMining(object, owner, owner.click);
					    }
					});
			    }
			}
			else {
			    owner.getActionSender().sendMessage("You only succeed in scratching the rock.");
			    owner.isMining(false);
			    if (!owner.getInventory().full()) {
					world.getDelayedEventHandler().add(new SingleEvent(owner, 500) {
					    public void action() {
						handleMining(object, owner, owner.click);
					    }
					});
			    }
			}
			owner.setBusy(false);
		    }
		});
    }
}
