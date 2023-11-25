package com.ejo.glowlib.misc;

import com.ejo.glowlib.util.NumberUtil;

import java.awt.*;

public class ColorE {


    public static final ColorE RED = new ColorE(new Color(255, 0, 0));
    public static final ColorE ORANGE = new ColorE(new Color(200, 100, 0));
    public static final ColorE YELLOW = new ColorE(new Color(255, 200, 0));
    public static final ColorE GREEN = new ColorE(new Color(0, 255, 0));
    public static final ColorE BLUE = new ColorE(new Color(0, 119, 255));
    public static final ColorE PURPLE = new ColorE(new Color(100, 0, 255));
    public static final ColorE WHITE = new ColorE(new Color(255, 255, 255));
    public static final ColorE GRAY = new ColorE(new Color(125, 125, 125));
    public static final ColorE GREY = new ColorE(new Color(125, 125, 125));
    public static final ColorE BLACK = new ColorE(new Color(0, 0, 0));

    public static final ColorE NULL = new ColorE(0, 0, 0, 0);


    private int red, green, blue, alpha;

    public ColorE(int red, int green, int blue, int alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    public ColorE(int red, int green, int blue) {
        this(red, green, blue, 255);
    }

    public ColorE(Color color) {
        this(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }


    public ColorE red(int red) {
        return new ColorE(red, getGreen(), getBlue(), getAlpha());
    }

    public ColorE green(int green) {
        return new ColorE(getRed(), green, getBlue(), getAlpha());
    }

    public ColorE blue(int blue) {
        return new ColorE(getRed(), getGreen(), blue, getAlpha());
    }

    public ColorE alpha(int alpha) {
        return new ColorE(getRed(), getGreen(), getBlue(), alpha);
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

    public int getHash() {
        return ((getAlpha() & 0xFF) << 24) | ((getRed() & 0xFF) << 16) | ((getGreen() & 0xFF) << 8) | ((getBlue() & 0xFF) << 0);
    }


    public ColorE setRed(int red) {
        this.red = (int) NumberUtil.getBoundValue(red, 0, 255);
        return this;
    }

    public ColorE setGreen(int green) {
        this.green = (int) NumberUtil.getBoundValue(green, 0, 255);
        return this;
    }

    public ColorE setBlue(int blue) {
        this.blue = (int) NumberUtil.getBoundValue(blue, 0, 255);
        return this;
    }

    public ColorE setAlpha(int alpha) {
        this.alpha = (int) NumberUtil.getBoundValue(alpha, 0, 255);
        return this;
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
