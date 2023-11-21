import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//experimenting
class GameTest {

    @Test
    void createFrameTest() {
        int height = 5;
        char[][] map = new char[height][height];

        assertThrows(IllegalArgumentException.class , () -> Game.createFrame(height, map));
    }
}