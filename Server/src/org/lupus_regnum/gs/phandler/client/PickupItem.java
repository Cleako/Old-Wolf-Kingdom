package org.lupus_regnum.gs.phandler.client;

import org.apache.mina.common.IoSession;
import org.lupus_regnum.gs.connection.Packet;
import org.lupus_regnum.gs.event.FightEvent;
import org.lupus_regnum.gs.event.WalkToPointEvent;
import org.lupus_regnum.gs.model.ActiveTile;
import org.lupus_regnum.gs.model.ChatMessage;
import org.lupus_regnum.gs.model.InvItem;
import org.lupus_regnum.gs.model.Item;
import org.lupus_regnum.gs.model.Npc;
import org.lupus_regnum.gs.model.Player;
import org.lupus_regnum.gs.model.Point;
import org.lupus_regnum.gs.model.World;
import org.lupus_regnum.gs.model.snapshot.Activity;
import org.lupus_regnum.gs.phandler.PacketHandler;
import org.lupus_regnum.gs.plugins.PluginHandler;
import org.lupus_regnum.gs.states.Action;



public class PickupItem implements PacketHandler {
    /**
     * World instance
     */
    public static final World world = World.getWorld();

    private Item getItem(int id, ActiveTile tile, Player player) {
		if (tile != null)
		    for (Item i : tile.getItems()) {
			if (i.getID() == id && i.visibleTo(player)) {
			    return i;
			}
	    }
		return null;
    }

    public void handlePacket(Packet p, IoSession session) throws Exception {
		Player player = (Player) session.getAttachment();
		if (player.isBusy()) {
		    player.resetPath();
		    return;
		}
		player.resetAll();
		Point location = Point.location(p.readShort(), p.readShort());
		int id = p.readShort();
		final ActiveTile tile = world.getTile(location);
		final Item item = getItem(id, tile, player);
		if (item == null) {
		    player.resetPath();
		    return;
		}
		if(player.isPMod() && !player.isMod())
		    return;
	
		player.setStatus(Action.TAKING_GITEM);
		World.getWorld().getDelayedEventHandler().add(new WalkToPointEvent(player, location, 1, false) {
		    public void arrived() {
				if (owner.isBusy() || owner.isRanging() || !tile.hasItem(item) || !owner.nextTo(item) || owner.getStatus() != Action.TAKING_GITEM) {
				    return;
				}
				if (item.getID() == 23) {
				    owner.getActionSender().sendMessage("I can't pick it up!");
				    owner.getActionSender().sendMessage("I need a pot to hold it in.");
				    return;
				}
				owner.resetAll();
				InvItem invItem = new InvItem(item.getID(), item.getAmount());
				if (!owner.getInventory().canHold(invItem)) {
				    owner.getActionSender().sendMessage("You cannot pickup this item, your inventory is full!");
				    return;
				}
				try {
				    if (item.getID() == 59 && item.getX() == 106 && item.getY() == 1476) {
				    	Npc n = world.getNpc(37, 103, 107, 1476, 1479);
						if (n != null && !n.inCombat()) {
							owner.informOfNpcMessage(new ChatMessage(n, "Nobody steals from this gang!", owner));
							fight(owner, n);
						}
				    } 
				    else if (item.getID() == 501 && item.getX() == 333 && item.getY() == 434) {
						Npc zam = world.getNpc(140, 328, 333, 433, 438, true);
						if (zam != null && !zam.inCombat()) {
						    owner.informOfNpcMessage(new ChatMessage(zam, "a curse be upon you", owner));
						    for (int i = 0; i < 3; i++) {
								int stat = owner.getCurStat(i);
								if (stat < 3)
								    owner.setCurStat(i, 0);
								else
								    owner.setCurStat(i, stat - 3);
						    }
						    owner.getActionSender().sendStats();
						    fight(owner, zam);
						    return;
						}
				    }
				}
				catch (Exception e) {
				    e.printStackTrace();
				}
				world.addEntryToSnapshots(new Activity(owner.getUsername(),owner.getUsername() + " picked up an item "+item.getDef().getName()+" (" + item.getID() + ") amount: " + item.getAmount() + " at: " + owner.getX() + "/" + owner.getY() + "|" + item.getX() + "/" + item.getY()));
				World.getWorld().getServer().getLoginConnector().getActionSender().tradeLog(owner.getUsernameHash(), item.droppedby(), item.getID(), item.getAmount(), owner.getX(), owner.getY(), 1);
				
				if(PluginHandler.getPluginHandler().blockDefaultAction("Pickup", new Object[] { owner, item })) {
					return;
				}
				
				world.unregisterItem(item);
				owner.getActionSender().sendSound("takeobject");
				owner.getInventory().add(invItem);
				owner.getActionSender().sendInventory();
		    }
		});
    }

    void fight(Player owner, Npc n) {
		n.resetPath();
		owner.resetPath();
		owner.resetAll();
		owner.setStatus(Action.FIGHTING_MOB);
		owner.getActionSender().sendSound("underattack");
		owner.getActionSender().sendMessage("You are under attack!");
	
		n.setLocation(owner.getLocation(), true);
		for (Player p : n.getViewArea().getPlayersSectorA()) {
		    p.removeWatchedNpc(n);
		}
                for (Player p : n.getViewArea().getPlayersSectorB()) {
		    p.removeWatchedNpc(n);
		}
                for (Player p : n.getViewArea().getPlayersSectorC()) {
		    p.removeWatchedNpc(n);
		}
                for (Player p : n.getViewArea().getPlayersSectorD()) {
		    p.removeWatchedNpc(n);
		}
	
		owner.setBusy(true);
		owner.setSprite(9);
		owner.setOpponent(n);
		owner.setCombatTimer();
	
		n.setBusy(true);
		n.setSprite(8);
		n.setOpponent(owner);
		n.setCombatTimer();
		FightEvent fighting = new FightEvent(owner, n, true);
		fighting.setLastRun(0);
		World.getWorld().getDelayedEventHandler().add(fighting);
    }

}
