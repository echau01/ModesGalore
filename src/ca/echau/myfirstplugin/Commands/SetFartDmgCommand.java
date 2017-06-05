package ca.echau.myfirstplugin.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ca.echau.myfirstplugin.Main;

public class SetFartDmgCommand implements CommandExecutor {
	private final Main plugin;
	
	public SetFartDmgCommand(final Main plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(final CommandSender theSender, final Command cmd, final String commandLabel,
			final String args[]) {
		if (theSender instanceof Player) {
			final Player player = (Player) theSender;
			if (commandLabel.equalsIgnoreCase("setfartdmg")) {
				if (player.hasPermission("myfirstplugin.setfartdmg")) {
					if (args.length == 1) {
						final double numHearts = Double.parseDouble(args[0]);
						plugin.getConfig().set("FartsDamageInHearts", numHearts);
						plugin.saveConfig();
						plugin.reloadConfig();
						player.sendMessage(ChatColor.GOLD + "Set the damage of a fart to " 
							+ ChatColor.RED + numHearts + ChatColor.GOLD + " hearts.");
					} else {
						player.sendMessage(ChatColor.RED + "Invalid arguments! Command usage: /setfartdmg <number of hearts>");
					}
				} else {
					player.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
				}
			}
		}
		return true;
	}
}
