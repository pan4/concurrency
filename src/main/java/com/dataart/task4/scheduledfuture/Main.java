package com.dataart.task4.scheduledfuture;

import com.dataart.utils.SysUtil;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    public static final int DELAY = 5;

    public static void main(String[] args) {
        System.out.println(String.format("Will start after %d seconds. Current time: %s", DELAY, new Date()));
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.schedule(new Task(), DELAY, TimeUnit.SECONDS);
        executor.shutdown();
        try {
            if(!executor.awaitTermination(1, TimeUnit.MINUTES)){
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }

}
