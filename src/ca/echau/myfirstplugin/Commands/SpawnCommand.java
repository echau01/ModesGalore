package ca.echau.myfirstplugin.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ca.echau.myfirstplugin.Main;

public class SpawnCommand implements CommandExecutor {
	private final Main plugin;

	public SpawnCommand(final Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(final CommandSender theSender, final Command cmd, final String commandLabel,
			final String args[]) {
		if (theSender instanceof Player) {
			final Player player = (Player) theSender;
			if (commandLabel.equalsIgnoreCase("spawn")) {
				if (player.hasPermission("myfirstplugin.spawn")) {
					final World world = player.getWorld();
					final String worldName = world.getName();
					if (plugin.getConfig().contains("spawnlocation." + worldName + ".x")) {
						final double x = plugin.getConfig().getDouble("spawnlocation." + worldName + ".x");
						final double y = plugin.getConfig().getDouble("spawnlocation." + worldName + ".y");
						final double z = plugin.getConfig().getDouble("spawnlocation." + worldName + ".z");
						final float yaw = (float) plugin.getConfig().getInt("spawnlocation." + worldName + ".yaw");
						final float pitch = (float) plugin.getConfig().getInt("spawnlocation." + worldName + ".pitch");
						final Location spawnLocation = new Location(world, x, y, z, yaw, pitch);
						player.teleport(spawnLocation);
						player.sendMessage(ChatColor.GOLD + "Teleported to spawn!");
						return true;
					} else {
						if (player.hasPermission("myfirstplugin.setspawn")) {
							player.sendMessage(ChatColor.RED + "There is no spawn set! Please set a spawn with /setspawn");
						} else {
							player.sendMessage(ChatColor.RED + "There is no spawn set! Tell an admin to set the spawn.");
						}
					}
				} else {
					player.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
				}
			}
		}
		return false;
	}
}
