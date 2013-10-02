package org.lupus_regnum.gs.phandler.client;

import org.apache.mina.common.IoSession;
import org.lupus_regnum.config.Formulae;
import org.lupus_regnum.gs.connection.Packet;
import org.lupus_regnum.gs.event.DelayedEvent;
import org.lupus_regnum.gs.event.ShortEvent;
import org.lupus_regnum.gs.event.WalkToPointEvent;
import org.lupus_regnum.gs.external.EntityHandler;
import org.lupus_regnum.gs.external.FiremakingDef;
import org.lupus_regnum.gs.model.ActiveTile;
import org.lupus_regnum.gs.model.Bubble;
import org.lupus_regnum.gs.model.GameObject;
import org.lupus_regnum.gs.model.InvItem;
import org.lupus_regnum.gs.model.Item;
import org.lupus_regnum.gs.model.Player;
import org.lupus_regnum.gs.model.Point;
import org.lupus_regnum.gs.model.World;
import org.lupus_regnum.gs.model.snapshot.Activity;
import org.lupus_regnum.gs.phandler.PacketHandler;
import org.lupus_regnum.gs.states.Action;
import org.lupus_regnum.gs.tools.DataConversions;



public class InvUseOnGroundItem implements PacketHandler {
    /**
     * World instance
     */
    public static final World world = World.getWorld();

    public Item getItem(int id, ActiveTile tile, Player player) {
	for (Item i : tile.getItems()) {
	    if (i.getID() == id && i.visibleTo(player)) {
		return i;
	    }
	}
	return null;
    }

    public void handlePacket(Packet p, IoSession session) throws Exception {
	try {
	    Player player = (Player) session.getAttachment();
	    if (player.isBusy()) {
		player.resetPath();
		return;
	    }
	    // incExp
	    player.resetAll();
	    Point location = Point.location(p.readShort(), p.readShort());
	    int id = p.readShort();
	    final ActiveTile tile = world.getTile(location);
	    if (tile == null)
		return;
	    final InvItem myItem = player.getInventory().get(p.readShort());
	    if (myItem == null)
		return;
	    if (tile.hasGameObject() && myItem.getID() != 135) {
		player.getActionSender().sendMessage("You cannot do that here, please move to a new area.");
		return;
	    }
	    final Item item = getItem(id, tile, player);

	    if (item == null || myItem == null) {
		player.setSuspiciousPlayer(true);
		player.resetPath();
		return;
	    }
	    world.addEntryToSnapshots(new Activity(player.getUsername(), player.getUsername() + " used item " + myItem.getDef().getName() + "(" + myItem.getID() + ")" + " [CMD: "+myItem.getDef().getCommand()+"] ON A GROUND ITEM "+ myItem.getDef().getName() + "(" + myItem.getID() + ")" + " [CMD: "+myItem.getDef().getCommand()+"] at: " + player.getX() + "/" + player.getY()));

	    player.setStatus(Action.USING_INVITEM_ON_GITEM);
	    World.getWorld().getDelayedEventHandler().add(new WalkToPointEvent(player, location, 1, false) {
		public void arrived() {
		    if (owner.isBusy() || owner.isRanging() || !tile.hasItem(item) || !owner.nextTo(item) || owner.getStatus() != Action.USING_INVITEM_ON_GITEM) {
			return;
		    }
		    if (myItem == null || item == null)
			return;
		    switch (item.getID()) {
		    case 23:
			if (myItem.getID() == 135) {
			    if (owner.getInventory().remove(myItem) < 0)
				return;
			    owner.getActionSender().sendMessage("You put the flour in the pot.");
			    Bubble bubble = new Bubble(owner, 135);
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
			    world.unregisterItem(item);
			    owner.getInventory().add(new InvItem(136));
			    owner.getActionSender().sendInventory();
			    return;
			}
		    case 14:
		    case 632:
		    case 633:
		    case 634:
		    case 635:
		    case 636:
		    	handleFireMaking();
		    	break;
		    default:
			owner.getActionSender().sendMessage("Nothing interesting happens.");
			return;
		    }
		}

		private void handleFireMaking() {
			handleFireMaking((int)Math.ceil(owner.getMaxStat(11) / 10));
			
		}
		private void handleFireMaking(int tries) {
			final int retries = --tries;
			final FiremakingDef def = EntityHandler.getFiremakingDef(item.getID());
			if (!itemId(new int[] { 166 }) || def == null) {
			    owner.getActionSender().sendMessage("Nothing interesting happens.");
			    return;
			}
			if (owner.getCurStat(11) < def.getRequiredLevel()) {
			    owner.getActionSender().sendMessage("You need at least " + def.getRequiredLevel() + " firemaking to light these logs.");
			    return;
			}
			owner.setBusy(true);
			Bubble bubble = new Bubble(owner, 166);
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
			owner.getActionSender().sendMessage("You attempt to light the logs...");
			World.getWorld().getDelayedEventHandler().add(new ShortEvent(owner) {
			    public void action() {
				if (Formulae.lightLogs(def, owner.getCurStat(11))) {
				    owner.getActionSender().sendMessage("They catch fire and start to burn.");
				    world.unregisterItem(item);
				    final GameObject fire = new GameObject(item.getLocation(), 97, 0, 0);
				    world.registerGameObject(fire);
				    world.getDelayedEventHandler().add(new DelayedEvent(null, def.getLength()) {
					public void run() {
					    if (tile.hasGameObject() && tile.getGameObject().equals(fire)) {
						world.unregisterGameObject(fire);
						world.registerItem(new Item(181, tile.getX(), tile.getY(), 1, null));
					    }
					    matchRunning = false;
					}
				    });
				    owner.incExp(11, Formulae.firemakingExp(owner.getMaxStat(11), def.getExp()), true);
				    owner.getActionSender().sendStat(11);
				    owner.setBusy(false);
				} else {
				    owner.getActionSender().sendMessage("You fail to light them.");
				    owner.setBusy(false);
				    if(retries > 0) {
				    	handleFireMaking(retries);
				    }
				}
				
			    }
			});
		}

		private boolean itemId(int[] ids) {
		    return DataConversions.inArray(ids, myItem.getID());
		}
	    });
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

}
