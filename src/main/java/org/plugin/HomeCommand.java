package org.plugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.*;
import org.bukkit.configuration.file.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class HomeCommand implements CommandExecutor {

    private final JavaPlugin plugin;
    private FileConfiguration homesConfig;
    private File homesFile;

    public HomeCommand(JavaPlugin plugin) {
        this.plugin = plugin;
        loadHomesFile();
    }

    private void loadHomesFile() {
        homesFile = new File(plugin.getDataFolder(), "homes.yml");
        if (!homesFile.exists()) {
            homesFile.getParentFile().mkdirs();
            try { homesFile.createNewFile(); } catch (IOException e) { e.printStackTrace(); }
        }
        homesConfig = YamlConfiguration.loadConfiguration(homesFile);
    }

    private void saveHomesFile() {
        try { homesConfig.save(homesFile); } catch (IOException e) { e.printStackTrace(); }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Nur Spieler können diesen Befehl nutzen.");
            return true;
        }

        String uuid = player.getUniqueId().toString();

        switch (command.getName().toLowerCase()) {
            case "sethome" -> {
                Location loc = player.getLocation();
                homesConfig.set(uuid + ".world", loc.getWorld().getName());
                homesConfig.set(uuid + ".x", loc.getX());
                homesConfig.set(uuid + ".y", loc.getY());
                homesConfig.set(uuid + ".z", loc.getZ());
                homesConfig.set(uuid + ".yaw", loc.getYaw());
                saveHomesFile();
                player.sendMessage("§aHome gesetzt!");
            }
            case "home" -> {
                if (!homesConfig.contains(uuid)) {
                    player.sendMessage("§cDu hast noch kein Home gesetzt. Nutze /sethome.");
                    return true;
                }
                String worldName = homesConfig.getString(uuid + ".world");
                double x = homesConfig.getDouble(uuid + ".x");
                double y = homesConfig.getDouble(uuid + ".y");
                double z = homesConfig.getDouble(uuid + ".z");
                float yaw = (float) homesConfig.getDouble(uuid + ".yaw");

                Location home = new Location(Bukkit.getWorld(worldName), x, y, z, yaw, 0);
                player.teleport(home);
                player.sendMessage("§aNach Hause teleportiert!");
            }
        }
        return true;
    }
}