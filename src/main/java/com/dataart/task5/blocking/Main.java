package com.dataart.task5.blocking;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {

    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(16);

        Thread producer1 = new Thread(new Producer1(queue));
        Thread producer2 = new Thread(new Producer2(queue));
        Thread consumer = new Thread(new Consumer(queue));

        producer1.start();
        producer2.start();
        consumer.start();
    }
}
