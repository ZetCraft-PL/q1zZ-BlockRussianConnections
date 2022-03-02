package me.q1zz.bungee;

import me.q1zz.bungee.data.Config;
import me.q1zz.bungee.listeners.PreLogin;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin {

    private static Main instance;

    public static Main getInstance() {
        return instance;
    }

    public String KICK_MESSAGE;

    @Override
    public void onEnable() {
        instance = this;

        Config.getInstance().setup(this);
        KICK_MESSAGE = Config.getInstance().getData().getString("settings.kick_message");

        ProxyServer.getInstance().getPluginManager().registerListener(this, new PreLogin());

    }
}
