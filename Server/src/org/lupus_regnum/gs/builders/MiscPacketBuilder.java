package org.lupus_regnum.gs.builders;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.lupus_regnum.config.Config;
import org.lupus_regnum.config.Formulae;
import org.lupus_regnum.gs.connection.RSCPacket;
import org.lupus_regnum.gs.model.Bank;
import org.lupus_regnum.gs.model.InvItem;
import org.lupus_regnum.gs.model.Player;
import org.lupus_regnum.gs.model.Shop;
import org.lupus_regnum.gs.model.World;
import org.lupus_regnum.gs.plugins.QuestInterface;
import org.lupus_regnum.gs.tools.Captcha;



public class MiscPacketBuilder {
     /**
     * The player we are creating packets for
     */
    private Player player; 
    /**
     * Constructs a new MiscPacketBuilder
     */
    public MiscPacketBuilder(Player player) {
    	this.player = player;
    }

    /**
     * Hides the bank windows
     */
    public void hideBank() {
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(171);
		player.getSession().write(s.toPacket());
    }
	
	/**
	 * Sends party invites
	 */
	public void sendPartyInvite(Player affected) {
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(69);
		s.addByte((byte) 9); // invite
		s.addLong(affected.getUsernameHash());
		s.addShort(affected.getIndex());
		//packets.add(s.toPacket());
		player.getSession().write(s.toPacket());
	}

    /**
     * Hides a question menu
     */
    public void hideMenu() {
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(127);
		player.getSession().write(s.toPacket());
    }

    /**
     * Hides the shop window
     */
    public void hideShop() {
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(220);
		player.getSession().write(s.toPacket());
    }

    /**
     * Sends a message box
     */
    public void sendAlert(String message, boolean big) {
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(big ? 64 : 148);
		s.addBytes(message.getBytes());
		player.getSession().write(s.toPacket());
    }

    public void sendAppearanceScreen() {
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(207);
		player.getSession().write(s.toPacket());
    }

    /**
     * Deny logging out
     */
    public void sendCantLogout() {
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(136);
		player.getSession().write(s.toPacket());
    }

    /**
     * Stores a key-value pair in the cache
     */
	public void sendCacheableString(String key, String value) {
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(132);
		s.addByte((byte) key.length());
		s.addBytes(key.getBytes());
		s.addByte((byte) value.length());
		s.addBytes(value.getBytes());
		player.getSession().write(s.toPacket());
    }

    /**
     * Stores a key-value pair in the cache
     */
	public void sendCacheableLong(String key, Long value) {
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(133);
		s.addByte((byte) key.length());
		s.addBytes(key.getBytes());
		s.addLong(value);
		player.getSession().write(s.toPacket());
    }

    /**
     * Stores a key-value pair in the cache
     */	
	public void sendCacheableInt(String key, Integer value) {
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(134);
		s.addByte((byte) key.length());
		s.addBytes(key.getBytes());
		s.addInt(value);
		player.getSession().write(s.toPacket());
    }	

    /**
     * Stores a key-value pair in the cache
     */
	public void sendCacheableBoolean(String key, Boolean value) {
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(135);
		s.addByte((byte) key.length());
		s.addBytes(key.getBytes());
		s.addByte((byte)(value ? 1 : 0));
		player.getSession().write(s.toPacket());
    }
	
    /**
     * Sends the players combat style
     */
	public void sendCombatStyle() {
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(129);
		s.addByte((byte) player.getCombatStyle());
		player.getSession().write(s.toPacket());
    }

    /**
     * Alert the client that they just died
     */
    public void sendDied() {
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(165);
		player.getSession().write(s.toPacket());
    }

    public void sendDuelAccept() {
		Player with = player.getWishToDuel();
		if (with == null) { // This shouldn't happen
		    return;
		}
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(147);
		s.addLong(with.getUsernameHash());
		s.addByte((byte) with.getDuelOffer().size());
		for (InvItem item : with.getDuelOffer()) {
		    s.addShort(item.getID());
		    s.addInt(item.getAmount());
		}
		s.addByte((byte) player.getDuelOffer().size());
		for (InvItem item : player.getDuelOffer()) {
		    s.addShort(item.getID());
		    s.addInt(item.getAmount());
		}
	
		s.addByte((byte) (player.getDuelSetting(0) ? 1 : 0)); 
		s.addByte((byte) (player.getDuelSetting(1) ? 1 : 0));
		s.addByte((byte) (player.getDuelSetting(2) ? 1 : 0)); 
		s.addByte((byte) (player.getDuelSetting(3) ? 1 : 0)); 
	
		player.getSession().write(s.toPacket());
    }

