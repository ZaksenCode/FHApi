package org.zaksen.fhapi;

import org.bukkit.plugin.java.JavaPlugin;
import org.zaksen.fhapi.command.HologramCMD;
import org.zaksen.fhapi.command.HologramCMDTabCompleter;
import org.zaksen.fhapi.text.HologramManager;

public final class FHApi extends JavaPlugin {

    @Override
    public void onEnable() {
        // Load data
        try {
            HologramManager.onServerStartup();
        } catch (Exception e) {
            System.out.println(e);
        }
        // Plugin startup logic
        getCommand("hologram").setExecutor(new HologramCMD());
        getCommand("hologram").setTabCompleter(new HologramCMDTabCompleter());
    }
}