package org.lupus_regnum.gs.builders.ls;

import org.lupus_regnum.gs.builders.LSPacketBuilder;
import org.lupus_regnum.gs.connection.LSPacket;
import org.lupus_regnum.gs.model.Player;
import org.lupus_regnum.gs.model.World;
import org.lupus_regnum.gs.util.EntityList;


public class PlayerListRequestPacketBuilder {
	/**
	 * World instance
	 */
	public static final World world = World.getWorld();
	/**
	 * Packets uID
	 */
	private long uID;

	public LSPacket getPacket() {
		EntityList<Player> players = world.getPlayers();

		LSPacketBuilder packet = new LSPacketBuilder();
		packet.setUID(uID);
		packet.addInt(players.size());
		for (Player p : players) {
			packet.addLong(p.getUsernameHash());
			packet.addShort(p.getX());
			packet.addShort(p.getY());
		}
		return packet.toPacket();
	}

	/**
	 * Sets the packet to reply to
	 */
	public void setUID(long uID) {
		this.uID = uID;
	}
}
