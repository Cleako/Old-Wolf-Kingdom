package org.wolf_kingdom.gs.model;

import java.util.ArrayList;

/**
 * 
 * @author xEnt
 * 
 */

public class Party {

	public int partyID;

	public Party(Player player, int ID) {
		partyID = ID;
		partyLeader = player;
		addMember(player);
	}

	public Player partyLeader;

	public ArrayList<Player> members = new ArrayList<Player>(8);

	public void addMember(Player p) {
		members.add(p);
		p.partyID = partyID;
		for (Player pl : members) {
			pl.getActionSender().sendParty();
		}
	}

	public void partyMessage(String s) {
		for (Player p : members) {
			p.getActionSender().sendMessage("[Party] " + s);
		}
	}

	public void removePlayer(Player toRemove) {
		if (toRemove == partyLeader) {
			for (Player pl : members) {
				if (toRemove != pl) {
					partyLeader = pl;
					partyMessage(pl.getUsername() + " is now the party leader");
					break;
				}
			}
		}
		members.remove(toRemove);
		toRemove.partyID = -1;
		toRemove.getActionSender().sendParty();
		for (Player p : members) {
			p.getActionSender().sendParty();
		}
	}

	public void updateHP(Player p) {
		for (Player player : members) {
			player.getActionSender().sendPartyHP(p);
		}
	}

	public boolean inParty() {
		return members.size() > 0;
	}

	public int getSize() {
		return members.size();
	}

}
