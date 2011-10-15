package org.lupus_regnum.gs.phandler.client;

import java.util.ArrayList;
import java.util.Map.Entry;

import org.apache.mina.common.IoSession;
import org.lupus_regnum.config.Constants;
import org.lupus_regnum.config.Formulae;
import org.lupus_regnum.gs.connection.Packet;
import org.lupus_regnum.gs.connection.RSCPacket;
import org.lupus_regnum.gs.external.EntityHandler;
import org.lupus_regnum.gs.model.InvItem;
import org.lupus_regnum.gs.model.Player;
import org.lupus_regnum.gs.model.World;
import org.lupus_regnum.gs.phandler.PacketHandler;
import org.lupus_regnum.gs.plugins.PluginHandler;



public class WieldHandler implements PacketHandler {
    /**
     * World instance
     */
    public static final World world = World.getWorld();

    public void handlePacket(Packet p, IoSession session) throws Exception {
		Player player = (Player) session.getAttachment();
		int pID = ((RSCPacket) p).getID();
		if (player.isBusy() && !player.inCombat()) {
		    return;
		}
		if (player.isDueling() && player.getDuelSetting(3)) {
		    player.getActionSender().sendMessage("Armour is disabled in this duel");
		    return;
		}
		player.resetAllExceptDueling();
		int idx = (int) p.readShort();
		if (idx < 0 || idx >= 30) {
		    player.setSuspiciousPlayer(true);
		    return;
		}
		InvItem item = player.getInventory().get(idx);
		if (item == null || !item.isWieldable()) {
		    player.setSuspiciousPlayer(true);
		    return;
		}
		if(player.getLocation().inWilderness() && item.getDef().isMembers() && Constants.GameServer.F2P_WILDY) {
			player.getActionSender().sendMessage("Can't wield a P2P item in wilderness");
		    return;
		}
	
		switch (pID) {
			case 181:
			    if (!item.isWielded()) {
			    	
			    	if(PluginHandler.getPluginHandler().blockDefaultAction("Wield", new Object[] { player, item }))
			    		return;
			    	wieldItem(player, item);
			   
			    }
			    break;
			case 92:
			    if (item.isWielded()) {
			    	if(PluginHandler.getPluginHandler().blockDefaultAction("UnWield", new Object[] { player, item }))
			    		return;
			    	unWieldItem(player, item, true);
			    }
			    break;
		}
		player.getActionSender().sendInventory();
		player.getActionSender().sendEquipmentStats();
    }

    public static void unWieldItem(Player player, InvItem item, boolean sound) {
    	item.setWield(false);
		if (sound) {
		    player.getActionSender().sendSound("click");
		}
		player.updateWornItems(item.getWieldableDef().getWieldPos(), player.getPlayerAppearance().getSprite(item.getWieldableDef().getWieldPos()));
    }

    private void wieldItem(Player player, InvItem item) {
		String youNeed = "";
		for (Entry<Integer, Integer> e : item.getWieldableDef().getStatsRequired()) {
		    if (player.getMaxStat(e.getKey()) < e.getValue()) {
			youNeed += ((Integer) e.getValue()).intValue() + " " + Formulae.statArray[((Integer) e.getKey()).intValue()] + ", ";
		    }
		}
		if (!youNeed.equals("")) {
		    player.getActionSender().sendMessage("You must have at least " + youNeed.substring(0, youNeed.length() - 2) + " to use this item.");
		    return;
		}
		if (EntityHandler.getItemWieldableDef(item.getID()).femaleOnly() && player.isMale()) {
		    player.getActionSender().sendMessage("This piece of armor is for a female only.");
		    return;
		}
		if (item.getID() == 407 || item.getID() == 401) {
			if (player.getCurStat(6) < 31) {
				player.getActionSender().sendMessage("You must have at least 31 magic");
					return;
			}
		}		
			
		ArrayList<InvItem> items = player.getInventory().getItems();
		for (InvItem i : items) {
		    if (item.wieldingAffectsItem(i) && i.isWielded()) {
			unWieldItem(player, i, false);
		    }
		}
		item.setWield(true);
		player.getActionSender().sendSound("click");
		player.updateWornItems(item.getWieldableDef().getWieldPos(), item.getWieldableDef().getSprite());
    }

}