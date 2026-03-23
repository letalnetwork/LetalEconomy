package dev.loststr1ng.letaleconomy.entity;

import java.util.List;

public abstract class AbstractServer {

    public abstract String getName();

    public abstract int getOnlinePlayersCount();

    public abstract List<AbstractEntity> getOnlinePlayers();

    public abstract void sendMessage(String message);

    public abstract void broadcastMessage(String message);

    public abstract void broadcastTitle(String title, String subtitle, int fadeIn, int stay, int fadeOut);

    public abstract void broadcastActionBar(String message);

    public abstract void broadcastSound(String sound, float volume, float pitch);

    public abstract AbstractEntity getPlayer(String name);

    public abstract void runTask(Runnable runnable);

    public abstract void runTaskAsync(Runnable runnable);

    public abstract void runTaskTimer(Runnable runnable, long delay, long period);

    public abstract void runTaskTimerAsync(Runnable runnable, long delay, long period);

    public abstract void stopTask(int taskId);

    public abstract void stopAllTasks();

}
