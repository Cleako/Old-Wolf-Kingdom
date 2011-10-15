package org.lupus_regnum.gs.event;

import org.lupus_regnum.gs.core.DelayedEventHandler;
import org.lupus_regnum.gs.model.Player;
import org.lupus_regnum.gs.model.Npc;
import org.lupus_regnum.gs.model.World;


public abstract class DelayedEvent {
    public static final World world = World.getWorld();
    protected int delay = 500;
    protected final DelayedEventHandler handler = World.getWorld().getDelayedEventHandler();
    private long lastRun = System.currentTimeMillis();
    protected Player owner;
    protected boolean matchRunning = true;

    public DelayedEvent(Player owner, int delay) {
		this.owner = owner;
		this.delay = delay;
    }
    
    public boolean belongsTo(Player player) {
    	return owner != null && owner.equals(player);
    }

    public int getDelay() {
    	return delay;
    }

    public Object getIdentifier() {
    	return null;
    }

    public Player getOwner() {
    	return owner;
    }

    public boolean hasOwner() {
    	return owner != null;
    }

    public boolean is(DelayedEvent e) {
    	return (e.getIdentifier() != null && e.getIdentifier().equals(getIdentifier()));
    }

    public abstract void run();

    public void setDelay(int delay) {
    	this.delay = delay;
    }

    public void setLastRun(long time) {
    	lastRun = time;
    }

    public final boolean shouldRemove() {
    	return !matchRunning;
    }

    public final boolean shouldRun() {
    	return matchRunning && System.currentTimeMillis() - lastRun >= delay;
    }

    public final void stop() {
    	matchRunning = false;
    }

    public int timeTillNextRun() {
		int time = (int) (delay - (System.currentTimeMillis() - lastRun));
		return time < 0 ? 0 : time;
    }

    public final void updateLastRun() {
    	lastRun = System.currentTimeMillis();
    }
}
