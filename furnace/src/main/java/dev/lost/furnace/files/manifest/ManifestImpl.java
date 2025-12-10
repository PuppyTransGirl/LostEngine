package dev.lost.furnace.files.manifest;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.UUID;

public record ManifestImpl(String name, String description) implements Manifest {

    @Override
    public JsonElement json() {
        return JsonParser.parseString("""
                {
                    "format_version": 2,
                    "header": {
                        "description": "%s",
                        "name": "%s",
                        "uuid": "%s",
                        "version": [1, 0, 0],
                        "min_engine_version": [1, 16, 0]
                    },
                    "modules": [
                        {
                            "description": "%s",
                            "type": "resources",
                            "uuid": "%s",
                            "version": [1, 0, 0]
                        }
                    ]
                }
                """.formatted(description, name, UUID.randomUUID().toString(), description, UUID.randomUUID().toString()));
    }
}
