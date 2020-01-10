package com.dataart.task5.nonblocking.time.wast;

import java.util.Queue;

public class Consumer implements Runnable {

    private final Queue<Integer> queue;

    public Consumer(Queue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("Consumer Started");
        try {
            while (true) {
                Integer poll = queue.poll();
                while (poll == null){
                    poll = queue.poll();
                }
                System.out.println(poll);
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

}