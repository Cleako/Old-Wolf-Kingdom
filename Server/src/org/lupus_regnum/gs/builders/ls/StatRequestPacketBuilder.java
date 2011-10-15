package org.lupus_regnum.gs.builders.ls;

import org.lupus_regnum.config.Config;
import org.lupus_regnum.gs.builders.LSPacketBuilder;
import org.lupus_regnum.gs.connection.LSPacket;
import org.lupus_regnum.gs.model.World;


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
