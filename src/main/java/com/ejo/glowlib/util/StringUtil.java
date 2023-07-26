package com.ejo.glowlib.util;

/**
 * The StringUtil class offers many conversion checking methods for Strings
 */
public class StringUtil {

    public static char getLetterFromIndex(int index) {
        return switch(index) {
            case 1 -> 'a';
            case 2 -> 'b';
            case 3 -> 'c';
            case 4 -> 'd';
            case 5 -> 'e';
            case 6 -> 'f';
            case 7 -> 'g';
            case 8 -> 'h';
            case 9 -> 'i';
            case 10 -> 'j';
            case 11 -> 'k';
            case 12 -> 'l';
            case 13 -> 'm';
            case 14 -> 'n';
            case 15 -> 'o';
            case 16 -> 'p';
            case 17 -> 'q';
            case 18 -> 'r';
            case 19 -> 's';
            case 20 -> 't';
            case 21 -> 'u';
            case 22 -> 'v';
            case 23 -> 'w';
            case 24 -> 'x';
            case 25 -> 'y';
            case 26 -> 'z';
            default -> '_';
        };
    }

    public static boolean isStringInteger(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isStringFloat(String string) {
        try {
            Float.parseFloat(string);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isStringDouble(String string) {
        try {
            Double.parseDouble(string);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isStringBoolean(String string) {
        return string.equalsIgnoreCase("true") || string.equalsIgnoreCase("false");
    }


    public static class ColorCodes {
        public static final String red = "\u001B[31m";
        public static final String yellow = "\u001B[33m";
        public static final String green = "\u001B[32m";
        public static final String blue = "\u001B[34m";
        public static final String cyan = "\u001B[36m";
        public static final String purple = "\u001B[35m";
        public static final String white = "\u001B[37m";
        public static final String black = "\u001B[30m";

        public static final String redH = "\u001B[41m";
        public static final String yellowH = "\u001B[43m";
        public static final String greenH = "\u001B[42m";
        public static final String blueH = "\u001B[44m";
        public static final String cyanH = "\u001B[46m";
        public static final String purpleH = "\u001B[45m";
        public static final String whiteH = "\u001B[47m";
        public static final String blackH = "\u001B[40m";

        public static final String reset = "\u001B[0m";
    }

}
