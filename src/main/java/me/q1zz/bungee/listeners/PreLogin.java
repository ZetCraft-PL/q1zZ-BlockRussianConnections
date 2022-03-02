package me.q1zz.bungee.listeners;

import me.q1zz.bungee.Main;
import me.q1zz.geoip.GetCountry;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PreLogin implements Listener {

    @EventHandler
    public void onPreLogin(PreLoginEvent event) {
        try {
            String ip = event.getConnection().getAddress().getAddress().toString().replace("/", "");
            String countryCode = GetCountry.getCountry(ip);
            if (countryCode != null && countryCode.equals("RU")) {
                event.getConnection().disconnect(ChatColor.translateAlternateColorCodes('&', Main.getInstance().KICK_MESSAGE));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
