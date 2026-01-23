package org.example;

/**
 * Observer interface for bomb detonation events.
 * Observers are notified when bombs are detonated in the maze.
 */
public interface BombObserver {
    void onBombDetonated(BombedRoom room);
}
