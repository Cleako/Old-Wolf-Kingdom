package org.lupus_regnum.client.component;


public class RSCOptionDialogButton {
	
	public String text = "";
	
	public void setText(String s) {
		text = s;
		int k = s.length() * 4; // make the box longer width if has longer text
		width+=k;
	}
	
	
	public int amount;
	public int x = 0;
	public int y = 0;
	public int width = 130;
	public int height = 0;
	public boolean exit = false;
	
}
