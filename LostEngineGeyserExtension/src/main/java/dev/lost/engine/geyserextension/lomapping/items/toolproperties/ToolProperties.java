package dev.lost.engine.geyserextension.lomapping.items.toolproperties;

import java.util.List;

public interface ToolProperties {
    List<? extends Rule> rules();
    float defaultMiningSpeed();

    boolean canDestroyBlocksInCreative();

    interface Rule {
        List<String> blocks();
        float speed();
    }
}
