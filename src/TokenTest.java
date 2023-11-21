import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TokenTest {

    @Test
    void lowHeightShouldThrowException() {

        int height = 5;
        char[][] map = new char[height][height];
        char symbol = 'x';

        assertThrows(IllegalArgumentException.class, () -> new Token(height, map, symbol));
    }

    @Test
    void heightEdgeCaseShouldSetProperPosition() {
        //MOCK??
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
    void overCrowdedMapShouldThrowException() {
        
    }

}