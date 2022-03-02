package me.q1zz.spigot;

import me.q1zz.spigot.listeners.PlayerJoin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;

    public static Main getInstance() {
        return instance;
    }

    public String KICK_MESSAGE;

    @Override
    public void onEnable() {
        instance = this;

        getConfig().options().copyDefaults(true);
        saveConfig();

        KICK_MESSAGE = getConfig().getString("settings.kick_message");

        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
    }
}
