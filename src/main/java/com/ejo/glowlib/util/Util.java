package com.ejo.glowlib.util;

/**
 * General Utility Class for GlowLib containing various useful and misc methods
 */
public class Util {

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

    public static Thread runInThread(String name, boolean daemon, Runnable action) {
        Thread thread = new Thread(action);
        thread.setName(name);
        thread.setDaemon(daemon);
        thread.start();
        return thread;
    }

}
