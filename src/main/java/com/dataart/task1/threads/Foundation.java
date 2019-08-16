package com.dataart.task1.threads;

import java.util.concurrent.TimeUnit;

public class Foundation extends Part {

    public Foundation() {
        this(null, null);
    }

    public Foundation(Part previous, Part next) {
        super(previous, next);
    }

    @Override
    protected void build() throws InterruptedException {
        int timeout = (int) Math.rint(Math.random() * 10);
        System.out.println("Building foundation... " + timeout + " sec remaining");

        TimeUnit.SECONDS.sleep(timeout);

        System.out.println("Foundation has been built");
    }

    @Override
    protected void finish() {
        System.out.println(String.format("Foundation was integrated in %s sec after start", elapsedTime));
    }
}
