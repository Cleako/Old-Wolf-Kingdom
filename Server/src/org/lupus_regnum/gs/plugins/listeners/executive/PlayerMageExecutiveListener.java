package org.lupus_regnum.gs.plugins.listeners.executive;

import org.lupus_regnum.gs.model.Player;

public interface PlayerMageExecutiveListener {
	/**
	 * Return true if you wish to prevent the cast
	 * @param player Player we do not want to be able to cast a spell on another player
	 * @param affectedPlayer Player we do not want to have a spell cast against by our player
	 * @param spell Spell we do not want being cast by our player on another player
	 * @return
	 */
	public boolean blockPlayerMage(Player player, Player affectedPlayer, Integer spell);
}
