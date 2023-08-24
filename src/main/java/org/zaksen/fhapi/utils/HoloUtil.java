package org.zaksen.fhapi.utils;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Display;
import org.bukkit.entity.TextDisplay;
import org.bukkit.util.Transformation;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.zaksen.fhapi.text.Hologram;
import org.zaksen.fhapi.text.HologramManager;

public class HoloUtil {

    // Holo
    public static Hologram getHolo(int id) {
        return HologramManager.getById(id);
    }

    // Spawn
    public static Hologram summonHoloWithId(Location location, String text, int id) {
        if(!HologramManager.hasId(id)) {
            return new Hologram(id, location, text);
        }
        return null;
    }

    public static Hologram summonHolo(Location location, String text) {
        return summonHoloWithId(location, text, HologramManager.getLastAvailableId());
    }

    // Remove
    public static void removeHolo(int id) {
        HologramManager.removeHolo(id);
    }

    public static void removeHolo(Hologram holo) {
        removeHolo(holo.getId());
    }

    // Text
    public static String formatText(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static void setText(Hologram holo, String text) {
        holo.setText(text);
    }

    public static void setFormattedText(Hologram holo, String text) {
        setText(holo, formatText(text));
    }

    // Location
    public static void setLocation(Hologram holo, Location location) {
        holo.setLocation(location);
    }


    // Background color
    public static void setBackgroundColor(Hologram holo, int red, int green, int blue) {
        setBackgroundColor(holo, Color.fromRGB(red, green, blue));
    }

    public static void setBackgroundColor(Hologram holo, int red, int green, int blue, int alpha) {
        setBackgroundColor(holo, Color.fromARGB(alpha, red, green, blue));
    }

    public static void setBackgroundColor(Hologram holo, Color color) {
        holo.getDisplay().setBackgroundColor(color);
    }

    // Glow color
    public static void setGlowColor(Hologram holo, int red, int green, int blue) {
        setGlowColor(holo, Color.fromRGB(red, green, blue));
    }

    public static void setGlowColor(Hologram holo, int red, int green, int blue, int alpha) {
        setGlowColor(holo, Color.fromARGB(alpha, red, green, blue));
    }

    public static void setGlowColor(Hologram holo, Color color) {
        holo.getDisplay().setGlowColorOverride(color);
    }

    // Transformation
    public static void setTransformation(Hologram holo, Transformation transformation) {
        holo.getDisplay().setTransformation(transformation);
    }

    // Scale
    public static void setScale(Hologram holo, float scale) {
        setScale(holo, new Vector3f(scale, scale, scale));
    }

    public static void setScale(Hologram holo, float scaleX, float scaleY, float scaleZ) {
        setScale(holo, new Vector3f(scaleX, scaleY, scaleZ));
    }

    public static void setScale(Hologram holo, Vector3f scale) {
        Transformation oldTransformation = holo.getDisplay().getTransformation();
        setTransformation(holo, new Transformation(oldTransformation.getTranslation(),
                    oldTransformation.getLeftRotation(), scale, oldTransformation.getRightRotation()));
    }

    // Translation
    public static void setTranslation(Hologram holo, float scale) {
        setTranslation(holo, new Vector3f(scale, scale, scale));
    }

    public static void setTranslation(Hologram holo, float scaleX, float scaleY, float scaleZ) {
        setTranslation(holo, new Vector3f(scaleX, scaleY, scaleZ));
    }

    public static void setTranslation(Hologram holo, Vector3f scale) {
        Transformation oldTransformation = holo.getDisplay().getTransformation();
        setTransformation(holo, new Transformation(scale,
                oldTransformation.getLeftRotation(), oldTransformation.getScale(), oldTransformation.getRightRotation()));
    }

    // Left rotation
    public static void setLRotation(Hologram holo, float rotation) {
        setLRotation(holo, new Quaternionf(rotation, rotation, rotation, 1.0f));
    }

    public static void setLRotation(Hologram holo, float rotationX, float rotationY, float rotationZ) {
        setLRotation(holo, new Quaternionf(rotationX, rotationY, rotationZ, 1.0f));
    }

    public static void setLRotation(Hologram holo, float rotation, float w) {
        setLRotation(holo, new Quaternionf(rotation, rotation, rotation, w));
    }

    public static void setLRotation(Hologram holo, float rotationX, float rotationY, float rotationZ, float w) {
        setLRotation(holo, new Quaternionf(rotationX, rotationY, rotationZ, w));
    }

    public static void setLRotation(Hologram holo, Quaternionf rotation) {
        Transformation oldTransformation = holo.getDisplay().getTransformation();
        setTransformation(holo, new Transformation(oldTransformation.getTranslation(),
                rotation, oldTransformation.getScale(), oldTransformation.getRightRotation()));
    }

    // Right rotation
    public static void setRRotation(Hologram holo, float rotation) {
        setRRotation(holo, new Quaternionf(rotation, rotation, rotation, 1.0f));
    }

    public static void setRRotation(Hologram holo, float rotationX, float rotationY, float rotationZ) {
        setRRotation(holo, new Quaternionf(rotationX, rotationY, rotationZ, 1.0f));
    }

    public static void setRRotation(Hologram holo, float rotation, float w) {
        setRRotation(holo, new Quaternionf(rotation, rotation, rotation, w));
    }

    public static void setRRotation(Hologram holo, float rotationX, float rotationY, float rotationZ, float w) {
        setRRotation(holo, new Quaternionf(rotationX, rotationY, rotationZ, w));
    }

    public static void setRRotation(Hologram holo, Quaternionf rotation) {
        Transformation oldTransformation = holo.getDisplay().getTransformation();
        setTransformation(holo, new Transformation(oldTransformation.getTranslation(),
                oldTransformation.getLeftRotation(), oldTransformation.getScale(), rotation));
    }

    // Alignment
    public static void setAlignment(Hologram holo, TextDisplay.TextAlignment textAlignment) {
        holo.getDisplay().setAlignment(textAlignment);
    }

    // Billboard
    public static void setBillboard(Hologram holo, Display.Billboard billboardType) {
        holo.getDisplay().setBillboard(billboardType);
    }

    // Line width
    public static void setLineWidth(Hologram holo, int width) {
        holo.getDisplay().setLineWidth(width);
    }

    // See through
    public static void setSeeThrough(Hologram holo, boolean see) {
        holo.getDisplay().setSeeThrough(see);
    }

    // Text opacity
    public static void setTextOpacity(Hologram holo, Byte opacity) {
        holo.getDisplay().setTextOpacity(opacity);
    }

    // Shadow
    public static void setShadow(Hologram holo, boolean shadow) {
        holo.getDisplay().setShadowed(shadow);
    }

    // View range
    public static void setViewRange(Hologram holo, float range) {
        holo.getDisplay().setViewRange(range);
    }

    // Rotation
    public static void setRotation(Hologram holo, float x, float y) {
        holo.getDisplay().setRotation(x, y);
    }
}