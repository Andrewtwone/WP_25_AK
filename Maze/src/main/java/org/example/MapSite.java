package org.example;

import java.awt.*;

public abstract class MapSite {

    protected int x, y;
    public static final int ROOM_SIZE = 60;

    public MapSite(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }

    public int getY() { return y; }

    public void setX(int x) { this.x = x; }

    public void setY(int y) { this.y = y; }

    public abstract void draw(Image image);
}

enum Direction {
    NORTH, EAST, SOUTH, WEST
}
