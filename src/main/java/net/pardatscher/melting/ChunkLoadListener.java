package net.pardatscher.melting;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

public class ChunkLoadListener implements Listener {

    @EventHandler
    public void onChunkLoad(ChunkLoadEvent event) {
        if (event.getChunk().contains(Material.LAVA.createBlockData())) {
            Bukkit.getLogger().info("Found Lava in Chunk x: " + event.getChunk().getX() + " y: " + event.getChunk().getZ());
        }
    }
}
