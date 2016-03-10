package me.mprey.ee;

import me.mprey.ee.listener.PlayerListener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Mason Prey on 3/9/16.
 */
public class Main extends JavaPlugin {

    private static Main instance;

    public void onEnable() {
        instance = this;

        new PlayerListener(this);
    }

    public void onDisable() {}

}
