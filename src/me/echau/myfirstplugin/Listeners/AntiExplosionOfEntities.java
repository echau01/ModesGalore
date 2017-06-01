package me.echau.myfirstplugin.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import me.echau.myfirstplugin.Main;

public class AntiExplosionOfEntities implements Listener {
	private final Main plugin;
	
	public AntiExplosionOfEntities(Main plugin) {
		this.plugin = plugin;
	}
	
	//Explosions created by Bukkit.getServer().createExplosion() do NOT trigger EntityExplodeEvent!
	@EventHandler
	public void onExplosion(EntityExplodeEvent event) {
		if (plugin.getConfig().getBoolean("AntiExplosionOfEntities")) {
			event.setCancelled(true);
		}
	}
}