package me.gabryosas.utils;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class UpdateChecker {
    private Plugin plugin;
    private String currentVersion;
    private String spigotResourceId;
    private String spigotResourceURL;

    public UpdateChecker(Plugin plugin, String spigotResourceId) {
        this.plugin = plugin;
        this.spigotResourceId = spigotResourceId;
        this.spigotResourceURL = "https://api.spigotmc.org/legacy/update.php?resource=" + spigotResourceId;
        PluginDescriptionFile pluginDescription = plugin.getDescription();
        this.currentVersion = pluginDescription.getVersion();
    }

    public boolean isNewVersionAvailable(Player player) {
        try {
            URL url = new URL(spigotResourceURL);
            URLConnection connection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String latestVersion = reader.readLine();
            reader.close();
            if (!latestVersion.equals(currentVersion)) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
