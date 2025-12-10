package dev.lost.engine.items.customitems;

import lombok.Getter;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.ToolMaterial;

public class CustomShovelItem extends ShovelItem implements CustomItem {

    @Getter
    private final String id;

    public CustomShovelItem(ToolMaterial material, float attackDamage, float attackSpeed, Properties properties, String id) {
        super(material, attackDamage, attackSpeed, properties);
        this.id = id;
    }

    @Override
    public ItemStack getDynamicMaterial() {
        return Items.WOODEN_SHOVEL.getDefaultInstance();
    }

    @Override
    public String toolType() {
        return "shovel";
    }
}