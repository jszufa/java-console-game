import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    void checkRoadWithW() {
        int height = 6;
        char[][] map = new char[height][height];
        var token = new Token(6, map, 'x');
        assertEquals(2, token.checkRoad('w', token.position).x);
    }
}