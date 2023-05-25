package me.gabryosas;

import me.gabryosas.commands.BisturiGive;
import me.gabryosas.commands.ReloadConfig;
import me.gabryosas.listeners.OnDeath;
import me.gabryosas.listeners.OnFerite;
import me.gabryosas.listeners.OnInteract;
import me.gabryosas.listeners.OnJoin;
import me.gabryosas.utils.UpdateChecker;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    public static Main plugin;
    private UpdateChecker updateChecker;
    @Override
    public void onEnable() {
        plugin = this;
        saveListener();
        saveCommand();
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "\n" +
                "┏━━━┓┏━━━┓┏━━━┓━━━━━━━━━━━━━━┏┓━━┏┓━━━┏┓━━━━━\n" +
                "┃┏━┓┃┃┏━┓┃┃┏━┓┃━━━━━━━━━━━━━┏┛┗┓┏┛┗┓━━┃┃━━━━━\n" +
                "┃┃━┃┃┃┃━┃┃┃┗━┛┃┏━┓┏━━┓┏┓┏━━┓┗┓┏┛┗┓┏┛┏┓┃┃━┏━━┓\n" +
                "┃┃━┃┃┃┗━┛┃┃┏━━┛┃┏┛┃┏┓┃┣┫┃┏┓┃━┃┃━━┃┃━┣┫┃┃━┃┏┓┃\n" +
                "┃┗━┛┃┃┏━┓┃┃┃━━━┃┃━┃┗┛┃┃┃┃┃━┫━┃┗┓━┃┗┓┃┃┃┗┓┃┃━┫\n" +
                "┗━━┓┃┗┛━┗┛┗┛━━━┗┛━┗━━┛┗┛┗━━┛━┗━┛━┗━┛┗┛┗━┛┗━━┛\n" +
                "━━━┗┛━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
                "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
                "Plugin abilitato con successo! Creato da: " + ChatColor.GOLD + "@GMDIdevelopment / @GabryOsas");
        saveDefaultConfig();
        updateChecker = new UpdateChecker(this, "106719");
    }
    @Override
    public void onLoad() {
        getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + "\n" +
                "┏━━━┓┏━━━┓┏━━━┓━━━━━━━━━━━━━━┏┓━━┏┓━━━┏┓━━━━━\n" +
                "┃┏━┓┃┃┏━┓┃┃┏━┓┃━━━━━━━━━━━━━┏┛┗┓┏┛┗┓━━┃┃━━━━━\n" +
                "┃┃━┃┃┃┃━┃┃┃┗━┛┃┏━┓┏━━┓┏┓┏━━┓┗┓┏┛┗┓┏┛┏┓┃┃━┏━━┓\n" +
                "┃┃━┃┃┃┗━┛┃┃┏━━┛┃┏┛┃┏┓┃┣┫┃┏┓┃━┃┃━━┃┃━┣┫┃┃━┃┏┓┃\n" +
                "┃┗━┛┃┃┏━┓┃┃┃━━━┃┃━┃┗┛┃┃┃┃┃━┫━┃┗┓━┃┗┓┃┃┃┗┓┃┃━┫\n" +
                "┗━━┓┃┗┛━┗┛┗┛━━━┗┛━┗━━┛┗┛┗━━┛━┗━┛━┗━┛┗┛┗━┛┗━━┛\n" +
                "━━━┗┛━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
                "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
                "Sto abilitando il plugin...");
    }
    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "\n" +
                "┏━━━┓┏━━━┓┏━━━┓━━━━━━━━━━━━━━┏┓━━┏┓━━━┏┓━━━━━\n" +
                "┃┏━┓┃┃┏━┓┃┃┏━┓┃━━━━━━━━━━━━━┏┛┗┓┏┛┗┓━━┃┃━━━━━\n" +
                "┃┃━┃┃┃┃━┃┃┃┗━┛┃┏━┓┏━━┓┏┓┏━━┓┗┓┏┛┗┓┏┛┏┓┃┃━┏━━┓\n" +
                "┃┃━┃┃┃┗━┛┃┃┏━━┛┃┏┛┃┏┓┃┣┫┃┏┓┃━┃┃━━┃┃━┣┫┃┃━┃┏┓┃\n" +
                "┃┗━┛┃┃┏━┓┃┃┃━━━┃┃━┃┗┛┃┃┃┃┃━┫━┃┗┓━┃┗┓┃┃┃┗┓┃┃━┫\n" +
                "┗━━┓┃┗┛━┗┛┗┛━━━┗┛━┗━━┛┗┛┗━━┛━┗━┛━┗━┛┗┛┗━┛┗━━┛\n" +
                "━━━┗┛━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
                "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
                "Sto disabilitando il plugin...");
    }
    public UpdateChecker getUpdateChecker() {
        return updateChecker;
    }
    public void saveListener(){
        getServer().getPluginManager().registerEvents(new OnFerite(), this);
        getServer().getPluginManager().registerEvents(new OnDeath(), this);
        getServer().getPluginManager().registerEvents(new OnInteract(), this);
        getServer().getPluginManager().registerEvents(new OnJoin(this), this);
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "\n" +
                "┏━━━┓┏━━━┓┏━━━┓━━━━━━━━━━━━━━┏┓━━┏┓━━━┏┓━━━━━\n" +
                "┃┏━┓┃┃┏━┓┃┃┏━┓┃━━━━━━━━━━━━━┏┛┗┓┏┛┗┓━━┃┃━━━━━\n" +
                "┃┃━┃┃┃┃━┃┃┃┗━┛┃┏━┓┏━━┓┏┓┏━━┓┗┓┏┛┗┓┏┛┏┓┃┃━┏━━┓\n" +
                "┃┃━┃┃┃┗━┛┃┃┏━━┛┃┏┛┃┏┓┃┣┫┃┏┓┃━┃┃━━┃┃━┣┫┃┃━┃┏┓┃\n" +
                "┃┗━┛┃┃┏━┓┃┃┃━━━┃┃━┃┗┛┃┃┃┃┃━┫━┃┗┓━┃┗┓┃┃┃┗┓┃┃━┫\n" +
                "┗━━┓┃┗┛━┗┛┗┛━━━┗┛━┗━━┛┗┛┗━━┛━┗━┛━┗━┛┗┛┗━┛┗━━┛\n" +
                "━━━┗┛━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
                "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
                "Listener caricati con successo!");
    }
    public void saveCommand(){
        this.getCommand("bisturi").setExecutor(new BisturiGive());
        this.getCommand("qareload").setExecutor(new ReloadConfig());
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "\n" +
                "┏━━━┓┏━━━┓┏━━━┓━━━━━━━━━━━━━━┏┓━━┏┓━━━┏┓━━━━━\n" +
                "┃┏━┓┃┃┏━┓┃┃┏━┓┃━━━━━━━━━━━━━┏┛┗┓┏┛┗┓━━┃┃━━━━━\n" +
                "┃┃━┃┃┃┃━┃┃┃┗━┛┃┏━┓┏━━┓┏┓┏━━┓┗┓┏┛┗┓┏┛┏┓┃┃━┏━━┓\n" +
                "┃┃━┃┃┃┗━┛┃┃┏━━┛┃┏┛┃┏┓┃┣┫┃┏┓┃━┃┃━━┃┃━┣┫┃┃━┃┏┓┃\n" +
                "┃┗━┛┃┃┏━┓┃┃┃━━━┃┃━┃┗┛┃┃┃┃┃━┫━┃┗┓━┃┗┓┃┃┃┗┓┃┃━┫\n" +
                "┗━━┓┃┗┛━┗┛┗┛━━━┗┛━┗━━┛┗┛┗━━┛━┗━┛━┗━┛┗┛┗━┛┗━━┛\n" +
                "━━━┗┛━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
                "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
                "Comandi caricati con successo!");
    }
}
