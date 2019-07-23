package com.dataart.task2.atomic.counters;

public class VolatileCounter extends Counter {

    private volatile int counter;

    @Override
    public void increment() {
        counter++;
    }

    @Override
    public int getValue() {
        return counter;
    }
}
