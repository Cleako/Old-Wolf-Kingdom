package org.lupus_regnum.config;


/**
 * Holds all important, commonly tweaked variables.
 */
public class Constants {
    /**
     * @category GameServer
     */
    public static class GameServer {
	/**
	 * Used throughout strings ingame, this is your Server's name.
	 */
	public static final String SERVER_NAME = "Dragon Annihilators";
	/**
	 * Set by runtime arguments. Is this World a Members/Subscriber (P2P)
	 * world?
	 */
	public static boolean MEMBER_WORLD = true;
	/**
	 * Is this wilderness fully f2p (no p2p weapons, items etc)
	 */
	public static boolean F2P_WILDY = false;
	/**
	 * Our World's Number, Gets set upon launch.
	 */
	public static int WORLD_NUMBER = 0;
	/**
	 * Message of the Day (Seen as you log in)
	 */
	public static String MOTD = "Welcome!";
	/**
	 * Exp Rate multiplier per kill.
	 */
	public static final double EXP_RATE = 1.0;
	/**
	 * Subscribed Exp Rate multiplier per kill.
	 */
	public static final double SUB_EXP_RATE = 2.0;
	/**
	 * Strikes, Bolts & Blast Spells.
	 * 
	 * Remember, 30+ Magic damage gives you +1 damage, so these damages are
	 * -1 the absolute max. Level Requirement, Max Damage
	 */
	public static final int[][] SPELLS = { { 1, 1 }, { 4, 2 }, { 9, 2 }, { 13, 3 }, { 17, 3 }, { 23, 4 }, { 29, 4 }, { 35, 5 }, { 41, 5 }, { 47, 6 }, { 53, 6 }, { 59, 7 }, { 62, 8 }, { 65, 9 }, { 70, 10 }, { 75, 11 } };
	/**k
	 * ID's of all Undead-type of NPC's. (Used for crumble undead & sounds)
	 */
	public static final int[] UNDEAD_NPCS = { 15, 53, 80, 178, 664, 41, 52, 68, 180, 214, 319, 40, 45, 46, 50, 179, 195 };
	/**
	 * ID's of all ARMOR type NPC's. (Used for armor hitting sounds)
	 */
	public static final int[] ARMOR_NPCS = { 66, 102, 189, 277, 322, 401324, 323, 632, 633 };
	/**
	 * Maximum hit for Crumble Undead (Magic) spell. (Against undead)
	 */
	public static final int CRUMBLE_UNDEAD_MAX = 12;
	/**
	 * These NPCs are NPCs that are attackable, but do not run on low health
	 * such as Guards etc.
	 */
	public static final int[] NPCS_THAT_DONT_RETREAT = { 65, 102, 100, 127, 258 };


    }
}
