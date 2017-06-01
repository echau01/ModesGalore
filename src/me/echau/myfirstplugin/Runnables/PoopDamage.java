package me.echau.myfirstplugin.Runnables;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;

import me.echau.myfirstplugin.Main;
import me.echau.myfirstplugin.Listeners.PoopOnSneak;

public class PoopDamage implements Runnable {
	private final Main plugin;
	
	public PoopDamage(Main plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public void run() {
		Map<List<FallingBlock>, UUID> poopToPlayerUUID = PoopOnSneak.getPoopToPlayerUUID();
		if (!poopToPlayerUUID.isEmpty()) {
			Bukkit.getServer().broadcastMessage("TEST!");
			for (Player player : Bukkit.getServer().getOnlinePlayers()) {
				entityLoop:
				for (Entity e : player.getNearbyEntities(0.5, 1.0, 0.5)) {
					if (e instanceof FallingBlock) {
						List<FallingBlock> poopBlocks = new ArrayList<FallingBlock>();
						Iterator<Map.Entry<List<FallingBlock>, UUID>> it = poopToPlayerUUID
								.entrySet()
								.iterator();
						while (it.hasNext()) {
							if ((poopBlocks = it.next().getKey()).contains(e)) {
								if (!player.getUniqueId().equals(poopToPlayerUUID.get(poopBlocks))) {
									player.damage(plugin.getConfig()
											.getDouble("PoopDamageInHearts") * 2);
									break entityLoop;
								}
							}
						}
					}
				}
			}
		}
	}
}
