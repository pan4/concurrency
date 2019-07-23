package com.dataart.task1.threads;

public class HouseBuilder {
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

    public House build(){
        walls.setAntecedent(foundation);
        roof.setAntecedent(walls);

        foundation.start();
        walls.start();
        roof.start();

        try {
            roof.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new House(foundation, walls, roof);
    }
}
