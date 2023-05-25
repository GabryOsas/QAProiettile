package me.gabryosas.listeners;

import me.gabryosas.Main;
import me.zombie_striker.qg.api.QAWeaponDamageEntityEvent;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.List;

public class OnFerite implements Listener {
    public static final List<String> arrayList = new ArrayList<>();
    @EventHandler
    public void onFerite(QAWeaponDamageEntityEvent e) {
        if (!(e.getDamaged() instanceof Player)) {
            return;
        }

        Player player = e.getPlayer();
        Player target = (Player) e.getDamaged();

        if (player.getWorld().getName().equalsIgnoreCase(Main.plugin.getConfig().getString("QAProiettile.Blacklist-World"))) {
            return;
        }

        if (target.hasPermission(Main.plugin.getConfig().getString("Permission.Override-Perms"))) {
            return;
        }

        String gun = e.getGun().getName();

        if (Main.plugin.getConfig().getStringList("QAProiettile.Blacklist-Gun").contains(gun)) {
            return;
        }

        target.sendTitle(
                ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("QAProiettile.Title")),
                ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("QAProiettile.Sub-Title")));
        arrayList.add(target.getName());
    }


    @EventHandler
    public void onJump(PlayerMoveEvent e) {
        Location from = e.getFrom();
        Location to = e.getTo();
        double heightDifference = to.getY() - from.getY();
        if (heightDifference <= 0) {
            return;
        }

        Player player = e.getPlayer();

        if (!arrayList.contains(player.getName())) {
            return;
        }

        if (!Main.plugin.getConfig().getBoolean("QAProiettile.Anti-Jump")) {
            return;
        }
        double jumpThreshold = 0.2;
        if (heightDifference > jumpThreshold) {
            e.setCancelled(true);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("Message.Jump")));
        }
    }
}
