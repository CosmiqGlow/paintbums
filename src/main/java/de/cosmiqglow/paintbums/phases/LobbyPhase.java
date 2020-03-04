package de.cosmiqglow.paintbums.phases;

import com.google.common.collect.ImmutableSet;
import com.google.gson.internal.bind.util.ISO8601Utils;
import de.cosmiqglow.fluctus.TimedGamePhase;
import de.cosmiqglow.paintbums.listeners.IngameDamageListener;
import de.cosmiqglow.paintbums.listeners.LobbyDamageListener;
import de.cosmiqglow.paintbums.listeners.LobbyInteractListener;
import de.cosmiqglow.paintbums.listeners.SpawnLocationListener;
import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.PlaceholderHook;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class LobbyPhase extends TimedGamePhase {

    private int minimumPlayers;
    private final ImmutableSet<Integer> counters;

    //TODO: Parameter with config?
    public LobbyPhase(JavaPlugin plugin) {
        super(plugin, plugin.getConfig().getInt("lobby.duration", 30)); //Not very elegant
        minimumPlayers = plugin.getConfig().getInt("lobby.minimum-players", 4);
        counters = ImmutableSet.of(initialTicks(), (initialTicks() / 2), 3, 2, 1);
    }

    @Override
    protected void onStart() {
        if (getPlayers().size() >= minimumPlayers) {
            setCurrentTicks(initialTicks() / 2);
            enableTicking();
        }

        Location spawnLocation = getSpawnLocation();
        register(new SpawnLocationListener(spawnLocation));
        register(new LobbyDamageListener(spawnLocation));
        register(new LobbyInteractListener());
    }

    @Override
    protected void onUpdate() {
        if (counters.contains(currentTicks())) {

            String message = getPlugin().getConfig().getString("lobby.countdown", "");
            if(message != null) {
                broadcast(message.replaceAll("%countdown%", String.valueOf(currentTicks())));
            }
        }
    }

    @Override
    protected void onEnd() {
        //TODO: Distribute, Teleport, Inventory, Scoreboard, ...
        for(Player player : getPlayers()) {
            player.getInventory().clear();
            //player.teleport()
        }

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        event.setJoinMessage(getMessageOf(player, "lobby.join-message", ""));
        player.sendMessage(getMessageOf(player, "lobby.welcome-message", ""));

        final PlayerInventory inventory = player.getInventory();
        inventory.clear();
        inventory.setItem(4, new ItemStack(Material.BAMBOO_SAPLING));
        inventory.setChestplate(new ItemStack(Material.ELYTRA));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        final Player player = event.getPlayer();
        String message = getMessageOf(player, "lobby.quit-message", "lOl");
        event.setQuitMessage(message);

        if ((getPlayers().size() - 1) < minimumPlayers) {
            disableTicking();
            resetTicks();
        }
    }

    @EventHandler
    public final void onKick(PlayerKickEvent event) {
        final Player player = event.getPlayer();
        String message = getMessageOf(player, "lobby.quit-message", "Bye Bye");
        event.setLeaveMessage(message);

        if ((getPlayers().size() - 1) < minimumPlayers) {
            disableTicking();
            resetTicks();
        }
    }

    @Override
    protected boolean isReadyToEnd() {
        return super.isReadyToEnd() && getPlayers().size() >= minimumPlayers;
    }

    @NotNull
    private String getMessageOf(Player player, String path, @NotNull String orElse) {
        String message = getPlugin().getConfig().getString(path, orElse);
        if(message != null) {
            message = PlaceholderAPI.setPlaceholders(player, message); //TODO: What if PlaceHolderAPI is missing?
        } else {
            message = orElse;
        }
        return message;
    }

    @NotNull
    private Location getSpawnLocation() {
        Location spawnLocation = getPlugin().getConfig().getLocation("lobby.location");
        if(spawnLocation == null) {
            World world = Bukkit.getWorlds().get(0);
            spawnLocation = new Location(world, 0.0, 0.0, 0.0);
            //TODO: What if world == null?
        }
        return spawnLocation;
    }

}
