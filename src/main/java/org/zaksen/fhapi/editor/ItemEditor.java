package org.zaksen.fhapi.editor;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemEditor
{
    public static Inventory lastItemEditor;

    public static void openItemMenu(Player player, int id) {
        player.openInventory(createItemMenu(id));
    }

    public static Inventory createItemMenu(int id) {
        lastItemEditor = Bukkit.createInventory(null, 45, String.format("Item editor - id: %s", id));
        for (int i = 36; i < lastItemEditor.getSize(); i++) {
            lastItemEditor.setItem(i, new ItemStack(Material.LIME_STAINED_GLASS_PANE));
        }
        ItemStack backBtn = new ItemStack(Material.PURPLE_STAINED_GLASS);
        ItemMeta bBMeta = backBtn.getItemMeta();
        bBMeta.setDisplayName("Back");
        backBtn.setItemMeta(bBMeta);
        lastItemEditor.setItem(37, backBtn);
        return lastItemEditor;
    }
}