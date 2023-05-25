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
    public void onClick(PlayerInteractAtEntityEvent e) {
        Player player = e.getPlayer();
        if (e.getRightClicked() instanceof Player target && e.getHand() == EquipmentSlot.HAND) {
            ItemStack mainHand = player.getInventory().getItemInMainHand();
            if (mainHand == null || !mainHand.hasItemMeta()) {
                return;
            }
            Material scalpelMaterial = Material.valueOf(Main.plugin.getConfig().getString("QAProiettile.Material"));
            String scalpelName = ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("QAProiettile.Scalpel"));
            if (mainHand.getType() == scalpelMaterial && mainHand.getItemMeta().getDisplayName().equals(scalpelName)) {
                ItemStack scalpel = new ItemStack(scalpelMaterial, 1);
                ItemMeta name = scalpel.getItemMeta();
                name.setDisplayName(scalpelName);
                scalpel.setItemMeta(name);
                if (OnFerite.arrayList.contains(target.getName())) {
                    player.getInventory().removeItem(scalpel);
                    OnFerite.arrayList.remove(target.getName());
                    String reviveMessage = ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("Message.Revive")).replace("%target%", target.getName());
                    player.sendMessage(reviveMessage);
                    String targetReviveMessage = ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("Message.Target-Revive")).replace("%player%", player.getName());
                    target.sendMessage(targetReviveMessage);
                } else {
                    String noDamageMessage = ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("Message.No-Damage")).replace("%target%", target.getName());
                    player.sendMessage(noDamageMessage);
                }
            }
        }
    }
}
