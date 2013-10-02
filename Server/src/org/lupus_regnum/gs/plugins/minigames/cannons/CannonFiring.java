package org.lupus_regnum.gs.plugins.minigames.cannons;

/**
 * Imports
 */
 import org.lupus_regnum.config.Formulae;
import org.lupus_regnum.gs.event.DelayedEvent;
import org.lupus_regnum.gs.event.RangeEvent;
import org.lupus_regnum.gs.model.InvItem;
import org.lupus_regnum.gs.model.Npc;
import org.lupus_regnum.gs.model.Player;
import org.lupus_regnum.gs.model.Projectile;
import org.lupus_regnum.gs.model.World;
//import org.lupus_regnum.gs.phandler.skills.declarations.StatDeclarations;
import org.lupus_regnum.gs.tools.DataConversions;

import java.util.ArrayList;
import java.util.List;

public class CannonFiring {
   /**
    * Cannon Firing Event
    * @author Yong Min
    */
   public static final World world = World.getWorld();
   
   static InvItem CANNON_BALL = new InvItem(1041);
   static int CANNON_BALLS = 1041;
   
   public static void checkAmmoAmount(Player player, InvItem item) {
      if(player.getInventory().countId(CANNON_BALLS) < 1) {
         player.getActionSender().sendMessage("You have run out of ammo!");
         return;
      }
      checkNearbyTargets(player, item);
   }
   
   public static void checkNearbyTargets(final Player player, final InvItem item) {
      world.getDelayedEventHandler().add(new DelayedEvent(player, 2000) {
         public void run() {
            owner.setBusy(true);
			int nearbyTargets = 0;
            player.getActionSender().sendMessage("Seeking targets in view...");
            List<Npc> npcsInView = player.viewArea.getNpcsInView();
            
            for(Npc n : npcsInView) {
               if((n.getLocation().inBounds(owner.getX() - 9, owner.getY() - 9, owner.getX() + 9, owner.getY() + 9)) && (n.getDef().isAttackable())) {
                  nearbyTargets++;
                  if(nearbyTargets == 1) {
                     player.setRangeEvent(new RangeEvent(player, n));
                     int cannonBallDamage = DataConversions.random(5, 30);
                     Projectile projectile = new Projectile(player, n, 5);
                     n.setLastDamage(cannonBallDamage);
                     int newHP = n.getHits() - cannonBallDamage;
                     n.setHits(newHP);
                     ArrayList<Player> playersToInform = new ArrayList<Player>();
                     playersToInform.addAll(player.getViewArea().getPlayersSectorA());
                     playersToInform.addAll(player.getViewArea().getPlayersSectorB());
                     playersToInform.addAll(player.getViewArea().getPlayersSectorC());
                     playersToInform.addAll(player.getViewArea().getPlayersSectorD());
                     playersToInform.addAll(n.getViewArea().getPlayersSectorA());
                     playersToInform.addAll(n.getViewArea().getPlayersSectorB());
                     playersToInform.addAll(n.getViewArea().getPlayersSectorC());
                     playersToInform.addAll(n.getViewArea().getPlayersSectorD());
                     for(Player p : playersToInform) {
                        p.informOfProjectile(projectile);
                        p.informOfModifiedHits(n);
                     }
                     player.getInventory().remove(CANNON_BALL);
                     player.getActionSender().sendInventory();
                     if(newHP <= 0) {
                        n.killedBy(player, false);
                        int rangedExp = DataConversions.roundUp(Formulae.combatExperience(n) / 4D);
                        player.incExp(4, rangedExp, true, true, false);
                        player.getActionSender().sendStat(4);
                     }
                     player.getActionSender().sendInventory();
                  }
               }
            }
			if(System.currentTimeMillis() - player.getLastMoved() < 2500) {
	    		player.getActionSender().sendMessage("You must remain by your cannon to feed it additional ammo!");
	    		this.stop();
				owner.setBusy(false);
				}
			if(nearbyTargets < 1) {
               player.getActionSender().sendMessage("The are currently no attackable targets in view!");
               this.stop();
			   owner.setBusy(false);
            }
            if(!player.getInventory().contains(CANNON_BALL)) {
               player.getActionSender().sendMessage("You have run out of ammo!");
               this.stop();
			   owner.setBusy(false);
            }
         }
      });
   }
}