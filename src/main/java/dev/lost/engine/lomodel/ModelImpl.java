package dev.lost.engine.lomodel;

import dev.lost.engine.lomodel.animations.AnimationImpl;
import dev.lost.engine.lomodel.cubes.CubeImpl;

import java.util.List;

public record ModelImpl(
        List<CubeImpl> cubes,
        List<AnimationImpl> animations
) implements Model {
}
