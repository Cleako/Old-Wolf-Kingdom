package org.wolf_kingdom.gs.plugins.minigames.pets;

import org.wolf_kingdom.gs.event.ShortEvent;
import org.wolf_kingdom.gs.model.InvItem;
import org.wolf_kingdom.gs.model.Npc;
import org.wolf_kingdom.gs.model.Player;
import org.wolf_kingdom.gs.model.World;
import static org.wolf_kingdom.gs.phandler.client.InvActionHandler.showBubble;
import org.wolf_kingdom.gs.plugins.listeners.action.CommandListener;
import org.wolf_kingdom.gs.plugins.listeners.action.PlayerLoginListener;
import org.wolf_kingdom.gs.plugins.listeners.action.PlayerLogoutListener;

public abstract class Pets implements PlayerLoginListener, PlayerLogoutListener, CommandListener {
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
                        showBubble(player, item);
                        player.getActionSender().sendMessage("You begin to summon your pet.");
			world.getDelayedEventHandler().add(new ShortEvent(player) {
				public void action() {
                                        owner.setBusy(true);
                                        Npc petDragon = new Npc(player.getLocation(), petNpcID[0],  player.getUsername());
					petDragon.shouldRespawn = false;
                                        petDragon.setFollowing(player, 1);
					player.getInventory().add(new InvItem(emptyPetItemID[0], 1));
					player.getActionSender().sendInventory();
                                        world.registerNpc(petDragon);
                                        owner.setBusy(false);
				}
			});
		}
            }
	}
        
        /*@Override
        public void onPlayerLogin(Player player) {
		if (player.getInventory().hasItemId(1231)) {
                            for(int it=0; it < 30; it++) {
                                if(player.getInventory().remove(new InvItem(1231)) != -1 ){
                                        player.getInventory().remove(new InvItem(1231));
                                        player.getActionSender().sendInventory();
                                        player.getInventory().add(new InvItem(1222));
                                        player.getActionSender().sendInventory();
                                        System.out.println("Empty pet item replaced with full pet item via Pets.java.");
                                }
                                else {
                                }
                            }
                }
	}*/
        
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