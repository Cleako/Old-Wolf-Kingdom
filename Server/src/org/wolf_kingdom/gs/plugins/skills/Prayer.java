package org.wolf_kingdom.gs.plugins.skills;

import org.wolf_kingdom.gs.model.GameObject;
import org.wolf_kingdom.gs.model.Player;
import org.wolf_kingdom.gs.plugins.listeners.action.ObjectActionListener;

public class Prayer implements ObjectActionListener {

	@Override
	public void onObjectAction(GameObject object, String command, Player player) {
		if(command.equalsIgnoreCase("recharge at")) {
			player.getActionSender().sendMessage("You recharge at the altar.");
			player.getActionSender().sendSound("recharge");
			int maxPray = object.getID() == 200 ? player.getMaxStat(5) + 2 : player.getMaxStat(5);
			if (player.getCurStat(5) < maxPray) {
				player.setCurStat(5, maxPray);
			}
			player.getActionSender().sendStat(5);
			return;
		}
	}
	
}
