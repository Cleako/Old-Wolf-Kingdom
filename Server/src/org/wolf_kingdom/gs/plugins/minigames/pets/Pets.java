package org.wolf_kingdom.gs.plugins.minigames.pets;

import org.wolf_kingdom.gs.external.EntityHandler;
import org.wolf_kingdom.gs.event.ShortEvent;
import org.wolf_kingdom.gs.model.InvItem;
import org.wolf_kingdom.gs.model.Mob;
import org.wolf_kingdom.gs.model.Npc;
import org.wolf_kingdom.gs.model.Player;
import org.wolf_kingdom.gs.model.World;
import org.wolf_kingdom.gs.states.CombatState;
import org.wolf_kingdom.gs.states.Action;
import org.wolf_kingdom.gs.tools.DataConversions;

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
            if (player.getInventory().hasItemId(fullPetItemID[0])) {
                if(item.getDef().getCommand().equalsIgnoreCase("inspect")) {
			player.getInventory().remove(fullPetItemID[0], 1);
			player.getActionSender().sendInventory();
                        player.getActionSender().sendMessage("You summon your pet.");
			world.getDelayedEventHandler().add(new ShortEvent(player) {
				public void action() {
                                        Npc petDragon = new Npc(player.getLocation(), petNpcID[0],  player.getUsername());
					petDragon.shouldRespawn = false;
                                        petDragon.setFollowing(player, 1);
					player.getInventory().add(new InvItem(emptyPetItemID[0], 1));
					player.getActionSender().sendInventory();
                                        world.registerNpc(petDragon);
				}
			});
		}
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