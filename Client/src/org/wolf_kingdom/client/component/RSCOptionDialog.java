package org.wolf_kingdom.client.component;

import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.wolf_kingdom.client.Client;
import org.wolf_kingdom.client.GameImage;
import org.wolf_kingdom.client.GameImageMiddleMan;
import org.wolf_kingdom.client.mudclient;
import org.wolf_kingdom.client.model.RSCComponent;
import org.wolf_kingdom.client.model.Sprite;

public abstract class RSCOptionDialog extends RSCComponent {

	private ArrayList<RSCOptionDialogButton> buttons = new ArrayList<RSCOptionDialogButton>();

	private String title = "";
	private String title2 = "";
	public mudclient mc;
	public Object obj; // args

	public RSCOptionDialog() {
		Client.getClient().components.add(this);
		super.setX(144);	
		super.setY(150);
		super.setWidth(490);
		super.setHeight(113);
	}

	public void setTitle2(String s) {
		title2 = s;
	}

	public boolean onKeyEvent(int keynum) {

		return false;
	}

	public boolean onMouseClick(boolean left, int x, int y) {
		for(RSCOptionDialogButton button : buttons) {
			if(Client.inBounds(x, y, button.x, button.y, button.width, button.height)) {
				if(button.exit) {
					destroy();
					return true;
				}
				onButtonClicked(button);
				return true;
			}
		}
		return true;
	}

	public void onRender(GameImageMiddleMan gfx, mudclient client) {
		try {
		if(mc == null)
			mc = client;
		//0x989898
		
		gfx.drawBoxAlpha(super.getX() + 10, super.getY() + 10, super.getWidth(), super.getHeight(), super.BLACK, 160);
		gfx.drawPicture(super.getX(), super.getY(), 2010);
		//gfx.drawBox(super.getX(), super.getY(), super.getWidth(), super.getHeight(), super.getColor()); // make black box
		//gfx.drawLineY(super.getX(), super.getY(), super.getHeight(), super.ORANGE); // left side
		//gfx.drawLineY(super.getX() + super.getWidth(), super.getY(), super.getHeight(), super.ORANGE); // right side
		//gfx.drawLineX(super.getX(), super.getY(), super.getWidth(), super.ORANGE); // top
		//gfx.drawLineX(super.getX(), super.getY() + super.getHeight(), super.getWidth(), super.ORANGE); // bottom
		gfx.drawText(title, client.windowWidth / 2, ((client.windowHeight / 2) - 52) + 20, 4, super.WHITE);
		gfx.drawText(title2, client.windowWidth / 2, (client.windowHeight / 2) - 52 + 20, 4, super.WHITE);

		int count = buttons.size() - 1;
		int x = (super.getX() + 15) + 16;
		int y = super.getY() + (super.getHeight() / 2);
		int height = (super.getHeight() / 2) - 15;
		int width = ((super.getWidth() - 10 - (15 * count)) / count);
		int index = 0;


		for(RSCOptionDialogButton button : buttons) {
			if(button.exit) {
				/*boolean highlight = Client.inBounds(client.mouseX, client.mouseY, button.x, button.y, button.width, button.height);
				gfx.drawBox(button.x, button.y, button.width, button.height, super.DARK_GRAY); // make gray
				gfx.drawLineY(button.x, button.y, button.height, highlight ? super.GREEN : super.WHITE); // left side
				gfx.drawLineY(button.x + button.width, button.y, button.height, highlight ? super.GREEN : super.WHITE); // right side
				gfx.drawLineX(button.x, button.y, button.width, highlight ? super.GREEN : super.WHITE); // top
				gfx.drawLineX(button.x, button.y + button.height, button.width, highlight ? super.GREEN : super.WHITE); // bottom
				gfx.drawText(button.text, button.x + (button.width / 2), button.y + (button.height / 2) + 5, 3, highlight ? super.GREEN : super.RED);
			*/
			} else {
				boolean highlight = Client.inBounds(client.mouseX, client.mouseY, x, y, width, height);
				gfx.drawBox(x, y, width, height, super.BLACK); // make gray
				//gfx.drawBoxAlpha(x, y, width, height, super.BLUE, 160);
				gfx.drawLineY(x, y, height, highlight ? super.BABY_BLUE : super.BLUE); // left side
				gfx.drawLineY(x + width, y, height, highlight ? super.BABY_BLUE : super.BLUE); // right side
				gfx.drawLineX(x, y, width, highlight ? super.BABY_BLUE : super.BLUE); // top
				gfx.drawLineX(x, y + height, width, highlight ? super.BABY_BLUE : super.BLUE); // bottom
				gfx.drawText(button.text, x + (width / 2), y + (height / 2) + 5, 3, highlight ? super.BABY_BLUE : super.BLUE);
				button.x = x;
				button.y = y;
				button.width = width;
				button.height = height;
				x+=width + 10;
				index++;
			}
		}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	public abstract void onButtonClicked(RSCOptionDialogButton button);

	public void setTitle(String title) {
		this.title = title;
	}


	public void setOptions(String... options) {
		for(String s : options) {
			RSCOptionDialogButton button = new RSCOptionDialogButton();
			button.setText(s);
			buttons.add(button);
		}
		RSCOptionDialogButton button = new RSCOptionDialogButton();
		button.x = 629;
		button.y = super.getY() + 9;
		button.height = 20;
		button.width = 20;
		button.text = "X";
		button.exit = true;
		buttons.add(button);
	}

}


