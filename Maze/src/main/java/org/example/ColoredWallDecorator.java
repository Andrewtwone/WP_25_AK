package org.example;

import java.awt.*;

/**
 * Decorator pattern: Adds a colored border/glow effect to walls.
 * This demonstrates how decorators can enhance visual appearance.
 */
public class ColoredWallDecorator extends MapSiteDecorator {
    
    private Color borderColor;
    private int borderWidth;
    
    public ColoredWallDecorator(MapSite mapSite, Color borderColor, int borderWidth) {
        super(mapSite);
        this.borderColor = borderColor;
        this.borderWidth = borderWidth;
    }
    
    @Override
    protected void drawDecoration(Image image) {
        Graphics2D g = (Graphics2D) image.getGraphics();
        g.setColor(borderColor);
        g.setStroke(new BasicStroke(borderWidth));
        
        // Draw a colored rectangle around the wall area
        int padding = 2;
        g.drawRect(getX() - padding, getY() - padding, 
                  ROOM_SIZE + padding * 2, ROOM_SIZE + padding * 2);
    }
}
