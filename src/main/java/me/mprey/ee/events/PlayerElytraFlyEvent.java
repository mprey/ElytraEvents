package me.mprey.ee.events;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * Created by Mason Prey on 3/9/16.
 */
public class PlayerElytraFlyEvent extends PlayerMoveEvent {

    private static HandlerList handlerList = new HandlerList();

    public PlayerElytraFlyEvent(Player player, Location from, Location to) {
        super(player, from, to);
    }

    public  HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
