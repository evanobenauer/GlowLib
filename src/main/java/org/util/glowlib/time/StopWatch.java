package org.util.glowlib.time;

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


    public void initStart() {
        if (!isStarted()) start();
    }

    public void start() {
        setStartTimeMS(System.currentTimeMillis());
    }

    public void stop() {
        setStartTimeMS(-1);
    }

    public void restart() {
        start();
    }


    public boolean isStarted() {
        return getStartTimeMS() > -1;
    }


    public boolean hasTimeElapsedMS(double time) {
        return isStarted() && (time < (System.currentTimeMillis() - getStartTimeMS()));
    }

    public boolean hasTimeElapsedS(double time) {
        return isStarted() && ((time*1000) < (System.currentTimeMillis() - getStartTimeMS()));
    }


    public double getCurrentTimeMS() {
        return System.currentTimeMillis() - getStartTimeMS();
    }

    public double getCurrentTimeS() {
        long currentTime = System.currentTimeMillis() - getStartTimeMS();
        return Double.parseDouble(String.format("%.2f",((float)currentTime)/1000));
    }


    private long getStartTimeMS() {
        return time;
    }

    private long setStartTimeMS(long time) {
        this.time = time;
        return this.time;
    }

}
