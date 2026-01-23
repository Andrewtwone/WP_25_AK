# Design Patterns Implementation Explanation

This document explains the 4 design patterns added to the Maze project: **Observer**, **Builder**, **Decorator**, and **Command**.

---

## 1. Observer Pattern

### What I Did:
- Created `BombObserver` interface - defines the contract for observers
- Created `BombDetonationLogger` - concrete observer that logs bomb explosions
- Modified `Maze` class to maintain a list of observers and notify them when bombs detonate

### Files Created/Modified:
- `BombObserver.java` - Observer interface
- `BombDetonationLogger.java` - Concrete observer implementation
- `Maze.java` - Added observer management (add/remove/notify methods)
- `App.java` - Registers the logger observer when maze is created

### Why This Pattern:
The Observer pattern allows the Maze to notify multiple components when bombs explode, without the Maze needing to know what those components are. This is useful for:
- **Separation of concerns**: Maze doesn't need to know about logging, UI updates, scoring, etc.
- **Extensibility**: Easy to add new observers (e.g., sound effects, score tracking) without modifying Maze
- **Loose coupling**: Observers and subject (Maze) are independent

### How It Works:
1. When a bomb detonates, `Maze.detonateBombRooms()` calls `notifyBombObservers()`
2. All registered observers receive the `onBombDetonated()` callback
3. Each observer can react independently (log, play sound, update score, etc.)

---

## 2. Builder Pattern

### What I Did:
- Created `MazeBuilder` class - provides a fluent interface for constructing mazes step-by-step
- Made `Maze.connectRooms()` and `Maze.setEntrance()` public so Builder can use them
- Builder allows setting dimensions, position, factory, connections, and entrances before building

### Files Created/Modified:
- `MazeBuilder.java` - Builder class with fluent interface
- `Maze.java` - Made some methods public for Builder access

### Why This Pattern:
The Builder pattern simplifies complex object construction:
- **Readability**: Clear, step-by-step construction instead of a huge constructor
- **Flexibility**: Can set only the parameters you need, in any order
- **Maintainability**: Adding new construction options doesn't require changing constructor signatures
- **Reusability**: Can create different maze configurations easily

### How It Works:
```java
Maze maze = new MazeBuilder()
    .setDimensions(3, 3)
    .setPosition(50, 50)
    .setFactory(factory)
    .addConnection(0, 0, 0, 1, Direction.EAST)
    .addEntrance(0, 0, Direction.WEST)
    .build();
```

The Builder accumulates configuration and then creates the Maze with all settings applied.

---

## 3. Decorator Pattern

### What I Did:
- Created `MapSiteDecorator` - abstract base decorator for MapSite objects
- Created `ColoredWallDecorator` - adds colored borders to walls
- Created `HighlightedDoorDecorator` - adds highlight circles to doors

### Files Created:
- `MapSiteDecorator.java` - Abstract decorator base class
- `ColoredWallDecorator.java` - Decorates walls with colored borders
- `HighlightedDoorDecorator.java` - Decorates doors with highlights

### Why This Pattern:
The Decorator pattern allows adding features to objects dynamically:
- **Flexibility**: Can add/remove visual features without modifying original classes
- **Composition over inheritance**: Can combine multiple decorators (e.g., colored + highlighted)
- **Open/Closed Principle**: Open for extension (new decorators), closed for modification (original classes)
- **Single Responsibility**: Each decorator adds one specific feature

### How It Works:
```java
// Original wall
Wall wall = new Wall(x, y, Direction.NORTH);

// Decorated wall with color
MapSite decorated = new ColoredWallDecorator(wall, Color.BLUE, 3);

// Can even stack decorators
MapSite doubleDecorated = new HighlightedDoorDecorator(
    new ColoredWallDecorator(wall, Color.BLUE, 3), 
    Color.YELLOW
);
```

The decorator wraps the original object and adds its own drawing on top.

---

## 4. Command Pattern

### What I Did:
- Created `Command` interface - defines execute() and undo() methods
- Created `DrawMazeCommand` - encapsulates maze creation
- Created `DetonateBombsCommand` - encapsulates bomb detonation
- Created `CommandInvoker` - manages command execution and undo history
- Modified `App.java` - uses commands instead of direct button actions

### Files Created/Modified:
- `Command.java` - Command interface
- `DrawMazeCommand.java` - Command for drawing maze
- `DetonateBombsCommand.java` - Command for detonating bombs
- `CommandInvoker.java` - Command manager with undo support
- `App.java` - Uses Command pattern for button actions

### Why This Pattern:
The Command pattern encapsulates requests as objects:
- **Undo/Redo**: Can reverse operations (though full undo requires state preservation)
- **Queuing**: Can queue commands for later execution
- **Logging**: Can log all commands for audit trails
- **Macro commands**: Can combine multiple commands into one
- **Decoupling**: Button doesn't need to know what action it triggers

### How It Works:
1. Each button action creates a Command object
2. Command is passed to CommandInvoker
3. Invoker executes command and stores it in history
4. Undo button pops command from history and calls undo()

---

## Summary for Your Teacher

**Observer Pattern**: Used to notify components (like loggers) when bombs explode. The Maze doesn't need to know about these components - they just register themselves and get notified automatically.

**Builder Pattern**: Used to construct Maze objects step-by-step with a fluent interface. Makes maze creation more readable and flexible than a complex constructor.

**Decorator Pattern**: Used to add visual enhancements (colors, highlights) to walls and doors dynamically. Allows combining multiple visual features without modifying original classes.

**Command Pattern**: Used to encapsulate button actions (draw maze, detonate bombs) as objects. Enables undo functionality and better separation between UI and business logic.

All patterns follow SOLID principles and improve code maintainability, extensibility, and flexibility.
