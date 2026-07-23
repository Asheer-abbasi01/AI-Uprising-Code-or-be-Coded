import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class AIUprisingGame {
    public static int health = 100;
    public static int score = 0;
    public static Scanner scanner = new Scanner(System.in);
    public static Random rand = new Random();

    public static void main(String[] args) {
        displayText(ConsoleColors.BOLD_WHITE + ">>>>  Welcome to AI Uprising: Code or Be Coded!  <<<<"
                + ConsoleColors.RESET, 10);
        System.out.println();
        showMainMenu();
    }

    public static void displayText(String text, int delay) {
        for (char letter : text.toCharArray()) {
            System.out.print(letter);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }

    public static void showMainMenu() {
        while (true) {
            displayText(ConsoleColors.BOLD_GREEN + "\n--- Adventure Awaits ---" + ConsoleColors.RESET, 50);
            System.out.println(ConsoleColors.WHITE + "1. Start New Game" + ConsoleColors.RESET);
            System.out.println("2. View High Scores");
            System.out.println("3. Exit");
            System.out.print(ConsoleColors.BOLD_GREEN + "Enter your choice: " + ConsoleColors.RESET);

            int choice = getChoice();
            switch (choice) {
                case 1:
                    startGame();
                    break;
                case 2:
                    HighScores.displayHighScores();
                    break;
                case 3:
                    displayText(ConsoleColors.RED + "Exiting the game. Goodbye!" + ConsoleColors.RESET, 20);
                    System.exit(0);
                    break;
                default:
                    System.out
                            .println(ConsoleColors.YELLOW + "Invalid choice. Please try again." + ConsoleColors.RESET);
                    break;
            }
        }
    }

    public static void startGame() {
        System.out.println();
        displayText(
                ConsoleColors.YELLOW + "\nYou are a skilled hacker in a world dominated by AI." + ConsoleColors.RESET,
                10);
        displayText("Your mission: Infiltrate an AI-controlled facility to steal crucial data.", 10);
        System.out.println();

        laserGridNavigation();
    }

    public static void laserGridNavigation() {
        int x = 0, y = 0;
        boolean firstMove = true;
        char[][] laserGrid = generateLaserGrid();
        char[][] playerGrid = initializePlayerGrid(laserGrid);

        displayText(ConsoleColors.RED
                + "Caution: The AI perimeter is protected by lethal laser grids. Navigate carefully, your mission depends on it!"
                + ConsoleColors.RESET, 20);

        while (true) {
            displayGrid(firstMove ? laserGrid : playerGrid);
            System.out.println(
                    ConsoleColors.WHITE + "Current Health: " + ConsoleColors.GREEN + health + ConsoleColors.RESET);

            System.out.print(ConsoleColors.BLUE + "Your move (U for Up, D for Down, L for Left, R for Right): "
                    + ConsoleColors.RESET);
            char move = scanner.next().toUpperCase().charAt(0);

            int newX = x, newY = y;
            switch (move) {
                case 'U':
                    if (x > 0)
                        newX = x - 1;
                    break;
                case 'D':
                    if (x < 4)
                        newX = x + 1;
                    break;
                case 'L':
                    if (y > 0)
                        newY = y - 1;
                    break;
                case 'R':
                    if (y < 4)
                        newY = y + 1;
                    break;
                default:
                    System.out.println(ConsoleColors.YELLOW + "Invalid move. Try again." + ConsoleColors.RESET);
                    continue;
            }

            if (laserGrid[newX][newY] == 'L') {
                System.out.println(ConsoleColors.RED + "You hit a laser! Health -20." + ConsoleColors.RESET);
                health -= 20;
                if (health <= 0) {
                    System.out.println(
                            ConsoleColors.RED + "Mission failed! Your health dropped to 0." + ConsoleColors.RESET);
                    displayGrid(laserGrid);
                    break;
                }
            }

            playerGrid[x][y] = '.';
            x = newX;
            y = newY;
            playerGrid[x][y] = '*';

            if (firstMove) {
                firstMove = false;
                hideLasers(playerGrid);
            }

            if (x == 4 && y == 4) {
                displayGrid(playerGrid);
                firstChoice();
                break;
            }
        }
    }

    private static char[][] generateLaserGrid() {
        char[][] grid = new char[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                grid[i][j] = (rand.nextInt(3) == 0) ? 'L' : '.';
            }
        }
        grid[0][0] = 'S';
        grid[4][4] = 'E';
        return grid;
    }

    private static char[][] initializePlayerGrid(char[][] laserGrid) {
        char[][] playerGrid = new char[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                playerGrid[i][j] = (laserGrid[i][j] == 'L') ? '.' : ' ';
            }
        }
        playerGrid[0][0] = '*';
        return playerGrid;
    }

    private static void hideLasers(char[][] playerGrid) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (playerGrid[i][j] != 'E') {
                    playerGrid[i][j] = '.';
                }
            }
        }
    }

    public static void displayGrid(char[][] grid) {
        for (char[] row : grid) {
            for (char cell : row) {
                if (cell == '*') {
                    System.out.print(ConsoleColors.RED + cell + " " + ConsoleColors.RESET);
                } else if (cell == 'L') {
                    System.out.print(ConsoleColors.YELLOW + cell + " " + ConsoleColors.RESET);
                } else if (cell == 'S') {
                    System.out.print(ConsoleColors.BLUE + cell + " " + ConsoleColors.RESET);
                } else if (cell == 'E') {
                    System.out.print(ConsoleColors.GREEN + cell + " " + ConsoleColors.RESET);
                } else {
                    System.out.print(cell + " ");
                }
            }
            System.out.println();
        }
    }

    public static void firstChoice() {
        displayText(ConsoleColors.CYAN + "================================================================"
                + ConsoleColors.RESET, 5);
        displayText("    " + ConsoleColors.WHITE + "You arrive at the perimeter of the AI-controlled facility!"
                + ConsoleColors.RESET, 30);
        displayText(ConsoleColors.CYAN + "================================================================"
                + ConsoleColors.RESET, 5);
        System.out.println(ConsoleColors.WHITE + "Choose your approach:" + ConsoleColors.RESET);
        System.out.println("1. Attempt to hack the security interface.");
        System.out.println("2. Sneak past the guards using the shadows.");
        System.out.println("3. Search for an alternative entrance around the perimeter.");
        System.out.print(ConsoleColors.BOLD_GREEN + "Enter Your Choice: " + ConsoleColors.RESET);

        int choice = getChoice();

        switch (choice) {
            case 1:
                hackSecurityInterface();
                break;
            case 2:
                sneakPastGuards();
                break;
            case 3:
                searchAlternativeEntrance();
                break;
            default:
                invalidChoice();
                break;
        }
    }

    public static void hackSecurityInterface() {
        displayText(ConsoleColors.BOLD_RED + "\nYou decide to hack the security interface.", 10);
        System.out.println(ConsoleColors.RESET + "A series of codes appear on the screen.");
        System.out.println("You must enter the correct sequence to gain access.");
        System.out.print(ConsoleColors.BOLD_GREEN + "Enter the 4-digit code (e.g., 1234): " + ConsoleColors.RESET);

        String input = scanner.next();

        boolean success = rand.nextBoolean(); // 50% chance

        if (success) {
            System.out.println("Hack successful! The door slides open.");
            score += 20;
            System.out.println("Your Score: " + score);
            System.out.println("However, alarms are now sounding in the facility.");
            System.out.println();
            secondChoice();
        } else {
            System.out.println("Hack failed! Alarms are triggered.");
            System.out.println("AI drones are now on high alert.");
            health -= 20;
            System.out.println("Your Health: " + health);
            if (health <= 0) {
                gameOver();
            } else {
                System.out.println("You manage to escape back to the perimeter.");
                System.out.println();
                firstChoice();
            }
        }
    }

    public static void sneakPastGuards() {
        System.out.println("\nYou attempt to sneak past the guards.");
        System.out.println("Staying in the shadows, you move quietly.");
        boolean success = rand.nextBoolean(); // 50% chance

        if (success) {
            System.out.println("Success! You have sneaked past the guards undetected.");
            score += 15;
            System.out.println("Your Score: " + score);
            System.out.println();
            secondChoice();
        } else {
            System.out.println("A guard spots you!");
            System.out.println("You must act quickly.");
            System.out.println("1. Fight the guard.");
            System.out.println("2. Try to reprogram the guard.");
            System.out.println("3. Run away.");
            System.out.print(ConsoleColors.BOLD_GREEN + "Enter Your Choice: " + ConsoleColors.RESET);

            int choice = getChoice();

            switch (choice) {
                case 1:
                    fightGuard();
                    break;
                case 2:
                    reprogramGuard();
                    break;
                case 3:
                    runAway();
                    break;
                default:
                    invalidChoice();
                    break;
            }
        }
    }

    public static void fightGuard() {
        System.out.println("\nYou decide to fight the guard.");
        boolean success = rand.nextBoolean(); // 50% chance

        if (success) {
            System.out.println("You defeat the guard!");
            score += 10;
            System.out.println("Your Score: " + score);
            System.out.println("You continue deeper into the facility.");
            System.out.println();
            secondChoice();
        } else {
            System.out.println("The guard overpowers you.");
            health -= 30;
            System.out.println("Your Health: " + health);
            if (health <= 0) {
                gameOver();
            } else {
                System.out.println("You barely escape with your life.");
                System.out.println();
                firstChoice();
            }
        }
    }

    public static void reprogramGuard() {
        System.out.println("\nYou attempt to reprogram the guard using your hacking device.");
        boolean success = rand.nextBoolean(); // 50% chance

        if (success) {
            System.out.println("Reprogramming successful! The guard now assists you.");
            score += 15;
            System.out.println("Your Score: " + score);
            System.out.println("The guard helps you infiltrate the facility.");
            System.out.println();
            secondChoice();
        } else {
            System.out.println("Reprogramming failed! The guard attacks you.");
            health -= 20;
            System.out.println("Your Health: " + health);
            if (health <= 0) {
                gameOver();
            } else {
                System.out.println("You manage to escape back to the perimeter.");
                System.out.println();
                firstChoice();
            }
        }
    }

    public static void runAway() {
        System.out.println("\nYou choose to run away.");
        System.out.println("You manage to escape, but you lose valuable time.");
        health -= 10;
        System.out.println("Your Health: " + health);
        if (health <= 0) {
            gameOver();
        } else {
            System.out.println("You return to the perimeter to try a different approach.");
            System.out.println();
            firstChoice();
        }
    }

    public static void searchAlternativeEntrance() {
        System.out.println("\nYou decide to search for an alternative entrance.");
        System.out.println("After a thorough search, you find a hidden ventilation shaft.");
        System.out.println("Do you want to:");
        System.out.println("1. Enter to Solve Riddles.");
        System.out.println("2. Continue searching for another entrance.");
        System.out.print(ConsoleColors.BOLD_GREEN + "Enter Your Choice: " + ConsoleColors.RESET);

        int choice = getChoice();

        switch (choice) {
            case 1:
                if (riddleChallenge()) {
                    // Code to proceed with the mission
                    score += 20; // Reward the player
                    System.out.println("Your Score: " + score);
                    secondChoice();
                } else {
                    // Code to handle failure
                    health -= 20; // Penalize the player
                    System.out.println("Your Health: " + health);
                    if (health <= 0) {
                        gameOver();
                    }
                    firstChoice();
                }

                // riddleChallenge();
                // enterVentilationShaft();
                break;
            case 2:
                System.out.println("\nYou continue searching but find nothing else.");
                System.out.println("You return to the main entrance.");
                System.out.println();
                firstChoice();
                break;
            default:
                invalidChoice();
                break;
        }
    }

    // TEXT pART

    public static boolean riddleChallenge() {
        List<String> riddlesAndAnswers = new ArrayList<>();

        // Read riddles and answers from the file
        try (BufferedReader br = new BufferedReader(new FileReader("riddles.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                riddlesAndAnswers.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading riddles file: " + e.getMessage());
            return false; // Fail if riddles cannot be loaded
        }

        if (riddlesAndAnswers.isEmpty()) {
            System.out.println("No riddles found in the file.");
            return false; // Fail if there are no riddles
        }

        // Shuffle the riddles to ensure randomness
        Collections.shuffle(riddlesAndAnswers);

        Scanner scanner = new Scanner(System.in);
        int correctAnswers = 0;
        int riddlesAsked = 0;

        System.out.println("\nSolve at least one of the following riddles to proceed!");

        // Ask up to 4 riddles
        for (String entry : riddlesAndAnswers) {
            if (riddlesAsked == 4)
                break; // Limit to 4 riddles

            String[] parts = entry.split(":", 2);
            if (parts.length < 2)
                continue; // Skip malformed entries

            String riddle = parts[0].trim();
            String answer = parts[1].trim().toLowerCase();

            System.out.println("\nRiddle: " + riddle);
            System.out.print("Your Answer: ");
            String playerAnswer = scanner.nextLine().trim().toLowerCase();

            if (playerAnswer.equals(answer)) {
                System.out.println("Correct! You solved the riddle.");
                correctAnswers++;
                break; // Success, stop asking riddles
            } else {
                System.out.println("Wrong answer! Try the next one.");
            }

            riddlesAsked++;

        }

        if (correctAnswers > 0) {
            System.out.println("\nThe challenge is complete! You may proceed.");
            return true; // Player succeeds
        } else {
            System.out.println("\nYou failed to solve any riddle. The way remains locked.");
            return false; // Player fails
        }

    }

    // Riddles Challenge
    public static void secondChoice() {
        displayText(ConsoleColors.CYAN + "================================================================", 5);
        displayText("    You are now inside the AI-controlled facility.! ", 30);
        displayText("================================================================", 5);
        System.out.println(ConsoleColors.RESET + "Choose your next action:");
        System.out.println("1. Navigate the corridors to find the server room.");
        System.out.println("2. Look for allies among other hackers.");
        System.out.println("3. Attempt to access the main AI core directly.");
        System.out.print(ConsoleColors.BOLD_GREEN + "Enter Your Choice: " + ConsoleColors.RESET);

        int choice = getChoice();

        switch (choice) {
            case 1:
                navigateCorridors();
                break;
            case 2:
                findAllies();
                break;
            case 3:
                accessAICore();
                break;
            default:
                invalidChoice();
                break;
        }
    }

    public static void navigateCorridors() {
        System.out.println("\nYou decide to navigate the corridors to find the server room.");
        System.out.println("The corridors are filled with AI drones patrolling the area.");
        System.out.println("Do you want to:");
        System.out.println("1. Move quickly to avoid detection.");
        System.out.println("2. Hide and wait for drones to pass.");
        System.out.println("3. Disable the drones silently.");
        System.out.print(ConsoleColors.BOLD_GREEN + "Enter Your Choice: " + ConsoleColors.RESET);

        int choice = getChoice();

        switch (choice) {
            case 1:
                moveQuickly();
                break;
            case 2:
                hideAndWait();
                break;
            case 3:
                disableDrones();
                break;
            default:
                invalidChoice();
                break;
        }
    }

    public static void moveQuickly() {
        System.out.println("\nYou choose to move quickly through the corridors.");
        boolean success = rand.nextBoolean(); // 50% chance

        if (success) {
            System.out.println("You successfully navigate through the corridors without being detected.");
            score += 20;
            System.out.println("Your Score: " + score);
            System.out.println("You find the server room.");
            System.out.println();
            accessServerRoom();
        } else {
            System.out.println("You are spotted by a drone!");
            System.out.println("A firefight ensues.");
            health -= 25;
            System.out.println("Your Health: " + health);
            if (health <= 0) {
                gameOver();
            } else {
                System.out.println("You manage to escape but lose valuable time.");
                System.out.println();
                navigateCorridors();
            }
        }
    }

    public static void hideAndWait() {
        System.out.println("\nYou decide to hide and wait for the drones to pass.");
        System.out.println("After a tense few minutes, the drones move away.");
        score += 10;
        System.out.println("Your Score: " + score);
        System.out.println("You proceed further into the facility.");
        System.out.println();
        accessServerRoom();
    }

    public static void disableDrones() {
        System.out.println("\nYou attempt to disable the drones silently using your hacking device.");
        boolean success = rand.nextBoolean(); // 50% chance

        if (success) {
            System.out.println("Success! The drones are disabled.");
            score += 15;
            System.out.println("Your Score: " + score);
            System.out.println("You can now move freely through the corridors.");
            System.out.println();
            accessServerRoom();
        } else {
            System.out.println("Hack failed! The drones detect you.");
            System.out.println("You are forced to fight them.");
            health -= 20;
            System.out.println("Your Health: " + health);
            if (health <= 0) {
                gameOver();
            } else {
                System.out.println("You manage to fend off the drones but lose some health.");
                System.out.println();
                accessServerRoom();
            }
        }
    }

    public static void accessServerRoom() {
        displayText(ConsoleColors.CYAN + "================================================================", 5);
        displayText("    You have reached the server room.! ", 30);
        displayText("================================================================", 5);
        System.out.println("Choose your action:");
        System.out.println("1. Attempt to hack the main server.");
        System.out.println("2. Install a virus to disrupt AI operations.");
        System.out.println("3. Download critical data and prepare to escape.");
        System.out.print(ConsoleColors.BOLD_GREEN + "Enter Your Choice: " + ConsoleColors.RESET);

        int choice = getChoice();

        switch (choice) {
            case 1:
                hackMainServer();
                break;
            case 2:
                installVirus();
                break;
            case 3:
                downloadData();
                break;
            default:
                invalidChoice();
                break;
        }
    }

    public static void hackMainServer() {
        System.out.println("\nYou attempt to hack the main server.");
        System.out.println("A complex code sequence appears.");
        System.out.print(ConsoleColors.BOLD_GREEN + "Enter the 6-digit code (e.g., 654321): ");
        String input = scanner.next();
        // String input = scanner.next();
        boolean success = rand.nextBoolean(); // 50% chance

        if (success) {
            System.out.println("Hack successful! You gain control over the AI's mainframe.");
            score += 30;
            System.out.println("Your Score: " + score);
            System.out.println("You can now decide to shut down the AI or negotiate peace.");
            System.out.println();
            endGame();
        } else {
            System.out.println("Hack failed! Security protocols activate.");
            System.out.println("AI defenses are now targeting you.");
            health -= 30;
            System.out.println("Your Health: " + health);
            if (health <= 0) {
                gameOver();
            } else {
                System.out.println("You manage to escape the server room but lose health.");
                System.out.println();
                secondChoice();
            }
        }
    }

    public static void installVirus() {
        System.out.println("\nYou decide to install a virus to disrupt AI operations.");
        boolean success = rand.nextBoolean(); // 50% chance

        if (success) {
            System.out.println("Virus installed successfully! AI systems are now malfunctioning.");
            score += 25;
            System.out.println("Your Score: " + score);
            System.out.println("This will give humanity a chance to rebel.");
            System.out.println();
            endGame();
        } else {
            System.out.println("Installation failed! The virus backfires.");
            System.out.println("AI defenses are now on high alert.");
            health -= 25;
            System.out.println("Your Health: " + health);
            if (health <= 0) {
                gameOver();
            } else {
                System.out.println("You escape the server room but lose some health.");
                System.out.println();
                secondChoice();
            }
        }
    }

    public static void downloadData() {
        System.out.println("\nYou start downloading critical data.");
        System.out.println("The download will take some time, and you must defend against incoming AI attacks.");
        System.out.println("Do you want to:");
        System.out.println("1. Continue downloading and defend simultaneously.");
        System.out.println("2. Abort the download and escape immediately.");
        System.out.print(ConsoleColors.BOLD_GREEN + "Enter Your Choice: " + ConsoleColors.RESET);

        int choice = getChoice();

        switch (choice) {
            case 1:
                boolean success = rand.nextBoolean(); // 50% chance
                if (success) {
                    System.out.println("Download successful! You have the data needed to weaken the AI.");
                    score += 20;
                    System.out.println("Your Score: " + score);
                    System.out.println("You can now prepare to escape.");
                    System.out.println();
                    endGame();
                } else {
                    System.out.println("AI defenses overwhelm you during the download.");
                    health -= 40;
                    System.out.println("Your Health: " + health);
                    if (health <= 0) {
                        gameOver();
                    } else {
                        System.out.println("You manage to escape but lose significant health.");
                        System.out.println();
                        secondChoice();
                    }
                }
                break;
            case 2:
                System.out.println("\nYou abort the download and decide to escape.");
                System.out.println("You successfully leave the server room with some data.");
                score += 10;
                System.out.println("Your Score: " + score);
                System.out.println();
                endGame();
                break;
            default:
                invalidChoice();
                break;
        }
    }

    public static void findAllies() {
        System.out.println("\nYou look for allies among other hackers in the facility.");
        boolean success = rand.nextBoolean(); // 50% chance

        if (success) {
            System.out.println("You find and team up with fellow hackers.");
            score += 20;
            System.out.println("Your Score: " + score);
            System.out.println("With their help, you navigate the facility more effectively.");
            System.out.println();
            accessServerRoom();
        } else {
            System.out.println("No allies are found. You proceed alone.");
            score += 5;
            System.out.println("Your Score: " + score);
            System.out.println();
            accessServerRoom();
        }
    }

    public static void accessAICore() {
        System.out.println("\nYou attempt to access the main AI core directly.");
        System.out.println("This is the most dangerous route but could yield the highest rewards.");
        System.out.println("Do you want to:");
        System.out.println("1. Proceed with caution.");
        System.out.println("2. Use a brute force attack.");
        System.out.print(ConsoleColors.BOLD_GREEN + "Enter Your Choice: " + ConsoleColors.RESET);

        int choice = getChoice();

        switch (choice) {
            case 1:
                proceedWithCaution();
                break;
            case 2:
                bruteForceAttack();
                break;
            default:
                invalidChoice();
                break;
        }
    }

    public static void proceedWithCaution() {
        System.out.println("\nYou proceed with caution towards the AI core.");
        boolean success = rand.nextBoolean(); // 50% chance

        if (success) {
            System.out.println("You successfully reach the AI core without being detected.");
            score += 30;
            System.out.println("Your Score: " + score);
            System.out.println("You have full access to the AI’s main functions.");
            System.out.println();
            endGame();
        } else {
            System.out.println("AI defenses activate as you approach the core.");
            System.out.println("A fierce battle ensues.");
            health -= 35;
            System.out.println("Your Health: " + health);
            if (health <= 0) {
                gameOver();
            } else {
                System.out.println("You manage to escape the core area but lose health.");
                System.out.println();
                secondChoice();
            }
        }
    }

    public static void bruteForceAttack() {
        System.out.println("\nYou decide to use a brute force attack on the AI core.");
        System.out.println("This method is risky but could disable the AI quickly.");
        boolean success = rand.nextBoolean(); // 50% chance

        if (success) {
            System.out.println("Brute force attack successful! The AI core is now offline.");
            score += 40;
            System.out.println("Your Score: " + score);
            System.out.println("AI systems are down, freeing humanity from their control.");
            System.out.println();
            endGame();
        } else {
            System.out.println("Brute force attack failed! AI defenses are overwhelming.");
            health -= 50;
            System.out.println("Your Health: " + health);
            if (health <= 0) {
                gameOver();
            } else {
                System.out.println("You barely escape the facility with critical injuries.");
                System.out.println();
                secondChoice();
            }
        }
    }

    public static void endGame() {
        displayText(ConsoleColors.YELLOW + "Congratulations! You have completed your mission.", 20);
        System.out.println(ConsoleColors.RESET + "Final Health: " + health);
        System.out.println("Final Score: " + score);

        // Add player score to high scores
        System.out.print(ConsoleColors.BLUE + "Enter your name for the high score list: " + ConsoleColors.RESET);
        String playerName = scanner.next(); // Assumes you have a scanner object for input
        HighScores.addScore(playerName, score);

        // Display the updated high scores
        HighScores.displayHighScores();

        displayText(ConsoleColors.YELLOW + "Thank you for playing AI Uprising: Code or Be Coded!" + ConsoleColors.RESET,
                50);
        System.exit(0);
    }

    public static void gameOver() {
        displayText("\nGame Over!", 50);
        System.out.println("Your Final Health: " + health);
        System.out.println("Your Final Score: " + score);
        displayText(
                ConsoleColors.YELLOW + "\nThank you for playing AI Uprising: Code or Be Coded!" + ConsoleColors.RESET,
                30);
        System.exit(0);
    }

    public static void invalidChoice() {
        System.out.println(ConsoleColors.BOLD_RED + "\nInvalid Choice. Please try again." + ConsoleColors.RESET);
        System.out.println();
        secondChoice();
    }

    public static int getChoice() {
        int choice = -1;
        try {
            choice = scanner.nextInt();
        } catch (Exception e) {
            scanner.next(); // Clear invalid input
        }
        return choice;
    }
}