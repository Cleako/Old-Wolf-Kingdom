package org.wolf_kingdom.client.listeners;

import org.wolf_kingdom.client.GameImageMiddleMan;
import org.wolf_kingdom.client.mudclient;

public interface ClientListener {
	
	/**
	 * Called every frame.
	 * @param gfx - the graphics class
	 * @param client - the mudclient
	 */
	public void onRender(GameImageMiddleMan gfx, mudclient client);
	/**
	 * 
	 * @param left - if true, left mouse button. else right
	 * @return - true (if it's been handled, won't take any further instructions for a mouse click in other areas)
	 */
	public boolean onMouseClick(boolean left, int x, int y);
	/**
	 * 
	 * @param keynum - the number of which key was pressed
	 * @return - true if keyboard input is final for this. if false - will continue to run instructions in other parts of the client for key input
	 */
	public boolean onKeyEvent(int keynum);
	
}
