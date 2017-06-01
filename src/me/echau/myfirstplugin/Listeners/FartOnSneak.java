package me.echau.myfirstplugin.Listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.util.Vector;

import me.echau.myfirstplugin.Main;

public class FartOnSneak implements Listener {
	private final Main plugin;

	public FartOnSneak(Main plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerSneak(PlayerToggleSneakEvent event) {
		if (event.isSneaking()) {
			if (plugin.getConfig().getBoolean("uuids." + event.getPlayer().getUniqueId() + ".EnableFarts")) {
				Location origin = event.getPlayer().getLocation().add(0, 0.3, 0);
				Vector step = origin.getDirection().multiply(-0.5).setY(0);

				for (int i = 0; i < 200; i++) {
					origin.add(step);
					event.getPlayer().getWorld().spawnParticle(Particle.CLOUD, origin, 1, 0, 0, 0, 0);

					if (event.getPlayer().getWorld().getBlockAt(origin).getType() != Material.AIR
							&& event.getPlayer().getWorld().getBlockAt(origin).getType() != Material.WATER
							&& event.getPlayer().getWorld().getBlockAt(origin).getType() != Material.STATIONARY_WATER
							&& event.getPlayer().getWorld().getBlockAt(origin).getType() != Material.LAVA
							&& event.getPlayer().getWorld().getBlockAt(origin).getType() != Material.STATIONARY_LAVA) {
						break;
					}
					
					for (Entity entity : event.getPlayer().getWorld().getNearbyEntities(origin, 0.1, 0.1, 0.1)) {
						if (entity instanceof LivingEntity) {
							if (entity instanceof Player && ((Player) entity).getUniqueId().equals(event.getPlayer().getUniqueId())) {

							} else {
								((LivingEntity) entity).damage(plugin.getConfig().getDouble("FartsDamageInHearts") * 2);
								event.getPlayer().getWorld().createExplosion(origin, 0);
							}
						}
					}
				}
			}
		}
	}
}
