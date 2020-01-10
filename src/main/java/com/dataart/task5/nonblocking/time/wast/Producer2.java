package com.dataart.task5.nonblocking.time.wast;

import java.util.Queue;

public class Producer2 implements Runnable {

    private final Queue<Integer> queue;
    private int seed = 1;

    Producer2(Queue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("ProducerNoBlocking2 Started 2");
        try {
            while (true) {
                queue.add(seed);
                Thread.sleep(200);
                seed += 2;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}