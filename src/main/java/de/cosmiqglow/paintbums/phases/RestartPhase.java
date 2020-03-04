package de.cosmiqglow.paintbums.phases;

import de.cosmiqglow.fluctus.TimedGamePhase;
import de.cosmiqglow.paintbums.listeners.LobbyDamageListener;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class RestartPhase extends TimedGamePhase {

    public RestartPhase(JavaPlugin plugin, int initialTicks) {
        super(plugin, initialTicks);
    }

    @Override
    protected void onStart() {
        register(new LobbyDamageListener(getSpawnLocation()));
    }

    @Override
    protected void onUpdate() {
        //TODO: Celebrate
    }

    @Override
    protected void onEnd() {
        //TODO: Kick all and restart
    }

    //TODO: Externalize
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
