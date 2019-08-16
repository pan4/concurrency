package com.dataart.task2.atomic.counters;

import java.util.concurrent.atomic.LongAdder;

public class LongAdderCounter extends Counter {
    private LongAdder counter = new LongAdder();

    @Override
    public void increment() {
        counter.increment();
    }

    @Override
    public int getValue() {
        return counter.intValue();
    }
}
