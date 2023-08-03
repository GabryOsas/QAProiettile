package me.gabryosas.listeners;

import me.gabryosas.Main;
import me.gabryosas.utils.Color;
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
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        if (plugin.getConfig().getBoolean("QAProiettile.Boolean.Credits")) {
            player.sendMessage("§9§lGMDI §7Plugin creato da §e@GabryOsas / @GMDIdevelopment");
        }

        if (!plugin.getConfig().getBoolean("QAProiettile.Boolean.Update")) {
            return;
        }

        if (!player.hasPermission(plugin.getConfig().getString("Permission.Scalpel-Perms"))) {
            return;
        }
        if (plugin.getUpdateChecker().isNewVersionAvailable()) {
            String updateMessage = Color.translateHexColorCodes(plugin.getConfig().getString("Message.Update-True").replace("%link%", "https://www.spigotmc.org/resources/qaproiettile.106719/"));
            player.sendMessage(updateMessage);
        } else {
            String updateMessage = Color.translateHexColorCodes(plugin.getConfig().getString("Message.Update-False"));
            player.sendMessage(updateMessage);
        }
    }
}

