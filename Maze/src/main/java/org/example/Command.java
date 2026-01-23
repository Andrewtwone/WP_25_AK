package org.example;

/**
 * Command pattern: Interface for encapsulating requests as objects.
 * This allows parameterizing clients with different requests, queuing operations,
 * and supporting undo/redo functionality.
 */
public interface Command {
    void execute();
    void undo();
}
