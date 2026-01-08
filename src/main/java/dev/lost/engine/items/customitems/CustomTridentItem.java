package dev.lost.engine.items.customitems;

import lombok.Getter;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TridentItem;

public class CustomTridentItem extends TridentItem implements CustomItem {

    @Getter
    private final String id;

    public CustomTridentItem(Properties properties, String id) {
        super(properties);
        this.id = id;
    }

    @Override
    public ItemStack getDynamicMaterial() {
        return Items.TRIDENT.getDefaultInstance();
    }
}
