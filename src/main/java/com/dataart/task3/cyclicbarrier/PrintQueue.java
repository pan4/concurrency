package com.dataart.task3.cyclicbarrier;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class PrintQueue {

    CyclicBarrier barrier;

    public PrintQueue(int hardWorking) {
        barrier = new CyclicBarrier(hardWorking, new Runnable() {
            @Override
            public void run() {
                System.out.println("print ...");
            }
        });
    }

    public void recharge(String name) {
        try {
            System.out.printf("%s has recharged \n", name);
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
