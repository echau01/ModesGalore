package ca.echau.myfirstplugin.Commands;

import java.text.DecimalFormat;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RectangleAreaCommand implements CommandExecutor {
	@Override
	public boolean onCommand(final CommandSender theSender, final Command cmd, final String commandLabel,
			final String args[]) {
		if (theSender instanceof Player) {
			final Player player = (Player) theSender;
			if (commandLabel.equalsIgnoreCase("rectanglearea") || commandLabel.equalsIgnoreCase("recarea")) {
				if (player.hasPermission("myfirstplugin.rectanglearea")) {
					if (args.length == 2) { //ONLY specify length and width, no more, no less
						try { //There's a possibility the player does not specify actual numbers.
							final double recLength = Double.parseDouble(args[0]);
							final double recWidth = Double.parseDouble(args[1]);
							if (recLength >= 0 && recWidth >= 0) {	//Length and width must be POSITIVE!
								final double recArea = recLength * recWidth;
								final DecimalFormat df = new DecimalFormat();
								df.setDecimalSeparatorAlwaysShown(false);
								player.sendMessage(ChatColor.GOLD + "The area of a " + ChatColor.RED + df.format(recLength)
									+ ChatColor.GOLD + " by " + ChatColor.RED + df.format(recWidth)
									+ ChatColor.GOLD + " rectangle is " + ChatColor.RED + df.format(recArea)
									+ ChatColor.GOLD + ".");
								return true;
							} else { //Specified negative length and/or width
								player.sendMessage(ChatColor.RED + "Please specify a length and a width that are both positive.");
							}
						} catch (NumberFormatException exception) { //Did not specify actual numbers
							player.sendMessage(ChatColor.RED + "Please specify a length and a width that are both positive numbers.");
						}
					} else if (args.length > 2) { //Too many arguments!
						player.sendMessage(ChatColor.RED + "Incorrect arguments! Please specify ONLY a length and a width.");
					} else { //Too few arguments!
						player.sendMessage(ChatColor.RED + "Incorrect arguments! Please specify a length and a width.");
					}
				} else {
					player.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
				}
			}
		}
		return false;
	}
}
