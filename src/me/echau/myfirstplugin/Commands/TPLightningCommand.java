package me.echau.myfirstplugin.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class TPLightningCommand implements CommandExecutor {
	@Override
	public boolean onCommand(final CommandSender theSender, final Command cmd, final String commandLabel,
			final String args[]) {
		if (theSender instanceof Player) {
			final Player player = (Player) theSender;
			if (commandLabel.equalsIgnoreCase("tplightning") || commandLabel.equalsIgnoreCase("tpl")) {
				if (player.hasPermission("myfirstplugin.tplightning")) {
					if (args.length == 1) {
						final Player targetPlayer = Bukkit.getPlayer(args[0]);
						if (targetPlayer != null) {
							final Location teleportLocation = new Location(targetPlayer.getWorld(), targetPlayer.getLocation().getX(), targetPlayer.getLocation().getY(), targetPlayer.getLocation().getZ());
							player.teleport(teleportLocation);
							player.sendMessage(ChatColor.GOLD + "Teleported to " + ChatColor.RED + targetPlayer.getName() + ChatColor.GOLD + " and struck them with lightning!");
							player.getWorld().strikeLightningEffect(teleportLocation);	//Does not actually do any damage or set fire. Use strikeLightning(Location) if you want to do damage and set fire. 
							targetPlayer.sendMessage(ChatColor.GOLD + "Spooky bats!!!");
							
							//Spawn 10 bats at the teleport location
							for (int i = 0; i < 10; i++) {
								player.getWorld().spawnEntity(teleportLocation, EntityType.BAT);
							}
						} else {
							player.sendMessage(ChatColor.RED + "Could not find the player " + ChatColor.DARK_RED + args[0] + ChatColor.RED + "!");
						}
					} else {
						player.sendMessage(ChatColor.RED + "Command usage: /tplightning <player>");
					}
				} else {
					player.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
				}
			}
		}
		return true;
	}
}
