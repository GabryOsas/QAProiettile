package me.gabryosas.listeners;

import me.gabryosas.Main;
import me.gabryosas.api.events.PlayerInjectEvent;
import me.gabryosas.runnable.PotionRunnable;
import me.gabryosas.utils.Color;
import me.gabryosas.utils.General;
import me.zombie_striker.qg.api.QAWeaponDamageEntityEvent;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class OnFerite implements Listener {
    public static final List<Player> arrayList = new ArrayList<>();
    @EventHandler
    public void onFerite(QAWeaponDamageEntityEvent e) {
        if (!(e.getDamaged() instanceof Player)) {
            return;
        }

        Player player = e.getPlayer();

        Player target = (Player) e.getDamaged();
        PlayerInjectEvent customEvent = new PlayerInjectEvent(player, target, e.getGun());
        Main.plugin.getServer().getPluginManager().callEvent(customEvent);
        if (customEvent.isCancelled()) return;
        if (arrayList.contains(target)) return;
        if (target.isBlocking()) return;
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
        if (Main.plugin.getConfig().getBoolean("QAProiettile.Boolean.Knockback-On-Damage")){
            knockPlayer(player, target);
        }
        if (Main.plugin.getConfig().getBoolean("QAProiettile.Boolean.Blood-Block")) {
            Location location = target.getLocation();
            Block block = player.getWorld().getBlockAt(location);
            if (block.getType() != Material.AIR && block.getType() != Material.REDSTONE_WIRE) {
                player.getWorld().dropItem(location, General.createItemStack(block.getType()));
            }
            block.setType(Material.REDSTONE_WIRE);
            player.getWorld().playEffect(target.getLocation(), Effect.STEP_SOUND, Material.REDSTONE_BLOCK);
        }
        if (target.isInsideVehicle() && Main.plugin.getConfig().getBoolean("QAProiettile.Boolean.Exit-On-Vehicle")){
            Location location = new Location(target.getWorld(), target.getLocation().getX(), target.getLocation().getY(), target.getLocation().getZ(), target.getLocation().getYaw(), target.getLocation().getPitch());
            target.teleport(location);
        }
        target.sendTitle(
                Color.translateHexColorCodes(Main.plugin.getConfig().getString("QAProiettile.Title")),
                Color.translateHexColorCodes(Main.plugin.getConfig().getString("QAProiettile.Sub-Title")));
        arrayList.add(target);

        if (Main.plugin.getConfig().getBoolean("QAProiettile.Boolean.Effect-On-Damage")) {
            ConfigurationSection potionSection = Main.plugin.getConfig().getConfigurationSection("Events.potion-events");
            if (potionSection == null) return;
            PotionRunnable.applyPotionEffects((List<String>) potionSection.getList("Effects"), target);
        }
    }
    private static void knockPlayer(Player player, Player target) {
        Vector knockback = player.getLocation().getDirection().multiply(1.0D);
        target.setVelocity(knockback);
        new BukkitRunnable() {
            public void run() {
                target.setVelocity(knockback);
            }
        }.runTaskLater(Main.plugin, 1l);
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

        if (!arrayList.contains(player)) {
            return;
        }

        if (!Main.plugin.getConfig().getBoolean("QAProiettile.Boolean.Anti-Jump")) {
            return;
        }
        double jumpThreshold = 0.2;
        if (heightDifference > jumpThreshold) {
            e.setCancelled(true);
            if (!Main.plugin.getConfig().getString("Message.Jump").equalsIgnoreCase("none")){
                player.sendMessage(Color.translateHexColorCodes(Main.plugin.getConfig().getString("Message.Jump")));
            }
        }
    }
}
