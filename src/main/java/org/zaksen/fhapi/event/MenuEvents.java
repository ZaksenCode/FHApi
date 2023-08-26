package org.zaksen.fhapi.event;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.zaksen.fhapi.editor.BlockEditor;
import org.zaksen.fhapi.editor.ItemEditor;
import org.zaksen.fhapi.editor.MainMenu;
import org.zaksen.fhapi.editor.TextEditor;

public class MenuEvents implements Listener {

    private int lastMenuPage = 0;
    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if(event.getInventory() == MainMenu.lastOpenInventory) {
            event.setCancelled(true);
            lastMenuPage = Integer.parseInt(event.getView().getOriginalTitle().replaceFirst("Holo editor - ", "").replaceFirst(" page", ""));
            Player player = (Player) event.getWhoClicked();
            ItemStack currentStack = event.getCurrentItem();
            if(currentStack != null) {
                if(currentStack.getType() == Material.NAME_TAG) {
                    int id = Integer.parseInt(currentStack.getItemMeta().getLore().get(0).replaceFirst("Id: ", ""));
                    TextEditor.openTextMenu(player, id);
                } else if (currentStack.getType() == Material.BEDROCK) {
                    int id = Integer.parseInt(currentStack.getItemMeta().getLore().get(0).replaceFirst("Id: ", ""));
                    BlockEditor.openBlockMenu(player, id);
                } else if (currentStack.getType() == Material.BARRIER) {
                    int id = Integer.parseInt(currentStack.getItemMeta().getLore().get(0).replaceFirst("Id: ", ""));
                    ItemEditor.openItemMenu(player, id);
                } else if (currentStack.getType() == Material.PURPLE_STAINED_GLASS) {
                    ItemMeta stackMeta = currentStack.getItemMeta();
                    if(stackMeta.getDisplayName().equals("<-")) {
                        if(lastMenuPage > 0) {
                            MainMenu.openMainGui(player, lastMenuPage - 1);
                        }
                    } else if(stackMeta.getDisplayName().equals("->")) {
                        MainMenu.openMainGui(player, lastMenuPage + 1);
                    }
                }
            }
        } else if (event.getInventory() == TextEditor.lastTextEditor) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            ItemStack currentStack = event.getCurrentItem();
            if(currentStack != null) {
                if(currentStack.getType() == Material.PURPLE_STAINED_GLASS) {
                    MainMenu.openMainGui(player, lastMenuPage);
                }
            }
        } else if (event.getInventory() == BlockEditor.lastBlockEditor) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            ItemStack currentStack = event.getCurrentItem();
            if(currentStack != null) {
                if(currentStack.getType() == Material.PURPLE_STAINED_GLASS) {
                    MainMenu.openMainGui(player, lastMenuPage);
                }
            }
        } else if (event.getInventory() == ItemEditor.lastItemEditor) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            ItemStack currentStack = event.getCurrentItem();
            if(currentStack != null) {
                if(currentStack.getType() == Material.PURPLE_STAINED_GLASS) {
                    MainMenu.openMainGui(player, lastMenuPage);
                }
            }
        }
    }
}
