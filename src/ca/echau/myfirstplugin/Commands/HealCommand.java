package ca.echau.myfirstplugin.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {
	@Override
	public boolean onCommand(final CommandSender theSender, final Command cmd, final String commandLabel,
			final String args[]) {
		if (theSender instanceof Player) {
			final Player player = (Player) theSender;
			if (commandLabel.equalsIgnoreCase("heal")) {
				if (player.hasPermission("myfirstplugin.heal")) {
					if (args.length == 0) {
						player.setHealth(20); //Remember this is 20 "half-points" -- 10 full hearts
						player.sendMessage(ChatColor.GOLD + "You have been healed.");
					} else if (args.length == 1) {
						if (Bukkit.getPlayer(args[0]) != null) {
							final Player playerToHeal = Bukkit.getPlayer(args[0]);
							playerToHeal.setHealth(20);
							player.sendMessage(ChatColor.GOLD + "You have healed " + ChatColor.RED + playerToHeal.getName() + ChatColor.GOLD + ".");
							playerToHeal.sendMessage(ChatColor.GOLD + "You have been healed by " + ChatColor.RED + playerToHeal.getName() + ChatColor.GOLD + ".");
						} else {
							player.sendMessage(ChatColor.RED + "Could not find the player " + ChatColor.DARK_RED + args[0] + ChatColor.RED + "!");
						}
					} else {
						player.sendMessage(ChatColor.RED + "Invalid arguments! Command usage: " + ChatColor.GOLD + "/heal (player)");
					}
				} else {
					player.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
				}
			}
		}
		return true;
	}
}
