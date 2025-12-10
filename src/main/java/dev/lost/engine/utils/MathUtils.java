package dev.lost.engine.utils;

import org.joml.Quaternionf;
import org.joml.Vector3f;

public class MathUtils {

    public static Vector3f rotatePointAroundOrigin(Vector3f point, Vector3f origin, Quaternionf rotation) {
        // Translate the point to be relative to the origin
        Vector3f translatedPoint = new Vector3f(point).sub(origin);

        // Apply the rotation
        rotation.transform(translatedPoint);

        // Translate the point back to the original coordinate system
        translatedPoint.add(origin);

        return translatedPoint;
    }
}
