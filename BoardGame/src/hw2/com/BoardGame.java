package hw2.com;
import java.util.*;
import java.util.Random;

public class BoardGame {
    DoublyLinkedList<NumberOfPlays> gameBoard;
    int numberOfPlayers;
    Player[] players;
    Random random;
    int totalGamesPlayed = 0;
    String playerNames = "";

    BoardGame(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
        this.players = new Player[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            char playerLetter = (char) ('A' + i);
            players[i] = new Player(String.valueOf(playerLetter));
        }
        this.random = new Random();
        this.gameBoard = new DoublyLinkedList<>();
        initializeGameBoard();
    }

    private void initializeGameBoard() {
        Random rand = new Random();
        gameBoard.addFirst(null); // Header
        gameBoard.addLast(null); // Tail
        DoublyLinkedList.Node<NumberOfPlays> prev = gameBoard.head;
        DoublyLinkedList.Node<NumberOfPlays> next = new DoublyLinkedList.Node<NumberOfPlays>(null, null, null);

        for (int i = 0; i < 25; i++) {
            next = new DoublyLinkedList.Node<>(new NumberOfPlays(rand.nextInt(10) + 1), prev, null);

            if (prev != null) {
                prev.setNext(next);
            }

            prev = next;
        }

        gameBoard.tail.setPrev(next);
    }

    private void movePlayer(Player player, int steps) {
        int currentPosition = player.position;
        int newPosition = currentPosition + steps;

        DoublyLinkedList.Node<NumberOfPlays> oldPositionNode = gameBoard.head;
        for (int i = 0; i < currentPosition; i++) {
            oldPositionNode = oldPositionNode.next;
        }

        if (newPosition <= 25) { // Player is within the board
            DoublyLinkedList.Node<NumberOfPlays> newPositionNode = gameBoard.head;
            for (int i = 0; i < newPosition; i++) {
                newPositionNode = newPositionNode.next;
            }

            if (newPositionNode.getElement().players.isEmpty()) {
                player.position = newPosition;
                player.score += newPositionNode.getElement().num;
                newPositionNode.getElement().players.add(player);
                if (currentPosition != 0) {
                    oldPositionNode.getElement().players.remove(player);
                }
            } else {
                if (newPosition - 7 > 0) {
                    player.position = newPosition - 7;
                    DoublyLinkedList.Node<NumberOfPlays> newPositionNode2 = gameBoard.head;
                    for (int i = 0; i < newPosition - 7; i++) {
                        newPositionNode2 = newPositionNode2.next;
                    }
                    newPositionNode2.getElement().players.add(player);
                    oldPositionNode.getElement().players.remove(player);
                } else {
                    player.position = 0;
                    if (currentPosition != 0) {
                        oldPositionNode.getElement().players.remove(player);
                    }
                }
            }
        } else {
            if (player.score < 44) {
                player.position = 0;
                oldPositionNode.getElement().players.remove(player);
            } else {
                player.position = 25;
            }
        }
    }

    private int rollDice() {
        return random.nextInt(6) + 1;
    }

    private void resetPlayers() {
        for (Player player : players) {
            player.position = 0;
            player.score = 0;
        }

        DoublyLinkedList.Node<NumberOfPlays> temp = gameBoard.head;
        for (int i = 0; i < 25; i++) {
            temp = temp.next;
            temp.getElement().players = new LinkedList<>();
        }
    }

    public double[][] playGame() {
        double gameCount = 0;
        int[] movesPerGame = new int[numberOfPlayers];
        int[] winsPerGame = new int[numberOfPlayers];
        int tempMove = 0;
        int[] totalMoves = new int[numberOfPlayers];
        StringBuilder result = new StringBuilder();

        while (gameCount < 1000) {
            boolean gameFinish = false;
            while (!gameFinish) {
                for (int i = 0; i < numberOfPlayers; i++) {
                    if (players[i].position >= 19) {
                        int roll = gameBoard.size();
                        movePlayer(players[i], roll);
                        movesPerGame[i] += roll;
                        tempMove++;
                        if (players[i].position == 25 && players[i].score >= 44) {
                            winsPerGame[i]++;
                            gameFinish = true;
                            totalMoves[i] += tempMove;
                            tempMove = 0;
                            break;
                        }
                    } else {
                        int dice = rollDice();
                        movePlayer(players[i], dice);
                        movesPerGame[i] += dice;
                        tempMove++;
                        if (players[i].position == 25 && players[i].score >= 44) {
                            winsPerGame[i]++;
                            gameFinish = true;
                            totalMoves[i] += tempMove;
                            tempMove = 0;
                            break;
                        }
                    }
                }
            }
            

            if (gameCount % 100 == 0) {
                printBoard(result, gameCount);
                System.out.println(result);
                result.setLength(0); // Clear the StringBuilder for next use
            }
            gameCount++;

            resetPlayers();
            
        }

        double[][] results = new double[numberOfPlayers][2]; // [player][avg moves, win rate]
        for (int i = 0; i < numberOfPlayers; i++) {
            results[i][0] = (double) totalMoves[i] / gameCount; // Calculate average moves
            results[i][1] = (double) winsPerGame[i] / gameCount; // Calculate win rate
        }

        return results;
    }


    public void printBoard(StringBuilder result, double gameCount) {
        result.append("Game: ").append((int) gameCount+1).append(System.lineSeparator());
        DoublyLinkedList.Node<NumberOfPlays> current = gameBoard.head.next;

        while (current != null) {
            for (int i = 0; i < 25; i++) {
                if (i == 9 || i == 16) {
                    result.append(System.lineSeparator());
                }
                result.append("").append(current.getElement().num).append(" ");
                for (int j = 0; j < current.getElement().players.size(); j++) {
                    result.append(current.getElement().players.get(j).name);
                }
                current = current.next;
            }
        }
        result.append(System.lineSeparator());
    }
}



