package org.lupus_regnum.gs.phandler.client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.mina.common.IoSession;
import org.lupus_regnum.config.Constants;
import org.lupus_regnum.config.Formulae;
import org.lupus_regnum.gs.connection.Packet;
import org.lupus_regnum.gs.connection.RSCPacket;
import org.lupus_regnum.gs.event.FightEvent;
import org.lupus_regnum.gs.event.ObjectRemover;
import org.lupus_regnum.gs.event.WalkMobToMobEvent;
import org.lupus_regnum.gs.event.WalkToMobEvent;
import org.lupus_regnum.gs.event.WalkToPointEvent;
import org.lupus_regnum.gs.external.EntityHandler;
import org.lupus_regnum.gs.external.ItemSmeltingDef;
import org.lupus_regnum.gs.external.ReqOreDef;
import org.lupus_regnum.gs.external.SpellDef;
import org.lupus_regnum.gs.model.ActiveTile;
import org.lupus_regnum.gs.model.GameObject;
import org.lupus_regnum.gs.model.InvItem;
import org.lupus_regnum.gs.model.Item;
import org.lupus_regnum.gs.model.Mob;
import org.lupus_regnum.gs.model.Npc;
import org.lupus_regnum.gs.model.PathGenerator;
import org.lupus_regnum.gs.model.Player;
import org.lupus_regnum.gs.model.Projectile;
import org.lupus_regnum.gs.model.World;
import org.lupus_regnum.gs.model.snapshot.Activity;
import org.lupus_regnum.gs.phandler.PacketHandler;
import org.lupus_regnum.gs.plugins.PluginHandler;
import org.lupus_regnum.gs.states.Action;
import org.lupus_regnum.gs.tools.DataConversions;



public class SpellHandler implements PacketHandler {

    public void handlePacket(Packet p, IoSession session) throws Exception {
	    	
		Player player = (Player) session.getAttachment();
                    if ((player.isBusy() && !player.inCombat()) || player.isRanging()) {
                        return;
                    }
                    if (!canCast(player)) {
                        return;
                    }
                    int pID = ((RSCPacket) p).getID();
		
                    if (player.isDueling() && player.getDuelSetting(1)) {
                        player.getActionSender().sendMessage("Magic is disabled in this duel");
                        return;
                    }
                    player.resetAllExceptDueling();
                    int idx = p.readShort();
                    /*
                    * TODO: Change the idx >= 53 below to one more than highest spell ID.
                    * Remember, spell IDs are integers so they start at 0.
                    */
                    if (idx < 0 || idx >= 53) {
                        player.setSuspiciousPlayer(true);
                        return;
                    }
                    SpellDef spell = EntityHandler.getSpellDef(idx);
                    world.addEntryToSnapshots(new Activity(player.getUsername(), player.getUsername() + " tried to cast spell ("+49+"): " + player.getX() + "/" + player.getY()));
	
                    if (player.getCurStat(6) < spell.getReqLevel()) {
                        player.setSuspiciousPlayer(true);
                        player.getActionSender().sendMessage("Your magic ability is not high enough for this spell.");
                        player.resetPath();
                        return;
                    }
                    if (!Formulae.castSpell(spell, player.getCurStat(6), player.getMagicPoints())) {
                        player.getActionSender().sendMessage("The spell fails, you may try again in 20 seconds.");
                        player.getActionSender().sendSound("spellfail");
                        player.setSpellFail();
                        player.resetPath();
                        return;
                    }
                    switch (pID) {
			case 206: // Cast on self
			    if (player.isDueling()) {
				player.getActionSender().sendMessage("This type of spell cannot be used in a duel.");
                                    return;
			    }
			    if (spell.getSpellType() == 0) {
				handleTeleport(player, spell, idx);
				return;
			    }
			    handleGroundCast(player, spell, idx);
			    break;
			case 55: // Cast on player
			    if (spell.getSpellType() == 1 || spell.getSpellType() == 2) {
				Player affectedPlayer = world.getPlayer(p.readShort());
                                    if (affectedPlayer == null) { // This shouldn't happen
                                        player.resetPath();
					    return;
					}
					if (player.withinRange(affectedPlayer, 5)) {
					    player.resetPath();
					}
					if(PluginHandler.getPluginHandler().blockDefaultAction("PlayerMage", new Object[] { player, affectedPlayer, idx })) {
                                            break;
					}
					handleMobCast(player, affectedPlayer, idx);
			    }
			    break;
			case 71: // Cast on npc
			    if (player.isDueling()) {
			    	return;
			    }
			    if (spell.getSpellType() == 2) {
				Npc affectedNpc = world.getNpc(p.readShort());
				if (affectedNpc == null) { // This shouldn't happen
				    player.resetPath();
				    return;
				}
			
				if (affectedNpc.getID() == 35) {
				    player.getActionSender().sendMessage("Delrith can not be attacked without the Silverlight sword");
                                    return;
				}
				if (player.withinRange(affectedNpc, 5)) {
				    player.resetPath();
				}
				if(PluginHandler.getPluginHandler().blockDefaultAction("PlayerMageNpc", new Object[] { player, affectedNpc })) {
                                    return;
				}
				handleMobCast(player, affectedNpc, idx);
			    }
			    break;
			case 51: // Cast on inventory item (was 49)
			    if (player.isDueling()) {
				player.getActionSender().sendMessage("This type of spell cannot be used in a duel.");
				return;
			    }
			    if (spell.getSpellType() == 3) {
				InvItem item = player.getInventory().get(p.readShort());
				if (item == null) { // This shoudln't happen
				    player.resetPath();
				    return;
				}
				handleInvItemCast(player, spell, idx, item);
			    }
			    break;
			case 67: // Cast on door - type 4
			    if (player.isDueling()) {
				player.getActionSender().sendMessage("This type of spell cannot be used in a duel.");
				return;
			    }
			    player.getActionSender().sendMessage("@or1@This type of spell is not yet implemented.");
			    break;
			case 17: // Cast on game object - type 5
			    if (player.isDueling()) {
				player.getActionSender().sendMessage("This type of spell cannot be used in a duel.");
				return;
			    }
			    player.getActionSender().sendMessage("@or1@This type of spell is not yet implemented.");
			    break;
			case 104: // Cast on ground item
			    if (player.isDueling()) {
				player.getActionSender().sendMessage("This type of spell cannot be used in a duel.");
				return;
			    }
			    ActiveTile t = world.getTile(p.readShort(), p.readShort());
			    int itemId = p.readShort();
			    Item affectedItem = null;
			    for (Item i : t.getItems()) {
				if (i.getID() == itemId && i.visibleTo(player)) {
				    affectedItem = i;
				    break;
				}
			    }
			    t.cleanItself();
			    if (affectedItem == null) { 
			    	return;
			    }
			    handleItemCast(player, spell, idx, affectedItem);
			    break;
			case 232: 
			    if (player.isDueling()) {
				player.getActionSender().sendMessage("This type of spell cannot be used in a duel.");
				return;
			    }
			    handleGroundCast(player, spell, idx);
			    break;
			}
                        player.getActionSender().sendInventory();
                        player.getActionSender().sendStat(6);
                        return;
    }
	
