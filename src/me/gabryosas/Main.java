package me.gabryosas;

import me.gabryosas.commands.BisturiGive;
import me.gabryosas.commands.ReloadConfig;
import me.gabryosas.listeners.OnFerite;
import me.gabryosas.listeners.OnInteract;
import me.gabryosas.listeners.OnJoin;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Main extends JavaPlugin {
    public static Main plugin;
    @Override
    public void onEnable() {
        plugin = this;
        //Registrazione Eventi
        saveListener();
        //Registrazione Comandi
        saveCommand();
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Sto abilitanto il plugin @GMDIdevelopment");
        saveDefaultConfig();
    }
    @Override
    public void onLoad() {
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Sto abilitanto il plugin @GMDIdevelopment");
    }
    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "Sto disabilitando il plugin @GMDIdevelopment");
    }
    public void saveListener(){
        getServer().getPluginManager().registerEvents(new OnFerite(), this);
        getServer().getPluginManager().registerEvents(new OnInteract(), this);
        getServer().getPluginManager().registerEvents(new OnJoin(), this);
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Listener caricati con successo!");
    }
    public void saveCommand(){
        this.getCommand("bisturi").setExecutor(new BisturiGive());
        this.getCommand("qareload").setExecutor(new ReloadConfig());
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Comandi caricati con successo!");
    }
}
