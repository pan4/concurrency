package com.dataart.task5.nonblocking.time.wast;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {

    public static void main(String[] args) {
        Queue<Integer> queue = new ConcurrentLinkedQueue<>();

        Thread producer1 = new Thread(new Producer1(queue));
        Thread producer2 = new Thread(new Producer2(queue));
        Thread consumer = new Thread(new Consumer(queue));

        producer1.start();
        producer2.start();
        consumer.start();
    }

}
