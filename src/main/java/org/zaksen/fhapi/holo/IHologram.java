package org.zaksen.fhapi.holo;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.zaksen.fhapi.data.LoadedDisplay;

import java.util.UUID;

public abstract class IHologram implements Hologram
{
    private final int id;
    private UUID entityUuid;
    protected Entity displayEntity;

    public IHologram(int id, Location location, EntityType type) {
        this.id = id;
        spawn(location, type);
    }

    public IHologram(LoadedDisplay loadedDisplay) {
        this.id = loadedDisplay.getId();
        displayEntity = loadedDisplay.getEntity();
        entityUuid = displayEntity.getUniqueId();
    }

    @Override
    public void spawn(Location location, EntityType type) {
        displayEntity = location.getWorld().spawnEntity(location, type);
        entityUuid = displayEntity.getUniqueId();
        HologramManager.addHolo(this);
    }

    @Override
    public void destroy() {
        displayEntity.remove();
    }

    @Override
    public Entity getDisplay() {
        return displayEntity;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public UUID getUUID() {
        return entityUuid;
    }


}