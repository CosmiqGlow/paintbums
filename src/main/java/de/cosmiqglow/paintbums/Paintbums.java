package de.cosmiqglow.paintbums;

import de.cosmiqglow.fluctus.ScheduledGamePhaseSeries;
import de.cosmiqglow.paintbums.listeners.CancelListener;
import de.cosmiqglow.paintbums.listeners.ChatListener;
import de.cosmiqglow.paintbums.listeners.CommandListener;
import de.cosmiqglow.paintbums.phases.IngamePhase;
import de.cosmiqglow.paintbums.phases.LobbyPhase;
import de.cosmiqglow.paintbums.phases.RestartPhase;
import org.bukkit.plugin.java.JavaPlugin;

public class Paintbums extends JavaPlugin {

    private final ScheduledGamePhaseSeries mainSeries = new ScheduledGamePhaseSeries(this);

    @Override
    public void onEnable() {
        loadConfig();
        registerListeners();

        mainSeries.add(new LobbyPhase(this));
        mainSeries.add(new IngamePhase(this, 60*5));
        mainSeries.add(new RestartPhase(this, 30));
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    private void loadConfig() {
        getConfig().addDefault("chat-options.provided-chat", true );
        getConfig().options().copyDefaults(true);
        saveConfig();
        reloadConfig();
    }

    private void registerListeners() {
        if (getConfig().getBoolean("chat-options.provided-chat"))
            this.getServer().getPluginManager().registerEvents(new ChatListener(), this);

        this.getServer().getPluginManager().registerEvents(new CancelListener(), this);
        this.getServer().getPluginManager().registerEvents(new CommandListener(), this);
    }


}