    public void sendDuelAcceptUpdate() {
		Player with = player.getWishToDuel();
		if (with == null) { // This shouldn't happen
		    return;
		}
		RSCPacketBuilder s1 = new RSCPacketBuilder();
		s1.setID(97);
		s1.addByte((byte) (player.isDuelOfferAccepted() ? 1 : 0));
		player.getSession().write(s1.toPacket());
	
		RSCPacketBuilder s2 = new RSCPacketBuilder();
		s2.setID(65);
		s2.addByte((byte) (with.isDuelOfferAccepted() ? 1 : 0));
		player.getSession().write(s2.toPacket());
    }

    public void sendDuelItems() {
		Player with = player.getWishToDuel();
		if (with == null) { // This shouldn't happen
		    return;
		}
		ArrayList<InvItem> items = with.getDuelOffer();
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(63);
		s.addByte((byte) items.size());
		for (InvItem item : items) {
		    s.addShort(item.getID());
		    s.addInt(item.getAmount());
		}
		player.getSession().write(s.toPacket());
    }

    public void sendDuelSettingUpdate() {
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(198);
		s.addByte((byte) (player.getDuelSetting(0) ? 1 : 0));
		s.addByte((byte) (player.getDuelSetting(1) ? 1 : 0));
		s.addByte((byte) (player.getDuelSetting(2) ? 1 : 0));
		s.addByte((byte) (player.getDuelSetting(3) ? 1 : 0));
		player.getSession().write(s.toPacket());
    }

    public void sendDuelWindowClose() {
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(160);
		player.getSession().write(s.toPacket());
    }

    public void sendDuelWindowOpen() {
		Player with = player.getWishToDuel();
		if (with == null) { // This shouldn't happen
		    return;
		}
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(229);
		s.addShort(with.getIndex());
		player.getSession().write(s.toPacket());
    }

    /**
     * Enter Sleep
     */
    public void sendEnterSleep() {
		player.isSleeping = true;
		byte[] image = Captcha.getCaptcha().generateCaptcha(player);
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(206);
		s.addBytes(image, 0, image.length);
		player.getSession().write(s.toPacket());
    }

    /**
     * Updates the equipment status
     */
    public void sendEquipmentStats() {
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(177);
		s.addShort(player.getArmourPoints());
		s.addShort(player.getWeaponAimPoints());
		s.addShort(player.getWeaponPowerPoints());
		s.addShort(player.getMagicPoints());
		s.addShort(player.getPrayerPoints());
		s.addShort(player.getRangePoints());
		player.getSession().write(s.toPacket());
    }

    /**
     * Updates the fatigue percentage
     */
    public void sendFatigue() {
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(126);
		s.addShort(player.getFatigue());
		player.getSession().write(s.toPacket());
    }

    /**
     * Sends the whole friendlist
     */
    public void sendFriendList() {
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(249);
		s.addByte((byte) player.getFriendList().size());
		for (Entry<Long, Integer> friend : player.getFriendList()) {
		    int world = friend.getValue();
		    s.addLong(friend.getKey());
		    s.addByte((byte) (world == Config.SERVER_NUM ? 99 : world));
		}
		player.getSession().write(s.toPacket());
    }

    /**
     * Updates a friends login status
     */
    public void sendFriendUpdate(long usernameHash, int world) {
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(25);
		s.addLong(usernameHash);
		s.addByte((byte) (world == Config.SERVER_NUM ? 99 : world));
		player.getSession().write(s.toPacket());
    }

    /**
     * Updates game settings, ie sound effects etc
     */
    public void sendGameSettings() {
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(152);
		s.addByte((byte) (player.getGameSetting(0) ? 1 : 0));
		s.addByte((byte) (player.getGameSetting(2) ? 1 : 0));
		s.addByte((byte) (player.getGameSetting(3) ? 1 : 0));
		s.addByte((byte) (player.getGameSetting(4) ? 1 : 0));
		s.addByte((byte) (player.getGameSetting(5) ? 1 : 0));
		s.addByte((byte) (player.getGameSetting(6) ? 1 : 0));
		player.getSession().write(s.toPacket());
    }

