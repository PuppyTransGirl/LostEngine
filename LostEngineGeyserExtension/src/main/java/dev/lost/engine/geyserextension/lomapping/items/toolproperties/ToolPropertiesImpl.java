package dev.lost.engine.geyserextension.lomapping.items.toolproperties;

import java.util.List;

public record ToolPropertiesImpl(
        List<RuleImpl> rules,
        float defaultMiningSpeed,
        boolean canDestroyBlocksInCreative
) implements ToolProperties {

    record RuleImpl(
            List<String> blocks,
            float speed
    ) implements Rule {
    }

}
