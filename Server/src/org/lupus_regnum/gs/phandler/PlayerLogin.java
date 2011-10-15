package org.lupus_regnum.gs.phandler;

import org.apache.mina.common.IoSession;
import org.lupus_regnum.config.Constants;
import org.lupus_regnum.config.Formulae;
import org.lupus_regnum.gs.builders.MiscPacketBuilder;
import org.lupus_regnum.gs.builders.RSCPacketBuilder;
import org.lupus_regnum.gs.model.Bank;
import org.lupus_regnum.gs.model.InvItem;
import org.lupus_regnum.gs.model.Inventory;
import org.lupus_regnum.gs.model.Player;
import org.lupus_regnum.gs.model.PlayerAppearance;
import org.lupus_regnum.gs.model.Point;
import org.lupus_regnum.gs.model.World;
import org.lupus_regnum.gs.phandler.client.WieldHandler;
import org.lupus_regnum.gs.plugins.PluginHandler;
import org.lupus_regnum.gs.tools.DataConversions;



public class PlayerLogin implements PacketHandler {
    /**
     * 	World instance
     */
    private static final World world = World.getWorld();

    /**
     * The player to update
     */
    private Player player;

    public PlayerLogin(Player player) {
    	this.player = player;
    }

    public void handlePacket(org.lupus_regnum.gs.connection.Packet p, IoSession session) throws Exception {
		byte loginCode = p.readByte();
		RSCPacketBuilder pb = new RSCPacketBuilder();
		pb.setBare(true);
		pb.addByte(loginCode);
		player.getSession().write(pb.toPacket());
		if (loginCode == 0 || loginCode == 1 || loginCode == 99) {
		    player.setOwner(p.readInt());
		    
		    player.setGroupID(p.readInt());
	
		    player.setSubscriptionExpires(p.readLong());
	
		    player.setLastIP(DataConversions.IPToString(p.readLong()));
		    player.setLastLogin(p.readLong());
		    /**
		     * Check if account is a new account
		     */
		    if (player.getLastLogin() == 0L) { 
				player.setLocation(Point.location(121, 647), true);
				p.readShort();
				p.readShort();
		    } 
		    else {
		    	player.setLocation(Point.location(p.readShort(), p.readShort()), true);
		    }
		    
		    player.setFatigue(p.readShort());
		    player.setCombatStyle((int) p.readByte());
	
		    player.setPrivacySetting(0, p.readByte() == 1);
		    player.setPrivacySetting(1, p.readByte() == 1);
		    player.setPrivacySetting(2, p.readByte() == 1);
		    player.setPrivacySetting(3, p.readByte() == 1);
	
		    player.setGameSetting(0, p.readByte() == 1);
		    player.setGameSetting(2, p.readByte() == 1);
		    player.setGameSetting(3, p.readByte() == 1);
		    player.setGameSetting(4, p.readByte() == 1);
		    player.setGameSetting(5, p.readByte() == 1);
		    player.setGameSetting(6, p.readByte() == 1);
	
		    PlayerAppearance appearance = new PlayerAppearance(p.readShort(), p.readShort(), p.readShort(), p.readShort(), p.readShort(), p.readShort());
		    if (!appearance.isValid()) {
				loginCode = 7;
				player.destroy(true);
				player.getSession().close();
		    }
		    player.setAppearance(appearance);
		    player.setWornItems(player.getPlayerAppearance().getSprites());
	
		    player.setMale(p.readByte() == 1);
		    long skull = p.readLong();
		    if (skull > 0)
		    	player.addSkull(skull);
	
		    for (int i = 0; i < 18; i++) {
				int exp = (int) p.readLong();
				player.setExp(i, exp);
				player.setMaxStat(i, Formulae.experienceToLevel(exp));
				player.setCurStat(i, p.readShort());
		    }
	
		    player.setCombatLevel(Formulae.getCombatlevel(player.getMaxStats()));
	
		    Inventory inventory = new Inventory(player);
		    int invCount = p.readShort();
		    for (int i = 0; i < invCount; i++) {
				InvItem item = new InvItem(p.readShort(), p.readInt());
				if (p.readByte() == 1 && item.isWieldable()) {
				    item.setWield(true);
				    player.updateWornItems(item.getWieldableDef().getWieldPos(), item.getWieldableDef().getSprite());
				}
				inventory.add(item);
		    }
	
		    player.setInventory(inventory);
	
		    Bank bank = new Bank();
		    int bnkCount = p.readShort();
		    for (int i = 0; i < bnkCount; i++) {
				bank.add(new InvItem(p.readShort(), p.readInt()));
		    }
		    player.setBank(bank);
	
		    int friendCount = p.readShort();
		    for (int i = 0; i < friendCount; i++) {
		    	player.addFriend(p.readLong(), p.readShort());
		    }
		    
		    int ignoreCount = p.readShort();
		    for (int i = 0; i < ignoreCount; i++) {
		    	player.addIgnore(p.readLong());
		    }
	
		    /* Muted  */
		    
		    player.setMuted(p.readLong());
		    if(player.isMuted()) {
		    	player.getActionSender().sendMessage("@red@You are muted for " + player.getDaysMuted() + " days!");
		    }
		    
		    int quests = p.readInt();
		    for(int i = 0; i < quests; i++) {
		    	player.setQuestStage(p.readInt(), p.readInt(), false);
		    }
		    int cacheSize = p.readInt();
			for(int i = 0; i < cacheSize; i++) {
				/**
				 * Construct key
				 */
				int keyLenght = p.readInt();
				String key = new String(p.readBytes(keyLenght));
				/**
				 * Manage item type now
				 * 0 - Integer
				 * 1 - String
				 * 2 - Boolean
				 * 3 - Long
				 */
				int identifier = p.readInt();
				if(identifier == 0) {
					player.getCache().store(key, p.readInt());
				}
				if(identifier == 1) {
					int stringLength = p.readInt();
					String string = new String(p.readBytes(stringLength));
					player.getCache().store(key, string);
				}
				if(identifier == 2) {
					player.getCache().store(key, (p.readInt() == 1));
				}
				if(identifier == 3) {
					player.getCache().store(key, p.readLong());
				}
			}
		    
		    
		    /* End of loading methods */
	
	    
		    /* Send client data */
		    world.registerPlayer(player);
	
		    player.updateViewedPlayers();
		    player.updateViewedObjects();
	
		    MiscPacketBuilder sender = player.getActionSender();
		    if(!player.getCache().hasKey("lastDeath")) {
				player.getCache().store("lastDeath", 0L);
			}
			if(!player.getCache().hasKey("clan")) {
				player.getCache().store("clan", 0L);
			}
			sender.sendServerInfo();
		    sender.sendFatigue();
		    sender.sendWorldInfo(); // sends info for the client to load terrain
		    sender.sendInventory();
		    sender.sendEquipmentStats();
		    sender.sendStats();
		    sender.sendPrivacySettings();
		    sender.sendGameSettings();
		    sender.sendFriendList();
		    sender.sendIgnoreList();
		    sender.sendCombatStyle();
	
	
		    for (InvItem i : player.getInventory().getItems()) {
				if (i.isWielded() && (i.getID() == 407 || i.getID() == 401)) {
				    if (player.getCurStat(6) < 31) {
					player.getActionSender().sendMessage("You must have at least 31 magic");
					WieldHandler.unWieldItem(player, i, true);
					player.getActionSender().sendInventory();
				    }
				}
				if(i.getID() == 1288 && i.isWielded()) {
					boolean found = false;
					for(int it=0; it < 18; it++) {
					    if(player.getMaxStat(it) == 99) {
						found = true; 
						break;
					    }
					}
					if(!found) {
					    player.getActionSender().sendMessage("Sorry, you need any skill of level 99 to wield this cape of legends");
					    WieldHandler.unWieldItem(player, i, true);
					} 
			    }
		    }
		    if(player.getLocation().inWilderness())
		    	player.p2pWildy();
	
		    int timeTillShutdown = World.getWorld().getServer().timeTillShutdown();
		    if (timeTillShutdown > -1)
		    	sender.startShutdown((int) (timeTillShutdown / 1000));
	
		    if (player.getLastLogin() == 0L) {
				player.getInventory().add(new InvItem(4));
				player.getInventory().add(new InvItem(70));
				player.getInventory().add(new InvItem(376));
				player.getInventory().add(new InvItem(156));
				player.getInventory().add(new InvItem(87));
				player.getInventory().add(new InvItem(1263));
				player.getActionSender().sendInventory();
				player.setChangingAppearance(true);
				sender.sendAppearanceScreen();
		    }
	
		    player.getActionSender().sendWakeUp(false);
		    sender.sendLoginBox();
		    sender.sendMessage(Constants.GameServer.MOTD);
		    sender.sendOnlinePlayers();
		    sender.sendQuestInfo();
		    
		    if (player.isAdmin() || player.isPMod()) {
				player.setnopk(true);
				player.setnonaggro(true);
		    }
	
		    player.setLoggedIn(true);
		    player.setBusy(false);
		    
		    PluginHandler.getPluginHandler().handleAction("PlayerLogin", new Object[] { player });
		} 
		else {
			player.destroy(true);
		}
    }
}
