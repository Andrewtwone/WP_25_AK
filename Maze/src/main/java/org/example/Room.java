package org.example;

import java.awt.*;

public class Room extends MapSite {
    private int nr;
    private MapSite[] sides = new MapSite[4];  // NORTH, EAST, SOUTH, WEST

    public Room(int nr, int x, int y) {
        super(x, y);
        this.nr = nr;
    }

    public void setSide(Direction d, MapSite site) {
        sides[d.ordinal()] = site;
    }

    public MapSite getSide(Direction d) {
        return sides[d.ordinal()];
    }

    @Override
    public void draw(Image image) {
        // rysujemy wszystkie ściany / drzwi
        for (MapSite side : sides) {
            if (side != null) {
                side.draw(image);
            }
        }

        // numer pokoju w środku
        Graphics g = image.getGraphics();
        g.setColor(Color.DARK_GRAY);
        g.drawString(
                Integer.toString(nr),
                x + ROOM_SIZE / 2 - 5,
                y + ROOM_SIZE / 2
        );
    }
}
