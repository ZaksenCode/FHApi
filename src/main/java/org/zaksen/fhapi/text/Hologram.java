package org.zaksen.fhapi.text;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.TextDisplay;
import org.zaksen.fhapi.data.LoadedHologram;

import java.util.UUID;

public class Hologram
{
    private final int id;
    private Location location;
    private String text;
    private UUID textWorldId;
    private TextDisplay textDisplay;

    public Hologram(int id, Location location, String text)
    {
        this.id = id;
        this.location = location;
        this.text = text;
        spawn(location, text);
    }

    public Hologram(LoadedHologram loadedHolo)
    {
        this.id = loadedHolo.getId();
        this.textDisplay = loadedHolo.getTextDisplay();
        this.text = textDisplay.getText();
        this.location = textDisplay.getLocation();
    }

    public void spawn(Location location, String text) {
        textDisplay = location.getWorld().spawn(location, TextDisplay.class);
        textWorldId = textDisplay.getUniqueId();
        textDisplay.setText(ChatColor.translateAlternateColorCodes('&', text));
        HologramManager.addHolo(this);
    }

    public void destroy() {
        textDisplay.remove();
    }

    public void setLocation(Location location) {
        this.location = location;
        textDisplay.teleport(location);
    }

    public void setText(String text) {
        this.text = text;
        textDisplay.setText(text);
    }

    public Location getLocation() {
        return location;
    }

    public String getText() {
        return text;
    }

    public int getId() {
        return id;
    }

    public TextDisplay getDisplay() {
        return textDisplay;
    }

    public UUID getUUID() {
        return textWorldId;
    }

    @Override
    public int hashCode() {
        return id;
    }
}