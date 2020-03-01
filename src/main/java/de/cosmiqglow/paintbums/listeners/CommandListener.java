package de.cosmiqglow.paintbums.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener implements Listener {

  @EventHandler
  public void onHelpCommand(PlayerCommandPreprocessEvent event) {
    String m = event.getMessage();
    if (m.startsWith("/help") || m.startsWith("/HELP") || m.equalsIgnoreCase("/help")){

    }


  }

  @EventHandler
  public void onFixCommand(PlayerCommandPreprocessEvent event) {

  }

  @EventHandler
  public void onSetupCommand(PlayerCommandPreprocessEvent event) {

  }
}
