package com.dataart.task2.atomic.counters;

public class SynchronizeCounter extends Counter {

    private int counter = 0;

    @Override
    public synchronized void increment() {
        counter++;
    }

    @Override
    synchronized public int getValue() {
        return counter;
    }
}
