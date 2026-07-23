import java.io.*;
import java.util.*;

public class HighScores {

    private static final String FILE_NAME = "highscores.txt";

    public static void main(String[] args) {
        // Example usage
        addScore("Alice", 150);
        displayHighScores();
    }

    // Method to add a new score to the high score list
    public static void addScore(String playerName, int score) {
        List<PlayerScore> scores = loadScores(); // Load existing scores
        scores.add(new PlayerScore(playerName, score)); // Add the new score

        // Sort scores in descending order and keep only the top 10
        scores.sort((a, b) -> b.score - a.score);
        if (scores.size() > 10) {
            scores = scores.subList(0, 10);
        }

        // Save updated scores back to the file
        saveScores(scores);
    }

    // Method to load scores from the file
    public static List<PlayerScore> loadScores() {
        List<PlayerScore> scores = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" - ");
                if (parts.length == 2) {
                    String name = parts[0];
                    int score = Integer.parseInt(parts[1]);
                    scores.add(new PlayerScore(name, score));
                }
            }
        } catch (IOException e) {
            System.out.println("No existing high scores found. A new file will be created.");
        }
        return scores;
    }

    // Method to save scores to the file
    public static void saveScores(List<PlayerScore> scores) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (PlayerScore ps : scores) {
                pw.println(ps.name + " - " + ps.score);
                System.out.println("Saving: " + ps.name + " - " + ps.score); // Debugging
            }
        } catch (IOException e) {
            System.out.println("Error saving high scores: " + e.getMessage());
        }
    }

    // Method to display high scores
    public static void displayHighScores() {
        List<PlayerScore> scores = loadScores();
        System.out.println("\n--- Top 10 High Scores ---");
        for (int i = 0; i < scores.size(); i++) {
            System.out.println((i + 1) + ". " + scores.get(i).name + " - " + scores.get(i).score);
        }
    }

    // Inner class to represent a player score
    static class PlayerScore {
        String name;
        int score;

        PlayerScore(String name, int score) {
            this.name = name;
            this.score = score;
        }
    }
}