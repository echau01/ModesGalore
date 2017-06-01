package me.echau.myfirstplugin.Commands.Modes;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.echau.myfirstplugin.Main;

public class LaunchModeCommand implements CommandExecutor {
	Main plugin;
	public LaunchModeCommand(Main plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender theSender, Command cmd, String commandLabel, String[] args) {
		if (theSender instanceof Player) {
			Player player = (Player) theSender;
			if (commandLabel.equalsIgnoreCase("launchmode") || commandLabel.equalsIgnoreCase("lm")) {
				if (player.hasPermission("myfirstplugin.launchmode")) {
					if (args.length == 0) {
						if (!plugin.getConfig().contains("uuids." + player.getUniqueId() + ".LaunchMode")) {
							plugin.getConfig().set("uuids." + player.getUniqueId() + ".LaunchMode", true);
							plugin.saveConfig();
							plugin.reloadConfig();
							player.sendMessage(ChatColor.AQUA + "Launch Mode " + ChatColor.GREEN + "enabled.");
						} else {
							if (!plugin.getConfig().getBoolean("uuids." + player.getUniqueId() + ".LaunchMode")) {
								plugin.getConfig().set("uuids." + player.getUniqueId() + ".LaunchMode", true);
								plugin.saveConfig();
								plugin.reloadConfig();
								player.sendMessage(ChatColor.AQUA + "Launch Mode " + ChatColor.GREEN + "enabled.");
							} else {
								plugin.getConfig().set("uuids." + player.getUniqueId() + ".LaunchMode", false);
								plugin.saveConfig();
								plugin.reloadConfig();
								player.sendMessage(ChatColor.AQUA + "Launch Mode " + ChatColor.RED + "disabled.");
							}
						}
					} else if (args.length == 1) {
						if (player.hasPermission("myfirstplugin.launchmode.player")) {
							Player playerBeingToggled = Bukkit.getPlayer(args[0]);
							if (playerBeingToggled != null) {
								if (!plugin.getConfig().contains("uuids." + playerBeingToggled.getUniqueId() + ".LaunchMode")) {
									plugin.getConfig().set("uuids." + playerBeingToggled.getUniqueId() + ".LaunchMode", true);
									plugin.saveConfig();
									plugin.reloadConfig();
									player.sendMessage(ChatColor.GREEN + "Enabled " + ChatColor.AQUA + "Launch Mode " + ChatColor.GREEN + "for " + args[0] + ChatColor.AQUA + ".");
									playerBeingToggled.sendMessage(ChatColor.AQUA + "Launch Mode" + ChatColor.GREEN + "enabled " + ChatColor.AQUA + "by " + ChatColor.GREEN + player.getName() + ChatColor.AQUA + ".");
								} else {
									if (!plugin.getConfig().getBoolean("uuids." + playerBeingToggled.getUniqueId() + ".LaunchMode")) {
										plugin.getConfig().set("uuids." + playerBeingToggled.getUniqueId() + ".LaunchMode", true);
										plugin.saveConfig();
										plugin.reloadConfig();
										player.sendMessage(ChatColor.GREEN + "Enabled " + ChatColor.AQUA + "Launch Mode " + ChatColor.GREEN + "for " + args[0] + ChatColor.AQUA + ".");
										playerBeingToggled.sendMessage(ChatColor.AQUA + "Launch Mode " + ChatColor.GREEN + "enabled " + ChatColor.AQUA + "by " + ChatColor.GREEN + player.getName() + ChatColor.AQUA + ".");
									} else {
										plugin.getConfig().set("uuids." + playerBeingToggled.getUniqueId() + ".LaunchMode", false);
										plugin.saveConfig();
										plugin.reloadConfig();
										player.sendMessage(ChatColor.RED + "Disabled " + ChatColor.AQUA + "Launch Mode " + ChatColor.RED + "for " + args[0] + ChatColor.AQUA + ".");
										playerBeingToggled.sendMessage(ChatColor.AQUA + "Launch Mode " + ChatColor.RED + "disabled " + ChatColor.AQUA + "by " + ChatColor.RED + player.getName() + ChatColor.AQUA + ".");
									}
								}
							} else {
								player.sendMessage(ChatColor.RED + "Could not find the player " + ChatColor.DARK_RED + args[0] + ChatColor.RED + "!");
							}
						} else {
							player.sendMessage(ChatColor.RED + "You don't have sufficient permissions!");
						}
					} else {
						player.sendMessage(ChatColor.RED + "Incorrect arguments! Command usage: /launchmode [player]");
					}
				} else {
					player.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
				}
			}
		}
		return true;
	}
}
