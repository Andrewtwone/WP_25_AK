package org.example.decorator;

import org.example.maze.MapSite;
import java.awt.*;

public class HighlightedDoorDecorator extends MapSiteDecorator {

    private Color highlightColor;

    public HighlightedDoorDecorator(MapSite mapSite, Color highlightColor) {
        super(mapSite);
        this.highlightColor = highlightColor;
    }

    @Override
    protected void drawDecoration(Image image) {
        Graphics2D g = (Graphics2D) image.getGraphics();
        g.setColor(highlightColor);
        g.setStroke(new BasicStroke(2));

        // Draw a small highlight circle near the door
        int centerX = getX() + ROOM_SIZE / 2;
        int centerY = getY() + ROOM_SIZE / 2;
        g.drawOval(centerX - 8, centerY - 8, 16, 16);
    }
}
