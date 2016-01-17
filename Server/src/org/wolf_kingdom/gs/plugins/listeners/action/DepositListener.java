package org.wolf_kingdom.gs.plugins.listeners.action;

import org.wolf_kingdom.gs.model.Player;

public interface DepositListener {
	/**
	 * Called when a user deposits an item
	 * @param p Player who deposits the item
	 * @param itemID Item that the player deposits
	 * @param amount Quantity of the item the player deposits
	 */
	public void onDeposit(Player p, int itemID, int amount);
}
