package org.lupus_regnum.client;

import java.awt.Event;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class GameFrame extends Frame {

	private static String readFile(String filename) throws IOException {
		   String lineSep = System.getProperty("line.separator");
		   BufferedReader br = new BufferedReader(new FileReader(filename));
		   String nextLine = "";
		   StringBuffer sb = new StringBuffer();
		   while ((nextLine = br.readLine()) != null) {
		     sb.append(nextLine);
		     sb.append(lineSep);
		   }
		   return sb.toString();
		}
	
    public GameFrame(GameWindow gameWindow, int width, int height, String title, boolean resizable, boolean flag1) {
		//	super(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration());
			
	
        frameOffset = 28;
        frameWidth = width;
        frameHeight = height;
        aGameWindow = gameWindow;
        if (flag1)
            frameOffset = 48;
        else
            frameOffset = 28;
        setTitle(title);
        setResizable(resizable);
        show();
        toFront();
        resize(frameWidth, frameHeight);
        aGraphics49 = getGraphics();

      
        		
        		
    }

    public Graphics getGraphics() {
        Graphics g = super.getGraphics();
        if (graphicsTranslate == 0)
            g.translate(0, 24);
        else
            g.translate(-5, 0);
        return g;
    }

	public void resize(int i, int j) {
        super.resize(i, j + frameOffset);
    }

	public boolean handleEvent(Event event) {
        if (event.id == 401)
            aGameWindow.keyDown(event, event.key);
        else if (event.id == 402)
            aGameWindow.keyUp(event, event.key);
        else if (event.id == 501)
            aGameWindow.mouseDown(event, event.x, event.y - 24);
        else if (event.id == 506)
            aGameWindow.mouseDrag(event, event.x, event.y - 24);
        else if (event.id == 502)
            aGameWindow.mouseUp(event, event.x, event.y - 24);
        else if (event.id == 503)
            aGameWindow.mouseMove(event, event.x, event.y - 24);
        else if (event.id == 201) {
        	if(mudclient.notifyUserOfCrash) {
				try {  
                	String data = URLEncoder.encode("report", "UTF-8") + "=" + URLEncoder.encode(readFile(System.getProperty("user.home") + File.separator + "LR" + File.separator + "error.log")); 
                	URL url = new URL("http://dargaming.net/crash.php"); 
                	URLConnection conn = url.openConnection(); 
                	conn.setDoOutput(true); 
                	OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream()); 
                	wr.write(data); 
                	wr.flush(); 

                	BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream())); 
                	String line; 
                	while ((line = rd.readLine()) != null) { 
                		} 
                	wr.close(); 
                	rd.close(); 
                	}
                catch (Exception e) { 
                	e.printStackTrace();
                } 
			}
			System.exit(0);
			
			
        }
        else if (event.id == 1001)
            aGameWindow.action(event, event.target);
        else if (event.id == 403)
            aGameWindow.keyDown(event, event.key);
        else if (event.id == 404)
            aGameWindow.keyUp(event, event.key);
        return true;
    }
//Create Account
    public final void paint(Graphics g) {
        aGameWindow.paint(g);
    }

    int frameWidth;
    int frameHeight;
    int graphicsTranslate;
    int frameOffset;
    GameWindow aGameWindow;
    Graphics aGraphics49;
   
}