    private static TreeMap<Integer, InvItem[]> staffs = new TreeMap<Integer, InvItem[]>();

    /**
     * World instance
     */
    public static final World world = World.getWorld();

    static {
	staffs.put(31, new InvItem[] { new InvItem(197), new InvItem(615), new InvItem(682) }); // Fire-Rune
	staffs.put(32, new InvItem[] { new InvItem(102), new InvItem(616), new InvItem(683) }); // Water-Rune
	staffs.put(33, new InvItem[] { new InvItem(101), new InvItem(617), new InvItem(684) }); // Air-Rune
	staffs.put(34, new InvItem[] { new InvItem(103), new InvItem(618), new InvItem(685) }); // Earth-Rune
    }

    private static boolean canCast(Player player) {
        if((player.isPMod() || player.isMod()) && !player.isAdmin()) {
            return false;
        }
	if (!player.castTimer()) {
	    player.getActionSender().sendMessage("You must wait another " + player.getSpellWait() + " seconds to cast another spell.");
	    player.resetPath();
	    return false;
	}
	return true;
    }

    private static boolean checkAndRemoveRunes(Player player, SpellDef spell) {
	for (Entry<Integer, Integer> e : spell.getRunesRequired()) {
	    boolean skipRune = false;
	    for (InvItem staff : getStaffs(e.getKey())) {
		if (player.getInventory().contains(staff)) {
		    for (InvItem item : player.getInventory().getItems()) {
			if (item.equals(staff) && item.isWielded()) {
			    skipRune = true;
			    break;
			}
		    }
		}
            }
            if (skipRune) {
                continue;
            }
            if (player.getInventory().countId(((Integer) e.getKey()).intValue()) < ((Integer) e.getValue()).intValue()) {
                player.setSuspiciousPlayer(true);
                player.getActionSender().sendMessage("You don't have all the reagents you need for this spell");
                return false;
            }
	}
	for (Entry<Integer, Integer> e : spell.getRunesRequired()) {
            boolean skipRune = false;
	    for (InvItem staff : getStaffs(e.getKey())) {
		if (player.getInventory().contains(staff)) {
		    for (InvItem item : player.getInventory().getItems()) {
                        if (item.equals(staff) && item.isWielded()) {
			    skipRune = true;
			    break;
			}
		   }
		}
            }
	    if (skipRune) {
	    	continue;
	    }
	    player.getInventory().remove(((Integer) e.getKey()).intValue(), ((Integer) e.getValue()).intValue());
	}
	return true;
    }

    private static InvItem[] getStaffs(int runeID) {
	InvItem[] items = staffs.get(runeID);
	if (items == null) {
            return new InvItem[0];
	}
	return items;
    }

    private void finalizeSpell(Player player, SpellDef spell) {
	player.lastCast = System.currentTimeMillis();
	player.getActionSender().sendSound("spellok");
	player.getActionSender().sendMessage("Cast spell successfully");
	player.incExp(6, spell.getExp(), true);
	player.setCastTimer();
    }
    /**
     * Handles npc chasing player.
     * @param owner
     * @param player
     * @param affectedMob
     */
    private void chasePlayer(Player owner, final Player player, Mob affectedMob) {
    	if (affectedMob instanceof Npc) {
	    final Npc npc = (Npc) affectedMob;
            if (npc.isBusy() || npc.getChasing() != null)
		return;
                npc.resetPath();
                npc.setChasing(player);

                // Radius is 0 to prevent wallhacking by
                // NPCs. Easiest method I can come up with
                // for now.
                World.getWorld().getDelayedEventHandler().add(new WalkMobToMobEvent(affectedMob, owner, 0) {
		public void arrived() {
		    if (affectedMob.isBusy() || owner.isBusy()) {
			npc.setChasing(null);
			return;
		    }
		    if (affectedMob.inCombat() || owner.inCombat()) {
			npc.setChasing(null);
			return;
		    }
                    npc.resetPath();
                    player.resetAll();
		    player.resetPath();
		    player.setBusy(true);
		    player.setStatus(Action.FIGHTING_MOB);
		    player.getActionSender().sendSound("underattack");
		    player.getActionSender().sendMessage("You are under attack!");
                    npc.setLocation(player.getLocation(), true);
		    for (Player p : npc.getViewArea().getPlayersSectorA())
		    	p.removeWatchedNpc(npc);
                    for (Player p : npc.getViewArea().getPlayersSectorB())
		    	p.removeWatchedNpc(npc);
                    for (Player p : npc.getViewArea().getPlayersSectorC())
		    	p.removeWatchedNpc(npc);
                    for (Player p : npc.getViewArea().getPlayersSectorD())
		    	p.removeWatchedNpc(npc);
                        player.setSprite(9);
                        player.setOpponent(npc);
                        player.setCombatTimer();
                        npc.setBusy(true);
                        npc.setSprite(8);
                        npc.setOpponent(player);
                        npc.setCombatTimer();
                        npc.setChasing(null);
			FightEvent fighting = new FightEvent(player, npc, true);
			fighting.setLastRun(0);
			world.getDelayedEventHandler().add(fighting);
                }
                
	
		public void failed() {
		    npc.setChasing(null);
		}
            });
	}
    }

