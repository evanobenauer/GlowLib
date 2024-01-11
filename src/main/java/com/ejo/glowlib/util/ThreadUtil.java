package com.ejo.glowlib.util;

/**
 * General Utility Class for GlowLib containing various useful and misc methods
 */
public class ThreadUtil {

    public static Thread runInThread(String name, boolean daemon, Runnable action) {
        Thread thread = new Thread(action);
        thread.setName(name);
        thread.setDaemon(daemon);
        thread.start();
        return thread;
    }

}
