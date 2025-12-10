package dev.lost.engine.items.customitems;

import lombok.Getter;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ToolMaterial;

public class CustomHoeItem extends HoeItem implements CustomItem {

    @Getter
    private final String id;

    public CustomHoeItem(ToolMaterial material, float attackDamage, float attackSpeed, Properties properties, String id) {
        super(material, attackDamage, attackSpeed, properties);
        this.id = id;
    }

    @Override
    public ItemStack getDynamicMaterial() {
        return Items.WOODEN_HOE.getDefaultInstance();
    }

    @Override
    public String toolType() {
        return "hoe";
    }
}
