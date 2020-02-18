package net.pardatscher.melting.listeners;

import net.pardatscher.melting.Melting;
import net.pardatscher.melting.repositories.ChunkRepository;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ChunkLoadListener implements Listener {

    private final ChunkRepository chunkRepository = JavaPlugin.getPlugin(Melting.class).getChunkRepository();

    @EventHandler
    public void onChunkLoad(ChunkLoadEvent event) {
        if (event.getChunk().contains(Material.LAVA.createBlockData()) && chunkRepository.addUnchecked(event.getChunk().getChunkSnapshot())) {
            Bukkit.getLogger().info("Found Lava in Chunk x: " + event.getChunk().getX() + " z: " + event.getChunk().getZ());
        }
    }
}
