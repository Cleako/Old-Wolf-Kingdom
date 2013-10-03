package org.lupus_regnum.gs.phandler.client;

import java.util.logging.Logger;
import org.apache.mina.common.IoSession;
import org.lupus_regnum.gs.connection.Packet;
import static org.lupus_regnum.gs.event.DelayedEvent.world;
import org.lupus_regnum.gs.event.ShortEvent;
import org.lupus_regnum.gs.event.WalkToMobEvent;
import org.lupus_regnum.gs.model.Bubble;
import org.lupus_regnum.gs.model.InvItem;
import org.lupus_regnum.gs.model.Npc;
import org.lupus_regnum.gs.model.Player;
import org.lupus_regnum.gs.model.World;
import org.lupus_regnum.gs.model.snapshot.Activity;
import org.lupus_regnum.gs.phandler.PacketHandler;
import org.lupus_regnum.gs.states.Action;
import org.lupus_regnum.gs.tools.DataConversions;


public class InvUseOnNpc implements PacketHandler {
    /**
     * World instance
     */
    public static final World world = World.getWorld();

    public void handlePacket(Packet p, IoSession session) throws Exception {
	Player player = (Player) session.getAttachment();
	if (player.isBusy()) {
	    player.resetPath();
	    return;
	}
	player.resetAll();
	int sh = -125534;
	try {
	    sh = p.readShort();
	} catch (NullPointerException npe) {
	    return;
	}

	final Npc affectedNpc = world.getNpc(sh);
	final InvItem item = player.getInventory().get(p.readShort());
	if (affectedNpc == null || item == null) { // This shouldn't happen
	    return;
	}
	world.addEntryToSnapshots(new Activity(player.getUsername(), player.getUsername() + " used item " + item.getDef().getName() + "(" + item.getID() + ")" + " [CMD: "+item.getDef().getCommand()+"] ON A NPC "+ affectedNpc.getDef().getName() + "(" + affectedNpc.getID() + ")" + " at: " + player.getX() + "/" + player.getY()));

	player.setFollowing(affectedNpc);
	player.setStatus(Action.USING_INVITEM_ON_NPC);
	World.getWorld().getDelayedEventHandler().add(new WalkToMobEvent(player, affectedNpc, 1) {
	    public void arrived() {
		owner.resetPath();
		owner.resetFollowing();
		if (!owner.getInventory().contains(item) || owner.isBusy() || owner.isRanging() || !owner.nextTo(affectedNpc) || affectedNpc.isBusy() || owner.getStatus() != Action.USING_INVITEM_ON_NPC) {
		    return;
		}
		owner.resetAll();
		switch (affectedNpc.getID()) {
		case 2: // Sheep
		    if (!itemId(new int[] { 144 })) {
			owner.getActionSender().sendMessage("Nothing interesting happens.");
			return;
		    }
		    owner.setBusy(true);
		    affectedNpc.blockedBy(owner);
		    affectedNpc.resetPath();
		    showBubble();
		    owner.getActionSender().sendMessage("You attempt to shear the sheep");
		    World.getWorld().getDelayedEventHandler().add(new ShortEvent(owner) {
			public void action() {
			    if (DataConversions.random(0, 4) != 0) {
				owner.getActionSender().sendMessage("You get some wool");
				owner.getInventory().add(new InvItem(145, 1));
				owner.getActionSender().sendInventory();
			    } else {
				owner.getActionSender().sendMessage("The sheep manages to get away from you!");
			    }
			    owner.setBusy(false);
			    affectedNpc.unblock();
			}
		    });
		    break;
		case 203: // Baby blue dragon
		    if (!itemId(new int[] { 1231 })) {
			owner.getActionSender().sendMessage("Nothing interesting happens.");
			return;
		    }
                    /*else if (!affectedNpc.withinRange(owner, 5)) {
                        System.out.println("Pet owner out of range.");
                        World.getWorld().getDelayedEventHandler().add(new ShortEvent(owner) {
                            public void action() {
                                System.out.println("Removing pet.");
                                affectedNpc.resetPath();
                                affectedNpc.unblock();
                                owner.getInventory().remove(new InvItem(1231, 1));
                                world.unregisterNpc(affectedNpc);
                                affectedNpc.remove();
                                System.out.println("Pet removed.");
                                owner.getInventory().add(new InvItem(1222, 1));
                            }
                        });
                    }*/
                    else if (!affectedNpc.isFollowing(owner)) {
			owner.getActionSender().sendMessage("That's someone elses pet!");
                        return;
                    }
		    owner.setBusy(true);
		    affectedNpc.blockedBy(owner);
		    affectedNpc.resetPath();
		    showBubble();
		    owner.getActionSender().sendMessage("You attempt to put the baby blue dragon in the crystal.");
		    World.getWorld().getDelayedEventHandler().add(new ShortEvent(owner) {
			public void action() {
			    if (DataConversions.random(0, 4) != 0) {
				owner.getActionSender().sendMessage("You catch the baby blue dragon in the crystal.");
				owner.getInventory().remove(new InvItem(1231, 1));
				owner.getActionSender().sendInventory();
                                owner.setBusy(false);
				affectedNpc.unblock();
				world.unregisterNpc(affectedNpc);
				affectedNpc.remove();
                                owner.getInventory().add(new InvItem(1222, 1));
                                owner.getActionSender().sendInventory();
				} else {
				owner.getActionSender().sendMessage("The baby blue dragon manages to get away from you!");
                                owner.setBusy(false);
				affectedNpc.unblock();
                                affectedNpc.setFollowing(owner, 1);
				}
			}
		    });
		    break;
		case 217:// Cow
		case 6:
		    if (item.getID() != 21) {
			owner.getActionSender().sendMessage("Nothing interesting happens.");
			return;
		    }
		    owner.setBusy(true);
		    affectedNpc.blockedBy(owner);
		    affectedNpc.resetPath();
		    Bubble bubble = new Bubble(owner, 21);
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
		    owner.getActionSender().sendMessage("You try to milk the cow.");
		    World.getWorld().getDelayedEventHandler().add(new ShortEvent(owner) {
			public void action() {
			    if (DataConversions.random(0, 4) != 0) {
				if (owner.getInventory().remove(item) < 0)
				    return;
				owner.getActionSender().sendMessage("You get some milk");
				owner.getInventory().add(new InvItem(22, 1));
				owner.getActionSender().sendInventory();
			    } else {
				owner.getActionSender().sendMessage("The cow runs away before you could get any milk");
			    }
			    owner.setBusy(false);
			    affectedNpc.unblock();
			}
		    });
		    break;
		case 160:// Thrander
		    int newID;
		    switch (item.getID()) {
		    case 308: // Bronze top
			newID = 117;
			break;
		    case 312: // Iron top
			newID = 8;
			break;
		    case 309: // Steel top
			newID = 118;
			break;
		    case 313: // Black top
			newID = 196;
			break;
		    case 310: // Mithril top
			newID = 119;
			break;
		    case 311: // Adamantite top
			newID = 120;
			break;
		    case 407: // Rune top
			newID = 401;
			break;
		    case 117: // Bronze body
			newID = 308;
			break;
		    case 8: // Iron body
			newID = 312;
			break;
		    case 118: // Steel body
			newID = 309;
			break;
		    case 196: // Black body
			newID = 313;
			break;
		    case 119: // Mithril body
			newID = 310;
			break;
		    case 120: // Adamantite body
			newID = 311;
			break;
		    case 401: // Rune body
			newID = 407;
			break;
		    case 214: // Bronze skirt
			newID = 206;
			break;
		    case 215: // Iron skirt
			newID = 9;
			break;
		    case 225: // Steel skirt
			newID = 121;
			break;
		    case 434: // Black skirt
			newID = 248;
			break;
		    case 226: // Mithril skirt
			newID = 122;
			break;
		    case 227: // Adamantite skirt
			newID = 123;
			break;
		    case 406: // Rune skirt
			newID = 402;
			break;
		    case 206: // Bronze legs
			newID = 214;
			break;
		    case 9: // Iron legs
			newID = 215;
			break;
		    case 121: // Steel legs
			newID = 225;
			break;
		    case 248: // Black legs
			newID = 434;
			break;
		    case 122: // Mithril legs
			newID = 226;
			break;
		    case 123: // Adamantite legs
			newID = 227;
			break;
		    case 402: // Rune legs
			newID = 406;
			break;
		    default:
			owner.getActionSender().sendMessage("Nothing interesting happens.");
			return;
		    }
		    final InvItem newPlate = new InvItem(newID, 1);
		    owner.getActionSender().sendMessage("Thrander hammers the armour");
		    World.getWorld().getDelayedEventHandler().add(new ShortEvent(owner) {
			public void action() {
			    if (owner.getInventory().remove(item) > -1) {
				owner.getInventory().add(newPlate);
				owner.getActionSender().sendInventory();
			    }
			    affectedNpc.unblock();
			}
		    });
		    affectedNpc.blockedBy(owner);
		    break;
		default:
		    owner.getActionSender().sendMessage("Nothing interesting happens.");
		    break;
		}
	    }

	    private boolean itemId(int[] ids) {
		return DataConversions.inArray(ids, item.getID());
	    }

	    private void showBubble() {
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
	});
    }

}
