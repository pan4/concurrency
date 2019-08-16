package com.dataart.task5.deadlock;

import java.util.concurrent.BlockingQueue;

public class Worker1 implements Runnable {
    private final BlockingQueue<Integer> queue1;
    private final BlockingQueue<Integer> queue2;
    private int seed = 0;

    public Worker1(BlockingQueue<Integer> queue1, BlockingQueue<Integer> queue2) {
        this.queue1 = queue1;
        this.queue2 = queue2;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println(String.format("Worker1 took %d value from queue2", queue2.take()));
                Thread.sleep(200);
                queue1.put(seed);
                System.out.println(String.format("Worker1 put %d value in the queue1", seed));
                seed++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
