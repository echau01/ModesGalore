package me.echau.myfirstplugin.Listeners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.util.Vector;

import me.echau.myfirstplugin.Main;

public class PoopOnSneak implements Listener {
	private final Main plugin;
	
	/* 
	 * Keeps track of who fired which poop blocks 
	 * 
	 * This map stores all of the poop blocks (in Lists), mapping each List to the UUID of the 
	 * player who fired them. Each player who fired a poop block that is still in the air has a 
	 * List of poop blocks. When all of a player's poop blocks have landed, the mapping of the 
	 * List of poop blocks to the player UUID is removed.
	 */
	private static Map<List<FallingBlock>, UUID> poopToPlayerUUID = 
			new HashMap<List<FallingBlock>, UUID>();
	
	public PoopOnSneak(Main plugin) {
		this.plugin = plugin;
	}
	
	/* For classes using getPoopToPlayerUUID() method */
	public PoopOnSneak() {
		this.plugin = null;
	}
	
	@EventHandler
	public void onSneak(PlayerToggleSneakEvent event) {
		Player player = event.getPlayer();
		if (event.isSneaking()) {
			if (plugin.getConfig().getBoolean("uuids." + player.getUniqueId() 
					+ ".EnablePooping")) {
				List<FallingBlock> poopBlocks = new ArrayList<FallingBlock>();
				for (int i = 0; i < 10; i++) {
					@SuppressWarnings("deprecation")
					FallingBlock poop = player.getWorld().spawnFallingBlock(player.getLocation(), 
							Material.WOOL, (byte) 12);
					poopBlocks.add(poop);
					poop.setDropItem(false);
					Vector initVec = player.getLocation().getDirection()
							.multiply(-(Math.random() * 5));
					Vector finalVec = initVec.setX(initVec.getX() * Math.random())
							.setY(initVec.getY() * Math.random())
							.setZ(initVec.getZ() * Math.random());
					poop.setVelocity(finalVec);
				}
				poopToPlayerUUID.put(poopBlocks, player.getUniqueId());
			}
		}
	}

	/* 
	 * Removes poop blocks from the List of poop blocks in the Map, updates the Map.
	 * 
	 * The block break effect spawns white wool particles, not brown wool. There does not seem to
	 * be a fix for this.
	 */
	@EventHandler
	public void onPoopFall(EntityChangeBlockEvent event) {
		if (event.getEntity() instanceof FallingBlock) {
			FallingBlock poop = (FallingBlock) event.getEntity();
			List<FallingBlock> poopBlocks = new ArrayList<FallingBlock>();
			Iterator<Map.Entry<List<FallingBlock>, UUID>> it = poopToPlayerUUID.entrySet()
					.iterator();
			while (it.hasNext()) {
				if ((poopBlocks = it.next().getKey()).contains(event.getEntity())) {
					poopBlocks.remove(poop);
					break;
				}
			}
			if (poopBlocks.isEmpty()) {
				poopToPlayerUUID.remove(poopBlocks);
			}
			poop.getWorld().playEffect(poop.getLocation(), Effect.STEP_SOUND, 35);
			event.setCancelled(true);
		}
	}
	
	public static Map<List<FallingBlock>, UUID> getPoopToPlayerUUID() {
		return poopToPlayerUUID;
	}
}
