package org.zaksen.fhapi.holo;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TextDisplay;
import org.zaksen.fhapi.data.LoadedDisplay;

public class TextHologram extends IHologram {
    public TextHologram(int id, Location location) {
        super(id, location, EntityType.TEXT_DISPLAY);
    }

    public TextHologram(LoadedDisplay loadedDisplay) {
        super(loadedDisplay);
    }

    @Override
    public void spawn(Location location, EntityType type) {
        super.spawn(location, type);
        getDisplay().setText("new text");
    }

    @Override
    public TextDisplay getDisplay() {
        return (TextDisplay) super.getDisplay();
    }
}