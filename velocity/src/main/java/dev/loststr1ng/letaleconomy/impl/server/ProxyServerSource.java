package dev.loststr1ng.letaleconomy.impl.server;

import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.scheduler.ScheduledTask;
import dev.loststr1ng.letaleconomy.LetalEconomyVelocity;
import dev.loststr1ng.letaleconomy.entity.AbstractEntity;
import dev.loststr1ng.letaleconomy.entity.AbstractServer;
import dev.loststr1ng.letaleconomy.impl.player.ProxyPlayer;
import net.kyori.adventure.text.minimessage.MiniMessage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProxyServerSource extends AbstractServer {

    private final ProxyServer proxyServer;
    private final LetalEconomyVelocity plugin;

    public ProxyServerSource(ProxyServer proxyServer, LetalEconomyVelocity plugin) {
        this.proxyServer = proxyServer;
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "Global";
    }

    @Override
    public int getOnlinePlayersCount() {
        return proxyServer.getPlayerCount();
    }

    @Override
    public List<AbstractEntity> getOnlinePlayers() {
        List<AbstractEntity> players = new ArrayList<>();
        proxyServer.getAllPlayers()
                .forEach(player -> players.add(new ProxyPlayer(player)));
        return players;
    }

    @Override
    public void sendMessage(String message) {
        proxyServer.getConsoleCommandSource().sendMessage(MiniMessage.miniMessage().deserialize(message));
    }

    @Override
    public void broadcastMessage(String message) {
        proxyServer.sendMessage(MiniMessage.miniMessage().deserialize(message));
    }

    @Override
    public void broadcastTitle(String title, String subtitle, int fadeIn, int stay, int fadeOut) {

    }

    @Override
    public void broadcastActionBar(String message) {

    }

    @Override
    public void broadcastSound(String sound, float volume, float pitch) {

    }

    @Override
    public AbstractEntity getPlayer(String name) {
        Optional<Player> player = proxyServer.getPlayer(name);
        return player.map(ProxyPlayer::new).orElse(null);
    }

    @Override
    public void runTask(Runnable runnable) {
        proxyServer.getScheduler().buildTask(plugin, runnable).schedule();
    }

    @Override
    public void runTaskAsync(Runnable runnable) {
        proxyServer.getScheduler().buildTask(plugin, runnable).schedule();
    }

    @Override
    public void runTaskTimer(Runnable runnable, long delay, long period) {
        proxyServer.getScheduler().buildTask(plugin,
                runnable)
                .repeat(Duration.ofSeconds(period))
                .delay(Duration.ofSeconds(delay))
                .schedule();
    }

    @Override
    public void runTaskTimerAsync(Runnable runnable, long delay, long period) {
        proxyServer.getScheduler().buildTask(plugin,
                        runnable)
                .repeat(Duration.ofSeconds(period))
                .delay(Duration.ofSeconds(delay))
                .schedule();
    }

    @Override
    public void stopTask(int taskId) {

    }

    @Override
    public void stopAllTasks() {
        proxyServer.getScheduler().tasksByPlugin(plugin)
                .forEach(ScheduledTask::cancel);

    }


}
