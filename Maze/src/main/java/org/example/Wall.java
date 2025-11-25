package org.example;

import java.awt.*;

public class Wall extends MapSite {

    private Direction direction;

    public Wall(int x, int y, Direction d) {
        super(x, y);
        this.direction = d;
    }

    @Override
    public void draw(Image image) {
        Graphics g = image.getGraphics();
        g.setColor(Color.BLACK);

        switch (direction) {
            case NORTH -> g.drawLine(x, y, x + ROOM_SIZE, y);
            case SOUTH -> g.drawLine(x, y + ROOM_SIZE, x + ROOM_SIZE, y + ROOM_SIZE);
            case WEST  -> g.drawLine(x, y, x, y + ROOM_SIZE);
            case EAST  -> g.drawLine(x + ROOM_SIZE, y, x + ROOM_SIZE, y + ROOM_SIZE);
        }
    }
}
