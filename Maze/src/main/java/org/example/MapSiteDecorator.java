package org.example;

import java.awt.*;

/**
 * Decorator pattern: Base decorator for MapSite objects.
 * Allows adding visual enhancements (colors, effects) to walls, doors, etc.
 */
public abstract class MapSiteDecorator extends MapSite {
    
    protected MapSite decoratedMapSite;
    
    public MapSiteDecorator(MapSite mapSite) {
        super(mapSite.getX(), mapSite.getY());
        this.decoratedMapSite = mapSite;
    }
    
    @Override
    public void draw(Image image) {
        decoratedMapSite.draw(image);
        drawDecoration(image);
    }
    
    protected abstract void drawDecoration(Image image);
}
