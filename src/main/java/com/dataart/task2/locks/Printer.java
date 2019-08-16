package com.dataart.task2.locks;

import com.dataart.utils.SysUtil;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Printer {

    private final Lock lock;

    public Printer(Lock lock) {
        this.lock = lock;
    }

    public void workProcess() {
        /**
         * Current implementation won't get any benefits from tryLock() usage because there isn't
         * another job for thread to be done except of printing.
         */
        lock.lock();
        try {
            System.out.printf("%s: Going to print a document\n", Thread.currentThread().getName());
            int duration = (int) (Math.random() * 10);
            System.out.printf("%s: PrintQueue: Printing a Job during %s seconds\n", Thread.currentThread().getName(), duration);
            SysUtil.sleep(duration);
            System.out.printf("%s: The document has been printed\n", Thread.currentThread().getName());
        } finally {
            lock.unlock();
        }
    }
}
