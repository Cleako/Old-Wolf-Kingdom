package org.lupus_regnum.ls.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.lupus_regnum.ls.Server;
import org.lupus_regnum.ls.util.DataConversions;


public class PlayerSave {
	
    public static final String[] statArray = { "attack", "defense", "strength", "hits", "ranged", "prayer", "magic", "cooking", "woodcut", "fletching", "fishing", "firemaking", "crafting", "smithing", "mining", "herblaw", "agility", "thieving" };

    public static PlayerSave loadPlayer(long user) {
    	PlayerSave save = new PlayerSave(user);
    	
    	ResultSet result;

		try {
		    result = Server.db.getQuery("SELECT r.*, u.username AS owner_username, u.userid, group_id as gid, u.sub_expires as subexp FROM `rsca2_players` AS r INNER JOIN `user` AS u ON u.userid=r.owner WHERE `user`='" + save.getUser() + "'");
		    if (!result.next()) {
		    	return save;
		    }
		    int sowner = result.getInt("owner");
		    
	    	save.setOwner(sowner, result.getInt("gid"), result.getLong("subexp"));
		    
	    	save.setMuted(result.getLong("muted"));
		    
		    save.setLogin(result.getLong("login_date"), DataConversions.IPToLong(result.getString("login_ip")));
		    
		    save.setLocation(result.getInt("x"), result.getInt("y"));
	
		    save.setFatigue(result.getInt("fatigue"));
		    
		    save.setCombatStyle((byte) result.getInt("combatstyle"));
	
		    save.setPrivacy(result.getInt("block_chat") == 1, result.getInt("block_private") == 1, result.getInt("block_trade") == 1, result.getInt("block_duel") == 1);
		   
		    save.setSettings(result.getInt("cameraauto") == 1, result.getInt("onemouse") == 1, result.getInt("soundoff") == 1, result.getInt("showroof") == 1, result.getInt("autoscreenshot") == 1, result.getInt("combatwindow") == 1);
	
		    save.setAppearance((byte) result.getInt("haircolour"), (byte) result.getInt("topcolour"), (byte) result.getInt("trousercolour"), (byte) result.getInt("skincolour"), (byte) result.getInt("headsprite"), (byte) result.getInt("bodysprite"), result.getInt("male") == 1, result.getInt("skulled"));
	
		    result = Server.db.getQuery("SELECT * FROM `rsca2_experience` WHERE `user`='" + save.getUser() + "'");
		    if (!result.next()) {
		    	return save;
		    }
		    for (int i = 0; i < 18; i++) {
		    	save.setExp(i, result.getInt("exp_" + statArray[i]));
		    }
	
		    result = Server.db.getQuery("SELECT * FROM `rsca2_curstats` WHERE `user`='" + save.getUser() + "'");
		    if (!result.next()) {
		    	return save;
		    }
		    for (int i = 0; i < 18; i++) {
		    	save.setLvl(i, result.getInt("cur_" + statArray[i]));
		    }
	
		    result = Server.db.getQuery("SELECT id,amount,wielded FROM `rsca2_invitems` WHERE `user`='" + save.getUser() + "' ORDER BY `slot` ASC");
		    while (result.next()) {
		    	save.addInvItem(result.getInt("id"), result.getInt("amount"), result.getInt("wielded") == 1);
		    }
	
		    result = Server.db.getQuery("SELECT id,amount FROM `rsca2_bank` WHERE `user`='" + save.getUser() + "' ORDER BY `slot` ASC");
		    while (result.next()) {
		    	save.addBankItem(result.getInt("id"), result.getInt("amount"));
		    }
	
		    result = Server.db.getQuery("SELECT friend FROM `rsca2_friends` WHERE `user`='" + save.getUser() + "'");
		    while (result.next()) {
		    	save.addFriend(result.getLong("friend"));
		    }
	
		    result = Server.db.getQuery("SELECT `ignore` FROM `rsca2_ignores` WHERE `user`='" + save.getUser() + "'");
		    while (result.next()) {
		    	save.addIgnore(result.getLong("ignore"));
		    }
		    result = Server.db.getQuery("SELECT * FROM `rsca2_quests` WHERE `user`='" + save.getUser() + "'");
		    while (result.next()) {
		    	save.setQuestStage(result.getInt("id"), result.getInt("stage"));
		    }
		    result = Server.db.getQuery("SELECT * FROM `rsca2_player_cache` WHERE `user`='" + save.getUser() + "'");
		    while (result.next()) {
		    	int identifier = result.getInt("type");
		    	String key = result.getString("key");
		    	if(identifier == 0) {
					save.getCache().put(key, result.getInt("value"));
				}
				if(identifier == 1) {
					save.getCache().put(key, result.getString("value"));
				}
				if(identifier == 2) {
					save.getCache().put(key, result.getBoolean("value"));
				}
				if(identifier == 3) {
					save.getCache().put(key, result.getLong("value"));
				}
		    }
	
		} 
		catch (SQLException e) {
		    Server.error("SQL Exception Loading " + DataConversions.hashToUsername(user) + ": " + e.getMessage());
		}
	return save;
    }
	/**
	 * Cache map
	 */
	private Map<String, Object> cache = new HashMap<String, Object>();

