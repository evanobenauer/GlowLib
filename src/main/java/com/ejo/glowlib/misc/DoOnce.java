package com.ejo.glowlib.misc;

/**
 * DoOnce is a class that will run an action a single time until reset
 */
public class DoOnce {

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
