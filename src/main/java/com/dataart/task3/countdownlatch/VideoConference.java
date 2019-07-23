package com.dataart.task3.countdownlatch;


import java.util.concurrent.CountDownLatch;

public class VideoConference implements Runnable {

    private CountDownLatch controller;

    public VideoConference(int number) {
        controller = new CountDownLatch(number);
    }

    public void arrive(String name) {
        System.out.printf("%s has arrived \n", name);
        controller.countDown();
    }

    public void run() {
        System.out.printf("Waiting for %d participants \n", controller.getCount());
        try {
            controller.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Conference is starting ...");
    }

}
