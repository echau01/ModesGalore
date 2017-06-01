package me.echau.myfirstplugin.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SeeYouLaterCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender theSender, Command cmd, String commandLabel, String args[]) {
		if (theSender instanceof Player) {
			Player player = (Player) theSender;
			if (commandLabel.equalsIgnoreCase("seeyoulater")) {
				if (player.hasPermission("myfirstplugin.seeyoulater")) {
					player.setHealth(0);
					for (Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()) {
						onlinePlayer.sendMessage(ChatColor.RED + player.getName() + ChatColor.GOLD + " decided to " + ChatColor.AQUA + ChatColor.BOLD + "SEE YOU LATER!");
					}
				} else {
					player.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
				}
			}
		}
		return true;
	}
}
