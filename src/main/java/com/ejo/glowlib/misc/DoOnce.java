package com.ejo.glowlib.misc;

/**
 * DoOnce is a class that will run an action a single time until reset
 */
public class DoOnce {

    public static DoOnce default1 = new DoOnce();
    public static DoOnce default2 = new DoOnce();
    public static DoOnce default3 = new DoOnce();
    public static DoOnce default4 = new DoOnce();
    public static DoOnce default5 = new DoOnce();
    public static DoOnce default6 = new DoOnce();
    public static DoOnce default7 = new DoOnce();
    public static DoOnce default8 = new DoOnce();
    public static DoOnce default9 = new DoOnce();
    public static DoOnce default10 = new DoOnce();

    private boolean shouldRun;

    private Runnable action;

    public DoOnce(Runnable action) {
        this.action = action;
        this.shouldRun = true;
    }

    public DoOnce() {
        this(null);
    }


    public void run() {
        if (shouldRun) {
            this.action.run();
            this.shouldRun = false;
        }
    }

    public void run(Runnable action) {
        if (shouldRun) {
            action.run();
            this.shouldRun = false;
        }
    }

    public void reset() {
        shouldRun = true;
    }


    public void setAction(Runnable action) {
        this.action = action;
    }

}
