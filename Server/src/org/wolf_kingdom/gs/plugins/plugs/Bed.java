package org.wolf_kingdom.gs.plugins.plugs;

import org.wolf_kingdom.gs.event.ShortEvent;
import org.wolf_kingdom.gs.model.GameObject;
import org.wolf_kingdom.gs.model.Player;
import org.wolf_kingdom.gs.model.World;
import org.wolf_kingdom.gs.plugins.listeners.action.ObjectActionListener;

public class Bed implements ObjectActionListener {

	@Override
	public void onObjectAction(GameObject object, String command, Player owner) {	
		if(command.equalsIgnoreCase("rest")) {
			owner.getActionSender().sendMessage("You rest on the bed");
			World.getWorld().getDelayedEventHandler().add(new ShortEvent(owner) {
			    public void action() {
			    	owner.getActionSender().sendEnterSleep();
			    }
			});
		}
	}
}
