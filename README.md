# BoardGame

## Project Overview
This project simulates a board game to calculate the probability of winning under various scenarios. The board game logic is implemented using a **doubly linked list** to represent the game board, and the simulation supports up to four players. Through this simulation, the program calculates the average number of moves required for a player to win and evaluates the game's dynamics under different player configurations.

## Key Features
1. **Board Representation**:
   - The game board is represented as a doubly linked list.
   - Each node contains information about the board square, such as its position and any players currently occupying it.

2. **Game Rules**:
   - Players start at the "Start" square and roll a dice to move 1–6 spaces.
   - If a player lands on an unoccupied square, they add the square's number to their score.
   - If a player lands on a square occupied by another player, the other player is sent back 7 spaces, and the current player adds the square's number to their score.
   - If a player moves back before the "Start" square, they return to "Start."
   - Players must reach the "End" square with a score of at least 44 points to win. Otherwise, they are sent back to "Start."

3. **Simulation**:
   - The program simulates 1,000 games for 1, 2, 3, and 4 players, recording the average number of moves needed for each player to win.
   - It outputs the results in a tabular format and prints the game board at specific intervals (e.g., every 100th game).

4. **Output**:
   - A detailed **Results Table** with average moves per player.
   - The final board state after specific games (e.g., Game 1, Game 101, Game 201, etc.).
   - Dynamic representation of player positions and board layout.

## Objectives
The primary goals of this project are:
- To represent a real-life board game using a **doubly linked list** data structure.
- To modify and enhance the provided `DoubleLinkedList` and `Node` classes.
- To employ simulation techniques to model the game dynamics.
- To practice writing modular and reusable classes.
- To gain experience working with existing code and debugging complex programs.

## Implementation Details
1. **Game Board**:
   - The board consists of nodes in a doubly linked list, each representing a square.
   - Each node contains:
     - The square’s number.
     - The players currently on the square (if any).

2. **Game Logic**:
   - Players roll a dice (values 1–6) and move accordingly.
   - Landing on occupied squares triggers the backtracking rules for other players.
   - Players must achieve 44 points or more to win when reaching the "End" square. Otherwise, they return to "Start."

3. **Simulation**:
   - The program simulates the game 1,000 times for each player configuration (1–4 players).
   - It calculates and outputs the average moves required for a win.

4. **Outputs**:
   - A **Results Table** showing the average number of moves for each player to win.
   - The board layout printed every 100 games for analysis.

## Example Results Table
```
RESULTS TABLE
Players    Avg Moves to Win (Player A)    Avg Moves to Win (Player B) ...
1          42.7                           -
2          45.1                           47.3
3          48.9                           50.4
4          52.3                           53.6
```

## How to Run
1. Compile and run the program using a Java IDE or terminal.
2. The program will simulate 1,000 games for each player configuration.
3. Outputs will be displayed in the console, including:
   - The **Results Table** summarizing the simulations.
   - The board layout at intervals (e.g., Game 1, Game 101, etc.).

## Notes
- The `DoubleLinkedList` class and `Node` class are heavily utilized and customized for this project.
- Plenty of loops and checks are used to ensure accurate simulation of game mechanics.
- Creativity in the display of the game board is encouraged; the format may vary from the provided sample but must clearly show player positions and scores.

## Acknowledgments
This project was inspired by the goal of applying advanced data structures to simulate real-world problems. Any assistance received has been acknowledged in the code, and all work is original, adhering to academic integrity standards.
