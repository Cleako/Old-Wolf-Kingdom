package org.wolf_kingdom.client;

import java.util.ArrayList;

import org.wolf_kingdom.client.component.RSCBox;
import org.wolf_kingdom.client.component.RSCInfoComponent;
import org.wolf_kingdom.client.component.RSCOptionDialog;
import org.wolf_kingdom.client.component.RSCOptionDialogButton;
import org.wolf_kingdom.client.component.RSCPartyComponent;
import org.wolf_kingdom.client.model.RSCComponent;

public class Client {

	public ArrayList<RSCComponent> components = new ArrayList<RSCComponent>();

	protected Client() { }
	public static int THING = 0;
	private static Client client = null;
	//*
	public static Client getClient() {
		if(client == null)
			client = new Client();
		return client;
	}

	public static boolean inBounds(int locX, int locY, int x, int y, int width, int height) {
		return locX >= x && locX <= x + width && locY >= y && locY <= y + height;
	}

	public ArrayList<RSCComponent> getComponents() {
		return components;
	}

	public void initComponents() {

	}

	static String bankpin = "";
	public static void test() {
		
		
		//mudclient.killQueue.addKill(new KillThing("@whi@#fau#I PK OMANSIDI #i81# #fus#Bassleader"));
	
		/*RSCOptionDialog option = new RSCOptionDialog() {
			public void onButtonClicked(RSCOptionDialogButton button) {
				bankpin+=button.text;
				if(bankpin.length() == 4) {			
					destroy();	
					System.out.println("Bankpin: " + bankpin);
					bankpin = "";
				} else {
					setTitle2("@red@" + bankpin + "*");
				}
			}
		};


		option.setTitle("Enter your Bank pin");
		option.setTitle2("@red@*");
		option.setOptions("1", "2", "3", "4", "5", "6", "7", "8", "9", "0");*/
		
	
	}



}
