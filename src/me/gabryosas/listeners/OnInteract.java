package me.gabryosas.listeners;

import me.gabryosas.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class OnInteract implements Listener {
    @EventHandler
    public void onClick(PlayerInteractAtEntityEvent e){
        Player player = e.getPlayer();
        if( e.getRightClicked() instanceof Player) {
            Player target = (Player) e.getRightClicked();
            if (!player.getInventory().getItemInMainHand().hasItemMeta()) return;
            if (player.getInventory().getItemInMainHand() == null) return;
            if (e.getHand() == EquipmentSlot.HAND){
            if (player.getInventory().getItemInMainHand().getType() == Material.valueOf(Main.plugin.getConfig().getString("QAProiettile.Material")) && player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("QAProiettile.Scalpel")))) {
                ItemStack bisturi = new ItemStack(Material.valueOf(Main.plugin.getConfig().getString("QAProiettile.Material")), 1);
                ItemMeta name = bisturi.getItemMeta();
                name.setDisplayName(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("QAProiettile.Scalpel")));
                bisturi.setItemMeta(name);
                if (OnFerite.arrayList.contains(target.getName())) {
                    player.getInventory().removeItem(bisturi);
                    OnFerite.arrayList.remove(target.getName());
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("Message.Revive")).replace("%target%", target.getName()));
                    target.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("Message.Target-Revive")).replace("%player%", player.getName()));
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("Message.No-Damage")).replace("%target%", target.getName()));
                }
            }
            }
        }
    }
}
