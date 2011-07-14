package me.KillerSmurf.BedRestore;

import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Logger;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;

public class BedRestore extends JavaPlugin {

   
	private final BedRestorePlayerListener PlayerListener = new BedRestorePlayerListener(this);
	
  
	
	Logger log = Logger.getLogger("Minecraft");


	public void onDisable() {
		log.info("[BedRestore] has been disabled!");
	}

	public void onEnable() {
		log.info("[BedRestore] has been Enabled!");
		
        PluginManager pm = this.getServer().getPluginManager();
        
        pm.registerEvent(Event.Type.PLAYER_BED_LEAVE, PlayerListener, Event.Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_BED_ENTER, PlayerListener, Event.Priority.Normal, this);
	}
	
}