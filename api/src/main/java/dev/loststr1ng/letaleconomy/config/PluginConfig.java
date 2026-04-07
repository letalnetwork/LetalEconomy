package dev.loststr1ng.letaleconomy.config;

import dev.loststr1ng.letaleconomy.config.sections.DataBaseSection;
import dev.loststr1ng.letaleconomy.config.sections.Economies;
import dev.loststr1ng.letaleconomy.config.sections.MessagesSection;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

@ConfigSerializable
public class PluginConfig {

    @Setting("database")
    public DataBaseSection dataBaseSection = new DataBaseSection();

    @Setting("economies")
    public Economies economies = new Economies();

    @Setting("messages")
    public MessagesSection messagesSection = new MessagesSection();

    public DataBaseSection getDataBaseSection() {
        return dataBaseSection;
    }

    public Economies getEconomies() {
        return economies;
    }

    public MessagesSection getMessagesSection() {
        return messagesSection;
    }
}
