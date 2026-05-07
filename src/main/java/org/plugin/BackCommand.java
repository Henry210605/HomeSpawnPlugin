package org.plugin;

import org.bukkit.Location;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class BackCommand implements CommandExecutor, Listener {

    private final HashMap<UUID, Location> lastLocations = new HashMap<>();

    public BackCommand(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        lastLocations.put(player.getUniqueId(), player.getLocation());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Nur Spieler können diesen Befehl nutzen.");
            return true;
        }

        Location last = lastLocations.get(player.getUniqueId());
        if (last == null) {
            player.sendMessage("§cKein letzter Standort vorhanden.");
            return true;
        }

        player.teleport(last);
        player.sendMessage("§aZum letzten Standort teleportiert!");
        return true;
    }
}