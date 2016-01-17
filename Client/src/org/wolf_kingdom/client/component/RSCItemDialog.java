package org.wolf_kingdom.client.component;


import java.util.ArrayList;

import org.wolf_kingdom.client.Client;
import org.wolf_kingdom.client.GameImageMiddleMan;
import org.wolf_kingdom.client.mudclient;
import org.wolf_kingdom.client.model.RSCComponent;

public class RSCItemDialog extends RSCComponent {
	
	private ArrayList<RSCOptionDialogButton> buttons = new ArrayList<RSCOptionDialogButton>();
	
	private String title = "";
	private String title2 = "";
	
	public RSCItemDialog() {
		Client.getClient().components.add(this);
		super.setX(60);
		super.setY(93);
		super.setWidth(389);
		super.setHeight(113);
	}
	
	public void setTitle2(String s) {
		title2 = s;
	}

	public boolean onKeyEvent(int keynum) {

		return false;
	}

	public boolean onMouseClick(boolean left, int x, int y) {
		
		return true;
	}
	
	public void onRender(GameImageMiddleMan gfx, mudclient client) {
		gfx.drawBox(super.getX(), super.getY(), super.getWidth(), super.getHeight(), super.getColor()); // make black box
		gfx.drawLineY(super.getX(), super.getY(), super.getHeight(), super.ORANGE); // left side
		gfx.drawLineY(super.getX() + super.getWidth(), super.getY(), super.getHeight(), super.ORANGE); // right side
		gfx.drawLineX(super.getX(), super.getY(), super.getWidth(), super.ORANGE); // top
		gfx.drawLineX(super.getX(), super.getY() + super.getHeight(), super.getWidth(), super.ORANGE); // bottom
		gfx.drawText(title, client.windowWidth / 2, (client.windowHeight / 2) - 52, 4, super.WHITE);
		gfx.drawText(title2, client.windowWidth / 2, (client.windowHeight / 2) - 52 + 20, 4, super.WHITE);
		
		int count = buttons.size();
		int x = (super.getX() + 15);
		int y = super.getY() + (super.getHeight() / 2);
		int height = (super.getHeight() / 2) - 15;
		int width = ((super.getWidth() - 10 - (15 * count)) / count);
		int index = 0;
	
		
		for(RSCOptionDialogButton button : buttons) {
			boolean highlight = Client.inBounds(client.mouseX, client.mouseY, x, y, width, height);
			gfx.drawBox(x, y, width, height, super.DARK_GRAY); // make gray
			gfx.drawLineY(x, y, height, highlight ? super.GREEN : super.WHITE); // left side
			gfx.drawLineY(x + width, y, height, highlight ? super.GREEN : super.WHITE); // right side
			gfx.drawLineX(x, y, width, highlight ? super.GREEN : super.WHITE); // top
			gfx.drawLineX(x, y + height, width, highlight ? super.GREEN : super.WHITE); // bottom
			gfx.drawText(button.text, x + (width / 2), y + (height / 2) + 5, 3, highlight ? super.GREEN : super.ORANGE);
			button.x = x;
			button.y = y;
			button.width = width;
			button.height = height;
			x+=width + 15;
			index++;
		}
	}
	

}