    /**
     * Sends the whole ignore list
     */
    public void sendIgnoreList() {
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(2);
		s.addByte((byte) player.getIgnoreList().size());
		for (Long usernameHash : player.getIgnoreList()) {
		    s.addLong(usernameHash.longValue());
		}
		player.getSession().write(s.toPacket());
    }

    /**
     * Incorrect sleep word!
     */
    public void sendIncorrectSleepword() {
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(225);
		player.getSession().write(s.toPacket());
    }

    public void sendInfo2() {
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(211);
		player.getSession().write(s.toPacket());
    }

    public void sendInventory() {
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(114);
		s.addByte((byte) player.getInventory().size());
		for (InvItem item : player.getInventory().getItems()) {
		    s.addShort(item.getID() + (item.isWielded() ? 32768 : 0));
		    if (item.getDef().isStackable()) {
			s.addInt(item.getAmount());
		    }
		}
		player.getSession().write(s.toPacket());
    }

    /**
     * Displays the login box and last IP and login date
     */
    public void sendLoginBox() {
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(248);
		s.addShort(player.getDaysSinceLastLogin());
		s.addShort(player.getDaysSubscriptionLeft());
		s.addBytes(player.getLastIP().getBytes());
		player.getSession().write(s.toPacket());
    }

    /**
     * Confirm logout allowed
     */
    public RSCPacket sendLogout() {
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(222);
		RSCPacket packet = s.toPacket();
		player.getSession().write(packet);
		return packet;
    }
    /**
     * Sends quest names and stages
     */
    public void sendQuestInfo() {
		RSCPacketBuilder s = new RSCPacketBuilder();
		List<QuestInterface> quests = World.getWorld().getQuests();
		s.setID(233);
		s.addByte((byte) quests.size());
		for (QuestInterface q : quests) {
			s.addInt(q.getQuestId());
			s.addInt(player.getQuestStage(q));
			s.addByte((byte) q.getQuestName().length());
			s.addBytes(q.getQuestName().getBytes());
		}
		player.getSession().write(s.toPacket());
    }
    /**
     * Sends quest stage
     */
    public void sendQuestInfo(int questID, int stage) {
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(234);
		s.addInt(questID);
		s.addInt(stage);
		player.getSession().write(s.toPacket());
    }
    /**
     * Shows a question menu
     */
    public void sendMenu(String[] options) {
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(223);
		s.addByte((byte) options.length);
		for (String option : options) {
		    s.addByte((byte) option.length());
		    s.addBytes(option.getBytes());
		}
		player.getSession().write(s.toPacket());
    }

    public void sendMessage(String message) {
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(48);
		s.addBytes(message.getBytes());
		player.getSession().write(s.toPacket());
    }

    public void sendOnlinePlayers() {
    	int players =  World.getWorld().getPlayers().size() ;
		
		for(Player p : World.getWorld().getPlayers()) {
			if(p.isPMod() && p.getPrivacySetting(1)) {
				players--; 
			}
		}
		this.sendMessage("@yel@Players Online: @whi@" + players);
    }
    public void sendOnlinePlayersList() {
    	int players =  World.getWorld().getPlayers().size() ;
		
		String newStr = "";
		for(Player p : World.getWorld().getPlayers()) {
			if(p.isPMod() && p.getPrivacySetting(1)) {
				players--; 
			}
			else {
				newStr += p.getUsername() + ", ";
			}
		}
		
		this.sendAlert("@yel@Players Online: @whi@" + players + " " + newStr, true);
    }
    /**
     * Sends the prayer list of activated/deactivated prayers
     */
    public void sendPrayers() {
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(209);
		for (int x = 0; x < 14; x++) {
		    s.addByte((byte) (player.isPrayerActivated(x) ? 1 : 0));
		}
		player.getSession().write(s.toPacket());
    }
    

    
    /**
     * Updates privacy settings, ie pm block etc
     */
    public void sendPrivacySettings() {
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(158);
		s.addByte((byte) (player.getPrivacySetting(0) ? 1 : 0));
		s.addByte((byte) (player.getPrivacySetting(1) ? 1 : 0));
		s.addByte((byte) (player.getPrivacySetting(2) ? 1 : 0));
		s.addByte((byte) (player.getPrivacySetting(3) ? 1 : 0));
		player.getSession().write(s.toPacket());
    }

