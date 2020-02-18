package net.pardatscher.melting.tasks;

import net.pardatscher.melting.Melting;
import net.pardatscher.melting.repositories.LavaRepository;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.BlockVector;

import java.util.Map;

public class BlockMelter implements Runnable {

    private final LavaRepository lavaRepository = JavaPlugin.getPlugin(Melting.class).getLavaRepository();

    @Override
    public void run() {
        while (JavaPlugin.getPlugin(Melting.class).isEnabled()) {
            try {
                Map<String, BlockVector> worldBlockVectorMap = lavaRepository.getWorldBlockVectorMap();


            } catch (InterruptedException e) {
                // Do nothing
            }
        }
    }
}
