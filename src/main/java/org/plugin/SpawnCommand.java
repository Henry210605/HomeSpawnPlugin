package org.plugin;

import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SpawnCommand implements CommandExecutor {

    public SpawnCommand(JavaPlugin plugin) {}

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Nur Spieler können diesen Befehl nutzen.");
            return true;
        }

        player.teleport(player.getWorld().getSpawnLocation());
        player.sendMessage("§aZum Spawn teleportiert!");
        return true;
    }
}