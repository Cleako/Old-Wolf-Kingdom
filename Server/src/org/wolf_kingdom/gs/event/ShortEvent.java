package org.wolf_kingdom.gs.event;

import org.wolf_kingdom.gs.model.Player;

public abstract class ShortEvent extends SingleEvent {

    public ShortEvent(Player owner) {
	super(owner, 1500);
    }

    public abstract void action();

}