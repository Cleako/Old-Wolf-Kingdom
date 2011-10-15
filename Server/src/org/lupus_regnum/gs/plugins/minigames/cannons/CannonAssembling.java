package org.lupus_regnum.gs.plugins.minigames.cannons;

/**
 * Imports
 */
import org.lupus_regnum.gs.event.ShortEvent;
import org.lupus_regnum.gs.phandler.client.InvActionHandler;
import org.lupus_regnum.gs.model.GameObject;
import org.lupus_regnum.gs.model.InvItem;
import org.lupus_regnum.gs.model.Player;
import org.lupus_regnum.gs.model.World;

public class CannonAssembling {
   /**
    * Cannon Assembling Class
    * @author Yong Min
    */
   public static final World world = World.getWorld();
   
   static int[] cannonItemID = {
      1032, // Cannon Base
      1033, // Cannon Stand
      1034, // Cannon Barrels
      1035 // Cannon Furnace
   };
   
   static int[] cannonObjectID = {
      946, // Cannon Base
      947, // Cannon Stand
      948, // Cannon Barrels
      943 // Fully Assembled Cannon
   };

   public static void setDownBase(final Player player, final InvItem item) {
      if(item.getDef().getCommand().equalsIgnoreCase("set down")) {
         if(world.getTile(player.getLocation()).hasGameObject()) {
            player.getActionSender().sendMessage("You can't set up a cannon here. Please move to a new area!");
            return;
         }
         player.resetPath();
         player.setBusy(true);
         player.getActionSender().sendMessage("You begin setting up your cannon..");
         world.getDelayedEventHandler().add(new ShortEvent(player) {
            public void action() {
               player.getInventory().remove(cannonItemID[0], 1);
               player.getActionSender().sendInventory();
               GameObject cannonBase = new GameObject(player.getLocation(), cannonObjectID[0], 0, 0, player.getUsername());
               world.registerGameObject(cannonBase);
               player.setBusy(false);
            }
         });
      }
   }
   
   public static void attachCannonStand(final Player player, final InvItem item, final GameObject object) {
      player.setBusy(true);
      player.getActionSender().sendMessage("You add the cannon stand to the base..");
      world.getDelayedEventHandler().add(new ShortEvent(player) {
         public void action() {
            InvActionHandler.showBubble(player, item);
            player.getInventory().remove(cannonItemID[1], 1);
            player.getActionSender().sendInventory();
            world.unregisterGameObject(object);
            GameObject cannonStand = new GameObject(object.getLocation(), cannonObjectID[1], 0, 0, player.getUsername());
            world.registerGameObject(cannonStand);
            player.setBusy(false);
         }
      });
   }
   
   public static void attachCannonBarrels(final Player player, final InvItem item, final GameObject object) {
      player.setBusy(true);
      player.getActionSender().sendMessage("You add the cannon barrels..");
      world.getDelayedEventHandler().add(new ShortEvent(player) {
         public void action() {
            InvActionHandler.showBubble(player, item);
            player.getInventory().remove(cannonItemID[2], 1);
            player.getActionSender().sendInventory();
            world.unregisterGameObject(object);
            GameObject cannonBarrels = new GameObject(object.getLocation(), cannonObjectID[2], 0, 0, player.getUsername());
            world.registerGameObject(cannonBarrels);
            player.setBusy(false);
         }
      });
   }
   
   public static void attachCannonFurnace(final Player player, final InvItem item, final GameObject object) {
      player.setBusy(true);
      player.getActionSender().sendMessage("You add the cannon furnace..");
      world.getDelayedEventHandler().add(new ShortEvent(player) {
         public void action() {
            InvActionHandler.showBubble(player, item);
            player.getInventory().remove(cannonItemID[3], 1);
            player.getActionSender().sendInventory();
            world.unregisterGameObject(object);
            GameObject cannonFurnace = new GameObject(object.getLocation(), cannonObjectID[3], 0, 0, player.getUsername());
            world.registerGameObject(cannonFurnace);
            player.setBusy(false);
         }
      });
   }
}