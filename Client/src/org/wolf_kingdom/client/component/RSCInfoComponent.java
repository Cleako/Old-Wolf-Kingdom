package org.wolf_kingdom.client.component;


import java.util.ArrayList;

import org.wolf_kingdom.client.Client;
import org.wolf_kingdom.client.GameImageMiddleMan;
import org.wolf_kingdom.client.mudclient;
import org.wolf_kingdom.client.model.RSCComponent;

public abstract class RSCInfoComponent extends RSCComponent {

	private ArrayList<RSCOptionDialogButton> buttons = new ArrayList<RSCOptionDialogButton>();

	public mudclient mc;
	public Object obj; // args
	

	public RSCInfoComponent() {
		Client.getClient().components.add(this);
		super.setX(110 + 43);	
		super.setY(107 - 38);
		super.setWidth(451);
		super.setHeight(315);
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
	
	public abstract void onButtonClicked(RSCOptionDialogButton b);

	@SuppressWarnings("static-access")
	public void onRender(GameImageMiddleMan gfx, mudclient client) {
		try {
			if(mc == null)
				mc = client;
			
			/*if(!mudclient.BIG_CLIENT) { // We don't use this.
				client.displayMessage("You must be on the orignal (Big) client mode to use this", 3, -1);
				destroy();
				return;
			}*/

			//selected = mudclient.SELECTED_STAT;
			gfx.drawBox(super.getX(), super.getY(), super.getWidth(), super.getHeight(), super.getColor()); // make black box
			gfx.drawLineY(super.getX(), super.getY(), super.getHeight(), super.ORANGE); // left side
			gfx.drawLineY(super.getX() + super.getWidth(), super.getY(), super.getHeight(), super.ORANGE); // right side
			gfx.drawLineX(super.getX(), super.getY(), super.getWidth(), super.ORANGE); // top
			gfx.drawLineX(super.getX(), super.getY() + super.getHeight(), super.getWidth(), super.ORANGE); // bottom
			gfx.drawString("@whi@Name: @gre@" + mudclient.INFO_NAME + "  @whi@ Combat Lv: @gre@" + mudclient.INFO_LV + "  @whi@ Nationality: @gre@" + (client.INFO_NATIONALITY == null ? "Unset" : client.INFO_NATIONALITY + "#f" + client.INFO_NATIONALITY + "#"), super.getX() + 10, super.getY() + 23, 5, super.WHITE);
			
			RSCOptionDialogButton button = new RSCOptionDialogButton();
			button.x = super.getX() + super.getWidth() - 25;
			button.y = super.getY() + 5;
			button.height = 20;
			button.width = 20;
			button.text = "X";
			button.exit = true;
			buttons.add(button);
			boolean highlight = Client.inBounds(client.mouseX, client.mouseY, button.x, button.y, button.width, button.height);
			gfx.drawBox(button.x, button.y, button.width, button.height, super.DARK_GRAY); // make gray
			gfx.drawLineY(button.x, button.y, button.height, highlight ? super.GREEN : super.WHITE); // left side
			gfx.drawLineY(button.x + button.width, button.y, button.height, highlight ? super.GREEN : super.WHITE); // right side
			gfx.drawLineX(button.x, button.y, button.width, highlight ? super.GREEN : super.WHITE); // top
			gfx.drawLineX(button.x, button.y + button.height, button.width, highlight ? super.GREEN : super.WHITE); // bottom
			gfx.drawText(button.text, button.x + (button.width / 2), button.y + (button.height / 2) + 5, 3, (highlight ? super.GREEN : super.ORANGE));
			
			
			
			String str = "--";
			if(client.INFO_DEATHS > 0) {
				double dbl = (double)((double)mudclient.INFO_KILLS / (double)mudclient.INFO_DEATHS);
				long l = (int)Math.round(dbl * 100);
				dbl = l / 100.0;
				str = "" + dbl;
			}
			
			gfx.drawString("                        @whi@Kills: @gre@" + mudclient.INFO_KILLS + "   @whi@ Deaths: @gre@" + mudclient.INFO_DEATHS + "   @whi@ K/D Ratio: @gre@" + (mudclient.INFO_DEATHS == 0 ? "--" : str), super.getX() + 10, super.getY() + 55, 4, super.WHITE);
			//gfx.drawString("Total Exp: @yel@" + client.TOTAL_EXP, super.getX() + 10, super.getY() + super.getHeight() - 11, 5, super.WHITE);
			//gfx.drawString("Combat Lv: @yel@" + client.ourPlayer.level + "  @whi@HP: @yel@" + client.playerStatBase[3], super.getX() + 250, super.getY() + super.getHeight() - 11, 5, super.WHITE);
	
			int y = super.getY() + 90;
			for(int i=0; i < 9; i++) {
				gfx.drawString("@whi@" + client.skillArray[i] + ": @gre@" + mudclient.INFO_SKILLS[i], super.getX() + 100, y, 4, super.WHITE);
				y+=20;
			}
			
			y = super.getY() + 90;
			for(int i=0; i < 9; i++) {
				gfx.drawString("@whi@" + client.skillArray[i + 9] + ": @gre@" + mudclient.INFO_SKILLS[i + 9], super.getX() + 250, y, 4, super.WHITE);
				y+=20;
			}
			
			y+=40;
			gfx.drawString("@whi@Skill Total: @gre@" + client.INFO_SKILLTOTAL + "     @whi@ Total Exp: @gre@" + client.INFO_TOTALEXP, super.getX() + 85, y - 15, 5, super.WHITE);
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	public void setOptions(String... options) {
		for(String s : options) {
			RSCOptionDialogButton button = new RSCOptionDialogButton();
			button.setText(s);
			buttons.add(button);
		}

	}

}


