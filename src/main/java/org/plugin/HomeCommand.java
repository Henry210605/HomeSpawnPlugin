package org.plugin;

import org.bukkit.Location;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class HomeCommand implements CommandExecutor {

    private final HashMap<UUID, Location> homes = new HashMap<>();
    private final JavaPlugin plugin;

    public HomeCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Nur Spieler können diesen Befehl nutzen.");
            return true;
        }

        switch (command.getName().toLowerCase()) {
            case "sethome" -> {
                homes.put(player.getUniqueId(), player.getLocation());
                player.sendMessage("§aHome gesetzt!");
            }
            case "home" -> {
                Location home = homes.get(player.getUniqueId());
                if (home == null) {
                    player.sendMessage("§cDu hast noch kein Home gesetzt. Nutze /sethome.");
                } else {
                    player.teleport(home);
                    player.sendMessage("§aNach Hause teleportiert!");
                }
            }
        }
        return true;
    }
}