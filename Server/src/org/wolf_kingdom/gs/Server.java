package org.wolf_kingdom.gs;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.apache.mina.common.IoAcceptor;
import org.apache.mina.common.IoAcceptorConfig;
import org.apache.mina.common.ThreadModel;
import org.apache.mina.transport.socket.nio.SocketAcceptor;
import org.apache.mina.transport.socket.nio.SocketAcceptorConfig;
import org.apache.mina.transport.socket.nio.SocketSessionConfig;
import org.wolf_kingdom.config.Config;
import org.wolf_kingdom.config.Constants;
import org.wolf_kingdom.gs.connection.RSCConnectionHandler;
import org.wolf_kingdom.gs.core.GameEngine;
import org.wolf_kingdom.gs.core.LoginConnector;
import org.wolf_kingdom.gs.event.DelayedEvent;
import org.wolf_kingdom.gs.event.SingleEvent;
import org.wolf_kingdom.gs.model.World;
import org.wolf_kingdom.gs.plugins.PluginHandler;
import org.wolf_kingdom.gs.util.Logger;


/**
 * The entry point for RSC server.
 */
public class Server {

    /**
     * World instance
     */
    private static World world = null;

    public static void main(String[] args) throws IOException {
		String configFile = "server.conf";
		if (args.length > 0) {
		    File f = new File(args[0]);
		    if (f.exists()) {
			configFile = f.getName();
		    }
		}
		
		Constants.GameServer.MOTD = "@yel@Welcome to @whi@" + Constants.GameServer.SERVER_NAME;
		
		try {
			Config.initConfig(configFile);
		}
		catch(Exception e) {
			System.out.println("Unable to initilize config file: " + configFile);
			System.exit(1);
		}
		
		
		world = World.getWorld();
		Logger.println(Constants.GameServer.SERVER_NAME + " [" + (Constants.GameServer.MEMBER_WORLD ? "P2P" : "F2P") + "] " + "Server starting up...");
		new Server();
    }

    public static boolean isMembers() {
    	return Constants.GameServer.MEMBER_WORLD;
    }

    /**
     * The SocketAcceptor
     */
    private IoAcceptor acceptor;
    /**
     * The login server connection
     */
    private LoginConnector connector;
    /**
     * The game engine
     */
    private GameEngine engine;

    
    public LoginConnector getConnector() {
	return connector;
    }

    public void setConnector(LoginConnector connector) {
	this.connector = connector;
    }

    public boolean isRunning() {
    	return running;
    }

    /**
     * Is the server running still?
     */
    private boolean running;

    /**
     * Update event - if the server is shutting down
     */
    private DelayedEvent updateEvent;

    /**
     * Creates a new server instance, which in turn creates a new engine and
     * prepares the server socket to accept connections.
     */
    public Server() {
		running = true;
		world.setServer(this);
		try {
			PluginHandler.getPluginHandler().initPlugins();
		}
		catch (Exception e) {
		    Logger.error(e);
		}
		try {
		    connector = new LoginConnector();
		    engine = new GameEngine();
		    engine.start();
		    while (!connector.isRegistered()) {
		    	Thread.sleep(100);
		    }
	
		    acceptor = new SocketAcceptor(Runtime.getRuntime().availableProcessors() + 1, Executors.newCachedThreadPool());
		    IoAcceptorConfig config = new SocketAcceptorConfig();
		    config.setDisconnectOnUnbind(true);
	
		    config.setThreadModel(ThreadModel.MANUAL);
		    SocketSessionConfig ssc = (SocketSessionConfig) config.getSessionConfig();
		    ssc.setSendBufferSize(10000);
		    ssc.setReceiveBufferSize(10000);
		    acceptor.bind(new InetSocketAddress(Config.SERVER_IP, Config.SERVER_PORT), new RSCConnectionHandler(engine), config);
	
		} catch (Exception e) {
		    Logger.error(e);
		}
    }

    /**
     * Returns the game engine for this server
     */
    public GameEngine getEngine() {
    	return engine;
    }

    public LoginConnector getLoginConnector() {
    	return connector;
    }

    public boolean isInitialized() {
    	return engine != null && connector != null;
    }

    /**
     * Kills the game engine and irc engine
     * 
     * @throws InterruptedException
     */
    public void kill() {
		Logger.print(Constants.GameServer.SERVER_NAME + " shutting down...");
		running = false;
		engine.emptyWorld();
		connector.kill();
		System.exit(0);

    }


    /**
     * Shutdown the server in 60 seconds
     */
    public boolean shutdownForUpdate() {
		if (updateEvent != null) {
		    return false;
		}
		updateEvent = new SingleEvent(null, 59000) {
		    public void action() {
			kill();
		    }
		};
		World.getWorld().getDelayedEventHandler().add(updateEvent);
		return true;
    }

    /**
     * MS till the server shuts down
     */
    public int timeTillShutdown() {
		if (updateEvent == null) {
		    return -1;
		}
		return updateEvent.timeTillNextRun();
    }

    /**
     * Unbinds the socket acceptor
     */
    public void unbind() {
		try {
		    acceptor.unbindAll();
		} 
		catch (Exception e) {}
    }
}
