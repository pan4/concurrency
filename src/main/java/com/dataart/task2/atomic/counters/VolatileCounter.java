package com.dataart.task2.atomic.counters;

public class VolatileCounter extends Counter {

    private volatile int counter;

    @Override
    public void increment() {
        counter++;
    }

    /**
     * Volatile keyword marks a variable to always go to main memory, for both reads and writes
     * But it does not guarantee atomicity and counter++ is not an atomic operation, but in fact is
     * read-increment-write operation
     */
    @Override
    public int getValue() {
        return counter;
    }
}
