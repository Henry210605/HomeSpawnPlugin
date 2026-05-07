package org.plugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SpawnCommand implements CommandExecutor {

    private final JavaPlugin plugin;

    public SpawnCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Nur Spieler können diesen Befehl nutzen.");
            return true;
        }

        World targetWorld = Bukkit.getWorld("world");

        if (targetWorld == null) {
            targetWorld = player.getWorld();
        }

        Location spawn = targetWorld.getSpawnLocation();
        if (spawn == null) {
            player.sendMessage("§cSpawn-Location nicht gefunden.");
            return true;
        }

        player.teleport(spawn);
        player.sendMessage("§aZum Spawn teleportiert!");
        return true;
    }
}