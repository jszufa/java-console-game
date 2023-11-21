import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class LevelTest {
    Random generator = new Random();

    @Test
    void lowHeightShouldThrowException() {

        int height = 5;

        assertThrows(IllegalArgumentException.class, () -> Level.createEmptyMap(height));
    }

    @Test
    void bigHeightShouldThrowException() {

        int height = 21;

        assertThrows(IllegalArgumentException.class, () -> Level.createEmptyMap(height));
    }

    @Test
    void randomMapFieldShouldBeSpace(){
        //arrange
        int height = 10;
        int rowIndex = generator.nextInt(height - 1);
        int colIndex = generator.nextInt(height - 1);
        char expectedResult = ' ';

        //act
        char[][] map = Level.createEmptyMap(height);
        int actualResult = map[rowIndex][colIndex];

        //Assert
        assertEquals(expectedResult, actualResult);
    }
}