	public Map<String,Object> getCache() {
		return cache;
	}
	
	/**
	 * Quest stages map
	 */
	private Map<Integer, Integer> questStages = new HashMap<Integer, Integer>();
	/**
	 * Gets a quest stage
	 * @param q
	 * @return 0 if not started, -1 if finished, else quest stage
	 */
	public int getQuestStage(int questID) {
		if(questStages.containsKey(questID)) {
			return questStages.get(questID);
		}
		return 0;
	}
	/**
	 * Updates a quests stage
	 * @param q quest to update
	 * @param stage new stage value
	 */
	public void setQuestStage(int questID, int stage) {
		questStages.put(questID, stage);
	}
	/**
	 * @return the quest IDs
	 */
	public Set<Integer> getQuestIDs() {
		return questStages.keySet();
	}
	
    private long muted;
    private ArrayList<BankItem> bankItems = new ArrayList<BankItem>();
    private boolean blockChat, blockPrivate, blockTrade, blockDuel;
    private boolean cameraAuto, oneMouse, soundOff, showRoof, autoScreenshot, combatWindow;
    private int combat, skillTotal;
    private byte combatStyle;
    private long[] exp = new long[18];
    private int fatigue;
    private ArrayList<Long> friendList = new ArrayList<Long>();
    private byte hairColour, topColour, trouserColour, skinColour, headSprite, bodySprite;
    private ArrayList<Long> ignoreList = new ArrayList<Long>();
    private ArrayList<InvItem> invItems = new ArrayList<InvItem>();
    private long lastUpdate = 0;
    private long loginDate, loginIP;
    private int[] lvl = new int[18];
    private boolean male;
    private int owner, group;
    private long skulled;
    private long subExpires;
    private long user;

    private int x, y;

    private PlayerSave(long user) {
    	this.user = user;
    }

    public void addBankItem(int id, int amount) {
    	bankItems.add(new BankItem(id, amount));
    }

    public void addFriend(long friend) {
    	friendList.add(friend);
    }

    public void addIgnore(long friend) {
    	ignoreList.add(friend);
    }

    public void addInvItem(int id, int amount, boolean wielded) {
    	invItems.add(new InvItem(id, amount, wielded));
    }

    public boolean autoScreenshot() {
    	return autoScreenshot;
    }

    public boolean blockChat() {
    	return blockChat;
    }

    public boolean blockDuel() {
    	return blockDuel;
    }

    public boolean blockPrivate() {
    	return blockPrivate;
    }

    public boolean blockTrade() {
    	return blockTrade;
    }

    public boolean cameraAuto() {
    	return cameraAuto;
    }

    public void clearBankItems() {
    	bankItems.clear();
    }

    public void clearInvItems() {
    	invItems.clear();
    }


    public boolean combatWindow() {
    	return combatWindow;
    }

    public int getBankCount() {
    	return bankItems.size();
    }

    public BankItem getBankItem(int i) {
    	return bankItems.get(i);
    }

    public int getBodySprite() {
    	return bodySprite;
    }

    public byte getCombatStyle() {
    	return combatStyle;
    }

    public long getExp(int i) {
    	return exp[i];
    }

    public int getFatigue() {
    	return fatigue;
    }

    public long getFriend(int i) {
    	return friendList.get(i);
    }

    public int getFriendCount() {
    	return friendList.size();
    }

    public int getGroup() {
    	return group;
    }

    public int getHairColour() {
    	return hairColour;
    }

    public int getHeadSprite() {
    	return headSprite;
    }

    public long getIgnore(int i) {
    	return ignoreList.get(i);
    }

    public int getIgnoreCount() {
    	return ignoreList.size();
    }

