package org.lupus_regnum.gs.event;

import java.util.ArrayList;

import org.lupus_regnum.config.Constants;
import org.lupus_regnum.config.Formulae;
import org.lupus_regnum.gs.model.InvItem;
import org.lupus_regnum.gs.model.Item;
import org.lupus_regnum.gs.model.Mob;
import org.lupus_regnum.gs.model.Npc;
import org.lupus_regnum.gs.model.PathGenerator;
import org.lupus_regnum.gs.model.Player;
import org.lupus_regnum.gs.model.Projectile;
import org.lupus_regnum.gs.model.World;
import org.lupus_regnum.gs.states.Action;
import org.lupus_regnum.gs.tools.DataConversions;



public class RangeEvent extends DelayedEvent {
    private Mob affectedMob;
    public int[][] allowedArrows = { { 189, 11, 638, 574, 639 }, // Shortbow
    { 188, 11, 638, 574, 639 }, // Longbow
    { 649, 11, 638, 574, 639 }, // Oak Shortbow
    { 648, 11, 638, 574, 639, 640, 641 }, // Oak Longbow
    { 651, 11, 638, 574, 639, 640, 641 }, // Willow Shortbow
    { 650, 11, 638, 574, 639, 640, 641, 642, 643 }, // Willow Longbow
    { 653, 11, 638, 574, 639, 640, 641, 642, 643 }, // Maple Shortbow
    { 652, 11, 638, 574, 639, 640, 641, 642, 643, 644, 645 }, // Maple Longbow
    { 655, 11, 638, 574, 639, 640, 641, 642, 643, 644, 645 }, // Yew Shortbow
    { 654, 11, 638, 574, 639, 640, 641, 642, 643, 644, 645, 646, 647 }, // Yew Longbow
    { 657, 11, 638, 574, 639, 640, 641, 642, 643, 644, 645, 646, 647 }, // Magic Shortbow
    { 656, 11, 638, 574, 639, 640, 641, 642, 643, 644, 645, 646, 647 } // Magic Longbow
    };

    private boolean firstRun = true;

    public RangeEvent(Player owner, Mob affectedMob) {
	super(owner, 2000);
	this.affectedMob = affectedMob;
    }

    public boolean equals(Object o) {
	if (o instanceof RangeEvent) {
	    RangeEvent e = (RangeEvent) o;
	    return e.belongsTo(owner);
	}
	return false;
    }

    public Mob getAffectedMob() {
	return affectedMob;
    }

    private Item getArrows(int id) {
	for (Item i : world.getTile(affectedMob.getLocation()).getItems()) {
	    if (i.getID() == id && i.visibleTo(owner) && !i.isRemoved()) {
		return i;
	    }
	}
	return null;
    }

