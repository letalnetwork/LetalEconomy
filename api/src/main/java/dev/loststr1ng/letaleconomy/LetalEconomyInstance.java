package dev.loststr1ng.letaleconomy;

import dev.loststr1ng.letaleconomy.plugin.LetalEconomyPlugin;

public abstract class LetalEconomyInstance {

    private final LetalEconomyPlugin plugin;

    public LetalEconomyInstance(LetalEconomyPlugin plugin) {
        this.plugin = plugin;
    }

}
