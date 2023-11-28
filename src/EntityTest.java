import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class EntityTest {


    //setInitialPosition tests
    @Test
    void lowHeightShouldThrowException() {

        int height = 5;
        char[][] map = new char[height][height];
        char symbol = 'x';

        assertThrows(IllegalArgumentException.class, () -> new Entity(map, symbol));
    }

    @Test
    void heightEdgeCaseShouldSetProperPosition() {
        //arrange
        int height = 6;
        char[][] map = new char[height][height];
        char symbol = 'x';

        //act
        var token = new Entity(map, symbol);

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
        assertThrows(IllegalStateException.class, () -> new Entity(map, symbol));
    }

    @Test
    void initialPositionShouldCopyPositionValuesNotReference() {
        //arrange
        int height = 20;
        char[][] map = new char[height][height];
        char symbol = 'x';

        //act
        var token = new Entity(map, symbol);

        //assert
        assertNotSame(token.position, token.initialPosition);
        assertSame(token.position.x, token.initialPosition.x);
        assertSame(token.position.y, token.initialPosition.y);
    }

    //move method tests
    @Test
    void moveWithSpaceShouldThrowException() {

        //arrange
        int height = 20;
        char[][] map = new char[height][height];
        char symbol = 'x';
        var token = new Entity(map, symbol);

        //act & assert
        assertThrows(IllegalArgumentException.class, () -> token.move(' ', map));
    }

    @Test
    void moveOutsideOfMapShouldNotHappen1() {
        //arrange
        int height = 6;
        char[][] map = new char[height][height];
        char symbol = 'x';
        var token = new Entity(map, symbol);

        token.position = new Coordinates(5, 5);

        //act
        token.move('d', map);
        token.move('s', map);

        //assert
        assertSame(5, token.position.x);
        assertSame(5 , token.position.y);
    }

    @Test
    void moveOutsideOfMapShouldNotHappen2() {
        //arrange
        int height = 6;
        char[][] map = new char[height][height];
        char symbol = 'x';
        var token = new Entity(map, symbol);

        token.position = new Coordinates(0, 5);

        //act
        token.move('d', map);
        token.move('w', map);

        //assert
        assertSame(0, token.position.x);
        assertSame(5 , token.position.y);
    }

    @Test
    void moveOutsideOfMapShouldNotHappen3() {
        //arrange
        int height = 6;
        char[][] map = new char[height][height];
        char symbol = 'x';
        var token = new Entity(map, symbol);

        token.position = new Coordinates(0, 0);

        //act
        token.move('a', map);
        token.move('w', map);

        //assert
        assertSame(0, token.position.x);
        assertSame(0 , token.position.y);
    }

    @Test
    void moveOutsideOfMapShouldNotHappen4() {
        //arrange
        int height = 6;
        char[][] map = new char[height][height];
        char symbol = 'x';
        var token = new Entity(map, symbol);

        token.position = new Coordinates(5, 0);

        //act
        token.move('a', map);
        token.move('s', map);

        //assert
        assertSame(5, token.position.x);
        assertSame(0 , token.position.y);
    }
}