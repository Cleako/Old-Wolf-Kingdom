package org.lupus_regnum.client.interfaces;

import java.util.ArrayList;

import org.lupus_regnum.client.GameImage;
import org.lupus_regnum.client.mudclient;
import org.lupus_regnum.client.interfaces.models.Callback;
import org.lupus_regnum.client.interfaces.models.IButton;
import org.lupus_regnum.client.interfaces.models.ILabel;
import org.lupus_regnum.client.interfaces.models.ILine;
import org.lupus_regnum.client.interfaces.models.IPanel;
import org.lupus_regnum.client.interfaces.models.ISprite;
import org.lupus_regnum.client.interfaces.models.OnMisClickCallBack;
import org.lupus_regnum.client.interfaces.models.RSGuiModels;

public class InterfaceGUI {
	
	int width;
	private int height;
	private mudclient mudclient;
	public boolean isVisible = false;
	int startX;
	int startY;
	public int backgroundColor = 0x463d2e;
	public int borderColor = 0x6b5e53;
	public ArrayList<RSGuiModels> buttons = new ArrayList<RSGuiModels>();
	public ILabel titlebar = null;
	public ILabel xbutton = null;
	public OnMisClickCallBack outsideclick = null;
	
	public InterfaceGUI(int width, int height, mudclient mc) {
		this.width = width;
		this.setHeight(height);
		this.mudclient = mc;
		startX = (mc.windowWidth - width)/2;
		startY = (mc.windowHeight - height)/2+10;
		titlebar = addLabel("Titlebar", 20, -24, null, 1);
		xbutton = addLabel("x", width-23, -23, new Callback() {

			@Override
			public void run(InterfaceGUI gui, RSGuiModels id) {
				gui.isVisible = false;
			}
		}, 1);
	}
	public IButton addButton(int locx, int locy, Callback callback, int height, int width) {
		IButton b = new IButton(locx, locy, callback, height, width, startX, startY, this);
		buttons.add(b);
		return b;
	}
	public ILabel addLabel(String text, int locx, int locy, Callback callback, int textBoldness) {
		ILabel b = new ILabel(text, locx, locy, callback, startX, startY, this, textBoldness);
		buttons.add(b);
		return b;
	}
	public ISprite addSprite(int id, int locx, int locy, Callback callback) {
		ISprite b = new ISprite(id, locx, locy, callback, startX, startY, this);
		buttons.add(b);
		return b;
	}
	public IPanel addPanel(int locx, int locy, int height, int width) {
		IPanel b = new IPanel(locx, locy, height, width, startX, startY, this);
		buttons.add(b);
		return b;
	}	
	public ILine addLine(int locx, int locy, int width, boolean horizontal) {
		ILine l = new ILine(locx, locy, width, horizontal, startX, startY, this);
		buttons.add(l);
		return l;
	}
	public void drawOnScreen() {
		if(!isVisible) 
			return;

		int titlebar = 30;
		mudclient.gameGraphics.drawBoxAlpha(startX, startY-titlebar, width, titlebar, backgroundColor, 160);
		
		mudclient.gameGraphics.drawLineX(startX, startY-titlebar, width, borderColor);
		mudclient.gameGraphics.drawLineY(startX, startY-titlebar, titlebar, borderColor);
		mudclient.gameGraphics.drawLineY(startX+width-1, startY-titlebar, titlebar, borderColor);
		
		mudclient.gameGraphics.drawLineX(startX-1, startY-titlebar-1, width+1, borderColor);
		mudclient.gameGraphics.drawLineY(startX-1, startY-titlebar-1, titlebar, borderColor);
		mudclient.gameGraphics.drawLineY(startX+width, startY-titlebar-1, titlebar, borderColor);
		
		mudclient.gameGraphics.drawBoxAlpha(startX, startY, width, getHeight(), backgroundColor,160);
		mudclient.gameGraphics.drawBoxEdge(startX, startY, width, getHeight(), borderColor);
		mudclient.gameGraphics.drawBoxEdge(startX-1, startY-1, width+2, getHeight()+2, borderColor);
		
		
		
		for(RSGuiModels b : buttons) {
			if(b instanceof IPanel)
				b.drawMe(mudclient.gameGraphics);
		}
		for(RSGuiModels b : buttons) {
			if(!(b instanceof IPanel))
				b.drawMe(mudclient.gameGraphics);
		}
	}
	public void handleMouseMove(int mouseX, int mouseY) {
		if(!isVisible) 
			return ;
		synchronized(buttons) {
			for(RSGuiModels b : buttons) {
				b.handleMouseMove(mouseX, mouseY);
			}
		}
	}
	public boolean handleClick(int mouseClick, int mouseX, int mouseY) {
		if(!isVisible) 
			return false;
		
		for(RSGuiModels b : buttons) {
			if(b.checkClick(mouseX, mouseY))
				return true;
		}
		return false;
	}
	public int textWidth(String s, int i) {
		int j = 0;
		byte abyte0[] = GameImage.aByteArrayArray336[i];
		for (int k = 0; k < s.length(); k++) {
			if (s.charAt(k) == '@' && k + 4 < s.length()
					&& s.charAt(k + 4) == '@') {
				k += 4;
			} else if (s.charAt(k) == '~' && k + 4 < s.length()
					&& s.charAt(k + 4) == '~') {
				k += 4;
			}
			else if (s.charAt(k) == '~' && k + 5 < s.length()
					&& s.charAt(k + 5) == '~') {
				k += 5;
			}else {
				try {
					j += abyte0[GameImage.charIndexes[s.charAt(k)] + 7];
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		return j;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getHeight() {
		return height;
	}
	public void clearLabels() {
		ArrayList<RSGuiModels> temp = new ArrayList<RSGuiModels>();
		for(RSGuiModels b : buttons) {
			if(b instanceof ILabel && ((ILabel) b).y > 0) {
				temp.add(b);
			}
		}
		buttons.removeAll(temp);
		
	}
}
