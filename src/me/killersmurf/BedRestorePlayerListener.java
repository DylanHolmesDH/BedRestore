package me.KillerSmurf.BedRestore;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerListener;

public class BedRestorePlayerListener extends PlayerListener {

	
	public static BedRestore plugin;
	
	public BedRestorePlayerListener(BedRestore instance) {
		plugin = instance;
	}

	public void onPlayerBedLeave(PlayerBedLeaveEvent event) {
		
		Player player = event.getPlayer();
	    player.setHealth(20);
		player.sendMessage(ChatColor.YELLOW + "You awake from a deep sleep, and feel much better.");

	}
	public void onPlayerBedEnter(PlayerBedEnterEvent event){
		Player player = event.getPlayer();
		player.sendMessage(ChatColor.BLUE + "You fall into a deep sleep...");
		
	}
}