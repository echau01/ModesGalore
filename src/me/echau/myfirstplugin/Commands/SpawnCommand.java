package me.echau.myfirstplugin.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.echau.myfirstplugin.Main;

public class SpawnCommand implements CommandExecutor {
	private final Main plugin;
	
	public SpawnCommand(Main plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender theSender, Command cmd, String commandLabel, String args[]) {
		if (theSender instanceof Player) {
			Player player = (Player) theSender;
			if (commandLabel.equalsIgnoreCase("spawn")) {
				if (player.hasPermission("myfirstplugin.spawn")) {
					try {
						World world = player.getWorld();
						String worldName = world.getName();
						double x = plugin.getConfig().getDouble("spawnlocation." + worldName + ".x");
						double y = plugin.getConfig().getDouble("spawnlocation." + worldName + ".y");
						double z = plugin.getConfig().getDouble("spawnlocation." + worldName + ".z");
						float yaw = (float) plugin.getConfig().getInt("spawnlocation." + worldName + ".yaw");
						float pitch = (float) plugin.getConfig().getInt("spawnlocation." + worldName + ".pitch");
						
						Location spawnLocation = new Location(world, x, y, z, yaw, pitch);
						player.teleport(spawnLocation);
						player.sendMessage(ChatColor.GOLD + "Teleported to spawn!");
					} catch(NullPointerException exception) {
						player.sendMessage(ChatColor.RED + "There is no spawn set! Tell an admin to set the spawn.");
						if(player.hasPermission("myfirstplugin.setspawn")) {
							player.sendMessage(ChatColor.RED + "Please relog to set spawn.");
						}
					}
				} else {
					player.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
				}
			}
		}
		return true;
	}

}
