package com.dataart.task5.deadlock;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue1 = new ArrayBlockingQueue<>(16);
        BlockingQueue<Integer> queue2 = new ArrayBlockingQueue<>(16);

        Thread worker1 = new Thread(new Worker1(queue1, queue2));
        Thread worker2 = new Thread(new Worker2(queue1, queue2));

        worker1.start();
        worker2.start();
    }
}
