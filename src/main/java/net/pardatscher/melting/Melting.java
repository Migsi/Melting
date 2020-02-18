package net.pardatscher.melting;

import net.pardatscher.melting.listeners.ChunkLoadListener;
import net.pardatscher.melting.repositories.ChunkRepository;
import net.pardatscher.melting.repositories.LavaRepository;
import net.pardatscher.melting.tasks.BlockMelter;
import net.pardatscher.melting.tasks.LavaFinder;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

public final class Melting extends JavaPlugin {

    private final ChunkRepository chunkRepository = new ChunkRepository();
    private final LavaRepository lavaRepository = new LavaRepository();

    private List<BukkitTask> taskList;

    private ChunkLoadListener listener;
    private LavaFinder lavaFinder;

    private BlockMelter blockMelter;

    public ChunkRepository getChunkRepository() {
        return chunkRepository;
    }

    public LavaRepository getLavaRepository() {
        return lavaRepository;
    }

    @Override
    public void onEnable() {
        // Create new task list
        taskList = new ArrayList<>();

        // Create and register chunk load listener
        listener = new ChunkLoadListener();
        getServer().getPluginManager().registerEvents(listener, this);

        // Create and start lava finder
        lavaFinder = new LavaFinder();
        taskList.add(Bukkit.getScheduler().runTaskAsynchronously(this, lavaFinder));

        // Create and start melter
        blockMelter = new BlockMelter();
        taskList.add(Bukkit.getScheduler().runTaskAsynchronously(this, blockMelter));
    }

    @Override
    public void onDisable() {
        // Unregister listener
        HandlerList.unregisterAll(listener);

        // Cancel remaining tasks
        taskList.forEach(BukkitTask::cancel);
    }
}
