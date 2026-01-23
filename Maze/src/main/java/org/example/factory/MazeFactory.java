package org.example.factory;

import org.example.maze.Direction;
import org.example.maze.Room;
import org.example.maze.Wall;
import org.example.maze.Door;

public interface MazeFactory {
    Room makeRoom(int nr, int x, int y);
    Wall makeWall(int x, int y, Direction dir);
    Door makeDoor(Room r1, Room r2, Direction dirFrom1To2);
}

