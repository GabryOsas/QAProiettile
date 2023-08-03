package me.gabryosas.commands;

import me.gabryosas.Main;
import me.gabryosas.utils.Color;
import me.gabryosas.utils.General;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class QACommand implements CommandExecutor {
    private final String permission = Main.plugin.getConfig().getString("Permission.Scalpel-Perms");
    public static Material material = Material.valueOf(Main.plugin.getConfig().getString("QAProiettile.Items.Material"));
    public static String displayName = Color.translateHexColorCodes(Main.plugin.getConfig().getString("QAProiettile.Items.Name"));
    public static int customModelData = Main.plugin.getConfig().getInt("QAProiettile.Items.Costum-Model-Data");
    private final String messageGive = Color.translateHexColorCodes(Main.plugin.getConfig().getString("Message.Give"));
    private final String messageNoPerms = Color.translateHexColorCodes(Main.plugin.getConfig().getString("Message.No-Perms"));
    private final String messageAntiConsole = Color.translateHexColorCodes(Main.plugin.getConfig().getString("Message.Anti-Console"));
    private final String permission_reload = Main.plugin.getConfig().getString("Permission.Reload-Perms");
    private final String messageReload = Color.translateHexColorCodes(Main.plugin.getConfig().getString("Message.Reload"));
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Main.plugin.getServer().getConsoleSender().sendMessage(messageAntiConsole);
            return true;
        }
        Player player = (Player) sender;
        if (args.length != 1){
            return true;
        }
        if (args[0].equalsIgnoreCase("reload")){
            if (!player.hasPermission(permission_reload)) {
                player.sendMessage(messageNoPerms);
                return true;
            }
            Main.plugin.reloadConfig();
            player.sendMessage(messageReload);
            return true;
        }
        if (args[0].equalsIgnoreCase("give")){
            if (!player.hasPermission(permission)){
                player.sendMessage(messageNoPerms);
                return true;
            }
            player.getInventory().addItem(General.createItemStack(material, displayName, customModelData, getLore()));
            player.sendMessage(messageGive);
            return true;
        }
        return true;
    }
    public static List<String> getLore() {
        List<String> lore = Main.plugin.getConfig().getStringList("QAProiettile.Items.Lore");
        for (int i = 0; i < lore.size(); i++) {
            String translated = Color.translateHexColorCodes(lore.get(i));
            lore.set(i, translated);
        }
        return lore;
    }
}
