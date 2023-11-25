package com.ejo.glowlib.misc;

/**
 * DoOnce is a class that will run an action a single time until reset
 */
public class DoOnce {

    public static DoOnce DEFAULT1 = new DoOnce();
    public static DoOnce DEFAULT2 = new DoOnce();
    public static DoOnce DEFAULT3 = new DoOnce();
    public static DoOnce DEFAULT4 = new DoOnce();
    public static DoOnce DEFAULT5 = new DoOnce();
    public static DoOnce DEFAULT6 = new DoOnce();
    public static DoOnce DEFAULT7 = new DoOnce();
    public static DoOnce DEFAULT8 = new DoOnce();
    public static DoOnce DEFAULT9 = new DoOnce();
    public static DoOnce DEFAULT10 = new DoOnce();

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

    public DoOnce reset() {
        shouldRun = true;
        return this;
    }


    public DoOnce setAction(Runnable action) {
        this.action = action;
        return this;
    }

}
