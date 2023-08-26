package org.zaksen.fhapi.editor;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BlockEditor
{
    public static Inventory lastBlockEditor;

    public static void openBlockMenu(Player player, int id) {
        player.openInventory(createBlockMenu(id));
    }

    public static Inventory createBlockMenu(int id) {
        lastBlockEditor = Bukkit.createInventory(null, 45, String.format("Block editor - id: %s", id));
        for (int i = 36; i < lastBlockEditor.getSize(); i++) {
            lastBlockEditor.setItem(i, new ItemStack(Material.LIME_STAINED_GLASS_PANE));
        }
        ItemStack backBtn = new ItemStack(Material.PURPLE_STAINED_GLASS);
        ItemMeta bBMeta = backBtn.getItemMeta();
        bBMeta.setDisplayName("Back");
        backBtn.setItemMeta(bBMeta);
        lastBlockEditor.setItem(37, backBtn);
        return lastBlockEditor;
    }
}
