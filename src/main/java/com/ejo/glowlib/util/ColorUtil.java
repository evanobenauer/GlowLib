package com.ejo.glowlib.util;

import com.ejo.glowlib.misc.ColorE;

public class ColorUtil {

    public static ColorE getColorFromString(String string) {
        switch (string) {
            case "Red" -> {
                return ColorE.RED;
            }
            case "Orange" -> {
                return ColorE.ORANGE;
            }
            case "Yellow" -> {
                return ColorE.YELLOW;
            }
            case "Green" -> {
                return ColorE.GREEN;
            }
            case "Blue" -> {
                return ColorE.BLUE;
            }
            case "Purple" -> {
                return ColorE.PURPLE;
            }
            case "White" -> {
                return ColorE.WHITE;
            }
            case "Black" -> {
                return ColorE.BLACK;
            }
            default -> {
                return ColorE.NULL;
            }
        }
    }

    /**
     * Returns a color leaning towards Red or Green depending on the scale. Scale is given from 0 to 1
     * @param scale
     * @return
     */
    public static ColorE getRedGreenScaledColor(double scale) {
        return new ColorE((int)((1 - scale) * 255), (int)(scale * 255), 0);
    }
}
