package org.zaksen.fhapi.utils;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Display;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Transformation;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.zaksen.fhapi.holo.ItemHologram;

public class HoloItem {

    // Item
    public static void setItem(ItemHologram holo, ItemStack stack) {
        holo.getDisplay().setItemStack(stack);
    }

    // Location
    public static void setLocation(ItemHologram holo, Location location) {
        holo.getDisplay().teleport(location);
    }

    // Glow color
    public static void setGlowColor(ItemHologram holo, int red, int green, int blue) {
        setGlowColor(holo, Color.fromRGB(red, green, blue));
    }

    public static void setGlowColor(ItemHologram holo, int red, int green, int blue, int alpha) {
        setGlowColor(holo, Color.fromARGB(alpha, red, green, blue));
    }

    public static void setGlowColor(ItemHologram holo, Color color) {
        holo.getDisplay().setGlowColorOverride(color);
    }

    // Transformation
    public static void setTransformation(ItemHologram holo, Transformation transformation) {
        holo.getDisplay().setTransformation(transformation);
    }

    // Scale
    public static void setScale(ItemHologram holo, float scale) {
        setScale(holo, new Vector3f(scale, scale, scale));
    }

    public static void setScale(ItemHologram holo, float scaleX, float scaleY, float scaleZ) {
        setScale(holo, new Vector3f(scaleX, scaleY, scaleZ));
    }

    public static void setScale(ItemHologram holo, Vector3f scale) {
        Transformation oldTransformation = holo.getDisplay().getTransformation();
        setTransformation(holo, new Transformation(oldTransformation.getTranslation(),
                oldTransformation.getLeftRotation(), scale, oldTransformation.getRightRotation()));
    }

    // Translation
    public static void setTranslation(ItemHologram holo, float scale) {
        setTranslation(holo, new Vector3f(scale, scale, scale));
    }

    public static void setTranslation(ItemHologram holo, float scaleX, float scaleY, float scaleZ) {
        setTranslation(holo, new Vector3f(scaleX, scaleY, scaleZ));
    }

    public static void setTranslation(ItemHologram holo, Vector3f scale) {
        Transformation oldTransformation = holo.getDisplay().getTransformation();
        setTransformation(holo, new Transformation(scale,
                oldTransformation.getLeftRotation(), oldTransformation.getScale(), oldTransformation.getRightRotation()));
    }

    // Left rotation
    public static void setLRotation(ItemHologram holo, float rotation) {
        setLRotation(holo, new Quaternionf(rotation, rotation, rotation, 1.0f));
    }

    public static void setLRotation(ItemHologram holo, float rotationX, float rotationY, float rotationZ) {
        setLRotation(holo, new Quaternionf(rotationX, rotationY, rotationZ, 1.0f));
    }

    public static void setLRotation(ItemHologram holo, float rotation, float w) {
        setLRotation(holo, new Quaternionf(rotation, rotation, rotation, w));
    }

    public static void setLRotation(ItemHologram holo, float rotationX, float rotationY, float rotationZ, float w) {
        setLRotation(holo, new Quaternionf(rotationX, rotationY, rotationZ, w));
    }

    public static void setLRotation(ItemHologram holo, Quaternionf rotation) {
        Transformation oldTransformation = holo.getDisplay().getTransformation();
        setTransformation(holo, new Transformation(oldTransformation.getTranslation(),
                rotation, oldTransformation.getScale(), oldTransformation.getRightRotation()));
    }

    // Right rotation
    public static void setRRotation(ItemHologram holo, float rotation) {
        setRRotation(holo, new Quaternionf(rotation, rotation, rotation, 1.0f));
    }

    public static void setRRotation(ItemHologram holo, float rotationX, float rotationY, float rotationZ) {
        setRRotation(holo, new Quaternionf(rotationX, rotationY, rotationZ, 1.0f));
    }

    public static void setRRotation(ItemHologram holo, float rotation, float w) {
        setRRotation(holo, new Quaternionf(rotation, rotation, rotation, w));
    }

    public static void setRRotation(ItemHologram holo, float rotationX, float rotationY, float rotationZ, float w) {
        setRRotation(holo, new Quaternionf(rotationX, rotationY, rotationZ, w));
    }

    public static void setRRotation(ItemHologram holo, Quaternionf rotation) {
        Transformation oldTransformation = holo.getDisplay().getTransformation();
        setTransformation(holo, new Transformation(oldTransformation.getTranslation(),
                oldTransformation.getLeftRotation(), oldTransformation.getScale(), rotation));
    }

    // Billboard
    public static void setBillboard(ItemHologram holo, Display.Billboard billboardType) {
        holo.getDisplay().setBillboard(billboardType);
    }

    // Shadow radius
    public static void setShadowRadius(ItemHologram holo, float radius) {
        holo.getDisplay().setShadowRadius(radius);
    }

    // View range
    public static void setViewRange(ItemHologram holo, float range) {
        holo.getDisplay().setViewRange(range);
    }

    // Brightness
    public static void setBrightness(ItemHologram holo, Display.Brightness brightness) {
        holo.getDisplay().setBrightness(brightness);
    }

    // Rotation
    public static void setRotation(ItemHologram holo, float x, float y) {
        holo.getDisplay().setRotation(x, y);
    }
}