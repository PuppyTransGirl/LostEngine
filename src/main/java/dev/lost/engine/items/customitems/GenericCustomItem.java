package dev.lost.engine.items.customitems;

import lombok.Getter;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class GenericCustomItem extends Item implements CustomItem {

    @Getter
    private final String id;
    private final String toolType;

    public GenericCustomItem(Properties properties, String id, String toolType) {
        super(properties);
        this.toolType = toolType;
        this.id = id;
    }

    public GenericCustomItem(Properties properties, String id) {
        this(properties, id, null);
    }

    @Override
    public ItemStack getDynamicMaterial() {
        return Items.RECOVERY_COMPASS.getDefaultInstance();
    }

    @Override
    public String toolType() {
        return toolType;
    }
}
