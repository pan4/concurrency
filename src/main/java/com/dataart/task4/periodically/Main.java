package com.dataart.task4.periodically;

import com.dataart.task4.scheduledfuture.Task;
import com.dataart.utils.SysUtil;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    public static final int DELAY = 5;

    public static void main(String[] args)  {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(new PeriodicallyTask("alert system"),0, DELAY, TimeUnit.SECONDS);
        SysUtil.sleep(25);
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
