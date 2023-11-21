import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//experimenting
class WallsTest {

    @Test
    void setInitialPositionWithLowHeight() {
        int height = 5;
        char[][] map = new char[height][height];

        assertThrows(IllegalArgumentException.class , () -> new Walls(height, map, 0, '+'));
    }

    void setInitialPositionWithBigHeight() {
        //Arrange
        int height = 5;
        char[][] map = new char[height][height];


    }
}