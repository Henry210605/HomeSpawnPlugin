package org.plugin;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("HomeSpawnPlugin started!");

        HomeCommand homeCommand = new HomeCommand(this);
        getCommand("sethome").setExecutor(homeCommand);
        getCommand("home").setExecutor(homeCommand);
        getCommand("clearhome").setExecutor(homeCommand);
        getCommand("clearhomes").setExecutor(homeCommand);

        getCommand("back").setExecutor(new BackCommand(this));

        SpawnCommand spawnCommand = new SpawnCommand(this);
        getCommand("spawn").setExecutor(spawnCommand);
    }

    @Override
    public void onDisable() {
        getLogger().info("HomeSpawnPlugin stopped.");
    }
}