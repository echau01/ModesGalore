package me.echau.myfirstplugin.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RTeleportCommand implements CommandExecutor {
	@Override
	public boolean onCommand(final CommandSender theSender, final Command cmd, final String commandLabel,
			final String args[]) {
		if (theSender instanceof Player) {
			final Player player = (Player) theSender;
			if (commandLabel.equalsIgnoreCase("rteleport") || commandLabel.equalsIgnoreCase("rtp")) { //If player typed in "/rteleport"
				if (player.hasPermission("myfirstplugin.rteleport")) { //Check if the player has perms:
					final int randomX = (int) (Math.random() * 20000 - 10000); //x coordinate = A random integer from -10000 to 10000
					final int randomZ = (int) (Math.random() * 20000 - 10000); //z coordinate = A random integer from -10000 to 10000
					final int randomY = player.getWorld().getHighestBlockYAt(randomX, randomZ); //y coordinate = The highest block at the x and z coordinates. Guarantees that the player doesn't teleport in the ground.
					final Location randomLocation = new Location(player.getWorld(), randomX, randomY, randomZ);
					final Material blockUnderneath = randomLocation.getBlock().getRelative(BlockFace.DOWN).getType();	//Gets the block that the player will tp to, then gets the block directly beneath it.
					if (blockUnderneath == Material.STATIONARY_WATER || blockUnderneath == Material.STATIONARY_LAVA) { //If the block underneath the player is water or lava
						player.getPlayer().getServer().dispatchCommand(player, "rtp");	//Forces the player to run the command again.
					} else {	//If the block underneath is not water or lava, tp the player:
						player.sendMessage(ChatColor.GOLD + "Teleporting..."); //Sends message.
						player.teleport(randomLocation); //Teleports the player.
						player.sendMessage(ChatColor.GOLD + "You were randomly teleported to X: " + ChatColor.RED + Integer.toString(randomX) + ChatColor.GOLD
								+ ", Y: " + ChatColor.RED + Integer.toString(randomY) + ChatColor.GOLD
								+ ", Z: " + ChatColor.RED + Integer.toString(randomZ)); //Sends success message.
					}
				} else {
					player.sendMessage(ChatColor.RED + "You don't have permission to use this command!"); //Sends failure.
				}
			}
		}
		return true;
	}
}
