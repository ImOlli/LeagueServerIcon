package de.imolli;

import org.bukkit.plugin.java.JavaPlugin;

public final class LeagueServerIcon extends JavaPlugin {

    @Override
    public void onEnable() {
        IconRepository.getInstance().loadIcons();
    }

}
