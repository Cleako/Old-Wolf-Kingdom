package org.wolf_kingdom.gs.plugins.minigames;

import org.wolf_kingdom.gs.model.MenuHandler;
import org.wolf_kingdom.gs.model.Player;
import org.wolf_kingdom.gs.model.Point;
import org.wolf_kingdom.gs.model.World;
import org.wolf_kingdom.gs.plugins.listeners.action.CommandListener;

public class EventSummoning implements CommandListener {

	/**
	 * Location of the event where everyone joins
	 */
	private Point location = null;
	
	/**
	 * Max level limitation
	 */
	private int maxLevel = -1;
	
	@Override
	public void onCommand(String command, String[] args, Player player) {
		if(command.equalsIgnoreCase("setevent") && player.isPMod()) {
			this.maxLevel = -1;
			if(args.length > 0) {
				try {
					maxLevel = Integer.valueOf(args[0]);
				}
				catch(Exception e) {
					player.getActionSender().sendMessage("Max level limit not a number.");
					return;
				}
			}
			this.location = player.getLocation();			
			World.getWorld().sendWorldAnnouncement("An event has started! Type ::event to join "+ (this.location.inWilderness() ? "(@red@This event is in the wilderness@whi@)" : "") +(maxLevel > -1 ? "(@gre@This event is for level "+maxLevel+"s@whi@)" : "")+"");
			
		}
		if(command.equalsIgnoreCase("unsetevent") && player.isPMod()) {
			this.location = null;
			this.maxLevel = -1;
			World.getWorld().sendWorldAnnouncement("The event has finished!");
		}
		if(command.equalsIgnoreCase("event")) {
			if(this.location == null) {
				player.getActionSender().sendMessage("No event has been set");
				return;
			}
			else if(this.maxLevel > 0 && player.getCombatLevel() != this.maxLevel) {
				player.getActionSender().sendMessage("This event is for level " + maxLevel + "s only!");
				return;
			}
			else if(!player.canLogout() || System.currentTimeMillis() - player.getLastMoved() < 10000) {
				player.getActionSender().sendMessage("You must stand peacefully in one place for 10 seconds!");
				return;
			}
	    	else if (player.getLocation().inModRoom() && !player.isMod()) {
	    		player.getActionSender().sendMessage("You cannot use ::event here");
	    		return;
		    }
			if(this.location.inWilderness()) {
				
				String[] options = new String[] { 
						"Yes, I am ready to go into the wilderness!",
						"No, I am not ready.",
					};
		    	player.setMenuHandler(new MenuHandler(options) {
			    	public void handleReply(final int option, final String reply) {
			    		switch (option) {
				        	case 0: //Yes
				        		owner.teleport(location.getX(), location.getY(), true);
				        		return;
				        	case 1: //No
				        		
				        		return;

				        	default:
				        		return;
			    		}
			    	}
		    	});
		    	player.getActionSender().sendMenu(options);
			}
			else {
				player.teleport(location.getX(), location.getY(), true);
			}
		}
		
	}

}
