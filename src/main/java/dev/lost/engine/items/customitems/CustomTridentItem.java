package dev.lost.engine.items.customitems;

import lombok.Getter;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.component.Consumable;

public class CustomTridentItem extends TridentItem implements CustomItem {

    private static final ItemStack DYNAMIC_ITEMSTACK = Items.RECOVERY_COMPASS.getDefaultInstance();

    static {
        DYNAMIC_ITEMSTACK.set(
                DataComponents.CONSUMABLE,
                Consumable.builder()
                        .consumeSeconds(Float.MAX_VALUE)
                        .animation(ItemUseAnimation.TRIDENT)
                        .hasConsumeParticles(false)
                        .build()
        );
    }

    @Getter
    private final String id;

    public CustomTridentItem(Properties properties, String id) {
        super(properties);
        this.id = id;
    }

    @Override
    public ItemStack getDynamicMaterial() {
        return DYNAMIC_ITEMSTACK.copy();
    }
}
