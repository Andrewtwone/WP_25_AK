package org.example;

import javax.swing.*;
import java.awt.*;

public class JMyPanel extends JPanel {

    private Image image;

    public Image getImage() {
        if (image == null) {
            image = createImage(getWidth(), getHeight());
        }
        return image;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (image != null) {
            g.drawImage(image, 0, 0, this);
        }
    }
}