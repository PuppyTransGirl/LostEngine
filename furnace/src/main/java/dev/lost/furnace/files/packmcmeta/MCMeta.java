package dev.lost.furnace.files.packmcmeta;

import com.google.gson.JsonElement;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public interface MCMeta {

    @NotNull
    @Contract(value = "_, _ -> new", pure = true)
    static MCMeta meta(int packFormat, String description) {
        return new MCMetaImpl(packFormat, description);
    }

    int packFormat();

    String description();

    JsonElement json();

}
