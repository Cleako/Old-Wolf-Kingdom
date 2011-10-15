package org.lupus_regnum.client.component;

import org.lupus_regnum.client.Client;
import org.lupus_regnum.client.GameImageMiddleMan;
import org.lupus_regnum.client.Mob;
import org.lupus_regnum.client.mudclient;
import org.lupus_regnum.client.model.RSCComponent;
import org.lupus_regnum.client.util.DataConversions;

public abstract class RSCPartyComponent extends RSCComponent {


	public RSCPartyComponent() {
		Client.getClient().components.add(this);
		super.setX(5);
		super.setY(73);
		super.setWidth(121);
		super.setHeight(40);
	}


	public boolean onKeyEvent(int keynum) {

		return false;
	}

	public boolean onMouseClick(boolean left, int x, int y) {

		return false;
	}
	public void onRender(GameImageMiddleMan gfx, mudclient client) {
		if(!isVisible())
			return;
		int boxY = super.getY() + 25;
		int alphaHeight = 32 * client.partyMembers.size();	
		gfx.drawBoxAlpha(super.getX(), super.getY(), super.getWidth(), alphaHeight, 0x989898, 160);

		for(int i=0; i < client.partyMembers.size(); i++) {

			double maxHP = client.partyMembers.get(i).maxHP;
			double curHP = client.partyMembers.get(i).curHP;
			int percent = (int) ((curHP / maxHP) * 100); // percentage of our HP.

			gfx.drawBox(super.getX() + 5, boxY, (int)(percent % (super.getWidth() - 15)) + percent / 10, 5, super.RED);
			gfx.drawBoxEdge(super.getX() + 5, boxY, super.getWidth() - 10, 5, super.WHITE);
			String logo = null;
		

			/*if(client.partyMembers.get(i).flag == null) {
				System.out.println("null yes");
				for(Mob m : client.playerArray) {
					if(m != null && m.name != null) {
						if(m.name.equals(client.partyMembers.get(i).name)) {
							if(m.flag != null) {
								logo = m.flag;
								client.partyMembers.get(i).flag = logo;
							} else {

							}
						}
					}
				}
			} else {
				System.out.println("null no");
				logo = client.partyMembers.get(i).flag;
			}*/

			if(logo == null || logo.length() != 2)
				gfx.drawString(client.partyMembers.get(i).name + (client.partyMembers.get(i).leader ? " #adm#" : ""), super.getX() + 5, boxY - 6, 2, curHP == 0 ? super.RED :  super.WHITE);
			
			gfx.drawString((curHP == 0 ? "@red@Lv" : "@gre@L") + client.partyMembers.get(i).lv, super.getX() + super.getWidth() - 25, boxY - 6, 2, super.WHITE);
			boxY+=30;
		}
	}

}


