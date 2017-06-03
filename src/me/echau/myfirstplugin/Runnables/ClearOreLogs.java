package me.echau.myfirstplugin.Runnables;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.echau.myfirstplugin.Listeners.MineOreMessage;

public class ClearOreLogs implements Runnable {
	@Override
	public void run() {
		MineOreMessage.getDiamondHashMap().clear();
		MineOreMessage.getCoalHashMap().clear();
		MineOreMessage.getRedstoneHashMap().clear();
		MineOreMessage.getEmeraldHashMap().clear();;
		MineOreMessage.getGoldHashMap().clear();
		MineOreMessage.getIronHashMap().clear();
		MineOreMessage.getLapisHashMap().clear();
		MineOreMessage.getQuartzHashMap().clear();
		for (final Player player : Bukkit.getServer().getOnlinePlayers()) {
			if (player.hasPermission("myfirstplugin.vieworelogs")) {
				player.sendMessage(ChatColor.RED + "OreLogs have been cleared!");
			}
		}
	}
}
