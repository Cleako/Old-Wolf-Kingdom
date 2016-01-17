package org.wolf_kingdom.gs.plugins.listeners.executive;

import org.wolf_kingdom.gs.model.Player;

public interface WithdrawExecutiveListener {
	/**
	 * Return true if you wish to prevent a user from withdrawing an item
	 * @param p Player we do not want able to withdraw an item
	 * @param itemID Item we do not want our player to withdraw
	 * @param amount Quantity of the item we do not want our player to withdraw
	 */
	public void blockWithdraw(Player p, int itemID, int amount);
}
