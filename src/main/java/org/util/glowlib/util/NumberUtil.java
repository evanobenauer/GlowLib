package org.util.glowlib.util;

/**
 * The NumberUtil class offers many conversion checking methods for numbers
 */
public class NumberUtil {

    public static boolean isNumberInteger(double number) {
        try {
            return number == (int) number;
        } catch (Exception exception) {
            return false;
        }
    }

    public static boolean isNumberLong(double number) {
        try {
            return number == (long) number;
        } catch (Exception exception) {
            return false;
        }
    }

    public static boolean isNumberShort(double number) {
        try {
            return number == (short) number;
        } catch (Exception exception) {
            return false;
        }
    }

    public static boolean isNumberFloat(double number) {
        try {
            return number == (float) number;
        } catch (Exception exception) {
            return false;
        }
    }

    public static boolean isNumberDouble(double number) {
        try {
            return number == (double) number;
        } catch (Exception exception) {
            return false;
        }
    }

    public static Number boundValue(Number value, double low, double high) {
        if (value.doubleValue() < low) value = low;
        if (value.doubleValue() > high) value = high;
        return value;
    }

}
