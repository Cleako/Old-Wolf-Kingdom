package org.lupus_regnum.client.component;

import org.lupus_regnum.client.Client;
import org.lupus_regnum.client.GameImageMiddleMan;
import org.lupus_regnum.client.mudclient;
import org.lupus_regnum.client.model.RSCComponent;

public abstract class RSCBox extends RSCComponent {

	public RSCBox() {
		super.setX(512);
		super.setY(446);
		super.setWidth(247);
		super.setHeight(17);
	}

	private mudclient client;



	public boolean onKeyEvent(int keynum) {

		return false;
	}

	public boolean onMouseClick(boolean left, int x, int y) {

		if(Client.inBounds(x, y, super.getX(), super.getY(), super.getWidth(), super.getHeight())) {
			if(client.option != null)
				client.option.destroy();
			client.option = null;
			onClick();
			return true;
		}
		return false;
	}

	public void onRender(GameImageMiddleMan gfx, mudclient clientt) {
		if(client == null)
			client = clientt;
		/*gfx.drawBox(super.getX(), super.getY() - 1, super.getWidth(), super.getHeight(), super.DARK_GRAY); // make black box
		if(Client.inBounds(client.mouseX, client.mouseY, super.getX(), super.getY(), super.getWidth(), super.getHeight())) {
			gfx.drawBoxEdge(super.getX(), super.getY() - 1, super.getWidth(), super.getHeight(), super.GREEN);
			gfx.drawString("    Tools", 595, 459, 2, super.GREEN);
		}
		else {
			gfx.drawBoxEdge(super.getX(), super.getY() - 1, super.getWidth(), super.getHeight(), super.ORANGE);
			gfx.drawString("    Tools", 595, 459, 2, super.WHITE);
		}*/

	}


	public abstract void onClick();



}
