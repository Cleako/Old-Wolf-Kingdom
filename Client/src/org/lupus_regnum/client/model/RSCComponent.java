package org.lupus_regnum.client.model;

import org.lupus_regnum.client.GameImage;
import org.lupus_regnum.client.listeners.ClientListener;

public abstract class RSCComponent implements ClientListener {
	
	static {
		BLUE = GameImage.convertRGBToLong(53, 103, 238);
		BABY_BLUE = GameImage.convertRGBToLong(77, 177, 214);
	}
	
	private int height = 50;
	private int width = 100;
	private int x = 0;
	private int y = 0;
	public boolean visible = true;
	private int opacity = 50;
	
	public boolean needsCleanup = false;
	
	public static final int RED = 0xff0000;
	public static final int BABY_BLUE;
	public static final int LIGHT_RED = 0xff9040;
	public static final int YELLOW = 0xffff00;
	public static final int GREEN = 65280;
	public static int BLUE;
	public static final int CYAN = 0xff0000;
	public static final int DARK_GRAY = 0x808080;
	public static final int MAGNETA = 0xff00ff;
	public static final int WHITE = 0xffffff;
	public static final int BLACK = 0;
	public static final int DARK_RED = 0xc00000;
	public static final int ORANGE = 0xff9040;
	public static final int DARK_ORANGE = 0xff3000;
	public static final int LIME_GREEN = 0x80ff00;
	
	public void destroy() {
		needsCleanup = true;
	}
	
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public int getOpacity() {
		return opacity;
	}
	public void setOpacity(int opacity) {
		this.opacity = opacity;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public int color = 0;



}
