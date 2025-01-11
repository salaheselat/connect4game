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

        while (turn <= 42) {
            int selectedColumn;
            boolean isPlayerMoveValid;
            do {
                String playersNumber;
                if (player == 'X') {
                    playersNumber = "1";
                } else {
                    playersNumber = "2";
                }
                System.out.print("Player " + playersNumber+ " " + player + " Select Column > ");
                selectedColumn = stdin.nextInt();
                isPlayerMoveValid = isMoveValid(selectedColumn, grid);
                if (!isPlayerMoveValid) {
                    System.out.println("Please Choose a column between 0-6.");
                }
            } while (!isPlayerMoveValid);

            for (int row = grid.length - 1; row >= 0; row--) {
                if (grid[row][selectedColumn] == ' ') {
                    grid[row][selectedColumn] = player;
                    break;
                }
            }
            displayBoard(grid);

            if (player == 'X') {
                player = 'O';
            } else {
                player = 'X';
            }
            turn++;
        }
        System.out.println("Board is full game draw!");
    }

        public static void displayBoard ( char[][] grid){
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
}

