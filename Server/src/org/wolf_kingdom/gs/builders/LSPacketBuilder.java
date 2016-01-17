package org.wolf_kingdom.gs.builders;

import org.wolf_kingdom.gs.connection.LSPacket;
import org.wolf_kingdom.gs.core.LoginConnector;
import org.wolf_kingdom.gs.phandler.PacketHandler;
import org.wolf_kingdom.gs.tools.DataConversions;


public class LSPacketBuilder extends StaticPacketBuilder {
    /**
     * ID of the packet
     */
    private int pID = 0;
    /**
     * UID of the packet
     */
    private long uID = 0;

    /**
     * Sets the handler for this packet.
     * 
     * @param handler
     *            The handler of the packet
     */
    public LSPacketBuilder setHandler(LoginConnector connector, PacketHandler handler) {
	uID = DataConversions.getRandom().nextLong();
	connector.setHandler(uID, handler);
	return this;
    }

    /**
     * Sets the ID for this packet.
     * 
     * @param id
     *            The ID of the packet
     */
    public LSPacketBuilder setID(int pID) {
	this.pID = pID;
	return this;
    }

    /**
     * Sets the uID for this packet.
     * 
     * @param id
     *            The uID of the packet
     */
    public LSPacketBuilder setUID(long uID) {
	this.uID = uID;
	return this;
    }

    /**
     * Returns a <code>LSPacket</code> object for the data contained in this
     * builder.
     * 
     * @return A <code>LSPacket</code> object
     */
    public LSPacket toPacket() {
	byte[] data = new byte[curLength];
	System.arraycopy(payload, 0, data, 0, curLength);
	return new LSPacket(null, pID, uID, data, bare);
    }

}