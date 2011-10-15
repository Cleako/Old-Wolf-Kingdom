package org.lupus_regnum.gs.plugins.listeners.action;

import org.lupus_regnum.gs.model.Player;

public interface CommandListener {
	/**
	 * Called when a user sends a command
	 * @param command Command our player sends
	 * @param args Arguments of the command the player sends
	 * @param player Player who sends the command
	 */
	public void onCommand(String command, String[] args, Player player);
}
