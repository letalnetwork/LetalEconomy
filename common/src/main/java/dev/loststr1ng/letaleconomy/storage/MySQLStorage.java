package dev.loststr1ng.letaleconomy.storage;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import dev.loststr1ng.letaleconomy.entity.AbstractEntity;
import dev.loststr1ng.letaleconomy.entity.OfflineEntity;
import dev.loststr1ng.letaleconomy.model.Economy;
import dev.loststr1ng.letaleconomy.model.PlayerData;
import dev.loststr1ng.letaleconomy.plugin.LetalEconomyPlugin;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MySQLStorage extends Storage {

    public Map<UUID, PlayerData> cachedPlayers;

    private HikariDataSource dataSource;
    private final LetalEconomyPlugin plugin;

    private final String host;
    private final int port;
    private final String database;
    private final String username;
    private final String password;

    public MySQLStorage(LetalEconomyPlugin plugin) {
        this.host = plugin.getConfigManager().getConfig().getDataBaseSection().getHost();
        this.port = plugin.getConfigManager().getConfig().getDataBaseSection().getPort();
        this.database = plugin.getConfigManager().getConfig().getDataBaseSection().getDatabase();
        this.username = plugin.getConfigManager().getConfig().getDataBaseSection().getUsername();
        this.password = plugin.getConfigManager().getConfig().getDataBaseSection().getPassword();
        this.plugin = plugin;
        this.cachedPlayers = new HashMap<>();
    }

    @Override
    public void setup() {


        HikariConfig config = getHikariConfig();

        dataSource = new HikariDataSource(config);

        try (Connection connection = dataSource.getConnection()) {
            String createTable = "CREATE TABLE IF NOT EXISTS letaleconomy_balances (" +
                    "uuid VARCHAR(36) NOT NULL, " +
                    "name VARCHAR(64)," +
                    "currency VARCHAR(64) NOT NULL, " +
                    "balance DOUBLE NOT NULL, " +
                    "PRIMARY KEY(uuid, currency)" +
                    ");";
            try (PreparedStatement statement = connection.prepareStatement(createTable)) {
                statement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private @NotNull HikariConfig getHikariConfig() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl(
                "jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=false&allowPublicKeyRetrieval=true");
        config.setUsername(username);
        config.setPassword(password);

        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        return config;
    }

    @Override
    public PlayerData loadOrCreate(AbstractEntity entity) {
        if(cachedPlayers.containsKey(entity.getUUID())){
            return cachedPlayers.get(entity.getUUID());
        }
        PlayerData data = new PlayerData(entity);
        String query = "SELECT currency, balance FROM letaleconomy_balances WHERE uuid = ?";

        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, entity.getUUID().toString());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String currency = resultSet.getString("currency");
                    double balance = resultSet.getDouble("balance");
                    data.setBalance(currency, balance);
                }
            }

            if(data.getBalances().isEmpty()){
                // has no data, new player
                for(Economy economy: plugin.getConfigManager().getConfig().getEconomies().getEconomiesList().values()){
                    data.setBalance(economy.getName(), economy.getStartingBalance());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    @Override
    public void save(PlayerData playerData) {
        if (playerData.getBalances().isEmpty())
            return;

        String query = "INSERT INTO letaleconomy_balances (uuid,name, currency, balance) VALUES (?,?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE balance = VALUES(balance)";

        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {

            connection.setAutoCommit(false);
            String uuid = playerData.getEntity().getUUID().toString();

            for (Map.Entry<String, Double> entry : playerData.getBalances().entrySet()) {
                statement.setString(1, uuid);
                statement.setString(2, playerData.getEntity().getName());
                statement.setString(3, entry.getKey());
                statement.setDouble(4, entry.getValue());
                statement.addBatch();
            }

            statement.executeBatch();
            connection.commit();
            connection.setAutoCommit(true);

            playerData.setUpdated(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void reload() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
        }
        setup();
    }

    @Override
    public void loadAll() {

        Map<UUID, PlayerData> data = new HashMap<>();
        String query = "SELECT * FROM letaleconomy_balances";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    UUID uuid = UUID.fromString(resultSet.getString("uuid"));
                    String name = resultSet.getString("name");

                    PlayerData playerData = data.computeIfAbsent(uuid, u -> {
                        OfflineEntity entity = new OfflineEntity(
                                name,
                                u
                        );
                        return new PlayerData(entity);
                    });

                    String currency = resultSet.getString("currency");
                    double balance = resultSet.getDouble("balance");

                    playerData.setBalance(currency, balance);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.cachedPlayers = data;
        }



    }


    public void saveUpdatedPlayers(){
        for(PlayerData playerData: cachedPlayers.values()){
            if(playerData.isUpdated()){
                save(playerData);
            }
        }
    }
}
