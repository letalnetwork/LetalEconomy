package dev.loststr1ng.letaleconomy;

import org.bukkit.plugin.java.JavaPlugin;

public class LetalEconomyPaper extends JavaPlugin {

    private LetalEconomyInstance instance;

    @Override
    public void onEnable() {
        //instance = new LetalEconomyInstance();
        //instance.onEnable();
        getLogger().info("LetalEconomy (Paper) has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("LetalEconomy (Paper) has been disabled.");
    }
}
