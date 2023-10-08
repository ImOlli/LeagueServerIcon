package de.imolli;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.util.CachedServerIcon;

public class ServerPingHandler implements Listener {

    @EventHandler
    public void onServerPing(ServerListPingEvent event) {

        event.setServerIcon();
    }

}
