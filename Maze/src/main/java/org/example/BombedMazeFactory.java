package org.example;

import java.util.Set;

public class BombedMazeFactory implements MazeFactory {

    private final Set<Integer> bombRooms; // np. {5, 9}

    public BombedMazeFactory(Set<Integer> bombRooms) {
        this.bombRooms = bombRooms;
    }

    @Override
    public Room makeRoom(int nr, int x, int y) {
        if (bombRooms.contains(nr)) return new BombedRoom(nr, x, y);
        return new Room(nr, x, y);
    }

    @Override
    public Wall makeWall(int x, int y, Direction dir) {
        return new BombedWall(x, y, dir, false);
    }

    @Override
    public Door makeDoor(Room r1, Room r2, Direction dirFrom1To2) {
        return new Door(r1, r2, dirFrom1To2);
    }
}
