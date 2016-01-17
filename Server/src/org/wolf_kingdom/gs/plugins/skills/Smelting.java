package org.wolf_kingdom.gs.plugins.skills;

import org.wolf_kingdom.gs.event.ShortEvent;
import org.wolf_kingdom.gs.external.EntityHandler;
import org.wolf_kingdom.gs.external.ItemSmeltingDef;
import org.wolf_kingdom.gs.external.ReqOreDef;
import org.wolf_kingdom.gs.model.Bubble;
import org.wolf_kingdom.gs.model.GameObject;
import org.wolf_kingdom.gs.model.InvItem;
import org.wolf_kingdom.gs.model.Player;
import org.wolf_kingdom.gs.model.World;
import org.wolf_kingdom.gs.plugins.listeners.action.InvUseOnObjectListener;
import org.wolf_kingdom.gs.tools.DataConversions;

public class Smelting implements InvUseOnObjectListener {

	@Override
	public void onInvUseOnObject(GameObject obj, InvItem item, Player player) {
		if((obj.getID() == 813 || obj.getID() == 118) && !DataConversions.inArray(new int[]{172, 384, 625}, item.getID())) {
 		   	handleRegularSmelting(item, player, obj);
			return;
		}
	}
	private void handleRegularSmelting(final InvItem item, Player owner, final GameObject obj) {
		ItemSmeltingDef smeltingDef = item.getSmeltingDef();
		if (smeltingDef == null) {
			owner.getActionSender().sendMessage("Nothing interesting happens.");
			return;
		}
		for (ReqOreDef reqOre : smeltingDef.getReqOres()) {
			if (owner.getInventory().countId(reqOre.getId()) < reqOre.getAmount()) {
				if (item.getID() == 151) {
			    	smeltingDef = EntityHandler.getItemSmeltingDef(9999);
			    	break;
				}
			owner.getActionSender().sendMessage("You need " + reqOre.getAmount() + " " + EntityHandler.getItemDef(reqOre.getId()).getName() + " to smelt a " + item.getDef().getName() + ".");
			return;
			}
		}
		if (owner.getCurStat(13) < smeltingDef.getReqLevel()) {
		    owner.getActionSender().sendMessage("You need a smithing level of " + smeltingDef.getReqLevel() + " to smelt this.");
		    return;
		}
		if(!owner.getInventory().contains(item)) {
			return;
		}
		if(!owner.withinRange(obj, 2)) {
			return;
		}
		owner.setBusy(true);
		showBubble(owner, item);
		owner.getActionSender().sendMessage("You smelt the " + item.getDef().getName() + " in the furnace.");
			
		final ItemSmeltingDef def = smeltingDef;
		World.getWorld().getDelayedEventHandler().add(new ShortEvent(owner) {
		    public void action() {
				InvItem bar = new InvItem(def.getBarId());
				if (owner.getInventory().remove(item) > -1) {
				    for (ReqOreDef reqOre : def.getReqOres()) {
				    	for (int i = 0; i < reqOre.getAmount(); i++) {
					    owner.getInventory().remove(new InvItem(reqOre.getId()));
				    	}
				    }
				    if (item.getID() == 151 && def.getReqOres().length == 0 && DataConversions.random(0, 1) == 1) {
				    	owner.getActionSender().sendMessage("The ore is too impure and unable to be refined.");
				    } 
				    else {
						owner.getInventory().add(bar);
						owner.getActionSender().sendMessage("You retrieve a " + bar.getDef().getName() + ".");
						owner.incExp(13, def.getExp(), true);
						owner.getActionSender().sendStat(13);
				    }
				    owner.getActionSender().sendInventory();
				}
				owner.setBusy(false);
				if(owner.getInventory().hasItemId(item.getID()))
					handleRegularSmelting(item, owner, obj);
		    }
		});
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
