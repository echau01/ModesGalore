package me.echau.myfirstplugin.Runnables;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import me.echau.myfirstplugin.Main;

public class RainbowArmor implements Runnable {
	private final Main plugin;
	
	public RainbowArmor(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public void run() {
		Random r = new Random();

		//Red, green, and blue values range from 0 to 255. Generates a random color each time.
		Color color = Color.fromRGB(r.nextInt(256), r.nextInt(256), r.nextInt(256));

		for (Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()) {
			for (ItemStack armor : onlinePlayer.getInventory().getArmorContents()) {
				if (plugin.getConfig().getBoolean("uuids." + onlinePlayer.getUniqueId().toString() + ".RainbowArmor")) {
					if (armor != null) {
						if (armor.getType() == Material.LEATHER_HELMET) {
							LeatherArmorMeta meta = (LeatherArmorMeta) armor.getItemMeta();
							meta.setColor(color);
							armor.setItemMeta(meta);
							onlinePlayer.getInventory().setHelmet(armor);
						}
						if (armor.getType() == Material.LEATHER_CHESTPLATE) {
							LeatherArmorMeta meta = (LeatherArmorMeta) armor.getItemMeta();
							meta.setColor(color);
							armor.setItemMeta(meta);
							onlinePlayer.getInventory().setChestplate(armor);
						}
						if (armor.getType() == Material.LEATHER_LEGGINGS) {
							LeatherArmorMeta meta = (LeatherArmorMeta) armor.getItemMeta();
							meta.setColor(color);
							armor.setItemMeta(meta);
							onlinePlayer.getInventory().setLeggings(armor);
						}
						if (armor.getType() == Material.LEATHER_BOOTS) {
							LeatherArmorMeta meta = (LeatherArmorMeta) armor.getItemMeta();
							meta.setColor(color);
							armor.setItemMeta(meta);
							onlinePlayer.getInventory().setBoots(armor);
						}
					}
				} else {
					if (armor != null)
					{
						if (armor.getType() == Material.LEATHER_HELMET) {
							LeatherArmorMeta meta = (LeatherArmorMeta) Bukkit.getItemFactory().getItemMeta(armor.getType());
							armor.setItemMeta(meta);
							onlinePlayer.getInventory().setHelmet(armor);
						}
						if (armor.getType() == Material.LEATHER_CHESTPLATE) {
							LeatherArmorMeta meta = (LeatherArmorMeta) Bukkit.getItemFactory().getItemMeta(armor.getType());
							armor.setItemMeta(meta);
							onlinePlayer.getInventory().setChestplate(armor);
						}
						if (armor.getType() == Material.LEATHER_LEGGINGS) {
							LeatherArmorMeta meta = (LeatherArmorMeta) Bukkit.getItemFactory().getItemMeta(armor.getType());
							armor.setItemMeta(meta);
							onlinePlayer.getInventory().setLeggings(armor);
						}
						if (armor.getType() == Material.LEATHER_BOOTS) {
							LeatherArmorMeta meta = (LeatherArmorMeta) Bukkit.getItemFactory().getItemMeta(armor.getType());
							armor.setItemMeta(meta);
							onlinePlayer.getInventory().setBoots(armor);
						}
					}
				}
			}
		}
	}
}
