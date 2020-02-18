package net.pardatscher.melting;

import org.bukkit.plugin.java.JavaPlugin;

public final class Melting extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("onEnable has been invoked!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("onDisable has been invoked!");
    }
}
