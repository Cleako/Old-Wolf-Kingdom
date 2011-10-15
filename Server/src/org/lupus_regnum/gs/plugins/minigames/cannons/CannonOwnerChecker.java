package org.lupus_regnum.gs.plugins.minigames.cannons;

/**
 * Imports
 */
import org.lupus_regnum.gs.model.GameObject;
import org.lupus_regnum.gs.model.InvItem;
import org.lupus_regnum.gs.model.Player;
 
public class CannonOwnerChecker {
   /**
    * Cannon Ownership Checker
    * @author Yong Min
    */
   //public final Player player;
   
   static int[] cannonObjectID = {
      946, // Cannon Base
      947, // Cannon Stand
      948, // Cannon Barrels
      943 // Fully Assembled Cannon
   };
   
   static int[] cannonItemID = {
      1032, // Cannon Base
      1033, // Cannon Stand
      1034, // Cannon Barrels
      1035 // Cannon Furnace
   };
   
   public static void checkFiringOwnership(Player player, GameObject object, String command, InvItem item) {
      if(!object.getOwner().equals(player.getUsername())) {
         player.getActionSender().sendMessage("This cannon doesn't belong to you. This cannon belongs to @ora@" + object.getOwner());
         return;
      }
      if((object.getOwner().equals(player.getUsername())) && (command.equalsIgnoreCase("fire"))) {
         CannonFiring.checkAmmoAmount(player, item);
      }
   }
   
   public static void checkCannonOwnership(Player player, GameObject object) {
      if(!object.getOwner().equals(player.getUsername())) {
         player.getActionSender().sendMessage("This cannon doesn't belong to you. This cannon belongs to @ora@" + object.getOwner());
         return;
      }
      if(object.getOwner().equals(player.getUsername())) {
         if(object.getID() == cannonObjectID[0]) { // Cannon Base
            CannonPickup.pickUpBase(player, object);
         } else if(object.getID() == cannonObjectID[1]) { // Cannon Stand
            CannonPickup.pickUpStand(player, object);
         } else if(object.getID() == cannonObjectID[2]) { // Cannon Barrels
            CannonPickup.pickUpBarrels(player, object);
         } else if(object.getID() == cannonObjectID[3]) { // Cannon Furnace
            CannonPickup.pickUpCannon(player, object);
         }
      }
   }
   
   public static void checkCannonAttachingOwnership(Player player, InvItem item, GameObject object) {
      if(!object.getOwner().equals(player.getUsername())) {
         player.getActionSender().sendMessage("This cannon doesn't belong to you. This cannon belongs to @ora@" + object.getOwner());
         return;
      }
      if((object.getID() == cannonObjectID[0]) && (item.getID() != cannonItemID[1]) ||
         (object.getID() == cannonObjectID[1]) && (item.getID() != cannonItemID[2]) ||
         (object.getID() == cannonObjectID[2]) && (item.getID() != cannonItemID[3])) {
         player.getActionSender().sendMessage("Nothing interesting happens.");
         return;
      }
      if((object.getID() == cannonObjectID[0]) && (item.getID() == cannonItemID[1])) {
         CannonAssembling.attachCannonStand(player, item, object);
      }
      if((object.getID() == cannonObjectID[1]) && (item.getID() == cannonItemID[2])) {
         CannonAssembling.attachCannonBarrels(player, item, object);
      }
      if((object.getID() == cannonObjectID[2]) && (item.getID() == cannonItemID[3])) {
         CannonAssembling.attachCannonFurnace(player, item, object);
      }
   }
}