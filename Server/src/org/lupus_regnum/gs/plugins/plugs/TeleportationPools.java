package org.lupus_regnum.gs.plugins.plugs;

import org.lupus_regnum.gs.event.ShortEvent;
import org.lupus_regnum.gs.model.GameObject;
import org.lupus_regnum.gs.model.MenuHandler;
import org.lupus_regnum.gs.model.Player;
import org.lupus_regnum.gs.model.World;
import org.lupus_regnum.gs.plugins.listeners.action.ObjectActionListener;

/**
 * Handles teleporation pools <br/><br/>
 * TODO: Lumbridge teleportation limit after death / login.
 * @author Ecko
  */

public class TeleportationPools implements ObjectActionListener {

        public static final World world = World.getWorld();
        
	@Override
	public void onObjectAction(GameObject object, String command, Player owner) {	
		if(command.equals("step into")) {
			/*if (owner.getLocation().inWilderness() && System.currentTimeMillis() - owner.getLastMoved() < 10000) {
	        	owner.getActionSender().sendMessage("You have to stand still for 10 seconds to use the portal.");
			}*/	
			if (owner.getLocation().inWilderness() && System.currentTimeMillis() - owner.getLastDeath() < 60000) {
	        	owner.getActionSender().sendMessage("You cannot use the teleportation pool for 60 seconds after dying.");
			}
                        else if (owner.getLocation().inLumbridge() && System.currentTimeMillis() - owner.getLastDeath() < 60000) {
	        	owner.getActionSender().sendMessage("You cannot use the teleportation pool for 60 seconds after dying.");
			}
			/*else if (System.currentTimeMillis() - owner.getCombatTimer() < 10000) {
	        	owner.getActionSender().sendMessage("You need to wait at least 10 seconds after combat before using the portal");
			}*/
			else {
                            owner.getActionSender().sendMessage("You step into the portal.");
                            world.getDelayedEventHandler().add(new ShortEvent(owner) {
                                public void action() {
                                    owner.teleport(53, 161, false);
                                    owner.getActionSender().sendMessage("You arrive in Mystenhaven");
                                }
                            });
                            
                            /*String[] options = new String[] { "Edgeville", "Draynor", "Varrock", "Mage Bank", "Castle (Wild)", "Lvl 12 Altar (Wild)", "Lvl 38 Altar (Wild)" };
                            owner.setMenuHandler(new MenuHandler(options) {
			    	public void handleReply(final int option, final String reply) {
			    		switch (option) {
				        	case 0: // Edgeville
				        		owner.teleport(212, 437, true);
				        		break;
				        	case 1: // Draynor
				            	owner.teleport(215, 630, true);
				                break;
				        	case 2: // Varrock
				            	owner.teleport(145, 500, true);
				                break;
				        	case 3: // Mage Bank
				            	owner.teleport(446, 3374, true);
				            	break;
				        	case 4: // Castle (Wilderness)
				        		owner.teleport(267, 340, true);
				                break;
				        	case 5: // Lvl 12 Altar (Wilderness)
				        		owner.teleport(116, 370, true);
				        		break;
				        	case 6: // Lvl 38 Altar (Wilderness)
				        		owner.teleport(333, 195, true);
				        		break;
				        	default:
				        		return;
			    		}
			    	}
		    	});
		    	owner.getActionSender().sendMenu(options);*/
			}
			return;
		}
	}
}
