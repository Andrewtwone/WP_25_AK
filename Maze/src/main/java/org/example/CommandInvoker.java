package org.example;

import java.util.Stack;

/**
 * Command pattern: Invoker that manages command execution and undo/redo.
 * This demonstrates the Command pattern's ability to queue, log, and undo operations.
 */
public class CommandInvoker {
    
    private Stack<Command> history = new Stack<>();
    private final int maxHistorySize = 10;
    
    public void executeCommand(Command command) {
        command.execute();
        history.push(command);
        
        // Limit history size
        if (history.size() > maxHistorySize) {
            history.remove(0);
        }
    }
    
    public void undo() {
        if (!history.isEmpty()) {
            Command command = history.pop();
            command.undo();
        }
    }
    
    public boolean canUndo() {
        return !history.isEmpty();
    }
}
