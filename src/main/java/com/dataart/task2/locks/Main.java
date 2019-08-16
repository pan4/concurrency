package com.dataart.task2.locks;

import java.util.concurrent.locks.ReentrantLock;

public class Main {

    private static final int SIZE = 5;

    public static void main(String args[]) {
        Printer printer = new Printer(new ReentrantLock());
        Thread thread[] = new Thread[SIZE];

        for (int i = 0; i < SIZE; i++) {
            thread[i] = new Thread(new Job(printer), "Printer " + i);
        }

        for (int i = 0; i < SIZE; i++) {
            thread[i].start();
        }
    }

}
