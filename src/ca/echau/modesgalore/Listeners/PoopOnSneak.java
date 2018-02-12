package ca.echau.modesgalore.Listeners;

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

import ca.echau.modesgalore.Main;

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
	private static final Map<List<FallingBlock>, UUID> POOP_TO_PLAYER_UUID = 
			new HashMap<List<FallingBlock>, UUID>();
	
	public PoopOnSneak(final Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onSneak(final PlayerToggleSneakEvent event) {
		final Player player = event.getPlayer();
		if (event.isSneaking()) {
			if (plugin.getConfig().getBoolean("uuids." + player.getUniqueId() 
					+ ".EnablePooping")) {
				final List<FallingBlock> poopBlocks = new ArrayList<FallingBlock>();
				for (int i = 0; i < 10; i++) {
					@SuppressWarnings("deprecation")
					final FallingBlock poop = player.getWorld().spawnFallingBlock(player.getLocation(), 
							Material.WOOL, (byte) 12);
					poopBlocks.add(poop);
					poop.setDropItem(false);
					final Vector initVec = player.getLocation().getDirection()
							.multiply(-(Math.random() * 5));
					final Vector finalVec = initVec.setX(initVec.getX() * Math.random())
							.setY(initVec.getY() * Math.random())
							.setZ(initVec.getZ() * Math.random());
					poop.setVelocity(finalVec);
				}
				POOP_TO_PLAYER_UUID.put(poopBlocks, player.getUniqueId());
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
	public void onPoopLand(final EntityChangeBlockEvent event) {
		if (event.getEntity() instanceof FallingBlock) {
			final FallingBlock poop = (FallingBlock) event.getEntity();
			List<FallingBlock> poopBlocksList = new ArrayList<FallingBlock>();
			for (Iterator<List<FallingBlock>> entries = POOP_TO_PLAYER_UUID.keySet().iterator();
					entries.hasNext();) {
				if ((poopBlocksList = entries.next()).contains(event.getEntity())) {
					poopBlocksList.remove(poop);
					poop.getWorld().playEffect(poop.getLocation(), Effect.STEP_SOUND, 35);
					event.setCancelled(true);
					break;
				}
			}
			if (poopBlocksList.isEmpty()) {
				POOP_TO_PLAYER_UUID.remove(poopBlocksList);
			}
		}
	}
}
