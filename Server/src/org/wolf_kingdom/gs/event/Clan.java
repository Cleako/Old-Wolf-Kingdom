package org.wolf_kingdom.gs.event;

import org.wolf_kingdom.gs.model.Player;
import org.wolf_kingdom.gs.model.World;
import java.util.ArrayList;

public class Clan {

	public Clan(Player p) {
		player = p;
	}

	public int MaxClanSize = 25; // Maximum size of Players in Clan.
	public Player player;
	public final static World world = World.getWorld();
	public ArrayList<String> playersToGiveTo = new ArrayList<String>();

	public void invitePlayer(String playername) {
		try {
			Player target = world.getPlayer1(playername);

			if (playername.equals(player.getUsername())) {
				player.getActionSender().sendMessage("You cannot invite your self to a clan.");
				return;
			}

			if (player.inClan && !player.myClan.get(0).equals(player.getUsername())) {
				player.getActionSender().sendMessage("Sorry, you are not the clan leader.");
				return;
			}

			if (this.isClanFull()) {
				player.getActionSender().sendMessage("You cannot fit anymore players into your clan.");
				return;
			}

			if (target.inClan) {
				player.getActionSender().sendMessage("Sorry, they are already in a clan.");
				return;
			}
			
			if (target.invitedPlayers.contains(player.getUsername())) {
				player.getActionSender().sendMessage("You have already invited this player.");
				return;
			}
			target.getActionSender().sendMessage("@gre@" + player.getUsername() + " @whi@has invited you to join their clan. Type ::acceptclaninvite to join.");
			player.getActionSender().sendMessage("You have invited @gre@" + target.getUsername() + " @whi@to join your clan.");
			target.lastClanInvite = player.getUsername();
			player.invitedPlayers.add(target.getUsername());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void acceptPlayer() {
		try {

			Player leader = world.getPlayer1(player.lastClanInvite);

			if (player.lastClanInvite == null) {
				player.getActionSender().sendMessage("Sorry, nobody has invited you to a clan.");
			}

			if (this.invitedPlayersContains(player.getUsername(), leader)) {

				if (leader.myClan.size() > MaxClanSize) {
					player.getActionSender().sendMessage("Sorry, the clan you are trying to join is currently full.");
					return;
				}
				if (!leader.inClan) {
					leader.myClan.add(leader.getUsername());
				}

				leader.myClan.add(player.getUsername());
				player.myClan = leader.myClan;
				player.inClan = true;
				leader.inClan = true;
				player.lastClanInvite = null;

				sendClanMessage("@gre@" + player.getUsername() + "@whi@ has joined the clan");
				player.getActionSender().sendMessage("@gre@Clan Members: @yel@" + this.clanMembersString());


			} else {
				player.getActionSender().sendMessage("@gre" + leader.getUsername() + " has not invited you to join their clan");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public String clanMembersString() {
		try {
			if (!player.inClan) {
				return "Sorry, you are not a member of a clan.";
			}
			String str = player.myClan.get(0) + "@whi@(@yel@C@whi@), ";
			for (int i = 1; i < player.myClan.size(); i++) {
				str = str + player.myClan.get(i) + ", ";
			}
			return str;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "Error. Please log out and back in to correct this.";
		}
	}

	public void sendClanMessageUser(String message) {
		try {
			if (!player.inClan) {
				player.getActionSender().sendMessage("Sorry, you are not a member of a clan.");
				return;
			}
			for (int i = 0; i < player.myClan.size(); i++) {
				Player member = world.getPlayer1(player.myClan.get(i));
				member.getActionSender().sendMessage("@whi@(@yel@C@whi@) @gre@" + player.getUsername() + " @whi@" + message);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void sendClanMessage(String message) {
		try {
			for (int i = 0; i < player.myClan.size(); i++) {
				Player member = world.getPlayer1(player.myClan.get(i));
				member.getActionSender().sendMessage("@whi@(@yel@C@whi@) " + message);
			}
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void updateClanMembers() {
		try {

			for (int i = 0; i < player.myClan.size(); i++) {
				Player member = world.getPlayer1(player.myClan.get(i));
				member.myClan = player.myClan;
				member.myClan.clear();
				member.myClan.addAll(player.myClan);              
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void removePlayer() {
		try {

			ArrayList<String> temparray = new ArrayList<String>();
			temparray.addAll(player.myClan);

			for (int i = 0; i < temparray.size(); i++) {
				Player member = world.getPlayer1(temparray.get(i));
				if (member.myClan.get(i).isEmpty()) {

				} else {
					removePlayerClan(getIndex());
				}
				member.getActionSender().sendMessage("@whi@(@yel@" + player.getUsername() + "@whi@) @red@has left the clan.");
				if (temparray.get(i).isEmpty()) {
					break;
				}
			}
			player.clearMyClan();
			player.getActionSender().sendMessage("You have left the clan.");

		} catch (Exception e) {
			System.out.println(e.getMessage() + " \nStack: " + e.getStackTrace().toString());
		}
	}

	public int getIndex() {
		try {

			for (int i = 0; i < player.myClan.size(); i++) {
				if (player.myClan.get(i).equals(player.getUsername())) {
					return i;
				}
			}
			return 1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 1;
		}
	}

	public int getClanSize() {
		return player.myClan.size() + 1;
	}

	public void removePlayerClan(int id) {
		player.myClan.remove(id);
	}

	public boolean isClanFull() {
		return player.myClan.size() >= MaxClanSize;
	}

	public boolean clanContains(String name) {
		try {
			for (int i = 0; i < player.myClan.size(); i++) {
				if (player.myClan.get(i).equals(name)) {
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean invitedPlayersContains(String name, Player leader) {

		try {
			for (int i = 0; i < leader.invitedPlayers.size(); i++) {
				if (leader.invitedPlayers.get(i).equals(name)) {
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean isClanLeader() {
		return player.myClan.get(0).equals(player.getUsername());
	}

	public void summonClan() {
		if (!isClanLeader()) {
			player.getActionSender().sendMessage("Sorry, you are not the clan leader.");
			return;
		}

		for (int i = 0; i < player.myClan.size(); i++) {
			if (player.myClan.get(i).equals(player.getUsername())) {

			} else {
				Player member = world.getPlayer1(player.myClan.get(i));
				member.getActionSender().sendMessage("@gre@" + player.getUsername() + " @whi@wishes to summon you. Type ::acceptclansummon to be summoned.");
				member.summonLeader = player.getUsername();
			}
		}

	}

	public void acceptSummon() {
		Player leader = world.getPlayer1(player.myClan.get(0));
		if (player.inClan) {
			/*if (player.isSkulled()) { // I don't get why this is even here.
				player.getActionSender().sendMessage("Your leader wanted to summon you, but he cannot when you are skulled.");
				player.summonLeader = null;
				return;
			}*/
			if (player.summonLeader.equals(player.myClan.get(0))) {
				player.teleport(leader.getLocation().x, leader.getLocation().y, true);
				player.summonLeader = null;
			} else {
				player.getActionSender().sendMessage("Nobody has tried to summoned you.");
			}
		} else {

			player.getActionSender().sendMessage("Sorry, you are not a member of a clan.");
		}
	}
}