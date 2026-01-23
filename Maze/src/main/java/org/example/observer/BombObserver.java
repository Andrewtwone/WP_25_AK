package org.example.observer;

import org.example.maze.BombedRoom;

public interface BombObserver {
    void onBombDetonated(BombedRoom room);
}
