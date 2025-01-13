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
        boolean timeBombX = false;
        boolean timeBombO = false;

        while (!winner && turn <= 42) {
            int selectedColumn = -1;
            boolean isPlayerMoveValid = false;
            boolean isPlayerBlitz = false;
            boolean isPlayerTimeBomb = false;
            //player number
            do {
                String playersNumber;
                if (player == 'X') {
                    playersNumber = "1";
                } else {
                    playersNumber = "2";
                }
                // Handle moves like blitz and time bomb
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
                } else if (playerInput.equals("T") || playerInput.equals("t")) {
                    if ((player == 'X' && timeBombX) || (player == 'O' && timeBombO)) {
                        System.out.println("Time Bomb used!.");
                    } else {
                        isPlayerTimeBomb = applyTimeBomb(stdin, grid);
                        if (isPlayerTimeBomb) {
                            if (player == 'X') {
                                timeBombX = true;
                            } else {
                                timeBombO = true;
                            }
                        }
                        break;
                    }
                }else {
                    // handle choosing game columns
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

            } while (!isPlayerMoveValid && !isPlayerBlitz && !isPlayerTimeBomb);
            //drop into columns
            if (!isPlayerBlitz && !isPlayerTimeBomb) {
                for (int row = grid.length - 1; row >= 0; row--) {
                    if (grid[row][selectedColumn] == ' ') {
                        grid[row][selectedColumn] = player;
                        break;
                    }
                }
            }
            // display the board after dropping to columns
            displayBoard(grid);
            winner = determineWinner(player, grid); // check for possible winner
            // handle turn
            if (player == 'X') {
                player = 'O';
            } else {
                player = 'X';
            }
            turn++;
        }
        // display the results of the winner or draw if full
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
    // board function
    public static void displayBoard(char[][] grid) {
        // add columns number on top
        System.out.print("");
        for (int col = 0; col < grid[0].length; col++) {
            System.out.print(col + " ");
        }
        // show the grid
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
        // add column numbers to bottom to make it easier to read
        System.out.print("");
        for (int col = 0; col < grid[0].length; col++) {
            System.out.print(col + " ");
        }
        System.out.println();
    }
    // function checks if move within bound and  space is available in columns
    public static boolean isMoveValid(int column, char[][] grid) {
        if (column < 0 || column >= grid[0].length || grid[0][column] != ' ') {
            return false;
        }
        return true;
    }
    // find winner
    public static boolean determineWinner(char player, char[][] grid) {
        // check horizontal win
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
        // check vertical win
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
        // check diagonal win top left to right
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
        // check diagonal bottom left to right
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
    // blitz function to clear column
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
    // time bomb function to drop bomb into column
    public static boolean applyTimeBomb(Scanner stdin, char[][] grid) {
        boolean validTimeBomb = false;
        while (!validTimeBomb) {
            System.out.print("Time Bomb please select column > ");
            try {
                int column = Integer.parseInt(stdin.nextLine());
                if (column >= 0 && column < grid[0].length) {
                    for (int row = grid.length - 1; row >= 0; row--) {
                        if (grid[row][column] == ' ') {
                            grid[row][column] = '*';
                            displayBoard(grid);
                            validTimeBomb = true;

                            // Trigger explosion
                            for (int bomb = 0; bomb < grid.length; bomb++) {
                                if (grid[bomb][column] == '*') {
                                    // explode columns
                                    for (int surRow = -1; surRow <= 1; surRow++) {
                                        for (int surCol = -1; surCol <= 1; surCol++) {
                                            int bombRow = bomb + surRow;
                                            int bombCol = column + surCol;
                                            if (bombRow >= 0 && bombRow < grid.length && bombCol >= 0 && bombCol < grid[0].length) {
                                                grid[bombRow][bombCol] = ' ';
                                            }
                                        }
                                    }
                                    break;
                                }
                            }
                            displayBoard(grid); //display after bomb explodes
                            break;
                        }
                    }
                    if (!validTimeBomb) {
                        System.out.println("Choose an empty space.");
                    }
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