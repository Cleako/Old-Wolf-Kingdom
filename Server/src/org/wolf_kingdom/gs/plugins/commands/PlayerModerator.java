
package org.wolf_kingdom.gs.plugins.commands;

import org.wolf_kingdom.gs.model.Player;
import org.wolf_kingdom.gs.model.Point;
import org.wolf_kingdom.gs.model.World;
import org.wolf_kingdom.gs.plugins.listeners.action.CommandListener;
import org.wolf_kingdom.gs.tools.DataConversions;
import org.wolf_kingdom.gs.util.Logger;

public class PlayerModerator implements CommandListener {
	
    /**
     * World instance
     */
    public static final World world = World.getWorld();

	
    private String[] towns = { "varrock", "falador", "draynor", "portsarim", "karamja", "alkharid", "lumbridge", "edgeville", "castle", "taverly", "clubhouse", "seers", "barbarian", "rimmington", "catherby", "ardougne", "yanille", "lostcity", "gnome" };
    private Point[] townLocations = { Point.location(122, 509), Point.location(304, 542), Point.location(214, 632), Point.location(269, 643), Point.location(370, 685), Point.location(89, 693), Point.location(120, 648), Point.location(217, 449), Point.location(270, 352), Point.location(373, 498), Point.location(653, 491), Point.location(501, 450), Point.location(233, 513), Point.location(325, 663), Point.location(440, 501), Point.location(549, 589), Point.location(583, 747), Point.location(127, 3518), Point.location(703, 527) };

	
	@Override
	public void onCommand(String command, String[] args, Player player) {
		player.getActionSender().sendMessage("Command: " + command);
		if (!player.isPMod()) {
		    return;
		}
		if (command.equals("info")) {
		    if (args.length != 1) {
				player.getActionSender().sendMessage("Invalid args. Syntax: INFO name");
				return; 
		    }
		    World.getWorld().getServer().getLoginConnector().getActionSender().requestPlayerInfo(player, DataConversions.usernameToHash(args[0]));
		    return;
		}		
		else if (command.equalsIgnoreCase("town")) {
		    try {
			String town = args[0];
			if (town != null) {
			    for (int i = 0; i < towns.length; i++)
					if (town.equalsIgnoreCase(towns[i])) {
					    player.teleport(townLocations[i].getX(), townLocations[i].getY(), true);
					    return;
					}
				}
		    } 
		    catch (Exception e) {
		    	e.printStackTrace();
		    }
		}
		else if (command.equals("goto") || command.equals("summon")) {
		    boolean summon = command.equals("summon");
		   
		    if (args.length != 1) {
				player.getActionSender().sendMessage("Invalid args. Syntax: " + (summon ? "SUMMON" : "GOTO") + " name");
				return;
		    }
		    long usernameHash = DataConversions.usernameToHash(args[0]);
		    Player affectedPlayer = World.getWorld().getPlayer(usernameHash);
		    if (affectedPlayer != null) {
		    	if((summon && !player.isMod()) && (Math.abs(affectedPlayer.getX() - player.getX()) > 10 || Math.abs(affectedPlayer.getY() - player.getY()) > 10)) {
		    		player.getActionSender().sendMessage("As a Pmod you can only summon someone within a 10 square radius");
		    		return;
		    	}
				if (summon) {
				    Logger.mod(player.getUsername() + " summoned " + affectedPlayer.getUsername() + " from " + affectedPlayer.getLocation().toString() + " to " + player.getLocation().toString());
				    affectedPlayer.teleport(player.getX(), player.getY(), true);
				    affectedPlayer.getActionSender().sendMessage("You have been summoned by " + player.getUsername());
				}
				else {
				    Logger.mod(player.getUsername() + " went from " + player.getLocation() + " to " + affectedPlayer.getUsername() + " at " + affectedPlayer.getLocation().toString());
				    player.teleport(affectedPlayer.getX(), affectedPlayer.getY(), true);
				}
		    } 
		    else {
		    	player.getActionSender().sendMessage("Invalid player, maybe they aren't currently on this server?");
		    }
		    return;
		}
		else if (command.equals("invis")) {
		    if (player.isInvis()) {
		    	player.setinvis(false);
		    } 
		    else {
		    	player.setinvis(true);
		    }
		    player.getActionSender().sendMessage("Invis status is: " + player.isInvis());
		}
		else if (command.equals("teleport")) {
		    if (args.length != 2) {
				player.getActionSender().sendMessage("Invalid args. Syntax: TELEPORT x y");
				return;
		    }
		    int x = Integer.parseInt(args[0]);
		    int y = Integer.parseInt(args[1]);
		    if (world.withinWorld(x, y)) {
				Logger.mod(player.getUsername() + " teleported from " + player.getLocation().toString() + " to (" + x + ", " + y + ")");
				player.teleport(x, y, true);
			    } 
		    else {
				player.getActionSender().sendMessage("Invalid coordinates!");
			    }
		    return;
		}
		else if (command.equalsIgnoreCase("kick")) {
		    Player p = world.getPlayer(DataConversions.usernameToHash(args[0]));
		    p.destroy(false);
		    world.sendWorldMessage("@whi@" + p.getUsername() + " @or2@has been kicked from the server");
		    return;
		}
		else if (command.equals("take") || command.equals("put")) {
		    if (args.length != 1) {
				player.getActionSender().sendMessage("Invalid args. Syntax: TAKE name");
				return;
		    }
		    Player affectedPlayer = world.getPlayer(DataConversions.usernameToHash(args[0]));
		    if (affectedPlayer == null) {
				player.getActionSender().sendMessage("Invalid player, maybe they aren't currently online?");
				return;
		    }
		    Logger.mod(player.getUsername() + " took " + affectedPlayer.getUsername() + " from " + affectedPlayer.getLocation().toString() + " to admin room");
		    affectedPlayer.teleport(78, 1642, true);
		    if (command.equals("take")) {
		    	player.teleport(76, 1642, true);
		    }
		    return;
		}
		else if(command.equals("nopk")) {
	    	player.setnopk(!player.isNopk());
	    	player.getActionSender().sendMessage("Nopk is : " + player.isNopk());
	    	}
		return;
	}

}
