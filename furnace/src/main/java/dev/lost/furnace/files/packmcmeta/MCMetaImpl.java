package dev.lost.furnace.files.packmcmeta;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public record MCMetaImpl(int packFormat, String description) implements MCMeta {

    @Override
    public JsonElement json() {
        return JsonParser.parseString("{\"pack\":{\"pack_format\":%d,\"description\":\"%s\"}}".formatted(packFormat, description));
    }
}