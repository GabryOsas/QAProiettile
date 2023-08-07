package me.gabryosas.api.events;

import me.zombie_striker.qg.guns.Gun;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerReviveTargetEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final Player target;
    private boolean cancelled = false;

    public PlayerReviveTargetEvent(Player player, Player target) {
        this.player = player;
        this.target = target;
    }

    public Player getPlayer() {
        return player;
    }
    public Player getTarget(){
        return target;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}