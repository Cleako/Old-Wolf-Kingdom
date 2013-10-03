package org.lupus_regnum.gs.model;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Properties;
import java.util.TreeMap;
import java.util.LinkedList;
import java.util.List;

import org.lupus_regnum.gs.Server;
import org.lupus_regnum.gs.core.ClientUpdater;
import org.lupus_regnum.gs.core.DelayedEventHandler;
import org.lupus_regnum.gs.db.DBConnection;
import org.lupus_regnum.gs.event.DelayedEvent;
import org.lupus_regnum.gs.event.SingleEvent;
import org.lupus_regnum.gs.external.GameObjectLoc;
import org.lupus_regnum.gs.external.NPCLoc;
import org.lupus_regnum.gs.io.WorldLoader;
import org.lupus_regnum.gs.model.snapshot.Snapshot;
import org.lupus_regnum.gs.model.Item;
import org.lupus_regnum.gs.plugins.QuestInterface;
import org.lupus_regnum.gs.states.CombatState;
import org.lupus_regnum.gs.util.EntityList;
import org.lupus_regnum.gs.util.Logger;
import org.lupus_regnum.gs.tools.DataConversions;



public final class World {

	/**
	 * Holds parties in an array 
	 */
	public HashMap<Integer, Party> parties = new HashMap<Integer, Party>();
	public Party getParty(int id) {
		return parties.get(id);
	}
	public static int PARTY_COUNT = 1;
	/**
	 * Holds quests in an array 
	 */
	private List<QuestInterface> quests = new LinkedList<QuestInterface>();
	/**
	 * Registers a quest with the world
	 * @param quest
	 */
	public void registerQuest(QuestInterface quest) {
		if(quest.getQuestName() == null) {
			throw new IllegalArgumentException("Quest name cannot be null");
		}
		else if(quest.getQuestName().length() > 40) {
			throw new IllegalArgumentException("Quest name cannot be longer then 40 characters");
		}
		for(QuestInterface q : quests) {
			if(q.getQuestId() == quest.getQuestId()) {
				throw new IllegalArgumentException("Quest ID must be unique");
			}
		}
		quests.add(quest);
	}
	public List<QuestInterface> getQuests() {
		return quests;
	}
	/**
	 * Double ended queue to store snapshots into
	 */
	private Deque<Snapshot> snapshots = new LinkedList<Snapshot>();

	/**
	 * Returns double-ended queue for snapshots.
	 */
	public synchronized Deque<Snapshot> getSnapshots() {
		return snapshots;
	}
	/**
	 * Add entry to snapshots
	 */
	public synchronized void addEntryToSnapshots(Snapshot snapshot) {
		snapshots.offerFirst(snapshot);
	}
	public void sendWorldMessage(String msg) {
		for (Player p : getPlayers()) {
			p.getActionSender().sendMessage(msg);
		}
	}
	public void sendWorldAnnouncement(String msg) {
		for (Player p : getPlayers()) {
			p.getActionSender().sendMessage("% " + msg);
		}
	}
	public void sendMessage(Player p, String msg) {
		p.getActionSender().sendMessage(msg);
	}
	/**
	 * The maximum height of the map (944 squares per level)
	 */
	public static final int MAX_HEIGHT = 3776;
	/**
	 * The maximum width of the map
	 */
	public static final int MAX_WIDTH = 944;
	/**
	 * World instance
	 */
	private static World worldInstance;

	public WorldLoader wl;
	/**
	 * Database connection
	 */
	private DBConnection db = new DBConnection();
	
	public DBConnection getDB() {
		return db;
	}
	
