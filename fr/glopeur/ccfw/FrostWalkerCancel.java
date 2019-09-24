package fr.glopeur.ccfw;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class FrostWalkerCancel implements Listener {
	
	private Main plugin;
	
	public FrostWalkerCancel(Main plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	private void onCrouch(PlayerToggleSneakEvent e) {

		Player p = e.getPlayer();

		if (p.getInventory().getBoots() != null) {
			if (!p.isSneaking() && p.getInventory().getBoots().containsEnchantment(Enchantment.FROST_WALKER)) {
				p.setMetadata("hasCCFW" + p.getInventory().getBoots().getEnchantmentLevel(Enchantment.FROST_WALKER), new FixedMetadataValue(plugin, 1));
				p.getInventory().getBoots().removeEnchantment(Enchantment.FROST_WALKER);
			} else {
				for (int i = 0; i < 255; i++) {
					if (p.hasMetadata("hasCCFW" + i)) {
						p.getInventory().getBoots().addEnchantment(Enchantment.FROST_WALKER, i);
						p.removeMetadata("hasCCFW" + i, plugin);
					}
				}
			}
		} else {
		}
	}
	
	@EventHandler
	private void onPlayerQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();

		enchantBoots(p);
	}	
	
	@EventHandler
	private void onPlayerKick(PlayerKickEvent e) {
		Player p = e.getPlayer();
		
		enchantBoots(p);
	}
	
	protected void echantBootsAtStop() {
		for (Player p : plugin.getServer().getOnlinePlayers()) {
			enchantBoots(p);
		}
	}
	
	private void enchantBoots(Player p) {
		if (p.getInventory().getBoots() != null) {
			for (int i = 0; i < 255; i++) {
				if (p.hasMetadata("hasCCFW" + i)) {
					p.getInventory().getBoots().addEnchantment(Enchantment.FROST_WALKER, i);
					p.removeMetadata("hasCCFW" + i, plugin);
				}
			}
		} else {
			for (int i = 0; i < 255; i++) {
				if (p.hasMetadata("hasCCFW" + i)) {
					p.removeMetadata("hasCCFW" + i, plugin);
				}
			}
		}
	}
}
