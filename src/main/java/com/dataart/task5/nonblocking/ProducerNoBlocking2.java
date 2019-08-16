package com.dataart.task5.nonblocking;

import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ProducerNoBlocking2 implements Runnable {

    private final Queue<Integer> queue;
    private final Condition notEmpty;
    private final Lock lock;
    private int seed = 0;

    ProducerNoBlocking2(Queue<Integer> queue, Lock lock, Condition condition) {
        this.queue = queue;
        this.lock = lock;
        this.notEmpty = condition;
    }

    @Override
    public void run() {
        System.out.println("ProducerNoBlocking2 Started");
        while (true) {
            lock.lock();
            try {
                queue.add(seed);
                System.out.println(String.format("ProducerNoBlocking2 added %d value in the queue", seed));
                notEmpty.signal();
            } finally {
                lock.unlock();
            }
            seed += 2;
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
    }
}
