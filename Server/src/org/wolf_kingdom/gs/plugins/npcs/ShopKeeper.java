package org.wolf_kingdom.gs.plugins.npcs;

import org.wolf_kingdom.gs.event.ShortEvent;
import org.wolf_kingdom.gs.model.ChatMessage;
import org.wolf_kingdom.gs.model.Npc;
import org.wolf_kingdom.gs.model.Player;
import org.wolf_kingdom.gs.model.Shop;
import org.wolf_kingdom.gs.model.World;
import org.wolf_kingdom.gs.plugins.listeners.action.TalkToNpcListener;
import org.wolf_kingdom.gs.tools.DataConversions;

public class ShopKeeper implements TalkToNpcListener {

	private World world = World.getWorld();
	
	int[] shopKeepers = new int[]{ 
			51, 183, 112, 55, 56, 12, 82, 83, 44, 233, 87, 235, 88, 105, 106, 130, 143,
			145, 146, 168, 169, 185, 186, 222, 223, 371, 391, 528, 58, 54, 173, 250, 155,
			289, 149, 131, 129, 167, 141, 115, 69, 59, 48, 228, 230, 101, 773, 75, 157,
			228, 1, 84, 85, 103, 165, 336, 337, 331, 330, 328, 325, 329, 514, 522, 620,
			282, 793, 113, 156, 297, 624, 788, 779, 269
			
	};
	
	@Override
	public void onTalkToNpc(Player player, final Npc npc) {
		if(!DataConversions.inArray(shopKeepers, npc.getID())) {
			return;
		}
		final Shop shop = world.getShop(npc.getLocation());
    	if (shop == null) {
    		return;
    	}	
    	try {
    		if (shop.getGreeting() != null) {
    			player.informOfNpcMessage(new ChatMessage(npc, shop.getGreeting(), player));
    		}
    		player.setBusy(true);
    		world.getDelayedEventHandler().add(new ShortEvent(player) {
    			public void action() {
    				owner.setBusy(false);
    				owner.setAccessingShop(shop);
    				owner.getActionSender().showShop(shop);
    			}
    		});
    		npc.blockedBy(player);
    	} 
    	catch (Exception e) {
    		e.printStackTrace();
    	}
	}
	
}
