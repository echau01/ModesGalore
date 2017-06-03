package me.echau.myfirstplugin.Listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import me.echau.myfirstplugin.Main;

public class LaunchYourselfWithBow implements Listener {
	private final Main plugin;
	
	//To cancel fall damage - represents players who are in the air after launch
	private final List<String> isBeingLaunched = new ArrayList<String>();
	
	public LaunchYourselfWithBow(final Main plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onArrowShoot(final EntityShootBowEvent event) {
		if (event.getEntity() instanceof Player) {
			final Player player = (Player) event.getEntity();
			if (plugin.getConfig().getBoolean("uuids." + player.getUniqueId().toString() + ".LaunchMode")) {
				final Vector arrowVelocity = event.getProjectile().getVelocity();
				final Vector playerVelocity = arrowVelocity.multiply(3);
				player.setVelocity(playerVelocity);
				
				//Add player to isBeingLaunched only if he isn't in there already.
				if (!isBeingLaunched.contains(player.getName())) {
					isBeingLaunched.add(player.getName());
				}
				
				//Take away an arrow if in survival
				if (player.getGameMode() == GameMode.SURVIVAL) {
					if (player.getInventory().contains(Material.ARROW)) {
						player.getInventory().removeItem(new ItemStack(Material.ARROW, 1));
					}
				}
			} else { //If Launch Mode disabled
				isBeingLaunched.remove(player.getName());
			}
		}
	}

	//Cancel fall damage from launching yourself
	@EventHandler
	public void playerTakesFallDamage(final EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			final Player player = (Player) event.getEntity();
			if (isBeingLaunched.contains(player.getName())) {
				if (event.getCause() == DamageCause.FALL) {
					event.setCancelled(true);
					isBeingLaunched.remove(player.getName());
				}
			}
		}
	}
}
