package com.dataart.task4.scheduledfuture;

import com.dataart.utils.SysUtil;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    public static final int DELAY = 5;

    public static void main(String[] args) {
        System.out.println("Will start after " + DELAY + " seconds");
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.schedule(new Task(), DELAY, TimeUnit.SECONDS);
        executor.shutdown();
        SysUtil.sleep(15);
    }

}
