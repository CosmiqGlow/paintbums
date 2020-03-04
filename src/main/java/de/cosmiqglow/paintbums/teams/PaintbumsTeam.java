package de.cosmiqglow.paintbums.teams;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.List;

public class PaintbumsTeam {

  private final String name;
  private final Color color;
  private final Location spawn;
  private int points;
  private List<Player> list;

  public PaintbumsTeam(final String name, final Color color, Location spawn) {
    this.name = name;
    this.color = color;
    this.spawn = spawn;
  }

  public boolean canJoin(Player player) {
    return list.contains(player);
  }

  public void join(Player player) {
    if (!canJoin(player)) return;

    list.add(player);
    player.teleport(spawn);

    //An ItemFactory would've been too much
    ItemStack elytra = new ItemStack(Material.ELYTRA);
    elytra.getItemMeta().setUnbreakable(true);

    ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
    LeatherArmorMeta lMeta = (LeatherArmorMeta) helmet.getItemMeta();
    lMeta.setColor(color);
    helmet.setItemMeta(lMeta);

    player.getInventory().clear();
    player.getInventory().setChestplate(elytra);
    player.getInventory().setHelmet(helmet);

    //TODO: Check if user activated our chatsystem
    player.sendMessage("You've joined the " + color + " team");
  }

  public void leave(Player player) {
    if (canJoin(player)) return;

    list.add(player);
    player.sendMessage("You've left the " + color + " team");
  }

  public void clear() {
    list.forEach(player -> list.remove(player));
    points = 0;
  }

  public void incrementPoints(int amount) {
    if (amount <= 0) return;
    points += amount;
  }

  public void incrementPoints() {
    incrementPoints(1);
  }

  public void decrementPoints(int amount) {
    if (amount <= 0 || points == 0) return;
    points -= amount;
  }

  public void decrementPoints() {
    decrementPoints(1);
  }

  public int getSize() {
    return list.size();
  }

  public Location getSpawn() {
    return spawn;
  }

  public Color getColor() {
    return color;
  }

  public String getName() {
    return name;
  }

  public int getPoints() {return points;}
}
