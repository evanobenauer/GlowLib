package com.ejo.glowlib.util;

/**
 * The StringUtil class offers many conversion checking methods for Strings
 */
public class StringUtil {

    public static char getLetterFromIndex(int index) {
        return switch (index) {
            case 0 -> 'a';
            case 1 -> 'b';
            case 2 -> 'c';
            case 3 -> 'd';
            case 4 -> 'e';
            case 5 -> 'f';
            case 6 -> 'g';
            case 7 -> 'h';
            case 8 -> 'i';
            case 9 -> 'j';
            case 10 -> 'k';
            case 11 -> 'l';
            case 12 -> 'm';
            case 13 -> 'n';
            case 14 -> 'o';
            case 15 -> 'p';
            case 16 -> 'q';
            case 17 -> 'r';
            case 18 -> 's';
            case 19 -> 't';
            case 20 -> 'u';
            case 21 -> 'v';
            case 22 -> 'w';
            case 23 -> 'x';
            case 24 -> 'y';
            case 25 -> 'z';
            default -> '_';
        };
    }

    private int getIndexFromLetter(char letter) {
        return switch (letter) {
            case 'a', 'A' -> 0;
            case 'b', 'B' -> 1;
            case 'c', 'C' -> 2;
            case 'd', 'D' -> 3;
            case 'e', 'E' -> 4;
            case 'f', 'F' -> 5;
            case 'g', 'G' -> 6;
            case 'h', 'H' -> 7;
            case 'i', 'I' -> 8;
            case 'j', 'J' -> 9;
            case 'k', 'K' -> 10;
            case 'l', 'L' -> 11;
            case 'm', 'M' -> 12;
            case 'n', 'N' -> 13;
            case 'o', 'O' -> 14;
            case 'p', 'P' -> 15;
            case 'q', 'Q' -> 16;
            case 'r', 'R' -> 17;
            case 's', 'S' -> 18;
            case 't', 'T' -> 19;
            case 'u', 'U' -> 20;
            case 'v', 'V' -> 21;
            case 'w', 'W' -> 22;
            case 'x', 'X' -> 23;
            case 'y', 'Y' -> 24;
            case 'z', 'Z' -> 25;
            default -> -1;
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


    public static class ConsoleColorCodes {
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
