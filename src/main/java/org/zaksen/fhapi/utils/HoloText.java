package org.zaksen.fhapi.utils;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Display;
import org.bukkit.entity.TextDisplay;
import org.bukkit.util.Transformation;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.zaksen.fhapi.holo.TextHologram;

public class HoloText {

    // Text
    public static void setText(TextHologram holo, String text) {
        holo.getDisplay().setText(text);
    }

    public static void setFormattedText(TextHologram holo, String text) {
        setText(holo, ChatUtils.formatText(text));
    }

    // Location
    public static void setLocation(TextHologram holo, Location location) {
        holo.getDisplay().teleport(location);
    }


    // Background color
    public static void setBackgroundColor(TextHologram holo, int red, int green, int blue) {
        setBackgroundColor(holo, Color.fromRGB(red, green, blue));
    }

    public static void setBackgroundColor(TextHologram holo, int red, int green, int blue, int alpha) {
        setBackgroundColor(holo, Color.fromARGB(alpha, red, green, blue));
    }

    public static void setBackgroundColor(TextHologram holo, Color color) {
        holo.getDisplay().setBackgroundColor(color);
    }

    // Glow color
    public static void setGlowColor(TextHologram holo, int red, int green, int blue) {
        setGlowColor(holo, Color.fromRGB(red, green, blue));
    }

    public static void setGlowColor(TextHologram holo, int red, int green, int blue, int alpha) {
        setGlowColor(holo, Color.fromARGB(alpha, red, green, blue));
    }

    public static void setGlowColor(TextHologram holo, Color color) {
        holo.getDisplay().setGlowColorOverride(color);
    }

    // Transformation
    public static void setTransformation(TextHologram holo, Transformation transformation) {
        holo.getDisplay().setTransformation(transformation);
    }

    // Scale
    public static void setScale(TextHologram holo, float scale) {
        setScale(holo, new Vector3f(scale, scale, scale));
    }

    public static void setScale(TextHologram holo, float scaleX, float scaleY, float scaleZ) {
        setScale(holo, new Vector3f(scaleX, scaleY, scaleZ));
    }

    public static void setScale(TextHologram holo, Vector3f scale) {
        Transformation oldTransformation = holo.getDisplay().getTransformation();
        setTransformation(holo, new Transformation(oldTransformation.getTranslation(),
                    oldTransformation.getLeftRotation(), scale, oldTransformation.getRightRotation()));
    }

    // Translation
    public static void setTranslation(TextHologram holo, float scale) {
        setTranslation(holo, new Vector3f(scale, scale, scale));
    }

    public static void setTranslation(TextHologram holo, float scaleX, float scaleY, float scaleZ) {
        setTranslation(holo, new Vector3f(scaleX, scaleY, scaleZ));
    }

    public static void setTranslation(TextHologram holo, Vector3f scale) {
        Transformation oldTransformation = holo.getDisplay().getTransformation();
        setTransformation(holo, new Transformation(scale,
                oldTransformation.getLeftRotation(), oldTransformation.getScale(), oldTransformation.getRightRotation()));
    }

    // Left rotation
    public static void setLRotation(TextHologram holo, float rotation) {
        setLRotation(holo, new Quaternionf(rotation, rotation, rotation, 1.0f));
    }

    public static void setLRotation(TextHologram holo, float rotationX, float rotationY, float rotationZ) {
        setLRotation(holo, new Quaternionf(rotationX, rotationY, rotationZ, 1.0f));
    }

    public static void setLRotation(TextHologram holo, float rotation, float w) {
        setLRotation(holo, new Quaternionf(rotation, rotation, rotation, w));
    }

    public static void setLRotation(TextHologram holo, float rotationX, float rotationY, float rotationZ, float w) {
        setLRotation(holo, new Quaternionf(rotationX, rotationY, rotationZ, w));
    }

    public static void setLRotation(TextHologram holo, Quaternionf rotation) {
        Transformation oldTransformation = holo.getDisplay().getTransformation();
        setTransformation(holo, new Transformation(oldTransformation.getTranslation(),
                rotation, oldTransformation.getScale(), oldTransformation.getRightRotation()));
    }

    // Right rotation
    public static void setRRotation(TextHologram holo, float rotation) {
        setRRotation(holo, new Quaternionf(rotation, rotation, rotation, 1.0f));
    }

    public static void setRRotation(TextHologram holo, float rotationX, float rotationY, float rotationZ) {
        setRRotation(holo, new Quaternionf(rotationX, rotationY, rotationZ, 1.0f));
    }

    public static void setRRotation(TextHologram holo, float rotation, float w) {
        setRRotation(holo, new Quaternionf(rotation, rotation, rotation, w));
    }

    public static void setRRotation(TextHologram holo, float rotationX, float rotationY, float rotationZ, float w) {
        setRRotation(holo, new Quaternionf(rotationX, rotationY, rotationZ, w));
    }

    public static void setRRotation(TextHologram holo, Quaternionf rotation) {
        Transformation oldTransformation = holo.getDisplay().getTransformation();
        setTransformation(holo, new Transformation(oldTransformation.getTranslation(),
                oldTransformation.getLeftRotation(), oldTransformation.getScale(), rotation));
    }

    // Alignment
    public static void setAlignment(TextHologram holo, TextDisplay.TextAlignment textAlignment) {
        holo.getDisplay().setAlignment(textAlignment);
    }

    // Billboard
    public static void setBillboard(TextHologram holo, Display.Billboard billboardType) {
        holo.getDisplay().setBillboard(billboardType);
    }

    // Line width
    public static void setLineWidth(TextHologram holo, int width) {
        holo.getDisplay().setLineWidth(width);
    }

    // See through
    public static void setSeeThrough(TextHologram holo, boolean see) {
        holo.getDisplay().setSeeThrough(see);
    }

    // Text opacity
    public static void setTextOpacity(TextHologram holo, Byte opacity) {
        holo.getDisplay().setTextOpacity(opacity);
    }

    // Shadow
    public static void setShadow(TextHologram holo, boolean shadow) {
        holo.getDisplay().setShadowed(shadow);
    }

    // View range
    public static void setViewRange(TextHologram holo, float range) {
        holo.getDisplay().setViewRange(range);
    }

    // Rotation
    public static void setRotation(TextHologram holo, float x, float y) {
        holo.getDisplay().setRotation(x, y);
    }
}