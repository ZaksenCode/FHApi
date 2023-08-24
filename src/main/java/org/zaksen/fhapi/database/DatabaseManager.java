package org.zaksen.fhapi.database;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.TextDisplay;
import org.zaksen.fhapi.data.LoadedHologram;
import org.zaksen.fhapi.text.Hologram;

import java.sql.*;
import java.util.HashMap;
import java.util.UUID;

public class DatabaseManager {
    public static final DatabaseManager Instance = new DatabaseManager();

    private final Connection connection;
    private final Statement statement;

    private DatabaseManager() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:holograms.sqlite");
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS ids(id INTEGER, uuid TEXT);");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addHologram(Hologram holo) {
        try {
            statement.execute(String.format("INSERT INTO ids(id, uuid) VALUES('%s', '%s');", holo.getId(), holo.getUUID().toString()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeHologram(Hologram holo) {
        try {
            statement.executeUpdate(String.format("DELETE FROM ids WHERE id = %s", holo.getId()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public HashMap<Integer, LoadedHologram> getHolograms()
    {
        HashMap<Integer, LoadedHologram> result = new HashMap<>();
        ResultSet messageSet;
        try {
            messageSet = statement.executeQuery("SELECT * FROM ids;");
            while(messageSet.next()) {
                int id = messageSet.getInt("id");
                String uuid = messageSet.getString("uuid");
                Entity loadedEntity = Bukkit.getEntity(UUID.fromString(uuid));
                if(loadedEntity instanceof TextDisplay) {
                    result.put(id, new LoadedHologram(id, (TextDisplay) loadedEntity));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
