package org.example.command;

import java.util.Stack;


public class CommandInvoker {
    
    private Stack<Command> history = new Stack<>();
    private final int maxHistorySize = 10;
    
    public void executeCommand(Command command) {
        command.execute();
        history.push(command);

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
