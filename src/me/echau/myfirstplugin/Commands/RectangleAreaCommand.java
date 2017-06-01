package me.echau.myfirstplugin.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RectangleAreaCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender theSender, Command cmd, String commandLabel, String args[]) {
		if (theSender instanceof Player) {
			Player player = (Player) theSender;
			if (commandLabel.equalsIgnoreCase("rectanglearea") || commandLabel.equalsIgnoreCase("recarea")) {
				if (player.hasPermission("myfirstplugin.rectanglearea")) {
					if (args.length == 2) { //ONLY specify length and width, no more, no less
						try { //There's a possibility the player does not specify actual numbers.
							double recLength = Double.parseDouble(args[0]);
							double recWidth = Double.parseDouble(args[1]);
							if (recLength >= 0 && recWidth >= 0) {	//Length and width must be POSITIVE!
								double recArea = recLength * recWidth;	//Calculates the rectangle's area, storing the result as a double in recArea

								if (recLength == (int) recLength || recWidth == (int) recWidth || recArea == (int) recArea) { //At least one of length, width, and area is an integer. Every possible case must be checked and is shown below.
									if (recLength == (int) recLength && recWidth == (int) recWidth) { //Length and width are both integers. Example: 2 * 5 = 10
										int intRecLength = (int) recLength;
										int intRecWidth = (int) recWidth;
										int intRecArea = (int) recArea; //If length and width are integers, area must be an integer as well.
										player.sendMessage(ChatColor.GOLD + "The area of a " + ChatColor.RED + intRecLength + ChatColor.GOLD + " by " + ChatColor.RED + intRecWidth + ChatColor.GOLD + " rectangle is " + ChatColor.RED + intRecArea + ChatColor.GOLD + ".");

									} else if (recLength == (int) recLength && recWidth != (int) recWidth && recArea == (int) recArea) { //Width isn't an integer, but length and area are. Example: 2 * 0.5 = 1
										int intRecLength = (int) recLength;
										int intRecArea = (int) recArea;
										player.sendMessage(ChatColor.GOLD + "The area of a " + ChatColor.RED + intRecLength + ChatColor.GOLD + " by " + ChatColor.RED + recWidth + ChatColor.GOLD + " rectangle is " + ChatColor.RED + intRecArea + ChatColor.GOLD + ".");

									} else if (recLength != (int) recLength && recWidth == (int) recWidth && recArea == (int) recArea) { //Length isn't an integer, but width and area are. Example: 1.5 * 2 = 3
										int intRecWidth = (int) recWidth;
										int intRecArea = (int) recArea;
										player.sendMessage(ChatColor.GOLD + "The area of a " + ChatColor.RED + recLength + ChatColor.GOLD + " by " + ChatColor.RED + intRecWidth + ChatColor.GOLD + " rectangle is " + ChatColor.RED + intRecArea + ChatColor.GOLD + ".");

									} else if (recLength != (int) recLength && recWidth != (int) recWidth && recArea == (int) recArea) { //Only area is an integer. Example: 2.5 * 0.4 = 1
										int intRecArea = (int) recArea;
										player.sendMessage(ChatColor.GOLD + "The area of a " + ChatColor.RED + recLength + ChatColor.GOLD + " by " + ChatColor.RED + recWidth + ChatColor.GOLD + " rectangle is " + ChatColor.RED + intRecArea + ChatColor.GOLD + ".");

									} else if (recLength == (int) recLength && recWidth != (int) recWidth && recArea != (int) recArea) { //Only length is an integer. Example: 3 * 2.5 = 7.5
										int intRecLength = (int) recLength;
										player.sendMessage(ChatColor.GOLD + "The area of a " + ChatColor.RED + intRecLength + ChatColor.GOLD + " by " + ChatColor.RED + recWidth + ChatColor.GOLD + " rectangle is " + ChatColor.RED + recArea + ChatColor.GOLD + ".");

									} else if (recLength != (int) recLength && recWidth == (int) recWidth && recArea != (int) recArea) { //Only width is an integer. Example: 2.7 * 2 = 5.4
										int intRecWidth = (int) recWidth;
										player.sendMessage(ChatColor.GOLD + "The area of a " + ChatColor.RED + recLength + ChatColor.GOLD + " by " + ChatColor.RED + intRecWidth + ChatColor.GOLD + " rectangle is " + ChatColor.RED + recArea + ChatColor.GOLD + ".");
									}

								} else { //If NONE of length, width, and area is an integer
									player.sendMessage(ChatColor.GOLD + "The area of a " + ChatColor.RED + recLength + ChatColor.GOLD + " by " + ChatColor.RED + recWidth + ChatColor.GOLD + " rectangle is " + ChatColor.RED + recArea + ChatColor.GOLD + ".");
								}
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
		return true;
	}

}
