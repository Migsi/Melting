package net.pardatscher.melting.repositories;

import org.bukkit.util.BlockVector;

import java.util.HashMap;
import java.util.Map;

public class LavaRepository {

    private Map<String, BlockVector> worldBlockVectorMap = new HashMap<>();

    public synchronized boolean add(String world, BlockVector blockVector) {
        if (worldBlockVectorMap.putIfAbsent(world, blockVector) != null) {
            notifyAll();
            return true;
        }
        return false;
    }

    public synchronized Map<String, BlockVector> getWorldBlockVectorMap() throws InterruptedException {
        while (worldBlockVectorMap.isEmpty()) {
            wait();
        }
        return worldBlockVectorMap;
    }
}
