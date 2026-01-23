package org.example;

import java.util.function.Supplier;

/**
 * Command pattern: Concrete command for detonating bombs.
 * Encapsulates the bomb detonation logic.
 */
public class DetonateBombsCommand implements Command {
    
    private Supplier<Maze> mazeGetter;
    private Runnable redrawAction;
    private boolean executed = false;
    
    public DetonateBombsCommand(Supplier<Maze> mazeGetter, Runnable redrawAction) {
        this.mazeGetter = mazeGetter;
        this.redrawAction = redrawAction;
    }
    
    @Override
    public void execute() {
        Maze maze = mazeGetter.get();
        if (maze != null) {
            maze.detonateBombRooms();
            redrawAction.run();
            executed = true;
        }
    }
    
    @Override
    public void undo() {
        // Note: Undoing detonation would require storing previous state
        // For simplicity, we'll just note that undo is not fully implemented
        // In a real scenario, we'd need to track which rooms/walls were detonated
        if (executed) {
            System.out.println("Cannot undo detonation - state not preserved");
        }
    }
}
