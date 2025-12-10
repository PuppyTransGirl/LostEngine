package dev.lost.engine.lomodel.cubes;

import java.util.Map;
import java.util.UUID;

public interface Cube {
    String name();
    UUID uuid();
    float[] position();
    float[] scale();
    float[] rotation();
    Map<String, String> faces();
}
