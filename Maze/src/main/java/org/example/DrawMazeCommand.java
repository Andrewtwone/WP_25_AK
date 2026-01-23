package org.example;

import java.util.Set;
import java.util.function.Consumer;

/**
 * Command pattern: Concrete command for drawing a maze.
 * Encapsulates the maze creation logic.
 */
public class DrawMazeCommand implements Command {
    
    private MazeFactory factory;
    private Consumer<Maze> mazeSetter;
    private Runnable redrawAction;
    private Maze previousMaze;
    private Maze newMaze;
    
    public DrawMazeCommand(Set<Integer> bombRooms, Consumer<Maze> mazeSetter, Runnable redrawAction) {
        this.factory = new BombedMazeFactory(bombRooms);
        this.mazeSetter = mazeSetter;
        this.redrawAction = redrawAction;
    }
    
    @Override
    public void execute() {
        // Store previous maze for undo
        // Note: In a real scenario, we'd need a way to get current maze
        // For now, we'll create a new maze
        newMaze = new Maze(3, 3, 50, 50, factory);
        mazeSetter.accept(newMaze);
        redrawAction.run();
    }
    
    @Override
    public void undo() {
        if (previousMaze != null) {
            mazeSetter.accept(previousMaze);
            redrawAction.run();
        }
    }
}
