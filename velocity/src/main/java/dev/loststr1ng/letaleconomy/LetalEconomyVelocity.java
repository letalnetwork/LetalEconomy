package dev.loststr1ng.letaleconomy;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import dev.loststr1ng.letaleconomy.impl.EconomyVelocity;
import dev.loststr1ng.letaleconomy.plugin.LetalEconomyPlugin;
import org.slf4j.Logger;

import java.nio.file.Path;

@Plugin(id = "letaleconomy", name = "LetalEconomy", version = "1.0", authors = {
        "loststr1ng" })
public class LetalEconomyVelocity {

    private final ProxyServer server;
    private final Logger logger;
    private LetalEconomyPlugin instance;
    private final Path dataDirectory;

    @Inject
    public LetalEconomyVelocity(ProxyServer server, Logger logger, @DataDirectory Path dataDirectory) {
        this.server = server;
        this.logger = logger;
        this.dataDirectory = dataDirectory;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        instance = new EconomyVelocity(this);
        instance.onEnable();
        logger.info("LetalEconomy (Velocity) has been enabled!");
    }

    public ProxyServer getServer() {
        return server;
    }

    public Logger getLogger() {
        return logger;
    }

    public LetalEconomyPlugin getInstance() {
        return instance;
    }

    public Path getDataDirectory() {
        return dataDirectory;
    }
}
