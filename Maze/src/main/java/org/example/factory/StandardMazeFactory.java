package org.example.factory;

import org.example.maze.Direction;
import org.example.maze.Room;
import org.example.maze.Wall;
import org.example.maze.Door;

public class StandardMazeFactory implements MazeFactory {

    @Override
    public Room makeRoom(int nr, int x, int y) {
        return new Room(nr, x, y);
    }

    @Override
    public Wall makeWall(int x, int y, Direction dir) {
        return new Wall(x, y, dir);
    }

    @Override
    public Door makeDoor(Room r1, Room r2, Direction dirFrom1To2) {
        return new Door(r1, r2, dirFrom1To2);
    }
}

