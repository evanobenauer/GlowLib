package com.ejo.glowlib.util;

//TODO: Add list sorting mechanism
public class ListUtil {

    public static <T> int getMaxRowSize(T[][] grid) {
        int length = grid[0].length;
        for (T[] row : grid) {
            if (row.length > length) length = row.length;
        }
        return length;
    }

    public static int getMaxStringLength(String[] list) {
        int length = list[0].length();
        for (String string : list) {
            if (string.length() > length) length = string.length();
        }
        return length;
    }

}
