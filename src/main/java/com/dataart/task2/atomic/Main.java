package com.dataart.task2.atomic;

import com.dataart.task2.atomic.counters.AtomicCounter;
import com.dataart.task2.atomic.counters.Counter;
import com.dataart.task2.atomic.counters.LongAdderCounter;
import com.dataart.task2.atomic.counters.SynchronizeCounter;
import com.dataart.task2.atomic.counters.SynchronizeThisCounter;
import com.dataart.task2.atomic.counters.UnsafeCounter;
import com.dataart.task2.atomic.counters.VolatileCounter;
import com.dataart.utils.SysUtil;

public class Main {

    private static final int INCREMENT_COUNT = 9000;
    private static long startTime;
    private static long endTime;

    public static void main(String[] args) {
        test(new SynchronizeCounter());
        test(new AtomicCounter());
        test(new LongAdderCounter());
//        test(new SynchronizeThisCounter());
//        test(new UnsafeCounter());
//        test(new VolatileCounter());
    }


    private static void test(Counter counter) {
        Thread thread[] = new Thread[INCREMENT_COUNT];

        for (int i = 0; i < INCREMENT_COUNT; i++) {
            thread[i] = new Thread(new Task(counter), "Thread " + i);
        }

        startTime = System.currentTimeMillis();

        for (int i = 0; i < INCREMENT_COUNT; i++) {
            thread[i].start();
        }

        for (int i = 0; i < INCREMENT_COUNT; i++) {
            try {
                thread[i].join();
            } catch (InterruptedException e) {
                Thread.interrupted();
                throw new RuntimeException(e);
            }
        }

        endTime = System.currentTimeMillis();

        System.out.println("Expected = " + INCREMENT_COUNT + " " + counter.getClass().getSimpleName() + " = " + counter.getValue());
        System.out.println(counter.getClass().getSimpleName() + " = " + (endTime - startTime) + " milliseconds\n");
    }

}