	/**
	 * returns the only instance of this world, if there is not already one,
	 * makes it and loads everything
	 */
	public static synchronized World getWorld() {
		if (worldInstance == null) {
			worldInstance = new World();
			try {
				worldInstance.wl = new WorldLoader();
				worldInstance.wl.loadWorld(worldInstance);
				worldInstance.getDB().loadObjects(worldInstance);
				} 
			catch (Exception e) {
				Logger.error(e);
			}
		}
		return worldInstance;
	}
	/**
	 * The client updater instance
	 */
	private ClientUpdater clientUpdater;
	/**
	 * The delayedeventhandler instance
	 */
	private DelayedEventHandler delayedEventHandler;
	/**
	 * A list of all npcs on the server
	 */
	private EntityList<Npc> npcs = new EntityList<Npc>(4000);
	/**
	 * A list of all players on the server
	 */
	private EntityList<Player> players = new EntityList<Player>(2000);
	/**
	 * The server instance
	 */
	private Server server;
	/**
	 * A list of all shops on the server
	 */
	private List<Shop> shops = new ArrayList<Shop>();
	/**
	 * The tiles the map is made up of
	 */
	public ActiveTile[][] tiles = new ActiveTile[MAX_WIDTH][MAX_HEIGHT];
	/**
	 * Data about the tiles, are they walkable etc
	 */
	private TileValue[][] tileType = new TileValue[MAX_WIDTH][MAX_HEIGHT];

	/**
	 * Counts how many npcs are currently here
	 */
	public int countNpcs() {
		return npcs.size();
	}

	/**
	 * Counts how many players are currently connected
	 */
	public int countPlayers() {
		return players.size();
	}

	/**
	 * Adds a DelayedEvent that will remove a GameObject
	 */
	public void delayedRemoveObject(final GameObject object, final int delay) {
		delayedEventHandler.add(new SingleEvent(null, delay) {

			public void action() {
				ActiveTile tile = getTile(object.getLocation());
				if (tile.hasGameObject() && tile.getGameObject().equals(object)) {
					unregisterGameObject(object);
				}
			}
		});
	}
	
	public void delayedRemoveItem(final Item item, final int delay) {
		delayedEventHandler.add(new SingleEvent(null, delay) {

			public void action() {
				ActiveTile tile = getTile(item.getLocation());
				if (tile.hasItems() && tile.getItems().equals(item)) {
					unregisterItem(item);
				}
			}
		});
	}

	/**
	 * Adds a DelayedEvent that will spawn a GameObject
	 */
	public void delayedSpawnObject(final GameObjectLoc loc, final int respawnTime) {
		delayedEventHandler.add(new SingleEvent(null, respawnTime) {

			public void action() {
				registerGameObject(new GameObject(loc));
			}
		});
	}

	/**
	 * Gets the ClientUpdater instance
	 */
	public ClientUpdater getClientUpdater() {
		return clientUpdater;
	}

	/**
	 * Gets the DelayedEventHandler instance
	 */
	public DelayedEventHandler getDelayedEventHandler() {
		return delayedEventHandler;
	}

	/**
	 * Gets an Npc by their server index
	 */
	public Npc getNpc(int idx) {
		return npcs.get(idx);
	}

	/**
	 * Gets an npc by their coords and id]
	 */
	public Npc getNpc(int id, int minX, int maxX, int minY, int maxY) {
		for (Npc npc : npcs) {
			if (npc.getID() == id && npc.getX() >= minX && npc.getX() <= maxX && npc.getY() >= minY && npc.getY() <= maxY) {
				return npc;
			}
		}
		return null;
	}

	/**
	 * Gets an npc by their coords and id]
	 */
	public Npc getNpc(int id, int minX, int maxX, int minY, int maxY, boolean notNull) {
		for (Npc npc : npcs) {
			if (npc.getID() == id && npc.getX() >= minX && npc.getX() <= maxX && npc.getY() >= minY && npc.getY() <= maxY) {
				if (!npc.inCombat()) {
					return npc;
				}
			}
		}
		return null;
	}

	/**
	 * Gets the list of npcs on the server
	 */
	public synchronized EntityList<Npc> getNpcs() {
		return npcs;
	}

