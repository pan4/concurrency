package com.dataart.task1.threads;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class HouseBuilder {
    private ThreadFactory factory = new HouseWorkerFactory("House worker");

    private Foundation foundation;
    private Walls walls;
    private Roof roof;

    public HouseBuilder foundation(Foundation foundation) {
        this.foundation = foundation;
        return this;
    }

    public HouseBuilder walls(Walls walls) {
        this.walls = walls;
        return this;
    }

    public HouseBuilder roof(Roof roof) {
        this.roof = roof;
        return this;
    }

    public House build() {
        Thread foundationThread = factory.newThread(foundation);
        Thread wallsThread = factory.newThread(walls);
        Thread roofThread = factory.newThread(roof);
        foundation.setWorker(foundationThread);
        walls.setWorker(wallsThread);
        roof.setWorker(roofThread);

        foundation.setNext(walls);
        walls.setPrevious(foundation);
        walls.setNext(roof);
        roof.setPrevious(walls);

        foundationThread.start();
        wallsThread.start();
        roofThread.start();

        try {
            roofThread.join();
        } catch (InterruptedException e) {
            Thread.interrupted();
            throw new RuntimeException(e);
        }

        if(roof.isIntegrated()) {
            return new House(foundation, walls, roof);
        } else {
            throw new RuntimeException("Building of house has been failed");
        }
    }
}
