package com.dataart.task2.atomic.counters;

public class SynchronizeCounter extends Counter {

    private int counter = 0;

    @Override
    public synchronized void increment() {
        counter++;
    }

    /**
     * Yes, getValue should be synchronized. Otherwise, a thread could be working with a outdated value.
     * Alternatively we can mark counter as volatile.
    */
    @Override
    synchronized public int getValue() {
        return counter;
    }
}
