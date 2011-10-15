package org.lupus_regnum.gs.event;

import org.lupus_regnum.gs.model.ActiveTile;
import org.lupus_regnum.gs.model.GameObject;
import org.lupus_regnum.gs.model.World;


public class ObjectRemover extends DelayedEvent {
    public static final World world = World.getWorld();
    private GameObject object;

    public ObjectRemover(GameObject object, int delay) {
	super(null, delay);
	this.object = object;
    }

    public boolean equals(Object o) {
	if (o instanceof ObjectRemover) {
	    return ((ObjectRemover) o).getObject().equals(getObject());
	}
	return false;
    }

    public GameObject getObject() {
	return object;
    }

    public void run() {
	ActiveTile tile = world.getTile(object.getLocation());
	if (!tile.hasGameObject() || !tile.getGameObject().equals(object)) {
	    super.matchRunning = false;
	    return;
	}
	tile.remove(object);
	world.unregisterGameObject(object);
	super.matchRunning = false;
    }

}