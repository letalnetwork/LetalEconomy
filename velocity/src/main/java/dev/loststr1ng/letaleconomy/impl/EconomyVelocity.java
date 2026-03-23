package dev.loststr1ng.letaleconomy.impl;

import dev.loststr1ng.letaleconomy.LetalEconomyVelocity;
import dev.loststr1ng.letaleconomy.config.sections.DataBaseSection;
import dev.loststr1ng.letaleconomy.entity.AbstractServer;
import dev.loststr1ng.letaleconomy.impl.server.ProxyServerSource;
import dev.loststr1ng.letaleconomy.plugin.LetalEconomyPlugin;
import dev.loststr1ng.letaleconomy.storage.MySQLStorage;
import dev.loststr1ng.letaleconomy.storage.Storage;

import java.nio.file.Path;

public class EconomyVelocity extends LetalEconomyPlugin {

    private final LetalEconomyVelocity plugin;
    private final Storage storage;

    public EconomyVelocity(LetalEconomyVelocity plugin) {
        super(plugin.getDataDirectory());
        this.plugin = plugin;
        DataBaseSection section = getConfigManager().getConfig().getDataBaseSection();
        this.storage = new MySQLStorage(this);
    }

    @Override
    public Storage getStorage() {
        return storage;
    }


    @Override
    public void onEnable() {
        storage.setup();
    }

    @Override
    public void onDisable() {

    }

    @Override
    public AbstractServer getServer() {
        return new ProxyServerSource(plugin.getServer(), plugin);
    }
}
