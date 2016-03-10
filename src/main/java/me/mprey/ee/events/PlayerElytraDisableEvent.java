package me.mprey.ee.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

/**
 * Created by Mason Prey on 3/9/16.
 */
public class PlayerElytraDisableEvent extends PlayerEvent implements Cancellable {

    private static HandlerList handlerList = new HandlerList();

    private boolean cancelled;

    public PlayerElytraDisableEvent(Player player) {
        super(player);
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public void setCancelled(boolean b) {
        this.cancelled = b;
    }

    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

}