    public int getInvCount() {
    	return invItems.size();
    }

    public InvItem getInvItem(int i) {
    	return invItems.get(i);
    }

    public long getLastIP() {
    	return loginIP;
    }

    public long getLastLogin() {
    	return loginDate;
    }

    public long getLastUpdate() {
    	return lastUpdate;
    }

    public int getOwner() {
    	return owner;
    }

    public int getSkinColour() {
    	return skinColour;
    }

    public long getSkullTime() {
    	return skulled;
    }

    public int getStat(int i) {
    	return lvl[i];
    }

    public long getSubscriptionExpires() {
    	return subExpires;
    }

    public int getTopColour() {
    	return topColour;
    }

    public int getTrouserColour() {
    	return trouserColour;
    }

    public long getUser() {
    	return user;
    }

    public String getUsername() {
    	return DataConversions.hashToUsername(user);
    }

    public int getX() {
    	return x;
    }

    public int getY() {
    	return y;
    }

    public boolean isMale() {
    	return male;
    }

    public boolean oneMouse() {
    	return oneMouse;
    }

    public void removeFriend(long friend) {
    	friendList.remove(friend);
    }

    public void removeIgnore(long friend) {
    	ignoreList.remove(friend);
    }

    public boolean save() {
		try {
		    String query;
	
		    Server.db.updateQuery("DELETE FROM `rsca2_bank` WHERE `user`='" + user + "'");
		    if (bankItems.size() > 0) {
			query = "INSERT INTO `rsca2_bank`(`user`, `id`, `amount`, `slot`) VALUES";
			int slot = 0;
			for (BankItem item : bankItems) {
			    query += "('" + user + "', '" + item.getID() + "', '" + item.getAmount() + "', '" + (slot++) + "'),";
			}
			Server.db.updateQuery(query.substring(0, query.length() - 1));
		    }
	
		    Server.db.updateQuery("DELETE FROM `rsca2_invitems` WHERE `user`='" + user + "'");
	
		    ResultSet result = Server.db.getQuery("Select 1 FROM `rsca2_players` WHERE `user`='" + user + "' AND `owner`='" + owner + "'");
		    if (!result.next())
			return false;
	
		    Server.db.updateQuery("UPDATE `rsca2_players` SET `combat`=" + combat + ", skill_total=" + skillTotal + ", `x`=" + x + ", `y`='" + y + "', `fatigue`='" + fatigue + "', `haircolour`=" + hairColour + ", `topcolour`=" + topColour + ", `trousercolour`=" + trouserColour + ", `skincolour`=" + skinColour + ", `headsprite`=" + headSprite + ", `bodysprite`=" + bodySprite + ", `male`=" + (male ? 1 : 0) + ", `skulled`=" + skulled + ", `combatstyle`=" + combatStyle + " WHERE `user`='" + user + "'");
	
		    query = "UPDATE `rsca2_experience` SET ";
		    for (int i = 0; i < 18; i++)
			query += "`exp_" + statArray[i] + "`=" + exp[i] + ",";
	
		    Server.db.updateQuery(query.substring(0, query.length() - 1) + " WHERE `user`='" + user + "'");
	
		    query = "UPDATE `rsca2_curstats` SET ";
		    for (int i = 0; i < 18; i++)
			query += "`cur_" + statArray[i] + "`=" + lvl[i] + ",";
	
		    Server.db.updateQuery(query.substring(0, query.length() - 1) + " WHERE `user`='" + user + "'");
	
		    if (invItems.size() > 0) {
				query = "INSERT INTO `rsca2_invitems`(`user`, `id`, `amount`, `wielded`, `slot`) VALUES";
				int slot = 0;
				for (InvItem item : invItems)
				    query += "('" + user + "', '" + item.getID() + "', '" + item.getAmount() + "', '" + (item.isWielded() ? 1 : 0) + "', '" + (slot++) + "'),";
		
				Server.db.updateQuery(query.substring(0, query.length() - 1));
		    }
		    
		    if(questStages.size() > 0) {
		    	Server.db.updateQuery("DELETE FROM `rsca2_quests` WHERE `user`='" + user + "'");
		    	query = "INSERT INTO `rsca2_quests` (`user`, `id`, `stage`) VALUES";
			    java.util.Set<Integer> keys = questStages.keySet();
			    for (int id : keys) {
			    	query += "('" + user + "', '" + id + "', '" + questStages.get(id) + "'),";
			    }
			    Server.db.updateQuery(query.substring(0, query.length() - 1));
		    }
	
		    Server.db.updateQuery("DELETE FROM `rsca2_player_cache` WHERE `user`='" + user + "'");
		    if(cache.size() > 0) {
			    query = "INSERT INTO `rsca2_player_cache` (`user`, `type`, `key`, `value`) VALUES";
			    for (String key : cache.keySet()) {
			    	Object o = cache.get(key);
					if(o instanceof Integer) {
						query += "('" + user + "', '0', '" + key + "', '" + cache.get(key) + "'),";
				    }
					if(o instanceof String) {
						query += "('" + user + "', '1', '" + key + "', '" + cache.get(key) + "'),";
					}
					if(o instanceof Boolean) {
						query += "('" + user + "', '2', '" + key + "', '" + ((Boolean) cache.get(key) ? 1 : 0) + "'),";
					}
					if(o instanceof Long) {
						query += "('" + user + "', '3', '" + key + "', '" + cache.get(key) + "'),";
					}
			    }
			    Server.db.updateQuery(query.substring(0, query.length() - 1));
		    }
		    return true;
		} 
		catch (SQLException e) {
		    Server.error(e);
		    return false;
		}
    }