    public void godSpellObject(Mob affectedMob, int spell) {
            switch (spell) {
                case 33:
                    GameObject guthix = new GameObject(affectedMob.getLocation(), 1142, 0, 0);
                    world.registerGameObject(guthix);
                    World.getWorld().getDelayedEventHandler().add(new ObjectRemover(guthix, 500));
                    break;
                case 34:
                    GameObject sara = new GameObject(affectedMob.getLocation(), 1031, 0, 0);
                    world.registerGameObject(sara);
                    World.getWorld().getDelayedEventHandler().add(new ObjectRemover(sara, 500));
                    break;
                case 35:
                    GameObject zammy = new GameObject(affectedMob.getLocation(), 1036, 0, 0);
                    world.registerGameObject(zammy);
                    World.getWorld().getDelayedEventHandler().add(new ObjectRemover(zammy, 500));
                    break;
                case 47:
                    GameObject charge = new GameObject(affectedMob.getLocation(), 1147, 0, 0);
                    world.registerGameObject(charge);
                    World.getWorld().getDelayedEventHandler().add(new ObjectRemover(charge, 500));
                    break;
            }
    }

    private void handleGroundCast(Player player, SpellDef spell, int id) {
        switch (id) {
            case 7: // Bones to bananas
                if (!checkAndRemoveRunes(player, spell)) {
                    return;
		}
		Iterator<InvItem> inventory = player.getInventory().iterator();
		int boneCount = 0;
		while (inventory.hasNext()) {
                    InvItem i = inventory.next();
                    if (i.getID() == 20) {
                        inventory.remove();
			boneCount++;
                    }
		}
		for (int i = 0; i < boneCount; i++) {
                    player.getInventory().add(new InvItem(249));
		}
		finalizeSpell(player, spell);
		break;
            case 48: // Charge
                if(world.getTile(player.getLocation()).hasGameObject()) {
                    player.getActionSender().sendMessage("You cannot charge here, please move to a different area.");
                    return;
		}
		if(!checkAndRemoveRunes(player, spell)) {
                    return;
		}
		player.getActionSender().sendMessage("@gre@You feel charged with magical power.");
		player.setCharged();
		godSpellObject(player, 47);
		finalizeSpell(player, spell);
		return;
                       
            case 49: // Mystenhaven portal
                //int myWildLvl =  player.getLocation().wildernessLevel();
                if(world.getTile(player.getLocation()).hasGameObject()) {
                    player.getActionSender().sendMessage("You may not cast this spell here, please move to a different area.");
                    return;
                }
                if(!checkAndRemoveRunes(player, spell))  {
                    return;
                }
                //if(myWildLvl > 0) {
                    GameObject portal = new GameObject(player.getLocation(), 1155, 0, 0); //spawns a portal
                    world.registerGameObject(portal);
                    World.getWorld().getDelayedEventHandler().add(new ObjectRemover(portal, 10000)); //vanishes after 10 seconds
                    player.resetPath();
                    finalizeSpell(player, spell);
                    player.getActionSender().sendMessage("@gre@You summon a portal to Mystenhaven.");
                /*} 
                else {
                    player.getActionSender().sendMessage("You may only cast this spell while located in the badlands!");
                }*/
                break;
        }
    }

    private void handleInvItemCast(Player player, SpellDef spell, int id, InvItem affectedItem) {
        switch (id) {
            case 3: // Enchant lvl-1 Sapphire amulet
                if (affectedItem.getID() == 302) {
                    if (!checkAndRemoveRunes(player, spell)) {
                        return;
                    }
                    player.getInventory().remove(affectedItem);
                    player.getInventory().add(new InvItem(314));
                    finalizeSpell(player, spell);
		}
		else {
                    player.getActionSender().sendMessage("This spell cannot be used on this kind of item");
		}
		break;
            case 10: // Low level alchemy
                if (affectedItem.getID() == 10) {
                    player.getActionSender().sendMessage("You cannot alchemy that");
                    return;
		}
		if (!checkAndRemoveRunes(player, spell)) {
                    return;
		}
		if (player.getInventory().remove(affectedItem) > 1) {
                    int value = (int) (affectedItem.getDef().getBasePrice() * 0.4D * affectedItem.getAmount());
                    player.getInventory().add(new InvItem(10, value)); // 40%
		}
		finalizeSpell(player, spell);
		break;
            case 13: // Enchant lvl-2 emerald amulet
                if (affectedItem.getID() == 303) {
                    if (!checkAndRemoveRunes(player, spell)) {
                        return;
                    }
                    player.getInventory().remove(affectedItem);
                    player.getInventory().add(new InvItem(315));
                    finalizeSpell(player, spell);
                } 
		else {
                    player.getActionSender().sendMessage("This spell cannot be used on this kind of item");
		}
		break;
            case 21: // Superheat item
                ItemSmeltingDef smeltingDef = affectedItem.getSmeltingDef();
                if (smeltingDef == null) {
                    player.getActionSender().sendMessage("This spell cannot be used on this kind of item");
                    return;
                }
                for (ReqOreDef reqOre : smeltingDef.getReqOres()) {
                    if (player.getInventory().countId(reqOre.getId()) < reqOre.getAmount()) {
                        if (affectedItem.getID() == 151) {
                            smeltingDef = EntityHandler.getItemSmeltingDef(9999);
                            break;
                        }
                        player.getActionSender().sendMessage("You need " + reqOre.getAmount() + " " + EntityHandler.getItemDef(reqOre.getId()).getName() + " to smelt a " + affectedItem.getDef().getName() + ".");
                        return;
                        }
                    }
                    if (player.getCurStat(13) < smeltingDef.getReqLevel()) {
			player.getActionSender().sendMessage("You need a smithing level of " + smeltingDef.getReqLevel() + " to smelt this.");
			return;
                    }
                    if (!checkAndRemoveRunes(player, spell)) {
                        return;
                    }
                    InvItem bar = new InvItem(smeltingDef.getBarId());
                        if (player.getInventory().remove(affectedItem) > -1) {
                            for (ReqOreDef reqOre : smeltingDef.getReqOres()) {
                                for (int i = 0; i < reqOre.getAmount(); i++) {
                                    player.getInventory().remove(new InvItem(reqOre.getId()));
                                }
                            }
                            player.getActionSender().sendMessage("You make a " + bar.getDef().getName() + ".");
                            player.getInventory().add(bar);
                            player.incExp(13, smeltingDef.getExp(), true);
                            player.getActionSender().sendStat(13);
                            player.getActionSender().sendInventory();
                        }
                finalizeSpell(player, spell);
                break;
            case 24: // Enchant lvl-3 ruby amulet
                if (affectedItem.getID() == 304) {
		if (!checkAndRemoveRunes(player, spell)) {
                    return;
		}
		player.getInventory().remove(affectedItem);
		player.getInventory().add(new InvItem(316));
		finalizeSpell(player, spell);
                }
                else {
                    player.getActionSender().sendMessage("This spell cannot be used on this kind of item");
                }
                break;
            case 28: // High level alchemy
                if (affectedItem.getID() == 10) {
                    player.getActionSender().sendMessage("You cannot alchemy that");
                    return;
                }
                if (!checkAndRemoveRunes(player, spell)) {
                    return;
                }
                if (player.getInventory().remove(affectedItem) > -1) {
                    int value = (int) (affectedItem.getDef().getBasePrice() * 0.6D * affectedItem.getAmount());
                    player.getInventory().add(new InvItem(10, value)); // 60%
                }
                finalizeSpell(player, spell);
                break;
            case 30: // Enchant lvl-4 diamond amulet
                if (affectedItem.getID() == 305) {
                    if (!checkAndRemoveRunes(player, spell)) {
                        return;
                    }
                    player.getInventory().remove(affectedItem);
                    player.getInventory().add(new InvItem(317));
                    finalizeSpell(player, spell);
		} 
                else {
                    player.getActionSender().sendMessage("This spell cannot be used on this kind of item");
                }
                break;
            case 43: // Enchant lvl-5 dragonstone amulet
                if(affectedItem.getID() == 610) { 
                    if(!checkAndRemoveRunes(player, spell)) { 
                        return; 
                    }
                    player.getInventory().remove(affectedItem);
                    player.getInventory().add(new InvItem(522));
                    finalizeSpell(player, spell); 
                }
                else {
                    player.getActionSender().sendMessage("This spell cannot be used on this kind of item"); 
		}
                break;
        }
	if (affectedItem.isWielded()) {
            player.getActionSender().sendSound("click");
            affectedItem.setWield(false);
            player.updateWornItems(affectedItem.getWieldableDef().getWieldPos(), player.getPlayerAppearance().getSprite(affectedItem.getWieldableDef().getWieldPos()));
            player.getActionSender().sendEquipmentStats();
	}
    }

