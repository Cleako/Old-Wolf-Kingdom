package org.lupus_regnum.gs.event;

import org.lupus_regnum.gs.model.GameObject;
import org.lupus_regnum.gs.model.Player;

public abstract class WalkToObjectEvent extends DelayedEvent {
    protected GameObject object;
    private boolean stop;

    public WalkToObjectEvent(Player owner, GameObject object, boolean stop) {
	super(owner, 601);
	this.object = object;
	this.stop = stop;
	if (stop && owner.atObject(object)) {
	    owner.resetPath();
	    arrived();
	    super.matchRunning = false;
	}
    }

    public abstract void arrived();

    public final void run() {
	if (stop && owner.atObject(object)) {
	    owner.resetPath();
	    arrived();
	} else if (owner.hasMoved()) {
	    return; // We're still moving
	} else if (owner.atObject(object)) {
	    arrived();
	}
	super.matchRunning = false;
    }

}