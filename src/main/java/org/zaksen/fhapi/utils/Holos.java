package org.zaksen.fhapi.utils;

import org.bukkit.Location;
import org.zaksen.fhapi.holo.*;

public class Holos
{
    public static IHologram getHolo(int id) { return HologramManager.getById(id); }
    public static void removeHolo(int id) {
        HologramManager.removeHolo(id);
    }

    // Text Display
    public static TextHologram getTextHolo(int id) {
        return (TextHologram) HologramManager.getById(id);
    }

    public static TextHologram spawnTextWithId(Location location, int id) {
        if(!HologramManager.hasId(id)) {
            return new TextHologram(id, location);
        }
        return null;
    }

    public static TextHologram spawnText(Location location) {
        return spawnTextWithId(location, HologramManager.getLastAvailableId());
    }

    // Block Display
    public static BlockHologram getBlockHolo(int id) {
        return (BlockHologram) HologramManager.getById(id);
    }

    public static BlockHologram spawnBlockWithId(Location location, int id) {
        if(!HologramManager.hasId(id)) {
            return new BlockHologram(id, location);
        }
        return null;
    }

    public static BlockHologram spawnBlock(Location location) {
        return spawnBlockWithId(location, HologramManager.getLastAvailableId());
    }

    // Item Display
    public static ItemHologram getItemHolo(int id) {
        return (ItemHologram) HologramManager.getById(id);
    }

    public static ItemHologram spawnItemWithId(Location location, int id) {
        if(!HologramManager.hasId(id)) {
            return new ItemHologram(id, location);
        }
        return null;
    }

    public static ItemHologram spawnItem(Location location) {
        return spawnItemWithId(location, HologramManager.getLastAvailableId());
    }
}