package com.dataart.task5.deadlock;

import java.util.concurrent.BlockingQueue;

public class Worker2 implements Runnable {
    private final BlockingQueue<Integer> queue1;
    private final BlockingQueue<Integer> queue2;
    private int seed = 0;

    public Worker2(BlockingQueue<Integer> queue1, BlockingQueue<Integer> queue2) {
        this.queue1 = queue1;
        this.queue2 = queue2;
    }

    @Override
    public void run() {
        while (true) {
            try {
                queue2.put(seed);
                System.out.println(String.format("Worker2 put %d value in the queue2", seed));
                seed++;
                Thread.sleep(200);
                if (seed % 2 == 0) {
                    System.out.println(String.format("Worker2 took %d value from queue1", queue1.take()));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