    private void handleItemCast(Player player, final SpellDef spell, final int id, final Item affectedItem) {
	player.setStatus(Action.CASTING_GITEM);
    	World.getWorld().getDelayedEventHandler().add(new WalkToPointEvent(player, affectedItem.getLocation(), 5, true) {
            public void arrived() {
                owner.resetPath();
                ActiveTile tile = world.getTile(location);
                if (!canCast(owner) || !tile.hasItem(affectedItem) || owner.getStatus() != Action.CASTING_GITEM || affectedItem.isRemoved()) {
                    return;
                }
                owner.resetAllExceptDueling();
                switch (id) {
                    case 16: // Telekinetic grab
                        if (affectedItem.getID() == 575) {
                            owner.getActionSender().sendMessage("You may not telegrab this item");
                            return;
                        }
                        if (affectedItem.getLocation().inBounds(490, 464, 500, 471) || affectedItem.getLocation().inBounds(490, 1408, 500, 1415)) {
                            owner.getActionSender().sendMessage("Telekinetic grab cannot be used in here");
                            return;
                        }
                        if (affectedItem.getLocation().inBounds(97, 1428, 106, 1440) || affectedItem.getLocation().inBounds(490, 1408, 500, 1415)) {
                            owner.getActionSender().sendMessage("Telekinetic grab cannot be used in here");
                            return;
                        }
                        if(DataConversions.inArray(Formulae.telegrabBlocked, affectedItem.getID())) {
                            owner.getActionSender().sendMessage("This item cannot be telegrabbed!");
                            return;
                        }
                        if (!checkAndRemoveRunes(owner, spell)) {
                            return;
                        }
                        owner.getActionSender().sendTeleBubble(location.getX(), location.getY(), true);
                        for (Object o : owner.getWatchedPlayers().getAllEntities()) {
                            Player p = ((Player) o);
                            p.getActionSender().sendTeleBubble(location.getX(), location.getY(), true);
                        }
                        world.unregisterItem(affectedItem);
                        finalizeSpell(owner, spell);
                        owner.getInventory().add(new InvItem(affectedItem.getID(), affectedItem.getAmount()));
                        break;
                    }
                    owner.getActionSender().sendInventory();
                    owner.getActionSender().sendStat(6);
            }
	});
    }

