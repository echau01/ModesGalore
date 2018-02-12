package ca.echau.modesgalore.Runnables;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class OreLogsAlmostCleared implements Runnable {
	@Override
	public void run() {
		for (final Player player : Bukkit.getServer().getOnlinePlayers()) {
			if (player.hasPermission("modesgalore.vieworelogs")) {
				player.sendMessage(ChatColor.RED + "OreLogs are being cleared in 5 minutes! View them with /orelogs <player>");
			}
		}
	}
}
