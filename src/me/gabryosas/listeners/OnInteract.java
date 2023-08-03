package me.gabryosas.listeners;

import me.gabryosas.Main;
import me.gabryosas.commands.QACommand;
import me.gabryosas.utils.Color;
import me.gabryosas.utils.General;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class OnInteract implements Listener {
    @EventHandler
    public void onClick(PlayerInteractAtEntityEvent e) {
        Player player = e.getPlayer();
        if (!(e.getRightClicked() instanceof Player)) return;
        Player target = (Player) e.getRightClicked();
        if (!(e.getHand() == EquipmentSlot.HAND)) return;
        ItemStack mainHand = player.getInventory().getItemInMainHand();
        if (!mainHand.hasItemMeta()) return;
        if (mainHand.getType() != QACommand.material && !mainHand.getItemMeta().getDisplayName().equalsIgnoreCase(QACommand.displayName)) return;
        if (!OnFerite.arrayList.contains(target)) {
            String noDamageMessage = Color.translateHexColorCodes(Main.plugin.getConfig().getString("Message.No-Damage")).replace("%target%", target.getName());
            player.sendMessage(noDamageMessage);
            return;
        }
        player.getInventory().removeItem(General.createItemStackQuantity(QACommand.material, QACommand.displayName, QACommand.customModelData, QACommand.getLore(), 1));
        OnFerite.arrayList.remove(target);
        String reviveMessage = Color.translateHexColorCodes(Main.plugin.getConfig().getString("Message.Revive")).replace("%target%", target.getName());
        String targetReviveMessage = Color.translateHexColorCodes(Main.plugin.getConfig().getString("Message.Target-Revive")).replace("%player%", player.getName());
        player.sendMessage(reviveMessage);
        target.sendMessage(targetReviveMessage);
    }
}
