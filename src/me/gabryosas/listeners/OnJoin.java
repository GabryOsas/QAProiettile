package me.gabryosas.listeners;

import me.gabryosas.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnJoin implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        if (Main.plugin.getConfig().getBoolean("QAProiettile.Credits") == true){
            Player player = e.getPlayer();
            player.sendMessage("§9§lGMDI §7Plugin creato da §e@GabryOsas / @GMDIdevelopment");
        }
    }
}
