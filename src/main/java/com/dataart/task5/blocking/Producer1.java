package com.dataart.task5.blocking;

import java.util.concurrent.BlockingQueue;

public class Producer1 implements Runnable {

    private final BlockingQueue<Integer> queue;
    private int seed = 0;

    Producer1(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("ProducerBlocking1 Started 1");
        try {
            while (true) {
                queue.put(seed);
                Thread.sleep(100);
                seed += 2;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
