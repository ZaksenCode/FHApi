package org.zaksen.fhapi.database;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zaksen.fhapi.FHApi;
import org.zaksen.fhapi.data.ILoadedDisplay;
import org.zaksen.fhapi.holo.IHologram;
import org.zaksen.fhapi.holo.TextHologram;

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

    public void addHologram(@NotNull IHologram holo) {
        try {
            PreparedStatement newSt = connection.prepareStatement("INSERT INTO ids(id, uuid) VALUES(?, ?);");
            newSt.setInt(1, holo.getId());
            newSt.setString(2, holo.getUUID().toString());
            newSt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Failed to add hologram to database", e);
        }
    }

    public void removeHologram(@NotNull IHologram holo) {
        try(PreparedStatement preSt = connection.prepareStatement("DELETE FROM ids WHERE id = ?;")) {
            preSt.setInt(1, holo.getId());
            preSt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Failed to delete hologram from database", e);
        }
    }

    public HashMap<Integer, ILoadedDisplay> getHolograms() {
        HashMap<Integer, ILoadedDisplay> result = new HashMap<>();
        ResultSet messageSet;
        try {
            messageSet = statement.executeQuery("SELECT * FROM ids;");
            while (messageSet.next()) {
                int id = messageSet.getInt("id");
                String uuid = messageSet.getString("uuid");
                Entity loadedEntity = Bukkit.getEntity(UUID.fromString(uuid));
                result.put(id, new ILoadedDisplay(id, loadedEntity));
            }
        } catch (SQLException e) {
            logger.error("Failed to retrieve holograms from database", e);
        }
        return result;
    }
}
