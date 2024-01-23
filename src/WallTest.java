import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WallTest {

    @Test
    void overcrowdedMapShouldThrowException() {
        //arrange
        int height = 10;
        char[][] map = new char[height][height];
        char symbol = '+';
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < height; j++) {
                map[i][j] = 'S';
            }
        }

        //act & assert
        assertThrows(IllegalStateException.class, () -> new Wall(map, symbol));
    }

    @Test
    void smallEmptyMap_ShouldNot_ThrowException() {
        int height = 3;
        char[][] map = new char[height][height];
        char symbol = '+';

        //act & assert
        assertDoesNotThrow(() -> new Wall(map, symbol));
    }

    @Test
    void smallEmptyMap_withWallFrame_ShouldNot_ThrowException() {
        int height = 3;
        char[][] map = new char[height][height];
        char symbol = '+';
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < height; j++) {

                if (i == 0 || i == height - 1 || j == 0 || j == height - 1) {
                    map[i][j] = symbol;
                }
            }
        }

        //act & assert
        assertDoesNotThrow(() -> new Wall(map, symbol));
    }

    @Test
    void smallEmptyMap_withDifferentNeighbour_Should_ThrowException() {
        int height = 3;
        char[][] map = new char[height][height];
        map[0][2] = 'q';
        char symbol = '+';

        //act & assert
        assertThrows(IllegalStateException.class, () -> new Wall(map, symbol));
    }

}
