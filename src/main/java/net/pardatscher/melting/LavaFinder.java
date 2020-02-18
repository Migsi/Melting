package net.pardatscher.melting;

import org.bukkit.ChunkSnapshot;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BlockVector;

import java.util.Set;

public class LavaFinder extends BukkitRunnable {

    private final ChunkRepository chunkRepository = JavaPlugin.getPlugin(Melting.class).getChunkRepository();
    private final LavaRepository lavaRepository = JavaPlugin.getPlugin(Melting.class).getLavaRepository();

    private int highestBlockYInChunk(ChunkSnapshot chunkSnapshot) {
        int y = 0;
        for (int x = 0; x < 16; ++x) {
            for (int z = 0; z < 16; ++z) {
                y = Math.max(y, chunkSnapshot.getHighestBlockYAt(x, z));
            }
        }
        return y;
    }

    @Override
    public void run() {
        while (JavaPlugin.getPlugin(Melting.class).isEnabled()) {
            try {
                Set<ChunkSnapshot> chunkSnapshotSet = chunkRepository.getUncheckedChunkSnapshots();
                chunkSnapshotSet.forEach(chunkSnapshot -> {
                    final int maxY = highestBlockYInChunk(chunkSnapshot);
                    for (int y = 0; y < maxY; ++y) {
                        for (int x = 0; x < 16; ++x) {
                            for (int z = 0; z < 16; ++z) {
                                if (chunkSnapshot.getBlockData(x, y, z).getMaterial() == Material.LAVA) {
                                    lavaRepository.add(new BlockVector(x, y, z));
                                }
                            }
                        }
                    }

                    chunkRepository.addChecked(chunkSnapshot);
                });
            } catch (InterruptedException e) {
                // Do nothing
            }
        }
    }
}
