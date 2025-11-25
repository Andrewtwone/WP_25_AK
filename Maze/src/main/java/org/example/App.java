package org.example;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {

    private JMyPanel panel;

    public App() {
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JMyPanel();
        JButton button = new JButton("Draw maze");

        button.addActionListener(e -> {
            Image image = panel.getImage();
            Graphics g = image.getGraphics();

            // tło
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, panel.getWidth(), panel.getHeight());

            // tworzymy labirynt 3x3, start rysowania od (50,50)
            Maze maze = new Maze(3, 3, 50, 50);
            maze.draw(image);

            panel.repaint();
        });

        setLayout(new BorderLayout());
        JPanel menuPanel = new JPanel(new GridLayout(1, 1));
        menuPanel.add(button);
        add(menuPanel, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new App().setVisible(true));
    }
}