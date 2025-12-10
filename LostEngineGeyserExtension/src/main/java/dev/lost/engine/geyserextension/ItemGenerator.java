package dev.lost.engine.geyserextension;

import dev.lost.engine.geyserextension.lomapping.Mapping;
import org.geysermc.geyser.api.event.lifecycle.GeyserDefineCustomItemsEvent;
import org.geysermc.geyser.api.item.custom.v2.CustomItemBedrockOptions;
import org.geysermc.geyser.api.item.custom.v2.NonVanillaCustomItemDefinition;
import org.geysermc.geyser.api.item.custom.v2.component.java.Consumable;
import org.geysermc.geyser.api.item.custom.v2.component.java.FoodProperties;
import org.geysermc.geyser.api.item.custom.v2.component.java.ItemDataComponents;
import org.geysermc.geyser.api.item.custom.v2.component.java.ToolProperties;
import org.geysermc.geyser.api.util.CreativeCategory;
import org.geysermc.geyser.api.util.Holders;
import org.geysermc.geyser.api.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class ItemGenerator {

    public static void generateFromJson(@NotNull Mapping mapping, GeyserDefineCustomItemsEvent event) {
        mapping.items().forEach(item -> {
            NonVanillaCustomItemDefinition.Builder builder = NonVanillaCustomItemDefinition.builder(Identifier.of(item.identifier()), item.javaId())
                    // TODO: translations are currently not working
                    .translationString("item." + item.identifier())
                    .component(ItemDataComponents.MAX_STACK_SIZE, item.stackSize())
                    .component(ItemDataComponents.MAX_DAMAGE, item.maxDamage())
                    .component(ItemDataComponents.ENCHANTABLE, item.enchantable())
                    .bedrockOptions(CustomItemBedrockOptions.builder()
                            .allowOffhand(true)
                            .creativeCategory(Optional.ofNullable(CreativeCategory.fromName(item.creativeCategory())).orElse(CreativeCategory.ITEM_COMMAND_ONLY))
                            .creativeGroup(item.creativeGroup())
                            .displayHandheld(item.isTool())
                            .icon(item.icon())
                    );
            if (item.isEatable()) builder
                    .component(ItemDataComponents.FOOD, FoodProperties.builder().canAlwaysEat(item.isAlwaysEatable()).build())
                    .component(ItemDataComponents.CONSUMABLE, Consumable.builder().consumeSeconds(item.consumeSeconds()).build());
            if (item.isTool()) builder
                    .component(
                            ItemDataComponents.TOOL, getGeyserToolProperties(item.toolProperties())
                    );
            event.register(builder.build());
        });
    }

    private static ToolProperties getGeyserToolProperties(dev.lost.engine.geyserextension.lomapping.items.toolproperties.@NotNull ToolProperties properties) {
        ToolProperties.Builder builder = ToolProperties.builder()
                .canDestroyBlocksInCreative(properties.canDestroyBlocksInCreative())
                .defaultMiningSpeed(properties.defaultMiningSpeed());
        for (dev.lost.engine.geyserextension.lomapping.items.toolproperties.ToolProperties.Rule rule : properties.rules()) {
            builder.rule(ToolProperties.Rule.builder()
                    .blocks(Holders.of(rule.blocks().stream().map(Identifier::of).toList()))
                    .speed(rule.speed())
                    .build());
        }
        return builder.build();
    }

}
