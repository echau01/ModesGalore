package me.echau.myfirstplugin.Listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.util.Vector;

import me.echau.myfirstplugin.Main;

public class FartOnSneak implements Listener {
	private final Main plugin;

	public FartOnSneak(final Main plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerSneak(final PlayerToggleSneakEvent event) {
		if (event.isSneaking()) {
			if (plugin.getConfig().getBoolean("uuids." + event.getPlayer().getUniqueId() + ".EnableFarts")) {
				final Location fartLoc = event.getPlayer().getLocation().add(0, 0.3, 0);
				final Vector fartNextLoc = fartLoc.getDirection()
						.multiply(-0.1)	//multiply(-0.1) means 10 cloud particles are spawned per block. 1 / 0.1 = 10
						.setY(0);
				for (int i = 0; i < 1280; i++) {	//fart always travels 128 blocks.
					fartLoc.add(fartNextLoc);
					event.getPlayer().getWorld().spawnParticle(Particle.CLOUD, fartLoc, 1, 0, 0, 0, 0);
					if (event.getPlayer().getWorld().getBlockAt(fartLoc).getType() != Material.AIR
							&& event.getPlayer().getWorld().getBlockAt(fartLoc).getType() != Material.WATER
							&& event.getPlayer().getWorld().getBlockAt(fartLoc).getType() != Material.STATIONARY_WATER
							&& event.getPlayer().getWorld().getBlockAt(fartLoc).getType() != Material.LAVA
							&& event.getPlayer().getWorld().getBlockAt(fartLoc).getType() != Material.STATIONARY_LAVA) {
						break;
					}
					for (final Entity entity : event.getPlayer().getWorld().getNearbyEntities(fartLoc, 0.1, 0.1, 0.1)) {
						if (entity instanceof LivingEntity) {
							if (!entity.equals(event.getPlayer())) {
								((LivingEntity) entity).damage(plugin.getConfig().getDouble("FartsDamageInHearts") * 2);
								event.getPlayer().getWorld().createExplosion(fartLoc, 0);
							}
						}
					}
				}
			}
		}
	}
}
