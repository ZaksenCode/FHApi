package org.zaksen.fhapi.data;

import org.bukkit.entity.Entity;

public class ILoadedDisplay implements LoadedDisplay {
    private final int id;
    private final Entity displayEntity;

    public ILoadedDisplay(int id, Entity displayEntity) {
        this.id = id;
        this.displayEntity = displayEntity;
    }

    public int getId() {
        return id;
    }

    public Entity getEntity() {
        return displayEntity;
    }
}