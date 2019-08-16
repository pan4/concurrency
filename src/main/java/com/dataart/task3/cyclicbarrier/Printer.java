package com.dataart.task3.cyclicbarrier;


import java.util.concurrent.TimeUnit;

public class Printer implements Runnable {

    private String name;
    private PrintQueue printQueue;

    public Printer(PrintQueue printQueue, String name) {
        this.name = name;
        this.printQueue = printQueue;
    }

    public void run() {
        while (true) {
            int timeout = (int)Math.rint(Math.random() * 10);
            try {
                TimeUnit.SECONDS.sleep(timeout);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
            if (printQueue.getCurrentNumber() == printQueue.getTotalNumber()){
                return;
            }
            printQueue.recharge(name);
        }
    }
}
