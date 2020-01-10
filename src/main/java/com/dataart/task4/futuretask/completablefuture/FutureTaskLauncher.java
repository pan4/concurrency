package com.dataart.task4.futuretask.completablefuture;

import com.dataart.task4.futuretask.PreparationException;
import com.dataart.utils.SysUtil;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Exceptional scenarios can be handled in two paradigms
 * 1. Asynchronous exception handling.
 * CompletableFuture comes with a handy tool call exceptionally()
 * I can use that to handle the exceptions happen inside the asynchronous code block.
 * Handling exception in this way can be used to generate partial results to the consumer.
 * 2. Synchronous exception handling
 * And if I do not like partial results or asynchronous type exception handling the CompletableFuture.allOf()
 * provides the handy mechanism to deal with that. The java document says.
 * Returns a new CompletableFuture that is completed when all of the given CompletableFutures complete.
 * If any of the given CompletableFutures complete exceptionally, then the returned CompletableFuture also does so,
 * with a CompletionException holding this exception as its cause.
 *
 * References: https://medium.com/@kalpads/fantastic-completablefuture-allof-and-how-to-handle-errors-27e8a97144a0
 */
public class FutureTaskLauncher {
    private static final int SPACECRAFTS_NUMBER = 5;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(SPACECRAFTS_NUMBER);

        List<CompletableFuture<SpaceCraft>> launchers = IntStream.range(0, SPACECRAFTS_NUMBER)
                .mapToObj(n -> launchSpaceCraft(prepareSpaceCraft(n, executor)))
                .collect(Collectors.toList());

        CompletableFuture<Void> allFutures = CompletableFuture
                .allOf(launchers.toArray(new CompletableFuture[launchers.size()]));

        CompletableFuture<List<SpaceCraft>> launcherSpaceCraftFutures = allFutures
                .thenApply(future -> launchers.stream()
                        .map(launcher -> launcher.join())
                        .collect(Collectors.toList()));

        try {
            launcherSpaceCraftFutures.get().stream()
                    .filter(Objects::nonNull)
                    .forEach(spaceCraft -> System.out.println(spaceCraft.getName() + " has been launched"));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
            try {
                if (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
            }
        }
    }

    private static CompletableFuture<SpaceCraft> prepareSpaceCraft(int number, Executor executor) {
        return CompletableFuture
                .supplyAsync(() -> {
                    SysUtil.sleepRandom(2, 5);
                    Random random = new Random();
                    if (random.nextBoolean()) {
                        throw new PreparationException();
                    }
                    SpaceCraft spaceCraft = new SpaceCraft("SpaceCraft" + number);
                    System.out.println(spaceCraft.getName() + " has been prepared");
                    return spaceCraft;
                }, executor);
    }

    private static CompletableFuture<SpaceCraft> launchSpaceCraft(CompletableFuture<SpaceCraft> futureSpaceCraft) {
        return futureSpaceCraft.thenApply(spaceCraft -> {
            System.out.println(spaceCraft.getName() + " is launching");
            return spaceCraft;
        }).exceptionally(ex -> {
            ex.printStackTrace();
            return null;
        });
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
