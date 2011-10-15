package org.lupus_regnum.gs.model;

import org.lupus_regnum.config.Formulae;


public class Point {
    public static Point location(int x, int y) {
	if (x < 0 || y < 0) {
	    throw new IllegalArgumentException("Point may not contain non negative values x:" + x + " y:" + y);
	}
	return new Point(x, y);
    }

    public int x;

    public int y;

    protected Point() {
    }

    public Point(int x, int y) {
		this.x = x;
		this.y = y;
    }

    public final boolean equals(Object o) {
		if (o instanceof Point) {
		    return this.x == ((Point) o).x && this.y == ((Point) o).y;
		}
		return false;
    }

    public String getDescription() {
		if (inModRoom()) {
		    return "Mod Room";
		}
		
		int wild = wildernessLevel();
		if (wild > 0) {
		    return "Wilderness lvl-" + wild;
		}
		
		if  (inVarrock()) {
			return "Varrock";
		}
		
		if  (inEdgeville()) {
			return "Edgeville";
		}
		
		if  (inBarbVillage()) {
			return "Barbarian Village";
		}
		
		if  (inDraynor()) {
			return "Draynor";
		}
		
		if  (inLumbridge()) {
			return "Lumbridge";
		}
		
		if  (inAlKharid()) {
			return "Al Kharid";
		}
		
		if  (inFalador()) {
			return "Falador";
		}
		
		if  (inPortSarim()) {
			return "Port Sarim";
		}
		
		if  (inTaverly()) {
			return "Taverly";
		}
		
		if  (inEntrana()) {
			return "Entrana";
		}
		
		if  (inCatherby()) {
			return "Catherby";
		}
		
		if  (inSeers()) {
			return "Seers";
		}
		
		if  (inGnomeStronghold()) {
			return "Gnome Stronghold";
		}
		
		if  (inArdougne()) {
			return "Ardougne";
		}
		
		if  (inYanille()) {
			return "Yanille";
		}
		
		if  (inBrimhaven()) {
			return "Brimhaven";
		}
		
		if  (inKaramja()) {
			return "Karamja";
		}
		
		if  (inShiloVillage()) {
			return "ShiloVillage";
		}
		
		return "Unknown";
    }

    public final int getX() {
    	return x;
    }

    public final int getY() {
    	return y;
    }

    public int hashCode() {
    	return x << 16 | y;
    }

    public boolean inBounds(int x1, int y1, int x2, int y2) {
    	return x >= x1 && x <= x2 && y >= y1 && y <= y2;
    }

    public boolean inModRoom() {
    	return inBounds(64, 1639, 80, 1643);
    }
	
	public boolean inWilderness() {
    	return wildernessLevel() > 0;
    }
	
	public  boolean inVarrock() {
		return inBounds(50, 180, 444, 565);
	}
	
	public  boolean inEdgeville() {
		return inBounds(180, 245, 427, 472);
	}
   
	public  boolean inBarbVillage() {
		return inBounds(180, 245, 472, 535);
	}
   
	public  boolean inDraynor() {
		return inBounds(180, 245, 535, 715);
	}
   
	public  boolean inLumbridge() {
		return inBounds(100, 600, 180, 670);
	}
   
	public  boolean inAlKharid() {
		return inBounds(47, 94, 578, 733);
	}
   
	public  boolean inFalador() {
		return inBounds(245, 338, 510, 608);
	}
   
	public  boolean inPortSarim() {
		return inBounds(245, 355, 608, 693);
	}
   
	public  boolean inTaverly() {
		return inBounds(338, 384, 430, 576);
	}
   
	public  boolean inEntrana() {
		return inBounds(395, 441, 525, 573);
	}
   
	public  boolean inCatherby() {
		return inBounds(399, 477, 476, 513);
	}
   
	public  boolean inSeers() {
		return inBounds(477, 592, 432, 485);
	}
   
	public  boolean inGnomeStronghold() {
		return inBounds(673, 751, 432,  537);
	}
   
	public boolean inArdougne() {
		return  inBounds(500, 708, 537, 640);
	}
   
	public boolean  inYanille() {
		return inBounds(528, 671, 712, 785);
	}
   
	public  boolean inBrimhaven() {
		return inBounds(435, 522, 640, 710);
	}
   
	public  boolean inKaramja() {
		return inBounds(333, 435, 679, 710);
	}
   
	public  boolean inShiloVillage() {
		return inBounds(384, 431, 815,  860);
	}

    public String toString() {
    	return "(" + x + ", " + y + ")";
    }

    public int wildernessLevel() {
		int wild = 2203 - (y + (1776 - (944 * Formulae.getHeight(this))));
		if (x + 2304 >= 2640) {
		    wild = -50;
		}
		if (wild > 0) {
		    return 1 + wild / 6;
		}
		return 0;
    }
    
    public static boolean inWilderness(int x, int y) {
		int wild = 2203 - (y + (1776 - (944 * (int) (y / 944))));
		if (x + 2304 >= 2640) {
		    wild = -50;
		}
		if (wild > 0) {
		    return (1 + wild / 6) >= 1;
		}
		return false;
    }
}
