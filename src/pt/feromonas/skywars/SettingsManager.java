package pt.feromonas.skywars;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class SettingsManager {

	private final Main plugin;
	private final String name;

	private FileConfiguration fileConfiguration;
	private File file;

	public SettingsManager(Main plugin, String name) {
		this.plugin = plugin;
		this.name = name;
	}

	private void checkFolder() {
		File file = new File(plugin.getDataFolder() + "/settings/");
		if(!file.exists()) file.mkdir();
	}

	public void setupFile() {
		checkFolder();

		file = new File(plugin.getDataFolder() + "/settings/", name + ".yml");

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				Bukkit.getServer().getLogger().severe("Could not create " + name + ".yml");
			}
		}
		fileConfiguration = YamlConfiguration.loadConfiguration(file);
	}

	public FileConfiguration getFile() {
		return fileConfiguration;
	}

	public void saveFile() {
		try {
			fileConfiguration.save(file);
		} catch (IOException e) {
			Bukkit.getServer().getLogger().severe("Could not save " + name + ".yml");
		}
	}

	public void reloadFile() {
		fileConfiguration = YamlConfiguration.loadConfiguration(file);
	}


}