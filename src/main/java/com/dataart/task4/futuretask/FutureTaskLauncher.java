package com.dataart.task4.futuretask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class FutureTaskLauncher {

    public static void main(String[] args) {
        List<SpaceCraft> spaceCrafts = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            spaceCrafts.add(new SpaceCraft());
        }
        ExecutorService executor = Executors.newCachedThreadPool();
        try {
            List<Future<String>> futures = executor.invokeAll(spaceCrafts);
            for (Future<String> future : futures) {
                System.out.println(future.get());
            }
            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
