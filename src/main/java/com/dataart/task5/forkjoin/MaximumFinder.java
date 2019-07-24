package com.dataart.task5.forkjoin;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public class MaximumFinder extends RecursiveTask<Integer> {
    private static int[] data;
    private static int threshold;
    private final int first;
    private final int last;

    public MaximumFinder(int first, int last, int threshold) {
        this.threshold = threshold;
        this.first = first;
        this.last = last;
    }

    public static void main(String[] args) {
        data = new int[1_000_000];
        initData(data);
        measure(4);
        measure(40);
        measure(80);
        measure(160);
    }

    private static void measure(int parallelism) {
        long start = System.nanoTime();

        find(parallelism);

        long end = System.nanoTime();
        long elapsedTime = end - start;

        System.out.printf("With parallelism %d it took %d microseconds \n", parallelism,
                TimeUnit.MICROSECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS));
    }

    private static void find(int parallelism) {
        final ForkJoinPool pool = new ForkJoinPool(parallelism);
        MaximumFinder task = new MaximumFinder(0, data.length, 3000);
        pool.submit(task);
        pool.shutdown();
        try {
            pool.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Integer compute() {
        int result = data[0];
        if (last - first < threshold) {
            result = findMax();
        } else {
            int middle = (last + first) / 2;
            MaximumFinder t1 = new MaximumFinder(first, middle, threshold);
            MaximumFinder t2 = new MaximumFinder(middle, last, threshold);
            invokeAll(t1, t2);
            try {
                result = t1.get() >= t2.get() ? t1.get() : t2.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private int findMax() {
        int max = data[first];
        for (int i = first + 1; i < last; i++) {
            max = data[i] > max ? data[i] : max;
        }
        return max;
    }

    private static void initData(int[] data) {
        final Random random = new Random();
        for (int i = 0; i < data.length; i++) {
            data[i] = random.nextInt(888);
        }
    }
}