	/**
	 * Gets a Player by their server index
	 */
	public Player getPlayer(int idx) {
		return players.get(idx);
	}
	
	public Player getPlayer1(String username) {
		return getPlayer(DataConversions.usernameToHash(username));
	}
	
	/**
	 * Gets the list of parties on the server
	 */
	public void setParties(HashMap<Integer, Party> parties) {
		this.parties = parties;
	}

	public HashMap<Integer, Party> getParties() {
		return parties;
	}

	/**
	 * Gets a player by their username hash
	 */
	public Player getPlayer(long usernameHash) {
		for (Player p : players) {
			if (p.getUsernameHash() == usernameHash) {
				return p;
			}
		}
		return null;
	}

	/**
	 * Gets the list of players on the server
	 */
	public synchronized EntityList<Player> getPlayers() {
		return players;
	}

	/**
	 * Gets the server instance
	 */
	public Server getServer() {
		return server;
	}

	/**
	 * Gets a list of all shops
	 */
	public Shop getShop(Point location) {
		for (Shop shop : shops) {
			if (shop.withinShop(location)) {
				return shop;
			}
		}
		return null;
	}

	public List<Shop> getShops() {
		return shops;
	}

	/**
	 * Gets the active tile at point x, y
	 */
	public ActiveTile getTile(int x, int y) {
		if (!withinWorld(x, y)) {
			return null;
		}
		ActiveTile t = tiles[x][y];
		if (t == null) {
			t = new ActiveTile(x, y);
			tiles[x][y] = t;
		}
		return t;
	}

	/**
	 * Gets the tile at a point
	 */
	public ActiveTile getTile(Point p) {
		return getTile(p.getX(), p.getY());
	}

	/**
	 * Gets the tile value as point x, y
	 */
	public TileValue getTileValue(int x, int y) {
		if (!withinWorld(x, y)) {
			return null;
		}
		TileValue t = tileType[x][y];
		if (t == null) {
			t = new TileValue();
			tileType[x][y] = t;
		}
		return t;
	}

	/**
	 * Checks if the given npc is on the server
	 */
	public boolean hasNpc(Npc n) {
		return npcs.contains(n);
	}

	/**
	 * Checks if the given player is on the server
	 */
	public boolean hasPlayer(Player p) {
		return players.contains(p);
	}

	/**
	 * Checks if the given player is logged in
	 */
	public boolean isLoggedIn(long usernameHash) {
		Player friend = getPlayer(usernameHash);
		if (friend != null) {
			return friend.loggedIn();
		}
		return false;
	}

	/**
	 * Updates the map to include a new door
	 */
	public void registerDoor(GameObject o) {
		if (o.getDoorDef().getDoorType() != 1) {
			return;
		}
		int dir = o.getDirection();
		int x = o.getX(), y = o.getY();
		if (dir == 0) {
			getTileValue(x, y).objectValue |= 1;
			getTileValue(x, y - 1).objectValue |= 4;
		} else if (dir == 1) {
			getTileValue(x, y).objectValue |= 2;
			getTileValue(x - 1, y).objectValue |= 8;
		} else if (dir == 2) {
			getTileValue(x, y).objectValue |= 0x10;
		} else if (dir == 3) {
			getTileValue(x, y).objectValue |= 0x20;
		}
	}

	/**
	 * Registers an object with the world
	 */
	public void registerGameObject(GameObject o) {
		switch (o.getType()) {
			case 0:
				registerObject(o);
				break;
			case 1:
				registerDoor(o);
				break;
			}
	}
	/**
	 * Registers an item to be removed after 3 minutes
	 */
	public void registerItem(final Item i) {
		try {
			if (i.getLoc() == null) {
				delayedEventHandler.add(new DelayedEvent(null, 180000) {
	
					public void run() {
						ActiveTile tile = getTile(i.getLocation());
						if (tile.hasItem(i)) {
							unregisterItem(i);
						}
						matchRunning = false;
					}
				});
			}
		}
		catch(Exception e) {
			i.remove();
			e.printStackTrace();
		}
	}

