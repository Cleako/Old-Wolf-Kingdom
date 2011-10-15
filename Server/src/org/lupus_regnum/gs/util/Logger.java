package org.lupus_regnum.gs.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.lupus_regnum.config.Config;
import org.lupus_regnum.gs.model.World;


public class Logger {
    /**
     * Simple date formatter to keep a date on outputs
     */
    private static SimpleDateFormat formatter = new SimpleDateFormat(Config.DATE_FORMAT);
    
    public static void error(Object o) {
		if (o instanceof Exception) {
		    Exception e = (Exception) o;
		    e.printStackTrace();
		    if (World.getWorld() == null || !World.getWorld().getServer().isInitialized()) {
		    	System.exit(1);
		    } 
		    else {
		    	World.getWorld().getServer().kill();
		    }
		    return;
		}
		if (o.toString() != null)
			World.getWorld().getServer().getLoginConnector().getActionSender().logAction(o.toString(), 2);
    }

    public static void event(Object o) {
    	World.getWorld().getServer().getLoginConnector().getActionSender().logAction(o.toString(), 1);
    }

    public static void mod(Object o) {
    	World.getWorld().getServer().getLoginConnector().getActionSender().logAction(o.toString(), 3);
	}
    /**
     * Sends s to loginserver and prints to stdout
     * @param s
     */
    public static void systemerr(String s) {
    	World.getWorld().getServer().getLoginConnector().getActionSender().logAction(s, 4);
    	print(s);
    }
    /**
     * Prints to console with timestamp
     * @param o Object to print
     */
    public static void print(Object o) {
    	System.out.print(formatter.format(new Date()) + " " + o.toString());
    }
    /**
     * Prints to console with timestamp and newline
     * @param o Object to print
     */
    public static void println(Object o) {
    	if(o == null) {
    		System.out.println(formatter.format(new Date()) + " Object is null");
    	}
    	System.out.println(formatter.format(new Date()) + " " + o.toString());
    }
}