    public void setAppearance(byte hairColour, byte topColour, byte trouserColour, byte skinColour, byte headSprite, byte bodySprite, boolean male, long skulled) {
		this.hairColour = hairColour;
		this.topColour = topColour;
		this.trouserColour = trouserColour;
		this.skinColour = skinColour;
		this.headSprite = headSprite;
		this.bodySprite = bodySprite;
		this.male = male;
		this.skulled = skulled;
    }

    public void setCombatStyle(byte combatStyle) {
    	this.combatStyle = combatStyle;
    }

    public void setExp(int stat, long exp) {
    	this.exp[stat] = exp;
    }

    public void setFatigue(int fatigue) {
    	this.fatigue = fatigue;
    }

    public void setGameSetting(int idx, boolean on) {
		switch (idx) {
			case 0:
			    cameraAuto = on;
			    break;
			case 2:
			    oneMouse = on;
			    break;
			case 3:
			    soundOff = on;
			    break;
			case 4:
			    showRoof = on;
			    break;
			case 5:
			    autoScreenshot = on;
			    break;
			case 6:
			    combatWindow = on;
			    break;
		}
    }

    public void setLastUpdate(long lastUpdate) {
    	this.lastUpdate = lastUpdate;
    }

    public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
    }

    public void setLogin(long loginDate, long loginIP) {
		this.loginDate = loginDate;
		this.loginIP = loginIP;
    }

    public void setLvl(int stat, int lvl) {
    	this.lvl[stat] = lvl;
    }

    public void setOwner(int owner) {
    	this.owner = owner;
    }

    public void setOwner(int owner, int group, long subExpires) {
		this.owner = owner;
		this.group = group;
		this.subExpires = subExpires;
    }

    public void setPrivacy(boolean blockChat, boolean blockPrivate, boolean blockTrade, boolean blockDuel) {
		this.blockChat = blockChat;
		this.blockPrivate = blockPrivate;
		this.blockTrade = blockTrade;
		this.blockDuel = blockDuel;
    }

    public void setPrivacySetting(int idx, boolean on) {
		switch (idx) {
			case 0:
			    blockChat = on;
			    break;
			case 1:
			    blockPrivate = on;
			    break;
			case 2:
			    blockTrade = on;
			    break;
			case 3:
			    blockDuel = on;
			    break;
			}
    }


    public void setSettings(boolean cameraAuto, boolean oneMouse, boolean soundOff, boolean showRoof, boolean autoScreenshot, boolean combatWindow) {
		this.cameraAuto = cameraAuto;
		this.oneMouse = oneMouse;
		this.soundOff = soundOff;
		this.showRoof = showRoof;
		this.autoScreenshot = autoScreenshot;
		this.combatWindow = combatWindow;
    }

    public void setStat(int stat, long exp, int lvl) {
		this.exp[stat] = exp;
		this.lvl[stat] = lvl;
    }

    public void setTotals(int combat, int skillTotal) {
		this.combat = combat;
		this.skillTotal = skillTotal;
    }

    public boolean showRoof() {
		return showRoof;
    }

    public boolean soundOff() {
    	return soundOff;
    }

	public void setMuted(long muted) {
		this.muted = muted;
	}

	public long getMuted() {
		return muted;
	}
}
