package com.dataart.task2.atomic.counters;

public class SynchronizeThisCounter extends Counter {
    private int counter = 0;


    @Override
    public void increment() {
        synchronized (this){
            counter++;
        }
    }

    @Override
    public int getValue() {
        synchronized (this){
            return counter;
        }
    }
}
