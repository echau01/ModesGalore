package ca.echau.myfirstplugin.Runnables;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import ca.echau.myfirstplugin.Main;

public class RainbowArmor implements Runnable {
	private final Main plugin;
	
	public RainbowArmor(final Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public void run() {
		final Random r = new Random();

		//Red, green, and blue values range from 0 to 255. Generates a random color each time.
		final Color color = Color.fromRGB(r.nextInt(256), r.nextInt(256), r.nextInt(256));

		for (final Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()) {
			for (final ItemStack armor : onlinePlayer.getInventory().getArmorContents()) {
				if (plugin.getConfig().getBoolean("uuids." + onlinePlayer.getUniqueId().toString() + ".RainbowArmor")) {
					if (armor != null) {
						if (armor.getType() == Material.LEATHER_HELMET) {
							final LeatherArmorMeta meta = (LeatherArmorMeta) armor.getItemMeta();
							meta.setColor(color);
							armor.setItemMeta(meta);
							onlinePlayer.getInventory().setHelmet(armor);
						}
						if (armor.getType() == Material.LEATHER_CHESTPLATE) {
							final LeatherArmorMeta meta = (LeatherArmorMeta) armor.getItemMeta();
							meta.setColor(color);
							armor.setItemMeta(meta);
							onlinePlayer.getInventory().setChestplate(armor);
						}
						if (armor.getType() == Material.LEATHER_LEGGINGS) {
							final LeatherArmorMeta meta = (LeatherArmorMeta) armor.getItemMeta();
							meta.setColor(color);
							armor.setItemMeta(meta);
							onlinePlayer.getInventory().setLeggings(armor);
						}
						if (armor.getType() == Material.LEATHER_BOOTS) {
							final LeatherArmorMeta meta = (LeatherArmorMeta) armor.getItemMeta();
							meta.setColor(color);
							armor.setItemMeta(meta);
							onlinePlayer.getInventory().setBoots(armor);
						}
					}
				} else {
					if (armor != null) {
						if (armor.getType() == Material.LEATHER_HELMET) {
							final LeatherArmorMeta meta = (LeatherArmorMeta) Bukkit.getItemFactory().getItemMeta(armor.getType());
							armor.setItemMeta(meta);
							onlinePlayer.getInventory().setHelmet(armor);
						}
						if (armor.getType() == Material.LEATHER_CHESTPLATE) {
							final LeatherArmorMeta meta = (LeatherArmorMeta) Bukkit.getItemFactory().getItemMeta(armor.getType());
							armor.setItemMeta(meta);
							onlinePlayer.getInventory().setChestplate(armor);
						}
						if (armor.getType() == Material.LEATHER_LEGGINGS) {
							final LeatherArmorMeta meta = (LeatherArmorMeta) Bukkit.getItemFactory().getItemMeta(armor.getType());
							armor.setItemMeta(meta);
							onlinePlayer.getInventory().setLeggings(armor);
						}
						if (armor.getType() == Material.LEATHER_BOOTS) {
							final LeatherArmorMeta meta = (LeatherArmorMeta) Bukkit.getItemFactory().getItemMeta(armor.getType());
							armor.setItemMeta(meta);
							onlinePlayer.getInventory().setBoots(armor);
						}
					}
				}
			}
		}
	}
}
