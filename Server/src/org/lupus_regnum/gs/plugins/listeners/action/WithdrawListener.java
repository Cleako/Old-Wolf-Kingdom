package org.lupus_regnum.gs.plugins.listeners.action;

import org.lupus_regnum.gs.model.Player;
/**
 * This interface is called when a user withdraws an item from the bank
 * @author Peeter
 *
 */
public interface WithdrawListener {
	/**
	 * Called when a user withdraws an item
	 * @param p Player that has withdrawn the item
	 * @param itemID Item that the player has withdrawn
	 * @param amount Quantity of the item that the player has withdrawn
	 */
	public void onWithdraw(Player p, int itemID, int amount);
}
