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
        }
    }

