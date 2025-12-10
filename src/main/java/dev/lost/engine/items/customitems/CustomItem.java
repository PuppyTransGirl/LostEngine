package dev.lost.engine.items.customitems;

import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public interface CustomItem {

    ItemStack getDynamicMaterial();

    String getId();

    default @Nullable String toolType() {
        return null;
    }
}
