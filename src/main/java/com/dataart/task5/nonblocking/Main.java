package com.dataart.task5.nonblocking;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) {
        Queue<Integer> queue = new ConcurrentLinkedQueue<>();
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        Thread producer1 = new Thread(new ProducerNoBlocking1(queue, lock, condition));
        Thread producer2 = new Thread(new ProducerNoBlocking2(queue, lock, condition));
        Thread consumer = new Thread(new Consumer(queue, lock, condition));

        producer1.start();
        producer2.start();
        consumer.start();
    }

}
