package me.echau.myfirstplugin.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DamageCommand implements CommandExecutor {
	//For floating point calculations
	private static final double EPSILON = 0.0000001;
	@Override
	public boolean onCommand(CommandSender theSender, Command cmd, String commandLabel, String args[]) {
		if (theSender instanceof Player) {
			Player player = (Player) theSender;
			if (commandLabel.equalsIgnoreCase("damage")) {
				if (player.hasPermission("myfirstplugin.damage")) {
					if (args.length == 2) {
						if (Bukkit.getPlayer(args[0]) != null) { //There MUST be a player - if Bukkit.getPlayer(args[0]) returns NULL, that means a player CANNOT be found.
							try { //This code could result in an exception (i.e. error) if the player does not type a valid number into the command.
								Player playerToDamage = Bukkit.getPlayer(args[0]); //Gets the player's username from the command

								//Round args[1] to the nearest .5
								double damageRoundedStepOne = Double.parseDouble(args[1]) * 2;
								double damageRoundedStepTwo = Math.round(damageRoundedStepOne);
								double damageRounded = damageRoundedStepTwo / 2;

								double damageAmount = damageRounded * 2; //Doubles the rounded damage - whatever you put in damage(), Bukkit damages HALF of the input in hearts. To offset this, multiply by 2.
								if (damageRounded > 10) { //If the sender of the command puts a value higher than 10:
									playerToDamage.damage(20);
									player.sendMessage(ChatColor.RED + "Damaged " + ChatColor.GOLD + playerToDamage.getName() + ChatColor.RED + " for " + ChatColor.GOLD + "10" + ChatColor.RED + " hearts!");
									playerToDamage.sendMessage(ChatColor.RED + "You were damaged by " + ChatColor.GOLD + player.getName() + ChatColor.RED + " for " + ChatColor.GOLD + "10" + ChatColor.RED + " hearts!");

								} else if (damageRounded < 0.5) { //Prevents the specified amount from being too low to make a difference in the player's health
									player.sendMessage(ChatColor.RED + "Please specify a valid amount between 0.5 hearts and 10 hearts.");

								} else if (Math.abs(damageRounded - 1) < EPSILON) { //Changes "hearts" to "heart" in the case of 1 heart of dmg being dealt. < should be used instead of == in case of floating point rounding error
									playerToDamage.damage(2);
									player.sendMessage(ChatColor.RED + "Damaged " + ChatColor.GOLD + playerToDamage.getName() + ChatColor.RED + " for " + ChatColor.GOLD + "1" + ChatColor.RED + " heart!");
									playerToDamage.sendMessage(ChatColor.RED + "You were damaged by " + ChatColor.GOLD + player.getName() + ChatColor.RED + " for " + ChatColor.GOLD + "1" + ChatColor.RED + " heart!");

								} else { //The normal case where 0.5 <= damageRounded <= 10, and damageRounded != 1
									playerToDamage.damage(damageAmount);
									if (Math.abs(damageRounded - (int) damageRounded) < EPSILON) { //Checks if damageRounded is an integer. If damageRounded is a number like 5.00000000001, the difference is smaller than EPSILON but it might as well be an int anyway
										int damageRoundedInt = (int) damageRounded; //Removes the 0 after the decimal place.
										player.sendMessage(ChatColor.RED + "Damaged " + ChatColor.GOLD + playerToDamage.getName() + ChatColor.RED + " for " + ChatColor.GOLD + damageRoundedInt + ChatColor.RED + " hearts!");
										playerToDamage.sendMessage(ChatColor.RED + "You were damaged by " + ChatColor.GOLD + player.getName() + ChatColor.RED + " for " + ChatColor.GOLD + damageRoundedInt + ChatColor.RED + " hearts!");

									} else { //If damageRounded is NOT an integer
										player.sendMessage(ChatColor.RED + "Damaged " + ChatColor.GOLD + playerToDamage.getName() + ChatColor.RED + " for " + ChatColor.GOLD + damageRounded + ChatColor.RED + " hearts!");
										playerToDamage.sendMessage(ChatColor.RED + "You were damaged by " + ChatColor.GOLD + player.getName() + ChatColor.RED + " for " + ChatColor.GOLD + damageRounded + ChatColor.RED + " hearts!");
									}
								}
							} catch (NumberFormatException exception) { //If there is an exception, send the player a message. This particular exception is called NumberFormatException.
								player.sendMessage(ChatColor.RED + args[1] + " is not a valid number!");
							}
						} else { //Can't find the player - Bukkit.getPlayer(args[0]) returns NULL
							player.sendMessage(ChatColor.RED + "Could not find the player " + ChatColor.DARK_RED + args[0] + ChatColor.RED + "!"); //If a player can't be found, send this message
						}
					} else { //Number of arguments != 2
						player.sendMessage(ChatColor.RED + "Invalid arguments! Try /damage <player> <number of hearts>");
					}
				} else { //Insufficient perms
					player.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
				}
			}
		}
		return true;
	}

}
