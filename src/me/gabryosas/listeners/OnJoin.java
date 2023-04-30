package me.gabryosas.listeners;

import me.gabryosas.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnJoin implements Listener {
    private Main plugin;

    public OnJoin(Main plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        if (Main.plugin.getConfig().getBoolean("QAProiettile.Credits")){
            player.sendMessage("§9§lGMDI §7Plugin creato da §e@GabryOsas / @GMDIdevelopment");
        }
        if (Main.plugin.getConfig().getBoolean("QAProiettile.Update")) {
            if (player.hasPermission(Main.plugin.getConfig().getString("Permission.Scalpel-Perms"))) {
                if (plugin.getUpdateChecker().isNewVersionAvailable(player)) {
                    String updateMessage = ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("Message.Update-True"));
                    player.sendMessage(updateMessage);
                } else {
                    String updateMessage = ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("Message.Update-False"));
                    player.sendMessage(updateMessage);
                }
            }
        }
    }
}
