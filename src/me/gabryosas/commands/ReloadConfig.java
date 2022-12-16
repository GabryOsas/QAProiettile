package me.gabryosas.commands;

import me.gabryosas.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadConfig implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (player.hasPermission(Main.plugin.getConfig().getString("Permission.ReloadPerms"))){
                Main.plugin.reloadConfig();
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("Message.Reload")));
            }else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("Message.NoPerms")));
            }
        }else {
            Main.plugin.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("Message.AntiConsole")));
        }
        return true;
    }
}
