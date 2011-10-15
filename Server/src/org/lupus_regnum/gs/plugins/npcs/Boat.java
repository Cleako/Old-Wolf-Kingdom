package org.lupus_regnum.gs.plugins.npcs;

import org.lupus_regnum.gs.event.ShortEvent;
import org.lupus_regnum.gs.model.ChatMessage;
import org.lupus_regnum.gs.model.MenuHandler;
import org.lupus_regnum.gs.model.Npc;
import org.lupus_regnum.gs.model.Player;
import org.lupus_regnum.gs.model.Point;
import org.lupus_regnum.gs.model.World;
import org.lupus_regnum.gs.plugins.listeners.action.TalkToNpcListener;
import org.lupus_regnum.gs.tools.DataConversions;

public class Boat implements TalkToNpcListener {
	/**
	 * World instance
	 */
	public static final World world = World.getWorld();

	private static final String[] destinationNames = {
		"Karamja", "Brimhaven", "Port Sarim", "Ardougne",
		"Port Khazard", "Catherby", "Shilo"
	};
	private static final Point[] destinationCoords = {
		Point.location(324, 713), Point.location(467, 649), Point.location(268, 650), Point.location(538, 616),
		Point.location(541, 702), Point.location(439, 506), Point.location(471, 853)
	};

	int[] boatMen = new int[]{ 166, 170, 171, 163, 317, 316, 280, 764 };
	
	@Override
	public void onTalkToNpc(Player player, final Npc npc) {
		if(!DataConversions.inArray(boatMen, npc.getID())) {
			return;
		}
  		player.informOfNpcMessage(new ChatMessage(npc, "G'day sailor, where would you like to go?", player));
  		player.setBusy(true);
  		world.getDelayedEventHandler().add(new ShortEvent(player) {
  			public void action() {
  				owner.setBusy(false);
			owner.setMenuHandler(new MenuHandler(destinationNames) {
				public void handleReply(final int option, final String reply) {
					if(owner.isBusy() || option < 0 || option >= destinationNames.length) {
						npc.unblock();
						return;
					}
					owner.informOfChatMessage(new ChatMessage(owner, reply + " please", npc));
					owner.setBusy(true);
					world.getDelayedEventHandler().add(new ShortEvent(owner) {
						public void action() {
  								owner.getActionSender().sendMessage("You board the ship");
  								world.getDelayedEventHandler().add(new ShortEvent(owner) {
  									public void action() {
  										Point p = destinationCoords[option];
  										owner.teleport(p.getX(), p.getY(), false);
  										owner.getActionSender().sendMessage("The ship arrives at " + reply);
									owner.setBusy(false);
  										npc.unblock();
  									}
  								});
						}
					});
				}
			});
			owner.getActionSender().sendMenu(destinationNames);
  			}
  		});
  		npc.blockedBy(player);
	}
}
