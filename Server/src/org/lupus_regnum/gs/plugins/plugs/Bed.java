package org.lupus_regnum.gs.plugins.plugs;

import org.lupus_regnum.gs.event.ShortEvent;
import org.lupus_regnum.gs.model.GameObject;
import org.lupus_regnum.gs.model.Player;
import org.lupus_regnum.gs.model.World;
import org.lupus_regnum.gs.plugins.listeners.action.ObjectActionListener;

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
