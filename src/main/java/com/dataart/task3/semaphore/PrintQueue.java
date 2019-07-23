package com.dataart.task3.semaphore;

import com.dataart.utils.SysUtil;

import java.util.concurrent.Semaphore;

public class PrintQueue {
    private final Semaphore semaphore;

    public PrintQueue(int countPrints) {
        this.semaphore = new Semaphore(countPrints);
    }

    public void printJob() {
        try {
            semaphore.acquire();
            int duration=(int)(Math.random()*10);
            System.out.printf("%s: PrintQueue: Printing a Job during %d seconds\n",
                    Thread.currentThread().getName(),duration);
            SysUtil.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

}
