import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//experimenting
class WallsTest {

    @Test
    void createFrameTest() {
        int height = 5;
        char[][] map = new char[height][height];

        assertThrows(IllegalArgumentException.class , () -> new Walls(height, map, 0, '+'));
    }
}