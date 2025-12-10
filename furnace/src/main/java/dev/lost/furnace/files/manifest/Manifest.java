package dev.lost.furnace.files.manifest;

import com.google.gson.JsonElement;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public interface Manifest {

    @NotNull
    @Contract(value = "_, _ -> new", pure = true)
    static Manifest manifest(String name, String description) {
        return new ManifestImpl(name, description);
    }

    String name();

    String description();

    JsonElement json();

}