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
	
	public Respawn(final Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void respawnAtSetSpawn(final PlayerRespawnEvent event) {
		final Player player = event.getPlayer();
		if (player.getBedSpawnLocation() == null) {	//Check if bed spawn location exists, if not, use set spawn location.
			final World world = player.getWorld();
			final String worldName = world.getName();
			final double x = plugin.getConfig().getDouble("spawnlocation." + worldName + ".x");
			final double y = plugin.getConfig().getDouble("spawnlocation." + worldName + ".y");
			final double z = plugin.getConfig().getDouble("spawnlocation." + worldName + ".z");
			final float yaw = (float) plugin.getConfig().getInt("spawnlocation." + worldName + ".yaw");
			final float pitch = (float) plugin.getConfig().getInt("spawnlocation." + worldName + ".pitch");
			final Location spawnLocation = new Location(world, x, y, z, yaw, pitch);
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
