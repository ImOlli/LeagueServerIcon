package de.imolli;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerPingHandler implements Listener {

    private static ServerPingHandler instance;

    public static ServerPingHandler getInstance() {
        if (instance == null) {
            instance = new ServerPingHandler();
        }

        return instance;
    }

    @EventHandler
    public void onServerPing(ServerListPingEvent event) {
        event.setServerIcon(IconRepository.getInstance().getRandomIcon());
    }

}
