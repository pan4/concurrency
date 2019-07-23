package com.dataart.task5.blocking;

import java.util.concurrent.BlockingQueue;

public class Producer2 implements Runnable {

    private final BlockingQueue<Integer> queue;
    private int seed = 1;

    Producer2(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("ProducerBlocking1 Started 2");
        try {
            while (true) {
                queue.put(seed);
                Thread.sleep(200);
                seed += 2;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
