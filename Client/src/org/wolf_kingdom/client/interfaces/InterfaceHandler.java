package org.wolf_kingdom.client.interfaces;

import java.util.ArrayList;

public class InterfaceHandler {
	
	private int mouseX = 0;
	private int mouseY = 0;
	private int mouseClick = 0;
	public ArrayList<InterfaceGUI> guis = new ArrayList<InterfaceGUI>();
	public void mouseMove(int mouseX, int mouseY) {
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		for(InterfaceGUI i : guis) {
			i.handleMouseMove(mouseX, mouseY);
		}
	}
	public boolean insideAnInterface(int mouseX, int mouseY) {
		boolean returnval = false;
		for(InterfaceGUI i : guis) {
			if(i.isVisible) {
				if(mouseX >= i.startX && mouseX <= i.startX+i.width && mouseY >= i.startY && mouseY <= i.startY + i.getHeight()) {
					returnval = true;
				}
				else {
					if(i.outsideclick != null)
						i.outsideclick.run();
				}
			}
		}
		
		return returnval;
	}
	public void mouseClicked(int click) {
		this.mouseClick = click;
		for(InterfaceGUI i : guis) {
			if(i.isVisible) {
				i.handleClick(mouseClick, mouseX, mouseY);
			}
		}
	}
	/**
	 * Called by the game loop.
	 */
	public void tick() {
		for(InterfaceGUI i : guis) {
			i.drawOnScreen();
		}
	}
}
