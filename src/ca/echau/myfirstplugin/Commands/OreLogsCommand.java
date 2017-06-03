package ca.echau.myfirstplugin.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ca.echau.myfirstplugin.Listeners.MineOreMessage;

public class OreLogsCommand implements CommandExecutor {
	@Override
	public boolean onCommand(final CommandSender theSender, final Command cmd, final String commandLabel,
			final String args[]) {
		if (theSender instanceof Player) {
			final Player player = (Player) theSender;
			if (commandLabel.equalsIgnoreCase("orelogs")) {
				if (player.hasPermission("myfirstplugin.vieworelogs")) {
					if (args.length == 1) {
						final String target = args[0].toUpperCase();
						player.sendMessage(ChatColor.GRAY + "OreLogs for " + ChatColor.RED + target + ChatColor.GRAY + " within the past hour:");
						if (MineOreMessage.getDiamondHashMap().containsKey(target)) {
							final int diamond = MineOreMessage.getDiamondHashMap().get(target);
							player.sendMessage(ChatColor.GRAY + "  - " + ChatColor.AQUA + diamond + " diamonds.");
						}
						if (MineOreMessage.getCoalHashMap().containsKey(target)) {
							final int coal = MineOreMessage.getCoalHashMap().get(target);
							player.sendMessage(ChatColor.GRAY + "  - " + ChatColor.BLACK + coal + " coal.");
						}
						if (MineOreMessage.getRedstoneHashMap().containsKey(target)) {
							final int redstone = MineOreMessage.getRedstoneHashMap().get(target);
							player.sendMessage(ChatColor.GRAY + "  - " + ChatColor.RED + redstone + " redstone.");
						}
						if (MineOreMessage.getEmeraldHashMap().containsKey(target)) {
							final int emerald = MineOreMessage.getEmeraldHashMap().get(target);
							player.sendMessage(ChatColor.GRAY + "  - " + ChatColor.GREEN + emerald + " emeralds.");
						}
						if (MineOreMessage.getGoldHashMap().containsKey(target)) {
							final int gold = MineOreMessage.getGoldHashMap().get(target);
							player.sendMessage(ChatColor.GRAY + "  - " + ChatColor.GOLD + gold + " gold ore.");
						}
						if (MineOreMessage.getIronHashMap().containsKey(target)) {
							final int iron = MineOreMessage.getIronHashMap().get(target);
							player.sendMessage(ChatColor.GRAY + "  - " + iron + " iron ore.");
						}
						if (MineOreMessage.getLapisHashMap().containsKey(target)) {
							final int lapis = MineOreMessage.getLapisHashMap().get(target);
							player.sendMessage(ChatColor.GRAY + "  - " + ChatColor.BLUE + lapis + " lapis ore.");
						}
						if (MineOreMessage.getQuartzHashMap().containsKey(target)) {
							final int quartz = MineOreMessage.getQuartzHashMap().get(target);
							player.sendMessage(ChatColor.GRAY + "  - " + ChatColor.WHITE + quartz + " quartz ore.");
						}
					} else {
						player.sendMessage(ChatColor.RED + "Invalid arguments! Command usage: /orelogs <player>");
					}
				} else {
					player.sendMessage(ChatColor.RED + "You don't have permission to view OreLogs!");
				}
			}
		}
		return true;
	}
}
