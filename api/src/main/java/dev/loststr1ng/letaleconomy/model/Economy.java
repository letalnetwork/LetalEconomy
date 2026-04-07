package dev.loststr1ng.letaleconomy.model;

public class Economy {

    private final String name;
    private final double maxBalance;
    private final double minBalance;
    private final double startingBalance;
    private String server;

    public Economy(String name, double maxBalance, double minBalance, double startingBalance, String server) {
        this.name = name;
        this.maxBalance = maxBalance;
        this.minBalance = minBalance;
        this.startingBalance = startingBalance;
        this.server = server;
    }

    public String getName() {
        return name;
    }

    public double getMaxBalance() {
        return maxBalance;
    }

    public double getMinBalance() {
        return minBalance;
    }

    public double getStartingBalance() {
        return startingBalance;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }
}
