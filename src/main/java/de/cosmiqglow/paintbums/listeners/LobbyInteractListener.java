package de.cosmiqglow.paintbums.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;

public class LobbyInteractListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        event.setCancelled(true);
        Player player = (Player) event.getWhoClicked();
        int slot = event.getRawSlot();

        //TODO: Get Team with associated slot
        if(slot == 0) {
            player.sendMessage("Yeah you clicked that slot!");
        } else {
            player.sendMessage("Yeah you clicked that other slot!");
        }
    }

    @EventHandler
    public void onInteract(InventoryInteractEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onItemDrag(InventoryDragEvent event) {
        event.setCancelled(true);
    }

}
