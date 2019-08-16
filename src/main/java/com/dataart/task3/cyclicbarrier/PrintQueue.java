package com.dataart.task3.cyclicbarrier;


import com.dataart.task2.atomic.counters.AtomicCounter;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

public class PrintQueue {

    private CyclicBarrier barrier;
    private AtomicCounter counter = new AtomicCounter();
    private ReentrantLock lock = new ReentrantLock();
    private final int totalNumber;

    public PrintQueue(int hardWorking, int documentNumber) {
        this.totalNumber = documentNumber;
        barrier = new CyclicBarrier(hardWorking, () -> System.out.println("print ..."));
    }

    public void recharge(String name) {
        try {
            lock.lock();
            try {
                if(counter.getValue() < totalNumber) {
                    counter.increment();
                    System.out.printf("%s has sent document to print\n", name);
                }
                else {
                    return;
                }
            } finally {
                lock.unlock();
            }
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    public int getCurrentNumber(){
        return counter.getValue();
    }

    public int getTotalNumber(){
        return totalNumber;
    }

}
