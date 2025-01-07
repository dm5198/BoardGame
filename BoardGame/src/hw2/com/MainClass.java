package hw2.com;
//package hw2.com;

//package hw2.com;

import hw2.com.BoardGame;
import java.text.DecimalFormat;

public class MainClass {
    public static void main(String[] args) {
        String[] games = {"1", "2", "3", "4"};

        // Initialize array to store game results
        double[][][] gameResults = new double[5][5][2];

        int totalGamesPlayed = 0;

        for (int i = 1; i <= 4; i++) {
            BoardGame game = new BoardGame(i);
            double[][] gameResult = game.playGame();
            gameResults[i] = gameResult; // Store the game result
            totalGamesPlayed += 1000; // Here we have added the total games played from the BoardGame instance
        }

        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println("Total Results for " + totalGamesPlayed + " Games");
        System.out.printf("%-30s %-30s %-30s %-30s %-30s%n", "", "A avg moves / % win", "B avg moves / % win", "C avg moves / % win", "D avg moves / % win");
        for (int i = 1; i <= 4; i++) {
            System.out.printf("%-30s", games[i - 1]);
            for (int j = 0; j < i; j++) {
                System.out.printf("%-30s", df.format(gameResults[i][j][0]) + " moves " + df.format(gameResults[i][j][1] * 100) + "%");
            }
            System.out.println();
        }
    }
}










