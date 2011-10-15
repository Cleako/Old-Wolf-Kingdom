package org.lupus_regnum.gs.plugins.listeners.action;

import org.lupus_regnum.gs.model.Player;

public interface DepositListener {
	/**
	 * Called when a user deposits an item
	 * @param p Player who deposits the item
	 * @param itemID Item that the player deposits
	 * @param amount Quantity of the item the player deposits
	 */
	public void onDeposit(Player p, int itemID, int amount);
}
