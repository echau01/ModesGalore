package me.echau.myfirstplugin.Commands.Modes;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.echau.myfirstplugin.Main;

public class EnablePoopingCommand implements CommandExecutor {
	private final Main plugin;
	
	public EnablePoopingCommand(final Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(final CommandSender theSender, final Command cmd, final String commandLabel,
			final String[] args) {
		if (theSender instanceof Player) {
			final Player player = (Player) theSender;
			if (commandLabel.equalsIgnoreCase("enablepooping") || commandLabel.equalsIgnoreCase("ep")) {
				if (args.length == 0) {
					if (player.hasPermission("myfirstplugin.enablepooping")) {
						if (!plugin.getConfig().contains("uuids." + player.getUniqueId() + ".EnablePooping")) {
							plugin.getConfig().set("uuids." + player.getUniqueId() + ".EnablePooping", true);
							plugin.saveConfig();
							plugin.reloadConfig();
							player.sendMessage(ChatColor.AQUA + "Pooping " + ChatColor.GREEN + "enabled.");
						} else {
							if (!plugin.getConfig().getBoolean("uuids." + player.getUniqueId() + ".EnablePooping")) {
								plugin.getConfig().set("uuids." + player.getUniqueId() + ".EnablePooping", true);
								plugin.saveConfig();
								plugin.reloadConfig();
								player.sendMessage(ChatColor.AQUA + "Pooping " + ChatColor.GREEN + "enabled.");
							} else {
								plugin.getConfig().set("uuids." + player.getUniqueId() + ".EnablePooping", false);
								plugin.saveConfig();
								plugin.reloadConfig();
								player.sendMessage(ChatColor.AQUA + "Pooping " + ChatColor.RED + "disabled.");
							}
						}
					} else {
						player.sendMessage(ChatColor.RED + "You don't have sufficient permissions!");
					}
				} else if (args.length == 1) {
					if (player.hasPermission("myfirstplugin.enablepooping.player")) {
						final Player playerBeingToggled = Bukkit.getPlayer(args[0]);
						if (playerBeingToggled != null) {
							if (!plugin.getConfig().contains("uuids." + playerBeingToggled.getUniqueId() + ".EnablePooping")) {
								plugin.getConfig().set("uuids." + playerBeingToggled.getUniqueId() + ".EnablePooping", true);
								plugin.saveConfig();
								plugin.reloadConfig();
								player.sendMessage(ChatColor.GREEN + "Enabled " + ChatColor.AQUA + "Pooping " + ChatColor.GREEN + "for " + args[0] + ChatColor.AQUA + ".");
								playerBeingToggled.sendMessage(ChatColor.AQUA + "Pooping " + ChatColor.GREEN + "enabled " + ChatColor.AQUA + "by " + ChatColor.GREEN + player.getName() + ChatColor.AQUA + ".");
							} else {
								if (!plugin.getConfig().getBoolean("uuids." + playerBeingToggled.getUniqueId() + ".EnablePooping")) {
									plugin.getConfig().set("uuids." + playerBeingToggled.getUniqueId() + ".EnablePooping", true);
									plugin.saveConfig();
									plugin.reloadConfig();
									player.sendMessage(ChatColor.GREEN + "Enabled " + ChatColor.AQUA + "Pooping " + ChatColor.GREEN + "for " + args[0] + ChatColor.AQUA + ".");
									playerBeingToggled.sendMessage(ChatColor.AQUA + "Pooping " + ChatColor.GREEN + "enabled " + ChatColor.AQUA + "by " + ChatColor.GREEN + player.getName() + ChatColor.AQUA + ".");
								} else {
									plugin.getConfig().set("uuids." + playerBeingToggled.getUniqueId() + ".EnablePooping", false);
									plugin.saveConfig();
									plugin.reloadConfig();
									player.sendMessage(ChatColor.RED + "Disabled " + ChatColor.AQUA + "Pooping " + ChatColor.RED + "for " + args[0] + ChatColor.AQUA + ".");
									playerBeingToggled.sendMessage(ChatColor.AQUA + "Pooping " + ChatColor.RED + "disabled " + ChatColor.AQUA + "by " + ChatColor.RED + player.getName() + ChatColor.AQUA + ".");
								}
							}
						} else {
							player.sendMessage(ChatColor.RED + "Could not find the player " + ChatColor.DARK_RED + args[0] + ChatColor.RED + "!");
						}
					} else {
						player.sendMessage(ChatColor.RED + "You don't have sufficient permissions!");
					}
				} else {
					player.sendMessage(ChatColor.RED + "Incorrect arguments! Command usage: /enablepooping [player]");
				}
			}
		}
		return true;
	}
}
