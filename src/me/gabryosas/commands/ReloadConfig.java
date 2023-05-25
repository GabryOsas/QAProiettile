package me.gabryosas.commands;

import me.gabryosas.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadConfig implements CommandExecutor {
    private final String permission = Main.plugin.getConfig().getString("Permission.Reload-Perms");
    private final String messageReload = ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("Message.Reload"));
    private final String messageNoPerms = ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("Message.No-Perms"));
    private final String messageAntiConsole = ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("Message.Anti-Console"));

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Main.plugin.getServer().getConsoleSender().sendMessage(messageAntiConsole);
            return true;
        }

        Player player = (Player) sender;
        if (!player.hasPermission(permission)) {
            player.sendMessage(messageNoPerms);
            return true;
        }

        Main.plugin.reloadConfig();
        player.sendMessage(messageReload);
        return true;
    }
}

