package net.pardatscher.melting.repositories;

import org.bukkit.ChunkSnapshot;

import java.util.HashSet;
import java.util.Set;

public class ChunkRepository {

    private Set<ChunkSnapshot> chunkSnapshotSet = new HashSet<>();
    private Set<ChunkSnapshot> checkedChunkSnapshotSet = new HashSet<>();

    public synchronized boolean addUnchecked(ChunkSnapshot chunkSnapshot) {
        boolean ret = false;
        if (!checkedChunkSnapshotSet.contains(chunkSnapshot)) {
            ret = chunkSnapshotSet.add(chunkSnapshot);
            notifyAll();
        }
        return ret;
    }

    public synchronized boolean addChecked(ChunkSnapshot chunkSnapshot) {
        return chunkSnapshotSet.add(chunkSnapshot);
    }

    public synchronized Set<ChunkSnapshot> getUncheckedChunkSnapshots() throws InterruptedException {
        while (chunkSnapshotSet.isEmpty()) {
            wait();
        }
        Set<ChunkSnapshot> chunkSnapshotSet = this.chunkSnapshotSet;
        this.chunkSnapshotSet = new HashSet<>();
        return chunkSnapshotSet;
    }
}
