package org.lupus_regnum.gs.plugins.skills;

import org.lupus_regnum.config.Formulae;
import org.lupus_regnum.gs.event.ShortEvent;
import org.lupus_regnum.gs.external.EntityHandler;
import org.lupus_regnum.gs.external.ObjectFishDef;
import org.lupus_regnum.gs.external.ObjectFishingDef;
import org.lupus_regnum.gs.model.Bubble;
import org.lupus_regnum.gs.model.GameObject;
import org.lupus_regnum.gs.model.InvItem;
import org.lupus_regnum.gs.model.Player;
import org.lupus_regnum.gs.model.World;
import org.lupus_regnum.gs.plugins.listeners.action.ObjectActionListener;


public class Fishing implements ObjectActionListener {
	
    @Override
    public void onObjectAction(final GameObject object, String command, Player owner) {
		if (command.equals("lure") || command.equals("bait") || command.equals("net") || command.equals("harpoon") || command.equals("cage")) {
			handleFishing(object, owner, owner.click);
			return;
		}
    	return ;
    }

    private void handleFishing(final GameObject object, Player owner, final int click) { 
		final ObjectFishingDef def = EntityHandler.getObjectFishingDef(object.getID(), click);
		if (owner.isBusy()) {
		    return;
		}
		if (!owner.withinRange(object, 1))
		    return;
		if (def == null) { // This shouldn't happen
		    return;
		}
		if (owner.getCurStat(10) < def.getReqLevel()) {
		    owner.getActionSender().sendMessage("You need a fishing level of " + def.getReqLevel() + " to fish here.");
		    return;
		}
		int netId = def.getNetId();
		if (owner.getInventory().countId(netId) <= 0) {
		    owner.getActionSender().sendMessage("You need a " + EntityHandler.getItemDef(netId).getName() + " to catch these fish.");
		    return;
		}
		final int baitId = def.getBaitId();
		if (baitId >= 0) {
		    if (owner.getInventory().countId(baitId) <= 0) {
			owner.getActionSender().sendMessage("You don't have any " + EntityHandler.getItemDef(baitId).getName() + " left.");
			return;
		    }
		}

		owner.setBusy(true);
		owner.getActionSender().sendSound("fish");
		Bubble bubble = new Bubble(owner, netId);
		for (Player p : owner.getViewArea().getPlayersInView()) {
		    p.informOfBubble(bubble);
		}

		owner.getActionSender().sendMessage("You attempt to catch some fish");
		World.getWorld().getDelayedEventHandler().add(new ShortEvent(owner) {
		    public void action() {
				ObjectFishDef def = Formulae.getFish(object.getID(), owner.getCurStat(10), click);
				if (def != null) {
				    if (baitId >= 0) {
						int idx = owner.getInventory().getLastIndexById(baitId);
						InvItem bait = owner.getInventory().get(idx);
						int newCount = bait.getAmount() - 1;
						if (newCount <= 0) {
						    owner.getInventory().remove(idx);
						}
						else {
						    bait.setAmount(newCount);
						}
				    }
				    InvItem fish = new InvItem(def.getId());
				    owner.getInventory().add(fish);
				    owner.getActionSender().sendMessage("You catch a " + fish.getDef().getName() + ".");
				    owner.getActionSender().sendInventory();
				    owner.incExp(10, def.getExp(), true);
				    owner.getActionSender().sendStat(10);
				}
				else {
				    owner.getActionSender().sendMessage("You fail to catch anything.");
				}
				owner.setBusy(false);
				if(!owner.getInventory().full()) {
					handleFishing(object, owner, owner.click);
				}
		    }
		});
    }
}
