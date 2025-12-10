package dev.lost.engine.items.customitems;

import lombok.Getter;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class CustomBlockItem extends BlockItem implements CustomItem {

    @Getter
    private final String id;

    @Getter
    private final ItemStack dynamicMaterial;

    public CustomBlockItem(Block block, Properties properties, ItemStack dynamicMaterial, String id) {
        super(block, properties);
        this.dynamicMaterial = dynamicMaterial;
        this.id = id;
    }
}
