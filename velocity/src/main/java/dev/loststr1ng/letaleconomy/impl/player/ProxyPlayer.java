package dev.loststr1ng.letaleconomy.impl.player;

import com.velocitypowered.api.proxy.Player;
import dev.loststr1ng.letaleconomy.entity.AbstractEntity;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.title.Title;
import net.kyori.adventure.title.TitlePart;

import java.time.Duration;
import java.util.UUID;

public class ProxyPlayer extends AbstractEntity {

    private final Player player;

    public ProxyPlayer(Player player) {
        this.player = player;
    }

    @Override
    public String getName() {
        return player.getUsername();
    }

    @Override
    public UUID getUUID() {
        return player.getUniqueId();
    }

    @Override
    public void sendMessage(String message) {
        player.sendMessage(MiniMessage.miniMessage().deserialize(message));
    }

    @Override
    public void sendTitle(String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        Title.Times times = Title.Times.times(Duration.ofSeconds(fadeIn), Duration.ofSeconds(stay), Duration.ofSeconds(fadeOut));
        Title titled = Title.title(MiniMessage.miniMessage().deserialize(title), MiniMessage.miniMessage().deserialize(subtitle), times);
        player.showTitle(titled);
    }

    @Override
    public void sendActionBar(String message) {
        player.sendActionBar(MiniMessage.miniMessage().deserialize(message));
    }

    @Override
    public boolean isOnline() {
        return player.isActive();
    }

    @Override
    public boolean hasPermission(String permission) {
        return player.hasPermission(permission);
    }
}
