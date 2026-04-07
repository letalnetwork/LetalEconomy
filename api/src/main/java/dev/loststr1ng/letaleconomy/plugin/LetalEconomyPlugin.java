package dev.loststr1ng.letaleconomy.plugin;

import dev.loststr1ng.letaleconomy.entity.AbstractServer;
import dev.loststr1ng.letaleconomy.manager.ConfigManager;
import dev.loststr1ng.letaleconomy.storage.Storage;

import java.nio.file.Path;

public abstract class LetalEconomyPlugin {

    private final ConfigManager configManager;

    public LetalEconomyPlugin(Path directory) {
        this.configManager = new ConfigManager(directory);
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public abstract Storage getStorage();

    public abstract void onEnable();

    public abstract void onDisable();

    public abstract AbstractServer getServer();

}