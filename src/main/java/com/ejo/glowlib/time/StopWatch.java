package com.ejo.glowlib.time;

/**
 * The StopWatch class is a very simple stop watch. It is able to start, stop, restart, and get the time at any point in its process.
 * This is very useful for simply timing when you want things to occur, especially in loops
 */
public class StopWatch {


    private long time;


    public StopWatch() {
        setStartTimeMS(-1);
    }

    public StopWatch(boolean startOnInstantiation) {
        this();
        if (startOnInstantiation) start();
    }


    public void start() {
        if (!isStarted()) restart();
    }

    public void stop() {
        setStartTimeMS(-1);
    }

    public void restart() {
        setStartTimeMS(System.currentTimeMillis());
    }

    public void restartAtMS(double timeMS) {
        if (hasTimePassedMS(timeMS)) restart();
    }

    public void restartAtS(double timeS) {
        if (hasTimePassedS(timeS)) restart();
    }

    public void runMS(int timeMS, Runnable action) {
        start();
        if (hasTimePassedMS(timeMS)) {
            action.run();
            restart();
        }
    }

    public void runS(int timeS, Runnable action) {
        start();
        if (hasTimePassedS(timeS)) {
            action.run();
            restart();
        }
    }


    public boolean isStarted() {
        return getStartTimeMS() > -1;
    }

    public boolean hasTimePassedMS(double time) {
        return isStarted() && time < getTimeMS();
    }

    public boolean hasTimePassedS(double time) {
        return isStarted() && time * 1000 < getTimeMS();
    }

    public double getTimeMS() {
        return System.currentTimeMillis() - getStartTimeMS();
    }

    public double getTimeS() {
        return Double.parseDouble(String.format("%.2f", ((float) getTimeMS()) / 1000));
    }


    private long getStartTimeMS() {
        return time;
    }

    private long setStartTimeMS(long time) {
        this.time = time;
        return this.time;
    }

}