	/**
	 * Registers an npc with the world
	 */
	public void registerNpc(Npc n) {
		NPCLoc npc = n.getLoc();
		if (npc.startX < npc.minX || npc.startX > npc.maxX || npc.startY < npc.minY || npc.startY > npc.maxY || (getTileValue(npc.startX, npc.startY).mapValue & 64) != 0) {
			//Logger.println("Fucked Npc: <id>" + npc.id + "</id><startX>" + npc.startX + "</startX><startY>" + npc.startY + "</startY>");
		}
		npcs.add(n);
	}

	/**
	 * Updates the map to include a new object
	 */
	public void registerObject(GameObject o) {
		if (o.getGameObjectDef().getType() != 1 && o.getGameObjectDef().getType() != 2) {
			return;
		}
		int dir = o.getDirection();
		int width, height;
		if (dir == 0 || dir == 4) {
			width = o.getGameObjectDef().getWidth();
			height = o.getGameObjectDef().getHeight();
		} else {
			height = o.getGameObjectDef().getWidth();
			width = o.getGameObjectDef().getHeight();
		}
		for (int x = o.getX(); x < o.getX() + width; x++) {
			for (int y = o.getY(); y < o.getY() + height; y++) {
				if (o.getGameObjectDef().getType() == 1) {
					getTileValue(x, y).objectValue |= 0x40;
				} else if (dir == 0) {
					getTileValue(x, y).objectValue |= 2;
					getTileValue(x - 1, y).objectValue |= 8;
				} else if (dir == 2) {
					getTileValue(x, y).objectValue |= 4;
					getTileValue(x, y + 1).objectValue |= 1;
				} else if (dir == 4) {
					getTileValue(x, y).objectValue |= 8;
					getTileValue(x + 1, y).objectValue |= 2;
				} else if (dir == 6) {
					getTileValue(x, y).objectValue |= 1;
					getTileValue(x, y - 1).objectValue |= 4;
				}
			}
		}
		
	}

	/**
	 * Registers a player with the world and informs other players on their
	 * login
	 */
	public void registerPlayer(Player p) {
		p.setInitialized();
		players.add(p);
	}

	/**
	 * Inserts a new shop into the world
	 */
	public void registerShop(final Shop shop) {
		
		shop.setEquilibrium();
		shops.add(shop);
	}
	/**
	 * Sets the ClientUpdater instance
	 */
	public void setClientUpdater(ClientUpdater clientUpdater) {
		this.clientUpdater = clientUpdater;
	}

	/**
	 * Sets the DelayedEventHandler instance
	 */
	public void setDelayedEventHandler(DelayedEventHandler delayedEventHandler) {
		this.delayedEventHandler = delayedEventHandler;
	}

	/**
	 * adds or removes the given entity from the relivant tiles
	 */
	public void setLocation(Entity entity, Point oldPoint, Point newPoint) {
		ActiveTile t;
		if (oldPoint != null) {
			t = getTile(oldPoint);
			t.remove(entity);
		}
		if (newPoint != null) {
			t = getTile(newPoint);
			t.add(entity);
		}
	}

	/**
	 * Sets the instance of the server
	 */
	public void setServer(Server server) {
		this.server = server;
	}

	/**
	 * Removes a door from the map
	 */
	public void unregisterDoor(GameObject o) {
		if (o.getDoorDef().getDoorType() != 1) {
			return;
		}
		int dir = o.getDirection();
		int x = o.getX(), y = o.getY();
		if (dir == 0) {
			getTileValue(x, y).objectValue &= 0xfffe;
			getTileValue(x, y - 1).objectValue &= 65535 - 4;
		} else if (dir == 1) {
			getTileValue(x, y).objectValue &= 0xfffd;
			getTileValue(x - 1, y).objectValue &= 65535 - 8;
		} else if (dir == 2) {
			getTileValue(x, y).objectValue &= 0xffef;
		} else if (dir == 3) {
			getTileValue(x, y).objectValue &= 0xffdf;
		}
	}

