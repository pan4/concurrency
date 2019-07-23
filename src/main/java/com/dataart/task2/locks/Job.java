package com.dataart.task2.locks;

public class Job implements Runnable {

    private final Printer printer;

    public Job(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        printer.workProcess();
    }
}
