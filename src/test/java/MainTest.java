import org.example.Main;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

}
