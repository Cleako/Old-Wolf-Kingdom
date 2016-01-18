package org.wolf_kingdom.gs.plugins.commands;

import java.util.ArrayList;

import org.wolf_kingdom.gs.model.Player;
import org.wolf_kingdom.gs.model.World;
import org.wolf_kingdom.gs.model.snapshot.Chatlog;
import org.wolf_kingdom.gs.plugins.listeners.action.CommandListener;
import org.wolf_kingdom.gs.util.Logger;
import org.wolf_kingdom.gs.event.Clan;
import org.wolf_kingdom.gs.event.Party;
import org.wolf_kingdom.gs.tools.DataConversions;
import org.wolf_kingdom.gs.builders.ls.MiscPacketBuilder;
import org.wolf_kingdom.ls.packethandler.loginserver.JoinClan;


public class RegularUser implements CommandListener {

	@Override
	public void onCommand(String command, String[] args, Player player) {
		MiscPacketBuilder loginServer = World.getWorld().getServer().getLoginConnector().getActionSender();
		
		if (command.equals("invite")) {
			if (args[0] == null)
				return;
			if (!player.inParty()) {
				player.getActionSender().sendMessage("You are not in a party!");
				return;
			}
			if (player.getParty().partyLeader != player) {
				player.getActionSender().sendMessage(
				"You are not the party leader!");
				return;
			}
			int size = 4;
			if (player.isSubscriber())
				size = 8;
			
			if (player.getParty().getSize() > size) {
				player.getActionSender().sendMessage(
				"You have too many players in the party already!");
				return;
			}
			// forgot you had to write _ instead of space ingame.
			String name = args[0].replace("_", " ");

			Player p = World.getWorld().getPlayer(
					DataConversions.usernameToHash(name));
			if (p == null) {
				player.getActionSender().sendMessage(
				"This player does not exist!");
			}

			if (p.getParty() != null) {
				player.getActionSender().sendMessage(
				"That player is already in a party sorry");
				return;
			}
			player.getActionSender().sendMessage(
					"You have invited " + p.getUsername() + " to the party");

			p.invitedPlayer = player;
			p.getActionSender().sendPartyInvite(player);
		}
		
		
		/*
		 * Begin party commands
		 */
		if (command.equals("tellparty")) {
			Party party = new Party(player);
			String newStr = "";
			for(int i = 0; i < args.length; i++) {
				newStr = newStr + args[i] + " ";					
			}
			party.sendPartyMessageUser(newStr);
			return;
		}
		else if(command.equals("quitparty")) {
			Party party = new Party(player);
			party.removePlayer();
			return;
		} 
		else if(command.equals("invitetoparty")) {
			Party party = new Party(player);
			args[0] = args[0].replace("_", " ");
			party.invitePlayer(args[0]);
			return;
		}
		else if(command.equals("partylist")) {
			Party party = new Party(player);
			player.getActionSender().sendMessage("@gre@Party Members: @whi@" + party.partyMembersString());
			return;
		}
		else if(command.equals("summonparty")) {
			Party party = new Party(player);
			party.summonParty();
			return;
		}
		else if(command.equals("acceptpartysummon")) {
			Party party = new Party(player);
			party.acceptSummon();
			return;
		}
		else if(command.equals("acceptpartyinvite")) {
			Party party = new Party(player);
			party.acceptPlayer();
			return;
		} 
		// End party commands
		
		/*
		 * Begin clan commands
		 */
		else if(command.equals("tellclan")) {
			Clan clan = new Clan(player);
			String newStr = "";
			for(int i = 0; i < args.length; i++) {
				newStr = newStr + args[i] + " ";
			}
			clan.sendClanMessageUser(newStr);
			return;
        }
		else if(command.equals("invitetoclan")) {
			Clan clan = new Clan(player);
			args[0] = args[0].replace("_", " ");
			clan.invitePlayer(args[0]);
			return;
		}
		else if(command.equals("quitclan")) {
			Clan clan = new Clan(player);
			clan.removePlayer();
			return;
		}
		else if(command.equals("clanlist")) {
			Clan clan = new Clan(player);
			player.getActionSender().sendMessage("@gre@Clan Members: @whi@" + clan.clanMembersString());
			return;
		} 
		else if(command.equals("summonclan")) {
			Clan clan = new Clan(player);
			clan.summonClan();
			return;
		}
		else if(command.equals("acceptclansummon")) {
			Clan clan = new Clan(player);
			clan.acceptSummon();
			return;
		}
		else if(command.equals("acceptclaninvite")) {
			Clan clan = new Clan(player);
			clan.acceptPlayer();
			return;
		}
		else if(command.equals("registerclan")) {
            long player2 = DataConversions.usernameToHash(player.getUsername());
            long newStr = DataConversions.usernameToHash(args[0]);
            loginServer.joinClan(player2, newStr);
            player.getActionSender().sendMessage("@cya@[@whi@Server@cya@]@whi@: Your clan, (@gre@" + DataConversions.hashToUsername(newStr) + "@whi@), has been registered.");
            return;
        }
		// End clan commands
		
		if (command.equals("say")) {
			if(player.isMuted()) { 
				player.getActionSender().sendMessage("You are muted, you cannot send messages");
				return;
			}	
			if(System.currentTimeMillis() - player.getLastSayTime() < 10000 && !player.isPMod()) {
				player.getActionSender().sendMessage("You can only use ::say every 10 seconds");
				return;
			}
			player.setLastSayTime();
		    String newStr = "@gre@";
		    for (int i = 0; i < args.length; i++) {
		    	newStr += args[i] + " "; 
		    }
		    newStr = (player.isPMod() ? "#pmd# " : "") + player.getUsername() + ": " + newStr;
		    World.getWorld().sendWorldAnnouncement(newStr);
		    World.getWorld().addEntryToSnapshots(new Chatlog(player.getUsername(),"(Global) " + newStr, new ArrayList<String>()));
		    return;
		}
		else if (command.equals("help") || command.equals("commands")) {
		    player.getActionSender().sendAlert(
			//"@ora@::skull " + 
			"@gre@::say " + 
			//"@red@::noenergy " +
			"@cya@::onlinelist " +
			//"@ran@::blink " +
			//"@blu@::stuck " + 
                        "@red@::online " + 
                            "", true);
			
		    return;
		}
		/*else if (command.equals("skull")) {
		    int length = 20;
		    player.addSkull(length * 60000);
		    return;
		}*/
		/*else if (command.equals("noenergy")) {
		    player.setFatigue(0);
		    player.getActionSender().sendFatigue();
		    player.getActionSender().sendMessage("You discharge all of your energy reserves.");
		    return;
		}*/
		else if (command.equals("online")) {
		    player.getActionSender().sendOnlinePlayers();
		    return;
		}
		else if (command.equals("onlinelist")) {
		    player.getActionSender().sendOnlinePlayersList();
		    return;
		}
		/*else if (command.equals("stuck")) {
		    if (System.currentTimeMillis() - player.getCurrentLogin() < 30000) {
				player.getActionSender().sendMessage("You cannot do this after you have recently logged in");
				return;
		    }
	    	if(!player.canLogout() || System.currentTimeMillis() - player.getLastMoved() < 10000) {
	    		player.getActionSender().sendMessage("You must stand peacefully in one place for 10 seconds!");
	    		return;
	    	}
		    if (player.getLocation().inModRoom() && !player.isMod()) {
		    	player.getActionSender().sendMessage("You cannot use ::stuck here");
		    } 
		    else if (!player.isMod() && System.currentTimeMillis() - player.getLastMoved() < 30000 && System.currentTimeMillis() - player.getCastTimer() < 300000) {
				player.getActionSender().sendMessage("There is a 30 second delay on using ::stuck, please stand still for 30 seconds.");
			}
		    else if (!player.inCombat() && System.currentTimeMillis() - player.getCombatTimer() > 30000 || player.isMod()) {
				player.setCastTimer();
				player.teleport(122, 647, true);
		    }
		    else {
		    	player.getActionSender().sendMessage("You cannot use ::stuck for 30 seconds after combat");
		    }
		    return;
		}*/		
		return;
	}

}
