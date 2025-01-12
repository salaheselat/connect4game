package org.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        char[][] grid = new char[6][7];
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                grid[row][col] = ' ';
            }
        }
        displayBoard(grid);

        char player = 'X';
        int turn = 1;
        boolean winner = false;
        boolean blitzX = false;
        boolean blitzO = false;

        while (!winner && turn <= 42) {
            int selectedColumn = -1;
            boolean isPlayerMoveValid = false;
            boolean isPlayerBlitz = false;

            do {
                String playersNumber;
                if (player == 'X') {
                    playersNumber = "1";
                } else {
                    playersNumber = "2";
                }

                System.out.print("Player " + playersNumber + " " + player + " Select Column > ");
                String playerInput = stdin.nextLine();
                if (playerInput.equals("B") || playerInput.equals("b")) {
                    if ((player == 'X' && blitzX) || (player == 'O' && blitzO)) {
                        System.out.println("Blitz used!.");
                    } else {
                        isPlayerBlitz = applyBlitz(stdin, grid);
                        if (isPlayerBlitz) {
                            if (player == 'X') {
                                blitzX = true;
                            } else {
                                blitzO = true;
                            }
                        }
                        break;
                    }
                }else {
                    try {
                        selectedColumn = Integer.parseInt(playerInput);
                        isPlayerMoveValid = isMoveValid(selectedColumn, grid);
                        if (!isPlayerMoveValid) {
                            System.out.println("Please Choose a column between 0-6.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Choose Column 0-6.");
                    }
                }

            } while (!isPlayerMoveValid && !isPlayerBlitz);

            if (!isPlayerBlitz) {
                for (int row = grid.length - 1; row >= 0; row--) {
                    if (grid[row][selectedColumn] == ' ') {
                        grid[row][selectedColumn] = player;
                        break;
                    }
                }
            }

            displayBoard(grid);
            winner = determineWinner(player, grid);

            if (player == 'X') {
                player = 'O';
            } else {
                player = 'X';
            }
            turn++;
        }

        if (winner) {
            String playerNumber;
            char winningPlayer;
            if (player == 'O') {
                playerNumber = "1";
                winningPlayer = 'X';
            } else {
                playerNumber = "2";
                winningPlayer = 'O';
            }
            System.out.println("Player" + " " + playerNumber + " " + winningPlayer + " is the winner!");
        } else {
            System.out.println("Board is full game draw!");
        }
    }

    public static void displayBoard(char[][] grid) {
        System.out.print("");
        for (int col = 0; col < grid[0].length; col++) {
            System.out.print(col + " ");
        }
        System.out.println();
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == ' ') {
                    System.out.print("- ");
                } else {
                    System.out.print(grid[row][col] + " ");
                }
            }
            System.out.println();
        }
        System.out.print("");
        for (int col = 0; col < grid[0].length; col++) {
            System.out.print(col + " ");
        }
        System.out.println();
    }

    public static boolean isMoveValid(int column, char[][] grid) {
        if (column < 0 || column >= grid[0].length || grid[0][column] != ' ') {
            return false;
        }
        return true;
    }

    public static boolean determineWinner(char player, char[][] grid) {
        // horizontal win
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length - 3; col++) {
                if (grid[row][col] == player &&
                        grid[row][col + 1] == player &&
                        grid[row][col + 2] == player &&
                        grid[row][col + 3] == player) {
                    return true;
                }
            }
        }
        // vertical
        for (int row = 0; row < grid.length - 3; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == player &&
                        grid[row + 1][col] == player &&
                        grid[row + 2][col] == player &&
                        grid[row + 3][col] == player) {
                    return true;
                }
            }
        }
        // diagonal
        for (int row = 0; row < grid.length - 3; row++) {
            for (int col = 0; col < grid[0].length - 3; col++) {
                if (grid[row][col] == player &&
                        grid[row + 1][col + 1] == player &&
                        grid[row + 2][col + 2] == player &&
                        grid[row + 3][col + 3] == player) {
                    return true;
                }
            }
        }
        for (int row = 3; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length - 3; col++) {
                if (grid[row][col] == player &&
                        grid[row - 1][col + 1] == player &&
                        grid[row - 2][col + 2] == player &&
                        grid[row - 3][col + 3] == player) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean applyBlitz(Scanner stdin, char[][] grid) {
        boolean blitzUsed = false;
        while (!blitzUsed) {
            System.out.print("Blitz please select column > ");
            try {
                int column = Integer.parseInt(stdin.nextLine());
                if (column >= 0 && column < grid[0].length) {
                    for (int row = 0; row < grid.length; row++) {
                        grid[row][column] = ' ';
                    }
                    displayBoard(grid);
                    blitzUsed = true;
                } else {
                    System.out.println("Please Choose a column between 0-6.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Choose column 0-6.");
            }
        }
            return true;
        }
    }