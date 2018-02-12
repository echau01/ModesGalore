package ca.echau.modesgalore.Runnables;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import ca.echau.modesgalore.Listeners.MineOreMessage;

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
			if (player.hasPermission("modesgalore.vieworelogs")) {
				player.sendMessage(ChatColor.RED + "OreLogs have been cleared!");
			}
		}
	}
}