    public void run() {
	int bowID = owner.getRangeEquip();
	if (!owner.loggedIn() || (affectedMob instanceof Player && !((Player) affectedMob).loggedIn()) || affectedMob.getHits() <= 0 || !owner.checkAttack(affectedMob, true) || bowID < 0) {
	    owner.resetRange();
	    return;
	}
	if (owner.withinRange(affectedMob, 5)) {
	    if (owner.isFollowing()) {
		owner.resetFollowing();
	    }
	    if (!owner.finishedPath()) {
		owner.resetPath();
	    }
	} else {
	    owner.setFollowing(affectedMob);
	    return;
	}
    if (!new PathGenerator(owner.getX(), owner.getY(), affectedMob.getX(), affectedMob.getY()).isValid()) {
		owner.getActionSender().sendMessage("I can't get a clear shot from here");
		owner.resetPath();
		return;
	    }
	boolean xbow = DataConversions.inArray(Formulae.xbowIDs, bowID);
	int arrowID = -1;
	for (int aID : (xbow ? Formulae.boltIDs : Formulae.arrowIDs)) {
	    int slot = owner.getInventory().getLastIndexById(aID);
	    if (slot < 0) {
		continue;
	    }
	    InvItem arrow = owner.getInventory().get(slot);
	    if (arrow == null) { // This shouldn't happen
		continue;
	    }
	    arrowID = aID;
	    if(owner.getLocation().inWilderness() && Constants.GameServer.F2P_WILDY) {
		if(arrowID != 11 && arrowID != 190) {
		    owner.getActionSender().sendMessage("You may not use P2P (Member Item) Arrows in the F2P Wilderness");
		    owner.resetRange();
		    return;
		}
	    }
	    int newAmount = arrow.getAmount() - 1;
	    if (!xbow && arrowID > 0) {
		int temp = -1;

		for (int i = 0; i < allowedArrows.length; i++)
		    if (allowedArrows[i][0] == owner.getRangeEquip())
			temp = i;

		boolean canFire = false;
		for (int i = 0; i < allowedArrows[temp].length; i++)
		    if (allowedArrows[temp][i] == aID)
			canFire = true;

		if (!canFire) {
		    owner.getActionSender().sendMessage("Your arrows are too powerful for your Bow.");
		    owner.resetRange();
		    return;
		}
	    }
	    
	    if (newAmount <= 0) {
		owner.getInventory().remove(slot);
		owner.getActionSender().sendInventory();
	    } else {
		arrow.setAmount(newAmount);
		owner.getActionSender().sendUpdateItem(slot);
	    }
	    break;
	}
	if(arrowID == 574 || arrowID == 639 || arrowID == 641 || arrowID == 643 || arrowID == 645|| arrowID == 647 && !((Player)affectedMob).poisoned){ //Poison Arrows
        if(DataConversions.random(0, 6) == 1){
            if(((Player)affectedMob).poisoned){ // prevent multiple poisons
               return;
            }
               ((Player)affectedMob).getActionSender().sendMessage("@gr2@You have been poisoned!");
               ((Player)affectedMob).poisoned = true;
			   ((Player)affectedMob).isPoisoned();
        }
    }
	if (arrowID < 0) {
	    owner.getActionSender().sendMessage("You have run out of " + (xbow ? "bolts" : "arrows"));
	    owner.resetRange();
	    return;
	}
	if (affectedMob.isPrayerActivated(13)) {
		if(!owner.shouldRangePass()) {
		    owner.getActionSender().sendMessage("Your missile got blocked");
		    return;
		}
	}
	// if(owner.getRangeEquip())

	int damage = Formulae.calcRangeHit(owner.getCurStat(4), owner.getRangePoints(), affectedMob.getArmourPoints(), arrowID);
	
	    if (affectedMob instanceof Npc) {
		Npc npc = (Npc) affectedMob;
		if(damage > 1 && npc.getID() == 477)
		    damage = damage / 2;
	    }
	if (!Formulae.looseArrow(damage)) {
	    Item arrows = getArrows(arrowID);
	    if (arrows == null) {
		world.registerItem(new Item(arrowID, affectedMob.getX(), affectedMob.getY(), 1, owner));
	    } else {
		arrows.setAmount(arrows.getAmount() + 1);
	    }
	}
	if (firstRun) {
	    firstRun = false;
	    if (affectedMob instanceof Player) {
		((Player) affectedMob).getActionSender().sendMessage(owner.getUsername() + " is shooting at you!");
	    }
	}
	if (affectedMob instanceof Npc) {
	    Npc npc = (Npc) affectedMob;
	    npc.addRangeDamage(owner, damage);
	}
	Projectile projectile = new Projectile(owner, affectedMob, 2);

	ArrayList<Player> playersToInform = new ArrayList<Player>();
	playersToInform.addAll(owner.getViewArea().getPlayersInView());
	playersToInform.addAll(affectedMob.getViewArea().getPlayersInView());
	for (Player p : playersToInform) {
	    p.informOfProjectile(projectile);
	}

	if (System.currentTimeMillis() - affectedMob.lastTimeShot > 500) {
	    affectedMob.lastTimeShot = System.currentTimeMillis();
	    affectedMob.setLastDamage(damage);
	    int newHp = affectedMob.getHits() - damage;
	    affectedMob.setHits(newHp);
	    for (Player p : playersToInform) {
		p.informOfModifiedHits(affectedMob);
	    }
	    if (affectedMob instanceof Player) {
		Player affectedPlayer = (Player) affectedMob;
		affectedPlayer.getActionSender().sendStat(3);
	    }
	    owner.getActionSender().sendSound("shoot");
	    owner.setArrowFired();
	    if (newHp <= 0) {
			affectedMob.killedBy(owner, false);
			owner.resetRange();
	    }
	    else {
		if (owner instanceof Player && affectedMob instanceof Npc) // We're
		// ranging
		// an
		// NPC,
		// so
		// make
		// it
		// chase
		// the
		// player.
		{
		    final Npc npc = (Npc) affectedMob;
		    final Player player = (Player) owner;

		    if (npc.isBusy() || npc.getChasing() != null)
			return;

		    npc.resetPath();
		    npc.setChasing(player);

		    // Radius is 0 to prevent wallhacking by NPCs. Easiest
		    // method I
		    // can come up with for now.
		    World.getWorld().getDelayedEventHandler().add(new WalkMobToMobEvent(affectedMob, owner, 0) {
			public void arrived() {
			    if (affectedMob.isBusy() || player.isBusy()) {
				npc.setChasing(null);
				return;
			    }

			    npc.resetPath();
			    player.setBusy(true);
			    player.resetPath();
			    player.resetAll();
			    player.setStatus(Action.FIGHTING_MOB);
			    player.getActionSender().sendSound("underattack");
			    player.getActionSender().sendMessage("You are under attack!");

			    npc.setLocation(player.getLocation(), true);
			    for (Player p : npc.getViewArea().getPlayersInView())
				p.removeWatchedNpc(npc);

			    player.setBusy(true);
			    player.setSprite(9);
			    player.setOpponent(npc);
			    player.setCombatTimer();

			    npc.setBusy(true);
			    npc.setSprite(8);
			    npc.setOpponent(player);
			    npc.setCombatTimer();

			    npc.setChasing(null);

			    FightEvent fighting = new FightEvent(player, npc, true);
			    fighting.setLastRun(0);
			    World.getWorld().getDelayedEventHandler().add(fighting);
			}

			public void failed() {
			    npc.setChasing(null);
			}
		    });
		}
	    }
	}
    }
}
