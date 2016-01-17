package org.wolf_kingdom.gs.builders.ls;

import org.wolf_kingdom.config.Config;
import org.wolf_kingdom.gs.builders.LSPacketBuilder;
import org.wolf_kingdom.gs.connection.LSPacket;
import org.wolf_kingdom.gs.model.World;


public class StatRequestPacketBuilder {
	/**
	 * World instance
	 */
	public static final World world = World.getWorld();
	/**
	 * Packets uID
	 */
	private long uID;

	public LSPacket getPacket() {
		LSPacketBuilder packet = new LSPacketBuilder();
		packet.setUID(uID);
		packet.addInt(world.countPlayers());
		packet.addInt(world.countNpcs());
		packet.addLong(Config.START_TIME);
		return packet.toPacket();
	}

	/**
	 * Sets the packet to reply to
	 */
	public void setUID(long uID) {
		this.uID = uID;
	}
}
