package org.example;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {

    private JMyPanel panel;
    private Maze maze;

    public App() {
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JMyPanel();

        JButton drawBtn = new JButton("Draw maze");
        JButton detonateBtn = new JButton("Detonate bombs");

        drawBtn.addActionListener(e -> {
            MazeFactory factory = new BombedMazeFactory(java.util.Set.of(5, 9));
            maze = new Maze(3, 3, 50, 50, factory);
            redraw();
        });

        detonateBtn.addActionListener(e -> {
            if (maze == null) return;
            maze.detonateBombRooms();
            redraw();
        });

        setLayout(new BorderLayout());

        JPanel menuPanel = new JPanel(new GridLayout(1, 2));
        menuPanel.add(drawBtn);
        menuPanel.add(detonateBtn);

        add(menuPanel, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
    }

    private void redraw() {
        Image image = panel.getImage();
        Graphics g = image.getGraphics();

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, panel.getWidth(), panel.getHeight());

        if (maze != null) {
            maze.draw(image);
        }
        panel.repaint();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new App().setVisible(true));
    }
}
