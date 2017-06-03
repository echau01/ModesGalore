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
	private static final HashMap<String, Integer> DIAMOND = new HashMap<String, Integer>();
	private static final HashMap<String, Integer> COAL = new HashMap<String, Integer>();
	private static final HashMap<String, Integer> REDSTONE = new HashMap<String, Integer>();
	private static final HashMap<String, Integer> EMERALD = new HashMap<String, Integer>();
	private static final HashMap<String, Integer> GOLD = new HashMap<String, Integer>();
	private static final HashMap<String, Integer> IRON = new HashMap<String, Integer>();
	private static final HashMap<String, Integer> LAPIS = new HashMap<String, Integer>();
	private static final HashMap<String, Integer> QUARTZ = new HashMap<String, Integer>();
	
	@EventHandler
	public void onPlayerMineOre(BlockBreakEvent event) {
		final Player player = event.getPlayer();
		final Material blockMaterial = event.getBlock().getType();
		for (final Player playerToMessage : Bukkit.getServer().getOnlinePlayers()) {
			if (player.getGameMode() == GameMode.SURVIVAL) {
				if (playerToMessage.hasPermission("myfirstplugin.oremessage")) {
					if (blockMaterial.equals(Material.DIAMOND_ORE)) {
						playerToMessage.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "!" + ChatColor.GRAY + "] " + ChatColor.RED + ChatColor.BOLD.toString() + player.getName() + ChatColor.GRAY + " found " + ChatColor.AQUA + ChatColor.BOLD.toString() + "Diamond Ore" + ChatColor.GRAY + ".");
						Bukkit.getLogger().severe("[!] " + player.getName() + " found Diamond Ore");
						if (!DIAMOND.containsKey(player.getName().toUpperCase())) {
							DIAMOND.put(player.getName().toUpperCase(), 1);
						} else {
							DIAMOND.put(player.getName().toUpperCase(), DIAMOND.get(player.getName().toUpperCase()) + 1);
						}
					} else if (blockMaterial.equals(Material.COAL_ORE)) {
						playerToMessage.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "!" + ChatColor.GRAY + "] " + ChatColor.RED + ChatColor.BOLD.toString() + player.getName() + ChatColor.GRAY + " found " + ChatColor.BLACK + ChatColor.BOLD.toString() + "Coal Ore" + ChatColor.GRAY + ".");
						Bukkit.getLogger().info("[!] " + player.getName() + " found Coal Ore.");
						if (!COAL.containsKey(player.getName().toUpperCase())) {
							COAL.put(player.getName().toUpperCase(), 1);
						} else {
							COAL.put(player.getName().toUpperCase(), COAL.get(player.getName().toUpperCase()) + 1);
						}	
					} else if (blockMaterial.equals(Material.REDSTONE_ORE) || blockMaterial.equals(Material.GLOWING_REDSTONE_ORE)) {
						playerToMessage.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "!" + ChatColor.GRAY + "] " + ChatColor.RED + ChatColor.BOLD.toString() + player.getName() + ChatColor.GRAY + " found " + ChatColor.RED + ChatColor.BOLD.toString() + "Redstone Ore" + ChatColor.GRAY + ".");
						Bukkit.getLogger().info("[!] " + player.getName() + " found Redstone Ore.");
						if (!REDSTONE.containsKey(player.getName().toUpperCase())) {
							REDSTONE.put(player.getName().toUpperCase(), 1);
						} else {
							REDSTONE.put(player.getName().toUpperCase(), REDSTONE.get(player.getName().toUpperCase()) + 1);
						}					
					} else if (blockMaterial.equals(Material.EMERALD_ORE)) {
						playerToMessage.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "!" + ChatColor.GRAY + "] " + ChatColor.RED + ChatColor.BOLD.toString() + player.getName() + ChatColor.GRAY + " found " + ChatColor.GREEN + ChatColor.BOLD.toString() + "Emerald Ore" + ChatColor.GRAY + ".");
						Bukkit.getLogger().severe("[!] " + player.getName() + " found Emerald Ore.");
						if (!EMERALD.containsKey(player.getName().toUpperCase())) {
							EMERALD.put(player.getName().toUpperCase(), 1);
						} else {
							EMERALD.put(player.getName().toUpperCase(), EMERALD.get(player.getName().toUpperCase()) + 1);
						}						
					} else if (blockMaterial.equals(Material.GOLD_ORE)) {
						playerToMessage.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "!" + ChatColor.GRAY + "] " + ChatColor.RED + ChatColor.BOLD.toString() + player.getName() + ChatColor.GRAY + " found " + ChatColor.GOLD + ChatColor.BOLD.toString() + "Gold Ore" + ChatColor.GRAY + ".");
						Bukkit.getLogger().warning("[!] " + player.getName() + " found Gold Ore");
						if (!GOLD.containsKey(player.getName().toUpperCase())) {
							GOLD.put(player.getName().toUpperCase(), 1);
						} else {
							GOLD.put(player.getName().toUpperCase(), GOLD.get(player.getName().toUpperCase()) + 1);
						}						
					} else if (blockMaterial.equals(Material.IRON_ORE)) {
						playerToMessage.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "!" + ChatColor.GRAY + "] " + ChatColor.RED + ChatColor.BOLD.toString() + player.getName() + ChatColor.GRAY + " found " + ChatColor.DARK_GRAY + ChatColor.BOLD.toString() + "Iron Ore" + ChatColor.GRAY + ".");
						Bukkit.getLogger().warning("[!] " + player.getName() + " found Iron Ore.");
						if (!IRON.containsKey(player.getName().toUpperCase())) {
							IRON.put(player.getName().toUpperCase(), 1);
						} else {
							IRON.put(player.getName().toUpperCase(), IRON.get(player.getName().toUpperCase()) + 1);
						}						
					} else if (blockMaterial.equals(Material.LAPIS_ORE)) {
						playerToMessage.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "!" + ChatColor.GRAY + "] " + ChatColor.RED + ChatColor.BOLD.toString() + player.getName() + ChatColor.GRAY + " found " + ChatColor.BLUE + ChatColor.BOLD.toString() + "Lapis Ore" + ChatColor.GRAY + ".");
						Bukkit.getLogger().info("[!] " + player.getName() + " found Lapis Ore.");
						if (!LAPIS.containsKey(player.getName().toUpperCase())) {
							LAPIS.put(player.getName().toUpperCase(), 1);
						} else {
							LAPIS.put(player.getName().toUpperCase(), LAPIS.get(player.getName().toUpperCase()) + 1);
						}						
					} else if (blockMaterial.equals(Material.QUARTZ_ORE)) {
						playerToMessage.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "!" + ChatColor.GRAY + "] " + ChatColor.RED + ChatColor.BOLD.toString() + player.getName() + ChatColor.GRAY + " found " + ChatColor.WHITE + ChatColor.BOLD.toString() + "Quartz Ore" + ChatColor.GRAY + ".");
						Bukkit.getLogger().info("[!] " + player.getName() + " found Quartz Ore.");
						if (!QUARTZ.containsKey(player.getName().toUpperCase())) {
							QUARTZ.put(player.getName().toUpperCase(), 1);
						} else {
							QUARTZ.put(player.getName().toUpperCase(), QUARTZ.get(player.getName().toUpperCase()) + 1);
						}
					}
				}
			}
		}
	}
	
	//Methods for accessing HashMap instances in other classes.
	public static HashMap<String, Integer> getDiamondHashMap() {
		return DIAMOND;
	}
	
	public static HashMap<String, Integer> getCoalHashMap() {
		return COAL;
	}
	
	public static HashMap<String, Integer> getRedstoneHashMap() {
		return REDSTONE;
	}
	
	public static HashMap<String, Integer> getEmeraldHashMap() {
		return EMERALD;
	}
	
	public static HashMap<String, Integer> getGoldHashMap() {
		return GOLD;
	}
	
	public static HashMap<String, Integer> getIronHashMap() {
		return IRON;
	}
	
	public static HashMap<String, Integer> getLapisHashMap() {
		return LAPIS;
	}
	
	public static HashMap<String, Integer> getQuartzHashMap() {
		return QUARTZ;
	}
}
