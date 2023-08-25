package org.zaksen.fhapi.database;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.TextDisplay;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zaksen.fhapi.FHApi;
import org.zaksen.fhapi.data.LoadedHologram;
import org.zaksen.fhapi.text.Hologram;

import java.sql.*;
import java.util.HashMap;
import java.util.UUID;

public class DatabaseManager {
    public static final DatabaseManager Instance = new DatabaseManager();
    private final Logger logger;
    private Connection connection;
    private Statement statement;

    private DatabaseManager() {
        logger = LoggerFactory.getLogger(FHApi.class);
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:holograms.sqlite");
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS ids(id INTEGER, uuid TEXT);");
        } catch (SQLException e) {
            logger.error("A database connection could not be established", e);
        }
    }

    public void addHologram(@NotNull Hologram holo) {
        try {
            statement.execute(String.format("INSERT INTO ids(id, uuid) VALUES('%s', '%s');", holo.getId(), holo.getUUID().toString()));
        } catch (SQLException e) {
            logger.error("Failed to add hologram to database", e);
        }
    }

    public void removeHologram(@NotNull Hologram holo) {
        try {
            statement.executeUpdate(String.format("DELETE FROM ids WHERE id = %s", holo.getId()));
        } catch (SQLException e) {
            logger.error("Failed to delete hologram to database", e);
        }
    }

    public HashMap<Integer, LoadedHologram> getHolograms() {
        HashMap<Integer, LoadedHologram> result = new HashMap<>();
        ResultSet messageSet;
        try {
            messageSet = statement.executeQuery("SELECT * FROM ids;");
            while (messageSet.next()) {
                int id = messageSet.getInt("id");
                String uuid = messageSet.getString("uuid");
                Entity loadedEntity = Bukkit.getEntity(UUID.fromString(uuid));
                if (loadedEntity instanceof TextDisplay) {
                    result.put(id, new LoadedHologram(id, (TextDisplay) loadedEntity));
                }
            }
        } catch (SQLException e) {
            logger.error("Failed to retrieve holograms from database", e);
        }
        return result;
    }
}
