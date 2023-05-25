package me.gabryosas.listeners;

import me.gabryosas.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class OnDeath implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        Player player = e.getEntity();
        if (OnFerite.arrayList.contains(player.getName()) && Main.plugin.getConfig().getBoolean("QAProiettile.Remove-OnDeath")) {
            OnFerite.arrayList.remove(player.getName());
        }
    }
}

