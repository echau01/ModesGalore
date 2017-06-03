package me.echau.myfirstplugin.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.LargeFireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Shulker;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.SmallFireball;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.entity.Wither;
import org.bukkit.entity.WitherSkull;
import org.bukkit.entity.minecart.ExplosiveMinecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathMessages implements Listener {
	//Player killed by entities
	@EventHandler
	public void onPlayerDeathByEntity(final EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof Player) {
			final Player player = (Player) event.getEntity();
			if (player.getGameMode() == GameMode.SURVIVAL) {
				if (player.getHealth() <= event.getDamage()) {
					final DamageCause cause = player.getLastDamageCause().getCause();
					if (cause.equals(DamageCause.ENTITY_ATTACK)) {
						//player.getKiller() always returns a Player -- if it returns null, that means the player did not die by another player
						if (player.getKiller() != null) {
							final Player killer = player.getKiller();
							Bukkit.getServer().broadcastMessage(ChatColor.RED + player.getName() + ChatColor.GOLD + " was slain by " + ChatColor.RED + killer.getName() + ChatColor.GOLD + ".");
						} else {
							Bukkit.getServer().broadcastMessage(ChatColor.RED + player.getName() + ChatColor.GOLD + " was killed by a " + ChatColor.RED + event.getDamager().getName() + ChatColor.GOLD + ".");
						}
					} else if (event.getDamager() instanceof WitherSkull || event.getDamager() instanceof Wither) {
						Bukkit.getServer().broadcastMessage(ChatColor.RED + player.getName() + ChatColor.GOLD + " got blown up by the " + ChatColor.RED + "Wither" + ChatColor.GOLD + ".");
					} else if (event.getDamager() instanceof Creeper) {
						Bukkit.getServer().broadcastMessage(ChatColor.RED + player.getName() + ChatColor.GOLD + " got blown up by a " + ChatColor.RED + "Creeper" + ChatColor.GOLD + ".");
					} else if (event.getDamager() instanceof ExplosiveMinecart) {
						Bukkit.getServer().broadcastMessage(ChatColor.RED + player.getName() + ChatColor.GOLD + " got blown up by a " + ChatColor.RED + "TNT Minecart" + ChatColor.GOLD + ".");
					} else if (event.getDamager() instanceof TNTPrimed) {
						Bukkit.getServer().broadcastMessage(ChatColor.RED + player.getName() + ChatColor.GOLD + " got blown up by " + ChatColor.RED + "TNT" + ChatColor.GOLD + ".");
					} else if (event.getDamager() instanceof EnderCrystal) {
						Bukkit.getServer().broadcastMessage(ChatColor.RED + player.getName() + ChatColor.GOLD + " got blown up by an " + ChatColor.RED + "EnderCrystal" + ChatColor.GOLD + ".");
					} else if (cause.equals(DamageCause.PROJECTILE)) {
						final Projectile proj = (Projectile) event.getDamager();
						if (proj.getShooter() instanceof Player) {
							final Player killer = (Player) proj.getShooter();
							if (proj instanceof Fireball || proj instanceof LargeFireball || proj instanceof SmallFireball) {
								Bukkit.getServer().broadcastMessage(ChatColor.RED + player.getName() + ChatColor.GOLD + " fell by the fireball of " + ChatColor.RED + killer.getName() + ChatColor.GOLD + ".");
							} else if (proj instanceof Arrow) {
								Bukkit.getServer().broadcastMessage(ChatColor.RED + player.getName() + ChatColor.GOLD + " fell by the arrow of " + ChatColor.RED + killer.getName() + ChatColor.GOLD + ".");
							}
						} else if (proj.getShooter() instanceof Skeleton) {
							Bukkit.getServer().broadcastMessage(ChatColor.RED + player.getName() + ChatColor.GOLD + " was shot to death by a " + ChatColor.RED + "Skeleton" + ChatColor.GOLD + ".");
						} else if (proj.getShooter() instanceof Ghast) {
							Bukkit.getServer().broadcastMessage(ChatColor.RED + player.getName() + ChatColor.GOLD + " was fireballed to death by a " + ChatColor.RED + "Ghast" + ChatColor.GOLD + ".");
						} else if (proj.getShooter() instanceof Blaze) {
							Bukkit.getServer().broadcastMessage(ChatColor.RED + player.getName() + ChatColor.GOLD + " was fireballed to death by a " + ChatColor.RED + "Blaze" + ChatColor.GOLD + ".");	
						} else if (proj.getShooter() instanceof Shulker) {
							Bukkit.getServer().broadcastMessage(ChatColor.RED + player.getName() + ChatColor.GOLD + " was killed by a " + ChatColor.RED + "Shulker" + ChatColor.GOLD + ".");
						} else if (proj.getShooter() instanceof EnderDragon) {
							Bukkit.getServer().broadcastMessage(ChatColor.RED + player.getName() + ChatColor.GOLD + " was fireballed to death by the " + ChatColor.RED + "EnderDragon" + ChatColor.GOLD + ".");	
						}
					}
				}
			}
		}
	}

	//Player killed by non-entities (e.g. falling dmg)
	@EventHandler
	public void onPlayerDeath(final PlayerDeathEvent event) {
		final Player player = event.getEntity();
		if (player.getLastDamageCause() != null) {
			final DamageCause cause = player.getLastDamageCause().getCause();
			if (cause.equals(DamageCause.BLOCK_EXPLOSION)) {
				Bukkit.getServer().broadcastMessage(ChatColor.RED + player.getName() + ChatColor.GOLD + " blew up into bits and pieces.");
			} else if (cause.equals(DamageCause.CONTACT)) {
				Bukkit.getServer().broadcastMessage(ChatColor.RED + player.getName() + ChatColor.GOLD + " was pricked to death.");
			} else if (cause.equals(DamageCause.CRAMMING)) {
				Bukkit.getServer().broadcastMessage(ChatColor.RED + player.getName() + ChatColor.GOLD + " was squished to death.");
			} else if (cause.equals(DamageCause.CUSTOM)) {
				Bukkit.getServer().broadcastMessage(ChatColor.RED + player.getName() + ChatColor.GOLD + " died.");
			} else if (cause.equals(DamageCause.DRAGON_BREATH)) {
				Bukkit.getServer().broadcastMessage(ChatColor.RED + player.getName() + ChatColor.GOLD + " died from the EnderDragon's Breath");
			} else if (cause.equals(DamageCause.DROWNING)) {
				Bukkit.getServer().broadcastMessage(ChatColor.RED + player.getName() + ChatColor.GOLD + " is swimming with the fishes.");
			} else if (cause.equals(DamageCause.FALL)) {
				Bukkit.getServer().broadcastMessage(ChatColor.RED + player.getName() + ChatColor.GOLD + " thought they could fly.");
			} else if (cause.equals(DamageCause.FALLING_BLOCK)) {
				Bukkit.getServer().broadcastMessage(ChatColor.RED + player.getName() + ChatColor.GOLD + " was crushed by a falling block.");
			} else if (cause.equals(DamageCause.FIRE) || cause.equals(DamageCause.FIRE_TICK)) {
				Bukkit.getServer().broadcastMessage(ChatColor.RED + player.getName() + ChatColor.GOLD + " burned to death.");
			} else if (cause.equals(DamageCause.FLY_INTO_WALL)) {
				Bukkit.getServer().broadcastMessage(ChatColor.RED + player.getName() + ChatColor.GOLD + " ran into a wall!");
			} else if (cause.equals(DamageCause.HOT_FLOOR)) {
				Bukkit.getServer().broadcastMessage(ChatColor.RED + player.getName() + ChatColor.GOLD + " forgot that the floor was lava.");
			} else if (cause.equals(DamageCause.LAVA)) {
				Bukkit.getServer().broadcastMessage(ChatColor.RED + player.getName() + ChatColor.GOLD + " thought that lava was the same as water.");
			} else if (cause.equals(DamageCause.LIGHTNING)) {
				Bukkit.getServer().broadcastMessage(ChatColor.RED + player.getName() + ChatColor.GOLD + " was struck by lightning!");
			} else if (cause.equals(DamageCause.MELTING)) {
				Bukkit.getServer().broadcastMessage(ChatColor.RED + player.getName() + ChatColor.GOLD + " died from a melting snowman.");
			} else if (cause.equals(DamageCause.MAGIC)) {
				Bukkit.getServer().broadcastMessage(ChatColor.RED + player.getName() + ChatColor.GOLD + " was killed by a potion.");
			} else if (cause.equals(DamageCause.POISON)) {
				Bukkit.getServer().broadcastMessage(ChatColor.RED + player.getName() + ChatColor.GOLD + " was poisoned to death.");
			} else if (cause.equals(DamageCause.STARVATION)) {
				Bukkit.getServer().broadcastMessage(ChatColor.RED + player.getName() + ChatColor.GOLD + " died because they forgot to eat food!");
			} else if (cause.equals(DamageCause.SUFFOCATION)) {
				Bukkit.getServer().broadcastMessage(ChatColor.RED + player.getName() + ChatColor.GOLD + " suffocated in a wall.");
			} else if (cause.equals(DamageCause.SUICIDE)) {
				Bukkit.getServer().broadcastMessage(ChatColor.RED + player.getName() + ChatColor.GOLD + " killed themself.");
			} else if (cause.equals(DamageCause.THORNS)) {
				Bukkit.getServer().broadcastMessage(ChatColor.RED + player.getName() + ChatColor.GOLD + " died from the " + ChatColor.RED + "Thorns " + ChatColor.GOLD + "enchantment.");
			} else if (cause.equals(DamageCause.VOID)) {
				Bukkit.getServer().broadcastMessage(ChatColor.RED + player.getName() + ChatColor.GOLD + " fell into the Void.");
			} else if (cause.equals(DamageCause.WITHER)) {
				Bukkit.getServer().broadcastMessage(ChatColor.RED + player.getName() + ChatColor.GOLD + " withered away.");
			}
		}
	}


	//Remove Minecraft's default death messages
	@EventHandler
	public void removeDeathMessage(final PlayerDeathEvent event) {
		event.setDeathMessage("");
	}
}
