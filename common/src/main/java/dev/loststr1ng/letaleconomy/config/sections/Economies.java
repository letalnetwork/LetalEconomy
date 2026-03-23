package dev.loststr1ng.letaleconomy.config.sections;

import dev.loststr1ng.letaleconomy.model.Economy;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

import java.util.List;
import java.util.Map;

@ConfigSerializable
public class Economies {

    public transient Economy defaultEco = new Economy("coins", -1, 0, 0, "global");

    public Map<String, Economy> economyMap = Map.of(defaultEco.getName(), defaultEco);

    public Map<String, Economy> getEconomiesList() {
        return economyMap;
    }
}
