package dev.loststr1ng.letaleconomy.manager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import dev.loststr1ng.letaleconomy.config.PluginConfig;
import dev.loststr1ng.letaleconomy.config.serializer.EconomySerializer;
import dev.loststr1ng.letaleconomy.model.Economy;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurationOptions;
import org.spongepowered.configurate.yaml.NodeStyle;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

public class ConfigManager {

    private final Path path;
    private final YamlConfigurationLoader loader;

    private PluginConfig config;

    public ConfigManager(Path dataDirectory) {
        this.path = dataDirectory.resolve("config.yml");

        this.loader = YamlConfigurationLoader.builder()
                .path(path)
                .nodeStyle(NodeStyle.BLOCK)
                .defaultOptions(configurationOptions -> configurationOptions.serializers(builder -> builder.register(Economy.class, new EconomySerializer())))
                .build();
        load();

    }

    public void load() {

        try {

            if (Files.notExists(path)) {
                // create default config
                Files.createDirectories(path.getParent());

                PluginConfig defaults = new PluginConfig();

                CommentedConfigurationNode node = loader.createNode();
                node.set(PluginConfig.class, defaults);
                loader.save(node);
            }

            CommentedConfigurationNode node = loader.load();
            config = node.get(PluginConfig.class);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load config", e);
        }

    }

    public void save(PluginConfig config) {
        try {
            CommentedConfigurationNode node = loader.createNode();
            node.set(PluginConfig.class, config);
            loader.save(node);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save config", e);
        }
    }

    public void reload() {
        load();
    }

    public PluginConfig getConfig() {
        return config;
    }
}
