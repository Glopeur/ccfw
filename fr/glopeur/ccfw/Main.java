package fr.glopeur.ccfw;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	private FrostWalkerCancel fwc = new FrostWalkerCancel(this);

	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this.fwc, this);
	}
	
	public void onDisable() {
		fwc.echantBootsAtStop();
	}
}
