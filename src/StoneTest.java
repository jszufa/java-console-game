import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class StoneTest {

    @Test
    void overcrowdedMapShouldThrowException() {
        //arrange
        int height = 10;
        char[][] map = new char[height][height];
        char symbol = 'O';
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < height; j++) {
                map[i][j] = 'S';
            }
        }

        //act & assert
        assertThrows(IllegalStateException.class, () -> new Stone(map, symbol));
    }
}
