package me.echau.myfirstplugin.Commands.Modes;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.echau.myfirstplugin.Main;

public class RainbowArmorCommand implements CommandExecutor {
	Main plugin;
	public RainbowArmorCommand(Main plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender theSender, Command cmd, String commandLabel, String[] args) {
		if (theSender instanceof Player) {
			Player player = (Player) theSender;
			if (commandLabel.equalsIgnoreCase("rainbowarmor") || commandLabel.equalsIgnoreCase("rarmor")) {
				if (player.hasPermission("myfirstplugin.rainbowarmor")) {
					if (args.length == 0) {
						if (!plugin.getConfig().contains("uuids." + player.getUniqueId() + ".RainbowArmor")) {
							plugin.getConfig().set("uuids." + player.getUniqueId() + ".RainbowArmor", true);
							plugin.saveConfig();
							plugin.reloadConfig();
							player.sendMessage(ChatColor.AQUA + "Rainbow Armor " + ChatColor.GREEN + "enabled.");
						} else {
							if (!plugin.getConfig().getBoolean("uuids." + player.getUniqueId() + ".RainbowArmor")) {
								plugin.getConfig().set("uuids." + player.getUniqueId() + ".RainbowArmor", true);
								plugin.saveConfig();
								plugin.reloadConfig();
								player.sendMessage(ChatColor.AQUA + "Rainbow Armor " + ChatColor.GREEN + "enabled.");
							} else {
								plugin.getConfig().set("uuids." + player.getUniqueId() + ".RainbowArmor", false);
								plugin.saveConfig();
								plugin.reloadConfig();
								player.sendMessage(ChatColor.AQUA + "Rainbow Armor " + ChatColor.RED + "disabled.");
							}
						}
					} else if (args.length == 1) {
						if (player.hasPermission("myfirstplugin.rainbowarmor.player")) {
							Player playerBeingToggled = Bukkit.getPlayer(args[0]);
							if (playerBeingToggled != null) {
								if (!plugin.getConfig().contains("uuids." + playerBeingToggled.getUniqueId() + ".RainbowArmor")) {
									plugin.getConfig().set("uuids." + playerBeingToggled.getUniqueId() + ".RainbowArmor", true);
									plugin.saveConfig();
									plugin.reloadConfig();
									player.sendMessage(ChatColor.GREEN + "Enabled " + ChatColor.AQUA + "Rainbow Armor " + ChatColor.GREEN + "for " + args[0] + ChatColor.AQUA + ".");
									playerBeingToggled.sendMessage(ChatColor.AQUA + "Rainbow Armor " + ChatColor.GREEN + "enabled " + ChatColor.AQUA + "by " + ChatColor.GREEN + player.getName() + ChatColor.AQUA + ".");
								} else {
									if (!plugin.getConfig().getBoolean("uuids." + playerBeingToggled.getUniqueId() + ".RainbowArmor")) {
										plugin.getConfig().set("uuids." + playerBeingToggled.getUniqueId() + ".RainbowArmor", true);
										plugin.saveConfig();
										plugin.reloadConfig();
										player.sendMessage(ChatColor.GREEN + "Enabled " + ChatColor.AQUA + "Rainbow Armor " + ChatColor.GREEN + "for " + args[0] + ChatColor.AQUA + ".");
										playerBeingToggled.sendMessage(ChatColor.AQUA + "Rainbow Armor " + ChatColor.GREEN + "enabled " + ChatColor.AQUA + "by " + ChatColor.GREEN + player.getName() + ChatColor.AQUA + ".");
									} else {
										plugin.getConfig().set("uuids." + playerBeingToggled.getUniqueId() + ".RainbowArmor", false);
										plugin.saveConfig();
										plugin.reloadConfig();
										player.sendMessage(ChatColor.RED + "Disabled " + ChatColor.AQUA + "Rainbow Armor " + ChatColor.RED + "for " + args[0] + ChatColor.AQUA + ".");
										playerBeingToggled.sendMessage(ChatColor.AQUA + "Rainbow Armor " + ChatColor.RED + "disabled " + ChatColor.AQUA + "by " + ChatColor.RED + player.getName() + ChatColor.AQUA + ".");
									}
								}
							} else {
								player.sendMessage(ChatColor.RED + "Could not find the player " + ChatColor.DARK_RED + args[0] + ChatColor.RED + "!");
							}
						} else {
							player.sendMessage(ChatColor.RED + "You don't have sufficient permissions!");
						}
					} else {
						player.sendMessage(ChatColor.RED + "Incorrect arguments! Command usage: /rainbowarmor [player]");
					}
				} else {
					player.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
				}
			}
		}
		return true;
	}
}