package org.wolf_kingdom.gs.plugins.minigames.cannons;

/**
 * Imports
 */
import org.wolf_kingdom.gs.model.GameObject;
import org.wolf_kingdom.gs.model.InvItem;
import org.wolf_kingdom.gs.model.Player;
import org.wolf_kingdom.gs.model.World;
import org.wolf_kingdom.gs.plugins.minigames.cannons.CannonFiring;
 
public class CannonPickup {
   /**
    * Cannon Pickup Class
    * @author Yong Min
    */
   public static final World world = World.getWorld();
   
   static int[] cannonItemID = {
      1032, // Cannon Base
      1033, // Cannon Stand
      1034, // Cannon Barrels
      1035 // Cannon Furnace
   };
   
   public static void pickUpBase(Player player, GameObject object) {
      player.getInventory().add(new InvItem(cannonItemID[0], 1));
      player.getActionSender().sendInventory();
      world.unregisterGameObject(object);
   }
   
   public static void pickUpStand(Player player, GameObject object) {
      player.getInventory().add(new InvItem(cannonItemID[0], 1));
      player.getInventory().add(new InvItem(cannonItemID[1], 1));
      player.getActionSender().sendInventory();
      world.unregisterGameObject(object);
   }
   
   public static void pickUpBarrels(Player player, GameObject object) {
      player.getInventory().add(new InvItem(cannonItemID[0], 1));
      player.getInventory().add(new InvItem(cannonItemID[1], 1));
      player.getInventory().add(new InvItem(cannonItemID[2], 1));
      player.getActionSender().sendInventory();
      world.unregisterGameObject(object);
   }
   
   public static void pickUpCannon(Player player, GameObject object) {
      player.getInventory().add(new InvItem(cannonItemID[0], 1));
      player.getInventory().add(new InvItem(cannonItemID[1], 1));
      player.getInventory().add(new InvItem(cannonItemID[2], 1));
      player.getInventory().add(new InvItem(cannonItemID[3], 1));
      player.getActionSender().sendInventory();
      world.unregisterGameObject(object);
   }
}