package de.cosmiqglow.paintbums.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class IngameDamageListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if(event.getCause() == EntityDamageEvent.DamageCause.VOID) {
            //TODO: Spawn at teams location
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if(event.getDamager().getType() != EntityType.SNOWBALL || event.getEntityType() != EntityType.PLAYER) {
            event.setCancelled(true);
            return;
        }

        Player target = (Player) event.getEntity();
        Snowball paintball = (Snowball) event.getDamager();

        //TODO: Get target's and shooter's team
    }

}
