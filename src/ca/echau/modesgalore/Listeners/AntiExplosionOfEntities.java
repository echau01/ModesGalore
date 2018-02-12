package ca.echau.modesgalore.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import ca.echau.modesgalore.Main;

public class AntiExplosionOfEntities implements Listener {
	private final Main plugin;
	
	public AntiExplosionOfEntities(final Main plugin) {
		this.plugin = plugin;
	}
	
	//Explosions created by Bukkit.getServer().createExplosion() do NOT trigger EntityExplodeEvent!
	@EventHandler
	public void onExplosion(final EntityExplodeEvent event) {
		if (plugin.getConfig().getBoolean("AntiExplosionOfEntities")) {
			event.setCancelled(true);
		}
	}
}