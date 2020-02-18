package net.pardatscher.melting;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public final class Melting extends JavaPlugin {

    private final ChunkRepository chunkRepository = new ChunkRepository();
    private final LavaRepository lavaRepository = new LavaRepository();

    private ChunkLoadListener listener;

    public ChunkRepository getChunkRepository() {
        return chunkRepository;
    }

    public LavaRepository getLavaRepository() {
        return lavaRepository;
    }

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
