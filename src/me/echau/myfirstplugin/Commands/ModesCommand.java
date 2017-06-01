package me.echau.myfirstplugin.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.echau.myfirstplugin.Main;

public class ModesCommand implements CommandExecutor {
	private final Main plugin;

	public ModesCommand(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender theSender, Command cmd, String cmdLabel, String[] args) {
		if (theSender instanceof Player) {
			Player player = (Player) theSender;
			if (cmdLabel.equalsIgnoreCase("modes")) {
				if (player.hasPermission("myfirstplugin.modes")) {
					if (args.length == 0) {
						boolean modeFound = false;
						if (plugin.getConfig().getBoolean("uuids." + player.getUniqueId() + ".EnableFarts")) {
							player.sendMessage(ChatColor.GREEN + "Farts Mode " + ChatColor.GOLD + "enabled!");
							modeFound = true;
						}
						if (plugin.getConfig().getBoolean("uuids." + player.getUniqueId() + ".EnablePooping")) {
							player.sendMessage(ChatColor.GREEN + "Pooping " + ChatColor.GOLD + "enabled!");
							modeFound = true;
						}
						if (plugin.getConfig().getBoolean("uuids." + player.getUniqueId() + ".ExArrows")) {
							player.sendMessage(ChatColor.GREEN + "Explosive Arrows " + ChatColor.GOLD + "enabled!");
							modeFound = true;
						}
						if (plugin.getConfig().getBoolean("uuids." + player.getUniqueId() + ".LaunchMode")) {
							player.sendMessage(ChatColor.GREEN + "Launch Mode " + ChatColor.GOLD + "enabled!");
							modeFound = true;
						}
						if (plugin.getConfig().getBoolean("uuids." + player.getUniqueId() + ".RainbowArmor")) {
							player.sendMessage(ChatColor.GREEN + "Rainbow Armor " + ChatColor.GOLD + "enabled!");
							modeFound = true;
						}
						if (plugin.getConfig().getBoolean("uuids." + player.getUniqueId() + ".WalkOnSmokeMode")) {
							player.sendMessage(ChatColor.GREEN + "WalkOnSmoke Mode " + ChatColor.GOLD + "enabled!");
							modeFound = true;
						}
						if (!modeFound) {
							player.sendMessage(ChatColor.GOLD + "You have no modes enabled!");
						}
					} else if (args.length == 1) {
						if (player.hasPermission("myfirstplugin.modes.player")) {
							if (Bukkit.getPlayer(args[0]) != null) {
								Player targetPlayer = Bukkit.getPlayer(args[0]);
								boolean modeFound = false;
								if (plugin.getConfig().getBoolean("uuids." + targetPlayer.getUniqueId() + ".EnableFarts")) {
									player.sendMessage(ChatColor.RED + targetPlayer.getName() + ChatColor.GOLD + " has " + ChatColor.GREEN + "Farts Mode " + ChatColor.GOLD + "enabled!");
									modeFound = true;
								}
								if (plugin.getConfig().getBoolean("uuids." + targetPlayer.getUniqueId() + ".EnablePooping")) {
									player.sendMessage(ChatColor.RED + targetPlayer.getName() + ChatColor.GOLD + " has " + ChatColor.GREEN + "Pooping " + ChatColor.GOLD + "enabled!");
									modeFound = true;
								}
								if (plugin.getConfig().getBoolean("uuids." + targetPlayer.getUniqueId() + ".ExArrows")) {
									player.sendMessage(ChatColor.RED + targetPlayer.getName() + ChatColor.GOLD + " has " + ChatColor.GREEN + "Explosive Arrows " + ChatColor.GOLD + "enabled!");
									modeFound = true;
								}
								if (plugin.getConfig().getBoolean("uuids." + targetPlayer.getUniqueId() + ".LaunchMode")) {
									player.sendMessage(ChatColor.RED + targetPlayer.getName() + ChatColor.GOLD + " has " + ChatColor.GREEN + "Launch Mode " + ChatColor.GOLD + "enabled!");
									modeFound = true;
								}
								if (plugin.getConfig().getBoolean("uuids." + targetPlayer.getUniqueId() + ".RainbowArmor")) {
									player.sendMessage(ChatColor.RED + targetPlayer.getName() + ChatColor.GOLD + " has " + ChatColor.GREEN + "Rainbow Armor Mode " + ChatColor.GOLD + "enabled!");
									modeFound = true;
								}
								if (plugin.getConfig().getBoolean("uuids." + targetPlayer.getUniqueId() + ".WalkOnSmokeMode")) {
									player.sendMessage(ChatColor.RED + targetPlayer.getName() + ChatColor.GOLD + " has " + ChatColor.GREEN + "WalkOnSmoke Mode " + ChatColor.GOLD + "enabled!");
									modeFound = true;
								}
								if (!modeFound) {
									player.sendMessage(ChatColor.RED + targetPlayer.getName() + ChatColor.GOLD + " has no modes enabled!");
								}
							} else {
								player.sendMessage(ChatColor.RED + "Could not find the player " + ChatColor.DARK_RED + args[0] + ChatColor.RED + "!");
							}
						} else {
							player.sendMessage(ChatColor.RED + "You don't have sufficient permissions!");
						}
					} else {
						player.sendMessage(ChatColor.RED + "Invalid arguments! Command usage: /modes [player]");
					}
				} else {
					player.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
				}
			}
		}
		return true;
	}
}
