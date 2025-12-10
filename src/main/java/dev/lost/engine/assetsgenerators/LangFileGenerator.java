package dev.lost.engine.assetsgenerators;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import dev.lost.furnace.files.unknown.UnknownFile;
import dev.lost.furnace.resourcepack.BedrockResourcePack;
import dev.lost.furnace.resourcepack.JavaResourcePack;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class LangFileGenerator {

    private static final Gson GSON = new Gson();
    Map<String, Lang> languages = new Object2ObjectOpenHashMap<>();

    public void addTranslation(String langCode, String key, String value) {
        languages.computeIfAbsent(langCode, k -> new Lang()).put(key, value);
    }

    public void build(JavaResourcePack resourcePack, @Nullable BedrockResourcePack bedrockResourcePack) {
        JsonArray jsonLanguages = bedrockResourcePack != null ? new JsonArray() : null;
        languages.forEach((langCode, lang) -> {
            resourcePack.jsonFile("assets/minecraft/lang/%s.json".formatted(langCode.toLowerCase()), GSON.toJsonTree(lang));
            if (bedrockResourcePack != null) {
                jsonLanguages.add(langCode);
                StringBuilder sb = new StringBuilder();
                lang.forEach((key, value) -> {
                    if (key.startsWith("item.lost_engine."))
                        key = key.replaceFirst("item.lost_engine.", "item.lost_engine:");
                    sb.append(key).append("=").append(value).append("\n");
                });
                bedrockResourcePack.unknownFile(UnknownFile.utf8("texts/" + langCode + ".lang", sb.toString()));
            }
        });
        if (bedrockResourcePack != null) {
            bedrockResourcePack.unknownFile(UnknownFile.utf8("texts/languages.json", jsonLanguages.toString()));
        }
    }

    public static class Lang extends HashMap<String, String> {
    }
}
