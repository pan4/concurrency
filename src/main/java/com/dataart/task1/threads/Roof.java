package com.dataart.task1.threads;

import java.util.concurrent.TimeUnit;

public class Roof extends Part {

    public Roof(){
        this(null, null);
    }

    public Roof(Part previous, Part next){
        super(previous, next);
    }

    @Override
    protected void build() throws InterruptedException {
        int timeout = (int)Math.rint(Math.random() * 10);
        System.out.println("Building roof... " + timeout + " sec remaining");

        TimeUnit.SECONDS.sleep(timeout);

        System.out.println("Roof has been built");
    }

    @Override
    protected void finish() {
        System.out.println(String.format("Roof was integrated in %s sec after start", elapsedTime));
    }
}
