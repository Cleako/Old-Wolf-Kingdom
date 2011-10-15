package org.lupus_regnum.gs.plugins.minigames;

import java.util.ArrayList;

import org.lupus_regnum.gs.event.DelayedEvent;
import org.lupus_regnum.gs.event.SingleEvent;
import org.lupus_regnum.gs.model.Player;
import org.lupus_regnum.gs.model.World;
import org.lupus_regnum.gs.plugins.listeners.action.CommandListener;
import org.lupus_regnum.gs.plugins.listeners.action.PlayerDeathListener;
import org.lupus_regnum.gs.plugins.listeners.action.PlayerLogoutListener;
import org.lupus_regnum.gs.plugins.listeners.executive.PlayerAttackExecutiveListener;
import org.lupus_regnum.gs.plugins.listeners.executive.PlayerLogoutExecutiveListener;
import org.lupus_regnum.gs.plugins.listeners.executive.PlayerMageExecutiveListener;
import org.lupus_regnum.gs.plugins.listeners.executive.PlayerRangeExecutiveListener;
import org.lupus_regnum.gs.plugins.listeners.executive.TeleportExecutiveListener;
import org.lupus_regnum.gs.tools.DataConversions;

public class LastManStanding implements 
		PlayerAttackExecutiveListener, 
		PlayerLogoutExecutiveListener, 
		PlayerMageExecutiveListener, 
		PlayerRangeExecutiveListener, 
		TeleportExecutiveListener,
		CommandListener, 
		PlayerLogoutListener, 
		PlayerDeathListener {
	/**
	 * World instance
	 */
	World world = World.getWorld();
	/**
	 * Holds the data for players in the event
	 */
	private ArrayList<Long> players = new ArrayList<Long>();
	/**
	 * Determines if the event is running
	 */	
	private boolean running = false;
	/**
	 * Determines if registrations / joining period is over
	 */
	private boolean registrationsOpen = false;
	/**
	 * Determines the maximum combat level requirement to join the war
	 */
	private int maxLevel = 123;
	/**
	 * Determines the minimum combat level requirement to join the war
	 */
	private int minLevel = 3;
	/**
	 * Determines if range is allowed in this event
	 */
	private boolean allowRange = true;
	/**
	 * Called when the event is started (by a moderator/administrator or an automatic feature)
	 */
	private void start() {
		if(running) {
			return;
		}
		running = true;
		registrationsOpen = true;
		world.sendWorldAnnouncement("Last man standing: @gre@The last man stand event registration has started!");
		if(minLevel == maxLevel)
			world.sendWorldAnnouncement("Last man standing: @gre@This event is for level " + maxLevel + "s only!");
		else
			world.sendWorldAnnouncement("Last man standing: @gre@This event is for players between level "+minLevel+" and "+maxLevel+"");
		
		world.sendWorldAnnouncement("Type ::lastman to join the dangerous, player vs player event");				
		World.getWorld().getDelayedEventHandler().add(new DelayedEvent(null,60000) {
			@Override
			public void run() {
				if(registrationsOpen) {
					world.sendWorldAnnouncement("Last man standing: @gre@The last man stand event is starting!");
					if(minLevel == maxLevel)
						world.sendWorldAnnouncement("Last man standing: @gre@This event is for level " + maxLevel + "s only!");
					else
						world.sendWorldAnnouncement("Last man standing: @gre@This event is for players between level "+minLevel+" and "+maxLevel+"");
					
					world.sendWorldAnnouncement("Type ::lastman to join the dangerous, player vs player event");				}
				else {
					this.stop();
				}
				
			}
			
		});
		World.getWorld().getDelayedEventHandler().add(new SingleEvent(null, (4*60000)+1000) {
			@Override
			public void action() {
				world.sendWorldAnnouncement("@RAN@LAST MAN STANDING Registrations CLOSING in 50 SECONDS!");
				world.sendWorldAnnouncement("@RAN@Fighting starts automatically!");
			}
		});
		World.getWorld().getDelayedEventHandler().add(new SingleEvent(null, 5*60000) {
			@Override
			public void action() {
				world.sendWorldAnnouncement("@RAN@ LAST MAN STANDING Registrations closed, FIGHT!");
				world.sendWorldAnnouncement("@RAN@ LAST MAN STANDING Registrations closed, FIGHT!");
				world.sendWorldAnnouncement("@RAN@ LAST MAN STANDING Registrations closed, FIGHT!");
				registrationsOpen = false;
			}
		});
	}
	/**
	 * Called when a player wishes to join this event
	 * @param p
	 */
	private void handlePlayerJoin(Player p) {
		
    	if(!p.canLogout() || System.currentTimeMillis() - p.getLastMoved() < 10000) {
    		p.getActionSender().sendMessage("You must stand peacefully in one place for 10 seconds!");
    	}
    	else if (p.getLocation().inModRoom() && !p.isMod()) {
	    	p.getActionSender().sendMessage("You cannot use ::lastman here");
	    } 
	    else if(!running) {
			p.getActionSender().sendMessage("Last man standing event isn't running!");
		}
		else if(!registrationsOpen) {
			p.getActionSender().sendMessage("Last man standing registrations are closed, sorry");
		}
		else if(p.getCombatLevel() > maxLevel || p.getCombatLevel() < minLevel) {
			p.getActionSender().sendMessage("You do not meet the combat level requierments to join this event!");
		}
		else if(players.contains(p.getUsernameHash())) {
			p.getActionSender().sendMessage("You have already joined the last man standing event!");
		}
		else {
			p.teleport(DataConversions.random(224, 233), DataConversions.random(127, 134), true);
			players.add(p.getUsernameHash());
		}
	}
	/**
	 * Called when a player wishes to leave this event
	 * @param player
	 */
	private void handlePlayerLeave(Player player) {
		if(players.contains(player.getUsernameHash())) {
			
			player.getActionSender().sendMessage("You have left the last man standing event");
			
			players.remove(player.getUsernameHash());
			player.teleport(122, 647, true);
			if(!this.registrationsOpen)
				checkForWinner();
		}
		
	}
	/**
	 * Determines if player is in the mage arena (228, 130)
	 * @param p
	 * @return
	 */
	private boolean inArena(Player p) {
		return (p.getX() >= 223 && p.getY() >= 126 && p.getX() <= 234 && p.getY() <= 135);
	}
	/**
	 * Determines if the event is over (only one man left)
	 */
	private void checkForWinner() {
		if(players.size() == 1) {
			running = false;
			
			world.sendWorldAnnouncement("@RAN@The last man standing is " + DataConversions.hashToUsername(players.get(0)));
			world.sendWorldAnnouncement("@RAN@Congradulations!");
			players.remove(0);
		}
	}
	
	@Override
	public void onCommand(String command, String[] args, Player player) {
		if(command.equalsIgnoreCase("lastman")) {
			handlePlayerJoin(player);
			return;
		}
		else if(command.equalsIgnoreCase("leavelastman")) {
			handlePlayerLeave(player);
			return;
		}
		if(player.isMod()) {
			if(command.equalsIgnoreCase("startlastman")) {
				start();
				return;
			}
			else if(command.equalsIgnoreCase("lastmanrange")) {
				if(!running) {
					player.getActionSender().sendMessage("Last man standing event isn't running!");
					return;
				}
				this.allowRange = args[0].equalsIgnoreCase("true");
				player.getActionSender().sendMessage("Range is now: " + (this.allowRange ? "enabled" : "disabled"));
				world.sendWorldAnnouncement("@RED@Range has been " + (this.allowRange ? "enabled" : "disabled") + " for the last man standing event");
				return;
			}
		}
	}

	@Override
	public void onPlayerLogout(Player player) {
		handlePlayerLeave(player);
		
	}
	@Override
	public boolean blockPlayerLogout(Player player) {
		if(players.contains(player.getUsernameHash())) {
			/**
			 * If registrations are open and a player lags out, force him to leave
			 */
			if(this.registrationsOpen) {
				return false;
			}
			player.getActionSender().sendMessage("You cannot logout during this event, please use ::leavelastman");
			return true;
		}
		return false;
		
	}


	@Override
	public void onPlayerDeath(Player p) {
		if(players.contains(p.getUsernameHash()) && inArena(p)) {
			players.remove(p.getUsernameHash());
			p.getActionSender().sendMessage("You are not the last man standing!");
			checkForWinner();
		}
	}
	
	@Override
	public boolean blockPlayerAttack(Player p, Player affectedmob) {
		Player affectedPlayer = (Player)affectedmob;
		if(players.contains(p.getUsernameHash()) && players.contains(affectedPlayer.getUsernameHash()) && inArena(p) && inArena(affectedPlayer)) {
			/**
			 * Cannot attack if registrations are open
			 */
			if(registrationsOpen) {
				p.getActionSender().sendMessage("You cannot attack anyone yet!");
				return true;
			}
		}
		return false;
	}


	@Override
	public boolean blockPlayerMage(Player player, Player affectedPlayer, Integer spell) {
		if(players.contains(player.getUsernameHash()) && players.contains(affectedPlayer.getUsernameHash()) && inArena(player) && inArena(affectedPlayer)) {
			/**
			 * Cannot attack if registrations are open
			 */
			if(registrationsOpen) {
				player.getActionSender().sendMessage("You can only mage the people you are in combat with!");
				
				return true;
			}
			if(player.getOpponent() instanceof Player) {
				Player oppo = (Player)player.getOpponent();
				if(affectedPlayer.getUsernameHash() != oppo.getUsernameHash()) {
					player.getActionSender().sendMessage("You can only mage the people you are in combat with!");
					return true;
				}
			}
			else {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean blockPlayerRange(Player player, Player affectedPlayer) {
		if(players.contains(player.getUsernameHash()) && players.contains(affectedPlayer.getUsernameHash()) && inArena(player) && inArena(affectedPlayer)) {
			/**
			 * Cannot attack if registrations are open
			 */
			if(registrationsOpen) {
				player.getActionSender().sendMessage("You cannot attack anyone yet!");
				return true;
			}
			if(!this.allowRange) {
				player.getActionSender().sendMessage("Range has been disabled for this event!");
				return true;
			}

		}
		return false;
	}
	
	@Override
	public boolean blockTeleport(Player p) {
		if(players.contains(p.getUsernameHash()) && inArena(p)) {
			p.getActionSender().sendMessage("You cannot teleport out of this arena!");
			return true;
		}
		return false;
	}
	
}
