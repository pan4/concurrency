package com.dataart.task2.atomic.counters;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter extends Counter {
    /**
     * Atomic constructs like AtomicInteger use nonblocking algorithms, which use low-level atomic machine
     * instructions such as compare-and-swap instead of locks to ensure data integrity under concurrent access
     */
    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public void increment() {
        counter.incrementAndGet();
    }

    @Override
    public int getValue() {
        return counter.get();
    }
}
