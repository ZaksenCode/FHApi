package org.zaksen.fhapi.editor;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.zaksen.fhapi.holo.HologramManager;
import org.zaksen.fhapi.holo.IHologram;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class MainMenu
{
    public static Inventory lastOpenInventory;
    private static int holoPerPage = 9;

    public static void setHoloPrePage(int i) {
        holoPerPage = i;
    }

    public static void openMainGui(Player player, int page) {
        int offset = holoPerPage * page;
        final int[] itr = {1};
        List<ItemStack> holoStacks = new ArrayList<>();
        HologramManager.getHolos().forEach((id, holo) -> {
            if(itr[0] > offset) {
                ItemStack newHoloStack = getStackByHolo(holo);
                holoStacks.add(newHoloStack);
            }
            itr[0]++;
        });
        player.openInventory(createMainGui(holoPerPage, page, holoStacks));
    }

    public static Inventory createMainGui(int holosCount, int page, List<ItemStack> holoStacks) {
        lastOpenInventory = Bukkit.createInventory(null, holosCount + 9, String.format("Holo editor - %s page", page));
        int size = lastOpenInventory.getSize();
        for(int x = size - 1; x > size - 10; x--) {
            lastOpenInventory.setItem(x, new ItemStack(Material.LIME_STAINED_GLASS_PANE));
        }
        ItemStack leftArrow = new ItemStack(Material.PURPLE_STAINED_GLASS);
        ItemMeta lMeta = leftArrow.getItemMeta();
        lMeta.setDisplayName("<-");
        leftArrow.setItemMeta(lMeta);
        ItemStack rightArrow = new ItemStack(Material.PURPLE_STAINED_GLASS);
        ItemMeta rMeta = rightArrow.getItemMeta();
        rMeta.setDisplayName("->");
        rightArrow.setItemMeta(rMeta);
        lastOpenInventory.setItem(size - 8, leftArrow);
        lastOpenInventory.setItem(size - 2, rightArrow);
        for(int i = 0; i < holosCount; i++) {
            if(i < holoStacks.size()) {
                lastOpenInventory.setItem(i, holoStacks.get(i));
            }
        }
        return lastOpenInventory;
    }

    private static ItemStack getStackByHolo(IHologram holo) {
        ItemStack holoStack;
        if(holo.getDisplay() instanceof TextDisplay) {
            holoStack = new ItemStack(Material.NAME_TAG);
            ItemMeta stackMeta = holoStack.getItemMeta();
            stackMeta.setDisplayName("Text hologram");
            stackMeta.setLore(Collections.singletonList("Id: " + holo.getId()));
            holoStack.setItemMeta(stackMeta);
        } else if(holo.getDisplay() instanceof BlockDisplay) {
            holoStack = new ItemStack(Material.BEDROCK);
            ItemMeta stackMeta = holoStack.getItemMeta();
            stackMeta.setDisplayName("Block hologram");
            stackMeta.setLore(Collections.singletonList("Id: " + holo.getId()));
            holoStack.setItemMeta(stackMeta);
        } else {
            holoStack = new ItemStack(Material.BARRIER);
            ItemMeta stackMeta = holoStack.getItemMeta();
            stackMeta.setDisplayName("Item hologram");
            stackMeta.setLore(Collections.singletonList("Id: " + holo.getId()));
            holoStack.setItemMeta(stackMeta);
        }
        return holoStack;
    }
}