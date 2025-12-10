package dev.lost.engine.geyserextension.lomapping;

import dev.lost.engine.geyserextension.lomapping.blocks.Block;
import dev.lost.engine.geyserextension.lomapping.items.Item;

import java.util.List;

public interface Mapping {

    List<? extends Item> items();

    List<? extends Block> blocks();

}
