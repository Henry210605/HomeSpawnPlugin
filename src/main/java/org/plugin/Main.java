package org.plugin;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("HomeSpawnPlugin gestartet!");

        HomeCommand homeCommand = new HomeCommand(this);
        getCommand("sethome").setExecutor(homeCommand);
        getCommand("home").setExecutor(homeCommand);

        SpawnCommand spawnCommand = new SpawnCommand(this);
        getCommand("spawn").setExecutor(spawnCommand);
    }

    @Override
    public void onDisable() {
        getLogger().info("HomeSpawnPlugin stopped.");
    }
}