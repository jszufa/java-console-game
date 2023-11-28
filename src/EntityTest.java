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
        var entity = new Entity(map, symbol);

        //assert
        assertTrue(entity.position.x > 0 && entity.position.x < 5);
        assertTrue(entity.position.y > 0 && entity.position.y < 5);
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
        var entity = new Entity(map, symbol);

        //assert
        assertNotSame(entity.position, entity.initialPosition);
        assertSame(entity.position.x, entity.initialPosition.x);
        assertSame(entity.position.y, entity.initialPosition.y);
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
        var entity = new Entity(map, symbol);

        entity.position = new Coordinates(5, 5);

        //act
        entity.move('d', map);
        entity.move('s', map);

        //assert
        assertSame(5, entity.position.x);
        assertSame(5 , entity.position.y);
    }

    @Test
    void moveOutsideOfMapShouldNotHappen2() {
        //arrange
        int height = 6;
        char[][] map = new char[height][height];
        char symbol = 'x';
        var entity = new Entity(map, symbol);

        entity.position = new Coordinates(0, 5);

        //act
        entity.move('d', map);
        entity.move('w', map);

        //assert
        assertSame(0, entity.position.x);
        assertSame(5 , entity.position.y);
    }

    @Test
    void moveOutsideOfMapShouldNotHappen3() {
        //arrange
        int height = 6;
        char[][] map = new char[height][height];
        char symbol = 'x';
        var entity = new Entity(map, symbol);

        entity.position = new Coordinates(0, 0);

        //act
        entity.move('a', map);
        entity.move('w', map);

        //assert
        assertSame(0, entity.position.x);
        assertSame(0 , entity.position.y);
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