package org.example;

import java.awt.*;

public class BombedRoom extends Room {

    private boolean detonated = false;

    public BombedRoom(int nr, int x, int y) {
        super(nr, x, y);
    }

    public boolean isDetonated() {
        return detonated;
    }

    public void detonate() {
        detonated = true;
    }

    @Override
    public void draw(Image image) {
        super.draw(image);

        // mały znacznik bomby w pokoju (kółko)
        Graphics g = image.getGraphics();
        int cx = getX() + ROOM_SIZE / 2;
        int cy = getY() + ROOM_SIZE / 2 + 12;

        g.setColor(detonated ? Color.RED : Color.BLACK);
        g.fillOval(cx - 5, cy - 5, 10, 10);
    }
}
