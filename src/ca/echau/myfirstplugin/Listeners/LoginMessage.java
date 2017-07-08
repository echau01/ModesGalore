package ca.echau.myfirstplugin.Listeners;

import java.util.ArrayList;
import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import ca.echau.myfirstplugin.Main;

public class LoginMessage implements Listener {
	private final Main plugin;
	
	public LoginMessage(final Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void sendLoginMessage(final PlayerJoinEvent event) {
		final Player player = event.getPlayer(); 
		player.sendMessage(ChatColor.RED + "[MyFirstPlugin] " + ChatColor.GOLD + "MyFirstPlugin v0.2a enabled.");
		final Collection<Player> players = new ArrayList<Player>(Bukkit.getServer().getOnlinePlayers());
		
		//Set up individual config path for each player if none exists already.
		if (!plugin.getConfig().contains("uuids." + player.getUniqueId().toString())){
			plugin.getConfig().set("uuids." + player.getUniqueId().toString() + ".name", player.getName());
			plugin.saveConfig();
			plugin.reloadConfig();
			for (final Player onlinePlayer : players) {
				onlinePlayer.sendMessage(ChatColor.AQUA + "Please welcome " + ChatColor.GOLD + player.getName() + ChatColor.AQUA + " to the server!");
			}
		} else {	//In case of name changes
			if (!plugin.getConfig().getString("uuids." + player.getUniqueId().toString() + ".name").equalsIgnoreCase(player.getName())) {
				plugin.getConfig().set("uuids." + player.getUniqueId().toString() + ".name", player.getName());
				plugin.saveConfig();
				plugin.reloadConfig();
			}
			for (final Player onlinePlayer : players) {
				onlinePlayer.sendMessage(ChatColor.RED + "Welcome back " + ChatColor.GOLD + player.getName() + ChatColor.RED + " to the server!");
			}
		}
		
		//Modes
		if (plugin.getConfig().getBoolean("uuids." + player.getUniqueId().toString() + ".LaunchMode")) {
			player.sendMessage(ChatColor.AQUA + "Launch Mode " + ChatColor.GREEN + "enabled.");
		} 
		if (plugin.getConfig().getBoolean("uuids." + player.getUniqueId().toString() + ".ExArrows")) {
			player.sendMessage(ChatColor.AQUA + "Explosive Arrows " + ChatColor.GREEN + "enabled.");
		}
		if (plugin.getConfig().getBoolean("uuids." + player.getUniqueId().toString() + ".RainbowArmor")) {
			player.sendMessage(ChatColor.AQUA + "Rainbow Armor " + ChatColor.GREEN + "enabled.");
		}
		if (plugin.getConfig().getBoolean("uuids." + player.getUniqueId().toString() + ".WalkOnSmokeMode")) {
			player.sendMessage(ChatColor.AQUA + "WalkOnSmoke Mode " + ChatColor.GREEN + "enabled.");
		}
		if (plugin.getConfig().getBoolean("uuids." + player.getUniqueId().toString() + ".EnableFarts")) {
			player.sendMessage(ChatColor.AQUA + "Farts Mode " + ChatColor.GREEN + "enabled.");
		}
		if (plugin.getConfig().getBoolean("uuids." + player.getUniqueId().toString() + ".EnablePooping")) {
			player.sendMessage(ChatColor.AQUA + "Pooping " + ChatColor.GREEN + "enabled.");
		}
	}
}
