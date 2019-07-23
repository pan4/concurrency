package com.dataart.task2.atomic.counters;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter extends Counter {
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
