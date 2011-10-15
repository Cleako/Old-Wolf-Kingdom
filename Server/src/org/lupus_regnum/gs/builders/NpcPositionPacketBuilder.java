package org.lupus_regnum.gs.builders;

import java.util.Collection;

import org.lupus_regnum.gs.connection.RSCPacket;
import org.lupus_regnum.gs.model.Npc;
import org.lupus_regnum.gs.model.Player;
import org.lupus_regnum.gs.tools.DataConversions;
import org.lupus_regnum.gs.util.StatefulEntityCollection;


public class NpcPositionPacketBuilder {
	
    private Player playerToUpdate;

    public RSCPacket getPacket() {
	StatefulEntityCollection<Npc> watchedNpcs = playerToUpdate.getWatchedNpcs();
	Collection<Npc> newNpcs = watchedNpcs.getNewEntities();
	Collection<Npc> knownNpcs = watchedNpcs.getKnownEntities();
	
	RSCPacketBuilder packet = new RSCPacketBuilder();
	packet.setID(77);
	packet.addBits(knownNpcs.size(), 8);
	for (Npc n : knownNpcs) {
	    packet.addBits(n.getIndex(), 16);
	    if (watchedNpcs.isRemoving(n)) {
			packet.addBits(1, 1);
			packet.addBits(1, 1);
			packet.addBits(12, 4);
		    } 
	    else if (n.hasMoved()) {
	    	packet.addBits(1, 1);
	    	packet.addBits(0, 1);
	    	packet.addBits(n.getSprite(), 3);
	    	}
	    else if (n.spriteChanged()) {
	    	packet.addBits(1, 1);
	    	packet.addBits(1, 1);
	    	packet.addBits(n.getSprite(), 4);
	    } 
	    else {
	    	packet.addBits(0, 1);
	    }
	}
		for (Npc n : newNpcs) {
		    byte[] offsets = DataConversions.getMobPositionOffsets(n.getLocation(), playerToUpdate.getLocation());
		    packet.addBits(n.getIndex(), 16);
		    packet.addBits(offsets[0], 5);
		    packet.addBits(offsets[1], 5);
		    packet.addBits(n.getSprite(), 4);
		    packet.addBits(n.getID(), 10);
		}
	return packet.toPacket();
    }

    /**
     * Sets the player to update
     */
    public void setPlayer(Player p) {
	playerToUpdate = p;
    }
}
