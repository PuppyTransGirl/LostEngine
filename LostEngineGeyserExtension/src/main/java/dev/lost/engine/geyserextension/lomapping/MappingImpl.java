package dev.lost.engine.geyserextension.lomapping;

import dev.lost.engine.geyserextension.lomapping.blocks.BlockImpl;
import dev.lost.engine.geyserextension.lomapping.items.ItemImpl;

import java.util.List;

public record MappingImpl(
        List<ItemImpl> items,
        List<BlockImpl> blocks
) implements Mapping {
}