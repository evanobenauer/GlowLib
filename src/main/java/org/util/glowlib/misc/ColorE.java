package org.util.glowlib.misc;

import java.awt.*;

public class ColorE {


    public static ColorE RED = new ColorE(new Color(255, 0, 0));
    public static ColorE ORANGE = new ColorE(new Color(200, 100, 0));
    public static ColorE YELLOW = new ColorE(new Color(255, 200, 0));
    public static ColorE GREEN = new ColorE(new Color(0, 255, 0));
    public static ColorE BLUE = new ColorE(new Color(0, 119, 255));
    public static ColorE PURPLE = new ColorE(new Color(100, 0, 255));
    public static ColorE WHITE = new ColorE(new Color(255,255,255));
    public static ColorE GRAY = new ColorE(new Color(125,125,125));
    public static ColorE GREY = new ColorE(new Color(125,125,125));
    public static ColorE BLACK = new ColorE(new Color(0,0,0));


    private int red, green, blue, alpha;


    public ColorE(int red, int green, int blue, int alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    public ColorE(int red, int green, int blue) {
        this(red,green,blue,255);
    }

    public ColorE(Color color) {
        this(color.getRed(),color.getGreen(),color.getBlue(),color.getAlpha());
    }


    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public int getAlpha() {
        return alpha;
    }


    public void setRed(int red) {
        if (red > 255) red = 255;
        if (red < 0) red = 0;
        this.red = red;
    }

    public void setGreen(int green) {
        if (green > 255) green = 255;
        if (green < 0) green = 0;
        this.green = green;
    }

    public void setBlue(int blue) {
        if (blue > 255) blue = 255;
        if (blue < 0) blue = 0;
        this.blue = blue;
    }

    public void setAlpha(int alpha) {
        if (alpha > 255) alpha = 255;
        if (alpha < 0) alpha = 0;
        this.alpha = alpha;
    }

    public int getHash() {
        return ((getAlpha() & 0xFF) << 24) | ((getRed() & 0xFF) << 16) | ((getGreen() & 0xFF) << 8)  | ((getBlue() & 0xFF) << 0);
        //return new Color(getRed(),getGreen(),getBlue(),getAlpha()).hashCode();
    }

    @Override
    public String toString() {
        return "[ColorE: " + red + "|" + green + "|" + blue + "|" + alpha + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ColorE colorE)) return false;
        return getRed() == colorE.getRed() && getGreen() == colorE.getGreen() && getBlue() == colorE.getBlue() && getAlpha() == colorE.getAlpha();
    }

}
