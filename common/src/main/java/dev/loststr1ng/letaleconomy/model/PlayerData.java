package dev.loststr1ng.letaleconomy.model;

import java.util.HashMap;
import java.util.Map;
import dev.loststr1ng.letaleconomy.entity.AbstractEntity;

public class PlayerData {

    private final AbstractEntity entity;
    private final Map<String, Double> balances;

    public PlayerData(AbstractEntity entity) {
        this.entity = entity;
        this.balances = new HashMap<>();
    }

    public AbstractEntity getEntity() {
        return entity;
    }

    public Map<String, Double> getBalances() {
        return balances;
    }

    public void setBalance(String currency, double balance) {
        balances.put(currency, balance);
    }

    public double getBalance(String currency) {
        return balances.getOrDefault(currency, 0.0);
    }

}
