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
        /**
         * scheduleAtFixedRate executes a task with the given period while scheduleWithFixedDelay executes
         * it with the given delay between the termination of one execution and the commencement of the next.
         * Description of course task says that alarm action must be called every 5 seconds thus I use
         * scheduleAtFixedRate method
         */
        executor.scheduleAtFixedRate(new PeriodicallyTask("alert system"),0, DELAY, TimeUnit.SECONDS);
        SysUtil.sleep(25);
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
