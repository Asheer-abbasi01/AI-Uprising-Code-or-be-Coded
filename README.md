# AI-Uprising-Code-or-be-Coded

A text-based Java adventure game where the player navigates an AI-controlled facility, solves puzzles, and tries to survive the mission.

## Overview

`AI Uprising: Code or Be Coded` is a console game built with plain Java. The player starts at a control menu, moves through a laser grid, makes story decisions, and can face a riddle challenge before reaching the AI core.

## Key Features

- 5x5 laser grid explorer with health penalties.
- Multiple story choices that change the mission path.
- Randomized success/failure for many actions.
- Final score saving to a high score file.
- Colored terminal output using ANSI escape codes.
- Riddle challenge that loads questions from `riddles.txt`.

## Project Files

- `AIUprisingGame.java` - Main game program and flow logic.
- `HighScores.java` - Reads, writes, sorts, and displays the top high scores.
- `ConsoleColors.java` - ANSI color definitions for console output.
- `riddles.txt` - Riddle questions and answers used in the game.
- `highscores.txt` - Stored top scores when the game is played.

## Requirements

- Java JDK installed (`javac` and `java` commands available).
- Terminal or command prompt to run the game.
- `riddles.txt` must remain in the project folder.

## How to Run

Open a terminal in the project folder and run:

```bash
javac AIUprisingGame.java HighScores.java ConsoleColors.java
java AIUprisingGame
```

If the files are in `AI-Uprising-Code-or-be-Coded`, run the commands from inside that folder.

## How to Play

1. Choose `1` to start a new game.
2. Navigate the laser grid using:
   - `U` = Up
   - `D` = Down
   - `L` = Left
   - `R` = Right
3. Avoid lasers when possible or lose health.
4. Reach the exit and choose your next strategy.
5. Continue through the story choices, retrying if health remains.
6. If you succeed, enter your name to save your high score.

## Game Mechanics

- `health` starts at 100.
- Each laser hit subtracts health.
- Several actions use random success/failure.
- The mission can end in success or game over.
- Successfully finishing the game updates `highscores.txt`.

## Riddles

- `riddles.txt` stores riddles in this format:
  `Question : answer`
- The game randomly shuffles these and quizzes you.
- Enter the correct answer exactly as text.

## Notes

- The game uses ANSI colors, so colors work best in terminals that support them.
- `highscores.txt` is created automatically if it does not exist.
- This project uses the default Java package, so no package folder structure is required.

## Tips

- Play multiple times to see different outcomes because many choices are random.
- Use `2` from the main menu to view high scores.
- If the game crashes, make sure `riddles.txt` is present in the same folder as the `.java` files.
