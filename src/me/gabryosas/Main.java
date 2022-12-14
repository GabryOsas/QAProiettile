package me.gabryosas;

import me.gabryosas.commands.BisturiGive;
import me.gabryosas.listeners.OnFerite;
import me.gabryosas.listeners.OnInteract;
import me.gabryosas.listeners.OnJoin;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    public static Main plugin;
    @Override
    public void onEnable() {
        plugin = this;
        //Registrazione Eventi
        getServer().getPluginManager().registerEvents(new OnFerite(), this);
        getServer().getPluginManager().registerEvents(new OnInteract(), this);
        getServer().getPluginManager().registerEvents(new OnJoin(), this);
        //Registrazione Comandi
        this.getCommand("bisturi").setExecutor(new BisturiGive());
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
}
