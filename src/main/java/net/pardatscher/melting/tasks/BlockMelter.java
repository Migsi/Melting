package net.pardatscher.melting.tasks;

import net.pardatscher.melting.Melting;
import net.pardatscher.melting.repositories.LavaRepository;
import org.bukkit.ChunkSnapshot;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.BlockVector;

import java.util.Map;
import java.util.Set;

public class BlockMelter implements Runnable {

    private final LavaRepository lavaRepository = JavaPlugin.getPlugin(Melting.class).getLavaRepository();

    @Override
    public void run() {
        while (JavaPlugin.getPlugin(Melting.class).isEnabled()) {
            try {
                Map<ChunkSnapshot, Set<BlockVector>> chunkSnapshotBlockVectorMap = lavaRepository.getChunkSnapshotBlockVectorMap();


            } catch (InterruptedException e) {
                // Do nothing
            }
        }
    }
}
