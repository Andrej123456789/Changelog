package me.andrej123456789.changelog;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Command implements CommandExecutor, TabExecutor {
    private static final Plugin plugin = JavaPlugin.getProvidingPlugin(Changelog.class);

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull org.bukkit.command.Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length < 1) {
            return false;
        }

        if (!sender.hasPermission("changelog.use")) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use this command!" + ChatColor.RESET);
            return true;
        }

        String user_sending = sender.getName();

        if (!(sender instanceof Player)) {
            user_sending = "SERVER";
        }

        String webhookURL = plugin.getConfig().getString("url");

        DiscordWebhook webhook = new DiscordWebhook(webhookURL);

        String message = String.join(" ", args);
        webhook.setContent("**" + user_sending + "**" + " - " + message);

        try {
            webhook.execute();
        } catch (java.io.IOException e) {
            sender.sendMessage(ChatColor.DARK_RED + e.toString() + ChatColor.RESET);
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull org.bukkit.command.Command command, @NotNull String label, @NotNull String[] args) {
        return new ArrayList<>(); /* null = all player names */
    }
}
