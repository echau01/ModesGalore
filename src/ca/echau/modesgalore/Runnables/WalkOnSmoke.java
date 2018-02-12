package ca.echau.modesgalore.Runnables;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import ca.echau.modesgalore.Main;

public class WalkOnSmoke implements Runnable {
	private final Main plugin;
	
	public WalkOnSmoke(final Main plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public void run() {
		for (final Player player : Bukkit.getServer().getOnlinePlayers()) {
			if (plugin.getConfig().getBoolean("uuids." + player.getUniqueId() + ".WalkOnSmokeMode")) {
				if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.AIR) {
					final double x = player.getLocation().getX();
					final double y = player.getLocation().getY() - 1;
					final double z = player.getLocation().getZ();
					final Location loc = new Location(player.getWorld(), x, y, z);
					for (int i = 0; i <= 8; i++) {
						player.getWorld().playEffect(loc, Effect.SMOKE, i);
					}
				}
			}
		}
	}
}