    /**
     * Send a private message
     */
    public void sendPrivateMessage(long usernameHash, byte[] message) {
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(170);
		s.addLong(usernameHash);
		s.addBytes(message);
		player.getSession().write(s.toPacket());
    }

    public void sendRemoveItem(int slot) {
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(191);
		s.addByte((byte) slot);
		player.getSession().write(s.toPacket());
    }

    /**
     * Tells the client to save a screenshot
     */
    public void sendScreenshot() {
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(181);
		player.getSession().write(s.toPacket());
    }

    public void sendServerInfo() {
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(110);
		s.addLong(Config.START_TIME);
		s.addBytes(Config.SERVER_LOCATION.getBytes());
		player.getSession().write(s.toPacket());
    }

    /**
     * Sends a sound effect
     */
    public void sendSound(String soundName) {
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(11);
		s.addBytes(soundName.getBytes());
		player.getSession().write(s.toPacket());
    }

    /**
     * Updates just one stat
     */
    public void sendStat(int stat) {
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(208);
		s.addByte((byte) stat);
		s.addByte((byte) player.getCurStat(stat));
		s.addByte((byte) player.getMaxStat(stat));
		s.addInt(player.getExp(stat));
		player.getSession().write(s.toPacket());
    }
	
	/**
	 * Sends the party members
	 */
	public void sendParty() {
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(69);
		s.addByte((byte) 1); // refresh
		if (!player.inParty()) {
			s.addByte((byte) 0);
		} else {
			if (player.getParty() == null)
				return;
			s.addByte((byte) player.getParty().getSize());
			for (Player p : player.getParty().members) {
				String name = p.getUsername();
				/*if (p.flag != null && !p.flag.equals("--")
						&& p.flag.length() == 2)
					name = "#f" + p.flag + "#" + name;*/
				s.addByte((byte) name.length());
				s.addBytes(name.getBytes());
				s.addInt(p.getIndex());
				s.addByte((byte) p.getCurStat(3));
				s.addByte((byte) p.getMaxStat(3));
				s.addByte((byte) p.getCombatLevel());
				s.addByte((byte) (p.isPartyLeader() ? 1 : 0));
			}
		}
		player.getSession().write(s.toPacket());
	}

	/**
	 * Sends all party member's HP
	 */
	public void sendPartyHP(Player p) {
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(69);
		s.addByte((byte) 2); // hp
		s.addInt(p.getIndex());
		s.addByte((byte) p.getCurStat(3));
		s.addByte((byte) p.getMaxStat(3));
		player.getSession().write(s.toPacket());
	}

    /**
     * Updates the users stats
     */
    public void sendStats() {
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(180);
		for (int lvl : player.getCurStats())
		    s.addByte((byte) lvl);
		for (int lvl : player.getMaxStats())
		    s.addByte((byte) lvl);
		for (int exp : player.getExps())
		    s.addInt(exp);
		player.getSession().write(s.toPacket());
    }

    public void sendTeleBubble(int x, int y, boolean grab) {
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(23);
		s.addByte((byte) (grab ? 1 : 0));
		s.addByte((byte) (x - player.getX()));
		s.addByte((byte) (y - player.getY()));
		player.getSession().write(s.toPacket());
    }

    public void sendTradeAccept() {
		Player with = player.getWishToTrade();
		if (with == null) { // This shouldn't happen
		    return;
		}
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(251);
		s.addLong(with.getUsernameHash());
		s.addByte((byte) with.getTradeOffer().size());
		for (InvItem item : with.getTradeOffer()) {
		    s.addShort(item.getID());
		    s.addInt(item.getAmount());
		}
		s.addByte((byte) player.getTradeOffer().size());
		for (InvItem item : player.getTradeOffer()) {
		    s.addShort(item.getID());
		    s.addInt(item.getAmount());
		}
		player.getSession().write(s.toPacket());
    }

