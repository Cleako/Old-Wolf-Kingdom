package org.wolf_kingdom.gs.plugins.listeners.action;

import org.wolf_kingdom.gs.model.Player;

public interface CommandListener {
	/**
	 * Called when a user sends a command
	 * @param command Command our player sends
	 * @param args Arguments of the command the player sends
	 * @param player Player who sends the command
	 */
	public void onCommand(String command, String[] args, Player player);
}
