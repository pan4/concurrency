package com.dataart.task1.threads;

import java.util.concurrent.TimeUnit;

public abstract class Part implements Runnable {

    protected Part previous;
    protected Part next;

    protected Long elapsedTime;
    private boolean isIntegrated;
    private boolean isBuilt;
    private Thread worker;

    public Part() {
    }

    public Part(Part previous, Part next) {
        this.previous = previous;
        this.next = next;
    }

    @Override
    public void run() {
        long start = System.nanoTime();

        try {
            build();
            isBuilt = true;

            if (previous != null) {
                previous.worker.join();
            }
        } catch (InterruptedException e) {
            String phase = isBuilt ? "integrating" : "building";
            System.out.println(String.format("%s %s has been interrupted", getClass().getSimpleName(), phase));
            if (previous != null) {
                previous.worker.interrupt();
            }
            if (next != null) {
                next.worker.interrupt();
            }
            Thread.currentThread().interrupt();
            return;
        }

        long end = System.nanoTime();
        elapsedTime = TimeUnit.SECONDS.convert(end - start, TimeUnit.NANOSECONDS);

        if (previous == null || previous.isIntegrated) {
            isIntegrated = true;
            finish();
        }
    }

    protected abstract void build() throws InterruptedException;

    protected abstract void finish();

    public void setPrevious(Part previous) {
        this.previous = previous;
    }

    public void setNext(Part next) {
        this.next = next;
    }

    public boolean isIntegrated() {
        return isIntegrated;
    }

    public Thread getWorker() {
        return worker;
    }

    public void setWorker(Thread worker) {
        this.worker = worker;
    }
}