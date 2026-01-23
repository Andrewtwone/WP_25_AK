package org.example;

/**
 * Concrete Observer that logs bomb detonation events.
 * This demonstrates the Observer pattern by reacting to bomb explosions.
 */
public class BombDetonationLogger implements BombObserver {
    
    @Override
    public void onBombDetonated(BombedRoom room) {
        System.out.println("💣 BOMB DETONATED in Room #" + room.getNr() + 
                         " at position (" + room.getX() + ", " + room.getY() + ")");
    }
}
