package org.zaksen.fhapi.holo;

import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.TextDisplay;
import org.zaksen.fhapi.data.ILoadedDisplay;
import org.zaksen.fhapi.database.DatabaseManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HologramManager
{
    private static final HashMap<Integer, IHologram> holos = new HashMap<>();

    public static boolean hasId(int id)
    {
        return holos.containsKey(id);
    }

    public static IHologram getById(int id) {
        return holos.get(id);
    }

    public static int getLastAvailableId() {
        List<String> loadedIds = HologramManager.getIds();
        int idsSize = loadedIds.size();
        int newId = 1;
        if(idsSize > 0) {
            if(!loadedIds.contains(String.valueOf(idsSize))) {
                newId = loadedIds.size();
            } else if (!loadedIds.contains( String.valueOf(idsSize + 1))) {
                newId = loadedIds.size() + 1;
            } else {
                int maxId = 0;
                for(String id : loadedIds) {
                    if(maxId < Integer.parseInt(id)){
                        maxId = Integer.parseInt(id);
                    }
                }
                newId = maxId+1;
            }
        }
        return newId;
    }

    public static ArrayList<String> getIds()
    {
        ArrayList<String> ids = new ArrayList<>();
        holos.forEach((key, value) -> {
            ids.add(key.toString());
        });
        return ids;
    }

    public static void addHolo(IHologram holo)
    {
        holos.put(holo.getId(), holo);
        DatabaseManager.Instance.addHologram(holo);
    }

    public static void removeHolo(int id)
    {
        if(holos.containsKey(id)) {
            DatabaseManager.Instance.removeHologram(holos.get(id));
            holos.get(id).destroy();
            holos.remove(id);
        }
    }

    public static void onServerStartup()
    {
        HashMap<Integer, ILoadedDisplay> loadedHolos = DatabaseManager.Instance.getHolograms();
        loadedHolos.forEach((id, holo) -> {
            if (holo.getEntity() instanceof TextDisplay) {
                holos.put(id, new TextHologram(holo));
            } else if (holo.getEntity() instanceof BlockDisplay) {
                holos.put(id, new BlockHologram(holo));
            } else if (holo.getEntity() instanceof ItemDisplay) {
                holos.put(id, new ItemHologram(holo));
            }
        });
    }

    public static HashMap<Integer, IHologram> getHolos() {
        return holos;
    }
}