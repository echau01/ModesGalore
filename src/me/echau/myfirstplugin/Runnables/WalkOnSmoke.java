package me.echau.myfirstplugin.Runnables;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import me.echau.myfirstplugin.Main;

public class WalkOnSmoke implements Runnable {
	private final Main plugin;
	
	public WalkOnSmoke(Main plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public void run() {
		for (Player player : Bukkit.getServer().getOnlinePlayers()) {
			if (plugin.getConfig().getBoolean("uuids." + player.getUniqueId() + ".WalkOnSmokeMode")) {
				if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.AIR) {
					double x = player.getLocation().getX();
					double y = player.getLocation().getY() - 1;
					double z = player.getLocation().getZ();
					Location loc = new Location(player.getWorld(), x, y, z);
					for (int i = 0; i <= 8; i++) {
						player.getWorld().playEffect(loc, Effect.SMOKE, i);
					}
				}
			}
		}
	}
}