    private void handleMobCast(final Player player, Mob affectedMob, final int spellID) {
	if (player.isDueling() && affectedMob instanceof Player) {
	    Player aff = (Player) affectedMob;
	    if (!player.getWishToDuel().getUsername().toLowerCase().equals(aff.getUsername().toLowerCase()))
	    	return;
            }
            if (!new PathGenerator(player.getX(), player.getY(), affectedMob.getX(), affectedMob.getY()).isValid()) {
                player.getActionSender().sendMessage("I can't get a clear shot from here");
                player.resetPath();
                return;
            }
            if (affectedMob instanceof Player) {
                Player other = (Player) affectedMob;
                if (player.getLocation().inWilderness() && System.currentTimeMillis() - other.getLastRun() < 1000) {
            		player.resetPath();
            		return;
                }
            }
            if (player.getLocation().inWilderness() && System.currentTimeMillis() - player.getLastRun() < 3000) {
                player.resetPath();
                return;
            }
            player.setFollowing(affectedMob);
            player.setStatus(Action.CASTING_MOB);
            World.getWorld().getDelayedEventHandler().add(new WalkToMobEvent(player, affectedMob, 5) {
                public void arrived() {
                    if (!new PathGenerator(owner.getX(), owner.getY(), affectedMob.getX(), affectedMob.getY()).isValid()) {
                        owner.getActionSender().sendMessage("I can't get a clear shot from here");
                        owner.resetPath();
                        return;
                    }
                    player.resetFollowing();
                    owner.resetPath();
                    SpellDef spell = EntityHandler.getSpellDef(spellID);
                    if (!canCast(owner) || affectedMob.getHits() <= 0 || !owner.checkAttack(affectedMob, true) || owner.getStatus() != Action.CASTING_MOB) {
                        player.resetPath();
			return;
                    }
                    owner.resetAllExceptDueling();
                    switch (spellID) {
                    	case 1: // Confuse
			case 5: // Weaken
			case 9: // Curse
                            if (affectedMob instanceof Npc) {
                                Npc np = (Npc) affectedMob;
				if (spellID == 1) {
                                    if (np.confused) {
                                        owner.getActionSender().sendMessage("Your oponent is already confused");
					return;
                                    }
				}
				if (spellID == 5) {
				    if (np.weakend) {
					owner.getActionSender().sendMessage("Your oponent is already weakend");
					return;
				    }
				}
				if (spellID == 9) {
				    if (np.cursed) {
					owner.getActionSender().sendMessage("Your oponent is already cursed");
					return;
				    }
				}
                            }
                            if (affectedMob instanceof Player && !owner.isDueling()) {
                            	Player affectedPlayer = (Player) affectedMob;
				owner.setSkulledOn(affectedPlayer);
			    }
			    int stat = -1;
			    if (spellID == 1)
			    	stat = 0;
			    else if (spellID == 5)
			    	stat = 2;
			    else if (spellID == 9)
			    	stat = 1;
	
			    int statLv = -1;
			    if (affectedMob instanceof Player) {
				Player affectedPlayer = (Player) affectedMob;
				statLv = affectedPlayer.getCurStat(stat);
			    }
			    else if (affectedMob instanceof Npc) {
				Npc n = (Npc) affectedMob;
				if (stat == 0)
				    statLv = n.getAttack();
				else if (stat == 1)
				    statLv = n.getDefense();
				else if (stat == 2)
				    statLv = n.getStrength();
			    }
			    if (statLv == -1 || stat == -1)
			    	return;
	
			    if (affectedMob instanceof Player) {
				Player affectedPlayer = (Player) affectedMob;
				if (affectedPlayer.getCurStat(stat) < affectedPlayer.getMaxStat(stat) - (affectedPlayer.getMaxStat(stat) / 20)) {
				    owner.getActionSender().sendMessage("Your opponent is already stunned");
				    return;
				} 
				else {
				    affectedPlayer.setCurStat(stat, affectedPlayer.getCurStat(stat) - (affectedPlayer.getMaxStat(stat) / 20));
				    affectedPlayer.getActionSender().sendStats();
				    affectedPlayer.getActionSender().sendMessage(owner.getUsername() + " has stunned you");
				}
			    } 
			    else if (affectedMob instanceof Npc) {
				Npc n = (Npc) affectedMob;
				if (spellID == 1)
				    n.confused = true;
				else if (spellID == 5)
				    n.weakend = true;
				else if (spellID == 9)
				    n.cursed = true;
			    }
                            if (!checkAndRemoveRunes(owner, spell)) {
                                return;
			    }
			    	
			    Projectile projectilez = new Projectile(owner, affectedMob, 1);
			    ArrayList<Player> playersToInformm = new ArrayList<Player>();
			    playersToInformm.addAll(owner.getViewArea().getPlayersSectorA());
                            playersToInformm.addAll(owner.getViewArea().getPlayersSectorB());
                            playersToInformm.addAll(owner.getViewArea().getPlayersSectorC());
                            playersToInformm.addAll(owner.getViewArea().getPlayersSectorD());
			    playersToInformm.addAll(affectedMob.getViewArea().getPlayersSectorA());
                            playersToInformm.addAll(affectedMob.getViewArea().getPlayersSectorB());
                            playersToInformm.addAll(affectedMob.getViewArea().getPlayersSectorC());
                            playersToInformm.addAll(affectedMob.getViewArea().getPlayersSectorD());
			    for (Player p : playersToInformm) {
				p.informOfProjectile(projectilez);
				p.informOfModifiedHits(affectedMob);
                            }
		
			    finalizeSpell(owner, spell);
			    chasePlayer(owner, player, affectedMob);
			    owner.getActionSender().sendInventory();
			    owner.getActionSender().sendStat(6);
			    
			    return;
			case 19: // Crumble undead
	
			    if (affectedMob instanceof Player) {
                                owner.getActionSender().sendMessage("You can not use this spell on a Player");
                                return;
			    }
			    if (!checkAndRemoveRunes(owner, spell)) {
                                return;
			    }
			    Npc n = (Npc) affectedMob;
			    int damaga = Formulae.Rand(1, 5);
			    
			    if (n.undead) {
                                damaga = Formulae.Rand(3, Constants.GameServer.CRUMBLE_UNDEAD_MAX);
			    }
	
			    if (!n.undead) {
                                owner.getActionSender().sendMessage("You must use this spell on undead monsters only");
                                return;
			    }
			    if (Formulae.Rand(0, 8) == 2)
                                damaga = 0;
	
			    Projectile projectilee = new Projectile(owner, affectedMob, 1);
			    
			    affectedMob.setLastDamage(damaga);
			    int newp = affectedMob.getHits() - damaga;
			    affectedMob.setHits(newp);
			    
			    ArrayList<Player> playersToInforms = new ArrayList<Player>();
			    playersToInforms.addAll(owner.getViewArea().getPlayersSectorA());
                            playersToInforms.addAll(owner.getViewArea().getPlayersSectorB());
                            playersToInforms.addAll(owner.getViewArea().getPlayersSectorC());
                            playersToInforms.addAll(owner.getViewArea().getPlayersSectorD());
			    playersToInforms.addAll(affectedMob.getViewArea().getPlayersSectorA());
                            playersToInforms.addAll(affectedMob.getViewArea().getPlayersSectorB());
                            playersToInforms.addAll(affectedMob.getViewArea().getPlayersSectorC());
                            playersToInforms.addAll(affectedMob.getViewArea().getPlayersSectorD());
			    
			    for (Player p : playersToInforms) {
				p.informOfProjectile(projectilee);
				p.informOfModifiedHits(affectedMob);
			    }
			    if (newp <= 0) {
			    	affectedMob.killedBy(owner, owner.isDueling());
                            }
			    finalizeSpell(owner, spell);
			    chasePlayer(owner, player, affectedMob);
			    owner.getActionSender().sendInventory();
			    owner.getActionSender().sendStat(6);
			    return;
			case 42: // vulnerability
			case 45: // Enfeeble
                        case 50: //  Inhibit Cast spell
                            if(!checkAndRemoveRunes(owner, spell)) {
                                return;
                            }
                            if(affectedMob instanceof Player &&  !owner.isDueling()) {
                                Player affectedPlayer = (Player)affectedMob;
                                owner.setSkulledOn(affectedPlayer);
                            }
                            if(affectedMob instanceof Player) {
                                Player affectedPlayer = (Player)affectedMob;
                                affectedPlayer.getActionSender().sendMessage("You suddenly feel depleted of magic energy.");
                                owner.getActionSender().sendMessage("You suddenly feel depleted of magic energy.");
                                affectedPlayer.getActionSender().sendSound("spellfail");
                                owner.getActionSender().sendSound("spellfail");
                                affectedPlayer.setSpellFail();
                                owner.setSpellFail();
                                affectedPlayer.resetPath();
                                owner.resetPath();
                                Projectile projectile = new Projectile(owner,  affectedPlayer, 1);
                                affectedPlayer.getActionSender().sendMessage(owner.getUsername() + " has inhibited your spell casting ability temporarily.");
                                owner.getActionSender().sendMessage("The spell has inhibited your own spell casting ability temporarily.");
                                ArrayList<Player> playersToInform =  new ArrayList<Player>();
                                playersToInform.addAll(owner.getViewArea().getPlayersSectorA());
                                playersToInform.addAll(owner.getViewArea().getPlayersSectorB());
                                playersToInform.addAll(owner.getViewArea().getPlayersSectorC());
                                playersToInform.addAll(owner.getViewArea().getPlayersSectorD());
                                playersToInform.addAll(affectedMob.getViewArea().getPlayersSectorA());
                                playersToInform.addAll(affectedMob.getViewArea().getPlayersSectorB());
                                playersToInform.addAll(affectedMob.getViewArea().getPlayersSectorC());
                                playersToInform.addAll(affectedMob.getViewArea().getPlayersSectorD());
                                for(Player p : playersToInform) {
                                    p.informOfProjectile(projectile);
                                }
                                break;
                            }
			case 47: // Stun
                            /*
                             * TODO: implement the sleep(int) method so that the player may be stopped temporarily.
                             */
                            player.resetPath();
			    player.setBusy(true);
			    player.getActionSender().sendSound("underattack");
                            //sleep(5000);
                            player.setBusy(false);
			    break;
			case 25:
			    if(owner.getLocation().inWilderness()) {
				owner.getActionSender().sendMessage("Can not use this spell in wilderness");
				return;
			    }
			    if(affectedMob instanceof Npc) {
				if(((Npc)affectedMob).getID() == 477) {
				    player.getActionSender().sendMessage("The dragon seems immune to this spell");
				    return;
				}
			    }
			    boolean charged = false;
			    for(InvItem cape : owner.getInventory().getItems()) {
			    	if(cape.getID() == 1000 && cape.isWielded()) {
                                    charged= true;
			    	}
			    }
			    if(charged){
			    	if(!owner.isCharged()) {
                                    owner.getActionSender().sendMessage("@red@You are not charged!");
			    	}
				if(!checkAndRemoveRunes(owner, spell)) {
				    return;
				}
				if(affectedMob instanceof Player && !owner.isDueling()) {
				    Player affectedPlayer = (Player)affectedMob;
				    owner.setSkulledOn(affectedPlayer);
				}
				int damag = Formulae.calcSpellHit(20, owner.getMagicPoints());
				if(affectedMob instanceof Player) {
				    Player affectedPlayer = (Player)affectedMob;
				    affectedPlayer.getActionSender().sendMessage(owner.getUsername() + " is shooting at you!");
				}
				Projectile projectil = new Projectile(owner, affectedMob, 1);
				affectedMob.setLastDamage(damag);
				int newhp = affectedMob.getHits() - damag;
				affectedMob.setHits(newhp);
				ArrayList<Player> playersToInfor = new ArrayList<Player>();
				playersToInfor.addAll(owner.getViewArea().getPlayersSectorA());
                                playersToInfor.addAll(owner.getViewArea().getPlayersSectorB());
                                playersToInfor.addAll(owner.getViewArea().getPlayersSectorC());
                                playersToInfor.addAll(owner.getViewArea().getPlayersSectorD());
				playersToInfor.addAll(affectedMob.getViewArea().getPlayersSectorA());
                                playersToInfor.addAll(affectedMob.getViewArea().getPlayersSectorB());
                                playersToInfor.addAll(affectedMob.getViewArea().getPlayersSectorC());
                                playersToInfor.addAll(affectedMob.getViewArea().getPlayersSectorD());
				for(Player p : playersToInfor) {
				    p.informOfProjectile(projectil);
				    p.informOfModifiedHits(affectedMob);
				}
				if(affectedMob instanceof Player) {
				    Player affectedPlayer = (Player)affectedMob;
				    affectedPlayer.getActionSender().sendStat(3);
				}
				else if(affectedMob instanceof Npc) { 
                                    Npc affectedNpc = (Npc)affectedMob;
                                    affectedNpc.addMageDamage(owner, damag);
				}
				if(newhp <= 0) {
				    affectedMob.killedBy(owner, owner.isDueling());
				}
				owner.getActionSender().sendInventory();
				owner.getActionSender().sendStat(6);
				finalizeSpell(owner, spell);
				break;
                            }
			    else {
                                owner.getActionSender().sendMessage("You need to be wearing the Iban Staff to cast this spell!");
                                return;
			    }
			case 33:
			    if(owner.getLocation().inWilderness()) {
				owner.getActionSender().sendMessage("Can not use this spell in wilderness");
				return;
			    }
			    boolean hasCape = false;
				for(InvItem cape : owner.getInventory().getItems()) {
                                    if(cape.getID() == 1217 && cape.isWielded()) {
                                        hasCape=true;
                                    }
                                }
			    if(hasCape) {
				if(!owner.isCharged()) {
				    owner.getActionSender().sendMessage("@red@You are not charged!");
				}	
				if(!checkAndRemoveRunes(owner, spell)) {
				    return;
				}
				if(affectedMob instanceof Player && !owner.isDueling()) {
				    Player affectedPlayer = (Player)affectedMob;
				    owner.setSkulledOn(affectedPlayer);
				}
				int damag = Formulae.calcGodSpells(owner, affectedMob);
				if(affectedMob instanceof Player) {
				    Player affectedPlayer = (Player)affectedMob;
				    affectedPlayer.getActionSender().sendMessage(owner.getUsername() + " is shooting at you!");
				}
				else if(affectedMob instanceof Npc) { 
                                    Npc affectedNpc = (Npc)affectedMob;
                                    affectedNpc.addMageDamage(owner, damag);
				}
				Projectile projectil = new Projectile(owner, affectedMob, 1);
				godSpellObject(affectedMob, 33);
				affectedMob.setLastDamage(damag);
				int newhp = affectedMob.getHits() - damag;
				affectedMob.setHits(newhp);
				ArrayList<Player> playersToInfor = new ArrayList<Player>();
				playersToInfor.addAll(owner.getViewArea().getPlayersSectorA());
                                playersToInfor.addAll(owner.getViewArea().getPlayersSectorB());
                                playersToInfor.addAll(owner.getViewArea().getPlayersSectorC());
                                playersToInfor.addAll(owner.getViewArea().getPlayersSectorD());
				playersToInfor.addAll(affectedMob.getViewArea().getPlayersSectorA());
                                playersToInfor.addAll(affectedMob.getViewArea().getPlayersSectorB());
                                playersToInfor.addAll(affectedMob.getViewArea().getPlayersSectorC());
                                playersToInfor.addAll(affectedMob.getViewArea().getPlayersSectorD());
				for(Player p : playersToInfor) {
				    p.informOfProjectile(projectil);
				    p.informOfModifiedHits(affectedMob);
				}
				if(affectedMob instanceof Player) {
				    Player affectedPlayer = (Player)affectedMob;
				    affectedPlayer.getActionSender().sendStat(3);
				}
				if(newhp <= 0) {
				    affectedMob.killedBy(owner, owner.isDueling());
				}
				owner.getActionSender().sendInventory();
                                owner.getActionSender().sendStat(6);
				finalizeSpell(owner, spell);
				break;
			    }
			    else{
			    	owner.getActionSender().sendMessage("You need to be wearing the Staff of Guthix to cast this spell!");
			    	return;
			    	}
			case 34:
			    if(owner.getLocation().inWilderness()) {
				owner.getActionSender().sendMessage("Can not use this spell in wilderness");
				return;
			    }
			    boolean saradominCape = false;
                            for(InvItem cape : owner.getInventory().getItems()) {
                                if(cape.getID() == 1218 && cape.isWielded()) { 
                                    saradominCape = true; 
                                }
                            }
			    if(saradominCape) {
			    	if(!owner.isCharged()){
                                    owner.getActionSender().sendMessage("@red@You are not charged!");
                                }	
				if(!checkAndRemoveRunes(owner, spell)) {
				    return;
				}
				if(affectedMob instanceof Player && !owner.isDueling()) {
				    Player affectedPlayer = (Player)affectedMob;
				    owner.setSkulledOn(affectedPlayer);
				}
				int damag = Formulae.calcGodSpells(owner, affectedMob);
				if(affectedMob instanceof Player) {
				    Player affectedPlayer = (Player)affectedMob;
				    affectedPlayer.getActionSender().sendMessage(owner.getUsername() + " is shooting at you!");
				}
				else if(affectedMob instanceof Npc) { 
                                    Npc affectedNpc = (Npc)affectedMob;
                                    affectedNpc.addMageDamage(owner, damag);
				}
				Projectile projectil = new Projectile(owner, affectedMob, 1);
				godSpellObject(affectedMob, 34);
				affectedMob.setLastDamage(damag);
				int newhp = affectedMob.getHits() - damag;
				affectedMob.setHits(newhp);
				ArrayList<Player> playersToInfor = new ArrayList<Player>();
				playersToInfor.addAll(owner.getViewArea().getPlayersSectorA());
                                playersToInfor.addAll(owner.getViewArea().getPlayersSectorB());
                                playersToInfor.addAll(owner.getViewArea().getPlayersSectorC());
                                playersToInfor.addAll(owner.getViewArea().getPlayersSectorD());
				playersToInfor.addAll(affectedMob.getViewArea().getPlayersSectorA());
                                playersToInfor.addAll(affectedMob.getViewArea().getPlayersSectorB());
                                playersToInfor.addAll(affectedMob.getViewArea().getPlayersSectorC());
                                playersToInfor.addAll(affectedMob.getViewArea().getPlayersSectorD());
				for(Player p : playersToInfor) {
				    p.informOfProjectile(projectil);
				    p.informOfModifiedHits(affectedMob);
				}
				if(affectedMob instanceof Player) {
				    Player affectedPlayer = (Player)affectedMob;
				    affectedPlayer.getActionSender().sendStat(3);
				}
				if(newhp <= 0) {
				    affectedMob.killedBy(owner, owner.isDueling());
				}
				owner.getActionSender().sendInventory();
				owner.getActionSender().sendStat(6);
				finalizeSpell(owner, spell);
				break;
			    }
			    else{
			    	owner.getActionSender().sendMessage("You need to be wearing the Staff of Saradomin to cast this spell!");
			    	return;
			    }
			    
			case 35:
			    if(owner.getLocation().inWilderness()) {
				owner.getActionSender().sendMessage("Can not use this spell in wilderness");
				return;
			    }
			    boolean hasZamorackCape = false;
			    for(InvItem cape : owner.getInventory().getItems()) {
			    	if(cape.getID() == 1216 && cape.isWielded()) { hasZamorackCape = true; }
			    }
			    if(hasZamorackCape){
			    	if(!owner.isCharged()){
                                    owner.getActionSender().sendMessage("@red@You are not charged!");
			    	}
				if(!checkAndRemoveRunes(owner, spell)) {
				    return;
				}
				if(affectedMob instanceof Player && !owner.isDueling()) {
				    Player affectedPlayer = (Player)affectedMob;
				    owner.setSkulledOn(affectedPlayer);
				}
				int damag = Formulae.calcGodSpells(owner, affectedMob);
				if(affectedMob instanceof Player) {
				    Player affectedPlayer = (Player)affectedMob;
				    affectedPlayer.getActionSender().sendMessage(owner.getUsername() + " is shooting at you!");
				}
				else if(affectedMob instanceof Npc) { 
                                    Npc affectedNpc = (Npc)affectedMob;
                                    affectedNpc.addMageDamage(owner, damag);
				}
                                Projectile projectil = new Projectile(owner, affectedMob, 1);
                                godSpellObject(affectedMob, 35);
                                affectedMob.setLastDamage(damag);
				
                                int newhp = affectedMob.getHits() - damag;
                                affectedMob.setHits(newhp);
				ArrayList<Player> playersToInfor = new ArrayList<Player>();
				playersToInfor.addAll(owner.getViewArea().getPlayersSectorA());
                                playersToInfor.addAll(owner.getViewArea().getPlayersSectorB());
                                playersToInfor.addAll(owner.getViewArea().getPlayersSectorC());
                                playersToInfor.addAll(owner.getViewArea().getPlayersSectorD());
				playersToInfor.addAll(affectedMob.getViewArea().getPlayersSectorA());
                                playersToInfor.addAll(affectedMob.getViewArea().getPlayersSectorB());
                                playersToInfor.addAll(affectedMob.getViewArea().getPlayersSectorC());
                                playersToInfor.addAll(affectedMob.getViewArea().getPlayersSectorD());
				for(Player p : playersToInfor) {
				    p.informOfProjectile(projectil);
				    p.informOfModifiedHits(affectedMob);
				}
				if(affectedMob instanceof Player) {
				    Player affectedPlayer = (Player)affectedMob;
				    affectedPlayer.getActionSender().sendStat(3);
				}
				if(newhp <= 0) {
				    affectedMob.killedBy(owner, owner.isDueling());
				}
				owner.getActionSender().sendInventory();
				owner.getActionSender().sendStat(6);
				finalizeSpell(owner, spell);
				break;
			    }
			    else {
			    	owner.getActionSender().sendMessage("You need to be wearing the Staff of Zaramorak to cast this spell");
			    	return;
			    }
	
			default:
			    if(spell.getReqLevel() == 62 || spell.getReqLevel() == 65 || spell.getReqLevel() == 70 || spell.getReqLevel() == 75) {
				if(player.getLocation().inWilderness() && Constants.GameServer.F2P_WILDY) {
				    player.getActionSender().sendMessage("You can not cast this Members spell in F2P Wilderness");
				    return;
				}
			    }
			    if (!checkAndRemoveRunes(owner, spell)) {
			    	return;
			    }
			    if (affectedMob instanceof Player && !owner.isDueling()) {
				Player affectedPlayer = (Player) affectedMob;
				owner.setSkulledOn(affectedPlayer);
			    }
			    int max = -1;
			    for (int i = 0; i < Constants.GameServer.SPELLS.length; i++) {
				if (spell.getReqLevel() == Constants.GameServer.SPELLS[i][0])
				    max = Constants.GameServer.SPELLS[i][1];
			    }
			    if (player.getMagicPoints() > 30)
				max += 1;
			    int damage = Formulae.calcSpellHit(max, owner.getMagicPoints());
			    if (affectedMob instanceof Player) {
					Player affectedPlayer = (Player) affectedMob;
					affectedPlayer.getActionSender().sendMessage(owner.getUsername() + " is shooting at you!");
			    }
			    if (affectedMob instanceof Npc) {
					Npc npcc = (Npc) affectedMob;
					npcc.addMageDamage(owner, damage);
			    }
			    Projectile projectile = new Projectile(owner, affectedMob, 1);
			    affectedMob.setLastDamage(damage);
			    int newHp = affectedMob.getHits() - damage;
			    affectedMob.setHits(newHp);
	
			    ArrayList<Player> playersToInform = new ArrayList<Player>();
			    playersToInform.addAll(owner.getViewArea().getPlayersSectorA());
                            playersToInform.addAll(owner.getViewArea().getPlayersSectorB());
                            playersToInform.addAll(owner.getViewArea().getPlayersSectorC());
                            playersToInform.addAll(owner.getViewArea().getPlayersSectorD());
			    playersToInform.addAll(affectedMob.getViewArea().getPlayersSectorA());
                            playersToInform.addAll(affectedMob.getViewArea().getPlayersSectorB());
                            playersToInform.addAll(affectedMob.getViewArea().getPlayersSectorC());
                            playersToInform.addAll(affectedMob.getViewArea().getPlayersSectorD());
			    for (Player p : playersToInform) {
				p.informOfProjectile(projectile);
				p.informOfModifiedHits(affectedMob);
			    }
			    if (affectedMob instanceof Player) {
				Player affectedPlayer = (Player) affectedMob;
				affectedPlayer.getActionSender().sendStat(3);
			    }
			    if (newHp <= 0) {
				if(affectedMob instanceof Player)
                                    affectedMob.killedBy(owner, owner.isDueling());
				
				if (owner instanceof Player) {
                                    Player toLoot = owner;
				    if (owner instanceof Player && affectedMob instanceof Npc) {
					Npc npc = (Npc) affectedMob;
					toLoot = npc.handleLootAndXpDistribution(owner);
				    }
				    if(!(affectedMob instanceof Player))
				    affectedMob.killedBy(toLoot, owner.isDueling());
                                }
			    }
			    finalizeSpell(owner, spell);
			    if (newHp > 0) {
			    	chasePlayer(owner, player, affectedMob);
			    }
			    break;
			}
			owner.getActionSender().sendInventory();
			owner.getActionSender().sendStat(6);
		    }
		});
    }

    private void handleTeleport(Player player, SpellDef spell, int id) {
		if (player.getLocation().wildernessLevel() >= 20 || (player.getLocation().inModRoom() && !player.isMod())) {
		    player.getActionSender().sendMessage("A magical force stops you from teleporting.");
		    return;
		}
		if (!checkAndRemoveRunes(player, spell)) {
		    return;
		}
		switch (id) {
                    case 12: // Varrock
                        player.teleport(122, 503, true);
                        break;
                    case 15: // Lumbridge
                        player.teleport(118, 649, true);
                        break;
                    case 18: // Falador
                        player.teleport(313, 550, true);
                        break;
                    case 22: // Camalot
                        player.teleport(465, 456, true);
                        break;
                    case 26: // Ardougne
                        player.teleport(585, 621, true);
                        break;
                    case 31: // Watchtower
                        player.teleport(637, 2628, true);
                        break;
                    case 37: // Lost city
                        player.teleport(131, 3544, true);
                        break;
		}
		finalizeSpell(player, spell);
		player.getActionSender().sendInventory();
    }
}