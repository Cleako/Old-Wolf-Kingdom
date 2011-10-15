package org.lupus_regnum.gs.event;

import org.lupus_regnum.gs.model.Player;
import org.lupus_regnum.gs.model.Point;

public abstract class WalkToPointEvent extends DelayedEvent {
    protected Point location;
    private int radius;
    private boolean stop;

    public WalkToPointEvent(Player owner, Point location, int radius, boolean stop) {
	super(owner, 500);
	this.location = location;
	this.radius = radius;
	this.stop = stop;
	if (stop && owner.withinRange(location, radius)) {
	    owner.resetPath();
	    arrived();
	    super.matchRunning = false;
	}
    }

    public abstract void arrived();

    public Point getLocation() {
	return location;
    }

    public final void run() {
	if (stop && owner.withinRange(location, radius)) {
	    owner.resetPath();
	    arrived();
	} else if (owner.hasMoved()) {
	    return; // We're still moving
	} else if (owner.withinRange(location, radius)) {
	    arrived();
	}
	super.matchRunning = false;
    }

}