package com.dataart.task3.cyclicbarrier;

public class Main {

    private static final int BATCH_SIZE = 5;
    private static final int THREADS_NUMBER = 50;
    private static final int DOCUMENTS_NUMBER = 50;

    public static void main(String args[]) {
        PrintQueue printQueue = new PrintQueue(BATCH_SIZE, DOCUMENTS_NUMBER);
        Thread[] arr = new Thread[THREADS_NUMBER];

        for (int i = 0; i < THREADS_NUMBER; i++) {
            arr[i] = new Thread(new Printer(printQueue, "PrinterDevice" + (i + 1)));
            arr[i].start();
        }
    }
}
