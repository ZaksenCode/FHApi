package org.zaksen.fhapi;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.zaksen.fhapi.command.HologramCMD;
import org.zaksen.fhapi.command.HologramCMDTabCompleter;
import org.zaksen.fhapi.event.MenuEvents;
import org.zaksen.fhapi.holo.HologramManager;
import org.zaksen.fhapi.utils.ChatUtils;
import org.zaksen.fhapi.editor.MainMenu;

import java.util.HashMap;
import java.util.Map;

public final class FHApi extends JavaPlugin {

    private static Map<String, String> formattedMessages = new HashMap<>();

    @Override
    public void onEnable() {
        // Load data
        try {
            HologramManager.onServerStartup();
        } catch (Exception e) {
            System.out.println(e);
        }
        saveDefaultConfig();
        loadFormattedMessages();
        // Plugin startup logic
        getCommand("hologram").setExecutor(new HologramCMD());
        getCommand("hologram").setTabCompleter(new HologramCMDTabCompleter());
        Bukkit.getPluginManager().registerEvents(new MenuEvents(), this);

        int newPageCount = getConfig().getInt("gui_page-count");
        MainMenu.setHoloPrePage(newPageCount);
    }

    private void loadFormattedMessages() {
        formattedMessages.put("color_symbol", getConfig().getString("color_symbol"));
        formattedMessages.put("msg_prefix", ChatUtils.formatText(getConfig().getString("msg_prefix")));
        formattedMessages.put("msg_wrong-args", ChatUtils.formatText(getConfig().getString("msg_wrong-args")));
        formattedMessages.put("msg_wrong-subcommand", ChatUtils.formatText(getConfig().getString("msg_wrong-subcommand")));
        formattedMessages.put("msg_spawn-holo", ChatUtils.formatText(getConfig().getString("msg_spawn-holo")));
        formattedMessages.put("msg_remove-holo", ChatUtils.formatText(getConfig().getString("msg_remove-holo")));
        formattedMessages.put("msg_exists-id", ChatUtils.formatText(getConfig().getString("msg_exists-id")));
    }

    public static Map<String, String> getFormattedMessages(){
        return formattedMessages;
    }
}