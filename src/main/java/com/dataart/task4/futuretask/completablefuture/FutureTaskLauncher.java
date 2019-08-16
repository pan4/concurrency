package com.dataart.task4.futuretask.completablefuture;

import com.dataart.utils.SysUtil;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class FutureTaskLauncher {
    private static final int SPACECRAFTS_NUMBER = 5;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(SPACECRAFTS_NUMBER);
        CompletableFuture<?>[] launchers = IntStream.range(0, SPACECRAFTS_NUMBER)
                .mapToObj(n -> launchSpaceCraft(prepareSpaceCraft(n, executor)))
                .toArray(CompletableFuture<?>[]::new);
        CompletableFuture.allOf(launchers).join();
        executor.shutdown();
        try {
            if(executor.awaitTermination(1, TimeUnit.MINUTES)){
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static CompletableFuture<SpaceCraft> prepareSpaceCraft(int number, Executor executor) {
        return CompletableFuture.supplyAsync(() -> {
            SysUtil.sleepRandom(2, 5);
            SpaceCraft spaceCraft = new SpaceCraft("SpaceCraft" + number);
            System.out.println(spaceCraft.getName() + " has been prepared");
            return spaceCraft;
        }, executor);
    }

    private static CompletableFuture<Void> launchSpaceCraft(CompletableFuture<SpaceCraft> futureSpaceCraft) {
        return futureSpaceCraft.thenAccept(spaceCraft -> System.out.println(spaceCraft.getName() + " is launching"));
    }
}

class SpaceCraft {
    String name;

    public SpaceCraft(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