	/**
	 * Removes an object from the server
	 */
	public void unregisterGameObject(GameObject o) {
		o.remove();
		setLocation(o, o.getLocation(), null);
		switch (o.getType()) {
		case 0:
			unregisterObject(o);
			break;
		case 1:
			unregisterDoor(o);
			break;
		}
	}

	/**
	 * Removes an item from the server
	 */
	public void unregisterItem(Item i) {
		i.remove();
		setLocation(i, i.getLocation(), null);
	}

	/**
	 * Removes an npc from the server
	 */
	public void unregisterNpc(Npc n) {
		if (hasNpc(n)) {
			npcs.remove(n);
		}
		setLocation(n, n.getLocation(), null);
	}

	/**
	 * Removes an object from the map
	 */
	public void unregisterObject(GameObject o) {
		if (o.getGameObjectDef().getType() != 1 && o.getGameObjectDef().getType() != 2) {
			return;
		}
		int dir = o.getDirection();
		int width, height;
		if (dir == 0 || dir == 4) {
			width = o.getGameObjectDef().getWidth();
			height = o.getGameObjectDef().getHeight();
		} else {
			height = o.getGameObjectDef().getWidth();
			width = o.getGameObjectDef().getHeight();
		}
		for (int x = o.getX(); x < o.getX() + width; x++) {
			for (int y = o.getY(); y < o.getY() + height; y++) {
				if (o.getGameObjectDef().getType() == 1) {
					getTileValue(x, y).objectValue &= 0xffbf;
				} else if (dir == 0) {
					getTileValue(x, y).objectValue &= 0xfffd;
					getTileValue(x - 1, y).objectValue &= 65535 - 8;
				} else if (dir == 2) {
					getTileValue(x, y).objectValue &= 0xfffb;
					getTileValue(x, y + 1).objectValue &= 65535 - 1;
				} else if (dir == 4) {
					getTileValue(x, y).objectValue &= 0xfff7;
					getTileValue(x + 1, y).objectValue &= 65535 - 2;
				} else if (dir == 6) {
					getTileValue(x, y).objectValue &= 0xfffe;
					getTileValue(x, y - 1).objectValue &= 65535 - 4;
				}
			}
		}
	}

	/**
	 * Removes a player from the server and saves their account
	 */
	public void unregisterPlayer(Player p) {
		p.setLoggedIn(false);
		p.resetAll();
		p.save();
		Mob opponent = p.getOpponent();
		if (opponent != null) {
			p.resetCombat(CombatState.ERROR);
			opponent.resetCombat(CombatState.ERROR);
		}
		server.getLoginConnector().getActionSender().playerLogout(p.getUsernameHash());
		delayedEventHandler.removePlayersEvents(p);
		players.remove(p);
		setLocation(p, p.getLocation(), null);
	}

	/**
	 * Are the given coords within the world boundaries
	 */
	public boolean withinWorld(int x, int y) {
		return x >= 0 && x < MAX_WIDTH && y >= 0 && y < MAX_HEIGHT;
	}
	/**
	 * 
	 */
	private boolean doubleXP = false;;
	
	public boolean isDoubleXpWeekend() {
		return doubleXP;
	}
	public void setDoubleXpWeekend(boolean b) {
		this.doubleXP = b;
	}
	/**
	* Finds a specific quest by ID
	* @param q
	* @return
	* @throws IllegalArgumentException when a quest by that ID isn't found
	*/
	public QuestInterface getQuest(int q) throws IllegalArgumentException {
		for(QuestInterface quest : this.getQuests()) {
						if(quest.getQuestId() == q) {
							return quest;
						}
		}
		throw new IllegalArgumentException("No quest found");
	}
	
}
