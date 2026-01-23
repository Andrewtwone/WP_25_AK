package org.example.decorator;

import org.example.maze.MapSite;
import java.awt.*;

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
