package org.wolf_kingdom.client.entityhandling;

import java.util.ArrayList;

import org.wolf_kingdom.client.mudclient;
import org.wolf_kingdom.client.util.PersistenceManager;

import defs.DoorDef;
import defs.ElevationDef;
import defs.GameObjectDef;
import defs.ItemDef;
import defs.NPCDef;
import defs.PrayerDef;
import defs.SpellDef;
import defs.TileDef;
import defs.extras.AnimationDef;
import defs.extras.TextureDef;

/**
 * This class handles the loading of entities from the conf files, and provides
 * methods for relaying these entities to the user.
 */
public class EntityHandler {

	private static NPCDef[] npcs;
	private static ItemDef[] items;
	private static TextureDef[] textures;
	private static AnimationDef[] animations;
	private static SpellDef[] spells;
	private static PrayerDef[] prayers;
	private static TileDef[] tiles;
	private static DoorDef[] doors;
	private static ElevationDef[] elevation;
	private static GameObjectDef[] objects;

	private static ArrayList<String> models = new ArrayList<String>();
	private static int invPictureCount = 0;

	public static int getModelCount() {
		return models.size();
	}

	public static String getModelName(int id) {
		if (id < 0 || id >= models.size()) {
			return null;
		}
		return models.get(id);
	}

	public static int invPictureCount() {
		return invPictureCount;
	}

	public static int npcCount() {
		return npcs.length;
	}

	public static NPCDef getNpcDef(int id) {
		if (id < 0 || id >= npcs.length) {
			return null;
		}
		return npcs[id];
	}

	public static int itemCount() {
		return items.length;
	}

	public static ItemDef getItemDef(int id) {
		if (id < 0 || id >= items.length) {
			return null;
		}
		return items[id];
	}

	public static int textureCount() {
		return textures.length;
	}

	public static TextureDef getTextureDef(int id) {
		if (id < 0 || id >= textures.length) {
			return null;
		}
		return textures[id];
	}

	public static int animationCount() {
		return animations.length;
	}

	public static AnimationDef getAnimationDef(int id) {
		if (id < 0 || id >= animations.length) {
			return null;
		}
		return animations[id];
	}

	public static int spellCount() {
		return spells.length;
	}

	public static SpellDef getSpellDef(int id) {
		if (id < 0 || id >= spells.length) {
			return null;
		}
		return spells[id];
	}

	public static int prayerCount() {
		return prayers.length;
	}

	public static PrayerDef getPrayerDef(int id) {
		if (id < 0 || id >= prayers.length) {
			return null;
		}
		return prayers[id];
	}

	public static int tileCount() {
		return tiles.length;
	}

	public static TileDef getTileDef(int id) {
		if (id < 0 || id >= tiles.length) {
			return null;
		}
		return tiles[id];
	}

	public static int doorCount() {
		return doors.length;
	}

	public static DoorDef getDoorDef(int id) {
		if (id < 0 || id >= doors.length) {
			return null;
		}
		return doors[id];
	}

	public static int elevationCount() {
		return elevation.length;
	}

	public static ElevationDef getElevationDef(int id) {
		if (id < 0 || id >= elevation.length) {
			return null;
		}
		return elevation[id];
	}

	public static int objectCount() {
		return objects.length;
	}

	public static GameObjectDef getObjectDef(int id) {
		if (id < 0 || id >= objects.length) {
			return null;
		}
		return objects[id];
	}

	public static void load() {
		npcs = (NPCDef[]) PersistenceManager.load(mudclient
				.loadCachedFile("NPCs.wk"));
		mudclient.getMc().drawDownloadProgress("Loading NPCSs", 3);
		items = (ItemDef[]) PersistenceManager.load(mudclient
				.loadCachedFile("Items.wk"));
		mudclient.getMc().drawDownloadProgress("Loading Items", 5);
		textures = (TextureDef[]) PersistenceManager.load(mudclient
				.loadCachedFile("Textures.wk"));
		mudclient.getMc().drawDownloadProgress("Loading Textures", 7);
		animations = (AnimationDef[]) PersistenceManager.load(mudclient
				.loadCachedFile("Animations.wk"));
		mudclient.getMc().drawDownloadProgress("Loading Animations", 9);
		spells = (SpellDef[]) PersistenceManager.load(mudclient
				.loadCachedFile("Spells.wk"));
		mudclient.getMc().drawDownloadProgress("Loading Spells", 11);
		prayers = (PrayerDef[]) PersistenceManager.load(mudclient
				.loadCachedFile("Prayers.wk"));
		mudclient.getMc().drawDownloadProgress("Loading Prayers", 13);
		tiles = (TileDef[]) PersistenceManager.load(mudclient
				.loadCachedFile("Tiles.wk"));
		mudclient.getMc().drawDownloadProgress("Loading Tiles", 15);
		doors = (DoorDef[]) PersistenceManager.load(mudclient
				.loadCachedFile("Doors.wk"));
		mudclient.getMc().drawDownloadProgress("Loading Doors", 17);
		elevation = (ElevationDef[]) PersistenceManager.load(mudclient
				.loadCachedFile("Elevation.wk"));
		mudclient.getMc().drawDownloadProgress("Loading Elevation", 19);
		objects = (GameObjectDef[]) PersistenceManager.load(mudclient
				.loadCachedFile("Objects.wk"));
		mudclient.getMc().drawDownloadProgress("Loading Objects", 21);

		for (int id = 0; id < items.length; id++) {
			if (items[id].getSprite() + 1 > invPictureCount) {
				invPictureCount = items[id].getSprite() + 1;
			}
		}

		for (int id = 0; id < objects.length; id++) {
			objects[id].modelID = storeModel(objects[id].getObjectModel());
		}
	}

	public static int storeModel(String name) {
		if (name.equalsIgnoreCase("na")) {
			return 0;
		}
		int index = models.indexOf(name);
		if (index < 0) {
			models.add(name);
			return models.size() - 1;
		}
		return index;
	}

}
