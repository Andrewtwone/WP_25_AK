package org.example;

import java.awt.*;

public class Door extends MapSite {

    private Room room1;
    private Room room2;
    private boolean isOpen = true;
    private Direction direction;

    public Door(Room room1, Room room2, Direction direction) {
        super(computeX(room1, room2, direction), computeY(room1, room2, direction));
        this.room1 = room1;
        this.room2 = room2;
        this.direction = direction;
    }

    public void setDoorState(boolean open) {
        isOpen = open;
    }

    private static int computeX(Room r1, Room r2, Direction dir) {
        // pozycja drzwi – na wspólnej ścianie
        return switch (dir) {
            case NORTH, SOUTH -> r1.getX();
            case EAST -> Math.max(r1.getX(), r2.getX());
            case WEST -> Math.min(r1.getX(), r2.getX());
        };
    }

    private static int computeY(Room r1, Room r2, Direction dir) {
        return switch (dir) {
            case EAST, WEST -> r1.getY();
            case SOUTH -> Math.max(r1.getY(), r2.getY());
            case NORTH -> Math.min(r1.getY(), r2.getY());
        };
    }

    @Override
    public void draw(Image image) {
        Graphics g = image.getGraphics();
        g.setColor(isOpen ? Color.RED : Color.GRAY);

        int offset = ROOM_SIZE / 4; // długość przerwy

        switch (direction) {
            case NORTH, SOUTH -> {
                int yLine = y;
                g.drawLine(x, yLine, x + offset, yLine);
                g.drawLine(x + ROOM_SIZE - offset, yLine, x + ROOM_SIZE, yLine);
            }

            case WEST, EAST -> {
                int xLine = x;
                g.drawLine(xLine, y, xLine, y + offset);
                g.drawLine(xLine, y + ROOM_SIZE - offset, xLine, y + ROOM_SIZE);
            }

        }
    }
}