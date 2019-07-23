package com.dataart.task1.threads;

public class House {
    private final Foundation foundation;
    private final Walls walls;
    private final Roof roof;

    public House(Foundation foundation, Walls walls, Roof roof){
        this.foundation = foundation;
        this.walls = walls;
        this.roof = roof;

        System.out.println("House has been built");
    }

    public static HouseBuilder builder(){
        return new HouseBuilder();
    }
}
