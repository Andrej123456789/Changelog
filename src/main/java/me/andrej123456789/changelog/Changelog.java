package me.andrej123456789.changelog;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Changelog extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getLogger().info("Initialization of Changelog is done!");
        getServer().getConsoleSender().sendMessage("[SimpleJobs] If you like this plugin, give it a star on Github: "
                                                    + ChatColor.DARK_AQUA +
                                                    "https://github.com/Andrej123456789/Changelog");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
