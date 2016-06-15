package com.utils;

/**
 * This class represents the Time Manager
 */
public class TimeManager {
    private long begin, end;

    public void start(){
        begin = System.currentTimeMillis();
    }

    public void stop(){
        end = System.currentTimeMillis();
    }

    public void restart(){
        begin = 0;
        end = 0;
    }

    public long getMilliseconds() {
        return end-begin;
    }

    public double getSeconds() {
        return (end - begin) / 1000.0;
    }
}
