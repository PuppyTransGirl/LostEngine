package dev.lost.engine.utils;

import java.awt.*;

public class ColorUtils {

    public static Color fromHex(String hex) {
        if (hex.startsWith("#")) hex = hex.substring(1);
        if (hex.length() == 8) {
            int r = Integer.parseInt(hex.substring(0, 2), 16);
            int g = Integer.parseInt(hex.substring(2, 4), 16);
            int b = Integer.parseInt(hex.substring(4, 6), 16);
            int a = Integer.parseInt(hex.substring(6, 8), 16);
            return new Color(r, g, b, a);
        } else if (hex.length() == 6) {
            int r = Integer.parseInt(hex.substring(0, 2), 16);
            int g = Integer.parseInt(hex.substring(2, 4), 16);
            int b = Integer.parseInt(hex.substring(4, 6), 16);
            return new Color(r, g, b);
        } else {
            throw new IllegalArgumentException("Format hex invalide");
        }
    }

}
