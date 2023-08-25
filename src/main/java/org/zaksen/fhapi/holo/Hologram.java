package org.zaksen.fhapi.holo;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.UUID;

public interface Hologram
{
    void spawn(Location location, EntityType type);
    void destroy();
    Entity getDisplay();
    int getId();
    UUID getUUID();
}