    public void sendTradeAcceptUpdate() {
		Player with = player.getWishToTrade();
		if (with == null) { // This shouldn't happen
		    return;
		}
		RSCPacketBuilder s1 = new RSCPacketBuilder();
		s1.setID(18);
		s1.addByte((byte) (player.isTradeOfferAccepted() ? 1 : 0));
		player.getSession().write(s1.toPacket());
	
		RSCPacketBuilder s2 = new RSCPacketBuilder();
		s2.setID(92);
		s2.addByte((byte) (with.isTradeOfferAccepted() ? 1 : 0));
		player.getSession().write(s2.toPacket());
    }

    public void sendTradeItems() {
		Player with = player.getWishToTrade();
		if (with == null) { // This shouldn't happen
		    return;
		}
		ArrayList<InvItem> items = with.getTradeOffer();
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(250);
		s.addByte((byte) items.size());
		for (InvItem item : items) {
		    s.addShort(item.getID());
		    s.addInt(item.getAmount());
		}
		player.getSession().write(s.toPacket());
    }

    public void sendTradeWindowClose() {
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(187);
		player.getSession().write(s.toPacket());
    }

    public void sendTradeWindowOpen() {
		Player with = player.getWishToTrade();
		if (with == null) { // This shouldn't happen
		    return;
		}
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(4);
		s.addShort(with.getIndex());
		player.getSession().write(s.toPacket());
    }

    public void sendUpdateItem(int slot) {
		InvItem item = player.getInventory().get(slot);
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(228);
		s.addByte((byte) slot);
		s.addShort(item.getID() + (item.isWielded() ? 32768 : 0));
		if (item.getDef().isStackable()) {
		    s.addInt(item.getAmount());
		}
		player.getSession().write(s.toPacket());
    }

    public void sendWakeUp(boolean success) {
		if(success) {
		    player.setFatigue(100);
		    player.getActionSender().sendFatigue();
		    player.getActionSender().sendMessage("You wake up fully recharged.");
		}
		player.isSleeping = false;
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(224);
		player.getSession().write(s.toPacket());
    }
    /**
     * Sent when the user changes coords incase they moved up/down a level
     */
    public void sendWorldInfo() {
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(131);
		s.addShort(player.getIndex());
		s.addShort(2304);
		s.addShort(1776);
		s.addShort(Formulae.getHeight(player.getLocation()));
		s.addShort(944);
		player.getSession().write(s.toPacket());
    }

    /**
     * Show the bank window
     */
    public void showBank() {
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(93);
		s.addByte((byte) player.getBank().size());
		s.addByte((byte) Bank.MAX_SIZE);
		for (InvItem i : player.getBank().getItems()) {
		    s.addShort(i.getID());
		    s.addInt(i.getAmount());
		}
		player.getSession().write(s.toPacket());
    }

    /**
     * Show the bank window
     */
    public void showShop(Shop shop) {
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(253);
		s.addByte((byte) shop.size());
		s.addByte((byte) (shop.isGeneral() ? 1 : 0));
		s.addByte((byte) shop.getSellModifier());
		s.addByte((byte) shop.getBuyModifier());
		for (InvItem i : shop.getItems()) {
		    s.addShort(i.getID());
		    s.addShort(i.getAmount());
		    s.addInt(Formulae.getPrice(shop.getFirstById(i.getID()), shop, true));
		    s.addInt(Formulae.getPrice(shop.getFirstById(i.getID()), shop, false));
		}
		player.getSession().write(s.toPacket());
    }
    public void showSmithing(int bar) {
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(254);
		s.addInt(bar);
		player.getSession().write(s.toPacket());
    }
    /**
     * Sends a system update message
     */
    public void startShutdown(int seconds) {
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(172);
		s.addShort((int) (((double) seconds / 32D) * 50));
		player.getSession().write(s.toPacket());
    }

    /**
     * Updates the id and amount of an item in the bank
     */
    public void updateBankItem(int slot, int newId, int amount) {
		RSCPacketBuilder s = new RSCPacketBuilder();
		s.setID(139);
		s.addByte((byte) slot);
		s.addShort(newId);
		s.addInt(amount);
		player.getSession().write(s.toPacket());
    }
}
