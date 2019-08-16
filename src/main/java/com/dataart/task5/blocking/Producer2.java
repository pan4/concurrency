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
        System.out.println("ProducerBlocking2 Started");
        try {
            while (true) {
                queue.put(seed);
                System.out.println(String.format("ProducerBlocking2 put %d value in the queue", seed));
                Thread.sleep(1000);
                seed += 2;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

}
