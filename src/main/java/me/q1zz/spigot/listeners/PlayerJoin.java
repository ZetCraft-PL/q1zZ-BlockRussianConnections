package me.q1zz.spigot.listeners;

import me.q1zz.geoip.GetCountry;
import me.q1zz.spigot.Main;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        try {
        String ip = event.getPlayer().getAddress().getAddress().toString().replace("/", "");
        String countryCode = GetCountry.getCountry(ip);
            if(countryCode != null && countryCode.equals("RU")) {
                event.getPlayer().kickPlayer(ChatColor.translateAlternateColorCodes('&', Main.getInstance().KICK_MESSAGE));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
