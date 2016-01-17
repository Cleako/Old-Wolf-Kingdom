package org.wolf_kingdom.gs.event;

import org.wolf_kingdom.gs.model.Player;
import org.wolf_kingdom.gs.model.World;
import java.util.ArrayList;

public class Party {

	public Party(Player p) {
		player = p;
	}

	public int MaxPartySize = 5; // Maximum size of Players in Party.
	public Player player;
	public final static World world = World.getWorld();
	public ArrayList<String> playersToGiveTo = new ArrayList<String>();

	public void invitePlayer(String playername) {
		try {
			Player target = world.getPlayer1(playername);

			if (playername.equals(player.getUsername())) {
				player.getActionSender().sendMessage("You cannot invite your self to a party.");
				return;
			}

			if (player.inParty && !player.myParty.get(0).equals(player.getUsername())) {
				player.getActionSender().sendMessage("Sorry, you are not the party leader.");
				return;
			}

			if (this.isPartyFull()) {
				player.getActionSender().sendMessage("You cannot fit anymore players into your party.");
				return;
			}

			if (target.inParty) {
				player.getActionSender().sendMessage("Sorry, they are already in a party.");
				return;
			}
			
			if (target.invitedPlayers.contains(player.getUsername())) {
				player.getActionSender().sendMessage("You have already invited this player.");
				return;
			}
			target.getActionSender().sendMessage("@gre@" + player.getUsername() + " @whi@has invited you to join their party, type ::acceptpartyinvite to join.");
			player.getActionSender().sendMessage("You have invited @gre@" + target.getUsername() + " @whi@to join your party");
			target.lastPartyInvite = player.getUsername();
			player.invitedPlayers.add(target.getUsername());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void acceptPlayer() {
		try {

			Player leader = world.getPlayer1(player.lastPartyInvite);

			if (player.lastPartyInvite == null) {
				player.getActionSender().sendMessage("Sorry, nobody has invited you to a party.");
			}

			if (this.invitedPlayersContains(player.getUsername(), leader)) {

				if (leader.myParty.size() > MaxPartySize) {
					player.getActionSender().sendMessage("Sorry, the party you are trying to join is currently full.");
					return;
				}
				if (!leader.inParty) {
					leader.myParty.add(leader.getUsername());
				}

				leader.myParty.add(player.getUsername());
				player.myParty = leader.myParty;
				player.inParty = true;
				leader.inParty = true;
				player.lastPartyInvite = null;

				sendPartyMessage("@gre@" + player.getUsername() + "@whi@ has joined the party.");
				player.getActionSender().sendMessage("@gre@Party Members: @yel@" + this.partyMembersString());


			} else {
				player.getActionSender().sendMessage("@gre" + leader.getUsername() + " has not invited you to join their party.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public String partyMembersString() {
		try {
			if (!player.inParty) {
				return "Sorry, you are not in a party.";
			}
			String str = player.myParty.get(0) + "@whi@(@yel@P@whi@), ";
			for (int i = 1; i < player.myParty.size(); i++) {
				str = str + player.myParty.get(i) + ", ";
			}
			return str;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "Error. Please log out and back in to correct this.";
		}
	}

	public void sendPartyMessageUser(String message) {
		try {
			if (!player.inParty) {
				player.getActionSender().sendMessage("Sorry, you are not in a party.");
				return;
			}
			for (int i = 0; i < player.myParty.size(); i++) {
				Player member = world.getPlayer1(player.myParty.get(i));
				member.getActionSender().sendMessage("@whi@(@yel@P@whi@) @gre@" + player.getUsername() + " @whi@" + message);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void sendPartyMessage(String message) {
		try {
			for (int i = 0; i < player.myParty.size(); i++) {
				Player member = world.getPlayer1(player.myParty.get(i));
				member.getActionSender().sendMessage("@whi@(@yel@P@whi@) " + message);
			}
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void updatePartyMembers() {
		try {

			for (int i = 0; i < player.myParty.size(); i++) {
				Player member = world.getPlayer1(player.myParty.get(i));
				member.myParty = player.myParty;
				member.myParty.clear();
				member.myParty.addAll(player.myParty);              
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void removePlayer() {
		try {

			ArrayList<String> temparray = new ArrayList<String>();
			temparray.addAll(player.myParty);

			for (int i = 0; i < temparray.size(); i++) {
				Player member = world.getPlayer1(temparray.get(i));
				if (member.myParty.get(i).isEmpty()) {

				} else {
					removePlayerParty(getIndex());
				}
				member.getActionSender().sendMessage("@whi@(@yel@" + player.getUsername() + "@whi@) @red@has left the party.");
				if (temparray.get(i).isEmpty()) {
					break;
				}
			}
			player.clearMyParty();
			player.getActionSender().sendMessage("You have left the party.");

		} catch (Exception e) {
			System.out.println(e.getMessage() + " \nStack: " + e.getStackTrace().toString());
		}
	}

	public int getIndex() {
		try {

			for (int i = 0; i < player.myParty.size(); i++) {
				if (player.myParty.get(i).equals(player.getUsername())) {
					return i;
				}
			}
			return 1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 1;
		}
	}

	public int getPartySize() {
		return player.myParty.size() + 1;
	}

	public void removePlayerParty(int id) {
		player.myParty.remove(id);
	}

	public boolean isPartyFull() {
		return player.myParty.size() >= MaxPartySize;
	}

	public boolean partyContains(String name) {
		try {
			for (int i = 0; i < player.myParty.size(); i++) {
				if (player.myParty.get(i).equals(name)) {
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

	public boolean isPartyLeader() {
		return player.myParty.get(0).equals(player.getUsername());
	}

	public void summonParty() {
		/*if (!isPartyLeader()) { // Lets anyone in the party summon the others if they need with this disabled.
			player.getActionSender().sendMessage("You are not the party leader");
			return;
		}*/

		for (int i = 0; i < player.myParty.size(); i++) {
			if (player.myParty.get(i).equals(player.getUsername())) {

			} else {
				Player member = world.getPlayer1(player.myParty.get(i));
				member.getActionSender().sendMessage("@gre@" + player.getUsername() + " @whi@wishes to summon you. Type ::acceptpartysummon to be summoned.");
				member.summonLeader = player.getUsername();
			}
		}

	}

	public void acceptSummon() {
		Player leader = world.getPlayer1(player.myParty.get(0));
		if (player.inParty) {
			/*if (player.isSkulled()) { // I don't get why this is even here.
				player.getActionSender().sendMessage("Your leader wanted to summon you, but he cannot when you are skulled.");
				player.summonLeader = null;
				return;
			}*/
			if (player.summonLeader.equals(player.myParty.get(0))) {
				player.teleport(leader.getLocation().x, leader.getLocation().y, true);
				player.summonLeader = null;
			} else {
				player.getActionSender().sendMessage("Nobody has tried to summoned you.");
			}
		} else {

			player.getActionSender().sendMessage("Sorry, you are not in a party.");
		}
	}
}