package de.cosmiqglow.paintbums.phases;

import de.cosmiqglow.fluctus.TimedGamePhase;
import de.cosmiqglow.paintbums.listeners.IngameDamageListener;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class IngamePhase extends TimedGamePhase {

    public IngamePhase(JavaPlugin plugin, int initialTicks) {
        super(plugin, initialTicks);
    }

    @Override
    protected void onStart() {
        register(new IngameDamageListener());
    }

    @Override
    protected void onUpdate() {
        //TODO: Update Scoreboard
    }

    @Override
    protected void onEnd() {
        //TODO: Teleport all
    }

}
