package me.q1zz.bungee.data;

import me.q1zz.bungee.Main;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.logging.Level;

public class Config {

    public static Config instance;

    public static File rfile;

    Configuration data;

    public static Config getInstance() {
        return instance;
    }

    static {
        instance = new Config();
    }

    public void setup(Plugin plugin) {
        if (plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
        if (!Main.getInstance().getDataFolder().exists()) {
            try {

                Main.getInstance().getDataFolder().mkdir();

                rfile = new File(Main.getInstance().getDataFolder(), "config.yml");

                if (!rfile.exists()) {
                    try (InputStream in = Main.getInstance().getResourceAsStream("config.yml")) {
                        Files.copy(in, rfile.toPath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                ProxyServer.getInstance().getLogger().log(Level.WARNING, "Unable to create config.yml Details: ");
                ProxyServer.getInstance().getLogger().log(Level.WARNING, "\n" + e.getMessage() + "\n");
            }
        }
        try {
            data = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(Main.getInstance().getDataFolder(), "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Configuration getData() {
        return data;
    }

    public void saveData() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(data, rfile);
        } catch(IOException e) {
            ProxyServer.getInstance().getLogger().log(Level.WARNING, "Unable to save config.yml Details: ");
            ProxyServer.getInstance().getLogger().log(Level.WARNING, "\n" + e.getMessage() + "\n");
        }
    }
}
