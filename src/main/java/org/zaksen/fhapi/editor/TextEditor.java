package org.zaksen.fhapi.editor;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TextEditor
{
    public static Inventory lastTextEditor;

    public static void openTextMenu(Player player, int id) {
        player.openInventory(createTextMenu(id));
    }

    public static Inventory createTextMenu(int id) {
        lastTextEditor = Bukkit.createInventory(null, 45, String.format("Text editor - id: %s", id));
        for (int i = 0; i < lastTextEditor.getSize(); i++) {
            lastTextEditor.setItem(i, new ItemStack(Material.LIME_STAINED_GLASS_PANE));
        }
        // Text
        ItemStack textBtn = new ItemStack(Material.OAK_SIGN);
        ItemMeta tMeta = textBtn.getItemMeta();
        tMeta.setDisplayName("Text");
        textBtn.setItemMeta(tMeta);
        lastTextEditor.setItem(10, textBtn);
        // Align
        ItemStack alignmentBtn = new ItemStack(Material.MAP);
        ItemMeta mMeta = textBtn.getItemMeta();
        mMeta.setDisplayName("Alignment");
        alignmentBtn.setItemMeta(mMeta);
        lastTextEditor.setItem(11, alignmentBtn);
        // Shadow
        ItemStack shdBtn = new ItemStack(Material.TINTED_GLASS);
        ItemMeta sMeta = shdBtn.getItemMeta();
        sMeta.setDisplayName("Shadow");
        shdBtn.setItemMeta(sMeta);
        lastTextEditor.setItem(12, textBtn);
        // Line width
        ItemStack lineBtn = new ItemStack(Material.MAP);
        ItemMeta lMeta = lineBtn.getItemMeta();
        lMeta.setDisplayName("Line width");
        lineBtn.setItemMeta(lMeta);
        lastTextEditor.setItem(13, alignmentBtn);
        // Back
        ItemStack backBtn = new ItemStack(Material.PURPLE_STAINED_GLASS);
        ItemMeta bBMeta = backBtn.getItemMeta();
        bBMeta.setDisplayName("Back");
        backBtn.setItemMeta(bBMeta);
        lastTextEditor.setItem(37, backBtn);
        return lastTextEditor;
    }
}