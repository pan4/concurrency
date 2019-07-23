package com.dataart.task2.locks;

import com.dataart.utils.SysUtil;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Printer {

    private final Lock lock = new ReentrantLock();

    public void workProcess() {
        lock.lock();
        try {
            System.out.printf("%s: Going to print a document\n", Thread.currentThread().getName());
            int duration = (int) (Math.random() * 10);
            System.out.println(Thread.currentThread().getName() + ":PrintQueue: Printing a Job during " + duration + " seconds");
            SysUtil.sleep(duration);
            System.out.printf("%s: The document has been printed\n", Thread.currentThread().getName());
        } finally {
            lock.unlock();
        }
    }
}
