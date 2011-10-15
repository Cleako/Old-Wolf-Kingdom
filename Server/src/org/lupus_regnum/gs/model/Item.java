package org.lupus_regnum.gs.model;

import org.lupus_regnum.gs.event.DelayedEvent;
import org.lupus_regnum.gs.external.EntityHandler;
import org.lupus_regnum.gs.external.ItemDef;
import org.lupus_regnum.gs.external.ItemLoc;


public class Item extends Entity {

	
    /**
     * Amount (for stackables)
     */
    private int amount;
    /**
     * Contains who dropped this item, if anyone
     */
    public long droppedby = 0;

    /**
     * Location definition of the item
     */
    private ItemLoc loc = null;

    /**
     * Contains the player that the item belongs to, if any
     */
    private Player owner;

    /**
     * Set when the item has been destroyed to alert players
     */
    private boolean removed = false;
    /**
     * The time that the item was spawned
     */
    private long spawnedTime;

    public boolean holidayItem = false;
    	
    public Item(int id, int x, int y, int amount, Player owner) {
		setID(id);
		setAmount(amount);
		this.owner = owner;
		if (owner != null)
		    droppedby = owner.getUsernameHash();
		spawnedTime = System.currentTimeMillis();
		setLocation(Point.location(x, y));
    }
    public Item(int id, int x, int y, int amount, Player owner, long spawntime) {
    	setID(id);
    	setAmount(amount);
    	this.owner = owner;
    	if (owner != null)
    	    droppedby = owner.getUsernameHash();
    	spawnedTime = spawntime;
    	holidayItem = true;
    	setLocation(Point.location(x, y));
    }
    public Item(ItemLoc loc) {
		this.loc = loc;
		setID(loc.id);
		setAmount(loc.amount);
		spawnedTime = System.currentTimeMillis();
		setLocation(Point.location(loc.x, loc.y));
    }

    public long droppedby() {
    	return droppedby;
    }

    public boolean equals(Object o) {
		if (o instanceof Item) {
		    Item item = (Item) o;
		    return item.getID() == getID() && item.getAmount() == getAmount() && item.getSpawnedTime() == getSpawnedTime() && (item.getOwner() == null || item.getOwner().equals(getOwner())) && item.getLocation().equals(getLocation());
		}
		return false;
    }

    public int getAmount() {
    	return amount;
    }

    public ItemDef getDef() {
    	return EntityHandler.getItemDef(id);
    }

    public ItemLoc getLoc() {
    	return loc;
    }

    public Player getOwner() {
    	return owner;
    }

    public long getSpawnedTime() {
    	return spawnedTime;
    }

    public boolean isOn(int x, int y) {
    	return x == getX() && y == getY();
    }

    public boolean isRemoved() {
    	return removed;
    }

    public void remove() {
		if (!removed && loc != null && loc.getRespawnTime() > 0) {
		    World.getWorld().getDelayedEventHandler().add(new DelayedEvent(null, loc.getRespawnTime() * 1000) {
				public void run() {
				    world.registerItem(new Item(loc));
				    matchRunning = false;
				}
		    });
		}
		removed = true;
    }

    public void setAmount(int amount) {
		if (getDef().isStackable()) {
		    this.amount = amount;
		} else {
		    this.amount = 1;
		}
    }

    public void setdroppedby(long hash) {
    	droppedby = hash;
    }

    public boolean visibleTo(Player p) {
		if (!holidayItem && (owner == null || p.equals(owner))) {
		    return true;
		}
		if (!getDef().canTrade())
		    return false;
		
		return System.currentTimeMillis() - spawnedTime > 60000;
    }
}
