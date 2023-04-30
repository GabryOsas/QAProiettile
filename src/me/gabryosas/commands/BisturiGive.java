package me.gabryosas.commands;

import me.gabryosas.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BisturiGive implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(Main.plugin.getConfig().getString("Permission.Scalpel-Perms"))){
                ItemStack itemStack = new ItemStack(Material.valueOf(Main.plugin.getConfig().getString("QAProiettile.Material")));
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("QAProiettile.Scalpel")));
                itemMeta.setCustomModelData(Main.plugin.getConfig().getInt("QAProiettile.Costum-Model-Data"));
                itemStack.setItemMeta(itemMeta);
                player.getInventory().addItem(itemStack);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("Message.Give")));
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("Message.No-Perms")));
            }
        }else {
            Main.plugin.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("Message.Anti-Console")));
        }
        return true;
    }
}
