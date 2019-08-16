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
        System.out.println("ProducerBlocking1 Started");
        try {
            while (true) {
                queue.put(seed);
                System.out.println(String.format("ProducerBlocking1 put %d value in the queue", seed));
                Thread.sleep(500);
                seed += 2;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}
