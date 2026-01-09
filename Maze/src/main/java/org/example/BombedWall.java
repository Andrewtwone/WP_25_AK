package org.example;

import java.awt.*;

public class BombedWall extends Wall {

    private boolean isDetonated;

    public BombedWall(int x, int y, Direction d, boolean isDetonated) {
        super(x, y, d);
        this.isDetonated = isDetonated;
    }

    public boolean isDetonated() {
        return isDetonated;
    }

    public void setDetonated(boolean detonated) {
        isDetonated = detonated;
    }

    @Override
    public void draw(Image image) {
        // ściana bazowa
        super.draw(image);

        // jeśli zrujnowana, to rysujemy "poszarpaną" czerwoną nakładkę
        if (!isDetonated) return;

        Graphics2D g = (Graphics2D) image.getGraphics();
        g.setColor(Color.RED);

        int delta = 4;             // amplituda poszarpania
        int pieces = 6;            // ile "zębów"
        int step = ROOM_SIZE / pieces;

        int x0 = getX();
        int y0 = getY();

        switch (getDirection()) {
            case NORTH -> drawBrokenHorizontal(g, x0, y0, step, delta);
            case SOUTH -> drawBrokenHorizontal(g, x0, y0 + ROOM_SIZE, step, delta);
            case WEST  -> drawBrokenVertical(g, x0, y0, step, delta);
            case EAST  -> drawBrokenVertical(g, x0 + ROOM_SIZE, y0, step, delta);
        }
    }

    private void drawBrokenHorizontal(Graphics2D g, int x, int y, int step, int delta) {
        int curX = x;
        int sign = 1;

        for (int i = 0; i < 6; i++) {
            int nextX = curX + step;
            int offY = y + sign * delta;

            // krótkie odcinki z przerwami
            int a = curX + step / 6;
            int b = nextX - step / 6;
            g.drawLine(a, offY, b, y);

            curX = nextX;
            sign *= -1;
        }
    }

    private void drawBrokenVertical(Graphics2D g, int x, int y, int step, int delta) {
        int curY = y;
        int sign = 1;

        for (int i = 0; i < 6; i++) {
            int nextY = curY + step;
            int offX = x + sign * delta;

            int a = curY + step / 6;
            int b = nextY - step / 6;
            g.drawLine(offX, a, x, b);

            curY = nextY;
            sign *= -1;
        }
    }
}
