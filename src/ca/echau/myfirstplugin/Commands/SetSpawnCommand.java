package ca.echau.myfirstplugin.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ca.echau.myfirstplugin.Main;

public class SetSpawnCommand implements CommandExecutor {
	private final Main plugin;
	
	public SetSpawnCommand(final Main plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(final CommandSender theSender, final Command cmd, final String commandLabel,
			final String args[]) {
		if (theSender instanceof Player) {
			final Player player = (Player) theSender;
			if (commandLabel.equalsIgnoreCase("setspawn")) {
				if (player.hasPermission("myfirstplugin.setspawn")) {
					if (args.length == 0) {
						final Location spawnLocation = player.getLocation();
						final String world = player.getWorld().getName();
						final double x = spawnLocation.getX();
						final double y = spawnLocation.getY();
						final double z = spawnLocation.getZ();
						final float yaw = spawnLocation.getYaw();
						final float pitch = spawnLocation.getPitch();
						plugin.getConfig().set("spawnlocation." + world + ".world", world);	//MULTIWORLD SPAWNS!
						plugin.getConfig().set("spawnlocation." + world + ".x", x);
						plugin.getConfig().set("spawnlocation." + world + ".y", y + 0.5);
						plugin.getConfig().set("spawnlocation." + world + ".z", z);
						plugin.getConfig().set("spawnlocation." + world + ".yaw", yaw);
						plugin.getConfig().set("spawnlocation." + world + ".pitch", pitch);
						plugin.saveConfig();
						plugin.reloadConfig();
						player.sendMessage(ChatColor.GOLD + "Set spawn at X: " + ChatColor.RED + (int) x
								+ ChatColor.GOLD + ", Y: " + ChatColor.RED + (int) y 
								+ ChatColor.GOLD + ", Z: " + ChatColor.RED + (int) z);
					} else {
						player.sendMessage(ChatColor.RED + "Command usage: /setspawn");
					}
				} else {
					player.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
				}
			}
		}
		return true;
	}
}
