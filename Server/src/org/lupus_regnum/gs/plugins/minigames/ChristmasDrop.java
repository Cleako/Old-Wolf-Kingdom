package org.lupus_regnum.gs.plugins.minigames;

import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.lupus_regnum.gs.event.MiniEvent;
import org.lupus_regnum.gs.event.DelayedEvent;
import org.lupus_regnum.gs.model.Item;
import org.lupus_regnum.gs.model.Player;
import org.lupus_regnum.gs.model.Point;
import org.lupus_regnum.gs.model.World;
import org.lupus_regnum.gs.tools.DataConversions;
import org.lupus_regnum.gs.plugins.listeners.action.CommandListener;
import org.lupus_regnum.gs.plugins.listeners.action.StartupListener;

public class ChristmasDrop implements 
	CommandListener, 
	StartupListener
	{
    /**
     * Is this running?
     */
    private boolean running;
    /*
     * Calls the StartupListener to check every 5 seconds to see if the christmas boolean is true or not.
     */
    public void onStartup() {
        World.getWorld().getDelayedEventHandler().add(new DelayedEvent(null, 1000*60) { // Ran every minute to see date = christmas
            @Override
            public void run() {
                Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("EST"));
				Calendar calendar = Calendar.getInstance();
				int month = calendar.get(Calendar.MONTH);
				int day = calendar.get(Calendar.DAY_OF_MONTH);
				int hour = calendar.get(Calendar.HOUR_OF_DAY);
				int minute = calendar.get(Calendar.MINUTE);
					if(month == cal.DECEMBER){
						if(day == 25){
								if(hour == 0){
									if(minute == 0){
										christmasdrop(971,0,0); 
										}
								}
								if(hour == 1){
									if(minute == 0){
										christmasdrop(971,0,0); 
										}
								}
								if(hour == 2){
									if(minute == 0){
										christmasdrop(971,0,0); 
										}
								}
								if(hour == 3){
									if(minute == 0){
										christmasdrop(971,0,0); 
										}
								}
								if(hour == 4){
									if(minute == 0){
										christmasdrop(971,0,0); 
										}
								}
								if(hour == 5){
									if(minute == 0){
										christmasdrop(971,0,0); 
										}
								}
								if(hour == 6){
									if(minute == 0){
										christmasdrop(971,0,0); 
										}
								}
								if(hour == 7){
									if(minute == 0){
										christmasdrop(971,0,0); 
										}
								}
								if(hour == 8){
									if(minute == 0){
										christmasdrop(971,0,0); 
										}
								}
								if(hour == 9){
									if(minute == 0){
										christmasdrop(971,0,0); 
										}
								}
								if(hour == 10){
									if(minute == 0){
										christmasdrop(971,0,0); 
										}
								}
								if(hour == 11){
									if(minute == 0){
										christmasdrop(971,0,0); 
										}
								}
								if(hour == 12){
									if(minute == 0){
										christmasdrop(971,0,0); 
										}
								}
								if(hour == 13){
									if(minute == 0){
										christmasdrop(971,0,0); 
										}
								}
								if(hour == 14){
									if(minute == 0){
										christmasdrop(971,0,0); 
										}
								}
								if(hour == 15){
									if(minute == 0){
										christmasdrop(971,0,0); 
										}
								}
								if(hour == 16){
									if(minute == 0){
										christmasdrop(971,0,0); 
										}
								}
								if(hour == 17){
									if(minute == 0){
										christmasdrop(971,0,0); 
										}
								}
								if(hour == 18){
									if(minute == 0){
										christmasdrop(971,0,0); 
										}
								}
								if(hour == 19){
									if(minute == 0){
										christmasdrop(971,0,0); 
										}
								}
								if(hour == 20){
									if(minute == 0){
										christmasdrop(971,0,0); 
										}
								}
								if(hour == 21){
									if(minute == 0){
										christmasdrop(971,0,0); 
										}
								}
								if(hour == 22){
									if(minute == 0){
										christmasdrop(971,0,0); 
										}
								}
								if(hour == 23){
									if(minute == 0){
										christmasdrop(971,0,0); 
										}
								}
						}
					}
			}
            });
	}
	
    /*
     * Command that sets the christmas boolean to true or false
     */
    public void onCommand(String command, String[] args, Player player) {
        if(command.equalsIgnoreCase("startchristmasdrop") && player.isAdmin()) {
            running = true;
            christmasdrop(971,0,0); //Begins the first drop immediately instead of waiting a full hour to occur
        }
        else if(command.equalsIgnoreCase("stopchristmasdrop") && player.isAdmin()) {
            running = false;
			}
        else if(command.equalsIgnoreCase("christmasdropstatus") && player.isAdmin()) {
            player.getActionSender().sendMessage("christmasdrop is set to " + running);
			}
		}
    
    /*
     * Spams the global chat area right before the drop occurs
     */
    public void spammsg() {
        World.getWorld().getDelayedEventHandler().add(new MiniEvent(null, 11000+15000) {
            @Override
            public void action() {
                World.getWorld().sendWorldAnnouncement("@red@Dropping in 3 seconds!");
                World.getWorld().getDelayedEventHandler().add(new MiniEvent(null, 1000) {
                    @Override
                    public void action() {
                        World.getWorld().sendWorldAnnouncement("@red@Dropping in 2 seconds!");
                        World.getWorld().getDelayedEventHandler().add(new MiniEvent(null, 1000) {
                            @Override
                            public void action() {
                                World.getWorld().sendWorldAnnouncement("@red@Dropping in 1 second!");
                                World.getWorld().getDelayedEventHandler().add(new MiniEvent(null, 1000) {
                                    @Override
                                    public void action() {
                                        World.getWorld().sendWorldAnnouncement("@red@Merry Christmas from Dragon Annihilators!");
                                    }
                                });
                            }
                        });
                    }
                });
            }    
        });
    
    }
    Point[] droplocs = { new Point(291,568), new Point(219, 642), new Point(136, 507), new Point(220, 432) };
       
    public void christmasdrop(final int firstdrop, final int seconddrop, final int thirddrop) {
        World.getWorld().sendWorldAnnouncement("@red@Christmas world drop started, dropping in 30 seconds!");
        World.getWorld().sendWorldAnnouncement("@whi@Get to Draynor, Falador (east bank), Varrock or Edgeville!");
        World.getWorld().sendWorldAnnouncement("@red@Dropping santa hats every hour on the hour!");
        spammsg();
        World.getWorld().getDelayedEventHandler().add(new MiniEvent(null, 30000) {
            @Override
            // drop set #1
            public void action() {
                for(int i = 0; i < 40; i++) {
                    Point coords = droplocs[DataConversions.random(0, droplocs.length-1)];
                    
                    int x = coords.getX()+(DataConversions.random(0, 1) == 1 ? -20 : 0)+DataConversions.random(0, 20);
                    int y = coords.getY()+(DataConversions.random(0, 1) == 1 ? -20 : 0)+DataConversions.random(0, 20);
                    while(World.getWorld().getTile(x, y).hasGameObject()) {
                        x = coords.getX()+(DataConversions.random(0, 1) == 1 ? -20 : 0)+DataConversions.random(0, 20);
                        y = coords.getY()+(DataConversions.random(0, 1) == 1 ? -20 : 0)+DataConversions.random(0, 20);
                        }
                    world.registerItem(new Item(firstdrop, x, y, 1, null, System.currentTimeMillis()-DataConversions.random(45000, 60000)));
                }
                if(seconddrop == 0) 
                    return;
				spammsg();
                World.getWorld().getDelayedEventHandler().add(new MiniEvent(null, 30000) {
                    @Override
                    // drop set #2
                    public void action() {
                        for(int i = 0; i < 40; i++) {
                            Point coords = droplocs[DataConversions.random(0, droplocs.length-1)];
                            
                            int x = coords.getX()+(DataConversions.random(0, 1) == 1 ? -20 : 0)+DataConversions.random(0, 20);
                            int y = coords.getY()+(DataConversions.random(0, 1) == 1 ? -20 : 0)+DataConversions.random(0, 20);
                            while(World.getWorld().getTile(x, y).hasGameObject()) {
                                x = coords.getX()+(DataConversions.random(0, 1) == 1 ? -20 : 0)+DataConversions.random(0, 20);
                                y = coords.getY()+(DataConversions.random(0, 1) == 1 ? -20 : 0)+DataConversions.random(0, 20);
                                }
                            world.registerItem(new Item(seconddrop, x, y, 1, null, System.currentTimeMillis()-DataConversions.random(45000, 60000)));
                        }
                        if(thirddrop == 0) 
                            return;
                        spammsg();
                        World.getWorld().getDelayedEventHandler().add(new MiniEvent(null, 30000) {
                            @Override
                            // drop set #3
                            public void action() {
                                for(int i = 0; i < 40; i++) {
                                    Point coords = droplocs[DataConversions.random(0, droplocs.length-1)];
                                    
                                    int x = coords.getX()+(DataConversions.random(0, 1) == 1 ? -20 : 0)+DataConversions.random(0, 20);
                                    int y = coords.getY()+(DataConversions.random(0, 1) == 1 ? -20 : 0)+DataConversions.random(0, 20);
                                    while(World.getWorld().getTile(x, y).hasGameObject()) {
                                        x = coords.getX()+(DataConversions.random(0, 1) == 1 ? -20 : 0)+DataConversions.random(0, 20);
                                        y = coords.getY()+(DataConversions.random(0, 1) == 1 ? -20 : 0)+DataConversions.random(0, 20);
                                        }
                                    if(thirddrop != 0) 
                                    world.registerItem(new Item(thirddrop, x, y, 1, null, System.currentTimeMillis()-DataConversions.random(45000, 60000)));
                                }
                            
                            }
                        });
                    }
                });
            }
        });
    }
}