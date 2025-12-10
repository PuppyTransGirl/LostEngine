package dev.lost.engine.lomodel.animations.tracks;

import java.util.Map;
import java.util.UUID;

public interface Track {
    UUID uuid();
    Map<Float, float[]> position();
    Map<Float, float[]> rotation();
    Map<Float, float[]> scale();
}
