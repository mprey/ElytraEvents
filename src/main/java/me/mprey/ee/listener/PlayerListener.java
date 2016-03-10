package me.mprey.ee.listener;

import me.mprey.ee.Main;
import me.mprey.ee.events.PlayerElytraDisableEvent;
import me.mprey.ee.events.PlayerElytraEnableEvent;
import me.mprey.ee.events.PlayerElytraFlyEvent;
import net.minecraft.server.v1_9_R1.EntityPlayer;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Mason Prey on 3/9/16.
 */
public class PlayerListener implements Listener {

    private List<UUID> flyingList;

    private static final int ELYTRA_FLAG = 7;

    private Main instance;

    public PlayerListener(Main instance) {
        instance.getServer().getPluginManager().registerEvents(this, instance);
        this.flyingList = new ArrayList<>();
        this.instance = instance;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        EntityPlayer player = ((CraftPlayer) e.getPlayer()).getHandle();

        if (isFlying(player)) {
            if (flyingList.contains(e.getPlayer().getUniqueId())) {
                PlayerElytraFlyEvent elytraFlyEvent = new PlayerElytraFlyEvent(e.getPlayer(), e.getFrom(), e.getTo());
                instance.getServer().getPluginManager().callEvent(elytraFlyEvent);

                e.setCancelled(elytraFlyEvent.isCancelled());
            } else {
                PlayerElytraEnableEvent elytraEnableEvent = new PlayerElytraEnableEvent(e.getPlayer());
                instance.getServer().getPluginManager().callEvent(elytraEnableEvent);

                if (elytraEnableEvent.isCancelled()) {
                    setFlying(player, false);
                } else {
                    flyingList.add(e.getPlayer().getUniqueId());
                }
            }
        } else if (flyingList.contains(e.getPlayer().getUniqueId())) {
            PlayerElytraDisableEvent elytraDisableEvent = new PlayerElytraDisableEvent(e.getPlayer());
            instance.getServer().getPluginManager().callEvent(elytraDisableEvent);

            if (elytraDisableEvent.isCancelled()) {
                setFlying(player, true);
            } else {
                flyingList.remove(e.getPlayer().getUniqueId());
            }
        }
    }

    private void setFlying(EntityPlayer player, boolean flying) {
        player.setFlag(ELYTRA_FLAG, flying);
    }

    private boolean isFlying(EntityPlayer player) {
        return player.cB();
    }

}
