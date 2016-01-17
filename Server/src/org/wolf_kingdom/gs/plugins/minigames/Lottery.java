package org.wolf_kingdom.gs.plugins.minigames;

import java.util.ArrayList;

import org.wolf_kingdom.config.Formulae;
import org.wolf_kingdom.gs.event.DelayedEvent;
import org.wolf_kingdom.gs.event.SingleEvent;
import org.wolf_kingdom.gs.model.InvItem;
import org.wolf_kingdom.gs.model.Player;
import org.wolf_kingdom.gs.model.World;
import org.wolf_kingdom.gs.plugins.listeners.action.CommandListener;
import org.wolf_kingdom.gs.plugins.listeners.action.PlayerLoginListener;
import org.wolf_kingdom.gs.plugins.listeners.action.PlayerLogoutListener;
import org.wolf_kingdom.gs.plugins.listeners.action.StartupListener;

public class Lottery implements PlayerLoginListener, PlayerLogoutListener, CommandListener, StartupListener {
	/**
	 * Lottery entriees
	 */
	private ArrayList<Player> players = new ArrayList<Player>();
	/**
	 * Lottery entriee names (used for re-logging usernames)
	 */
	private ArrayList<String> playerUsernames = new ArrayList<String>();
	/**
	 * How much is the entry fee?
	 */
	private int entryfee = 10000;
	/**
	 * How much is the award
	 */
	private int award = 0;
	/**
	 * How many minutes before announcing lottery winner
	 */
	private int waitBeforeAnnouncing = 5;
	/**
	 * Is this running?
	 */
	private boolean running = false;

	
	@Override
	public void onStartup() {
		World.getWorld().getDelayedEventHandler().add(new DelayedEvent(null, 60000*60*6) { // Ran every 6 hours
			@Override
			public void run() {
				startLotterySales();
			}}
		);
	}
	
	@Override
	public void onPlayerLogin(Player player) {
		if(playerUsernames.contains(player.getUsername()) && running) {
			players.add(player);
		}
	}

	@Override
	public void onPlayerLogout(Player player) {
		if(players.contains(player) && running) {
			players.remove(player);
		}
	}

	@Override
	public void onCommand(String command, String[] args, Player player) {
		if(command.equals("lottery")) {
			handlePlayerJoin(player);
			return;
		}
		else if(command.equals("startlotto") && player.isAdmin()) {
			startLotterySales();
			return;
		}
	}

	private void startLotterySales() {
		if(running) {
			return;
		}
		running = true;
		
		World.getWorld().sendWorldAnnouncement(" Lottery: @gre@The lottery is running! Type ::lottery to buy a ticket for " + entryfee + "!");
		World.getWorld().sendWorldAnnouncement(" Lottery: @gre@All the money is put into the pot and winner gets it all!");
		World.getWorld().sendWorldAnnouncement(" Lottery: @gre@Current pot is: " + award);
		
		World.getWorld().getDelayedEventHandler().add(new DelayedEvent(null,60000) {
			@Override
			public void run() {
				if(running) {
					World.getWorld().sendWorldAnnouncement(" Lottery: @gre@The lottery is running! Type ::lottery to buy a ticket for " + entryfee + "!");
					World.getWorld().sendWorldAnnouncement(" Lottery: @gre@All the money is put into the pot and winner gets it all!");
					World.getWorld().sendWorldAnnouncement(" Lottery: @gre@Current pot is: " + award);
				}
				else {
					this.stop();
				}
				
			}
			
		});
		World.getWorld().getDelayedEventHandler().add(new SingleEvent(null, waitBeforeAnnouncing*60000) {
			@Override
			public void action() {
				endLottery();
			}
		});
	}
	/**
	 * Ends the lottery by declaring winner and initilizing variables
	 */
	private void endLottery() {
		running = false;
		World.getWorld().sendWorldAnnouncement(" Lottery: @gre@The lottery is over! The winner is: " + findWinner());
		/**
		 * Initilize everything
		 */
		players = new ArrayList<Player>();
		playerUsernames = new ArrayList<String>();
		award = 0;
	}
	/**
	 * Finds a winner and distributes the award
	 * @return
	 */
	private String findWinner() {
		if(players.size() <= 0) {
			return "No-one participated!";
		}
		Player p = null;
		if(players.size() == 1) {
			p = players.get(0);
		}
		else {
			p = players.get(Formulae.Rand(0, (players.size()-1)));
		}
		if(p != null && p.getUsername() != null) {
			p.getActionSender().sendMessage("Congratulations, you have won the lottery!");
			InvItem coins = new InvItem(10);
			coins.setAmount(award);
			p.getInventory().add(coins);
			p.getActionSender().sendInventory();
			p.getActionSender().sendMessage(award + " coins has been added to your inventory!");
			return p.getUsername();
		}
		else  {
			players.remove(p);
			return findWinner();
		}
	}
	private void handlePlayerJoin(Player p) {
		if(!running) {
			p.getActionSender().sendMessage("Lottery isn't running at the moment!");
			return;
		}
		if(players.contains(p)) {
			p.getActionSender().sendMessage("You have already joined the lottery!");
			return;
		}
		if(p.getInventory().countId(10) >= entryfee && running) {
			if(p.getInventory().remove(10, entryfee) != -1) {
				players.add(p);
				playerUsernames.add(p.getUsername());
				p.getActionSender().sendMessage("You have joined the lottery! Good luck!");
				p.getActionSender().sendInventory();
				award += entryfee;
				return;
			}
		}
		p.getActionSender().sendMessage("Looks like you don't have enough money on you! Get " + entryfee + " to join!");
	}


}
