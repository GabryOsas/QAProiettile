package me.gabryosas.api;

import me.gabryosas.listeners.OnFerite;
import org.bukkit.entity.Player;

import java.util.List;

public class QAProiettileAPI {
    /*
    Coming soon...
     */
    public boolean hasFerite(Player player){
        if (OnFerite.arrayList.contains(player)){
            return true;
        }
        return false;
    }
    public void addPlayer(Player player){
        OnFerite.arrayList.add(player);
    }
    public void removePlayer(Player player){
        OnFerite.arrayList.remove(player);
    }
    public List<Player> getPlayers() {
        return OnFerite.arrayList;
    }
}
