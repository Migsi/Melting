package net.pardatscher.melting;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public final class Melting extends JavaPlugin {

    private ChunkLoadListener listener;

    @Override
    public void onEnable() {
        // Plugin startup logic
        listener = new ChunkLoadListener();
        getServer().getPluginManager().registerEvents(listener, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        HandlerList.unregisterAll(listener);
    }
}
