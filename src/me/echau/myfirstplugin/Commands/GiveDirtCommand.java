package me.echau.myfirstplugin.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.echau.myfirstplugin.Main;

public class GiveDirtCommand implements CommandExecutor {
	private final Main plugin;
	
	public GiveDirtCommand(final Main plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(final CommandSender theSender, final Command cmd, final String commandLabel,
			final String args[]) {
		if (theSender instanceof Player) {
			final Player player = (Player) theSender;
			if (commandLabel.equalsIgnoreCase("givedirt")) {
				if (player.hasPermission("myfirstplugin.givedirt")) {
					if (args.length == 0) {
						player.sendMessage(ChatColor.RED + "Command usage: /givedirt <player> [amount]");
					} else {
						Player receiver = Bukkit.getPlayer(args[0]);
						if (args.length == 1) {
							if (receiver != null) { //Check if the player (receiver) actually exists.
								final ItemStack dirt = new ItemStack(Material.DIRT, 64);
								final Inventory inv = Bukkit.createInventory(null, 9, ChatColor.DARK_AQUA + "Take the dirt!");
								inv.addItem(dirt);	//Add 64 dirt to the newly created inventory
								receiver.openInventory(inv);
							} else {
								player.sendMessage(ChatColor.RED + "Could not find the player " + ChatColor.DARK_RED + args[0] + ChatColor.RED + "!");
							}
						} else if (args.length == 2) {
							if (receiver != null) {	//Check if the player (receiver) actually exists.
								try {	//The command sender could pass in something that isn't a number.
									final int dirtAmount = (int) Math.round(Double.parseDouble(args[1]));	//Turn second parameter into double, round it to the nearest number, and cast as integer
									if (0 < dirtAmount && dirtAmount <= 576) {	//dirtAmount is between 0 and 576.
										final ItemStack dirt = new ItemStack(Material.DIRT, dirtAmount);	//Turns the second argument into a double, rounds it to the nearest number, and casts it as an integer.
										final Inventory inv = Bukkit.createInventory(null, 9, ChatColor.DARK_AQUA + "Take the dirt!");
										inv.addItem(dirt);	//Add the specified amount of dirt (i.e. dirtAmount) to the newly created inventory.
										receiver.openInventory(inv);	//Opens the inventory of the receiver
										player.sendMessage(ChatColor.GOLD + "Gave " + ChatColor.RED + dirtAmount + ChatColor.GOLD + " dirt to " + ChatColor.RED + receiver.getName() + ChatColor.GOLD + ".");
										receiver.sendMessage(ChatColor.RED + player.getName() + ChatColor.GOLD + " gave you " + ChatColor.RED + dirtAmount + ChatColor.GOLD + " dirt!");
									} else if (0 >= Double.parseDouble(args[1])){
										player.sendMessage(ChatColor.RED + "Please specify a number greater than 0.");
									} else { //The number specified was greater than 576 (the max capacity)
										final ItemStack dirt = new ItemStack(Material.DIRT, 576);
										final Inventory inv = Bukkit.createInventory(null, 9, ChatColor.DARK_AQUA + "Take the dirt!");
										inv.addItem(dirt);	//Add 576 dirt to the newly created inventory
										receiver.openInventory(inv);	//Opens the inventory of the receiver


										final int remainder = dirtAmount - 576;	//Calculates the remaining amount of dirt
										player.sendMessage(ChatColor.RED + "Not enough room, " + ChatColor.DARK_RED + remainder + ChatColor.RED + " dirt was lost.");
										receiver.sendMessage(ChatColor.RED + player.getName() + ChatColor.GOLD + " gave you " + ChatColor.RED + "576" + ChatColor.GOLD + " dirt!");

										final int remainderStacks = (int) remainder / 64;	//Calculates number of full stacks that the remaining dirt takes up.
										final int remainderStacksRemainder = remainder % 64;	//Calculates the amount of dirt in the final non-full stack. Example: 131 dirt = 2 full stacks + 1 non-full stack with 3 dirt.
										
										//Drops remaining amount of dirt, looping thru all full stacks of dirt.
										boolean dropDirt = plugin.getConfig().getBoolean("DropDirtOnFullInventory");
										if (dropDirt) {
											for(int i = 0; i <= remainderStacks; i++) {
												if (i == remainderStacks) {
													final Item droppedDirt = receiver.getWorld().dropItem(receiver.getLocation(), new ItemStack(Material.DIRT, remainderStacksRemainder));
													droppedDirt.setPickupDelay(20);
												} else {
													final Item droppedDirtStacks = receiver.getWorld().dropItem(receiver.getLocation(), new ItemStack(Material.DIRT, 64));
													droppedDirtStacks.setPickupDelay(20);
												}
											}
										}
									}
								} catch (NumberFormatException exception) {	//In the case that the command sender did not provide an actual number
									player.sendMessage(ChatColor.RED + "Please specify a proper number greater than 0.");
								}
							} else {	//Specified receiver is offline
								player.sendMessage(ChatColor.RED + "Could not find the player " + ChatColor.DARK_RED + args[0] + ChatColor.RED + "!");
							}
						} else {	//Too many arguments
							player.sendMessage(ChatColor.RED + "Command usage: /givedirt <player> [amount]");
						}
					}
				} else {	//No perms
					player.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
				}
			} 
		}
		return true;
	}
}
