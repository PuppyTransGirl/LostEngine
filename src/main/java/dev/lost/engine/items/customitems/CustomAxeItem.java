package dev.lost.engine.items.customitems;

import lombok.Getter;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ToolMaterial;

public class CustomAxeItem extends AxeItem implements CustomItem {

    @Getter
    private final String id;

    public CustomAxeItem(ToolMaterial material, float attackDamage, float attackSpeed, Properties properties, String id) {
        super(material, attackDamage, attackSpeed, properties);
        this.id = id;
    }

    @Override
    public ItemStack getDynamicMaterial() {
        return Items.WOODEN_AXE.getDefaultInstance();
    }

    @Override
    public String toolType() {
        return "axe";
    }
}