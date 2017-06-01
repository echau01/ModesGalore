package me.echau.myfirstplugin.Listeners;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class MineOreMessage implements Listener {
	//HashMap keys are player names in UPPERCASE. Values are the amount of the specific ore the player has mined in the past hour.
	//There can only be ONE instance of each HashMap! This is why these HashMaps are static.
	//Changing the values in these HashMaps will affect OreLogsCommand.java as well.
	private static HashMap<String, Integer> diamond = new HashMap<String, Integer>();
	private static HashMap<String, Integer> coal = new HashMap<String, Integer>();
	private static HashMap<String, Integer> redstone = new HashMap<String, Integer>();
	private static HashMap<String, Integer> emerald = new HashMap<String, Integer>();
	private static HashMap<String, Integer> gold = new HashMap<String, Integer>();
	private static HashMap<String, Integer> iron = new HashMap<String, Integer>();
	private static HashMap<String, Integer> lapis = new HashMap<String, Integer>();
	private static HashMap<String, Integer> quartz = new HashMap<String, Integer>();
	
	@EventHandler
	public void onPlayerMineOre(BlockBreakEvent event) {
		Player player = event.getPlayer();
		Material blockMaterial = event.getBlock().getType();
		for (Player playerToMessage : Bukkit.getServer().getOnlinePlayers()) {
			if (player.getGameMode() == GameMode.SURVIVAL) {
				if (playerToMessage.hasPermission("myfirstplugin.oremessage")) {
					if (blockMaterial.equals(Material.DIAMOND_ORE)) {
						playerToMessage.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "!" + ChatColor.GRAY + "] " + ChatColor.RED + ChatColor.BOLD.toString() + player.getName() + ChatColor.GRAY + " found " + ChatColor.AQUA + ChatColor.BOLD.toString() + "Diamond Ore" + ChatColor.GRAY + ".");
						Bukkit.getLogger().severe("[!] " + player.getName() + " found Diamond Ore");
						if (!diamond.containsKey(player.getName().toUpperCase())) {
							diamond.put(player.getName().toUpperCase(), 1);
						} else {
							diamond.put(player.getName().toUpperCase(), diamond.get(player.getName().toUpperCase()) + 1);
						}
						
					} else if (blockMaterial.equals(Material.COAL_ORE)) {
						playerToMessage.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "!" + ChatColor.GRAY + "] " + ChatColor.RED + ChatColor.BOLD.toString() + player.getName() + ChatColor.GRAY + " found " + ChatColor.BLACK + ChatColor.BOLD.toString() + "Coal Ore" + ChatColor.GRAY + ".");
						Bukkit.getLogger().info("[!] " + player.getName() + " found Coal Ore.");
						if (!coal.containsKey(player.getName().toUpperCase())) {
							coal.put(player.getName().toUpperCase(), 1);
						} else {
							coal.put(player.getName().toUpperCase(), coal.get(player.getName().toUpperCase()) + 1);
						}
						
					} else if (blockMaterial.equals(Material.REDSTONE_ORE) || blockMaterial.equals(Material.GLOWING_REDSTONE_ORE)) {
						playerToMessage.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "!" + ChatColor.GRAY + "] " + ChatColor.RED + ChatColor.BOLD.toString() + player.getName() + ChatColor.GRAY + " found " + ChatColor.RED + ChatColor.BOLD.toString() + "Redstone Ore" + ChatColor.GRAY + ".");
						Bukkit.getLogger().info("[!] " + player.getName() + " found Redstone Ore.");
						if (!redstone.containsKey(player.getName().toUpperCase())) {
							redstone.put(player.getName().toUpperCase(), 1);
						} else {
							redstone.put(player.getName().toUpperCase(), redstone.get(player.getName().toUpperCase()) + 1);
						}
						
					} else if (blockMaterial.equals(Material.EMERALD_ORE)) {
						playerToMessage.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "!" + ChatColor.GRAY + "] " + ChatColor.RED + ChatColor.BOLD.toString() + player.getName() + ChatColor.GRAY + " found " + ChatColor.GREEN + ChatColor.BOLD.toString() + "Emerald Ore" + ChatColor.GRAY + ".");
						Bukkit.getLogger().severe("[!] " + player.getName() + " found Emerald Ore.");
						if (!emerald.containsKey(player.getName().toUpperCase())) {
							emerald.put(player.getName().toUpperCase(), 1);
						} else {
							emerald.put(player.getName().toUpperCase(), emerald.get(player.getName().toUpperCase()) + 1);
						}
						
					} else if (blockMaterial.equals(Material.GOLD_ORE)) {
						playerToMessage.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "!" + ChatColor.GRAY + "] " + ChatColor.RED + ChatColor.BOLD.toString() + player.getName() + ChatColor.GRAY + " found " + ChatColor.GOLD + ChatColor.BOLD.toString() + "Gold Ore" + ChatColor.GRAY + ".");
						Bukkit.getLogger().warning("[!] " + player.getName() + " found Gold Ore");
						if (!gold.containsKey(player.getName().toUpperCase())) {
							gold.put(player.getName().toUpperCase(), 1);
						} else {
							gold.put(player.getName().toUpperCase(), gold.get(player.getName().toUpperCase()) + 1);
						}
						
					} else if (blockMaterial.equals(Material.IRON_ORE)) {
						playerToMessage.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "!" + ChatColor.GRAY + "] " + ChatColor.RED + ChatColor.BOLD.toString() + player.getName() + ChatColor.GRAY + " found " + ChatColor.DARK_GRAY + ChatColor.BOLD.toString() + "Iron Ore" + ChatColor.GRAY + ".");
						Bukkit.getLogger().warning("[!] " + player.getName() + " found Iron Ore.");
						if (!iron.containsKey(player.getName().toUpperCase())) {
							iron.put(player.getName().toUpperCase(), 1);
						} else {
							iron.put(player.getName().toUpperCase(), iron.get(player.getName().toUpperCase()) + 1);
						}
						
					} else if (blockMaterial.equals(Material.LAPIS_ORE)) {
						playerToMessage.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "!" + ChatColor.GRAY + "] " + ChatColor.RED + ChatColor.BOLD.toString() + player.getName() + ChatColor.GRAY + " found " + ChatColor.BLUE + ChatColor.BOLD.toString() + "Lapis Ore" + ChatColor.GRAY + ".");
						Bukkit.getLogger().info("[!] " + player.getName() + " found Lapis Ore.");
						if (!lapis.containsKey(player.getName().toUpperCase())) {
							lapis.put(player.getName().toUpperCase(), 1);
						} else {
							lapis.put(player.getName().toUpperCase(), lapis.get(player.getName().toUpperCase()) + 1);
						}
						
					} else if (blockMaterial.equals(Material.QUARTZ_ORE)) {
						playerToMessage.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "!" + ChatColor.GRAY + "] " + ChatColor.RED + ChatColor.BOLD.toString() + player.getName() + ChatColor.GRAY + " found " + ChatColor.WHITE + ChatColor.BOLD.toString() + "Quartz Ore" + ChatColor.GRAY + ".");
						Bukkit.getLogger().info("[!] " + player.getName() + " found Quartz Ore.");
						if (!quartz.containsKey(player.getName().toUpperCase())) {
							quartz.put(player.getName().toUpperCase(), 1);
						} else {
							quartz.put(player.getName().toUpperCase(), quartz.get(player.getName().toUpperCase()) + 1);
						}
						
					}
				}
			}
		}
	}
	
	//Methods for accessing HashMap instances in other classes.
	public static HashMap<String, Integer> getDiamondHashMap() {
		return diamond;
	}
	
	public static HashMap<String, Integer> getCoalHashMap() {
		return coal;
	}
	
	public static HashMap<String, Integer> getRedstoneHashMap() {
		return redstone;
	}
	
	public static HashMap<String, Integer> getEmeraldHashMap() {
		return emerald;
	}
	
	public static HashMap<String, Integer> getGoldHashMap() {
		return gold;
	}
	
	public static HashMap<String, Integer> getIronHashMap() {
		return iron;
	}
	
	public static HashMap<String, Integer> getLapisHashMap() {
		return lapis;
	}
	
	public static HashMap<String, Integer> getQuartzHashMap() {
		return quartz;
	}
}
