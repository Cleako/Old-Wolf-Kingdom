package org.wolf_kingdom.gs.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;

import org.wolf_kingdom.gs.model.Player;
import org.wolf_kingdom.gs.model.World;
import org.wolf_kingdom.gs.model.snapshot.Activity;
import org.wolf_kingdom.gs.model.snapshot.Chatlog;
import org.wolf_kingdom.gs.model.snapshot.Snapshot;
import org.wolf_kingdom.gs.util.Logger;
import org.wolf_kingdom.ls.util.DataConversions;


public class Queries {
	
	
	/**
	 * Submits a report to the database
	 * 
	 * Chatlogs, snapshots, locations etc are generated inside this method.
	 * 
	 * @param from Players usernameHash (who reported)
	 * @param about Players usernameHash (who got reported)
	 * @param reason What type of a report is this
	 */
	public synchronized void submitRepot(long from, long about, byte reason, Player from2) {
		
		PreparedStatement insertNewReportRow = null;
		try {
			insertNewReportRow = World.getWorld().getDB().getConnection().prepareStatement("INSERT INTO `wk_reports` (`from`, `about`, `time`, `reason`, `snapshot_from`,`snapshot_about`,`chatlogs`, `from_x`, `from_y`, `about_x`, `about_y`) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
		} catch (SQLException e) {
			e.printStackTrace();
			Logger.error(e);
		}
		
		long time = System.currentTimeMillis()/1000;
		String f = DataConversions.hashToUsername(from);
		String a = DataConversions.hashToUsername(about);
		
		Player about2 = World.getWorld().getPlayer(about);
		int player2X;
		int player2Y;
		if(about2 == null) {
			player2X = 0;
			player2Y = 0;
		}
		else {
			player2X = about2.getX();
			player2Y = about2.getY();
		}
		StringBuilder snapshot_from = new StringBuilder();
		StringBuilder snapshot_about = new StringBuilder();
		
		StringBuilder chatlog = new StringBuilder();
		Iterator<Snapshot> i = World.getWorld().getSnapshots().descendingIterator();
		while (i.hasNext()) {
			Snapshot s = i.next();
			if(s instanceof Chatlog) { 
				Chatlog cl = (Chatlog)s;
				if (cl.getRecievers().contains(a) || cl.getOwner().equals(a)) {
					chatlog.append((cl.getTimestamp()/1000) + " <" + cl.getOwner() + "> " + cl.getMessage() + "\n");
				}
			}
			else if (s instanceof Activity) {
				Activity ac = (Activity)s;
				if(ac.getOwner().equals(f)) {
					snapshot_from.append((ac.getTimestamp()/1000) + " " +  ac.getActivity() + "\n");
				}
				else if(ac.getOwner().equals(a)) {
					snapshot_about.append((ac.getTimestamp()/1000) + " " +  ac.getActivity() + "\n");
				}
			}
		}
		try {
			insertNewReportRow.setLong(1, from);
			insertNewReportRow.setLong(2, about); 
			insertNewReportRow.setLong(3, time); 
			insertNewReportRow.setInt(4, reason);
			insertNewReportRow.setString(5, snapshot_from.toString());
			insertNewReportRow.setString(6, snapshot_about.toString());
			insertNewReportRow.setString(7, chatlog.toString());
			insertNewReportRow.setInt(8, from2.getX());
			insertNewReportRow.setInt(9, from2.getY());
			insertNewReportRow.setInt(10, player2X);
			insertNewReportRow.setInt(11, player2Y);
			insertNewReportRow.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			Logger.error(e);
		} 
	}
}
