package org.lupus_regnum.gs.event;

import org.lupus_regnum.gs.model.Player;

public abstract class ShortEvent extends SingleEvent {

    public ShortEvent(Player owner) {
	super(owner, 1500);
    }

    public abstract void action();

}