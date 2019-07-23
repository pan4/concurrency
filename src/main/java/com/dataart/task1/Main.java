package com.dataart.task1;

import com.dataart.task1.threads.Foundation;
import com.dataart.task1.threads.House;
import com.dataart.task1.threads.Roof;
import com.dataart.task1.threads.Walls;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        long start = System.nanoTime();

        House.builder()
                .foundation(new Foundation())
                .walls(new Walls())
                .roof(new Roof())
                .build();

        long end = System.nanoTime();
        long elapsedTime = end - start;

        System.out.println("It took " + TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS) + " sec");
    }
}
