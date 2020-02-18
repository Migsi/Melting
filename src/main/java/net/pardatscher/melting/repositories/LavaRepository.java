package net.pardatscher.melting.repositories;

import org.bukkit.ChunkSnapshot;
import org.bukkit.util.BlockVector;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LavaRepository {

    private Map<ChunkSnapshot, Set<BlockVector>> chunkSnapshotBlockVectorMap = new HashMap<>();

    public synchronized boolean add(ChunkSnapshot chunkSnapshot, BlockVector blockVector) {
        Set<BlockVector> blockVectorSet = chunkSnapshotBlockVectorMap.getOrDefault(chunkSnapshot, new HashSet<>());
        if (blockVectorSet.add(blockVector)) {
            chunkSnapshotBlockVectorMap.put(chunkSnapshot, blockVectorSet);
            notifyAll();
            return true;
        }
        return false;
    }

    public synchronized Map<ChunkSnapshot, Set<BlockVector>> getChunkSnapshotBlockVectorMap() throws InterruptedException {
        while (chunkSnapshotBlockVectorMap.isEmpty()) {
            wait();
        }
        return chunkSnapshotBlockVectorMap;
    }
}
