package com.dataart.task4.futuretask;

import com.dataart.utils.SysUtil;

import java.util.Random;
import java.util.concurrent.Callable;

public class SpaceCraft implements Callable<String> {

    @Override
    public String call() {
        SysUtil.sleepRandom(2, 5);
        Random random = new Random();
        if(random.nextBoolean()){
            throw new PreparationException();
        }
        return Thread.currentThread().getName() + " has prepared";
    }

}
