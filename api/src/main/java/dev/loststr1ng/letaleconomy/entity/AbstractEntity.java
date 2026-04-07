package dev.loststr1ng.letaleconomy.entity;

import java.util.UUID;

public abstract class AbstractEntity {

    public abstract String getName();

    public abstract UUID getUUID();

    public abstract void sendMessage(String message);

    public abstract void sendTitle(String title, String subtitle, int fadeIn, int stay, int fadeOut);

    public abstract void sendActionBar(String message);

    public abstract boolean isOnline();

    public abstract boolean hasPermission(String permission);

}