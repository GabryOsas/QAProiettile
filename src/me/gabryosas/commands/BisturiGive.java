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
        Player player = (Player) sender;
        if (player.isOp()){
            ItemStack itemStack = new ItemStack(Material.valueOf(Main.plugin.getConfig().getString("QAProiettile.Material")));
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("QAProiettile.Bisturi")));
            itemStack.setItemMeta(itemMeta);
            player.getInventory().addItem(itemStack);
            player.sendMessage("§aBisturi givvato con successo");
        }else {
            player.sendMessage("§cNon hai abbastanza permessi");
        }
        return true;
    }
}
