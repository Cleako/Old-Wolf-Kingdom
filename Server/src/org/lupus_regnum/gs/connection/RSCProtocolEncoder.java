package org.lupus_regnum.gs.connection;

import org.apache.mina.common.ByteBuffer;
import org.apache.mina.common.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.lupus_regnum.gs.util.Logger;


/**
 * Encodes the high level <code>RSCPacket</code> class into the proper protocol
 * data required for transmission.
 */
public class RSCProtocolEncoder implements ProtocolEncoder {
    /**
     * Releases all resources used by this encoder.
     * 
     * @param session
     *            The IO session
     */
    public void dispose(IoSession session) {
    }

    /**
     * Converts a <code>RSCPacket</code> object into the raw data needed for
     * transmission.
     * 
     * @param session
     *            The IO session associated with the packet
     * @param message
     *            A <code>RSCPacket</code> to encode
     * @param out
     *            The output stream to which to write the data
     */
    public synchronized void encode(IoSession session, Object message, ProtocolEncoderOutput out) {
		if (!(message instanceof RSCPacket)) {
		    Logger.error(new Exception("Wrong packet type! " + message.toString()));
		    return;
		}
		try {
		    RSCPacket p = (RSCPacket) message;
		    byte[] data = p.getData();
		    int dataLength = data.length;
		    ByteBuffer buffer;
		    if (!p.isBare()) {
		    	buffer = ByteBuffer.allocate(dataLength + 3);
				byte[] outlen = { (byte) (dataLength >> 8), (byte) (dataLength) };
				buffer.put(outlen);
				int id = p.getID();
				buffer.put((byte) id);
		    } 
		    else {
		    	buffer = ByteBuffer.allocate(dataLength);
		    }
		    buffer.put(data, 0, dataLength);
		    buffer.flip();
		    out.write(buffer);
		    return;
		} 
		catch (Exception e) {
		    e.printStackTrace();
		}
	}
}
