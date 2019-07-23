package com.dataart.task1.threads;

import java.util.concurrent.TimeUnit;

public class Walls extends Part {

    public Walls(){
        this(null);
    }

    public Walls(Part antecedent){
        super(antecedent);
    }

    @Override
    protected void build() {
        int timeout = (int)Math.rint(Math.random() * 10);
        System.out.println("Building walls... " + timeout + " sec remaining");
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void finish() {
        System.out.println("Thread 2 walls has been built");
    }
}
