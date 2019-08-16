package com.dataart.task4.futuretask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class FutureTaskLauncher {
    private static final int SPACECRAFTS_NUMBER = 5;

    public static void main(String[] args) {
        List<SpaceCraft> spaceCrafts = new ArrayList<>();
        for (int i = 0; i < SPACECRAFTS_NUMBER; i++) {
            spaceCrafts.add(new SpaceCraft());
        }
        /**
         * I have relied on https://youtu.be/Zm3OgyQfDTU?t=383 piece of video. But I watched it once again and chose
         * FixedThreadPool because we had determined number of task with random payload.
         */
        ExecutorService executor = Executors.newFixedThreadPool(SPACECRAFTS_NUMBER);
        try {
            /**
             * ExecutorService.submit() returns a Future representing the task while
             * Executor.execute() returns void. (P.s Actually the question wasn't clear for me because of that
             * part "executor.execute(invokeAll)" )
             */
            List<Future<String>> futures = executor.invokeAll(spaceCrafts);
            for (Future<String> future : futures) {
                System.out.println(future.get());
            }
            executor.shutdown();
            if(executor.awaitTermination(1, TimeUnit.DAYS)){
                executor.shutdownNow();
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}
