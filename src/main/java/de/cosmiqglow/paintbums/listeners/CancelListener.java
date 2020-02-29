package de.cosmiqglow.paintbums.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class CancelListener implements Listener {

  @EventHandler
  public void onWeatherChange(WeatherChangeEvent event) {
    event.setCancelled(event.toWeatherState());
  }

  @EventHandler
  public void onFoodLevel(FoodLevelChangeEvent event) {
    event.setCancelled(true);
  }

  @EventHandler
  public void onItemConsume(PlayerItemConsumeEvent event) {
    event.setCancelled(true);
  }

  @EventHandler
  public void onHelpCommand(PlayerCommandPreprocessEvent event) {

  }
}
