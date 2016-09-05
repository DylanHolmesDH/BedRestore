package me.KillerSmurf.BedRestore;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.util.config.Configuration;

public class BedRestore extends JavaPlugin {

    private final BedRestorePlayerListener pl = new BedRestorePlayerListener();
    private static int healthgotten;

    Logger log = Logger.getLogger("Minecraft");

    public void onDisable() {
        log.info("[BedRestore] has been disabled!");
    }

    public void onEnable() {
        new File("plugins" + File.separator + "BedRestore").mkdir();
        File configFile = new File("plugins/BedRestore/config.yml");
        if (!configFile.exists()) {
            log.info("[BedRestore]Config file not found. Creating config file...");
            try {
                configFile.createNewFile(); //Create the config file if it's not here already
            } catch (Exception ex) {
                log.info("Error creating the config file. >:-O");
            }
        }
        Configuration config = new Configuration(configFile);
        config.load();
        if (config.getInt("settings.health", 0) == 0) {
            config.setProperty("settings.health", 20);
        }
        config.save();
        config.load();
        healthgotten = config.getInt("settings.health", 20);
        config.save();
        log.info("[BedRestore] has been Enabled!");

        PluginManager pm = this.getServer().getPluginManager();

        pm.registerEvent(Event.Type.PLAYER_BED_LEAVE, pl, Event.Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_BED_ENTER, pl, Event.Priority.Normal, this);
    }

    public class BedRestorePlayerListener extends PlayerListener {

        public void onPlayerBedLeave(PlayerBedLeaveEvent event) {

            Player player = event.getPlayer();
            int health = player.getHealth();
            int newhealth = health + BedRestore.healthgotten > 20 ? 20 : health + BedRestore.healthgotten;
            player.setHealth(newhealth);
            player.sendMessage(ChatColor.YELLOW + "You awake from a deep sleep, and feel much better.");

        }

        public void onPlayerBedEnter(PlayerBedEnterEvent event) {
            Player player = event.getPlayer();
            player.sendMessage(ChatColor.BLUE + "You fall into a deep sleep...");

        }
    }

}
