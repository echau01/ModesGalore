package ca.echau.modesgalore.Listeners;

import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

import ca.echau.modesgalore.Main;

public class ExplosiveArrows implements Listener {
	private final Main plugin;
	
	public ExplosiveArrows(final Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void explodeArrows(final ProjectileHitEvent event) {
		if (event.getEntity() instanceof Arrow) {
			if (event.getEntity().getShooter() instanceof Player) {
				final Player player = (Player) event.getEntity().getShooter();
				if (plugin.getConfig().getBoolean("uuids." + player.getUniqueId().toString() + ".ExArrows")) {
					final Location loc = event.getEntity().getLocation();
					event.getEntity().remove();
					event.getEntity().getWorld().createExplosion(loc, 4F);
				}
			}
		}
	}
}
