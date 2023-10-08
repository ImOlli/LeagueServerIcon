package de.imolli;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class LeagueServerIconPlugin extends JavaPlugin {

    public static Logger getPluginLogger() {
        return getPlugin(LeagueServerIconPlugin.class).getLogger();
    }

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(ServerPingHandler.getInstance(), this);
        IconRepository.getInstance().loadIcons();
    }

}
