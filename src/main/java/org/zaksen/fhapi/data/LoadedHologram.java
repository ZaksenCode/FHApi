package org.zaksen.fhapi.data;

import org.bukkit.entity.TextDisplay;

public class LoadedHologram {

    private final int id;
    private final TextDisplay textDisplay;

    public LoadedHologram(int id, TextDisplay textDisplay)
    {
        this.id = id;
        this.textDisplay = textDisplay;
    }

    public int getId() {
        return id;
    }

    public TextDisplay getTextDisplay() {
        return textDisplay;
    }
}
