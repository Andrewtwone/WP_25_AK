package org.example;

import java.awt.*;

public class BombedWall extends Wall {

    private boolean detonated;

    public BombedWall(int x, int y, Direction d, boolean detonated) {
        super(x, y, d);
        this.detonated = detonated;
    }

    public void setDetonated(boolean detonated) {
        this.detonated = detonated;
    }

    public boolean isDetonated() {
        return detonated;
    }

    @Override
    public void draw(Image image) {
        if (!detonated) {
            super.draw(image);
            return;
        }

        Graphics2D g = (Graphics2D) image.getGraphics();

        int x0 = getX();
        int y0 = getY();
        int gap = ROOM_SIZE / 3;
        int seg = (ROOM_SIZE - gap) / 2;
        int pad = ROOM_SIZE / 10;

        g.setColor(Color.BLACK);

        switch (getDirection()) {
            case NORTH -> {
                int y = y0;

                g.drawLine(x0, y, x0 + seg, y);
                g.drawLine(x0 + seg + gap, y, x0 + ROOM_SIZE, y);

                drawRubble(g, x0 + seg, y - pad, gap, pad * 2);
            }
            case SOUTH -> {
                int y = y0 + ROOM_SIZE;
                g.drawLine(x0, y, x0 + seg, y);
                g.drawLine(x0 + seg + gap, y, x0 + ROOM_SIZE, y);

                drawRubble(g, x0 + seg, y - pad, gap, pad * 2);
            }
            case WEST -> {
                int x = x0;
                g.drawLine(x, y0, x, y0 + seg);
                g.drawLine(x, y0 + seg + gap, x, y0 + ROOM_SIZE);

                drawRubble(g, x - pad, y0 + seg, pad * 2, gap);
            }
            case EAST -> {
                int x = x0 + ROOM_SIZE;
                g.drawLine(x, y0, x, y0 + seg);
                g.drawLine(x, y0 + seg + gap, x, y0 + ROOM_SIZE);

                drawRubble(g, x - pad, y0 + seg, pad * 2, gap);
            }
        }
    }

    private void drawRubble(Graphics2D g, int rx, int ry, int rw, int rh) {
        g.setColor(new Color(220, 40, 40));

        // rubble dots
        int dots = 6;
        for (int i = 0; i < dots; i++) {
            int dx = rx + (int) (Math.random() * rw);
            int dy = ry + (int) (Math.random() * rh);
            g.drawLine(dx, dy, dx + 3, dy + 2);
        }
    }

}
