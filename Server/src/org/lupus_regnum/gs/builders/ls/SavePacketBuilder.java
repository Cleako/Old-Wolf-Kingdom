package org.lupus_regnum.gs.builders.ls;

import java.util.Map;

import org.lupus_regnum.gs.builders.LSPacketBuilder;
import org.lupus_regnum.gs.connection.LSPacket;
import org.lupus_regnum.gs.model.Bank;
import org.lupus_regnum.gs.model.InvItem;
import org.lupus_regnum.gs.model.Inventory;
import org.lupus_regnum.gs.model.Player;
import org.lupus_regnum.gs.model.PlayerAppearance;
import org.lupus_regnum.gs.model.World;
import org.lupus_regnum.gs.plugins.QuestInterface;
import org.lupus_regnum.gs.tools.DataConversions;


public class SavePacketBuilder {
	/**
	 * Player to save
	 */
	private Player player;

	public LSPacket getPacket() {
		
		LSPacketBuilder packet = new LSPacketBuilder();
		packet.setID(20);
		packet.addLong(player.getUsernameHash());
		packet.addInt(player.getOwner());

		packet.addLong(player.getLastLogin() == 0L && player.isChangingAppearance() ? 0 : player.getCurrentLogin());
		packet.addLong(DataConversions.IPToLong(player.getCurrentIP()));
		packet.addShort(player.getCombatLevel());
		packet.addShort(player.getSkillTotal());
		packet.addShort(player.getX());
		packet.addShort(player.getY());
		packet.addShort(player.getFatigue());

		PlayerAppearance a = player.getPlayerAppearance();
		packet.addByte((byte) a.getHairColour());
		packet.addByte((byte) a.getTopColour());
		packet.addByte((byte) a.getTrouserColour());
		packet.addByte((byte) a.getSkinColour());
		packet.addByte((byte) a.getSprite(0));
		packet.addByte((byte) a.getSprite(1));

		packet.addByte((byte) (player.isMale() ? 1 : 0));
		packet.addLong(player.getSkullTime());
		packet.addByte((byte) player.getCombatStyle());

		for (int i = 0; i < 18; i++) {
			packet.addLong(player.getExp(i));
			packet.addShort(player.getCurStat(i));
		}

		Inventory inv = player.getInventory();
		packet.addShort(inv.size());
		for (InvItem i : inv.getItems()) {
			packet.addShort(i.getID());
			packet.addInt(i.getAmount());
			packet.addByte((byte) (i.isWielded() ? 1 : 0));
		}

		Bank bnk = player.getBank();
		packet.addShort(bnk.size());
		for (InvItem i : bnk.getItems()) {
			packet.addShort(i.getID());
			packet.addInt(i.getAmount());
		}
		packet.addInt(World.getWorld().getQuests().size());
		for(QuestInterface q : World.getWorld().getQuests()) {
			packet.addInt(q.getQuestId());
			packet.addInt(player.getQuestStage(q));
		}
		
		Map<String, Object> cache = player.getCache().getCacheMap();
		/**
		 * Send the size of the cache to the login server
		 */
		packet.addInt(cache.size());
		/**
		 * first Integer identifies the type. 
		 * 0 - Integer
		 * 1 - String
		 * 2 - Boolean
		 * 3 - Long
		 */
		for(String key : cache.keySet()) {
			byte[] data = key.getBytes();
			packet.addInt(data.length);
			packet.addBytes(data);
			
			Object o = cache.get(key);
			if(o instanceof Integer) {
				packet.addInt(0);
				packet.addInt((Integer)o);
			}
			if(o instanceof String) {
				packet.addInt(1);
				data = ((String)o).getBytes();
				packet.addInt(data.length);
				packet.addBytes(data);
			}
			if(o instanceof Boolean) {
				packet.addInt(2);
				packet.addInt((Boolean)o ? 1 : 0);
			}
			if(o instanceof Long) {
				packet.addInt(3);
				packet.addLong((Long)o);
			}
		}	
		return packet.toPacket();
	}

	/**
	 * Sets the player to save
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}
}