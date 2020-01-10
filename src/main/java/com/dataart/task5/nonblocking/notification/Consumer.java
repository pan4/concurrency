package com.dataart.task5.nonblocking.notification;

import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Consumer implements Runnable {

    private final Queue<Integer> queue;
    private final Lock lock;
    private final Condition notEmpty;

    public Consumer(Queue<Integer> queue, Lock lock, Condition condition) {
        this.queue = queue;
        this.lock = lock;
        this.notEmpty = condition;
    }

    @Override
    public void run() {
        System.out.println("Consumer Started");
        while (true) {
            try {
                lock.lock();
                while (queue.size() == 0) {
                    notEmpty.await();
                }
                System.out.println(String.format("Consumer polled %d value from the queue", queue.poll()));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }

        }
    }

}

