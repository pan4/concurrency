package com.dataart.task1.threads;

import java.util.concurrent.TimeUnit;

public class Roof extends Part {

    public Roof(){
        this(null);
    }

    public Roof(Part antecedent){
        super(antecedent);
    }

    @Override
    protected void build() {
        int timeout = (int)Math.rint(Math.random() * 10);
        System.out.println("Building roof... " + timeout + " sec remaining");
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void finish() {
        System.out.println("Thread 3 roof has been built");
    }
}
