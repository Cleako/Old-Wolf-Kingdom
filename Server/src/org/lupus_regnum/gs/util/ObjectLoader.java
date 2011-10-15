package org.lupus_regnum.gs.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.zip.GZIPInputStream;

import org.lupus_regnum.config.Config;



public class ObjectLoader {
    public static Object loadObject(String file) {
	try {
	    ObjectInputStream in = new ObjectInputStream(new GZIPInputStream(new FileInputStream(new File(Config.CONF_DIR, "data/ground.gz"))));
	    Object temp = in.readObject();
	    in.close();
	    return temp;
	} catch (Exception e) {
	    Logger.error(e);
	    return null;
	}
    }
}