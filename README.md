# Java Breakout Game - Refactored
Java Breakout game source code

http://zetcode.com/javagames/breakout/

This is an academic project aimed at refactoring the classic Breakout game by applying **Software Design Patterns** without adding new gameplay features.

##  Design Patterns Applied

This project was refactored to improve maintainability, loose coupling, and code reusability through the following design patterns:

1. **Singleton Pattern (`GameConfig`)**
   * Centralizes game configuration parameters (screen dimensions, tick speed/period, and boundaries).
   * Ensures thread-safe, single-instance global access to game settings across all entities.

2. **Factory Method Pattern (`GameObjectFactory`)**
   * Decouples object creation from the game board logic.
   * Centralizes the instantiation of game sprites (`BALL`, `PADDLE`, `BRICK`) through a unified factory method (`createGameObject`).
     
## Credits & Acknowledgments
- **Original Source Code:** ZetCode Java Breakout Game Tutorial.
- **Refactoring & Design Patterns:** Academic assignment for Software Design Patterns course.

![Breakout game screenshot](breakout_game.png)

