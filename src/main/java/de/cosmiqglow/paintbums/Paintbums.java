package de.cosmiqglow.paintbums;

import de.cosmiqglow.paintbums.listeners.CancelListener;
import de.cosmiqglow.paintbums.listeners.ChatListener;
import de.cosmiqglow.paintbums.listeners.CommandListener;
import org.bukkit.plugin.java.JavaPlugin;

public class Paintbums extends JavaPlugin {

    @Override
    public void onEnable() {
        loadConfig();
        registerListeners();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    private void loadConfig() {
        getConfig().addDefault("chat-options.use-provided-chat", true );
        getConfig().options().copyDefaults(true);
        saveConfig();
        reloadConfig();
    }

    private void registerListeners() {
        if (getConfig().getBoolean("Chat-Options.provided-chat"))
            this.getServer().getPluginManager().registerEvents(new ChatListener(), this);

        this.getServer().getPluginManager().registerEvents(new CancelListener(), this);
        this.getServer().getPluginManager().registerEvents(new CommandListener(), this);
    }


}