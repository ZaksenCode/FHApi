package org.zaksen.fhapi.holo;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemDisplay;
import org.zaksen.fhapi.data.LoadedDisplay;

public class BlockHologram extends IHologram {
    public BlockHologram(int id, Location location) {
        super(id, location, EntityType.BLOCK_DISPLAY);
    }

    public BlockHologram(LoadedDisplay loadedDisplay) {
        super(loadedDisplay);
    }

    @Override
    public void spawn(Location location, EntityType type) {
        super.spawn(location, type);
        getDisplay().setBlock(Material.BEDROCK.createBlockData());
    }

    @Override
    public BlockDisplay getDisplay() {
        return (BlockDisplay) super.getDisplay();
    }
}