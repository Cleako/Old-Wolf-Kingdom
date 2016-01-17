package org.wolf_kingdom.gs.event;

import org.wolf_kingdom.gs.model.Player;

public abstract class SingleEvent extends DelayedEvent {

    public SingleEvent(Player owner, int delay) {
	super(owner, delay);
    }

    public abstract void action();

    public void run() {
	action();
	super.matchRunning = false;
    }

}