package dev.loststr1ng.letaleconomy.config.serializer;

import dev.loststr1ng.letaleconomy.model.Economy;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;

import java.lang.reflect.Type;
import java.util.Arrays;

public class EconomySerializer implements TypeSerializer<Economy> {

    private static final String NAME = "name";
    private static final String MAX_BALANCE = "maxBalance";
    private static final String MIN_BALANCE = "minBalance";
    private static final String STARTING_BALANCE = "startingBalance";
    private static final String SERVER = "server";

    private ConfigurationNode nonVirtualNode(final ConfigurationNode source, final Object... path) throws SerializationException {
        if (!source.hasChild(path)) {
            throw new SerializationException("Required field " + Arrays.toString(path) + " was not present in node");
        }
        return source.node(path);
    }

    @Override
    public Economy deserialize(@NotNull Type type, @NotNull ConfigurationNode node) throws SerializationException {
        final ConfigurationNode name = nonVirtualNode(node, NAME);
        final ConfigurationNode maxBal = nonVirtualNode(node, MAX_BALANCE);
        final ConfigurationNode minBal = nonVirtualNode(node, MIN_BALANCE);
        final ConfigurationNode startingBal = nonVirtualNode(node, STARTING_BALANCE);
        final ConfigurationNode server = nonVirtualNode(node, SERVER);


        return new Economy(name.getString(), maxBal.getDouble(), minBal.getDouble(), startingBal.getDouble(), server.getString());
    }

    @Override
    public void serialize(@NotNull Type type, @Nullable Economy obj, @NotNull ConfigurationNode node) throws SerializationException {
        if(obj == null){
            node.raw(null);
            return;
        }

        node.node(NAME).set(obj.getName());
        node.node(MAX_BALANCE).set(obj.getMaxBalance());
        node.node(MIN_BALANCE).set(obj.getMinBalance());
        node.node(STARTING_BALANCE).set(obj.getStartingBalance());
        node.node(SERVER).set(obj.getServer());
    }
}
