package org.wolf_kingdom.client;

import java.awt.Event;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
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
		   BufferedReader br;
            br = new BufferedReader(new FileReader(filename));
		   String nextLine = "";
		   StringBuffer sb = new StringBuffer();
		   while ((nextLine = br.readLine()) != null) {
		     sb.append(nextLine);
		     sb.append(lineSep);
		   }
		   return sb.toString();
		}
	
    public GameFrame(GameWindow gameWindow, int width, int height, String title, boolean resizable, boolean flag1) {
        frameOffset = 28;
        frameWidth = width;
        frameHeight = height;
        aGameWindow = gameWindow;
        if (flag1)
            frameOffset = 48;
        else
            frameOffset = 28;
        super.setTitle(title);
        InputListener listener = new InputListener(gameWindow, true);
        super.addMouseWheelListener(listener);
        super.addMouseListener(listener);
        super.addMouseMotionListener(listener);
        super.addKeyListener(listener);
        super.setResizable(resizable);
        super.setVisible(true);
        super.toFront();	
        super.setSize(frameWidth, frameHeight);
        this.addWindowListener(new WindowListener(){
			@Override
			public void windowActivated(WindowEvent arg0) {}

			@Override
			public void windowClosed(WindowEvent arg0) {
				System.exit(-1);			
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(-1);				
			}
			
			@Override
			public void windowDeactivated(WindowEvent arg0) {}
			@Override
			public void windowDeiconified(WindowEvent arg0) {}
			@Override
			public void windowIconified(WindowEvent arg0) {}
			@Override
			public void windowOpened(WindowEvent arg0) {}
		});
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
    
    public void addListeners(GameFrame a, GameWindow aGameWindow) {
		InputListener input = new InputListener(aGameWindow, true);
		a.addMouseListener(input);
		a.addMouseMotionListener(input);
		a.addKeyListener(input);
		a.addMouseWheelListener(input);
    }
    
    @Override
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
        else if (event.id == 201)
            aGameWindow.destroy();
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
