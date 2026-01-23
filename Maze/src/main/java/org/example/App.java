package org.example;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {

    private JMyPanel panel;
    private Maze maze;
    private CommandInvoker commandInvoker; // Command pattern

    public App() {
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JMyPanel();
        commandInvoker = new CommandInvoker();

        JButton drawBtn = new JButton("Draw maze");
        JButton detonateBtn = new JButton("Detonate bombs");
        JButton undoBtn = new JButton("Undo");

        // Command pattern: Use command objects instead of direct actions
        drawBtn.addActionListener(e -> {
            Command drawCommand = new DrawMazeCommand(
                java.util.Set.of(5, 9),
                m -> maze = m,
                this::redraw
            );
            commandInvoker.executeCommand(drawCommand);
            
            // Observer pattern: Add observer to log bomb detonations
            if (maze != null) {
                maze.addBombObserver(new BombDetonationLogger());
            }
        });

        detonateBtn.addActionListener(e -> {
            Command detonateCommand = new DetonateBombsCommand(
                () -> maze,
                this::redraw
            );
            commandInvoker.executeCommand(detonateCommand);
        });

        undoBtn.addActionListener(e -> {
            commandInvoker.undo();
        });
        undoBtn.setEnabled(true);

        setLayout(new BorderLayout());

        JPanel menuPanel = new JPanel(new GridLayout(1, 3));
        menuPanel.add(drawBtn);
        menuPanel.add(detonateBtn);
        menuPanel.add(undoBtn);

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
