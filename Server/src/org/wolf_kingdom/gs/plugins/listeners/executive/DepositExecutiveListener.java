package org.wolf_kingdom.gs.plugins.listeners.executive;

import org.wolf_kingdom.gs.model.Player;

public interface DepositExecutiveListener {
	/**
	 * Return true if you wish to prevent a user from depositing an item
	 * @param p Player we want to prevent from depositing items
	 * @param itemID ID of item we want to prevent from being deposited
	 * @param amount Quantity of item we want to prevent from being depositied
	 */
	public boolean blockDeposit(Player p, int itemID, int amount);
}
