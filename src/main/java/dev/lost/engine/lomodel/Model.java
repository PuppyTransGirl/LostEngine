package dev.lost.engine.lomodel;

import dev.lost.engine.lomodel.animations.Animation;
import dev.lost.engine.lomodel.cubes.Cube;

import java.util.List;

public interface Model {

    List<? extends Cube> cubes();

    List<? extends Animation> animations();

}
