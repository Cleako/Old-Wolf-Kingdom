package org.lupus_regnum.gs.plugins.minigames;

import java.util.ArrayList;
import java.util.List;

import org.lupus_regnum.gs.model.Item;
import org.lupus_regnum.gs.model.Npc;
import org.lupus_regnum.gs.model.Player;
import org.lupus_regnum.gs.model.Point;
import org.lupus_regnum.gs.model.World;
import org.lupus_regnum.gs.plugins.listeners.action.CommandListener;
import org.lupus_regnum.gs.plugins.listeners.action.PlayerKilledNpcListener;
import org.lupus_regnum.gs.plugins.listeners.executive.PlayerKilledNpcExecutiveListener;
import org.lupus_regnum.gs.plugins.listeners.executive.PlayerMageNpcExecutiveListener;
import org.lupus_regnum.gs.plugins.listeners.executive.PlayerRangeNpcExecutiveListener;
import org.lupus_regnum.gs.tools.DataConversions;

public class DropParty implements
		CommandListener, 
		PlayerKilledNpcListener, 
		PlayerKilledNpcExecutiveListener,
		PlayerRangeNpcExecutiveListener,
		PlayerMageNpcExecutiveListener
		{

	private int npcID = 3;
	
	private int dropID = -1;
	
	private Point point = new Point(606, 704);
	
	private List<Npc> npcs = new ArrayList<Npc>();
	
	@Override
	public void onCommand(String command, String[] args, Player player) {
		if(command.equalsIgnoreCase("startdrop") && player.isAdmin()) {
			try {
				for (Npc npc : npcs) {
					World.getWorld().unregisterNpc(npc);
					npc.remove();
				}
				dropID = Integer.parseInt(args[0]);
				for(int i = 0; i < 100; i++) {
					Npc n = new Npc(npcID, 
							DataConversions.random(point.getX()-5, point.getX()+5),
							DataConversions.random(point.getY()-5, point.getY()+5), 
							point.getX()-5, 
							point.getX()+5, 
							point.getY()-5, 
							point.getY()+5);
					n.setHits(40);
					n.setRespawn(false);
					npcs.add(n);
					World.getWorld().registerNpc(n);
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				player.getActionSender().sendMessage("Wrong Drop ID");
			}
		}
	}
	
	@Override
	public boolean blockPlayerKilledNpc(Player p, Npc n) {
		if(n.getID() == npcID && n.getLocation().inBounds(point.getX()-10, point.getY()-10, point.getX()+10, point.getY()+10)) {
			boolean found = false;
			for (Npc npc : npcs) {
				if (npc.getID() == n.getID() && npc.getX() == n.getX() && npc.getY() == n.getY()) {
					found = true;
					break;
				}
			}
			return found;
		}
		return false;
	}
	@Override
	public void onPlayerKilledNpc(Player p, Npc n) {
		if(n.getID() == npcID && n.getLocation().inBounds(point.getX()-10, point.getY()-10, point.getX()+10, point.getY()+10)) {
			boolean found = false;
			for (Npc npc : npcs) {
				if (npc.getID() == n.getID() && npc.getX() == n.getX() && npc.getY() == n.getY()) {
					found = true;
					break;
				}
			}
			if(found) {
				npcs.remove(n);
				if(DataConversions.random(0, 100) <= 1) {
					Item i = new Item(dropID, n.getX(), n.getY(), 1, p);
					World.getWorld().sendWorldAnnouncement(p.getUsername() + " found the price - " + i.getDef().getName());
					World.getWorld().registerItem(i);
				}
			}
		}
	}

	@Override
	public boolean blockPlayerMageNpc(Player p, Npc n) {
		if(n.getID() == npcID && n.getLocation().inBounds(point.getX()-10, point.getY()-10, point.getX()+10, point.getY()+10)) {
			boolean found = false;
			for (Npc npc : npcs) {
				if (npc.getID() == n.getID() && npc.getX() == n.getX() && npc.getY() == n.getY()) {
					found = true;
					p.getActionSender().sendMessage("You cannot mage these Chickens!");
					break;
				}
			}
			return found;
		}
		return false;
	}

	@Override
	public boolean blockPlayerRangeNpc(Player p, Npc n) {
		if(n.getID() == npcID && n.getLocation().inBounds(point.getX()-10, point.getY()-10, point.getX()+10, point.getY()+10)) {
			boolean found = false;
			for (Npc npc : npcs) {
				if (npc.getID() == n.getID() && npc.getX() == n.getX() && npc.getY() == n.getY()) {
					found = true;
					p.getActionSender().sendMessage("You cannot range these Chickens!");
					break;
				}
			}
			return found;
		}
		return false;
	}
	


}
