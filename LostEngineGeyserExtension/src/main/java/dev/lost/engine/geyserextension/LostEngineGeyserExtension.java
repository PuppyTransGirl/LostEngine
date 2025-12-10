package dev.lost.engine.geyserextension;

import dev.lost.engine.geyserextension.lomapping.Mapping;
import dev.lost.engine.geyserextension.lomapping.MappingReader;
import org.geysermc.event.subscribe.Subscribe;
import org.geysermc.geyser.api.GeyserApi;
import org.geysermc.geyser.api.event.lifecycle.GeyserDefineCustomBlocksEvent;
import org.geysermc.geyser.api.event.lifecycle.GeyserDefineCustomItemsEvent;
import org.geysermc.geyser.api.event.lifecycle.GeyserPreInitializeEvent;
import org.geysermc.geyser.api.extension.Extension;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class LostEngineGeyserExtension implements Extension {

    private Mapping mapping;

    @Subscribe
    public void onGeyserDefineCustomItemsEvent(@NotNull GeyserDefineCustomItemsEvent event) {
        if (mapping == null) {
            this.logger().severe("Lost Engine mapping is not loaded, cannot define custom items.");
            return;
        }
        ItemGenerator.generateFromJson(mapping, event);
    }

    @Subscribe
    public void onGeyserDefineCustomBlocksEvent(@NotNull GeyserDefineCustomBlocksEvent event) {
        if (mapping == null) {
            this.logger().severe("Lost Engine mapping is not loaded, cannot define custom blocks.");
            return;
        }
        BlockGenerator.generateFromJson(mapping, event);
    }

    @Subscribe
    public void onGeyserPreInitializeEvent(@NotNull GeyserPreInitializeEvent event) {
        if (!isAddNonBedrockItemsEnabledFromGeyserConfig()) {
            this.logger().info(
                    "It looks like 'add-non-bedrock-items' is disabled in your Geyser config.yml, " +
                            "LostEngine Geyser Extension needs this option to be enabled for it to work, " +
                            "consider enabling it."
            );
        }
        File mappingFile = new File(dataFolder().toFile(), "mappings.lomapping");
        if (!mappingFile.exists()) {
            this.logger().severe("Lost Engine mapping file not found in the extension data folder. (%s)".formatted(mappingFile.getAbsolutePath()));
            mappingFile.getParentFile().mkdirs();
            return;
        }
        mapping = MappingReader.read(mappingFile.toPath());
    }

    public static boolean isAddNonBedrockItemsEnabled(Path configPath) {
        if (configPath == null || !Files.exists(configPath)) return false;
        try {
            List<String> lines = Files.readAllLines(configPath, StandardCharsets.UTF_8);
            for (String raw : lines) {
                String noComment = raw.split("#", 2)[0].trim();
                if (noComment.isEmpty()) continue;
                String lower = noComment.toLowerCase();
                if (!lower.startsWith("add-non-bedrock-items")) continue;
                int colon = noComment.indexOf(':');
                if (colon < 0) continue;
                String value = noComment.substring(colon + 1).trim();
                return Boolean.parseBoolean(value);
            }
        } catch (IOException ignored) {
        }
        return false;
    }

    public static boolean isAddNonBedrockItemsEnabledFromGeyserConfig() {
        Path cfg = GeyserApi.api().configDirectory().resolve("config.yml");
        return isAddNonBedrockItemsEnabled(cfg);
    }

}
