package me.gabryosas.listeners;

import me.gabryosas.Main;
import me.gabryosas.api.events.PlayerInjectEvent;
import me.gabryosas.api.events.PlayerReviveTargetEvent;
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
        if (!mainHand.getItemMeta().getDisplayName().equalsIgnoreCase(QACommand.displayName)) return;
        PlayerReviveTargetEvent customEvent = new PlayerReviveTargetEvent(player, target);
        Main.plugin.getServer().getPluginManager().callEvent(customEvent);
        if (customEvent.isCancelled()) return;
        if (target.isBlocking()) return;
        if (!OnFerite.arrayList.contains(target)) {
            String noDamageMessage = Color.translateHexColorCodes(Main.plugin.getConfig().getString("Message.No-Damage")).replace("%target%", target.getName());
            player.sendMessage(noDamageMessage);
            return;
        }
        OnFerite.arrayList.remove(target);
        String reviveMessage = Color.translateHexColorCodes(Main.plugin.getConfig().getString("Message.Revive")).replace("%target%", target.getName());
        String targetReviveMessage = Color.translateHexColorCodes(Main.plugin.getConfig().getString("Message.Target-Revive")).replace("%player%", player.getName());
        player.sendMessage(reviveMessage);
        target.sendMessage(targetReviveMessage);
        if (Main.plugin.getConfig().getBoolean("QAProiettile.Boolean.Remove-Items")){
            player.getInventory().removeItem(General.createItemStack(QACommand.material, QACommand.displayName, QACommand.customModelData, QACommand.getLore(), 1));
        }
    }
}
