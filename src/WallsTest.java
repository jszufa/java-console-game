import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class WallsTest {

    Random generator = new Random();
    @Test
    void randomFrameField_ShouldBe_Wall() {

        //arrange
        int height = 6;
        int randomIndex = generator.nextInt(height - 1);
        char[][] map = new char[height][height];
        char symbol = '+';

        //act
        Walls walls = new Walls(map, symbol);

        //assert
        assertEquals(symbol, map[0][randomIndex]);
        assertEquals(symbol, map[randomIndex][0]);
        assertEquals(symbol, map[height-1][randomIndex]);
        assertEquals(symbol, map[randomIndex][height-1]);
    }
}
