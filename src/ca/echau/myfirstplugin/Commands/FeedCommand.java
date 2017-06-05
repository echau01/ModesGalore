package ca.echau.myfirstplugin.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FeedCommand implements CommandExecutor {
	@Override
	public boolean onCommand(final CommandSender theSender, final Command cmd, final String commandLabel,
			final String args[]) {
		if (theSender instanceof Player) {
			final Player player = (Player) theSender;
			if (commandLabel.equalsIgnoreCase("feed")) {
				if (player.hasPermission("myfirstplugin.feed")) {
					if (args.length == 0) {
						player.setFoodLevel(20); //There are 10 "drumsticks" - each drumstick is worth 2.
						player.sendMessage(ChatColor.GOLD + "You have been fed.");
						return true;
					} else if (args.length == 1) {
						if (Bukkit.getPlayer(args[0]) != null) {
							final Player playerToFeed = Bukkit.getPlayer(args[0]);
							playerToFeed.setFoodLevel(20);
							player.sendMessage(ChatColor.GOLD + "You have fed " + ChatColor.RED + playerToFeed.getName() + ChatColor.GOLD + ".");
							playerToFeed.sendMessage(ChatColor.GOLD + "You have been fed by " + ChatColor.RED + playerToFeed.getName() + ChatColor.GOLD + ".");
							return true;
						} else {
							player.sendMessage(ChatColor.RED + "Could not find the player " + ChatColor.DARK_RED + args[0] + ChatColor.RED + "!");
						}
					} else {
						player.sendMessage(ChatColor.RED + "Invalid arguments! Command usage: " + ChatColor.GOLD + "/feed (player)");
					}
				} else {
					player.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
				}
			}
		}
		return false;
	}
}
