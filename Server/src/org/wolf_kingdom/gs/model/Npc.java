package org.wolf_kingdom.gs.model;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;

import org.wolf_kingdom.config.Constants;
import org.wolf_kingdom.config.Formulae;
import org.wolf_kingdom.gs.event.DelayedEvent;
import org.wolf_kingdom.gs.event.FightEvent;
import org.wolf_kingdom.gs.external.EntityHandler;
import org.wolf_kingdom.gs.external.ItemDropDef;
import org.wolf_kingdom.gs.external.NPCDef;
import org.wolf_kingdom.gs.external.NPCLoc;
import org.wolf_kingdom.gs.plugins.PluginHandler;
import org.wolf_kingdom.gs.states.Action;
import org.wolf_kingdom.gs.states.CombatState;
import org.wolf_kingdom.gs.tools.DataConversions;




public class Npc extends Mob {

	public int getItemid() {
		return itemid;
	}

	public void setItemid(int itemid) {
		this.itemid = itemid;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public boolean isHasArmor() {
		return hasArmor;
	}

	public void setHasArmor(boolean hasArmor) {
		this.hasArmor = hasArmor;
	}

	public boolean isUndead() {
		return undead;
	}

	public void setUndead(boolean undead) {
		this.undead = undead;
	}

	public boolean isRan() {
		return ran;
	}
	private boolean ran = false;
	/**
	 * The identifier for the NPC block event
	 */
	private static final int BLOCKED_IDENTIFIER = 69;
	/**
	 * World instance
	 */
	private static final World world = World.getWorld();
	/**
	 * The player currently blocking this npc
	 */
	private Player blocker = null;
	/**
	 * DelayedEvent used for unblocking an npc after set time
	 */
	private DelayedEvent chaseTimeout = null;
	/**
	 * Player (if any) that this npc is chasing
	 */
	private Player chasing = null;
	public boolean confused = false;
	/**
	 * The npcs hitpoints
	 */
	private int curHits;
	public boolean cursed = false;
	/**
	 * The definition of this npc
	 */
	private NPCDef def;
	
	private boolean goingToAttack = false;
	/**
	 * The location of this npc
	 */
	private NPCLoc loc;

	public boolean hasRan() {
		return ran;
	}

	public void setRan(boolean ran) {
		this.ran = ran;
	}

	public Player getBlocker() {
		return blocker;
	}

	public void setBlocker(Player blocker) {
		this.blocker = blocker;
	}

	public DelayedEvent getChaseTimeout() {
		return chaseTimeout;
	}

	public void setChaseTimeout(DelayedEvent chaseTimeout) {
		this.chaseTimeout = chaseTimeout;
	}

	public boolean isConfused() {
		return confused;
	}

	public void setConfused(boolean confused) {
		this.confused = confused;
	}

	public int getCurHits() {
		return curHits;
	}

	public void setCurHits(int curHits) {
		this.curHits = curHits;
	}

	public boolean isCursed() {
		return cursed;
	}

	public void setCursed(boolean cursed) {
		this.cursed = cursed;
	}

	private Player isGoingToAttack;
               
        public boolean isGoingToAttack() {
		return goingToAttack;
	}
        
        public boolean isGoingToAttack(Player player) {
            if (!isGoingToAttack()) return false;
            return goingToAttack = goingToAttack;
        }

	public void setGoingToAttack(boolean goingToAttack) {
		this.goingToAttack = goingToAttack;
	}
        
        public void teleport(int x, int y, boolean bubble) {
		if(bubble && PluginHandler.getPluginHandler().blockDefaultAction("Teleport", new Object[] { this })) {
			return;
		}
		Mob opponent = super.getOpponent();
		if (inCombat()) {
			resetCombat(CombatState.ERROR);
		}
		if (opponent != null) {
			opponent.resetCombat(CombatState.ERROR);
		}
		/*for (Object o : getWatchedPlayers().getAllEntities()) {
			Player p = ((Player) o);
			if (bubble) {
				p.getActionSender().sendTeleBubble(getX(), getY(), false);
			}
			p.removeWatchedPlayer(this);
		}
		if (bubble) {
			actionSender.sendTeleBubble(getX(), getY(), false);
		}*/
		setLocation(Point.location(x, y), true);
		resetPath();
		//actionSender.sendWorldInfo();
                //updateViewedPlayers();
                //updateViewedObjects();
	}

	/**
	 * Event to handle following
	 */
	private DelayedEvent npcFollowEvent;
        /**
	 * Who we are currently following (if anyone)
	 */
	private Player following;
        
        
        public boolean isFollowing() {
		return npcFollowEvent != null && following != null;
	}
        
        public boolean isFollowing(Player player) {
            if (!isFollowing()) return false;
            return following.equals(player);
        }
        
        public void resetFollowing() {
		following = null;
		if (npcFollowEvent != null) {
			npcFollowEvent.stop();
			npcFollowEvent = null;
		}
		resetPath();
	}
        
        public void setFollowing(final Player player, final int radius) {
		if (isFollowing()) {
			resetFollowing();
		}
		following = player; //pet system
		npcFollowEvent = new DelayedEvent(null, 500) {
			public void run() {
				if (player.isRemoved()) {
					resetFollowing();
                                        unblock();
                                        world.unregisterNpc(Npc.this);
                                        remove();
                                        player.getInventory().remove(new InvItem(1231, 1));
                                        player.getInventory().add(new InvItem(1222, 1));
                                        //System.out.println(following + "'s " + Npc.this + " has been returned to the crystal via Npc.java.");
                                } else if (!Npc.this.withinRange(player) && System.currentTimeMillis() - player.getLastMoved() > 1000) {
				        Npc.this.teleport(player.getX(), player.getY(), false);
                                        //System.out.println(following + "'s " + Npc.this + " has been teleported");
                                        Npc.this.resetPath();
                                } else if (Npc.this.isBusy()) {
                                        Npc.this.resetPath();
                                } else if (!Npc.this.finishedPath() && Npc.this.withinRange(player, radius)) {
					Npc.this.resetPath();
				} else if (Npc.this.finishedPath() && !Npc.this.withinRange(player, radius + 1)) {
					Npc.this.setPath(new Path(Npc.this.getX(), Npc.this.getY(), player.getX(), player.getY()));
				}
			}
		};
		World.getWorld().getDelayedEventHandler().add(npcFollowEvent);
	}
        
        public boolean withinRange(Entity e) {
		int xDiff = location.getX() - e.getLocation().getX();
		int yDiff = location.getY() - e.getLocation().getY();
		return xDiff <= 16 && xDiff >= -15 && yDiff <= 16 && yDiff >= -15;
	}
        
        public boolean isShouldRespawn() {
		return shouldRespawn;
	}

	public void setShouldRespawn(boolean shouldRespawn) {
		this.shouldRespawn = shouldRespawn;
	}

	public DelayedEvent getTimeout() {
		return timeout;
	}

	public void setTimeout(DelayedEvent timeout) {
		this.timeout = timeout;
	}

	public boolean isWeakend() {
		return weakend;
	}

	public void setWeakend(boolean weakend) {
		this.weakend = weakend;
	}

	public void setDef(NPCDef def) {
		this.def = def;
	}

	public void setLoc(NPCLoc loc) {
		this.loc = loc;
	}
	/**
	 * Should this npc respawn once it has been killed?
	 **/
	public boolean shouldRespawn = true;
	/**
	 * DelayedEvent used for unblocking an npc after set time
	 */
	private DelayedEvent timeout = null;
	
	public boolean weakend = false;

	public int itemid = -1;
	
	public int exp = -1; // used for events.

	public Npc(int id, int startX, int startY, int minX, int maxX, int minY, int maxY) {
		this(new NPCLoc(id, startX, startY, minX, maxX, minY, maxY));
	}

	public Npc(NPCLoc loc) {
		for (int i : Constants.GameServer.UNDEAD_NPCS) {
			if (loc.getId() == i) {
				this.undead = true;
			}
		}
		for (int i : Constants.GameServer.ARMOR_NPCS) {
			if (loc.getId() == i) {
				this.hasArmor = true;
			}
		}

		def = EntityHandler.getNpcDef(loc.getId());
		curHits = def.getHits();

		this.loc = loc;
		super.setID(loc.getId());
		super.setLocation(Point.location(loc.startX(), loc.startY()), true);
		super.setCombatLevel(Formulae.getCombatLevel(def.getAtt(), def.getDef(), def.getStr(), def.getHits(), 0, 0, 0));
		if (this.loc.getId() == 189 || this.loc.getId() == 53) {
			this.def.aggressive = true;
		}
	
	}

	public synchronized void blockedBy(Player player) {
		blocker = player;
		player.setNpc(this);
		setBusy(true);
		boolean eventExists = false;

		if (timeout != null) {
			ArrayList<DelayedEvent> events = World.getWorld().getDelayedEventHandler().getEvents();

			// Damn punk, gettin threading problems here without it synced.
			try {
				synchronized (events) {
					for (DelayedEvent e : events) {
						if (e.is(timeout)) {
							e.updateLastRun(); // If the event still exists,
							// reset its
							// delay time.
							eventExists = true;
						}
					}
					notifyAll();
				}
			} catch (ConcurrentModificationException cme) {
			}
		}

		if (eventExists) {
			return;
		}

		timeout = new DelayedEvent(null, 15000) {

			public Object getIdentifier() {
				return new Integer(BLOCKED_IDENTIFIER);
			}

			public void run() {
				unblock();
				matchRunning = false;
			}
		};

		World.getWorld().getDelayedEventHandler().add(timeout);
	}

	private Player findVictim() {
		if (goingToAttack) {
			return null;
		}
		if (hasRan()) {
			return null;
		}
		long now = System.currentTimeMillis();
		if (getChasing() != null) {
			return null;
		}
		ActiveTile[][] tiles = getViewArea().getViewedArea(2, 2, 2, 2);
		for (int x = 0; x < tiles.length; x++) {
			for (int y = 0; y < tiles[x].length; y++) {
				ActiveTile t = tiles[x][y];
				if (t != null) {
					for (Player p : t.getPlayers()) {
						if (p.inCombat()) {
							continue;
						}
						if (p.isBusy() || p.isNonaggro() || now - p.getCombatTimer() < (p.getCombatState() == CombatState.RUNNING || p.getCombatState() == CombatState.WAITING ? 3000 : 1500) || !p.nextTo(this) || !p.getLocation().inBounds(loc.minX - 4, loc.minY - 4, loc.maxX + 4, loc.maxY + 4)) {
							continue;
						}
						if(!(p.isBusy() || p.isNonaggro() || now - p.getCombatTimer() < (p.getCombatState() == CombatState.RUNNING || p.getCombatState() == CombatState.WAITING ? 3000 : 1500) || !p.nextTo(this))) {
							if (this.getLocation().inWilderness()) {
								return p;
							}
							if(p.getCombatLevel() <= ((this.getCombatLevel() * 2) + 1)) {
								return p;
							}
						}

		

					}
				}
			}
		}
		return null;
	}

	public int getArmourPoints() {
		return 1;
	}

	public int getAttack() {
		return def.getAtt();
	}

	public Player getChasing() {
		return chasing;
	}

	public int getCombatStyle() {
		return 0;
	}

	public NPCDef getDef() {
		return EntityHandler.getNpcDef(getID());
	}

	public int getDefense() {
		return def.getDef();
	}

	public int getHits() {
		return curHits;
	}

	public NPCLoc getLoc() {
		return loc;
	}

	public int getStrength() {
		return def.getStr();
	}

	public int getWeaponAimPoints() {
		return 1;
	}

	public int getWeaponPowerPoints() {
		return 1;
	}
        
	public void killedBy(Mob mob, boolean stake) {
		if (mob instanceof Player) {
			Player player = (Player) mob;
			player.getActionSender().sendSound("victory");
		}

		Mob opponent = super.getOpponent();
		if (opponent != null) {
			opponent.resetCombat(CombatState.WON);
		}

		resetCombat(CombatState.LOST);
		world.unregisterNpc(this);
		remove();

		Player owner = null;
		if( mob instanceof Player) {
			owner = handleLootAndXpDistribution((Player) mob);
			if(PluginHandler.getPluginHandler().blockDefaultAction("PlayerKilledNpc", new Object[]{ owner, this})) {
				return;
			}
		}
		
		ItemDropDef[] drops = def.getDrops();

		int total = 0;
		for (ItemDropDef drop : drops) {
			total += drop.getWeight();
		}
		//
		int hit = DataConversions.random(0, total);
		total = 0;
		if (!this.getDef().name.equalsIgnoreCase("ghost")) {
			if (this.getCombatLevel() >= 90 && Constants.GameServer.MEMBER_WORLD) {
				if (Formulae.Rand(0, 3000) == 500) {
					if (Formulae.Rand(0, 1) == 1) {
						world.registerItem(new Item(1276, getX(), getY(), 1, owner));
					} else {
						world.registerItem(new Item(1277, getX(), getY(), 1, owner));
					}
				}
			}
			for (ItemDropDef drop : drops) {
				if (drop == null) {
					continue;
				}
				if (drop.getWeight() == 0) {
					world.registerItem(new Item(drop.getID(), getX(), getY(), drop.getAmount(), owner));
					continue;
				}
				if (hit >= total && hit < (total + drop.getWeight())) {
					if (drop.getID() != -1) {
						world.registerItem(new Item(drop.getID(), getX(), getY(), drop.getAmount(), owner));
						break;
					}
				}
				total += drop.getWeight();
			}
		}
	} 


	public void remove() {
		if (!removed && shouldRespawn && def.respawnTime() > 0) {
			World.getWorld().getDelayedEventHandler().add(new DelayedEvent(null, def.respawnTime() * 1000) {

				public void run() {
					world.registerNpc(new Npc(loc));
					matchRunning = false;
				}
			});
		}

		removed = true;
		
	}

	public void setChasing(Player player) {

		this.chasing = player;
		goingToAttack = true;

		if (player == null) {
			this.chasing = null;
			goingToAttack = false;
			return;
		}

		chaseTimeout = new DelayedEvent(null, 15000) {

			public void run() {

				goingToAttack = false;
				setChasing(null);
				matchRunning = false;
			}
		};

		World.getWorld().getDelayedEventHandler().add(chaseTimeout);
	}

	public void setHits(int lvl) {
		if (lvl <= 0) {
			lvl = 0;
		}

		curHits = lvl;
	}

	public void setRespawn(boolean respawn) {
		shouldRespawn = respawn;
	}

	public void unblock() {
		if (blocker != null) {
			blocker.setNpc(null);
			blocker = null;
		}

		if (timeout == null) {
			return;
		}

		goingToAttack = false;
		setBusy(false);
		timeout.stop();
		timeout = null;
	}

	public void updatePosition() {
            if (following != null) {
                super.updatePosition();
                return;
            }
		long now = System.currentTimeMillis();
		Player victim = findVictim();
		if (!isBusy() && def.isAggressive() && now - getCombatTimer() > 3000 && victim != null) {
			resetPath();
			victim.resetPath();
			victim.resetAll();
			victim.setStatus(Action.FIGHTING_MOB);
			victim.getActionSender().sendSound("underattack");
			victim.getActionSender().sendMessage("You are under attack!");


			setLocation(victim.getLocation(), true);
			for (Player p : getViewArea().getPlayersSectorA()) {
				p.removeWatchedNpc(this);
			}
                        for (Player p : getViewArea().getPlayersSectorB()) {
				p.removeWatchedNpc(this);
			}
                        for (Player p : getViewArea().getPlayersSectorC()) {
				p.removeWatchedNpc(this);
			}
                        for (Player p : getViewArea().getPlayersSectorD()) {
				p.removeWatchedNpc(this);
			}

			victim.setBusy(true);
			victim.setSprite(9);
			victim.setOpponent(this);
			victim.setCombatTimer();

			setBusy(true);
			setSprite(8);
			setOpponent(victim);
			setCombatTimer();
			FightEvent fighting = new FightEvent(victim, this, true);
			fighting.setLastRun(0);
			World.getWorld().getDelayedEventHandler().add(fighting);
		}

		if (now - lastMovement > 2200) {
			lastMovement = now;
			int rand = DataConversions.random(0, 1);
			if (!isBusy() && finishedPath() && rand == 1 && !this.isRemoved()) {
				int newX = DataConversions.random(loc.minX(), loc.maxX());
				int newY = DataConversions.random(loc.minY(), loc.maxY());
				super.setPath(new Path(getX(), getY(), newX, newY));
			}
		}

		super.updatePosition();
	}

	@Override
	public String toString() {
		return "[NPC:" + EntityHandler.getNpcDef(id).getName() + "]";
	}
	public boolean hasArmor = false;
	public boolean undead = false;

	/**
	 * Holds players that did damage with combat
	 */
	private Map<Long, Integer> combatDamagers = new HashMap<Long, Integer>();
	/**
	 * Holds players that did damage with range
	 */
	private Map<Long, Integer> rangeDamagers = new HashMap<Long, Integer>();
	/**
	 * Holds players that did damage with mage
	 */
	private Map<Long, Integer> mageDamagers = new HashMap<Long, Integer>();
	
	/**
	 * Combat damage done by player p
	 * @param p
	 * @return
	 */
	public Integer getCombatDamageDoneBy(Player p) {
		if(p == null) {
			return 0;
		}
		if(!combatDamagers.containsKey(p.getUsernameHash())) {
			return 0;
		}
		int dmgDone = combatDamagers.get(p.getUsernameHash());
		return (dmgDone > this.getDef().getHits() ? this.getDef().getHits() : dmgDone);
	}
	/**
	 * Range damage done by player p
	 * @param p
	 * @return
	 */
	public Integer getRangeDamageDoneBy(Player p) {
		if(p == null) {
			return 0;
		}
		if(!rangeDamagers.containsKey(p.getUsernameHash())) {
			return 0;
		}
		int dmgDone = rangeDamagers.get(p.getUsernameHash());
		return (dmgDone > this.getDef().getHits() ? this.getDef().getHits() : dmgDone);
	}
	/**
	 * Mage damage done by player p
	 * @param p
	 * @return
	 */
	public Integer getMageDamageDoneBy(Player p) {
		if(p == null) {
			return 0;
		}
		if(!mageDamagers.containsKey(p.getUsernameHash())) {
			return 0;
		}
		int dmgDone = mageDamagers.get(p.getUsernameHash());
		return (dmgDone > this.getDef().getHits() ? this.getDef().getHits() : dmgDone);
	}
	/**
	 * Iterates over combatDamagers map and returns the keys
	 * @return
	 */
	public ArrayList<Long> getCombatDamagers() {
		return new ArrayList<Long>(combatDamagers.keySet());
	}
	/**
	 * Iterates over rangeDamagers map and returns the keys
	 * @return
	 */
	public ArrayList<Long> getRangeDamagers() {
		return new ArrayList<Long>(rangeDamagers.keySet());
	}
	/**
	 * Iterates over mageDamagers map and returns the keys
	 * @return
	 */
	public ArrayList<Long> getMageDamagers() {
		return new ArrayList<Long>(mageDamagers.keySet());
	}
	/**
	 * Adds combat damage done by a player
	 * @param p
	 * @param damage
	 */
	public void addCombatDamage(Player p, int damage) {
		if(combatDamagers.containsKey(p.getUsernameHash())) {
			combatDamagers.put(p.getUsernameHash(), combatDamagers.get(p.getUsernameHash()) + damage);
		}
		else {
			combatDamagers.put(p.getUsernameHash(), damage);
		}
	}
	/**
	 * Adds range damage done by a player
	 * @param p
	 * @param damage
	 */
	public void addRangeDamage(Player p, int damage) {
		if(rangeDamagers.containsKey(p.getUsernameHash())) {
			rangeDamagers.put(p.getUsernameHash(), rangeDamagers.get(p.getUsernameHash()) + damage);
		}
		else {
			rangeDamagers.put(p.getUsernameHash(), damage);
		}
	}
	/**
	 * Adds mage damage done by a player
	 * @param p
	 * @param damage
	 */
	public void addMageDamage(Player p, int damage) {
		if(mageDamagers.containsKey(p.getUsernameHash())) {
			mageDamagers.put(p.getUsernameHash(), mageDamagers.get(p.getUsernameHash()) + damage);
		}
		else {
			mageDamagers.put(p.getUsernameHash(), damage);
		}
	}
	
	/**
	 * Distributes the XP from this monster and the loot 
	 * @param attacker the person that "finished off" the npc
	 * @return the player who did the most damage / should get the loot
	 */
	public Player handleLootAndXpDistribution(Player attacker) {
		Player toLoot = attacker;
		int mostDamageDone = 0;
		int exp = DataConversions.roundUp(Formulae.combatExperience(this) / 4D);
		
		for (Long playerHash : getCombatDamagers()) {
			int newXP = 0;
			Player p = World.getWorld().getPlayer(playerHash);
			if(p == null)
				continue;
			int dmgDoneByPlayer = getCombatDamageDoneBy(p);


			if (dmgDoneByPlayer > mostDamageDone) {
				toLoot = p;
				mostDamageDone = dmgDoneByPlayer;
			}
			
			newXP = (exp * dmgDoneByPlayer) / this.getDef().hits;
			switch (p.getCombatStyle()) {
				case 0:
				    for (int x = 0; x < 3; x++) {
						p.incExp(x, newXP, true, true, true);
						p.getActionSender().sendStat(x);
				    }
				    break;
				case 1:
				    p.incExp(2, newXP * 3, true, true, true);
				    p.getActionSender().sendStat(2);
				    break;
				case 2:
				    p.incExp(0, newXP * 3, true, true, true);
				    p.getActionSender().sendStat(0);
				    break;
				case 3:
				    p.incExp(1, newXP * 3, true, true, true);
				    p.getActionSender().sendStat(1);
				    break;
			}
			p.incExp(3, newXP, true, true, true);
			p.getActionSender().sendStat(3);
		}
		for (Long playerHash : getRangeDamagers()) {
			
			int newXP = 0;
			Player p = World.getWorld().getPlayer(playerHash);
			int dmgDoneByPlayer = getRangeDamageDoneBy(p);
			if(p == null)
				continue;
		
			if (dmgDoneByPlayer > mostDamageDone) {
				toLoot = p;
				mostDamageDone = dmgDoneByPlayer;
			}
			newXP = (exp * dmgDoneByPlayer) / this.getDef().hits;
			p.incExp(4, newXP * 4, true, true, true);
		    p.getActionSender().sendStat(4);
		}
		for (Long playerHash : getMageDamagers()) {
			
			Player p = World.getWorld().getPlayer(playerHash);
			
			int dmgDoneByPlayer = getMageDamageDoneBy(p);
			if(p == null)
				continue;
			
			if (dmgDoneByPlayer > mostDamageDone) {
				toLoot = p;
				mostDamageDone = dmgDoneByPlayer;
			}
		}
		return toLoot;
	}
	
	/**
    * Npc Owner
    * @author NotPro
    */		
	private String owner = null;
	public Npc(NPCLoc loc, String owners) {
		for (int i : Constants.GameServer.UNDEAD_NPCS) {
			if (loc.getId() == i) {
				this.undead = true;
			}
		}
		for (int i : Constants.GameServer.ARMOR_NPCS) {
			if (loc.getId() == i) {
				this.hasArmor = true;
			}
		}
		def = EntityHandler.getNpcDef(loc.getId());
		curHits = def.getHits();

		this.loc = loc;
		this.owner = owners;
		super.setID(loc.getId());
		super.setLocation(Point.location(loc.startX(), loc.startY()), true);
		super.setCombatLevel(Formulae.getCombatLevel(def.getAtt(), def.getDef(), def.getStr(), def.getHits(), 0, 0, 0));
		if (this.loc.getId() == 189 || this.loc.getId() == 53) {
			this.def.aggressive = true;
		}
	}
   public Npc(Point location, int id, String owner) {
      this(new NPCLoc(id, location.getX(), location.getY(), owner));
   }
   public String getOwner() {
      return loc.owner;
   }
}
