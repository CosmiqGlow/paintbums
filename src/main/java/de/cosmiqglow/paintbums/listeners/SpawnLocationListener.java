package de.cosmiqglow.paintbums.listeners;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;

public class SpawnLocationListener implements Listener {

    private final Location spawnLocation;

    public SpawnLocationListener(Location spawnLocation) {
        this.spawnLocation = spawnLocation;
    }

    //TODO: Read location from config
    @EventHandler
    public void onSpawnLocation(PlayerSpawnLocationEvent event) {
        event.setSpawnLocation(spawnLocation);
    }

}
