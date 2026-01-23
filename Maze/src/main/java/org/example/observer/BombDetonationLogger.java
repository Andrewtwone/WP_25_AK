package org.example.observer;

import org.example.maze.BombedRoom;

public class BombDetonationLogger implements BombObserver {

    @Override
    public void onBombDetonated(BombedRoom room) {
        System.out.println("💣 BOMB DETONATED in Room #" + room.getNr() +
                " at position (" + room.getX() + ", " + room.getY() + ")");
    }
}
