package de.cosmiqglow.paintbums;

import org.bukkit.plugin.java.JavaPlugin;

public class Paintbums extends JavaPlugin {

    @Override
    public void onEnable() {
        loadConfig();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public void loadConfig() {
        getConfig().addDefault("Chat-Options.provided-chat", true );
        getConfig().options().copyDefaults(true);
        saveConfig();
        reloadConfig();
    }


}