package org.lupus_regnum.gs.plugins.minigames.pets;

import org.lupus_regnum.gs.external.EntityHandler;
import org.lupus_regnum.gs.event.ShortEvent;
import org.lupus_regnum.gs.model.InvItem;
import org.lupus_regnum.gs.model.Mob;
import org.lupus_regnum.gs.model.Npc;
import org.lupus_regnum.gs.model.Player;
import org.lupus_regnum.gs.model.World;
import org.lupus_regnum.gs.states.CombatState;
import org.lupus_regnum.gs.states.Action;
import org.lupus_regnum.gs.tools.DataConversions;

public class Pets {
    /**
     * World instance
     */
    public static final World world = World.getWorld();
	
    static int[] emptyPetItemID = {
		1231 // Charged crystal without a pet inside
    };
	static int[] fullPetItemID = {
		1222 // Red crystal with pet inside
    };
    static int[] petNpcID = {
		203 // Baby Blue Dragon
    }; 
	public static void summonPet(final Player player, final InvItem item) {
      if(item.getDef().getCommand().equalsIgnoreCase("inspect")) {
			player.getActionSender().sendMessage("You summon your pet.");
			world.getDelayedEventHandler().add(new ShortEvent(player) {
				public void action() {
					player.getInventory().remove(fullPetItemID[0], 1);
					player.getInventory().add(new InvItem(emptyPetItemID[0], 1));
					player.getActionSender().sendInventory();
					Npc petDragon = new Npc(player.getLocation(), petNpcID[0],  player.getUsername());
					petDragon.shouldRespawn = false;
					//petDragon.setFollowing(player);
					world.registerNpc(petDragon);
				}
			});
		}
	}
	/*public static void returnPet(final Player player, final InvItem item) {
      if(item.getDef().getCommand().equalsIgnoreCase("inspect")) {
			player.getActionSender().sendMessage("You return your pet to the red crystal.");
			world.getDelayedEventHandler().add(new ShortEvent(player) {
				public void action() {
					if (DataConversions.random(0, 4) != 0) {
						player.getActionSender().sendMessage("You catch the dragon in the crystal.");
						player.getInventory().remove(emptyPetItemID[0], 1);
						player.getInventory().add(new InvItem(fullPetItemID[0], 1));
						player.getActionSender().sendInventory();
						Npc petDragon = new Npc(player.getLocation(), petNpcID[0],  player.getUsername());
						Mob opponent = petDragon.getOpponent();
						if (opponent != null) {
							opponent.resetCombat(CombatState.ERROR);
						}
						petDragon.resetCombat(CombatState.ERROR);
						world.unregisterNpc(petDragon);
						petDragon.remove();
					} 
					else {
						player.getActionSender().sendMessage("The baby blue dragon manages to get away from you!");
					}				
				}
			});
		}
	}*/
}