package me.gabryosas.runnable;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class PotionRunnable extends BukkitRunnable {

    private List<Player> playerList;
    private FileConfiguration config;

    public PotionRunnable(List<Player> playerList, FileConfiguration config) {
        this.playerList = playerList;
        this.config = config;
    }
    @Override
    public void run() {
        ConfigurationSection potionSection = config.getConfigurationSection("Events.potion-events");
        if (potionSection != null && potionSection.getBoolean("Enable")) {
            applyPotionEffects((List<String>) potionSection.getList("Effects"));
        }
    }
    public static void applyPotionEffects(List<String> effects, Player player) {
        if (effects != null) {
            for (String effect : effects) {
                String[] effectData = effect.split(", ");
                if (effectData.length >= 3) {
                    String effectType = effectData[0];
                    int level = Integer.parseInt(effectData[1]);
                    int duration = Integer.parseInt(effectData[2]) * 20;
                    PotionEffectType potionEffectType = PotionEffectType.getByName(effectType);
                    if (potionEffectType != null) {
                        player.addPotionEffect(new PotionEffect(potionEffectType, duration, level - 1));
                    }
                }
            }
        }
    }
    private void applyPotionEffects(List<String> effects) {
        if (effects != null) {
            for (String effect : effects) {
                String[] effectData = effect.split(", ");
                if (effectData.length >= 3) {
                    String effectType = effectData[0];
                    int level = Integer.parseInt(effectData[1]);
                    int duration = Integer.parseInt(effectData[2]) * 20;
                    PotionEffectType potionEffectType = PotionEffectType.getByName(effectType);
                    if (potionEffectType != null) {
                        for (Player player : playerList) {
                            player.addPotionEffect(new PotionEffect(potionEffectType, duration, level - 1));
                        }
                    }
                }
            }
        }
    }
}