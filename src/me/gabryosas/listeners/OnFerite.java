package me.gabryosas.listeners;

import me.gabryosas.Main;
import me.zombie_striker.qg.api.QAWeaponDamageEntityEvent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import java.util.ArrayList;
import java.util.List;


public class OnFerite implements Listener {
    public static final List<String> arrayList = new ArrayList<>();
    @EventHandler
    public void onFerite(QAWeaponDamageEntityEvent e){
        if (e.getDamaged() instanceof Player) {
            Player target = (Player) e.getDamaged();
            if (!arrayList.contains(target.getName())) {
                target.sendTitle(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("QAProiettile.Title")), ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("QAProiettile.SubTitle")), 3, 40, 3);
                arrayList.add(target.getName());
            }
        }
    }
    @EventHandler
    public void onJump(PlayerMoveEvent e){
        Player player = e.getPlayer();
        if (e.getTo().getY() > e.getFrom().getY()) {
            if (arrayList.contains(player.getName())) {
                e.setCancelled(true);
                player.sendMessage("§cSei ferito da un proiettile, di conseguenza non puoi saltare!");
            }
        }
    }
}
