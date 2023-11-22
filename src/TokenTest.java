import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TokenTest {


    //setInitialPosition tests
    @Test
    void lowHeightShouldThrowException() {

        int height = 5;
        char[][] map = new char[height][height];
        char symbol = 'x';

        assertThrows(IllegalArgumentException.class, () -> new Token(height, map, symbol));
    }

    @Test
    void heightEdgeCaseShouldSetProperPosition() {
        //arrange
        int height = 6;
        char[][] map = new char[height][height];
        char symbol = 'x';

        //act
        var token = new Token(height, map, symbol);

        //assert
        assertTrue(token.position.x > 0 && token.position.x < 5);
        assertTrue(token.position.y > 0 && token.position.y < 5);
    }

    @Test
    void overcrowdedMapShouldThrowException() {
        //arrange
        int height = 10;
        char[][] map = new char[height][height];
        char symbol = 'x';
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < height; j++) {
                map[i][j] = 'S';
            }
        }

        //act & assert
        assertThrows(IllegalStateException.class, () -> new Token(height, map, symbol));
    }

    @Test
    void initialPositionShouldCopyPositionValuesNotReference() {
        //arrange
        int height = 20;
        char[][] map = new char[height][height];
        char symbol = 'x';

        //act
        var token = new Token(height, map, symbol);

        //assert
        assertNotSame(token.position, token.initialPosition);
        assertSame(token.position.x, token.initialPosition.x);
        assertSame(token.position.y, token.initialPosition.y);
    }

    //move method tests
    @Test
    void moveWithSpace() {

        //arrange
        int height = 20;
        char[][] map = new char[height][height];
        char symbol = 'x';
        var token = new Token(height, map, symbol);

        //act & assert
        assertThrows(IllegalArgumentException.class, () -> token.move(' ', map));
    }
}