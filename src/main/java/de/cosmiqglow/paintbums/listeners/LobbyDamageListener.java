package de.cosmiqglow.paintbums.listeners;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class LobbyDamageListener implements Listener {

    private final Location spawnLocation;

    public LobbyDamageListener(Location spawnLocation) {
        this.spawnLocation = spawnLocation;
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        event.setCancelled(true);

        if(event.getCause() == EntityDamageEvent.DamageCause.VOID) {
            //TODO: Teleport back to spawn
            event.getEntity().teleport(spawnLocation);
        }
    }

}
