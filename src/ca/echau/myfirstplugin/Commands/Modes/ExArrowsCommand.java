package ca.echau.myfirstplugin.Commands.Modes;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ca.echau.myfirstplugin.Main;

public class ExArrowsCommand implements CommandExecutor {
	private final Main plugin;
	
	public ExArrowsCommand(final Main plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(final CommandSender theSender, final Command cmd, final String commandLabel,
			final String[] args) {
		if (theSender instanceof Player) {
			final Player player = (Player) theSender;
			if (commandLabel.equalsIgnoreCase("exarrows")) {
				if (player.hasPermission("myfirstplugin.exarrows")) {
					if (args.length == 0) {
						if (!plugin.getConfig().contains("uuids." + player.getUniqueId() + ".ExArrows")) {
							plugin.getConfig().set("uuids." + player.getUniqueId() + ".ExArrows", true);
							plugin.saveConfig();
							plugin.reloadConfig();
							player.sendMessage(ChatColor.AQUA + "Explosive Arrows " + ChatColor.GREEN + "enabled.");
						} else {
							if (!plugin.getConfig().getBoolean("uuids." + player.getUniqueId() + ".ExArrows")) {
								plugin.getConfig().set("uuids." + player.getUniqueId() + ".ExArrows", true);
								plugin.saveConfig();
								plugin.reloadConfig();
								player.sendMessage(ChatColor.AQUA + "Explosive Arrows " + ChatColor.GREEN + "enabled.");
							} else {
								plugin.getConfig().set("uuids." + player.getUniqueId() + ".ExArrows", false);
								plugin.saveConfig();
								plugin.reloadConfig();
								player.sendMessage(ChatColor.AQUA + "Explosive Arrows " + ChatColor.RED + "disabled.");
							}
						}
					} else if (args.length == 1) {
						if (player.hasPermission("myfirstplugin.exarrows.player")) {
							final Player playerBeingToggled = Bukkit.getPlayer(args[0]);
							if (playerBeingToggled != null) {
								if (!plugin.getConfig().contains("uuids." + playerBeingToggled.getUniqueId() + ".ExArrows")) {
									plugin.getConfig().set("uuids." + playerBeingToggled.getUniqueId() + ".ExArrows", true);
									plugin.saveConfig();
									plugin.reloadConfig();
									player.sendMessage(ChatColor.GREEN + "Enabled " + ChatColor.AQUA + "Explosive Arrows " + ChatColor.GREEN + "for " + args[0] + ChatColor.AQUA + ".");
									playerBeingToggled.sendMessage(ChatColor.AQUA + "Explosive Arrows " + ChatColor.GREEN + "enabled " + ChatColor.AQUA + "by " + ChatColor.GREEN + player.getName() + ChatColor.AQUA + ".");
								} else {
									if (!plugin.getConfig().getBoolean("uuids." + playerBeingToggled.getUniqueId() + ".ExArrows")) {
										plugin.getConfig().set("uuids." + playerBeingToggled.getUniqueId() + ".ExArrows", true);
										plugin.saveConfig();
										plugin.reloadConfig();
										player.sendMessage(ChatColor.GREEN + "Enabled " + ChatColor.AQUA + "Explosive Arrows " + ChatColor.GREEN + "for " + args[0] + ChatColor.AQUA + ".");
										playerBeingToggled.sendMessage(ChatColor.AQUA + "Explosive Arrows " + ChatColor.GREEN + "enabled " + ChatColor.AQUA + "by " + ChatColor.GREEN + player.getName() + ChatColor.AQUA + ".");
									} else {
										plugin.getConfig().set("uuids." + playerBeingToggled.getUniqueId() + ".ExArrows", false);
										plugin.saveConfig();
										plugin.reloadConfig();
										player.sendMessage(ChatColor.RED + "Disabled " + ChatColor.AQUA + "Explosive Arrows " + ChatColor.RED + "for " + args[0] + ChatColor.AQUA + ".");
										playerBeingToggled.sendMessage(ChatColor.AQUA + "Explosive Arrows " + ChatColor.RED + "disabled " + ChatColor.AQUA + "by " + ChatColor.RED + player.getName() + ChatColor.AQUA + ".");
									}
								}
							} else {
								player.sendMessage(ChatColor.RED + "Could not find the player " + ChatColor.DARK_RED + args[0] + ChatColor.RED + "!");
							}
						} else {
							player.sendMessage(ChatColor.RED + "You don't have sufficient permissions!");
						}
					} else {
						player.sendMessage(ChatColor.RED + "Incorrect arguments! Command usage: /exarrows [player]");
					}
				} else {
					player.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
				}
			}
		}
		return true;
	}
}
