package org.lupus_regnum.client;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class GameWindow extends Applet implements Runnable 
{	
	
	public int getYOffset() {
		return 0;
	}
	public static int gameWidth = 800;
	public static int gameHeight = 600 - 30;
	private static final long serialVersionUID = -8976121820127349733L;
	public static final Color BAR_COLOUR = new Color(120, 0, 0);
	public static final Font LOADING_FONT = new Font("Helvetica", 0, 12);

	protected void startGame() {
	}

	protected synchronized void method2() {
	}

	protected void logoutAndStop() {
	}

	protected synchronized void method4() {
	}

	protected final void createWindow(int width, int height, String title,
			boolean resizable) {
		appletWidth = width;
		appletHeight = height;
		gameFrame = new GameFrame(this, width, height, title, resizable, false);
		loadingScreen = 1;
		gameWindowThread = new Thread(this);
		gameWindowThread.start();
		gameWindowThread.setPriority(1);
	}

	public void setLogo(Image logo) {
		//loadingLogo = logo;
	}

	protected final void changeThreadSleepModifier(int i) {
		threadSleepModifier = 1000 / i;
	}

	protected final void resetCurrentTimeArray() {
		for (int i = 0; i < 10; i++) {
			currentTimeArray[i] = 0L;
		}
	}

	public final synchronized boolean mouseMove(Event event, int i, int j) {
		mouseX = i;
		mouseY = j + yOffset;
		mouseDownButton = 0;
		lastActionTimeout = 0;
		mudclient.getInterfaceHandler().mouseMove(mouseX, mouseY);
		return true;
	}
	
	public final synchronized boolean mouseUp(Event event, int i, int j) {
		mouseX = i;
		mouseY = j + yOffset;
		mouseDownButton = 0;
		return true;
	}
	
	public final synchronized boolean mouseDown(Event event, int i, int j) {
		mouseX = i;
		mouseY = j + yOffset;
		mouseDownButton = event.metaDown() ? 2 : 1;
		lastMouseDownButton = mouseDownButton;
		lastActionTimeout = 0;
		mudclient.getInterfaceHandler().mouseClicked(mouseDownButton);
		
		handleMouseDown(mouseDownButton, i, j);
		return true;
	}


	public final synchronized boolean mouseDrag(Event event, int i, int j) {
		mouseX = i;
		mouseY = j + yOffset;
		mouseDownButton = event.metaDown() ? 2 : 1;
		return true;
	}

    public final synchronized boolean keyDown(Event event, int key) {
        handleMenuKeyDown(key);
        keyDown = key;
        keyDown2 = key;
        lastActionTimeout = 0;
        if (key == 1006)
            keyLeftDown = true;
        if (key == 1007)
            keyRightDown = true;
      
        if ((char) key == '\u03F0') // 1008 or F1
            keyF1Toggle = !keyF1Toggle;
        boolean validKeyDown = false;
        for (int j = 0; j < charSet.length(); j++) {
            if (key != charSet.charAt(j))
                continue;
            validKeyDown = true;
            break;
        }

        if (validKeyDown && inputText.length() < 20)
            inputText += (char) key;
        if (validKeyDown && inputMessage.length() < 80)
            inputMessage += (char) key;
        if (key == 8 && inputText.length() > 0) // backspace
            inputText = inputText.substring(0, inputText.length() - 1);
        if (key == 8 && inputMessage.length() > 0) // backspace
            inputMessage = inputMessage.substring(0, inputMessage.length() - 1);
        if (key == 10 || key == 13) { // enter/return
            enteredText = inputText;
            enteredMessage = inputMessage;
        }
        return true;
    }

	protected void handleMenuKeyDown(int key) { }

    public final synchronized boolean keyUp(Event event, int i) {
        keyDown = 0;
        if (i == 1006)
            keyLeftDown = false;
        if (i == 1007)
            keyRightDown = false;
        return true;
    }


	public final synchronized boolean mouseMove(int i, int j) {
		mouseX = i;
		mouseY = j + yOffset + getYOffset();
		mudclient.getInterfaceHandler().mouseMove(mouseX, mouseY);
		mouseDownButton = 0;
		lastActionTimeout = 0;
		return true;
	}

	public final synchronized boolean mouseUp(int i, int j) {
		mouseX = i;
		mouseY = j + yOffset + getYOffset();
		mouseDownButton = 0;
		return true;
	}

	public final synchronized boolean mouseDown(boolean meta, int i, int j) {
		mouseX = i;
		mouseY = j + yOffset + getYOffset();
		if(mudclient.getInterfaceHandler().insideAnInterface(mouseX, mouseY)) {
			return false;
		}
		
		mouseDownButton = meta ? 2 : 1;
		
		lastMouseDownButton = mouseDownButton;
		lastActionTimeout = 0;
		handleMouseDown(mouseDownButton, i, j);
		return true;
	}

	protected void handleMouseDown(int button, int x, int y) {
	}

	public final synchronized boolean mouseDrag(boolean meta, int i, int j) {
		mouseX = i;
		mouseY = j + yOffset + getYOffset();
		if(mudclient.getInterfaceHandler().insideAnInterface(mouseX, mouseY)) {
			return false;
		}
		mouseDownButton = meta ? 2 : 1;

		return true;
	}

	protected void handleMouseDrag(MouseEvent mouse, int x, int y, int button) {
	}

	public final void init() {
		
		appletWidth = gameWidth;
		appletHeight = gameHeight;
		loadingScreen = 1;
		startThread(this);

	}
	public final void start() {
		if (exitTimeout >= 0) {
			exitTimeout = 0;
		}
	}

	public final void stop() {
		if (exitTimeout >= 0) {
			exitTimeout = 4000 / threadSleepModifier;
		}
	}

	private final void close() {
		exitTimeout = -2;
		System.out.println("Closing program");
		logoutAndStop();
		try {
			Thread.sleep(1000L);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (gameFrame != null) {
			gameFrame.dispose();
		}
		System.exit(0);
	}

	private void loadFonts() {
		GameImage.loadFont("h11p", 0, this);
		GameImage.loadFont("h12b", 1, this);
		GameImage.loadFont("h12p", 2, this);
		GameImage.loadFont("h13b", 3, this);
		GameImage.loadFont("h14b", 4, this);
		GameImage.loadFont("h16b", 5, this);
		GameImage.loadFont("h20b", 6, this);
		GameImage.loadFont("h24b", 7, this);
	}

	public final void run() {
		if (loadingScreen == 1) {
			loadingScreen = 2;
			setLoadingGraphics(getGraphics());
			drawLoadingLogo();
			drawLoadingScreen(0, "Loading...");
			startGame();

			loadingScreen = 0;
		}
		int i = 0;
		int j = 256;
		int sleepTime = 1;
		int i1 = 0;
		for (int j1 = 0; j1 < 10; j1++)
			currentTimeArray[j1] = System.currentTimeMillis();

		while (exitTimeout >= 0) {
			if (exitTimeout > 0) {
				exitTimeout--;
				if (exitTimeout == 0) {
					close();
					gameWindowThread = null;
					return;
				}
			}
			int k1 = j;
			int i2 = sleepTime;
			j = 300;
			sleepTime = 1;
			long l1 = System.currentTimeMillis();
			if (currentTimeArray[i] == 0L) {
				j = k1;
				sleepTime = i2;
			} else if (l1 > currentTimeArray[i])
				j = (int) ((long) (2560 * threadSleepModifier) / (l1 - currentTimeArray[i]));
			if (j < 25)
				j = 25;
			if (j > 256) {
				j = 256;
				sleepTime = (int) ((long) threadSleepModifier - (l1 - currentTimeArray[i]) / 10L);
				try {
					if(sleepTime > 60) sleepTime = 60;
					Thread.sleep(sleepTime);
				} catch (InterruptedException _ex) {
					if (sleepTime < threadSleepTime)
						sleepTime = threadSleepTime;
				}
			}
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException _ex) {
				}
				currentTimeArray[i] = l1;
				i = (i + 1) % 10;
				if (sleepTime > 1) {
					for (int j2 = 0; j2 < 10; j2++)
						if (currentTimeArray[j2] != 0L)
							currentTimeArray[j2] += sleepTime;

				}
				int k2 = 0;
				while (i1 < 256) {
					method2();
					i1 += j;
					if (++k2 > anInt5) {
						i1 = 0;
						anInt10 += 6;
						if (anInt10 > 25) {
							anInt10 = 0;
							keyF1Toggle = true;
						}
						break;
					}
				}
				anInt10--;
				i1 &= 0xff;
				method4();
			}
			if (exitTimeout == -1)
				close();
			gameWindowThread = null;
		}

		public final void update(Graphics g) {
			paint(g);
		}

		public final void paint(Graphics g) {
			if (loadingScreen == 2 && loadingLogo != null) {
				drawLoadingScreen(anInt16, loadingBarText);
			}
		}

		private final void drawLoadingLogo() {
			getLoadingGraphics().setColor(Color.black);
			getLoadingGraphics().drawImage(loadingLogo, appletWidth, appletHeight,
					5, 0, this);
			loadFonts();
		}

		private final void drawLoadingScreen(int i, String s) {
			try {
				int j = (appletWidth - 281) / 2;
				int k = (appletHeight - 148) / 2;
				getLoadingGraphics().setColor(Color.black);
				getLoadingGraphics().fillRect(0, 0, appletWidth, appletHeight);
				getLoadingGraphics().drawImage(loadingLogo, 0, 0, appletWidth,
						appletHeight, this);
				j += 2;
				k += 120;
				anInt16 = i;
				loadingBarText = s;
				//getLoadingGraphics().setColor(BAR_COLOUR);
				//getLoadingGraphics().drawRect(j - 2, k - 2, 280, 23);
				getLoadingGraphics().fillRect(j, k, (277 * i) / 100, 20);
				getLoadingGraphics().setColor(Color.black);
				drawString(getLoadingGraphics(), s, LOADING_FONT, j + 138, k + 10);
				if (loadingString != null) {
					getLoadingGraphics().setColor(Color.WHITE);
					drawString(getLoadingGraphics(), loadingString, LOADING_FONT,
							j + 138, k - 120);
					return;
				}
			} catch (Exception _ex) {
			}
		}

		protected final void drawLoadingBarText(int i, String s) {
			try {
				int j = (appletWidth - 281) / 2;
				int k = (appletHeight - 148) / 2;
				j += 2;
				k += 120;
				anInt16 = i;
				loadingBarText = s;
				int l = (277 * i) / 100;
				getLoadingGraphics().setColor(BAR_COLOUR);
				getLoadingGraphics().fillRect(j, k, l, 20);
				getLoadingGraphics().setColor(Color.black);
				getLoadingGraphics().fillRect(j + l, k, 277 - l, 20);
				getLoadingGraphics().setColor(Color.WHITE);
				drawString(getLoadingGraphics(), s, LOADING_FONT, j + 138, k + 10);
				return;
			} catch (Exception _ex) {
				return;
			}
		}

		protected final void drawString(Graphics g, String s, Font font, int i,
				int j) {
			FontMetrics fontmetrics = (gameFrame == null ? this : gameFrame)
			.getFontMetrics(font);
			fontmetrics.stringWidth(s);
			g.setFont(font);
			g.drawString(s, i - fontmetrics.stringWidth(s) / 2, j
					+ fontmetrics.getHeight() / 4);
		}

		protected final static void drawStringStatic(Graphics g, String s,
				Font font, int i, int j) {
			FontMetrics fontmetrics = gameFrame.getFontMetrics(font);
			fontmetrics.stringWidth(s);
			g.setFont(font);
			g.drawString(s, i, j);
		}

		protected byte[] load(String filename) {
			int j = 0;
			int k = 0;
			byte abyte0[] = null;
			try {
				java.io.InputStream inputstream = DataOperations
				.streamFromPath(filename);
				DataInputStream datainputstream = new DataInputStream(inputstream);
				byte abyte2[] = new byte[6];
				datainputstream.readFully(abyte2, 0, 6);
				j = ((abyte2[0] & 0xff) << 16) + ((abyte2[1] & 0xff) << 8)
				+ (abyte2[2] & 0xff);
				k = ((abyte2[3] & 0xff) << 16) + ((abyte2[4] & 0xff) << 8)
				+ (abyte2[5] & 0xff);
				int l = 0;
				abyte0 = new byte[k];
				while (l < k) {
					int i1 = k - l;
					if (i1 > 1000) {
						i1 = 1000;
					}
					datainputstream.readFully(abyte0, l, i1);
					l += i1;
				}
				datainputstream.close();
			} catch (IOException _ex) {
				_ex.printStackTrace();
			}
			if (k != j) {
				byte abyte1[] = new byte[j];
				DataFileDecrypter.unpackData(abyte1, j, abyte0, k, 0);
				return abyte1;
			} else {
				return abyte0;
			}
		}

		public Graphics getGraphics() {
			if (gameFrame != null) {
				return gameFrame.getGraphics();
			}
			return super.getGraphics();
		}

		public Image createImage(int i, int j) {
			if (gameFrame != null) {
				return gameFrame.createImage(i, j);
			}
			return super.createImage(i, j);
		}

		protected Socket makeSocket(String address, int port) throws IOException {
			Socket socket = new Socket(InetAddress.getByName(address), port);
			socket.setSoTimeout(30000);
			socket.setTcpNoDelay(true);
			return socket;
		}

		protected void startThread(Runnable runnable) {
			Thread thread = new Thread(runnable);
			thread.setDaemon(true);
			thread.start();
		}

		public GameWindow() {
			appletWidth = gameWidth;
			appletHeight = gameHeight + 40;
			threadSleepModifier = 20;
			anInt5 = 1000;
			currentTimeArray = new long[10];
			loadingScreen = 1;
			loadingBarText = "Loading";


			threadSleepTime = 1;
			keyF1Toggle = false;
			inputText = "";
			enteredText = "";
			inputMessage = "";
			enteredMessage = "";
		}

		public void setLoadingGraphics(Graphics loadingGraphics) {
			GameWindow.loadingGraphics = loadingGraphics;
		}

		public static Graphics getLoadingGraphics() {
			return loadingGraphics;
		}

		private Image loadingLogo;
		protected static int appletWidth;
		protected static int appletHeight;
		protected Thread gameWindowThread;
		private int threadSleepModifier;
		private int anInt5;
		private long currentTimeArray[];
		public static GameFrame gameFrame = null;
		private int exitTimeout;
		private int anInt10;
		public int yOffset;
		public int lastActionTimeout;
		public int loadingScreen;
		public String loadingString;
		private int anInt16;
		private String loadingBarText;
		private static Graphics loadingGraphics;
		private static String charSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"\243$%^&*()-_=+[{]};:'@#~,<.>/?\\| ";

		public boolean keyLeftDown;
		public boolean keyRightDown;

		public int threadSleepTime;
		public int mouseX;
		public int mouseY;
		public int mouseDownButton;
		public int lastMouseDownButton;
		public int keyDown;
		public int keyDown2;
		public boolean keyF1Toggle;
		public String inputText;
		public String enteredText;
		public String inputMessage;
		public String enteredMessage;

	}
