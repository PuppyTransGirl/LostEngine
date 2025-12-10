package dev.lost.engine.utils;

import org.bukkit.Bukkit;

import java.util.UUID;

public class FloodgateUtils {

    public static final boolean IS_FLOODGATE_ENABLED = Bukkit.getPluginManager().isPluginEnabled("floodgate");

    public static boolean isBedrockPlayer(UUID uuid) {
        return IS_FLOODGATE_ENABLED && org.geysermc.floodgate.api.FloodgateApi.getInstance().isFloodgatePlayer(uuid);
    }

}
