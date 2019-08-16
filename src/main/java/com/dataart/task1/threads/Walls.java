package com.dataart.task1.threads;

import java.util.concurrent.TimeUnit;

public class Walls extends Part {

    public Walls() {
        this(null, null);
    }

    public Walls(Part previous, Part next) {
        super(previous, next);
    }

    @Override
    protected void build() throws InterruptedException {
        int timeout = (int) Math.rint(Math.random() * 10);
        System.out.println("Building walls... " + timeout + " sec remaining");

        TimeUnit.SECONDS.sleep(timeout);

        System.out.println("Walls have been built");
    }

    @Override
    protected void finish() {
        System.out.println(String.format("Walls were integrated in %s sec after start", elapsedTime));
    }
}
