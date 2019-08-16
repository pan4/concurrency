package com.dataart.task2.atomic;

import com.dataart.task2.atomic.counters.Counter;

public class Task implements Runnable {

    private Counter atomicCounter;
    int loadFactor = 10000;

    public Task(Counter atomicCounter) {
        this.atomicCounter = atomicCounter;
    }

    @Override
    public void run() {
        for(int i = 0; i < loadFactor; i++) {
            atomicCounter.increment();
        }
    }

}
