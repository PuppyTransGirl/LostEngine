package dev.lost.engine.lomodel.animations;

import dev.lost.engine.lomodel.animations.tracks.Track;

import java.util.Map;
import java.util.UUID;

public interface Animation {
    String name();
    float length();
    Map<UUID, ? extends Track> tracks();
}
