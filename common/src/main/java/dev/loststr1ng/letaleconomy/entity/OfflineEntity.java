package dev.loststr1ng.letaleconomy.entity;

import dev.loststr1ng.letaleconomy.plugin.LetalEconomyPlugin;

import java.util.UUID;

public class OfflineEntity extends AbstractEntity{

    private final String name;
    private final UUID uuid;
    private final LetalEconomyPlugin plugin;

    public OfflineEntity(String name, UUID uuid, LetalEconomyPlugin plugin) {
        this.name = name;
        this.uuid = uuid;
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }

    @Override
    public void sendMessage(String message) {

    }

    @Override
    public void sendTitle(String title, String subtitle, int fadeIn, int stay, int fadeOut) {

    }

    @Override
    public void sendActionBar(String message) {

    }

    @Override
    public boolean isOnline() {
        return false;
    }

    @Override
    public boolean hasPermission(String permission) {
        return false;
    }

    public AbstractEntity getEntity(){
        return plugin.getServer().getPlayer(name);
    }
}
