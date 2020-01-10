package com.dataart.task5.nonblocking.time.wast;

import java.util.Queue;

public class Producer1 implements Runnable {

    private final Queue<Integer> queue;
    private int seed = 0;

    Producer1(Queue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("ProducerNoBlocking1 Started 1");
        try {
            while (true) {
                queue.add(seed);
                Thread.sleep(100);
                seed += 2;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}
