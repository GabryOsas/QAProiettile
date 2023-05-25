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
    private final String permission = Main.plugin.getConfig().getString("Permission.Scalpel-Perms");
    private final String material = Main.plugin.getConfig().getString("QAProiettile.Material");
    private final String displayName = ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("QAProiettile.Scalpel"));
    private final int customModelData = Main.plugin.getConfig().getInt("QAProiettile.Costum-Model-Data");
    private final String messageGive = ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("Message.Give"));
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

        ItemStack itemStack = new ItemStack(Material.valueOf(material));
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayName);
        itemMeta.setCustomModelData(customModelData);
        itemStack.setItemMeta(itemMeta);

        player.getInventory().addItem(itemStack);
        player.sendMessage(messageGive);
        return true;
    }
}

