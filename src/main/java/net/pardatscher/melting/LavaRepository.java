package net.pardatscher.melting;

import org.bukkit.util.BlockVector;

import java.util.HashSet;
import java.util.Set;

public class LavaRepository {

    private Set<BlockVector> blockVectorSet = new HashSet<>();

    public synchronized boolean add(BlockVector blockVector) {
        return blockVectorSet.add(blockVector);
    }

}
