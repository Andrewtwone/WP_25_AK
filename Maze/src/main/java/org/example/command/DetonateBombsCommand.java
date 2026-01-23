package org.example.command;

import org.example.maze.Maze;
import java.util.function.Supplier;

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
        if (executed) {
            System.out.println("Cannot undo detonation - state not preserved");
        }
    }
}
