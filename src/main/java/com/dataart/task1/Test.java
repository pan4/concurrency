package com.dataart.task1;

import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) {

        Thread loop = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            while (!Thread.interrupted()) {
                                try {
                                    TimeUnit.MILLISECONDS.sleep(100);
                                } catch (InterruptedException e) {
//                                    Thread.currentThread().interrupt();
//                                    throw new RuntimeException(e);
                                }
                            }
                        } catch (RuntimeException e){
                            System.out.println(Thread.interrupted());
                        }
                    }
                }
        );
        loop.start();

        try {
            TimeUnit.SECONDS.sleep(1);
            loop.interrupt();
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(loop.isInterrupted());
    }
}
