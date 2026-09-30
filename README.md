# Java 2D Drawing Application

A desktop drawing application developed in Java using Swing and the Model-View-Controller (MVC) architecture. The application supports creating and manipulating geometric shapes while demonstrating object-oriented programming principles and several software design patterns.

## Features

### Drawing Shapes
The application supports creating and displaying multiple geometric shapes:

- Point
- Line
- Rectangle
- Circle
- Donut
- Hexagon

### Shape Manipulation
Users can:

- Add new shapes
- Select shapes
- Modify existing shapes
- Delete shapes
- Change the order of shapes

### Undo and Redo
The application implements Undo and Redo functionality using the **Command design pattern**.

Executed commands are stored so that operations can be reversed and restored.

### Save and Load
Drawing data can be saved and loaded, allowing users to continue working with previously created drawings.

### Operation Logging and Replay
Application operations can be recorded in a log and replayed later, allowing previously executed drawing actions to be reproduced.

## Tech Stack

- Java
- Java Swing
- Object-Oriented Programming
- MVC Architecture
- Eclipse

## Architecture

The application follows the **Model-View-Controller (MVC)** architectural pattern:

```text
Model
  |
Controller
  |
View
```

This separation keeps application data, user interface, and application logic organized independently.

## Design Patterns

Several software design patterns are implemented throughout the application.

### Command Pattern

Used to encapsulate drawing operations as commands and provide **Undo/Redo** functionality.

### Observer Pattern

Used for communication between application components when the application state changes.

### Strategy Pattern

Used to provide interchangeable strategies for specific application behavior.

### Adapter Pattern

Used to integrate the hexagon implementation with the application's existing shape model.

## Object-Oriented Design

The project demonstrates core object-oriented programming concepts including:

- Encapsulation
- Inheritance
- Polymorphism
- Abstraction
- Interfaces
- Class relationships

Different geometric shapes share common behavior while implementing their own drawing and manipulation logic.

## Project Structure

```text
java-2d-drawing-application/
│
├── InitialProject/
│   └── Java application source code
│
├── .project
├── .settings/
└── README.md
```

## Key Concepts Demonstrated

- Java desktop application development
- Object-oriented programming
- MVC architecture
- Software design patterns
- Command-based Undo/Redo
- Shape manipulation
- File persistence
- Operation logging and replay
- Event-driven GUI development with Swing

## Author

**Nikolina Azaševac**

Information Systems Engineering  
University of Novi Sad — Faculty of Technical Sciences