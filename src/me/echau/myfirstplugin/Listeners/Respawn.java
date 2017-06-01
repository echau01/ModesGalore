package me.echau.myfirstplugin.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import me.echau.myfirstplugin.Main;

public class Respawn implements Listener {
	private final Main plugin;
	
	public Respawn(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void respawnAtSetSpawn(PlayerRespawnEvent event) {
		Player player = event.getPlayer();
		if (player.getBedSpawnLocation() == null) {	//Check if bed spawn location exists, if not, use set spawn location.
			World world = player.getWorld();
			String worldName = world.getName();
			double x = plugin.getConfig().getDouble("spawnlocation." + worldName + ".x");
			double y = plugin.getConfig().getDouble("spawnlocation." + worldName + ".y");
			double z = plugin.getConfig().getDouble("spawnlocation." + worldName + ".z");
			float yaw = (float) plugin.getConfig().getInt("spawnlocation." + worldName + ".yaw");
			float pitch = (float) plugin.getConfig().getInt("spawnlocation." + worldName + ".pitch");
			Location spawnLocation = new Location(world, x, y, z, yaw, pitch);
			Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
				
				@Override
				public void run() {
					player.teleport(spawnLocation);
				}
				
			}, 1L);
		} else {
			Location bedSpawnLocation = player.getBedSpawnLocation();
			Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
				
				@Override
				public void run() {
					player.teleport(bedSpawnLocation);
				}
				
			}, 1L);
		}
	}
}
