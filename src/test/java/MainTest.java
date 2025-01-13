import org.example.Main;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    // test is move valid function
    @Test
    void testIsMoveValid() {
        char[][] grid = new char[6][7];
        // init grid
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                grid[row][col] = ' ';
            }
        }
        // check out of bound
        assertFalse(Main.isMoveValid(7, grid));
        assertFalse(Main.isMoveValid(8, grid));

        // check valid move (0-6 )
        assertTrue(Main.isMoveValid(0, grid));
        assertTrue(Main.isMoveValid(1, grid));

        // check invalid move when column full
        for (int row = 0; row < grid.length; row++) {
            grid[row][0] = 'X';
        }
        assertFalse(Main.isMoveValid(0, grid));
    }
    // test blitz function
    @Test
    void testApplyBlitz() {
        char[][] grid = new char[6][7];
        for (int row = 0; row < grid.length; row++) {
            grid[row][0] = 'X';
        }
        Main.applyBlitz(new java.util.Scanner("0\n"), grid);
        // check if blitz is applied to column
        for (int row = 0; row < grid.length; row++) {
            assertEquals(' ', grid[row][0]);
        }
    }
    // test horizontal win
@Test
void testDetermineWinnerHorizontal() {
    char[][] grid = new char[6][7];
    // init grid
    for (int row = 0; row < grid.length; row++) {
        for (int col = 0; col < grid[0].length; col++) {
            grid[row][col] = ' ';
        }
    }
    // horizontal win player x
    grid[0][0] = 'X';
    grid[0][1] = 'X';
    grid[0][2] = 'X';
    grid[0][3] = 'X';
    assertTrue(Main.determineWinner('X', grid));
    assertFalse(Main.determineWinner('O', grid));
}
// test vertical win
@Test
void testDetermineWinnerVertical() {
    char[][] grid = new char[6][7];
    // init grid
    for (int row = 0; row < grid.length; row++) {
        for (int col = 0; col < grid[0].length; col++) {
            grid[row][col] = ' ';
        }
    }
    //vertical win player o
    grid[0][0] = 'O';
    grid[1][0] = 'O';
    grid[2][0] = 'O';
    grid[3][0] = 'O';
    assertTrue(Main.determineWinner('O', grid));
    assertFalse(Main.determineWinner('X', grid));
}

// test diagonal win
    @Test
    void testDetermineWinnerDiagonal(){
        char[][] grid = new char[6][7];
        // init grid
        for (int row = 0; row < grid.length; row++){
            for (int col = 0; col< grid.length; col++){
                grid[row][col] = ' ';
            }
        }
        // diagonal win player x
        grid[0][0] = 'X';
        grid[1][1] = 'X';
        grid[2][2] = 'X';
        grid[3][3] = 'X';
        assertTrue(Main.determineWinner('X', grid));
        assertFalse(Main.determineWinner('O', grid));
    }

    @Test
    void testApplyTimeBomb(){

    }


}
