package com.dataart.task5.nonblocking;

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
                System.out.println(queue.poll());
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

