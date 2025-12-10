package dev.lost.engine.lomodel.animations;

import dev.lost.engine.lomodel.animations.tracks.TrackImpl;

import java.util.Map;
import java.util.UUID;

public record AnimationImpl(
        String name,
        float length,
        Map<UUID, TrackImpl> tracks
) implements Animation {
}
