package dev.loststr1ng.letaleconomy.config.sections;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;

@ConfigSerializable
public class DataBaseSection {

    public String host = "localhost";
    public int port = 3306;
    public String database = "letaleconomy";
    public String username = "londraperu";
    public String password = "londra1234";

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getDatabase() {
        return database;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
