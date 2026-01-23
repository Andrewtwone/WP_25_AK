package org.example.builder;

import org.example.factory.MazeFactory;
import org.example.maze.Direction;
import org.example.maze.Maze;

import java.util.ArrayList;
import java.util.List;


public class MazeBuilder {
    
    private int rows = 3;
    private int cols = 3;
    private int startX = 50;
    private int startY = 50;
    private MazeFactory factory;
    private List<Connection> connections = new ArrayList<>();
    private List<Entrance> entrances = new ArrayList<>();
    
    private static class Connection {
        int r1, c1, r2, c2;
        Direction dir;
        Connection(int r1, int c1, int r2, int c2, Direction dir) {
            this.r1 = r1; this.c1 = c1; this.r2 = r2; this.c2 = c2; this.dir = dir;
        }
    }
    
    private static class Entrance {
        int row, col;
        Direction side;
        Entrance(int row, int col, Direction side) {
            this.row = row; this.col = col; this.side = side;
        }
    }
    
    public MazeBuilder setDimensions(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        return this;
    }
    
    public MazeBuilder setPosition(int startX, int startY) {
        this.startX = startX;
        this.startY = startY;
        return this;
    }
    
    public MazeBuilder setFactory(MazeFactory factory) {
        this.factory = factory;
        return this;
    }
    
    public MazeBuilder addConnection(int r1, int c1, int r2, int c2, Direction dir) {
        connections.add(new Connection(r1, c1, r2, c2, dir));
        return this;
    }
    
    public MazeBuilder addEntrance(int row, int col, Direction side) {
        entrances.add(new Entrance(row, col, side));
        return this;
    }
    
    public Maze build() {
        if (factory == null) {
            throw new IllegalStateException("Factory must be set before building");
        }
        
        Maze maze = new Maze(cols, rows, startX, startY, factory);

        for (Connection conn : connections) {
            maze.connectRooms(conn.r1, conn.c1, conn.r2, conn.c2, conn.dir);
        }

        for (Entrance entrance : entrances) {
            maze.setEntrance(entrance.row, entrance.col, entrance.side);
        }
        
        return maze;
    }
}
