package me.andrej123456789.changelog;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Changelog extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic

        // Load PlaceholderAPI
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            Bukkit.getPluginManager().registerEvents(this, this);
        } else {
            getLogger().warning("Could not find PlaceholderAPI! This plugin is required.");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        // Load configuration
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        getLogger().info("Initialization of Changelog is done!");
        getServer().getConsoleSender().sendMessage("[Changelog] If you like this plugin, give it a star on Github: "
                                                    + ChatColor.DARK_AQUA +
                                                    "https://github.com/Andrej123456789/Changelog" +
                                                    ChatColor.RESET);

        getCommand("changelog").setExecutor(new Command());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
