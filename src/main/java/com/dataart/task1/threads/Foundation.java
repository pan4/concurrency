package com.dataart.task1.threads;

import java.util.concurrent.TimeUnit;

public class Foundation extends Part {

    public Foundation(){
        this(null);
    }

    public Foundation(Part antecedent){
        super(antecedent);
    }

    @Override
    protected void build() {
        int timeout = (int)Math.rint(Math.random() * 10);
        System.out.println("Building foundation... " + timeout + " sec remaining");
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void finish() {
        System.out.println("Thread 1 foundation has been built");
    }
}
