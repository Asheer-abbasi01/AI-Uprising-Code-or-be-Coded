# AI-Uprising-Code-or-be-Coded

A polished Java console adventure where the player navigates an AI-controlled facility, solves puzzles, and attempts to survive a dangerous mission.

## Overview

`AI Uprising: Code or Be Coded` is a clean, text-based Java game built for the terminal. The player explores a 5x5 laser grid, chooses story branches, and faces an optional riddle challenge on the way to the AI core.

## Key Features

- 5x5 laser grid exploration with health management.
- Branching narrative choices that affect mission progress.
- Randomized success and failure for replay value.
- Persistent high scores saved to a local file.
- ANSI-colored terminal output for better visual clarity.
- Integrated riddle challenge using `riddles.txt`.

## Riddle Content

This project includes a curated riddle set with short logic and wordplay challenges. The game loads and shuffles these questions from `riddles.txt`.

The included riddles cover:
- classic object and nature riddles (`fire`, `map`, `light`)
- everyday object puzzles (`keyboard`, `coin`, `egg`)
- time and motion brainteasers (`clock`, `future`)
- wordplay and pattern logic (`promise`, `echo`, `stamp`, `candle`)
- repeated challenge entries for familiarity and replayability (`footsteps`)

## Project Files

- `AIUprisingGame.java` - main game loop, story flow, grid navigation, and riddle handling.
- `HighScores.java` - loads, saves, sorts, and displays the best player scores.
- `ConsoleColors.java` - ANSI terminal color definitions used throughout the game.
- `riddles.txt` - stored riddle questions and answers in a simple `Question : answer` format.
- `highscores.txt` - auto-generated file for persistent score tracking.

## Requirements

- Java JDK installed and available on the command line.
- Terminal or command prompt capable of running Java programs.
- `riddles.txt` present in the same folder as the `.java` files.

## Run Instructions

From the project folder, compile and launch the game:

```bash
javac AIUprisingGame.java HighScores.java ConsoleColors.java
java AIUprisingGame
```

## Gameplay Summary

1. Select `1` to begin a new game.
2. Move through the laser grid using:
   - `U` = Up
   - `D` = Down
   - `L` = Left
   - `R` = Right
3. Avoid or survive laser hits to preserve health.
4. Continue through the story and make strategic choices.
5. Answer a randomized riddle when prompted.
6. Reach the AI core and save your score if successful.

## Notes

- Terminal color output works best in consoles that support ANSI escape codes.
- `highscores.txt` is created automatically when a game completes.
- No Java package structure is required; all source files are in the same folder.

## Tips

- Replay the game to explore alternate paths and outcomes.
- Use option `2` in the menu to review the current high scores.
- Ensure `riddles.txt` remains available in the game folder to avoid missing content.
