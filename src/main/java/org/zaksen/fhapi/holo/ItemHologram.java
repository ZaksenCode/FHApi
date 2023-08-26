package org.zaksen.fhapi.holo;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.ItemStack;
import org.zaksen.fhapi.data.LoadedDisplay;

public class ItemHologram extends IHologram {
    public ItemHologram(int id, Location location) {
        super(id, location, EntityType.ITEM_DISPLAY);
    }

    public ItemHologram(LoadedDisplay loadedDisplay) {
        super(loadedDisplay);
    }

    @Override
    public void spawn(Location location, EntityType type) {
        super.spawn(location, type);
        getDisplay().setItemStack(new ItemStack(Material.BARRIER));
    }

    @Override
    public ItemDisplay getDisplay() {
        return (ItemDisplay) super.getDisplay();
    }
}